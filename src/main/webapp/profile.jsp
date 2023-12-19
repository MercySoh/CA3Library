<%@include file="header.jsp"%>

<%@include file="navigator.jsp"%>

<div class="container text-center">
    <div class="row align-items-start">
        <div class="col">
            <h1>Profile</h1>
            <p>username: <%=u.getUserName()%></p>
            <p>email: <%=u.getEmail()%></p>
            <p>address: <%=u.getAddress()%></p>
            <p>phone: <%=u.getPhone()%></p>
        </div>
        <div class="col">
            <h1>loans</h1>
        </div>
    </div>
</div>

<%@include file="footer.jsp"%>
