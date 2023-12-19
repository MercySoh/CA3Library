package commands;

import business.Loan;
import business.Users;
import daos.BookDao;
import daos.LoanDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PayOverdueFeesCommand implements Command{
    private HttpServletRequest request;
    private HttpServletResponse response;

    public PayOverdueFeesCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String execute() {
        String destination = "profile.jsp";
        LoanDao loanDao = new LoanDao("ca3library");
        BookDao bookDao = new BookDao("ca3library");
        HttpSession session = request.getSession(true);
        Users u = (Users) session.getAttribute("user");
        String msg = null;
        if (u != null) {
            String loanId1 = (String) session.getAttribute("OverdueLoan");
            int loanId= Integer.parseInt(loanId1);
            Loan l = loanDao.getLoanById(loanId);
            int days = (int) l.getDueDate().until(LocalDate.now(), ChronoUnit.DAYS);
            double fee=days*1;
            loanDao.payOverDueFee(l.getLoanId(),fee);
            loanDao.returnBook(loanId);
            session.removeAttribute("OverdueLoan");
            bookDao.updateBookQuantity(l.getBookId(), +1);
        } else {
            msg = "You need to sign in";
            session.setAttribute("msg", msg);
            destination = "login.jsp";
        }
        return destination;
    }
}
