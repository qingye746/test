<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/12 0012
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<%
    //    response.setContentType("text/html;charset=utf-8");
//    //设置响应的消息体的数据格式以及编码
    boolean flag = false;
    Cookie[] cookies = request.getCookies();
    //获取所有cookies
    if (cookies != null && cookies.length > 0) {
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if ("lastTime".equals(name)) {
                flag = true;
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                String sdf_date = sdf.format(date);
                sdf_date = URLEncoder.encode(sdf_date, "utf-8");
                //URLEncoder编码之中没有空格
                cookie.setValue(sdf_date);
                //发送当前时间
                cookie.setMaxAge(60 * 60 * 24);
                //cookie存活时间时间为一天
                response.addCookie(cookie);
                String value = cookie.getValue();
                value = URLDecoder.decode(value, "utf-8");
                //URLEncoder解码
%>
<h1>欢迎回来,您上次访问时间为:<%=value%></h1>
<%

                //网页上显示显示信息
                break;
            }
        }
    }
    if (cookies == null || cookies.length == 0 || flag == false) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String sdf_date = sdf.format(date);
        sdf_date = URLEncoder.encode(sdf_date, "utf-8");
        Cookie cookie = new Cookie("lastTime", sdf_date);
        cookie.setValue(sdf_date);
        //发送当前时间
        cookie.setMaxAge(60 * 60 * 24);
        response.addCookie(cookie);
        String value = cookie.getValue();
%>
<h1>您好,欢迎您首次访问</h1>
<%

    }

%>

</body>
</html>
