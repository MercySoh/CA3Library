<%@include file="header.jsp" %>

<%@include file="navigator.jsp" %>

<%
    String msg = (String) session.getAttribute("msg");
    if (msg != null) {
%>
<p><%=msg%> </p>
<%
        session.removeAttribute("msg");
    }
%>

<div class="d-flex align-items-center justify-content-center" style="height: 100vh;">
    <form action="controller" method="post">
        <h3>Log in!</h3>
        <label class="form-label">username</label> <br/>
        <input class="form-control" name="username" required/> <br/>
        <label class="form-label">password</label> <br/>
        <input class="form-control" name="password" type="password" required/> <br/>

        <input type="submit" value="Register" class="btn btn-success"/>
        <!-- Include a hidden field to identify what the user wants to do -->
        <input type="hidden" name="action" value="login"/>
    </form>
</div>

<%@include file="footer.jsp" %>
