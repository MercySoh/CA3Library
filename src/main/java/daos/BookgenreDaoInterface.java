package daos;

import business.Bookgenre;

import java.util.ArrayList;

public interface BookgenreDaoInterface {
    /**
     * gets all BookGenres based on the bookId
     *
     * @param bookId, the bookId to be searched
     * @return an ArrayList of BookGenres or an empty arraylist if no match was found
     **/
    public ArrayList<Bookgenre> getAllBookGenres(int bookId);

    /**
     * gets all BookGenres based on the genreId
     *
     * @param genreId, the intended genreId to be searched
     * @return an ArrayList of BookGenres or an empty ArrayList if no match was found
     **/
    public ArrayList<Bookgenre> getBookGenresByGenreId(int genreId);

}
