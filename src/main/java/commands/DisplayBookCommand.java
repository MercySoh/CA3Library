package commands;

import business.*;
import controller.Controller;
import daos.BookDao;
import daos.BookgenreDao;
import daos.LoanDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.List;

public class DisplayBookCommand implements Command{

    private HttpServletRequest request;
    private HttpServletResponse response;

    public DisplayBookCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * execute the command
     * @return the destination link
     */
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

    /**
     * search a book by genre
     * @param genreID the genreID
     * @return back to dashboard
     */
    public String execute(int genreID){
        HttpSession session = request.getSession(true);
        BookgenreDao bookgenreDao = new BookgenreDao("ca3library");
        LoanDao loanDao = new LoanDao("ca3library");
        BookDao bookDao = new BookDao("ca3library");

        List<Bookgenre> bookgenres = bookgenreDao.getBookGenresByGenreId(genreID);
        Controller.books = new ArrayList<>();

        for(Bookgenre bg : bookgenres){
            Controller.books.add(bookDao.getBookByID(bg.getBookId()));
        }

        Users u = (Users)session.getAttribute("user");


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

    /**
     * search for a book title
     * @param title the title
     * @return back to dashboard
     */
    public String execute(String title){
        HttpSession session = request.getSession(true);
        LoanDao loanDao = new LoanDao("ca3library");
        BookDao bookDao = new BookDao("ca3library");

        Controller.books = bookDao.searchBookByTitle(title);
        
        Users u = (Users)session.getAttribute("user");

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
