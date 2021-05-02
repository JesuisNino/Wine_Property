package uk.ac.sheffield.assignment2021.gui;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineSample;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogram;

import java.util.List;
import java.util.NoSuchElementException;

public class Histogram extends AbstractHistogram {
    /**
     * Constructor. Called by AbstractWineSampleBrowserPanel
     *
     * @param cellar              to allow for getting min / max / avg values
     * @param filteredWineSamples a List of WineSamples to generate a histogram for.
     *                            These have already been filtered by the GUI's queries.
     * @param property            the WineProperty to generate a histogram for.
     */
    public Histogram(AbstractWineSampleCellar cellar, List<WineSample> filteredWineSamples, WineProperty property)
    {
        super(cellar, filteredWineSamples, property);
    }

    @Override
    public void updateHistogramContents(WineProperty property, List<WineSample> filteredWineSamples) {
        // TODO implement
    	for(int i=0;i<filteredWineSamples.size();i++) {
    		if(filteredWineSamples.get(i).getProperty(property))
    	}
    }

    @Override
    public double getAveragePropertyValue() throws NoSuchElementException {
        // TODO implement
        return 0;
    }
}
