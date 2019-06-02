package tcpServer;

import tcpServer.operations.*;
import tcpWork.MetroCardBank;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler extends Thread {
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private boolean work = true;
    private MetroCardBank mcb = null;
    private Socket s = null;

    public ClientHandler(MetroCardBank mcb, Socket s) {
        this.mcb = mcb;
        this.s = s;
        this.work = true;
        try {
            this.is = new ObjectInputStream(s.getInputStream());
            this.os = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException e) {
            System.out.println("111Error: " + e);
        }
    }

    private void processOperation(Object obj)
            throws IOException, ClassNotFoundException {
        if (obj instanceof StopOperation) { finish();}
        else if (obj instanceof AddMetroCardOperation) { addCard(obj); }
        else if (obj instanceof AddMoneyOperation) { addMoney(obj);}
        else if (obj instanceof PayMoneyOperation) { payMoney(obj);}
        else if (obj instanceof RemoveCardOperation) { removeCard(obj);}
        else if (obj instanceof ShowBalanceOperation) { showBalance(obj); }
        else if (obj instanceof ShowCardsOperation) { showCards(); }
        else { error(); }
    }

    private void finish() throws IOException {
        work = false;
        os.writeObject("Finish Work " + s);
        mcb = null;
        os.flush();
    }

    private void addCard(Object obj)
            throws IOException, ClassNotFoundException {
        mcb.addCard(((AddMetroCardOperation) obj).getCrd());
        os.writeObject("Card Added " + ((AddMetroCardOperation) obj).getSerNum());
        os.flush();
    }

    private void addMoney(Object obj)
            throws IOException, ClassNotFoundException {
        AddMoneyOperation op = (AddMoneyOperation) obj;
        boolean res = mcb.addMoney(op.getSerNum(), op.getMoney());
        System.out.println(op.getSerNum() + " // " + op.getMoney());
        if (res) {
            os.writeObject("Balance Added");
            os.flush();
        } else {
            os.writeObject("Cannot Balance Added");
            os.flush();
        }
    }

    private void payMoney(Object obj)
            throws IOException, ClassNotFoundException {
        PayMoneyOperation op = (PayMoneyOperation) obj;
        boolean res = mcb.getMoney(op.getSerNum(), op.getMoney());
        if (res) {
            os.writeObject("Money Payed");
            os.flush();
        } else {
            os.writeObject("Cannot Pay Money");
            os.flush();
        }
    }

    private void removeCard(Object obj)
            throws IOException, ClassNotFoundException {
        RemoveCardOperation op = (RemoveCardOperation) obj;
        boolean res = mcb.removeCard(op.getSerNum());
        if (res) {
            os.writeObject("Metro Card Succesfully Remove: " + op.getSerNum());
            os.flush();
        } else {
            os.writeObject("Cannot Remove Card" + op.getSerNum());
            os.flush();
        }
    }

    private void showBalance(Object obj)
            throws IOException, ClassNotFoundException {
        ShowBalanceOperation op = (ShowBalanceOperation) obj;
        int ind = mcb.findMetroCard(op.getSerNum());
        if(ind >= 0) {
            os.writeObject("Card: " + op.getSerNum() + " Balance: " +
                    mcb.getStore().get(ind).getBalance());
            os.flush();
        } else {
            os.writeObject("Cannot Show Balance for Card: " +
                    op.getSerNum());
            os.flush();
        }
    }

    private void showCards() {
        if (mcb != null) {
            try {
                os.writeObject(mcb.toString());
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                os.writeObject("There is no cards yet!");
                os.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void error()
            throws IOException {
        os.writeObject("Bad Operation");
        os.flush();
    }

    @Override
    public void run() {
        synchronized (mcb) {
            work = true;
            System.out.println("Client Handler Started for: " + s);
            while(work) {
              Object obj;
              try {
                  obj = is.readObject();
                  processOperation(obj);
              } catch (IOException e) {
                  System.out.println("!Error: " + e);
                  work = false;
              } catch (ClassNotFoundException ex) {
                  System.out.println("!!Error: " + ex);
              }
            }
            try {
                System.out.println("Client Handler Stopped for: " + s);
                s.close();
            } catch (IOException ex) {
                System.out.println("!!!Error: " + ex);
            }
        }
    }

}
