package tests;

import business.Book;
import daos.BookDao;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDaoTest {

    private final BookDao bookDao = new BookDao("ca3librarytest");

    /**
     * get all books, normal
     */
    @Test
    void getAllBooks_normal() {
        Book book1 = new Book("one piece", "oda", "it is a manga of pirates", 1000);
        book1.setBookID(1);
        Book booklast = new Book("blue lock", "yusuke nomura", "hardcore soccer", 66);
        booklast.setBookID(5);

        List<Book> books = bookDao.getAllBooks();
        assertEquals(5, books.size());

        assertEquals(books.get(0).getBookID(), book1.getBookID());
        assertEquals(books.get(4).getBookID(), booklast.getBookID());
    }

    /**
     * get book by ID, normal
     */
    @Test
    void getBookByID_normal() {
        Book book1 = new Book("one piece", "oda", "it is a manga of pirates", 1000);
        book1.setBookID(1);

        Book result = bookDao.getBookByID(1);

        assertEquals(book1.getBookID(), result.getBookID());
    }

    /**
     * get book by ID, no ID
     */
    @Test
    void getBookByID_none() {
        Book result = bookDao.getBookByID(10000);

        assertNull(result);
    }

    /**
     * search book by title, normal
     */
    @Test
    void searchBookByTitle_normal() {
        List<Book> books = bookDao.searchBookByTitle("blue");
        Book book = new Book("blue lock", "yusuke nomura", "hardcore soccer", 66);
        book.setBookID(5);

        assertEquals(1, books.size());
        assertEquals(5, book.getBookID());
    }

    /**
     * search book by title, no title found
     */
    @Test
    void searchBookByTitle_none() {
        List<Book> books = bookDao.searchBookByTitle("asfaf");

        assertEquals(0, books.size());
    }

    /**
     * update book quantity, normal
     */
    @Test
    void updateBookQuantity_normal() {
        int act = bookDao.updateBookQuantity(1, 10);
        assertEquals(1, act);

        Book book = bookDao.getBookByID(1);

        act = bookDao.updateBookQuantity(1, -10);
        assertEquals(1, act);

        assertEquals(book.getQuantity(), 1010);
    }

    /**
     * update book quantity, noID
     */
    @Test
    void updateBookQuantity_noID() {
        int act = bookDao.updateBookQuantity(1000, 10);
        assertEquals(0, act);
    }

    /**
     * update book quantity, negative negated
     */
    @Test
    void updateBookQuantity_negative_negated() {
        int act = bookDao.updateBookQuantity(1, -1110);
        assertEquals(1, act);

        Book book = bookDao.getBookByID(1);

        act = bookDao.updateBookQuantity(1, 1000);
        assertEquals(1, act);

        assertEquals(book.getQuantity(), 0);
    }
}