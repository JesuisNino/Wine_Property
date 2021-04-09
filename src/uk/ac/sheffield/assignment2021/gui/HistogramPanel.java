package uk.ac.sheffield.assignment2021.gui;

import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogramPanel;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractWineSampleBrowserPanel;

public class HistogramPanel extends AbstractHistogramPanel
{
    public HistogramPanel(AbstractWineSampleBrowserPanel parentPanel, AbstractHistogram histogram)
    {
        super(parentPanel, histogram);
    }


    /* NOTE: your HistogramPanel must override JPanel's `protected void paintComponent(Graphics g)`,
    in order to redraw your Histogram whenever it is updated.
    For example:

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension d = getSize();
        Graphics2D g2 = (Graphics2D) g;

        Line2D l = new Line2D.Double(
            0,
            0,
            d.width,
            d.height
        );
        g2.draw(l);
    }

     */
}
