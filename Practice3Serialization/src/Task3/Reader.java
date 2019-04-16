package Task3;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Reader implements Externalizable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private int age;
    private int library_card_number;
    private Map<Book, Rent> activeBooks;

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
        activeBooks = new HashMap<>();
        Rent rent = new Rent((int) (Math.random()*10));
        this.activeBooks.put(b, rent);
    }

    public void ShowActiveBooks () {
        Set<Book> books = activeBooks.keySet();
        Collection<Rent> rents = activeBooks.values();
        Set<Map.Entry<Book, Rent>> set = activeBooks.entrySet();

        System.out.println("Active Books and Rent: ");
        for (Map.Entry<Book, Rent> me : set) {
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }

    }

    @Override
    public String toString() {
        return "Reader: Name - " + this.getName() + ", Surname - " +
                this.getSurname() + ", Age - " + this.getAge() +
                ", Library Card Number - " + getLibrary_card_number() +
                "\n";
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getName());
        out.writeObject(this.getSurname());
        out.writeObject(this.getAge());
        out.writeObject(this.getLibrary_card_number());

        out.writeObject(this.getActiveBooks().size());
        Rent r;

        for (Task3.Book b : this.getActiveBooks().keySet()) {
            b.writeExternal(out);
            r = this.getActiveBooks().get(b);
            r.writeExternal(out);
        }
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setName((String)in.readObject());
        this.setSurname((String)in.readObject());
        this.setAge((int)in.readObject());
        this.setLibrary_card_number((int)in.readObject());

        Map<Task3.Book, Task3.Rent> aBook = new HashMap<>();
        Task3.Book b = new Book();
        Rent r = new Rent();
        int size = (int) in.readObject();

        for (int i = 0; i < size; i++) {
            b.readExternal(in);
            r.readExternal(in);
            aBook.put(b, r);
        }
        this.setActiveBooks(aBook);
    }
}
