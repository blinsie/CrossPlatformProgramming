package Task3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bookcase implements Externalizable {

    private static final long serialVersionUID = 1L;
    private String genre;
    private List<Book> shelf1;
    private List<Book> shelf2;
    private List<Book> shelf3;

    public Bookcase() {
        genre = "NoGenre";
        shelf1 = null;
        shelf2 = null;
        shelf3 = null;
    }

    public Bookcase(String genre, List<Book> shelf1, List<Book> shelf2, List<Book> shelf3) {
        this.genre = genre;
        this.shelf1 = shelf1;
        this.shelf2 = shelf2;
        this.shelf3 = shelf3;
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
        return "Bookcase: Genre - " + this.getGenre() +
                "\n Shelf1 (" + this.getShelf1().toString() + ")\n" +
                " Shelf2 (" + this.getShelf2().toString() + ")\n" +
                " Shelf3 (" + this.getShelf3().toString() + ")\n" +"\n";
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getGenre());
        out.writeObject(getShelf1().size());
        out.writeObject(getShelf2().size());
        out.writeObject(getShelf3().size());

        List<List<Task3.Book>> shelfs = Arrays.asList(getShelf1(), getShelf2(), getShelf3());

        for (List<Task3.Book> shelf : shelfs)
            for (Task3.Book b : shelf)
                b.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.genre = (String) in.readObject();

        List<Integer> sizes = Arrays.asList((Integer)in.readObject(), (Integer)in.readObject(), (Integer)in.readObject());

        shelf1 = new ArrayList<>();
        shelf2 = new ArrayList<>();
        shelf3 = new ArrayList<>();

        List<List<Task3.Book>> shelfs = Arrays.asList(shelf1, shelf2, shelf3);

        int i; int ind = 0;
        Task3.Book b = new Book();

        for (int size : sizes) {
            for (i = 0; i < size; i++) {
                b.readExternal(in);
                shelfs.get(ind).add(b);
            }
            ind++;
        }
        this.setShelf1(shelfs.get(0));
        this.setShelf2(shelfs.get(1));
        this.setShelf3(shelfs.get(2));
        //  in.close();
    }
}
