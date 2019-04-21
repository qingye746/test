<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/13 0013
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
</head>
<body>
<h1><%= request.getSession().getAttribute("username") %>欢迎您</h1>
</body>
</html>
