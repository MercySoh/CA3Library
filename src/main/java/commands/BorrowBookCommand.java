package commands;

import business.Book;
import business.Users;
import daos.BookDao;
import daos.LoanDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

public class BorrowBookCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public BorrowBookCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }


    @Override
    public String execute() {
        String destination = "index.jsp";
        LoanDao loanDao = new LoanDao("ca3library");
        BookDao bookDao = new BookDao("ca3library");
        HttpSession session = request.getSession(true);
        Users u = (Users) session.getAttribute("user");
        String msg = null;
        if (u != null) {
            //int userId= u.getUserID();

            int bookId = Integer.parseInt(request.getParameter("bookId"));
            LocalDate returnDate = LocalDate.now().plusDays(14);
            //int bookId2 = Integer.parseInt(bookId1);
            Book b1 = bookDao.getBookByID(bookId);
            if (b1.getQuantity() > 0) {
                bookDao.updateBookQuantity(bookId, -1);
                loanDao.borrowBook(u.getUserID(), bookId, returnDate);
                msg = "You borrowed " + b1.getBookName() +" successfully";
                session.setAttribute("msg", msg);
                destination = "index.jsp";
            } else {
                msg =  "the are no more copies available";
                session.setAttribute("msg", msg);
            }
            //loanDao.borrowBook()

        } else {
            msg = "You need to sign in in order to borrow a book";
            session.setAttribute("msg", msg);
            destination = "login.jsp";
        }
        return destination;
    }
}
