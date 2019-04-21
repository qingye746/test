package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: qingye
 * @Date: 2019/2/28 0028 20:57
 * @Version 1.0
 */

public class BaseServlet extends HttpServlet {
    /**
     * 使用对象方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf('/') + 1);

        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);


            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化json并返回
     * @param obj
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void writevalue(Object obj,HttpServletResponse resp) throws ServletException,IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(),obj);
    }

    /**
     * 序列化返回String类型的json数据
     * @param obj
     * @param resp
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String writevalueAsString(Object obj,HttpServletResponse resp) throws ServletException,IOException {
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        return mapper.writeValueAsString(obj);
    }

    /**
     * 校验验证码
     * @param name
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public boolean  checkverificationCode(String name,HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException {
        //验证校验
        String check = req.getParameter(name);
        //从session中获取验证码
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            ResultInfo info = new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码有误");
            //将info数据转换为json对象并返回
            //设置json的响应类型
            writevalue(info,resp);

            return false;
        }else {
            return true;
        }

    }
}
