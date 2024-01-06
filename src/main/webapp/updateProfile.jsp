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
        <h3>Update my profile</h3>
        <label class="form-label">username</label> <br/>
        <input class="form-control" name<%=u.getUserName()%> required/> <br/>
        <label class="form-label">password</label> <br/>
        <input class="form-control" name="password" type="password" required/> <br/>
        <label class="form-label">email</label> <br/>
        <input class="form-control" name="email" required/> <br/>
        <label class="form-label">address</label> <br/>
        <input class="form-control" name="address" required/> <br/>
        <label class="form-label">phone</label> <br/>
        <input class="form-control" name="phone" required/> <br/><br/>

        <input type="submit" value="Update" class="btn btn-success"/>
        <!-- Include a hidden field to identify what the user wants to do -->
        <input type="hidden" name ="action" value="profile" />
    </form>
</div>

<%@include file="footer.jsp"%>
