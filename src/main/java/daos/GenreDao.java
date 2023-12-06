package daos;

import business.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GenreDao extends Dao implements GenreDaoInterface{
    public GenreDao(String dbName) {
        super(dbName);
    }

    /**
     * Get a list of all genres in the library.
     *
     * @return A list of genres.
     */
    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();

        String query = "SELECT * FROM genres";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                genres.add(new Genre(
                        rs.getInt("genreID"),
                        rs.getString("genreName")
                ));
            }
        } catch (SQLException e) {
            // Handle or log the exception
            System.out.println("something went wrong with the getAllGenres");
            System.out.println(e.getMessage());
        }
        finally{
            freeConnection("fail to close connection at getAllGenres");
        }

        return genres;
    }

    /**
     * Get a genre by its ID.
     *
     * @param genreID The ID of the genre to retrieve.
     * @return The Genre object if found, otherwise null.
     */
    @Override
    public Genre getGenreByID(int genreID) {
        Genre genre = null;

        String query = "SELECT * FROM genres WHERE genreID = ?";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, genreID);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    genre = new Genre(
                            rs.getInt("genreID"),
                            rs.getString("genreName")
                    );
                }
            }
        } catch (SQLException e) {
            // Handle or log the exception
            System.out.println("something went wrong with the getGenreByID");
            System.out.println(e.getMessage());
        }
        finally {
            freeConnection("fail to close connection at getGenreByID");
        }


        return genre;
    }
}
