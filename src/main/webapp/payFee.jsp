<%@ page import="java.time.LocalDate" %>
<%@ include file="header.jsp" %>

<%@include file="navigator.jsp" %>

<div class="d-flex align-items-center justify-content-center" style="height: 100vh;">
    <form action="controller" method="post">
        <h3>Pay  Fee!</h3>
        <%%>
        <label class="form-label">Card Number</label> <br/>
        <input class="form-control" name="cardNumber" required/> <br/>
        <label class="form-label">Expiry Date</label> <br/>
        <input class="form-control" name="date" type="month" placeholder="Month" required/> <br/>
        <input class="form-control" name="date" type="number" placeholder="YYYY" min="<%=LocalDate.now().getYear()%>" max="<%=LocalDate.now().getYear()+20%>" required/> <br/>
        <label class="form-label">Code</label> <br/>
        <input class="form-control" name="code" pattern="[0-9]{3}" title="three digits at the back of your card" required/> <br/>

        <input type="submit" value="Pay" class="btn btn-success"/>
        <!-- Include a hidden field to identify what the user wants to do -->
        <input type="hidden" name ="action" value="PayFee" />
    </form>
</div>

<%@include file="footer.jsp" %>