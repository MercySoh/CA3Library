package tests;

import business.Genre;
import daos.BookDao;
import daos.GenreDao;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreDaoTest {

    private final GenreDao genreDao = new GenreDao("ca3librarytest");

    /**
     * get all genres, normal scenario
     */
    @Test
    void getAllGenres_normal() {
        List<Genre> genres = genreDao.getAllGenres();

        assertEquals(5, genres.size());
    }

    /**
     * get genre by ID, normal scenario
     */
    @Test
    void getGenreByID_normal() {
        Genre genre = genreDao.getGenreByID(1);

        assertEquals(1, genre.getGenreID());
        assertEquals("comedy", genre.getGenreName());
    }

    /**
     * get genre by ID, no ID found
     */
    @Test
    void getGenreByID_noID() {
        Genre genre = genreDao.getGenreByID(1000);

        assertNull(genre);
    }
}