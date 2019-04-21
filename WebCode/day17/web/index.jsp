<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/15 0015
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
${3+4}
<%
    request.setAttribute("zz","666");
    session.setAttribute("aa","777");
    application.setAttribute("bb0","888");
    pageContext.setAttribute("oo","999");
%>
${zz}
${aa}
${bb0}
</body>
</html>
