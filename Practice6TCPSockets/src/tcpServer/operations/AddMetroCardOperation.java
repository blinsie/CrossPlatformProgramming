package tcpServer.operations;

import tcpWork.MetroCard;
import tcpWork.User;

public class AddMetroCardOperation extends CardOperation {
    //send to server request of making new metroCard
    private MetroCard crd = null;
    private String serNum;
    private User user;
    private String idCard;
    private  String colledge;
    private double balance;

    public AddMetroCardOperation() {
        crd = new MetroCard();
        this.user = null;
        this.idCard = "";
        this.colledge = "";
        this.balance = 0.0;
    }

    public AddMetroCardOperation(String serNum) {
        crd = new MetroCard();
        crd.setIdCard(serNum);
        this.serNum = crd.getIdCard();
    }

    public AddMetroCardOperation(MetroCard crd) {
        this.crd = crd;
        this.serNum = crd.getIdCard();
    }

    public AddMetroCardOperation(User user, String idCard, String colledge, double balance) {
        crd = new MetroCard(user, idCard, colledge, balance);
        this.user = user;
        this.idCard = idCard;
        this.colledge = colledge;
        this.balance = balance;
    }

    public MetroCard getCrd() {
        return crd;
    }

    public String getSerNum() { return serNum; }

    @Override
    public String toString() {
        if (crd != null)
            return crd.toString();
        return "No Card";
    }
}

