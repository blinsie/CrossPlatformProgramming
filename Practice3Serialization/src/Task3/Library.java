package Task3;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Library implements Externalizable {

    private static final long serialVersionUID = 1L;

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

    Bookcase bookcase = new Bookcase("fantasy", sh1, sh2, sh3);

    Reader reader = new Reader();

        System.out.println(bookcase.toString());
        System.out.println(reader.toString());

        reader.setName("Jenny");
        reader.setSurname("Willson");
        reader.setAge(19);
        reader.setLibrary_card_number(48737709);
        System.out.println(reader.toString());

        reader.pickBook(b3);
        reader.ShowActiveBooks();

    //Serialization
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Library.dat"))) {
            a1.writeExternal(oos); b1.writeExternal(oos);
            a2.writeExternal(oos); b2.writeExternal(oos);
            a3.writeExternal(oos); b3.writeExternal(oos);
            a4.writeExternal(oos); b4.writeExternal(oos);
            a5.writeExternal(oos); b5.writeExternal(oos);

            bookcase.writeExternal(oos);
            reader.writeExternal(oos);
            System.out.println("File has been written.");
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    //Deserialization
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Library.dat"))){
            Author a11 = new Author(); a11.readExternal(ois);
            Book b11 = new Book(); b11.readExternal(ois);
            Author a22 = new Author(); a22.readExternal(ois);
            Book b22 = new Book(); b22.readExternal(ois);
            Author a33 = new Author(); a33.readExternal(ois);
            Book b33 = new Book(); b33.readExternal(ois);
            Author a44 = new Author(); a44.readExternal(ois);
            Book b44 = new Book(); b44.readExternal(ois);
            Author a55 = new Author(); a55.readExternal(ois);
            Book b55 = new Book(); b55.readExternal(ois);

            Bookcase bookcase1 = new Bookcase();
            bookcase1.readExternal(ois);

            Reader reader1 = new Reader();
            reader1.readExternal(ois);

            System.out.println("\n\tCHECKING DESERIALIZATION: \n");
            System.out.println(bookcase1.toString());
            System.out.println(reader1.toString());
            reader1.ShowActiveBooks();

            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
