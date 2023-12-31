<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
<%@include file="header.jsp" %>

<%@include file="navigator.jsp" %>

<style>
    table {
        border-collapse: collapse;
        width: 100%;
    }

    th, td {
        padding: 8px;
        text-align: left;
        border-bottom: 1px solid #DDD;
    }

    tr:hover {background-color: #D6EEEE;}

</style>


<%
    String msg = (String) session.getAttribute("msg");
    if (msg != null) {
%>
<div class="textbox-green">
    <p><%=msg%>
    </p>
</div>
<%
        session.removeAttribute("msg");
    }
%>

<div class="container text-center">
    <div class="row align-items-start">
        <div class="col">
            <h1>Profile</h1>
            <p>Username: <%=u.getUserName()%>
            </p>
            <p>Email: <%=u.getEmail()%>
            </p>
            <p>Address: <%=u.getAddress()%>
            </p>
            <p>Phone: <%=u.getPhone()%>
            </p>
            <a href="updateProfile.jsp">
            <button class="btn btn-info">Update user</button>
            </a>
        </div>
        <div class="col">
            <h1>Loans</h1>
            <%
                List<Loan> currentLoans = (List<Loan>) session.getAttribute("currentLoans");
                BookDao bookDao = new BookDao("ca3library");
                LoanDao loanDao = new LoanDao("ca3library");
                List<Loan> previousLoans = loanDao.getPreviousLoans(u.getUserID());
                List<Loan> allLoans = new ArrayList();
                for (Loan loan : previousLoans) {
                    allLoans.add(loan);
                }
                for (Loan loan : currentLoans) {
                  /*  boolean state=false;
                    for (Loan l : allLoans) {
                        if(l.equals(loan)==true) {
                            state=true;
                        }
                    }
                    if(state==false){*/
                    allLoans.add(loan);
                    // }
                }
                if (allLoans != null && allLoans.size() > 0) {
            %>
            <table>
                <tr>
                    <th>Book Id</th>
                    <th>Book Name</th>
                    <th>Borrow Date</th>
                    <th>Due Date</th>
                </tr>
                <%
                    for (Loan loan : allLoans) {
                        Book b = bookDao.getBookByID(loan.getBookId());
                %>
                <tr>
                    <td><%=loan.getBookId()%>
                    </td>
                    <td><%=b.getBookName()%>
                    </td>
                    <td><%=loan.getBorrowDate()%>
                    </td>
                    <td><%=loan.getDueDate()%>
                    </td>
                    <%
                        if (loan.getReturnedDate() == null && LocalDate.now().compareTo(loan.getDueDate()) > 0) {
                            int days = (int) loan.getDueDate().until(LocalDate.now(), ChronoUnit.DAYS);
                            double fee = days * 1;
                    %>
                    <td style="background: #fd5252">OVERDUE <%="$" + fee%>
                    </td>
                    <td><a href="controller?action=payOverdueFees&loanId=<%=loan.getLoanId()%>">
                        <button class="btn btn-warning">Pay fees</button>
                    </a>
                    </td>
                    <%
                    } else if (loan.getReturnedDate() != null) {
                    %>
                    <td>RETURNED</td>
                    <%
                    } else {
                    %>
                    <td><a href="controller?action=returnBook&loanId=<%=loan.getLoanId()%>">
                        <button class="btn btn-info">Return book</button>
                    </a>
                    </td>
                    <%
                        }
                    %>
                </tr>
                <%
                    }%>

            </table>
            <%
            } else {
            %>
            <p> No loans</p>
            <%
                }
            %>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
