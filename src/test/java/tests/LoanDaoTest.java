package tests;

import business.Loan;
import daos.LoanDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LoanDaoTest {
  private LoanDao loanDao = new LoanDao("ca3librarytest");
    @BeforeEach
    void setUp() {
        // insert loan for payOverdueLoan
        LocalDate borrowDate = LocalDate.of(2023, 12, 2);
        LocalDate dueDate = LocalDate.of(2023, 12, 7);
        loanDao.borrowBook(2, 5, borrowDate, dueDate);

        // insert Loan for payOverDueFee_forNotDueLoan()
        LocalDate borrowDate1 = LocalDate.now().minusDays(5);
        LocalDate dueDate1 = LocalDate.now().plusDays(1);
        loanDao.borrowBook(2, 4, borrowDate1, dueDate1);

        //  borrow a book for returnBook()
        LocalDate borrowDate2 = LocalDate.now().minusDays(5);
        LocalDate dueDate2 = LocalDate.now().plusDays(1);
        loanDao.borrowBook(2, 3, borrowDate2, dueDate2);
    }

    @AfterEach
    void tearDown() {
        /**delete the inserted loan payOverdueLoan for**/
        LocalDate borrowDate = LocalDate.of(2023, 12, 2);
        LocalDate dueDate = LocalDate.of(2023, 12, 7);
        LocalDate present = LocalDate.now();
        // int days= (int)dueDate.until(present , ChronoUnit.DAYS);
        Loan l = loanDao.getLoanByLoanFields(2, 5, borrowDate, dueDate);
        loanDao.deleteLoan(l.getLoanId());

        //delete the inserted loan for payOverDueFee_forNotDueLoan
        LocalDate borrowDate1 = LocalDate.now().minusDays(5);
        LocalDate dueDate1 = LocalDate.now().plusDays(1);
        LocalDate present1 = LocalDate.now();
        //  int days1= (int)dueDate.until(present1 , ChronoUnit.DAYS);
        Loan l1 = loanDao.getLoanByLoanFields(2, 4, borrowDate1, dueDate1);
        loanDao.deleteLoan(l1.getLoanId());

        //  Delete the loan for returnBook()**/
        LocalDate borrowDate2 = LocalDate.now().minusDays(5);
        LocalDate dueDate2 = LocalDate.now().plusDays(1);
        Loan l2 = loanDao.getLoanByLoanFields(2, 3, borrowDate2, dueDate2);
        loanDao.deleteLoan(l2.getLoanId());
    }

    /**
     * Test for get user current loans for a user when current loans exist
     **/
    @Test
    void getCurrentLoans_WhenLoansArePresent() {
        LocalDate borrowDate1 = LocalDate.of(2023, 12, 7);
        LocalDate borrowDate2 = LocalDate.of(2023, 12, 8);
        LocalDate dueDate1 = LocalDate.of(2023, 12, 8);
        LocalDate dueDate2 = LocalDate.of(2024, 1, 30);
        ArrayList<Loan> actual = loanDao.getCurrentLoans(1);
        Loan l1 = new Loan(1, 1, 1, borrowDate1, dueDate1, null);
        Loan l2 = new Loan(2, 1, 2, borrowDate2, dueDate2, null);
        ArrayList<Loan> expected = new ArrayList();
        expected.add(l1);
        expected.add(l2);
        assertEquals(actual, expected);
    }

    /*Test for get current loans when no loans are present**/
    @Test
    void getCurrentLoans_WhenNoLoansArePresent() {
        //LoanDao loanDao = new LoanDao("testca3library");
        ArrayList<Loan> actual = loanDao.getCurrentLoans(10);
        ArrayList<Loan> expected = new ArrayList();
        assertEquals(actual, expected);
    }

    /**
     * Test for getting a user's previous loans when the loans are present
     **/
    @Test
    void getPreviousLoans_WhenPreviousLoansExist() {
        LocalDate borrowDate1 = LocalDate.of(2023, 12, 5);
        LocalDate dueDate1 = LocalDate.of(2023, 12, 7);
        LocalDate returnDate1 = LocalDate.of(2023, 12, 7);
        ArrayList<Loan> actual = loanDao.getPreviousLoans(1);
        Loan l1 = new Loan(3, 1, 3, borrowDate1, dueDate1, returnDate1);
        ArrayList<Loan> expected = new ArrayList();
        expected.add(l1);
        assertEquals(actual, expected);
    }

    /**
     * Test for getting previous loans when No previous loans are present
     **/
    @Test
    void getPreviousLoans_WhenNoPreviousLoansExist() {
        ArrayList<Loan> actual = loanDao.getPreviousLoans(10);
        ArrayList<Loan> expected = new ArrayList();
        assertEquals(actual, expected);
    }

    /**
     * Test getting a user's overDue when Overdue loans are present
     **/
    @Test
    void getOverDueLoans_WhenOverDueLoansExist() {
        //LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate1 = LocalDate.of(2023, 12, 7);
        LocalDate dueDate1 = LocalDate.of(2023, 12, 8);
        ArrayList<Loan> actual = loanDao.getOverDueLoans(1);
        Loan l1 = new Loan(1, 1, 1, borrowDate1, dueDate1, null);
        ArrayList<Loan> expected = new ArrayList();
        expected.add(l1);
        assertEquals(actual, expected);
    }

    /**
     * Test getting overDue loans when No Overdue loans are present
     **/
    @Test
    void getOverDueLoans_WhenNoOverDueLoansExist() {
        ArrayList<Loan> actual = loanDao.getOverDueLoans(10);
        ArrayList<Loan> expected = new ArrayList();
        assertEquals(actual, expected);
    }

    /**
     * pay overdue fee for an existing Loan
     **/
    @Test
    void payOverDueFee_WhenLoanIsPresent() {
        LocalDate borrowDate = LocalDate.of(2023, 12, 2);
        LocalDate dueDate = LocalDate.of(2023, 12, 7);
        LocalDate present = LocalDate.now();
        int days = (int) dueDate.until(present, ChronoUnit.DAYS);
        Loan l1 = loanDao.getLoanByLoanFields(2, 5, borrowDate, dueDate);
        double fee = days * 1;
        boolean actual = loanDao.payOverDueFee(l1.getLoanId(), fee);
        boolean expected = true;
        assertEquals(actual, expected);
        if (actual == expected) {
            Loan l2 = loanDao.getLoanById(l1.getLoanId());
            assertEquals(l2.getUserId(), l1.getUserId());
            assertEquals(l2.getBookId(), l1.getBookId());
            assertEquals(l2.getDueDate(), l1.getDueDate());
            assertEquals(l2.getReturnedDate(), l1.getReturnedDate());
            assertEquals(l2.getFees(), fee);
        }
    }

    /**
     * When loan is not present
     **/
    @Test
    void payOverDueFee_WhenLoanIsNotPresent() {
        boolean actual = loanDao.payOverDueFee(10000, 20);
        boolean expected = false;
        assertEquals(actual, expected);
    }

    /**
     * When loan has been returned
     **/
    @Test
    void payOverDueFee_forAReturnedLoan() {
        boolean actual = loanDao.payOverDueFee(3, 20);
        boolean expected = false;
        assertEquals(actual, expected);
    }


    /**
     * pay overdue fee when Loan is not due
     **/
    @Test
    void payOverDueFee_forNotDueLoan() {
        LocalDate borrowDate = LocalDate.now().minusDays(5);
        LocalDate dueDate = LocalDate.now().plusDays(1);
        LocalDate present = LocalDate.now();
        int days = (int) dueDate.until(present, ChronoUnit.DAYS);
        Loan expectedl = loanDao.getLoanByLoanFields(2, 4, borrowDate, dueDate);
        double fee = days * 1;
        boolean actual = loanDao.payOverDueFee(expectedl.getLoanId(), fee);
        boolean expected = false;
        assertEquals(actual, expected);
        if (actual == expected) {
          Loan actualL = loanDao.getLoanById(expectedl.getLoanId());
           assertEquals(actualL.getLoanId(),expectedl.getLoanId());
            assertEquals(actualL.getUserId(),expectedl.getUserId());
            assertEquals(actualL.getBookId(),expectedl.getBookId());
            assertEquals(actualL.getBorrowDate(),expectedl.getBorrowDate());
            assertEquals(actualL.getDueDate(),expectedl.getDueDate());
            assertEquals(actualL.getReturnedDate(),expectedl.getReturnedDate());
            assertEquals(actualL.getFees(),expectedl.getFees());
        }
    }

    /**
     * return the borrowed book
     **/
    @Test
    void returnBook() {
        LocalDate borrowDate = LocalDate.now().minusDays(5);
        LocalDate dueDate = LocalDate.now().plusDays(1);
        Loan l = loanDao.getLoanByLoanFields(2, 3, borrowDate, dueDate);
        boolean actual = loanDao.returnBook(l.getLoanId());
        LocalDate expectereturndDate = LocalDate.now();
        boolean expected = true;
        assertEquals(actual, expected);
        if (actual == expected) {
            Loan actualLoan = loanDao.getLoanById(l.getLoanId());
            Loan expectedLoan = new Loan(l.getLoanId(), 2, 3, borrowDate, dueDate, expectereturndDate);
            assertEquals(expectedLoan, actualLoan);
            assertEquals(expectedLoan.getUserId(), actualLoan.getUserId());
            assertEquals(expectedLoan.getBookId(), actualLoan.getBookId());
            assertEquals(expectedLoan.getBorrowDate(), actualLoan.getBorrowDate());
            assertEquals(expectedLoan.getDueDate(), actualLoan.getDueDate());
            assertEquals(actualLoan.getReturnedDate(), expectereturndDate);
            assertEquals(expectedLoan.getFees(), actualLoan.getFees());
        }
    }

    /**
     * Return a book for a loan that doesn't exist
     **/
    @Test
    void returnBook_WhenLoanNotPresent() {
        boolean actual = loanDao.returnBook(10000);
        boolean expected = false;
        assertEquals(actual, expected);

    }

    /**
     * Get a Loan when a loan exist
     **/
    @Test
    void getLoanById_WhenLoanExist() {
        LocalDate borrowDate = LocalDate.of(2023, 12, 7);
        LocalDate dueDate = LocalDate.of(2023, 12, 9);
        Loan actual = loanDao.getLoanById(1);
        Loan expected = new Loan(1, 1, 1, borrowDate, dueDate, null);
        assertEquals(actual, expected);
    }

    /**
     * When the loan doesn't exist
     **/
    @Test
    void getLoanById_WhenLoanDoesNotExist() {
        //LoanDao loanDao = new LoanDao("testca3library");
        Loan actual = loanDao.getLoanById(10000);
        Loan expected = null;
        assertEquals(actual, expected);
    }

    @Test
    void borrowBook() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        LocalDate borrowedDate = LocalDate.now();
        boolean actual = loanDao.borrowBook(1, 5, dueDate);
        boolean expected = true;
        assertEquals(actual, expected);
        if (actual == expected) {
            Loan l1 = loanDao.getLoanByLoanFields(1, 5, borrowedDate, dueDate);
            Loan l2 = new Loan(l1.getLoanId(), 1, 5, borrowedDate, dueDate, null);
            assertEquals(l1.getLoanId(), l2.getLoanId());
            assertEquals(l1.getUserId(), l2.getUserId());
            assertEquals(l1.getBookId(), l2.getBookId());
            assertEquals(l1.getBorrowDate(), l2.getBorrowDate());
            assertEquals(l1.getDueDate(), l2.getDueDate());
            assertEquals(l1.getReturnedDate(), l2.getReturnedDate());
        }
    }

    @Test
    void deleteLoan() {
        LocalDate dueDate = LocalDate.now().plusDays(1);
        LocalDate borrowedDate = LocalDate.now();
        Loan actualLoan = loanDao.getLoanByLoanFields(1, 5, borrowedDate, dueDate);
        Loan expectedLoan = new Loan(actualLoan.getLoanId(), 1, 5, borrowedDate, dueDate, null);
        int actual = loanDao.deleteLoan(actualLoan.getLoanId());
        int expected = 1;
        assertEquals(actual, expected);
        if (actual == expected) {
            assertEquals(actualLoan.getUserId(), expectedLoan.getUserId());
            assertEquals(actualLoan.getBookId(), expectedLoan.getBookId());
            assertEquals(actualLoan.getBorrowDate(), expectedLoan.getBorrowDate());
            assertEquals(actualLoan.getDueDate(), expectedLoan.getDueDate());
            assertEquals(actualLoan.getReturnedDate(), expectedLoan.getReturnedDate());
            assertEquals(actualLoan.getFees(), expectedLoan.getFees());
        }
    }
/**Delete a loan when the loanId doesn't exist in the database **/
    @Test
    void deleteLoan_whenNoLoanIdIsFound() {
        int actual = loanDao.deleteLoan(10000);
        int expected = 0;
        assertEquals(actual, expected);
    }
/**Get a loan by loanId,bookId, borrowDate and dueDate when a loan exist with those details **/
    @Test
    void getLoanByLoanFields() {
        LocalDate borrowDate = LocalDate.of(2023, 12, 7);
        LocalDate dueDate = LocalDate.of(2023, 12, 8);
        Loan expected = new Loan(1, 1, 1, borrowDate, dueDate, null);
        Loan actual = loanDao.getLoanByLoanFields(1,1,borrowDate,dueDate);
        assertEquals(expected,actual);
    }
/**Get a loan by loanId,bookId, borrowDate and dueDate when no loan exist with those details **/
    @Test
    void getLoanByLoanFields_whenFieldsDoNotMatch() {
        LocalDate borrowDate = LocalDate.of(2023, 12, 7);
        LocalDate dueDate = LocalDate.of(2023, 12, 8);
        Loan expected = null;
        Loan actual = loanDao.getLoanByLoanFields(55,7,borrowDate,dueDate);
        assertEquals(expected,actual);
    }


}