package cm.itcase.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @Author: qingye
 * @Date: 2019/2/13 0013 16:25
 * @Version 1.0
 */
@WebServlet("/sessionDemo1")
public class sessionDemo1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("msg","hello");
        System.out.println(session);
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setMaxAge(60*5);
        response.addCookie(cookie);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
