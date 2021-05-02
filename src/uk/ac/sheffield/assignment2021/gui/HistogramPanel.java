package uk.ac.sheffield.assignment2021.gui;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Map;

import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogramPanel;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractWineSampleBrowserPanel;
import uk.ac.sheffield.assignment2021.codeprovided.gui.HistogramBin;

public class HistogramPanel extends AbstractHistogramPanel
{
    public HistogramPanel(AbstractWineSampleBrowserPanel parentPanel, AbstractHistogram histogram)
    {
        super(parentPanel, histogram);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = getSize();
        Graphics2D g2 = (Graphics2D) g;

        Map<HistogramBin, Integer> countsPerBin=getHistogram().getWineCountsPerBin();
        List<HistogramBin> binList=getHistogram().getBinsInBoundaryOrder();
        int maxCount=getHistogram().largestBinCount();
        for(int i=0;i<4;i++) {
        	Line2D y = new Line2D.Double(
                    d.getWidth()/100,
                    i*(d.height-2)/4,
                    d.width,
                    i*(d.height-2)/4
                );
            g2.draw(y);
        }

        for(int j=0;j<binList.size();j++) {
        	g2.drawRect(j*d.width/binList.size(), d.height-d.height*countsPerBin.get(binList.get(j))/maxCount, d.width/binList.size(), d.height*countsPerBin.get(binList.get(j))/maxCount);
        }
        	
        Line2D x = new Line2D.Double(
        		0,
        		d.height-2,
                d.width,
                d.height-2
            );
        g2.draw(x);
        
    }

    /* NOTE: your HistogramPanel must override JPanel's `protected void paintComponent(Graphics g)`,
    in order to redraw your Histogram whenever it is updated.
    For example:


     */
}
