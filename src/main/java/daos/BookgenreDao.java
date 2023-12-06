package daos;

import business.Bookgenre;
import business.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookgenreDao extends Dao implements BookgenreDaoInterface{

    public BookgenreDao(String dbName) {
        super(dbName);
    }

    public BookgenreDao(Connection con) {
        super(con);
    }

    @Override
        public ArrayList<Bookgenre> getAllBookGenres(int bookId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Bookgenre> bookgenres = new ArrayList();
        try {
            con = getConnection();

            String query = "Select * from bookgenres where bookID = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, bookId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bookgenre bg = new Bookgenre(rs.getInt("bookId"), rs.getInt("genreId"));
                bookgenres.add(bg);
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in the getAllBookGenres() method: " + e.getMessage());
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
                System.out.println("Exception occurred in the final section of the getAllBookGenres() method: " + e.getMessage());
            }
        }
        return bookgenres;
    }

    @Override
    public boolean addBookGenre(int bookId, int genreId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int rowsAffected=0;
        boolean state=false;
        try {
            con = getConnection();

            String command = "insert into bookgenres (bookId,genreId) values (?,?) ";
            ps = con.prepareStatement(command);
            ps.setInt(1, bookId);
            ps.setInt(2, genreId);
            rowsAffected = ps.executeUpdate();
           if(rowsAffected==1) {
               state=true;
           }

        } catch (SQLException e) {
            System.out.println("Exception occurred in the addBookGenre() method: " + e.getMessage());
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
                System.out.println("Exception occurred in the final section of the addBookGenre() method: " + e.getMessage());
            }
        }
        return state;
    }


    @Override
    public ArrayList<Bookgenre> getBookGenresByGenreId(int genreId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Bookgenre> bookgenres = new ArrayList();
        try {
            con = getConnection();

            String query = "Select * from bookgenres where genreID = ? ";
            ps = con.prepareStatement(query);
            ps.setInt(1, genreId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bookgenre bg = new Bookgenre(rs.getInt("bookId"), rs.getInt("genreId"));
                bookgenres.add(bg);
            }

        } catch (SQLException e) {
            System.out.println("Exception occurred in the getBookGenresByGenreId() method: " + e.getMessage());
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
                System.out.println("Exception occurred in the final section of the getBookGenresByGenreId() method: " + e.getMessage());
            }
        }
        return bookgenres;
    }
}
