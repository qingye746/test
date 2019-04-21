package cn.itcase.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: qingye
 * @Date: 2019/2/19 0019 11:42
 * @Version 1.0
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //0.强制转换
        HttpServletRequest  req = (HttpServletRequest) request;
        //1.获取资源请求路径
        String uri = req.getRequestURI();
        //2.判断是否包含登录相关资源路径,要注意排除掉 css/js/图片/验证码等资源
        if(uri.contains("/login.jsp")||uri.contains("/loginServlet")|| uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/checkCodeServlet")){
            //放行
            filterChain.doFilter(request,response);
        }else {
            //不包含，需要验证用户是否登录
            //3.从获取session中获取user
            Object user = req.getSession().getAttribute("user");
            if(user!=null){
                filterChain.doFilter(request, response);
            }else{
                //没有登录。跳转登录页面
                request.setAttribute("login_msg","您尚未登录，请登录");
                request.getRequestDispatcher("/login.jsp").forward(req,response);
            }
        }

    }

    @Override
    public void destroy() {

    }
}
