package daos;

import business.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoanDao extends Dao implements LoanDaoInterface {

    public LoanDao(String dbName) {
        super(dbName);
    }

    public LoanDao(Connection con) {
        super(con);
    }

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
                Loan l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
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
                Loan l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
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
                Loan l = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
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

            String command = "update loans set fee=?  where loanId=? ";
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

            String command = "update loans set returnDate=now() where loanId=? ";
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
                loan = new Loan(rs.getInt("loanId"), rs.getInt("userId"), rs.getInt("bookId"), rs.getDate("borrowDate").toLocalDate(), rs.getDate("dueDate").toLocalDate(), rs.getDate("returnedDate").toLocalDate(), rs.getDouble("fees"));
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


}
