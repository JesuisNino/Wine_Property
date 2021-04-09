package uk.ac.sheffield.assignment2021;

import uk.ac.sheffield.assignment2021.codeprovided.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class WineSampleCellar extends AbstractWineSampleCellar {
    /**
     * Constructor - reads wine sample datasets and list of queries from text file,
     * and initialises the wineSampleRacks Map
     *
     * @param redWineFilename
     * @param whiteWineFilename
     */
    public WineSampleCellar(String redWineFilename, String whiteWineFilename) {
        super(redWineFilename, whiteWineFilename);
    }

    @Override
    public WinePropertyMap parseWineFileLine(String line) throws IllegalArgumentException {
        // TODO implement  
        WinePropertyMap map = new WinePropertyMap();
    	String[] split = line.split(";");
        WineProperty[] property = WineProperty.values();
        if(split.length != property.length) {
        	throw new IllegalArgumentException("Parse wine file line is too few or too much!");
        }else {
        for(int i=0;i< split.length;i++) {
    			map.put(property[i],Double.valueOf(split[i].toString()));
        	}
        }
    	return map;     	
    }

    @Override
    public void updateCellar() {
        // TODO delete next line and implement
        wineSampleRacks.put(WineType.ALL, new ArrayList<>());
    }

    @Override
    public double getMinimumValue(WineProperty wineProperty, List<WineSample> wineList)
            throws NoSuchElementException {
        // TODO implement
        return -1;
    }

    @Override
    public double getMaximumValue(WineProperty wineProperty, List<WineSample> wineList)
            throws NoSuchElementException {
        // TODO implement
        return -1;
    }

    @Override
    public double getMeanAverageValue(WineProperty wineProperty, List<WineSample> wineList)
            throws NoSuchElementException {
        // TODO implement
        return -1;
    }

    @Override
    public List<WineSample> getFirstFiveWines(WineType type) {
        // TODO implement
        return new ArrayList<>();
    }
}
