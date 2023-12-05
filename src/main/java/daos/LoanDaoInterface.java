package daos;

import business.Loan;

import java.util.ArrayList;

public interface LoanDaoInterface {

    public ArrayList <Loan> getCurrentLoans(int userId);
    public ArrayList <Loan> getPreviousLoans(int userId);
    public ArrayList <Loan> getOverDueLoans(int userId);
    public boolean payOverDueFee(int loanId, double fee);
    public boolean returnBook(int loanId);
    public Loan getLoanById(int loanId);
 }
