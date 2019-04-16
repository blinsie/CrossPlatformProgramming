package Task1;

import java.io.Serializable;

public class Book implements Serializable {

    private String title;
    private Author author;
    private int year;
    private String color;
    private String genre;
    private int num_of_pages;

    public Book() {
        title = "NoTitle";
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

}
