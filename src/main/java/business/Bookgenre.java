package business;

import java.util.Objects;

public class Bookgenre {
    private int bookId;
    private int genreId;

    public Bookgenre(int bookId, int genreId) {
        this.bookId = bookId;
        this.genreId = genreId;
    }

    public Bookgenre() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookgenre that = (Bookgenre) o;
        return bookId == that.bookId && genreId == that.genreId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, genreId);
    }

    @Override
    public String toString() {
        return "Bookgenre{" +
                "bookId=" + bookId +
                ", genreId=" + genreId +
                '}';
    }
}
