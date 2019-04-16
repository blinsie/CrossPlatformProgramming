package Task2;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Library implements Serializable {

    public static void main(String[] args) {

    Author a1 = new Author("J.K. ", "Rowling");
    Book b1 = new Book("Harry Potter And The Sorcerer's Stone", a1);

    Author a2 = new Author("George R.R.", "Martin");
    Book b2 = new Book("Game Of Thrones", a2);

    Author a3 = new Author("Patric", "Rothfuss");
    Book b3 = new Book("The Name Of The Wind", a3, 1943, "Black", "Fantasy", 835);

    Author a4 = new Author("Brandon", "Sanderson");
    Book b4 = new Book("The Way Of Kings", a4);

    Author a5 = new Author("Ursula", "Leguin");
    Book b5 = new Book("A Wizard Of Earthsea", a5);

    List<Book> sh1 = Arrays.asList(b1, b5);
    List<Book> sh2 = Arrays.asList(b2, b3, b4);
    List<Book> sh3 = Arrays.asList(b1, b3, b4, b5);

    Bookcase bookcase = new Bookcase(1, "fantasy", sh1, sh2, sh3);

    Reader reader = new Reader();

        System.out.println(bookcase.toString());
        System.out.println(reader.toString());

        reader.setName("Jenny");
        reader.setSurname("Willson");
        reader.setAge(19);
        reader.setLibrary_card_number(48737709);
        System.out.println(reader.toString());

        reader.setActiveBooks(new HashMap<>());
        reader.pickBook(b3);
        reader.ShowActiveBooks();

    //Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Library.dat"))) {
            a1.writeAuthorObj(oos); b1.writeBookObj(oos);
            a2.writeAuthorObj(oos); b2.writeBookObj(oos);
            a3.writeAuthorObj(oos); b3.writeBookObj(oos);
            a4.writeAuthorObj(oos); b4.writeBookObj(oos);
            a5.writeAuthorObj(oos); b5.writeBookObj(oos);

            bookcase.writeObject(oos);
            reader.writeObject(oos);

            oos.flush();
            oos.close();
            System.out.println("File has been written.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    //Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Library.dat"))){
            Author a11 = new Author(); a11.readAuthorObj(ois);
            Book b11 = new Book(); b11.readBookObj(ois);
            Author a22 = new Author(); a22.readAuthorObj(ois);
            Book b22 = new Book(); b22.readBookObj(ois);
            Author a33 = new Author(); a33.readAuthorObj(ois);
            Book b33 = new Book(); b33.readBookObj(ois);
            Author a44 = new Author(); a44.readAuthorObj(ois);
            Book b44 = new Book(); b44.readBookObj(ois);
            Author a55 = new Author(); a55.readAuthorObj(ois);
            Book b55 = new Book(); b55.readBookObj(ois);

            Bookcase bookcase1 = new Bookcase();
            bookcase1.readObject(ois);
            Reader reader1 = new Reader();
            reader1.readObject(ois);
            ois.close();

            System.out.println("\n\tCHECKING DESERIALIZATION: \n");
            System.out.println(bookcase1.toString());
            System.out.println(reader1.toString());
            reader1.ShowActiveBooks();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
