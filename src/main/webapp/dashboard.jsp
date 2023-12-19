<%@ page import="daos.GenreDao" %>
<%@ page import="business.Genre" %>
<%@ page import="java.util.List" %>
<%@ page import="daos.BookDao" %>
<%@ page import="business.Book" %>
<%@ page import="daos.BookgenreDao" %>
<%@ page import="business.Bookgenre" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="header.jsp" %>

<%@include file="navigator.jsp" %>

<br/>

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
        <%
            BookgenreDao bookgenreDao = new BookgenreDao("ca3library");
            for(Book b : Controller.books){
                ArrayList<Bookgenre> genreBook = bookgenreDao.getAllBookGenres(b.getBookID());
                List<Genre> genresList = new ArrayList<>();
                for(Bookgenre bg : genreBook){
                    genresList.add(genreDao.getGenreByID(bg.getGenreId()));
                }
        %>
            <tr>
                <td><%=b.getBookID()%></td>
                <td><%=b.getBookName()%></td>
                <td><%=b.getAuthor()%></td>
                <td><%=b.getDescription()%></td>
                <td><%for(Genre g : genresList){%>
                    <%=g.getGenreName()%>
                    <%}%>
                </td>
                <td><%=b.getQuantity()%></td>
                <td><a href="controller?action=borrow&bookId="<%=b.getBookID()%>>borrow</a> </td>
            </tr>
        <%}%>
    </tbody>
</table>


<%@include file="footer.jsp" %>
