package tests;

import business.Bookgenre;
import daos.BookgenreDao;
import daos.LoanDao;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BookgenreDaoTest {
    BookgenreDao bookgenreDao = new BookgenreDao("ca3librarytest");

    /**
     * when the book id exist in the bookgenres table
     **/
    @Test
    void getAllBookGenres() {
        ArrayList<Bookgenre> actual = bookgenreDao.getAllBookGenres(1);
        Bookgenre bg1 = new Bookgenre(1, 1);
        Bookgenre bg2 = new Bookgenre(1, 2);
        ArrayList<Bookgenre> expected = new ArrayList();
        expected.add(bg1);
        expected.add(bg2);
        assertEquals(actual, expected);
    }

    /**
     * When no match exist in bookgenres table
     **/
    @Test
    void getAllBookGenres_WhenNoMatch() {
        ArrayList<Bookgenre> actual = bookgenreDao.getAllBookGenres(10);
        ArrayList<Bookgenre> expected = new ArrayList();
        assertEquals(actual, expected);
    }

    /**
     * When the genreId exist in the bookgenres table
     **/
    @Test
    void getBookGenresByGenreId() {
        ArrayList<Bookgenre> actual = bookgenreDao.getBookGenresByGenreId(1);
        Bookgenre bg1 = new Bookgenre(1, 1);
        Bookgenre bg2 = new Bookgenre(2, 1);
        Bookgenre bg3 = new Bookgenre(4, 1);
        ArrayList<Bookgenre> expected = new ArrayList();
        expected.add(bg1);
        expected.add(bg2);
        expected.add(bg3);
        assertEquals(actual, expected);
    }

    /**
     * When the genreId doesn't exist in the bookgenres table
     **/
    @Test
    void getBookGenresByGenreId_WhenNoMatchExist() {
        ArrayList<Bookgenre> actual = bookgenreDao.getBookGenresByGenreId(10);
        ArrayList<Bookgenre> expected = new ArrayList();
        assertEquals(actual, expected);
    }
}