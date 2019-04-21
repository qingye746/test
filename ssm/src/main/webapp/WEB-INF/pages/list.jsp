<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/23 0023
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"  isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>查询所有账户</h3>
<c:forEach var="account" items="${list}">
    ${account.id} ${account.name} ${account.money}
    <br>
</c:forEach>
</body>
</html>
