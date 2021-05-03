package uk.ac.sheffield.assignment2021.gui;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineSample;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractHistogram;
import uk.ac.sheffield.assignment2021.codeprovided.gui.HistogramBin;

import java.util.List;
import java.util.NoSuchElementException;

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
        // TODO implement
    	double min=cellar.getMinimumValue(property,filteredWineSamples);
    	double max=cellar.getMaximumValue(property,filteredWineSamples);
    	double interval=(max-min)/NUMBER_BINS;	
    	int binCount=1;
    	boolean finalBin=false;
    	
    	while(binCount<=NUMBER_BINS) {
    		
    		if(binCount==NUMBER_BINS) {
    			finalBin=true;
    		}
    		
    		HistogramBin bin=new HistogramBin(min,min+(binCount*interval),finalBin);
    		int count=0;   		
    		for(int i=0;i<filteredWineSamples.size();i++) {
    			if(bin.valueInBin(filteredWineSamples.get(i).getProperty(property))){
    			wineCountsPerBin.put(bin, count);
    			count++;
    			}	
    		}
    		binCount++;
    	}
    		
        
    }

	@Override
	public double getAveragePropertyValue() throws NoSuchElementException {
		// TODO implement
		return cellar.getMeanAverageValue(property, filteredWineSamples);
	}
}
