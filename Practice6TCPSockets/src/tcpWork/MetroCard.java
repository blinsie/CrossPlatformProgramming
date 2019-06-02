package tcpWork;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private User user;
    private String idCard;
    private  String colledge;
    private double balance;

    public MetroCard() {
        this.user = new User();
        this.idCard = "";
        this.colledge = "";
        this.balance = 0.0;
    }

    public MetroCard(User user, String idCard, String colledge, double balance) {
        this.user = user;
        this.idCard = idCard;
        this.colledge = colledge;
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getColledge() {
        return colledge;
    }

    public void setColledge(String colledge) {
        this.colledge = colledge;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "#  " + getIdCard() +  " --> " + user.getUser()
                + " Colledge: " + getColledge() + ", Balance: "
                + getBalance() + ".";
    }
}
