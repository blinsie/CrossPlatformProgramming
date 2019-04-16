package Task2;

import java.io.*;

public class Author {

    private transient String name;
    private transient String surname;
    private transient int age;
    private transient String sex;
    private transient String country;


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

    void writeAuthorObj(ObjectOutputStream out) throws IOException{
        out.writeObject(this.getName());
        out.writeObject(this.getSurname());
        out.writeObject(this.getAge());
        out.writeObject(this.getSex());
        out.writeObject(this.getCountry());
    }

    void readAuthorObj(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.setName((String) in.readObject());
        this.setSurname((String) in.readObject());
        this.age = (int) in.readObject();
        this.sex = (String) in.readObject();
        this.country = (String) in.readObject();
    }

}
