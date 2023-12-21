package daos;

import business.Book;
import business.Loan;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class LoanDao extends Dao implements LoanDaoInterface {

    public LoanDao(String dbName) {
        super(dbName);
    }

    public LoanDao(Connection con) {
        super(con);
    }

    /**
     * Method gets all the user's existing loans
     *
     * @param userId, the intended user's Id
     * @return ArrayList of loans
     */
    @Override
    public ArrayList<Loan> getCurrentLoans(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Loan> loans = new ArrayList();
        try {
            con = getConnection();

            String query = "Select * from loans where userId = ? and returnedDate is NULL";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Loan l;
                if (rs.getDate("returnedDate") != null) {
                    l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
                } else {
                    l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), null, rs.getDouble("fees"));
                }
                loans.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in the getCurrentLoans() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the final section of the getCurrentLoans() method: " + e.getMessage());
            }
        }
        return loans;
    }

    @Override
    public ArrayList<Loan> getPreviousLoans(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Loan> loans = new ArrayList();
        try {
            con = getConnection();

            String query = "Select * from loans where userId = ? and returnedDate is not NULL";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Loan l;
                if (rs.getDate("returnedDate") != null) {
                    l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
                } else {
                    l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), null, rs.getDouble("fees"));
                }
                loans.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in the getPreviousLoans() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the final section of the getPreviousLoans() method: " + e.getMessage());
            }
        }
        return loans;
    }

    @Override
    public ArrayList<Loan> getOverDueLoans(int userId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Loan> loans = new ArrayList();
        try {
            con = getConnection();

            String query = "Select * from loans where dueDate<now() and userId = ? and returnedDate is NULL";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Loan l;
                if (rs.getDate("returnedDate") != null) {
                    l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
                } else {
                    l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), null, rs.getDouble("fees"));
                }
                loans.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in the getOverDueLoans() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the final section of the getOverDueLoans() method: " + e.getMessage());
            }
        }
        return loans;
    }

    @Override
    public boolean payOverDueFee(int loanId, double fee) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        boolean state = false;
        try {
            con = getConnection();

            String command = "update loans set fees=?  where loanId=? and now()>dueDate and returnedDate is Null ";
            ps = con.prepareStatement(command);
            ps.setDouble(1, fee);
            ps.setInt(2, loanId);
            rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                state = true;
            }


        } catch (SQLException e) {
            System.out.println("Exception occurred in  the payOverDueFee() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in  the payOverDueFee() method:  " + e.getMessage());
            }
        }

        return state;
    }

    @Override
    public boolean returnBook(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        boolean state = false;

        try {
            con = getConnection();

            String command = "update loans set returnedDate=now() where loanId=? ";
            ps = con.prepareStatement(command);
            ps.setInt(1, loanId);
            rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                state = true;
            }


        } catch (SQLException e) {
            System.out.println("Exception occurred in  the returnBook() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in  the returnBook() method:  " + e.getMessage());
            }
        }


        return state;
    }

    @Override
    public Loan getLoanById(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = null;
        try {
            con = getConnection();

            String query = "select * from loans where loanId= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, loanId);
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getDate("returnedDate") != null) {
                    loan = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
                } else {
                    loan = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), null, rs.getDouble("fees"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in  the getLoanById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in  the getLoanById() method:  " + e.getMessage());
            }
        }
        return loan;
    }

    public boolean borrowBook(int userId, int bookId, LocalDate dueDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        boolean state = false;
        try {

            con = getConnection();
            String command = "insert into loans (userID,bookID,borrowDate,dueDate) values (?,?,now(),?) ";
            ps = con.prepareStatement(command);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.setDate(3, Date.valueOf(dueDate));
            rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                state = true;
            }


        } catch (SQLException e) {
            System.out.println("Exception occurred in the borrowBook() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the final section of the borrowBook() method: " + e.getMessage());
            }
        }
        return state;
    }

    public boolean borrowBook(int userId, int bookId, LocalDate borrowDate, LocalDate dueDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;
        boolean state = false;
        try {

            con = getConnection();
            String command = "insert into loans (userID,bookID,borrowDate,dueDate) values (?,?,?,?) ";
            ps = con.prepareStatement(command);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.setDate(3, Date.valueOf(borrowDate));
            ps.setDate(4, Date.valueOf(dueDate));
            rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                state = true;
            }


        } catch (SQLException e) {
            System.out.println("Exception occurred in the borrowBook() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in the final section of the borrowBook() method: " + e.getMessage());
            }
        }
        return state;
    }

    public Loan getLoanByLoanFields(int userId, int bookId, LocalDate borrowDate, LocalDate dueDate) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan loan = null;
        try {
            con = getConnection();

            String query = "select * from loans where userId= ? and bookId= ? and borrowDate= ? and dueDate= ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ps.setDate(3, Date.valueOf(borrowDate));
            ps.setDate(4, Date.valueOf(dueDate));
            rs = ps.executeQuery();

            if (rs.next()) {
                if (rs.getDate("returnedDate") != null) {
                    loan = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
                } else {
                    loan = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), null, rs.getDouble("fees"));
                }
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in  the getLoanById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in  the getLoanById() method:  " + e.getMessage());
            }
        }
        return loan;
    }

    public TreeMap<Integer, Integer> getTrendingBooks() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TreeMap<Integer, Integer> trends = new TreeMap<>(Comparator.reverseOrder());
        try {
            con = getConnection();

            String query = "SELECT count(bookID) as num, bookID from loans where  DATEDIFF(now(),borrowDate)<30 group by bookID order by num DESC";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                trends.put(rs.getInt("booKID"), rs.getInt("num"));
                count++;
                if (count == 10) {
                    break;
                }
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in  the getLoanById() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in  the getLoanById() method:  " + e.getMessage());
            }
        }
        return trends;
    }

    public int deleteLoan(int loanId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected = 0;

        try {
            con = getConnection();

            String command = "delete from loans where loanId=?";
            ps = con.prepareStatement(command);
            ps.setInt(1, loanId);
            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occurred in  the deleteLoan() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occurred in  the deleteLoan() method:  " + e.getMessage());
            }
        }


        return rowsAffected;
    }

}
