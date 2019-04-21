package cn.itcase.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: qingye
 * @Date: 2019/2/19 0019 9:49
 * @Version 1.0
 */
@WebFilter(value = "/index.jsp",dispatcherTypes = DispatcherType.FORWARD)
public class FilterDemo2 implements Filter {
    public FilterDemo2() {
        System.out.println("构造方法被执行了");
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init被执行了");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterDemo2被执行了");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("FilterDemo2回来了");
    }

    @Override
    public void destroy() {
        System.out.println("destroy被执行了");
    }
}
