package Task3;

import java.io.*;

public class Rent implements Externalizable {

    private static final long serialVersionUID = 1L;
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.getTime());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.setTime((int)in.readObject());
    }

//    WORKS
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Rent rr = new Rent(10);
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Rent.dat"));
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Rent.dat"));
//
//        rr.writeExternal(oos);
//        Rent r = new Rent();
//        r.readExternal(ois);
//
//        System.out.println(r.toString());
//    }
}
