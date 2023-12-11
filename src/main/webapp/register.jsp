<%--
  Created by IntelliJ IDEA.
  User: d00243400
  Date: 11/12/2023
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="controller" method="post">
    <label>username</label>
    <input name="username" required/>
    <label>password</label>
    <input name="password" required/>
    <label>email</label>
    <input name="email" required/>
    <label>address</label>
    <input name="address" required/>
    <label>phone</label>
    <input name="phone" required/>

    <input type="submit" value="Register" />
    <!-- Include a hidden field to identify what the user wants to do -->
    <input type="hidden" name ="action" value="register" />
</form>

</body>
</html>
