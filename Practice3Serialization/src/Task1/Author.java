package Task1;

import java.io.*;

public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String surname;
    private int age;
    private String sex;
    private String country;


    public Author() {
        name = "NoName";
        surname = "NoSurname";
        age = 0;
        country = "NoCountry";
        sex = "NoSex";
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author(String name, String surname, int age, String sex, String country) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.country = country;
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
    public String getSex() {
        return sex;
    }

    public String getCountry() {
        return country;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Task1.Author: Name - " + this.getName() + ", " +
                "Surname - " + this.getSurname() + ", Age - " +
                this.getAge() + ", Sex - " + this.getSex() +
                ", Country - " + this.getCountry() + "\n";

    }

//      WORKS
//    public static void main(String[] args) {
//
//        //Serialization
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Author.dat"))) {
//            Author author1 = new Author("Stephen", "King", 71, "male", "USA");
//            Author author2 = new Author("Howard", "Lovecraft", 46, "male", "USA");
//            System.out.print(author1.toString());
//            System.out.print(author2.toString());
//
//            oos.writeObject(author1);
//            oos.writeObject(author2);
//            System.out.println("File has been written.");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //Deserialization
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Author.dat"))){
//            Author a1 = (Author) ois.readObject();
//            Author a2 = (Author) ois.readObject();
//            System.out.println("Result of deserialization: ");
//            System.out.print(a1.toString());
//            System.out.print(a2.toString());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
