<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.temporal.ChronoUnit" %>
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
            <button class="btn btn-info">update user</button>
        </div>
        <div class="col">
            <h1>loans</h1>
            <%
                List<Loan> currentLoans = (List<Loan>) session.getAttribute("currentLoans");
                BookDao bookDao = new BookDao("ca3library");
                if (currentLoans != null && currentLoans.size() > 0) {
            %>
            <table>
                <tr>
                    <th>Book Id</th>
                    <th>Book Name</th>
                    <th>Borrow Date</th>
                    <th>Due Date</th>
                </tr>
                <%
                    for (Loan loan : currentLoans) {
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
                        if (LocalDate.now().compareTo(loan.getDueDate()) > 0) {
                            int days = (int) loan.getDueDate().until(LocalDate.now(), ChronoUnit.DAYS);
                            double fee=days*1;
                    %>
                    <td style="background: #fd5252">OVERDUE <%="$"+ fee%></td>
                    <td><a href="controller?action=payOverdueFees&loanId=<%=loan.getLoanId()%>"><button class="btn btn-warning">pay fees</button></a>
                    </td>
                    <%
                    } else {
                    %>
                    <td><a href="controller?action=returnBook&loanId=<%=loan.getLoanId()%>"><button class="btn btn-info">return book</button></a>
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

<%@include file="footer.jsp"%>
