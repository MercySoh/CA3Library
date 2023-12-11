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

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
/**
 * Test for get user current loans for a user when current loans exist
 * **/
    @Test
    void getCurrentLoans_WhenLoansArePresent() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate1= LocalDate.of(2023,12,7);
        LocalDate borrowDate2= LocalDate.of(2023,12,8);
        LocalDate dueDate1= LocalDate.of(2023,12,8);
        LocalDate dueDate2= LocalDate.of(2024,1,30);
       ArrayList <Loan> actual= loanDao.getCurrentLoans(1);
        Loan l1=new Loan(1,1,1,borrowDate1,dueDate1,null);
        Loan l2=new Loan(2,1,2,borrowDate2,dueDate2,null);
        ArrayList <Loan> expected=new ArrayList();
        expected.add(l1);
        expected.add(l2);
        assertEquals(actual,expected);
    }
/**Test for get current loans when no loans are present**/
    @Test
    void getCurrentLoans_WhenNoLoansArePresent() {
        LoanDao loanDao = new LoanDao("testca3library");
        ArrayList <Loan> actual= loanDao.getCurrentLoans(10);
        ArrayList <Loan> expected=new ArrayList();
        assertEquals(actual,expected);
    }
/**Test for getting a user's previous loans when the loans are present**/
    @Test
    void getPreviousLoans_WhenPreviousLoansExist() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate1= LocalDate.of(2023,12,5);
        LocalDate dueDate1= LocalDate.of(2023,12,7);
        LocalDate returnDate1= LocalDate.of(2023,12,7);
        ArrayList <Loan> actual= loanDao.getPreviousLoans(1);
        Loan l1=new Loan(3,1,3,borrowDate1,dueDate1,returnDate1);
        ArrayList <Loan> expected=new ArrayList();
        expected.add(l1);
        assertEquals(actual,expected);
    }

    /**Test for getting previous loans when No previous loans are present**/
    @Test
    void getPreviousLoans_WhenNoPreviousLoansExist() {
        LoanDao loanDao = new LoanDao("testca3library");
        ArrayList <Loan> actual= loanDao.getPreviousLoans(10);
        ArrayList <Loan> expected=new ArrayList();
        assertEquals(actual,expected);
    }
/**Test getting a user's overDue when Overdue loans are present  **/
    @Test
    void getOverDueLoans_WhenOverDueLoansExist() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate1= LocalDate.of(2023,12,7);
        LocalDate dueDate1= LocalDate.of(2023,12,8);
        ArrayList <Loan> actual= loanDao.getOverDueLoans(1);
        Loan l1=new Loan(1,1,1,borrowDate1,dueDate1,null);
        ArrayList <Loan> expected=new ArrayList();
        expected.add(l1);
        assertEquals(actual,expected);
    }

    /**Test getting overDue loans when No Overdue loans are present  **/
    @Test
    void getOverDueLoans_WhenNoOverDueLoansExist() {
        LoanDao loanDao = new LoanDao("testca3library");
        ArrayList <Loan> actual= loanDao.getOverDueLoans(10);
        ArrayList <Loan> expected=new ArrayList();
        assertEquals(actual,expected);
    }
    /**Insert an overdue loan**/
    @BeforeEach
    void setUpPayOverDueFee() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.of(2023,12,2);
        LocalDate dueDate= LocalDate.of(2023,12,7);
        loanDao.borrowBook(1,5,borrowDate,dueDate);
    }
    /**pay overdue fee for an existing Loan**/
    @Test
    void payOverDueFee_WhenLoanIsPresent() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.of(2023,12,2);
        LocalDate dueDate= LocalDate.of(2023,12,7);
        LocalDate present= LocalDate.now();
        int days= (int)dueDate.until(present , ChronoUnit.DAYS);
        Loan l = loanDao.getLoanByLoanFields(1,5,borrowDate,dueDate);
        double fee=days*1;
      boolean actual  =loanDao.payOverDueFee(l.getLoanId(),fee);
        boolean expected  =true;
       assertEquals(actual, expected);
       if(actual==expected){
           Loan l2 = loanDao.getLoanById(l.getLoanId());
           assertEquals(l2.getFees(), fee);
       }
    }
    /**delete the inserted loan**/
    @AfterEach
    void tearDownPayOverDueFee() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.of(2023,12,2);
        LocalDate dueDate= LocalDate.of(2023,12,7);
        LocalDate present= LocalDate.now();
        int days= (int)dueDate.until(present , ChronoUnit.DAYS);
        Loan l = loanDao.getLoanByLoanFields(1,5,borrowDate,dueDate);
        loanDao.deleteLoan(l.getLoanId());
    }
/**When loan is not present**/
    @Test
    void payOverDueFee_WhenLoanIsNotPresent() {
        LoanDao loanDao = new LoanDao("testca3library");
        boolean actual  =loanDao.payOverDueFee(500,20);
        boolean expected  =false;
        assertEquals(actual, expected);
    }

    /**When loan has been returned**/
    @Test
    void payOverDueFee_forAReturnedLoan() {
        LoanDao loanDao = new LoanDao("testca3library");
        boolean actual  =loanDao.payOverDueFee(3,20);
        boolean expected  =false;
        assertEquals(actual, expected);
    }
