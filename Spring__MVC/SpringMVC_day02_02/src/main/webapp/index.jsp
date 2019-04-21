<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>文件上传案例</head>
<body>
<h2>文件上传</h2>
<form action="/user/fileUpLoad" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="upload"/><br>
             <input type="submit" value="上传"/>
</form>
</body>
</html>
