<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">${error}</div>
<div align="center">
    <form action="${pageContext.request.contextPath}/login" method="post">
        USERNAME：<input type="text" name="username"><br/>
        PASSWORD：<input type="password" name="password"><br/>
        <input type="submit" value="登录">
    </form>
</div>
</body>
</html>