/**insert Loan for payOverDueFee_forNotDueLoan()**/
    @BeforeEach
    void setUpPayOverDueFee_forNotDueLoan() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.now().minusDays(5);
        LocalDate dueDate= LocalDate.now().plusDays(1);
        loanDao.borrowBook(1,4,borrowDate,dueDate);
    }
    /**pay overdue fee when Loan is not due**/
    @Test
    void payOverDueFee_forNotDueLoan() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.now().minusDays(5);
        LocalDate dueDate= LocalDate.now().plusDays(1);
        LocalDate present= LocalDate.now();
        int days= (int)dueDate.until(present , ChronoUnit.DAYS);
        Loan l = loanDao.getLoanByLoanFields(1,4,borrowDate,dueDate);
        double fee=days*1;
        boolean actual  =loanDao.payOverDueFee(l.getLoanId(),fee);
        boolean expected  =false;
        assertEquals(actual, expected);
    }
    /**delete the inserted loan**/
    @AfterEach
    void tearDownPayOverDueFee_forNotDueLoan() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.now().minusDays(5);
        LocalDate dueDate= LocalDate.now().plusDays(1);
        LocalDate present= LocalDate.now();
        int days= (int)dueDate.until(present , ChronoUnit.DAYS);
        Loan l = loanDao.getLoanByLoanFields(1,4,borrowDate,dueDate);
        loanDao.deleteLoan(l.getLoanId());
    }
/**borrow a book**/
    @BeforeEach
    void setUp_returnBook() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.now().minusDays(5);
        LocalDate dueDate= LocalDate.now().plusDays(1);
        loanDao.borrowBook(1,5,borrowDate,dueDate);
    }
/**return the borrowed book**/
    @Test
    void returnBook() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.now().minusDays(5);
        LocalDate dueDate= LocalDate.now().plusDays(1);
        Loan l = loanDao.getLoanByLoanFields(1,5,borrowDate,dueDate);
        boolean actual = loanDao.returnBook(l.getLoanId());
        LocalDate expectedDate= LocalDate.now();
        boolean expected=true;
        assertEquals(actual, expected);
        if(actual==expected){
            Loan actualLoan=loanDao.getLoanById(l.getLoanId());
            Loan expectedLoan= new Loan(l.getLoanId(),1,5,borrowDate,dueDate,expectedDate);
            assertEquals(expectedLoan,actualLoan);
           assertEquals(actualLoan.getReturnedDate(), expectedDate);
        }
    }
/**Delete the loan**/
    @AfterEach
    void tearDown_returnBook() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.now().minusDays(5);
        LocalDate dueDate= LocalDate.now().plusDays(1);
        Loan l = loanDao.getLoanByLoanFields(1,5,borrowDate,dueDate);
        loanDao.deleteLoan(l.getLoanId());
    }
/**Return a book for a loan that doesn't exist**/
    @Test
    void returnBook_WhenLoanNotPresent() {
        LoanDao loanDao = new LoanDao("testca3library");
        boolean actual = loanDao.returnBook(1000);
        boolean expected=false;
        assertEquals(actual, expected);

    }
/**When a loan exist**/
    @Test
    void getLoanById_WhenLoanExist() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate borrowDate= LocalDate.of(2023,12,7);
        LocalDate dueDate= LocalDate.of(2023,12,9);
        Loan actual= loanDao.getLoanById(1);
        Loan expected=new Loan(1,1,1,borrowDate,dueDate,null);
        assertEquals(actual,expected);
    }
/**When the loan doesn't exist**/
    @Test
    void getLoanById_WhenLoanDoesNotExist() {
        LoanDao loanDao = new LoanDao("testca3library");
        Loan actual= loanDao.getLoanById(1000);
        Loan expected=null;
        assertEquals(actual,expected);
    }
    @BeforeEach
    void setUp_borrowBook() {

    }
    @Test
    void borrowBook() {
       LoanDao loanDao = new LoanDao("testca3library");
        LocalDate dueDate= LocalDate.now().plusDays(1);
      boolean actual= loanDao.borrowBook(1,5,dueDate);
      boolean expected=true;
      assertEquals(actual,expected);
    }

    @AfterEach
    void tearDown_borrowBook() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate dueDate= LocalDate.now().plusDays(1);
        LocalDate borrowedDate= LocalDate.now();
       Loan l =loanDao.getLoanByLoanFields(1,5,borrowedDate,dueDate);
       loanDao.deleteLoan(l.getLoanId());
    }
    @BeforeEach
    void setUp_borrowBook2() {

    }
    @Test
    void borrowBook2() {
        LoanDao loanDao = new LoanDao("testca3library");
        LocalDate dueDate= LocalDate.of(2023,12,9);
        boolean actual= loanDao.borrowBook(2,1,dueDate);
        boolean expected=false;
        assertEquals(actual,expected);
    }
}