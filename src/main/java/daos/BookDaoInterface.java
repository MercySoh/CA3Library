package daos;

import business.Book;

import java.util.List;

public interface BookDaoInterface {
    /**
     * get all the books
     */
    public List<Book> getAllBooks();

    /**
     * get the book by ID
     */
    public Book getBookByID(int bookID);

    /**
     * search book by the title
     */
    public List<Book> searchBookByTitle(String title);

    /**
     * update the quantity in the book
     */
    public int updateBookQuantity(int bookID, int increase);

    /**
     * Add book to the library database.
     */
    public int addBook (String bookName, String author, String desc, int quantity);

    /**
     * Add book to the library database.
     */
    public int deleteBook(int bookID);
}
