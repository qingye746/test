package cn.itcase.web.Servlet;

import cn.itcase.Service.Impl.UserServiceImpl;
import cn.itcase.Service.UserService;
import cn.itcase.domain.User;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @Author: qingye
 * @Date: 2019/2/16 0016 9:43
 * @Version 1.0
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        //获取用户填入的验证码
        String verifycode = request.getParameter("verifycode");
        //验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = session.getAttribute("CHECKCODE_SERVER").toString();
        session.removeAttribute("CHECKCODE_SERVER");
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            request.setAttribute("login_msg","验证码有误");
            //验证码有误，转发到原始登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        //封装user对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用survice查询
        UserService service = new UserServiceImpl();
        User loginuser = service.login(user);
        if(loginuser!=null){
            //登录成功
            //将用户存入session
            session.setAttribute("user",loginuser);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");

        }else{
            request.setAttribute("login_msg","用户名或密码错误");
            //验证码有误，转发到原始登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
