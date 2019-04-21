package cm.itcase.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * @Author: qingye
 * @Date: 2019/2/12 0012 21:39
 * @Version 1.0
 */
@WebServlet("/cookieTest")
public class cookieTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //设置响应的消息体的数据格式以及编码
        boolean flag = false;
        Cookie[] cookies = request.getCookies();
        //获取所有cookies
        if(cookies!=null&&cookies.length>0){
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if("lastTime".equals(name)){
                    flag =true;
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                    String sdf_date = sdf.format(date);
                    sdf_date   = URLEncoder.encode(sdf_date, "utf-8");
                    //URLEncoder编码之中没有空格
                    cookie.setValue(sdf_date);
                    //发送当前时间
                    cookie.setMaxAge(60*60*24);
                    //cookie存活时间时间为一天
                    response.addCookie(cookie);
                    String value = cookie.getValue();
                    value = URLDecoder.decode(value,"utf-8");
                    //URLEncoder解码
                    response.getWriter().write("<h1>欢迎回来,您上次访问时间为:"+value+"</h1>");
                    //网页上显示显示信息
                    break;
                }
            }
        }
        if(cookies==null||cookies.length==0||flag==false){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            String sdf_date = sdf.format(date);
            sdf_date = URLEncoder.encode(sdf_date, "utf-8");
            Cookie cookie = new Cookie("lastTime",sdf_date );
            cookie.setValue(sdf_date);
            //发送当前时间
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            String value = cookie.getValue();
            response.getWriter().write("<h1>您好,欢迎您首次访问</h1>");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
