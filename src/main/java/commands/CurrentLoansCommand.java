package commands;

import business.Loan;
import business.Users;
import daos.LoanDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class CurrentLoansCommand implements Command {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CurrentLoansCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public String execute() {
        HttpSession session = request.getSession(true);
        Users u = (Users) session.getAttribute("user");
       String destination="currentLoans.jsp";
       String msg=null;
        if (u != null) {
            LoanDao loanDao= new LoanDao("ca3library");
            List<Loan> loans= loanDao.getCurrentLoans(u.getUserID());
            session.setAttribute("currentLoans",loans);
        }
        else{
            msg = "You need to sign in in order to view Loans";
            session.setAttribute("msg", msg);
            destination = "login.jsp";
        }
       return destination;

    }
}
