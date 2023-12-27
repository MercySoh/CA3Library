package commands;

import business.Loan;
import business.Users;
import daos.BookDao;
import daos.LoanDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ReturnBookCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ReturnBookCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * return book to library
     * @return to the profile page
     */
    @Override
    public String execute() {
        String destination = "profile.jsp";
        LoanDao loanDao = new LoanDao("ca3library");
        BookDao bookDao = new BookDao("ca3library");
        HttpSession session = request.getSession(true);
        Users u = (Users) session.getAttribute("user");
        String msg = null;
        if (u != null) {
            int loanId = Integer.parseInt(request.getParameter("loanId"));
            loanDao.returnBook(loanId);
            Loan l = loanDao.getLoanById(loanId);
            bookDao.updateBookQuantity(l.getBookId(), +1);
        } else {
            msg = "You need to sign in";
            session.setAttribute("msg", msg);
            destination = "login.jsp";
        }
        return destination;
    }
}
