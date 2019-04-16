package Task2;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Reader {

    private transient String name;
    private transient String surname;
    private transient int age;
    private transient int library_card_number;
    private transient Map<Book, Rent> activeBooks;

    public Reader() {
        name = "NoName";
        surname = "NoSurname";
        age = 0;
        library_card_number = 0;
        activeBooks = null;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getLibrary_card_number() {
        return library_card_number;
    }

    public Map<Book, Rent> getActiveBooks() {
        return activeBooks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLibrary_card_number(int library_card_number) {
        this.library_card_number = library_card_number;
    }

    public void setActiveBooks(Map<Book, Rent> activeBooks) {
        this.activeBooks = activeBooks;
    }

    public void pickBook(Book b) {
        Rent rent = new Rent((int) (Math.random()*10));
        this.activeBooks.put(b, rent);
    }

    public void ShowActiveBooks () {
        System.out.println("Active Books and Rent: ");
        for (Map.Entry entry : this.getActiveBooks().entrySet()) {
            System.out.println(entry.getKey().toString() + entry.getValue().toString());
        }
    }

    @Override
    public String toString() {
        return "Reader: Name - " + this.getName() + ", Surname - " +
                this.getSurname() + ", Age - " + this.getAge() +
                ", Library Card Number - " + getLibrary_card_number() +
                "\n";
    }

    void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this.getName());
        out.writeObject(this.getSurname());
        out.writeObject(this.getAge());
        out.writeObject(this.getLibrary_card_number());

        out.writeObject(this.getActiveBooks().size());

        for (Task2.Book b : this.getActiveBooks().keySet()) {
            out.writeObject(b.getTitle());
            b.getAuthor().writeAuthorObj(out);
            out.writeObject(b.getYear());
            out.writeObject(b.getColor());
            out.writeObject(b.getGenre());
            out.writeObject(b.getNum_of_pages());
            out.writeObject(this.getActiveBooks().get(b));
        }
    }

    void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.setName((String)in.readObject());
        this.setSurname((String)in.readObject());
        this.setAge((int)in.readObject());
        this.setLibrary_card_number((int)in.readObject());

        Map<Book,Rent> aBook = new HashMap<>();
        Task2.Author author = new Task2.Author();
        Book b; String t = "";
        int size = (int) in.readObject();

        for (int i = 0; i < size; i++) {
            t = (String) in.readObject();
            author.readAuthorObj(in);
            b = new Task2.Book(t, author, (int) in.readObject(), (String) in.readObject(),
                    (String) in.readObject(), (int) in.readObject());
            aBook.put(b, (Rent)in.readObject());
        }
        this.setActiveBooks(aBook);
    }

//      WORKS
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Reader reader = new Reader();
//        reader.setName("Jenny");
//        reader.setSurname("Willson");
//        reader.setAge(19);
//        reader.setLibrary_card_number(48737709);
//        reader.setActiveBooks(new HashMap<Book, Rent>());
//
//        Task2.Author a2 = new Task2.Author("George R.R.", "Martin");
//        Task2.Book b2 = new Task2.Book("Game Of Thrones", a2);
//
//        Author a3 = new Author("Patric", "Rothfuss");
//        Book b3 = new Book("The Name Of The Wind", a3, 1943, "Black", "Fantasy", 835);
//
//        reader.pickBook(b2);
//        reader.pickBook(b3);
//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Reader.dat"));
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Reader.dat"));
//        System.out.println("\tBEFORE SERIALIZATION: ");
//        System.out.println(reader.toString());
//        reader.ShowActiveBooks();
//
//        //serialization
//        reader.writeObject(oos);
//
//        //deserialization
//        System.out.println("\tAFTER SERIALIZATION: ");
//        reader.readObject(ois);
//
//        System.out.println(reader.toString());
//        reader.ShowActiveBooks();
//
//    }
}
