package uk.ac.sheffield.assignment2021.gui;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineSample;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.assignment2021.codeprovided.gui.HistogramBin;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class Histogram extends AbstractHistogram {
	/**
	 * Constructor. Called by AbstractWineSampleBrowserPanel
	 *
	 * @param cellar              to allow for getting min / max / avg values
	 * @param filteredWineSamples a List of WineSamples to generate a histogram for.
	 *                            These have already been filtered by the GUI's
	 *                            queries.
	 * @param property            the WineProperty to generate a histogram for.
	 */
	public Histogram(AbstractWineSampleCellar cellar, List<WineSample> filteredWineSamples, WineProperty property) {
		super(cellar, filteredWineSamples, property);
	}

	@Override
	public void updateHistogramContents(WineProperty property, List<WineSample> filteredWineSamples) {
		// Initialise the variables
		double min = cellar.getMinimumValue(property, filteredWineSamples);
		double max = cellar.getMaximumValue(property, filteredWineSamples);
		double interval = (max - min) / NUMBER_BINS;
		int binCount = 1;
		int binIndex = 0;
		boolean finalBin = false;
		wineCountsPerBin = new TreeMap<>();
		List<HistogramBin> binList = new ArrayList<>();
		
		// Only parse data when there are wines that meet the requirements
		if (!filteredWineSamples.isEmpty()) {
			// Set all bins have a proper size
			while (binCount <= NUMBER_BINS) {
				if (binCount == NUMBER_BINS)
					finalBin = true;
				binList.add(new HistogramBin(min + (binCount - 1) * interval, min + (binCount * interval), finalBin));
				binCount++;
			}
            
			//Count wines that meet the requirements for all bins
			while (binIndex < NUMBER_BINS) {
				int count = 0;
				for (int i = 0; i < filteredWineSamples.size(); i++) {
					if (binList.get(binIndex).valueInBin(filteredWineSamples.get(i).getProperty(property))) {
						count++;
					} else {
						continue;
					}
				}
				wineCountsPerBin.put(binList.get(binIndex), count);
				binIndex++;
			}
		}
	}

	@Override
	public double getAveragePropertyValue() throws NoSuchElementException {
		// Get mean average value from wine samples
		return cellar.getMeanAverageValue(property, filteredWineSamples);
	}
}
