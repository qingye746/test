<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/3/22 0022
  Time: 16:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.min.js"></script>
    <script>
        $(window.onload=(function () {
            $("#btn").click(function () {
                $.ajax({
                    url:"user/testAjax",
                    contentType:"application/json;charset=utf-8",
                    data:'{"uname":"hehe","password":"123","age":30}',
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        // data服务器端响应的json的数据，进行解析
                      alert(data);
                        alert(data.uname);
                        alert(data.password);
                        alert(data.age);
                    }
                })
            })
        }))
    </script>
</head>
<body>

<a href="/user/testString">testString</a>
<br>
<a href="/user/testVoid">testVoid</a>
<br>
<a href="/user/modelAndView">modelAndView</a>
<br>
<a href="/user/testFR">testVoid</a>
<br>
<button id="btn">发送ajax请求</button>
</body>
</html>
