package cn.itcase.web.Servlet;

import cn.itcase.Service.Impl.UserServiceImpl;
import cn.itcase.Service.UserService;
import cn.itcase.domain.PageBean;
import cn.itcase.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/2/18 0018 14:59
 * @Version 1.0
 */
@WebServlet("/findUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        //当前页码
        String currentPage = request.getParameter("currentPage");
        //每页展示行数
        String rows = request.getParameter("rows");
        //创建service调用
        if(currentPage == null ||"".equals(currentPage)){
            currentPage ="1";
        }

        if(rows == null || "".equals(rows)){
            rows = "5";
        }
        Map<String, String[]> condition = request.getParameterMap();
        UserService service = new UserServiceImpl();
        PageBean<User> pb = service.findUserByPage(currentPage, rows,condition);
        request.setAttribute("pb",pb);
        request.getRequestDispatcher("/list.jsp").forward(request,response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
