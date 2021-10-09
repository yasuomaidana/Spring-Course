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
    <title>Menu</title>
    <script script language="JavaScript" type="text/JavaScript"
            src="${pageContext.request.contextPath}/scripts/test.js"></script>
</head>
<body>
    <header><h1>Manu</h1></header>
    <main>
        Here you can see this is the<br> username: <b>${username}</b><br>password: <b>${password}</b>
        <br>And this is the object sent: <b>${userDto}</b>
        <br>Accessing data through object:<br> username: <b>${userDto.username}</b>
    </main>
</body>
</html>
