package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * @Date: 2019/2/28 0028 20:56
 * @Version 1.0
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    //声明UserService业务对象
    private UserServiceImpl service = new UserServiceImpl();

    /**
     * 注册方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证校验
        boolean cheflag = checkverificationCode("check", request, response);
        if (cheflag) {
            //获取数据
            Map<String, String[]> map = request.getParameterMap();
            //封装对象
            User user = new User();
            //调用beanutils.populate封装
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            //service调用方法,返回布尔值判断是否登录成功
            boolean flag = service.regist(user);
            ResultInfo info = new ResultInfo();
            //响应结果
            if (flag) {
                info.setFlag(true);
            } else {
                info.setFlag(false);
                info.setErrorMsg("用户信息已存在");
            }
            //将info数据转换为json对象并返回
            writevalue(info, response);
        }else {
            return;
        }


    }

    /**
     * 登录方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证校验
        boolean cheflag = checkverificationCode("check", request, response);
        if (cheflag) {
            Map<String, String[]> map = request.getParameterMap();
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            User u = service.login(user);
            ResultInfo info = new ResultInfo();
            if (u == null) {
                info.setFlag(false);
                info.setErrorMsg("用户名或者密码错误");

            }
            //判断用户是否激活
            if (u != null && !"Y".equals(u.getStatus())) {
                info.setFlag(false);
                info.setErrorMsg("该用户尚未激活");
            }
            //用户符合条件
            if (u != null && "Y".equals(u.getStatus())) {
                request.getSession().setAttribute("user", u);
                info.setFlag(true);

            }
            writevalue(info,response);
        }else {
            return;
        }
    }

    /**
     * 查找单个用户方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("user");
        writevalue(user, response);

    }

    /**
     * 注销方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //销毁服务端数据
        request.getSession().invalidate();
        //重定向
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    /**
     * 激活方法
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null) {
            boolean flag = service.active(code);
            String msg = null;
            if (flag) {
                msg = "激活成功,请<a href = 'login.html'> 登录</a>";
            } else {
                msg = "激活失败,请联系管理员";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}