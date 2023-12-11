<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>

<%
  Object msg = session.getAttribute("msg");
  // If there is an error message to print
  if(msg != null){
    String mainMsg = (String) msg;

%>
<p> <%=mainMsg%> </p>

<%
    session.removeAttribute("msg");
  }
%>
<a href="controller?action=registering">register</a> <br/>
<a href="controller?action=test">Hello Servlet</a>
</body>
</html>