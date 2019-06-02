package tcpWork;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String surName;
    private String sex;
    private String birthday;

    public User() {
        this.name = "";
        this.surName = "";
        this.sex = "";
        this.birthday = null;
    }

    public User(String name, String surName, String sex, String birthday) {
        this.name = name;
        this.surName = surName;
        this.sex = sex;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return name + " " + surName + " " + sex +
                " " + birthday;
    }

    public String getUser() {
        return "// " + getName() + " " + getSurName() + " //";
    }
}
