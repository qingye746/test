<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/14 0014
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>

        #error {
            color: red;
        }
    </style>
    <script>
        window.onload =function () {
            var  img = document.getElementById("imgcode");
            img.onclick = function () {
               var date =new Date().getTime();
                img.src = "/day16/verificationCode?"+date;
            }
        }
    </script>
</head>
<body>
<form action="/day16/logindemo" method="post">
    <tr>
        <td>姓名：</td>
        <td><input type="text" name="user"></td>
    </tr>
    <br>
    <br>
    <tr>
        <td>密码：</td>
        <td><input type="password" name="password"></td>
    </tr>
    <br>
    <br>
    <tr>
        <td>验证码：</td>
        <td><input type="text" name="checkCode"></td>
    </tr>
    <br>
    <br>
    <tr>
        <td><img src="/day16/verificationCode" id="imgcode"></td>
        <td><button type="submit">登录</button></td>
    </tr>
    <br>
    <div id="error"><%=request.getAttribute("cc_error") == null ? "" : request.getAttribute("cc_error")%>
    </div>
    <div id="error"><%=request.getAttribute("login_error") == null ? "" : request.getAttribute("login_error") %>
    </div>
</form>
</body>
</html>
