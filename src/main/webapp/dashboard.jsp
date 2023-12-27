<%@ page import="daos.GenreDao" %>
<%@ page import="business.Genre" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.BookDao" %>
<%@ page import="business.Book" %>
<%@ page import="daos.BookgenreDao" %>
<%@ page import="business.Bookgenre" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="commands.Command" %>
<%@ include file="header.jsp" %>

<%@include file="navigator.jsp" %>

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

<table class="table">
    <thead>
    <tr>
        <th scope="col">bookID</th>
        <th scope="col">name</th>
        <th scope="col">author</th>
        <th scope="col">description</th>
        <th scope="col">genres</th>
        <th scope="col">quantity</th>
    </tr>
    </thead>
    <tbody>

    <div id="moving-div">
        <%
            BookDao bookDao = new BookDao("ca3library");
            BookgenreDao bookgenreDao = new BookgenreDao("ca3library");
            LoanDao loanDao = new LoanDao("ca3library");
            TreeMap<Integer, Integer> trends = loanDao.getTrendingBooks();
            for (Map.Entry<Integer, Integer> entry : trends.entrySet()) {
                int bookId = entry.getKey();
                Book b = bookDao.getBookByID(bookId);
                int borrow = entry.getValue();
        %>
        <i><%=b.getBookName()%> borrowed <%=borrow%> times,
        </i>
        <%

            }
        %>
    </div>

    <%
        for (Book b : Controller.books) {
            ArrayList<Bookgenre> genreBook = bookgenreDao.getAllBookGenres(b.getBookID());
            List<Genre> genresList = new ArrayList<>();
            for (Bookgenre bg : genreBook) {
                genresList.add(genreDao.getGenreByID(bg.getGenreId()));
            }
    %>
    <tr>
        <td><%=b.getBookID()%>
        </td>
        <td><%=b.getBookName()%>
        </td>
        <td><%=b.getAuthor()%>
        </td>
        <td><%=b.getDescription()%>
        </td>
        <td><%for (Genre g : genresList) {%>
            <%=g.getGenreName()%>
            <%}%>
        </td>
        <td><%=b.getQuantity()%>
        </td>
        <td><a href=<%="controller?action=borrow&bookId=" + b.getBookID()%>>borrow</a></td>
    </tr>
    <%}%>
    </tbody>
</table>


<%@include file="footer.jsp" %>
