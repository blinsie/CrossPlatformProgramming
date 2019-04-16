package Task1;

import java.io.Serializable;

public class Rent implements Serializable {

    private int time;

    public Rent() {
        time = 0;
    }

    public Rent(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Rent time: " + this.getTime() +  "\n";
    }
}
