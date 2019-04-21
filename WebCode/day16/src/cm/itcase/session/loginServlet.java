package cm.itcase.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: qingye
 * @Date: 2019/2/13 0013 19:13
 * @Version 1.0
 */
@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置requsest编码
        request.setCharacterEncoding("utf-8");
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        //获取生成的验证码
        HttpSession session = request.getSession();
        String check_session = session.getAttribute("check_session").toString();
        //删除生成的验证码
        session.removeAttribute("check_session");
        //判断验证码
        if (checkCode != null &&check_session.equalsIgnoreCase(checkCode)){
            if("zhangsan".equals(username)&&"123".equals(password)){
                //登录成功
                //存储信息，用户信息
                session.setAttribute("username",username);
                //重定向
                response.sendRedirect(request.getContextPath()+"/success.jsp");

            }else{
                //验证码错误
                request.setAttribute("login_error","登录名或用户名错误");
                //跳转到登录页面
                request.getRequestDispatcher("/checkcode1.jsp").forward(request,response);
            }

        }else{
            //验证码错误
            request.setAttribute("cc_error","验证码错误");
            //跳转到登录页面
            request.getRequestDispatcher("/checkcode1.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
