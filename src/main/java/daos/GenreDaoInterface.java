package daos;

import business.Genre;

import java.util.List;

public interface GenreDaoInterface {
    /**
     * get all genres
     *
     * @return a list of genres
     */
    public List<Genre> getAllGenres();

    /**
     * get the genre based from the ID
     *
     * @param genreID the genreID
     * @return the genre
     */
    public Genre getGenreByID(int genreID);
}
