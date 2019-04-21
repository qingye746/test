package cn.itcase.web.Servlet;

import cn.itcase.DAO.Impl.UserDaoImpl;
import cn.itcase.Service.Impl.UserServiceImpl;
import cn.itcase.Service.UserService;
import cn.itcase.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: qingye
 * @Date: 2019/2/15 0015 18:54
 * @Version 1.0
 */
@WebServlet("/userListServlet")
public class userListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       //调用UserService完成查询
        UserService service = new UserServiceImpl();
        List<User> users = service.findAll();
        //将List存入request域中
        request.setAttribute("users",users);
        //转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
