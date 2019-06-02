import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
public class ChartFrame extends ApplicationFrame {

    public ChartFrame(String title, XYSeriesCollection dataSeries) {
        super(title);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Chart",
                "X",
                "Y",
                dataSeries,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel(chart);
        // С размерами 450*450
        chartPanel.setPreferredSize(new Dimension(450, 450));
        // И ползунками если необходимо
        JScrollPane sp = new JScrollPane(chartPanel);
        sp.setPreferredSize(new Dimension(500, 500));
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        final XYPlot plot = chart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.GREEN);
//        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
//        renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
//        renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer );
        setContentPane(sp);

    }

}
