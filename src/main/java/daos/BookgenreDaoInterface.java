package daos;

import business.Bookgenre;

import java.util.ArrayList;

public interface BookgenreDaoInterface {
    public ArrayList <Bookgenre> getAllBookGenres(int bookId);
    public ArrayList <Bookgenre> getBookGenresByGenreId(int genreId);

}
