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
 * @Date: 2019/2/14 0014 15:52
 * @Version 1.0
 */
@WebServlet("/logindemo")
public class logindemo extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String user = request.getParameter("user");
        String password = request.getParameter("password");
        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        String check_session = session.getAttribute("check_session").toString();
        session.removeAttribute("check_session");
        if(checkCode!=null&&check_session.equalsIgnoreCase(checkCode)){
            if("zhangsan".equals(user)&&"123".equals(password)){
                session.setAttribute("username",user);
                response.sendRedirect(request.getContextPath()+"/success.jsp");
            }else{
                request.setAttribute("login_error","用户名或密码有误请重新输入");
                request.getRequestDispatcher("/fromdemo.jsp").forward(request,response);
            }
        }else{
            request.setAttribute("cc_error","验证码有误请重新输入");
            request.getRequestDispatcher("/fromdemo.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
