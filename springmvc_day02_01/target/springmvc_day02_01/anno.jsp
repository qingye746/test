<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/21 0021
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--常用的注解--%>

<a href="anno/testRequestParam?name=哈哈">RequestParam</a>

<form action="anno/testRequestBody" method="post">

    用户姓名：<input type="text" name="uname"/><br/>
    用户年龄：<input type="text" name="age"/><br/>


    <input type="submit" value="提交"/>
</form>
</body>
</html>
