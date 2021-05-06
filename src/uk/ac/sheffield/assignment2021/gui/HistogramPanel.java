package uk.ac.sheffield.assignment2021.gui;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import java.util.Map;

import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogramPanel;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractWineSampleBrowserPanel;
import uk.ac.sheffield.assignment2021.codeprovided.gui.HistogramBin;

public class HistogramPanel extends AbstractHistogramPanel {
	public HistogramPanel(AbstractWineSampleBrowserPanel parentPanel, AbstractHistogram histogram) {
		super(parentPanel, histogram);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// Initialise the graphics
		super.paintComponent(g);
		Dimension d = getSize();
		Graphics2D g2 = (Graphics2D) g;
        
		// Get the parsed data
		Map<HistogramBin, Integer> countsPerBin = getHistogram().getWineCountsPerBin();
		List<HistogramBin> binList = getHistogram().getBinsInBoundaryOrder();
		int maxCount = getHistogram().largestBinCount();

		// Draw the lines and set the y axe marks
		for (int i = 0; i < 5; i++) {
			Line2D y = new Line2D.Double(80, i * 90 + 5, d.width, i * 90 + 5);
			g2.draw(y);
			g2.drawString(String.valueOf(i * maxCount / 4), 50, (4 - i) * 90 + 10);
		}
        
		// Set y axe label
		String[] yLabel = { "F", "r", "e", "q", "u", "e", "n", "c", "y" };
		for (int i = 0; i < yLabel.length; i++) {
			Font f = new Font("SansSerif", 0, 20);
			g2.setFont(f);
			g2.setPaint(Color.BLACK);
			g2.drawString(yLabel[i], 20, 80 + 30 * i);
		}
        
		// Draw bins and x axe marks with the parsed data 
		int binWidth = (d.width - 80) / 11;
		for (int j = 0; j < binList.size(); j++) {
			g2.setPaint(Color.GRAY);
			g2.fillRect(80 + j * binWidth, 365 - 360 * countsPerBin.get(binList.get(j)) / maxCount, binWidth,
					360 * countsPerBin.get(binList.get(j)) / maxCount);
			g2.setPaint(Color.BLACK);
			g2.drawString(String.format("%.4f", binList.get(j).getLowerBoundary()), 60 + j * binWidth, 390);
			if (j == binList.size() - 1)
				g2.drawString(String.format("%.4f", binList.get(j).getUpperBoundary()), 20 + binList.size() * binWidth,
						390);
		}

	}

	/*
	 * NOTE: your HistogramPanel must override JPanel's `protected void
	 * paintComponent(Graphics g)`, in order to redraw your Histogram whenever it is
	 * updated. For example:
	 * 
	 * 
	 */
}
