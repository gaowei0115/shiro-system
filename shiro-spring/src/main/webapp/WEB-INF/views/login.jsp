<%--
  Created by IntelliJ IDEA.
  User: gaowei
  Date: 2017/12/7
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <div align="center">
        <br><br><br><br><br><br>
        <h1>LOGIN PAGE</h1>
        <br><br><br>
    </div>
    <div align="center">
        <form method="post" action="login">
            USERNAME: <input id="username" name="username" type="text"/>
            <br>
            PASSWORD: <input id="password" name="password" type="password"/>
            <br><br>
            <input type="submit" id="login" value="LOGIN"/>
            <input type="submit" id="register" value="REGISTER"/>
        </form>
    </div>
</body>
</html>
