public class SolveEqFunction implements Evaluatable {

    private LeftHand fun;
    private double tol;
    private double rootApprox;

    public SolveEqFunction() {
        fun = new LeftHand();
        tol = 1.0e-7;
        rootApprox = 0.0;
    }

    public double getTol() {
        return tol;
    }

    public void setTol(double tol) {
        this.tol = tol;
    }

    public void setRootApprox(double rootApprox) {
        this.rootApprox = rootApprox;
    }

    public double checkRoot(double x) {
        return fun.evalf(x);
    }

    @Override
    public double evalf(double x) {
// TODO Auto-generated method stub
        fun.setA(x);
        rootApprox = NumMethods.findRoot(rootApprox, tol, fun);
        return rootApprox;
    }

    /*** @param args
    */
    public static void main(String[] args) {
    // TODO Auto-generated method stub
    SolveEqFunction fun = new SolveEqFunction();

    java.util.Scanner in = new java.util.Scanner(System.in);
    System.out.print("a:  ");
    double a = in.nextDouble();
    System.out.print("xAppr: ");
    double xAppr = in.nextDouble();
    fun.setRootApprox(xAppr);
    double res = fun.evalf(a);
    System.out.println("Корень: " + res + "\tточность: " + fun.getTol() + "\tf(root): " + fun.checkRoot(res));
    }

}