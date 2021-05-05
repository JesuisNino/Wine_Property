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
		super.paintComponent(g);
		Dimension d = getSize();
		Graphics2D g2 = (Graphics2D) g;

		Map<HistogramBin, Integer> countsPerBin = getHistogram().getWineCountsPerBin();
		List<HistogramBin> binList = getHistogram().getBinsInBoundaryOrder();
//		System.out.println(binList.size());

		int maxCount = getHistogram().largestBinCount();
		for (int i = 0; i < 5; i++) {
			Line2D y = new Line2D.Double(80, i * 90 + 5, d.width, i * 90 + 5);
			g2.draw(y);
			g2.drawString(String.valueOf(i * maxCount / 4), 50, (4 - i) * 90 + 10);
		}

		String[] yLabel = { "F", "r", "e", "q", "u", "e", "n", "c", "y" };
		for (int i = 0; i < yLabel.length; i++) {
			Font f = new Font("SansSerif", 0, 20);
			g2.setFont(f);
			g2.drawString(yLabel[i], 20, 80 + 30 * i);
		}

		int binWidth = (d.width - 80) / 11;
		for (int j = 0; j < binList.size(); j++) {
			g2.setPaint(Color.GRAY);
			g2.fillRect(80 + j * binWidth, d.height - d.height * countsPerBin.get(binList.get(j)) / maxCount, binWidth,
					d.height * countsPerBin.get(binList.get(j)) / maxCount);
			g2.setPaint(Color.BLACK);
			g2.drawString("", 80 + j * binWidth / 2, j);
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
