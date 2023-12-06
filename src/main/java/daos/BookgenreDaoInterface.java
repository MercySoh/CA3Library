package daos;

import business.Bookgenre;

import java.util.ArrayList;

public interface BookgenreDaoInterface {
    public ArrayList <Bookgenre> getAllBookGenres(int bookId);
    public boolean addBookGenre(int bookId, int genreId);
    public ArrayList <Bookgenre> getBookGenresByGenreId(int genreId);

}
