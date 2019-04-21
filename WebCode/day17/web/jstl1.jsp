<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/15 0015
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>

<body>
<%
    request.setAttribute("number",4);
%>
<c:if test="${number%2==0}">
    ${number}为偶数
</c:if>
<c:choose>
    <c:when test="${number==1}">星期一</c:when>
    <c:when test="${number==2}">星期二</c:when>
    <c:when test="${number==3}">星期三</c:when>
    <c:when test="${number==4}">星期四</c:when>
    <c:when test="${number==5}">星期五</c:when>
    <c:when test="${number==6}">星期六</c:when>
    <c:when test="${number==7}">星期日</c:when>
    <c:otherwise>数字输入有误</c:otherwise>
</c:choose>
</body>
</html>
