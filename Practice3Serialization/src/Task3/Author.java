package Task3;

import java.io.*;

public class Author implements Externalizable {

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getName());
        out.writeObject(this.getSurname());
        out.writeObject(this.getAge());
        out.writeObject(this.getSex());
        out.writeObject(this.getCountry());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setName((String) in.readObject());
        this.setSurname((String) in.readObject());
        this.setAge((int) in.readObject());
        this.setSex((String) in.readObject());
        this.setCountry((String) in.readObject());
    }


}
