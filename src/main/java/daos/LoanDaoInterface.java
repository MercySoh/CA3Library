package daos;

import business.Loan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

public interface LoanDaoInterface {
    /**
     * Method gets all the user's existing loans based on the user's ID
     *
     * @param userId, the intended user's Id
     * @return ArrayList of loans
     */
    public ArrayList<Loan> getCurrentLoans(int userId);

    /**
     * Gets a user's previous loans or all returned loans based on the userId
     *
     * @param userId, the user's ID
     * @return method returns an Arraylist of previous loans or an empty Arraylist if no previous loans are found
     **/
    public ArrayList<Loan> getPreviousLoans(int userId);

    /**
     * Gets a user's overdue loans based on the userID
     *
     * @param userId, the user's ID
     * @return returns an Arraylist of overdue loans or an empty Arraylist of loans if no overdue loans are found
     **/
    public ArrayList<Loan> getOverDueLoans(int userId);

    /**
     * pays the overdue fee for an overdue loan based on the ID
     *
     * @param loanId, the loanId for the overdue loan
     * @param fee,    the fee to be paid
     * @return true if the loan has been paid or false if the payment wasn't successful
     **/
    public boolean payOverDueFee(int loanId, double fee);

    /**
     * returns a borrowed book, based on the loanID
     *
     * @param loanId, the loanId of Loan to be returned
     * @return true, if loan was returned or false if the return wasn't successful
     **/
    public boolean returnBook(int loanId);

    /**
     * get a loan from the database based on the loanId
     *
     * @param loanId, the intended loanId
     * @return a Loan or a null Loan if no Loan was found
     **/
    public Loan getLoanById(int loanId);

    /**
     * Enables a user to borrow a book when a valid userId and bookId are provided
     *
     * @param userId, the userId to the person borrowing
     * @param bookId, the bookId for the book being borrowed
     * @param dueDate ,the due date to return the book
     * @return true if the book was borrowed or false for otherwise
     **/
    public boolean borrowBook(int userId, int bookId, LocalDate dueDate);

    /**
     * Enables a user to borrow a book when a valid userId and bookId are provided
     *
     * @param userId,     the userId to the person borrowing
     * @param bookId,     the bookId for the book being borrowed
     * @param borrowDate, the date the book was borrowed
     * @param dueDate     ,the due date to return the book
     * @return true if the book was borrowed or false for otherwise
     **/
    public boolean borrowBook(int userId, int bookId, LocalDate borrowDate, LocalDate dueDate);

    /**
     * Get a Loan based on the userId, bookId, borrowDate, and dueDate
     *
     * @param userId,     the loan's userId
     * @param bookId,     the loan's bookId
     * @param borrowDate, the loan's borrowDate
     * @param dueDate,    the loan's dueDate
     * @return a Loan, or a null Loan if Loan wasn't found
     **/
    public Loan getLoanByLoanFields(int userId, int bookId, LocalDate borrowDate, LocalDate dueDate);

    /**
     * gets the top 10 most borrowed books in the last 30 days and the number of times they were borrowed
     *
     * @return TreeMap <Integer, Integer> where the key is the bookId and the value is the number of times it was borrowed
     **/
    public TreeMap<Integer, Integer> getTrendingBooks();

    /**
     * Deletes a loan from the database based on the loanId
     *
     * @param loanId, the loanId of the Loan to be deleted
     * @return 1 if the loan was deleted or 0 if the Loan wasn't deleted
     **/
    public int deleteLoan(int loanId);
}
