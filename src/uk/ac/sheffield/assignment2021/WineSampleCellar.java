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
		// Parse the properties from a given line from a wine file
		WinePropertyMap map = new WinePropertyMap();
		String[] split = line.split(";");
		WineProperty[] property = WineProperty.values();

		// Check if the entered line has correct length
		if (split.length != property.length) {
			throw new IllegalArgumentException("Parse wine file line is too few or too much!");
		} else {
			// Add elements into the map
			for (int i = 0; i < split.length; i++) {
				map.put(property[i], Double.valueOf(split[i].toString()));
			}
		}

		return map;
	}

	@Override
	public void updateCellar() {
		// Update wineSampleRacks and add a "ALL" wine type
		List<WineSample> racks = new ArrayList<>();
		racks.addAll(wineSampleRacks.get(WineType.RED));
		racks.addAll(wineSampleRacks.get(WineType.WHITE));
		wineSampleRacks.put(WineType.ALL, racks);
	}

	@Override
	public double getMinimumValue(WineProperty wineProperty, List<WineSample> wineList) throws NoSuchElementException {
		// Get the minimum value of the given property for the specified wines
		double value = 0.0;

		if (wineList.size() > 0) {
			value = wineList.get(0).getProperty(wineProperty);
			for (int i = 1; i < wineList.size(); i++) {
				if (value > wineList.get(i).getProperty(wineProperty)) {
					value = wineList.get(i).getProperty(wineProperty);
				}
			}
		}

		return value;
	}

	@Override
	public double getMaximumValue(WineProperty wineProperty, List<WineSample> wineList) throws NoSuchElementException {
		// Get the maximum value of the given property for the specified wines
		double value = 0;

		if (wineList.size() > 0) {
			for (int i = 0; i < wineList.size(); i++) {
				if (value < wineList.get(i).getProperty(wineProperty)) {
					value = wineList.get(i).getProperty(wineProperty);
				}
			}
		}

		return value;
	}

	@Override
	public double getMeanAverageValue(WineProperty wineProperty, List<WineSample> wineList)
			throws NoSuchElementException {
		// Get the mean value of the given property for the specified wines
		double sum = 0;

		if (wineList.size() > 0) {
			for (int i = 0; i < wineList.size(); i++) {
				sum += wineList.get(i).getProperty(wineProperty);
			}
		}

		// Calculate the men value
		double avg = sum / wineList.size();

		return avg;
	}

	@Override
	public List<WineSample> getFirstFiveWines(WineType type) {
		// Get the first 5 wines of the given wine type
		List<WineSample> list = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			list.add(wineSampleRacks.get(type).get(i));
		}

		return list;
	}
}
