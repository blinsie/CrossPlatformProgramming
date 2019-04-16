package Task2;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bookcase implements Serializable {

    private int index;
    private String genre;
    private transient List<Book> shelf1;
    private transient List<Book> shelf2;
    private transient List<Book> shelf3;

    public Bookcase() {
        index = 0;
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
        return "Bookcase: Index - " + this.getIndex() + ", Genre - " + this.getGenre() +
                "\n Shelf1 (" + this.getShelf1().toString() + ")\n" +
                " Shelf2 (" + this.getShelf2().toString() + ")\n" +
                " Shelf3 (" + this.getShelf3().toString() + ")\n" +"\n";
    }

    void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this.getIndex());
        out.writeObject(this.getGenre());

        out.writeObject(getShelf1().size());
        out.writeObject(getShelf2().size());
        out.writeObject(getShelf3().size());

        List<List<Book>> shelfs = Arrays.asList(getShelf1(),getShelf2(),getShelf3());

        for (List<Book> shelf : shelfs)
            for (Task2.Book b : shelf) {
                out.writeObject(b.getTitle());
                out.writeObject(b.getAuthor().getName());
                out.writeObject(b.getAuthor().getSurname());
                out.writeObject(b.getAuthor().getAge());
                out.writeObject(b.getAuthor().getSex());
                out.writeObject(b.getAuthor().getCountry());
                out.writeObject(b.getYear());
                out.writeObject(b.getColor());
                out.writeObject(b.getGenre());
                out.writeObject(b.getNum_of_pages());
            }
    }


    void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        this.setIndex((int) in.readObject());
        this.setGenre((String) in.readObject());

        List<Integer> sizes = Arrays.asList((Integer)in.readObject(), (Integer)in.readObject(), (Integer)in.readObject());

        shelf1 = new ArrayList<>();
        shelf2 = new ArrayList<>();
        shelf3 = new ArrayList<>();

        List<List<Book>> shelfs = Arrays.asList(shelf1, shelf2, shelf3);

        int i; String t = "";

        Task2.Author author = new Task2.Author();
        Task2.Book b; int ind = 0;
        for (int size : sizes) {
            for (i = 0; i < size; i++) {
                t = (String) in.readObject();
                author.setName((String) in.readObject());
                author.setSurname((String) in.readObject());
                author.setAge((int) in.readObject());
                author.setSex((String) in.readObject());
                author.setCountry((String) in.readObject());
                b = new Task2.Book(t, author, (int) in.readObject(), (String) in.readObject(),
                        (String) in.readObject(), (int) in.readObject());
                shelfs.get(ind).add(b);
            }
            ind++;
        }
        this.setShelf1(shelfs.get(0));
        this.setShelf2(shelfs.get(1));
        this.setShelf3(shelfs.get(2));
    }

//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Task2.Author a1 = new Task2.Author("J.K. ", "Rowling");
//        Task2.Book b1 = new Task2.Book("Harry Potter And The Sorcerer's Stone", a1);
//
//        Task2.Author a2 = new Task2.Author("George R.R.", "Martin");
//        Task2.Book b2 = new Task2.Book("Game Of Thrones", a2);
//
//        Task2.Author a3 = new Task2.Author("Patric", "Rothfuss");
//        Task2.Book b3 = new Task2.Book("The Name Of The Wind", a3, 1943, "Black", "Fantasy", 835);
//
//        Task2.Author a4 = new Task2.Author("Brandon", "Sanderson");
//        Task2.Book b4 = new Task2.Book("The Way Of Kings", a4);
//
//        Task2.Author a5 = new Task2.Author("Ursula", "Leguin");
//        Task2.Book b5 = new Task2.Book("A Wizard Of Earthsea", a5);
//
//        List<Task2.Book> sh1 = Arrays.asList(b4, b5);
//        List<Task2.Book> sh2 = Arrays.asList(b2, b1, b4);
//        List<Task2.Book> sh3 = Arrays.asList(b1, b3, b4, b5);
//
//        Task2.Bookcase bookcase = new Task2.Bookcase(1, "fantasy", sh1, sh2, sh3);
//        System.out.println("\tBEFORE SERIALIZATION: ");
//        System.out.println(bookcase.toString());
//
//        //serialization
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Bookcase.dat"));
//        bookcase.writeObject(oos);
//        System.out.println("File has been written.");
//
//        //deserialization
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Bookcase.dat"));
//        Bookcase bookcase1 = new Bookcase();
//        bookcase1.readObject(ois);
//
//        System.out.println("AFTER SERIALIZATION: ");
//        System.out.println(bookcase1.toString());
//
//        oos.close(); ois.close();
//    }

}
