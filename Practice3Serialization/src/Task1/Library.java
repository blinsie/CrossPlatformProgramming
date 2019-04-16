package Task1;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Library implements Serializable {

    public static void serializeObject(String filename, Object obj) {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(obj);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String filename) {
        Object obj = null;
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            obj = ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }

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

        System.out.println("\tBEFORE SERIALIZATION: ");

        System.out.println(bookcase.toString());
        System.out.println(reader.toString());

        reader.setName("Jenny");
        reader.setSurname("Willson");
        reader.setAge(19);
        reader.setLibrary_card_number(48737709);
        System.out.println(reader.toString());

        reader.pickBook(b3);
        reader.ShowActiveBooks();

        //serialization
        serializeObject("Bookcase1.dat", bookcase);

        //deserialization
        Bookcase bookcase1 = (Bookcase) deSerializeObject("Bookcase1.dat");
        System.out.println("\tAFTER SERIALIZATION: ");
        System.out.println(bookcase1.toString());
    }
}
