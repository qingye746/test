<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/23 0023
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        //定义的基本格式
        var person = {
            "name": "张三",
            age: 23,
            "gender": true
        };
        //获取name值
        var name = person.name;
       // var name = person["name"];
       // alert(name);

        //嵌套格式
        var persons ={
            pesons1: [{"name": "张三", "age": 23, "gender": true},
             {"name": "李四", "age": 26, "gender": false},
                {"name": "王五", "age": 18, "gender": true} ],
            pesons2:[{"name": "清叶", "age": 13, "gender": true},
                {"name": "风雪", "age": 22, "gender": false},
                {"name": "黎光", "age": 15, "gender": true} ]
        };
        var name1 = persons.pesons1[0].name;
       // alert(name1);
        var ps =[
            {"name": "张三", "age": 23, "gender": true},
            {"name": "李四", "age": 26, "gender": false},
            {"name": "王五", "age": 18, "gender": true}
        ];
        var name2 = ps[1].name;
       // alert(name2);
        //遍历person对象中的所有键与值
       /* for(var k in person){
            alert(k+":"+person[k])
        }*/
        //遍历ps数组对象中的所有键与值
        for(var i = 0;i <ps.length  ; i++ ){
            var  p = ps[i];
            for(var k in p){
                alert(k+":"+p[k])
            }
        }
    </script>
</head>
<body>

</body>
</html>
