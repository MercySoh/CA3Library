package daos;

import business.Genre;

import java.util.List;

public interface GenreDaoInterface {
    /**
     * get all genres
     */
    public List<Genre> getAllGenres();

    /**
     * get the genre based from the ID
     */
    public Genre getGenreByID(int genreID);
}
