<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/23 0023
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="account/findAll">查询所有账户</a>
    <h3>保存账户</h3>
    <form action="account/saveAccount" method="post">
        姓名：<input type="text" name="name"/><br>
        金额：<input type="text" name="money"><br>
        <input type="submit" value="保存">
    </form>
</body>
</html>
