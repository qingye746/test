<%@ page import="cn.itcase.item.user" %>
<%@ page import="java.util.Date" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/15 0015
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    user user = new user();
    user.setName("张三");
    user.setAge(22);
    user.setBirthday( new Date());
    request.setAttribute("u",user);
%>
${u.getday()}
</body>
</html>
