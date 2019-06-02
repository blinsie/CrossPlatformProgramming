import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class DerivativeApplication {

    public static void main(String[] args) throws IOException {
        Evaluatable functs[] = new Evaluatable[3];
        functs[0] = new FFunction(0.5);
        functs[1] = new SolveEqFunction();
        functs[2] = new FileListInterpolation();

        ((SolveEqFunction)functs[1]).setRootApprox(0.7);

        try {
            ((FileListInterpolation)functs[2]).readFromFile("TblFunc.dat");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        }

        String fileName = "";

        XYSeries dataset[] = new XYSeries[3];
        dataset[0] = new XYSeries("Ffunction", false, true);
        dataset[1] = new XYSeries("SolveEqFunction", false, true);
        dataset[2] = new XYSeries("FileListInterpolation", false, true);

        for (int i = 0; i < 3; i++) {
                System.out.println("Функция: " + functs[i].getClass().getSimpleName());
                fileName = functs[i].getClass().getSimpleName() + ".dat";
                PrintWriter out = new PrintWriter(new FileWriter(fileName));
                for (double x = 1.5; x <= 6.5; x += 0.05) {
                    System.out.println("x: " + x + "\tf: " + functs[i].evalf(x) + "\tf': " +
                            NumMethods.der(x, 1.0e-4, functs[i]));
                    dataset[i].add(x, functs[i].evalf(x));
                    out.printf("%16.6e%16.6e%16.6e\n", x, functs[i].evalf(x),
                            NumMethods.der(x, 1.0e-4, functs[i]));
                }
                System.out.println("\n");
                out.close();
        }

        XYSeriesCollection data1 = new XYSeriesCollection();
        data1.addSeries(dataset[0]);
        XYSeriesCollection data2 = new XYSeriesCollection();
        data2.addSeries(dataset[1]);
        XYSeriesCollection data3 = new XYSeriesCollection();
        data3.addSeries(dataset[2]);

        ChartFrame demo1 = new ChartFrame("JFreeChart: FFunction", data1);
        demo1.pack();
        RefineryUtilities.centerFrameOnScreen(demo1);
        demo1.setVisible(true);

        ChartFrame demo2 = new ChartFrame("JFreeChart: SolveEqFunction", data2);
        demo2.pack();
        RefineryUtilities.centerFrameOnScreen(demo2);
        demo2.setVisible(true);

        ChartFrame demo3 = new ChartFrame("JFreeChart: FileListInterpolation", data3);
        demo3.pack();
        RefineryUtilities.centerFrameOnScreen(demo3);
        demo3.setVisible(true);
    }

}