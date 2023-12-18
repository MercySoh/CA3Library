<%@ include file="header.jsp" %>
<%@include file="navigator.jsp" %>

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
        <td><a href="controller?action=&bookId=<%=loan.getBookId()%>" >return book</a>
        </td>
    </tr>
    <%
        }
    %>

</table>
<%
} else {
%>
<p> No loans</p>
<%
    }
%>


<%@include file="footer.jsp" %>
