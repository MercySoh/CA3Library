<%@include file="header.jsp"%>

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

<%@include file="footer.jsp"%>
