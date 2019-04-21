<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/13 0013
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>登录</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!--<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
    <!--<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>-->
    <!--<![endif]&ndash;&gt;-->
    <style>
        #form1 {
            margin-left: 600px;
            margin-top: 100px;
        }

        #error {
            color: red;
        }
    </style>
    <script>
        window.onload = function () {
            var img = document.getElementById("checkcode");
            img.onclick = function () {
                var date = new Date().getTime();
                img.src = "/day16/verificationCode?" + date;
            }
        }
    </script>

</head>
<body>
<form action="/day16/loginServlet" method="post" id="form1">
    <div class="form-group">
        <label for="exampleInputname">用户名</label>
        <input type="text" class="form-control" id="exampleInputname" name="username" placeholder="请输入用户名">
    </div>
    <br>
    <div class="form-group">
        <label for="exampleInputPassword1">密码</label>
        <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="请输入密码">
    </div>
    <br>
    <div class="form-group">
        <label>验证码</label>
        <input type="text" class="form-control" name="checkCode" placeholder="请输入验证码">
    </div>
    <br>
    <div>
        <img src="/day16/verificationCode" id="checkcode">
        <button type="submit" class="btn btn-default">登录</button>
    </div>


</form>

<div id="error"><%=request.getAttribute("cc_error") == null ? "" : request.getAttribute("cc_error")%>
</div>
<div id="error"><%=request.getAttribute("login_error") == null ? "" : request.getAttribute("login_error") %>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/js/jquery-3.2.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="/js/bootstrap.min.js"></script>
</body>
</html>
