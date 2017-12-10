<%--
  Created by IntelliJ IDEA.
  User: mmc
  Date: 2017/12/4
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>index.jsp</title>
</head>
<body>
    <h1>index.....</h1>

    Welcome : <shiro:principal></shiro:principal>

    <br><br>
    <shiro:hasRole name="user">
    <a href="user.jsp">user page</a>
    <br><br>
    </shiro:hasRole>
    <shiro:hasRole name="admin">
    <a href="admin.jsp">admin page</a>
    <br><br>
    </shiro:hasRole>

    <a href="logout">logout</a>
</body>
</html>
