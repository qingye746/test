package cn.itcase.web.Filter;

import com.sun.jdi.Value;

import javax.servlet.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/19 0019 16:33
 * @Version 1.0
 */
public class SensitiveWordsFilter implements Filter {
    private List<String> list = new ArrayList<String>();//敏感词汇集合

    @Override
    public void init(FilterConfig Config) throws ServletException {
        //1.获取文件真实路径
        try {
            ServletContext servletContext = Config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
            BufferedReader br = new BufferedReader(new FileReader(realPath));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.将文件的每一行数据添加到list中
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //1.创建代理对象，增强getParameter方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), response.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if (method.getName().equals("getParameter")) {
                    //增强返回值
                    //获取返回值
                    String value = (String) method.invoke(request, response);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                }

                return method.invoke(request, args);

            }
        });
        //放行
        filterChain.doFilter(proxy_req, response);
    }

    @Override
    public void destroy() {

    }
}
