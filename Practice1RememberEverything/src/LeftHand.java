
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;
import java.io.IOException;


public class LeftHand implements Evaluatable {

    private double a;

    public LeftHand(double a) {
        this.a = a;
    }

    public LeftHand() {
        this(0);
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    @Override
    public double evalf(double x) {
// TODO Auto-generated method stub
        return 1.0 / Math.pow(Math.cosh(x), 2) - a * x;
    }

    public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub

        LeftHand fun = new LeftHand();
        final XYSeries series1 = new XYSeries("Value a", false, true);

        java.util.Scanner in = new java.util.Scanner(System.in);
        System.out.print("a: ");
        double a = in.nextDouble();
        fun.setA(a);
        System.out.print("xBeg: ");
        double xBeg = in.nextDouble();
        System.out.print("xEnd: ");
        double xEnd = in.nextDouble();
        System.out.print("xStep: ");
        double xStep = in.nextDouble();

        System.out.println("Параметр a: " + fun.getA());
        java.io.PrintWriter out = new java.io.PrintWriter(
                new java.io.FileWriter("LeftHand_A=" + a + ".dat"));
        for (double x = xBeg; x <= xEnd; x += xStep) {
            System.out.printf("x: %6.4f\tf: %6.4f\n", x, fun.evalf(x));
            series1.add( x, fun.evalf(x) );
        }

        final XYSeriesCollection data = new XYSeriesCollection();
        data.addSeries(series1);

        ChartFrame demo1 = new ChartFrame("JFreeChart: FFunction", data);
        demo1.pack();
        // И показываем
        RefineryUtilities.centerFrameOnScreen(demo1);
        demo1.setVisible(true);

        out.close();
    }
}

//class ChartFrame extends ApplicationFrame {
//
//    public ChartFrame(String title, XYSeriesCollection dataSeries) {
//        super(title);
//        JFreeChart chart = ChartFactory.createXYLineChart(
//                "XY Chart",
//                "X",
//                "Y",
//                dataSeries,
//                PlotOrientation.VERTICAL,
//                true,
//                true,
//                false
//        );
//        ChartPanel chartPanel = new ChartPanel(chart);
//        // С размерами 450*450
//        chartPanel.setPreferredSize(new Dimension(450, 450));
//        // И ползунками если необходимо
//        JScrollPane sp = new JScrollPane(chartPanel);
//        sp.setPreferredSize(new Dimension(500, 500));
//        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        setContentPane(sp);
//    }

//}
