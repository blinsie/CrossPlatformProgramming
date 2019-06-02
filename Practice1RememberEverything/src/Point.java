public abstract class Point {
    private double[] coords = null; public Point(int num) {
        this.coords = new double[num];
    }

    public void setCoord(int num, double x) { coords[num-1] = x;
    }

    public double getCoord(int num) {
        return coords[num-1];
    }

    @Override
    public String toString() {
// TODO Auto-generated method stub
        String res = "(";
        for (double x : coords) { res += x + ", ";
        }
        return res.substring(0, res.length()-2) + ")";
    }

    public abstract int compareTo(Point2D pt);
}
