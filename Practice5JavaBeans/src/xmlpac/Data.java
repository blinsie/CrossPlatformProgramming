package xmlpac;

public class Data {

    private String date;
    private double x;
    private double y;

    public Data() {
        this.date = "";
        this.x = 0;
        this.y = 0;
    }
    public Data(double x, double y) {
        this.date = "";
        this.x = x;
        this.y = y;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "(" + this.getDate() + " --> " + this.getX()
                + ", " + this.getY() + ")";
    }
}
