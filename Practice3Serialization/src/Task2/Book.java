package Task2;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Book {

    private transient String title;
    private transient Author author;
    private transient int year;
    private transient String color;
    private transient String genre;
    private transient int num_of_pages;

    public Book() {
        this.title = "NoTitle";
        author = null;
        year = 0;
        color = "NoColor";
        genre = "NoGenre";
        num_of_pages = 0;
    }

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, Author author, int year, String color, String genre, int num_of_pages) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.color = color;
        this.genre = genre;
        this.num_of_pages = num_of_pages;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public String getGenre() {
        return genre;
    }

    public int getNum_of_pages() {
        return num_of_pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNum_of_pages(int num_of_pages) {
        this.num_of_pages = num_of_pages;
    }

    @Override
    public String toString() {
        return "Book: Title - " + this.getTitle() + ", Author - " +
                this.getAuthor().getSurname() + " " + this.getAuthor().getName() +
                ", Year - " + this.getYear() + ", Color - " + this.getColor() +
                ", Genre - " + this.getGenre() + ", Number of Pages - " +
                this.getNum_of_pages() + "\n";
    }

    void writeBookObj(ObjectOutputStream out) throws IOException {
        out.writeObject(this.getTitle());
        this.getAuthor().writeAuthorObj(out);
        out.writeObject(this.getYear());
        out.writeObject(this.getColor());
        out.writeObject(this.getGenre());
        out.writeObject(this.getNum_of_pages());
    }

    void readBookObj (ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.setTitle((String) in.readObject());
        Task2.Author a = new Task2.Author();
        a.readAuthorObj(in);
        this.setAuthor(a);
        this.setYear((int) in.readObject());
        this.setColor((String) in.readObject());
        this.setGenre((String) in.readObject());
        this.setNum_of_pages((int) in.readObject());
    }
}
