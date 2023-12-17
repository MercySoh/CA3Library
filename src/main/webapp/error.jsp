<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 17/12/2023
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String msg = (String) session.getAttribute("errorMessage");

    if (msg != null) {
%>
<p><%=msg%>
</p>
<%
    session.removeAttribute("errorMessage");
} else {

%>
<p> You came here by mistake there is no error</p>
<%
    }
%>


</body>
</html>
