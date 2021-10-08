<%--
  Created by IntelliJ IDEA.
  User: ymaidana
  Date: 08/10/21
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <header>
        <h1>Welcome to Login</h1>
    </header>
    <main>
        <form action="/login" method="post">
            <label for="user">Name:</label>
            <input type="text" name="user" id="user">
            <label for="password">Name:</label>
            <input type="password" name="password" id="password">
            <input type="submit" value="Login">
        </form>
    </main>
</body>
</html>
