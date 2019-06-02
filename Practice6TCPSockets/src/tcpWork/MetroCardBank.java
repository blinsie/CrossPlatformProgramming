package tcpWork;

import java.io.Serializable;
import java.util.ArrayList;

public class MetroCardBank implements Serializable {
    ArrayList<MetroCard> store;

    public MetroCardBank() {
        this.store = new ArrayList<>();
    }

    public ArrayList<MetroCard> getStore() {
        return store;
    }

    public void setStore(ArrayList<MetroCard> store) {
        this.store = store;
    }

    //finding metroCard by serial number(idCard)
    // returns id in array "store"
    public int findMetroCard(String idCard) {
        for (MetroCard c : store)
            if (c.getIdCard().equals(idCard))
                return store.indexOf(c);
        return -1;
    }

    //returns amount of registered cards
    public int numCards() {
        return store.size();
    }

    //adding a new card to array "store"
    public void addCard(MetroCard newCard) {
        store.add(newCard);
    }

    //removing a card from array "store"
    //returns true if card was deleted
    //returns false if card wasn't deleted or store didn't contain card
    public boolean removeCard(String idCard) {
        if (store != null) {
            for (MetroCard c : store)
                if (c.getIdCard().equals(idCard)) {
                    store.remove(c);
                    return true;
                }
            return false;
        }
        else return false;
    }

    //refresh the current balance with the new money
    //returns true if card balance changed
    //returns false if card doesn't exists in array
    public boolean addMoney(String idCard, double money) {
        if (store != null) {
            for (MetroCard c : store) {
                System.out.println(c.getIdCard());
                if (c.getIdCard().equals(idCard)) {
                    c.setBalance(c.getBalance() + money);
                    return true;
                }
            }
            return false;
        }
        else return false;
    }

    //method of payment taken for transit
    //returns true if card was delete
    //returns false if card exists in array
    public boolean getMoney(String idCard, double money) {
        if (store != null) {
            for (MetroCard c : store) {
                if (c.getIdCard().equals(idCard)) {
                    c.setBalance(c.getBalance() - money);
                    return true;
                }
            }
            return false;
        }
        else return false;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards: ");
        for (MetroCard c : store) {
            buf.append("\n" + c.toString());
        }
        return buf.toString();
    }
}
