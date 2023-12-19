package commands;

import business.*;
import controller.Controller;
import daos.BookDao;
import daos.BookgenreDao;
import daos.LoanDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

public class DisplayBookCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;

    public DisplayBookCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String execute() {
        HttpSession session = request.getSession(true);
        LoanDao loanDao = new LoanDao("ca3library");
        BookDao bookDao = new BookDao("ca3library");

        Users u = (Users)session.getAttribute("user");

        Controller.books = bookDao.getAllBooks();

        if(session.getAttribute("user") != null){
            List<Loan> currentLoans = loanDao.getCurrentLoans(u.getUserID());
            List<Book> books = new ArrayList<>();

            for(Loan l : currentLoans){
                books.add(bookDao.getBookByID(l.getBookId()));
            }

            Controller.books.removeAll(books);
        }

        return "dashboard.jsp";
    }
}
