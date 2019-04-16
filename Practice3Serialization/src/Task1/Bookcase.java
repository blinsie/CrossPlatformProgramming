package Task1;

import java.io.Serializable;
import java.util.List;

public class Bookcase implements Serializable {

    private int index;
    private String genre;
    private List<Book> shelf1;
    private List<Book> shelf2;
    private List<Book> shelf3;

    public Bookcase() {
        this.index = 0;
        this.genre = "NoGenre";
        this.shelf1 = null;
        this.shelf2 = null;
        this.shelf3 = null;
    }

    public Bookcase(String fantasy, List<Task2.Book> sh1, List<Task2.Book> sh2, List<Task2.Book> sh3) {
        genre = "NoGenre";
        shelf1 = null;
        shelf2 = null;
        shelf3 = null;
    }

    public Bookcase(int index, String genre, List<Book> shelf1, List<Book> shelf2, List<Book> shelf3) {
        this.index = index;
        this.genre = genre;
        this.shelf1 = shelf1;
        this.shelf2 = shelf2;
        this.shelf3 = shelf3;
    }

    public int getIndex() {
        return index;
    }

    public String getGenre() {
        return genre;
    }

    public List<Book> getShelf1() {
        return shelf1;
    }

    public List<Book> getShelf2() {
        return shelf2;
    }

    public List<Book> getShelf3() {
        return shelf3;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setShelf1(List<Book> shelf1) {
        this.shelf1 = shelf1;
    }

    public void setShelf2(List<Book> shelf2) {
        this.shelf2 = shelf2;
    }

    public void setShelf3(List<Book> shelf3) {
        this.shelf3 = shelf3;
    }

    @Override
    public String toString() {
        return "Bookcase: Index - " + ", Genre - " + this.getGenre() +
                "\n Shelf1 (" + this.getShelf1().toString() + ")\n" +
                " Shelf2 (" + this.getShelf2().toString() + ")\n" +
                " Shelf3 (" + this.getShelf3().toString() + ")\n" +"\n";
    }


}
