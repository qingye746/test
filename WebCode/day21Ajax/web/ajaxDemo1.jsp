<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/23 0023
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function fun() {
            //使用$.ajax()发送异步请求
            $.ajax({
                url:"ajaxServlet" , // 请求路径
                type:"POST" , //请求方式
                //data: "username=jack&age=23",//请求参数
                data:{"username":"jack","age":23},//json写法
                async:true,//请求类型 ：true默认同步
                success:function (data) {
                    alert(data);
                },//响应成功后的回调函数
                error:function () {
                    alert("出错啦...")
                },//表示如果请求响应出现错误，会执行的回调函数

                dataType:"text"//设置接受到的响应数据的格式
            });
        }
        function fun2(){
        $.get("ajaxServlet",{username:"rose"},function (data) {
            alert(data)
        },"text")
        }
        function fun3(){
            $.post("ajaxServlet",{username:"rose"},function (data) {
                alert(data)
            },"text")
        }
    </script>
</head>
<body>

</body>
</html>
