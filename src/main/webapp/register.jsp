<%@include file="header.jsp"%>

<%@include file="navigator.jsp"%>

<%
    String msg = (String) session.getAttribute("msg");
    if (msg != null) {
%>
<div class="textbox-red">
    <p><%=msg%>
    </p>
</div>
<%
        session.removeAttribute("msg");
    }
%>

<div class="d-flex align-items-center justify-content-center" style="height: 100vh;">
    <form action="controller" method="post">
        <h3>Sign in!</h3>
        <label class="form-label">Username</label> <br/>
        <input class="form-control" name="username" required/> <br/>
        <label class="form-label">Password</label> <br/>
        <input class="form-control" name="password" type="password" required/> <br/>
        <label class="form-label">Email</label> <br/>
        <input class="form-control" name="email" required/> <br/>
        <label class="form-label">Address</label> <br/>
        <input class="form-control" name="address" required/> <br/>
        <label class="form-label">Phone</label> <br/>
        <input class="form-control" name="phone" required/> <br/><br/>

        <input type="submit" value="Register" class="btn btn-success"/>
        <!-- Include a hidden field to identify what the user wants to do -->
        <input type="hidden" name ="action" value="register" />
    </form>
</div>

<%@include file="footer.jsp"%>
