<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/23 0023
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="true" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="Js/jquery-3.3.1.js"></script>
    <script>
$(function () {
    $("#username").blur(function () {
        //alert("11111")
        //获取文本框输入的值
        var username = $(this).val();

        //发送ajax请求
        //期望服务器响应回的数据格式：{"userExsit":true,"msg":"此用户名太受欢迎,请更换一个"}
        //                         {"userExsit":false,"msg":"用户名可用"}
        $.get("findUserServlet",{username:username},function (date) {
           alert(date.msg);
            var span = $("#s_username");
            if(date.userExsit){
                span.css("color","red");
                span.html(date.msg)
            }else {
                span.css("color","green");
                span.html(date.msg)
            }
        })
    })
})
    </script>
</head>
<body>
<from>
    <input type="text" id="username" name="username" placeholder="请输入用户名">
    <span id="s_username"></span>
    <br>
    <input type="password" name="password" placeholder="请输入密码">
    <br>
    <input type="submit" value="注册"><br>

</input>
</from>
</body>
</html>
