package daos;

import business.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDao extends Dao implements BookDaoInterface {

    public BookDao(String dbName) {
        super(dbName);
    }
    public BookDao(Connection con) {super(con); }

    /**
     * get all the books in the table
     * @return all books in table
     */
    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        String query = "SELECT * FROM books";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("bookID"),
                        rs.getString("bookName"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            // Handle or log the exception
            System.out.println("something went wrong with the getAllBooks");
            System.out.println(e.getMessage());
        }
        finally {
            freeConnection("fail to free connection at getAllBooks");
        }

        return books;
    }


    /**
     * get a book based on an ID
     * @param bookID the bookID
     * @return the book found, or null if not found
     */
    @Override
    public Book getBookByID(int bookID) {
        Book book = null;

        String query = "SELECT * FROM books WHERE bookID = ?";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    book = new Book(
                            rs.getInt("bookID"),
                            rs.getString("bookName"),
                            rs.getString("author"),
                            rs.getString("description"),
                            rs.getInt("quantity")
                    );
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            System.out.println("something went wrong with getBookID");
            System.out.println(e.getMessage());
        }
        finally {
            freeConnection("fail to free connection at getBookByID");
        }

        return book;
    }


    /**
     * search book by title
     * @param title the book title
     * @return the list of books found
     */
    @Override
    public List<Book> searchBookByTitle(String title) {
        List<Book> books = new ArrayList<>();

        String query = "SELECT * FROM books WHERE bookName LIKE ?";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, title + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("bookID"),
                        rs.getString("bookName"),
                        rs.getString("author"),
                        rs.getString("description"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            // Handle or log the exception
            System.out.println("something went wrong with the getAllBooks");
            System.out.println(e.getMessage());
        }
        finally {
            freeConnection("fail to close connection at the getAllBooks");
        }

        return books;
    }

    /**
     * updates the book quantity
     * @param bookID the bookID
     * @param quantity quantity to add / subtract
     * @return the rows affected, 1 for success, 0 for fail
     */
    @Override
    public int updateBookQuantity(int bookID, int quantity) {
        int rowsAffected = 0;
        String query = "UPDATE books SET quantity = CASE " +
                "WHEN quantity + (?) < 0 THEN 0 " +
                "ELSE quantity + (?) " +
                "END " +
                "WHERE bookID = ?";

        try{
            con = getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, quantity);
            ps.setInt(2, quantity);
            ps.setInt(3, bookID);

            rowsAffected = ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("something went wrong with the updateBookQuantity");
            System.out.println(e.getMessage());
        }
        finally {
            freeConnectionUpdate("fail to close connection at the updateBookQuantity");
        }
        return rowsAffected;
    }
}
