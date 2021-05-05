package uk.ac.sheffield.assignment2021.gui;

import java.util.ArrayList;
import java.util.List;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.Query;
import uk.ac.sheffield.assignment2021.codeprovided.SubQuery;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;
import uk.ac.sheffield.assignment2021.codeprovided.gui.AbstractWineSampleBrowserPanel;

public class WineSampleBrowserPanel extends AbstractWineSampleBrowserPanel {
	public WineSampleBrowserPanel(AbstractWineSampleCellar cellar) {
		super(cellar);
	}

	@Override
	public void addListeners() {
		// TODO implement
		buttonAddFilter.addActionListener(e -> {
			addFilter();
			executeQuery();
			updateStatistics();
			updateWineDetailsBox();
			updateHistogram();
			repaint();
		});
		buttonClearFilters.addActionListener(e -> {
			clearFilters();
			updateStatistics();
			updateWineDetailsBox();
			updateHistogram();
			repaint();
		});
		comboWineTypes.addActionListener(e -> {
			executeQuery();
			updateStatistics();
			updateWineDetailsBox();
			updateHistogram();
			repaint();
		});
		comboHistogramProperties.addActionListener(e -> {
			updateHistogram();
			repaint();
		});
	}

	@Override
	public void addFilter() {
		// TODO implement
		WineProperty wineProperty = WineProperty.fromName((String) comboQueryProperties.getSelectedItem());
		String operator = (String) comboOperators.getSelectedItem();
		Double values = Double.valueOf(value.getText());

		subQueryList.add(new SubQuery(wineProperty, operator, values));
		String subQueriesText = subQueryList.toString().replace("[", "").replace("]", "").replace(",", ";");

		subQueriesTextArea.setText(subQueriesText);
	}

	@Override
	public void clearFilters() {
		// TODO implement
		subQueryList.clear();
		subQueriesTextArea.setText(null);
		comboQueryProperties.setSelectedIndex(0);
		comboOperators.setSelectedIndex(0);
		comboWineTypes.setSelectedIndex(0);
	}

	@Override
	public void updateStatistics() {
		// TODO implement
		statisticsTextArea.setText(null);
		if (subQueryList.isEmpty()) {
			return;
		} else if (filteredWineSampleList.isEmpty()) {
			statisticsTextArea.append("No wines under these filters!");
		} else {
			statisticsTextArea.append("\t");
			for (int i = 0; i < WineProperty.values().length; i++) {
				statisticsTextArea.append(WineProperty.values()[i].toString() + "\t");
			}

			statisticsTextArea.append("\n");
			statisticsTextArea.append("Minimum:\t");
			for (int i = 0; i < 5; i++) {
				statisticsTextArea
						.append(cellar.getMinimumValue(WineProperty.values()[i], filteredWineSampleList) + "\t");
			}
			statisticsTextArea
					.append(cellar.getMinimumValue(WineProperty.values()[5], filteredWineSampleList) + "\t\t");
			statisticsTextArea
					.append(cellar.getMinimumValue(WineProperty.values()[6], filteredWineSampleList) + "\t\t");
			for (int i = 7; i < WineProperty.values().length; i++) {
				statisticsTextArea
						.append(cellar.getMinimumValue(WineProperty.values()[i], filteredWineSampleList) + "\t");
			}

			statisticsTextArea.append("\n");
			statisticsTextArea.append("Maximum:\t");
			for (int i = 0; i < 5; i++) {
				statisticsTextArea
						.append(cellar.getMaximumValue(WineProperty.values()[i], filteredWineSampleList) + "\t");
			}
			statisticsTextArea
					.append(cellar.getMaximumValue(WineProperty.values()[5], filteredWineSampleList) + "\t\t");
			statisticsTextArea
					.append(cellar.getMaximumValue(WineProperty.values()[6], filteredWineSampleList) + "\t\t");
			for (int i = 7; i < WineProperty.values().length; i++) {
				statisticsTextArea
						.append(cellar.getMaximumValue(WineProperty.values()[i], filteredWineSampleList) + "\t");
			}

			statisticsTextArea.append("\n");
			statisticsTextArea.append("Mean:\t");
			for (int i = 0; i < 5; i++) {
				statisticsTextArea.append(String.format("%.2f",
						cellar.getMeanAverageValue(WineProperty.values()[i], filteredWineSampleList)) + "\t");
			}
			statisticsTextArea.append(
					String.format("%.2f", cellar.getMeanAverageValue(WineProperty.values()[5], filteredWineSampleList))
							+ "\t\t");
			statisticsTextArea.append(
					String.format("%.2f", cellar.getMeanAverageValue(WineProperty.values()[6], filteredWineSampleList))
							+ "\t\t");
			for (int i = 7; i < WineProperty.values().length; i++) {
				statisticsTextArea.append(String.format("%.2f",
						cellar.getMeanAverageValue(WineProperty.values()[i], filteredWineSampleList)) + "\t");
			}

			statisticsTextArea.append("\n");
			statisticsTextArea.append("Showing " + filteredWineSampleList.size() + " out of "
					+ cellar.getNumberWineSamples(WineType.ALL) + " samples.");

		}
	}

	@Override
	public void updateWineDetailsBox() {
		// TODO implement
		filteredWineSamplesTextArea.setText(null);
		if (subQueryList.isEmpty()) {
			return;
		} else if (filteredWineSampleList.isEmpty()) {
			filteredWineSamplesTextArea.append("No wines under these filters!");
		} else {
			filteredWineSamplesTextArea.append("WineType\t");
			filteredWineSamplesTextArea.append("ID\t");

			for (int i = 0; i < WineProperty.values().length; i++) {
				filteredWineSamplesTextArea.append(WineProperty.values()[i].toString() + "\t");
			}

			int line = 0;

			while (line < filteredWineSampleList.size()) {
				filteredWineSamplesTextArea.append("\n");
				filteredWineSamplesTextArea.append(filteredWineSampleList.get(line).getWineType().toString() + "\t");
				filteredWineSamplesTextArea.append(String.valueOf(filteredWineSampleList.get(line).getId()) + "\t");
				for (int i = 0; i < 5; i++) {
					filteredWineSamplesTextArea.append(
							(String.valueOf(filteredWineSampleList.get(line).getProperty(WineProperty.values()[i])))
									+ "\t");
				}
				filteredWineSamplesTextArea
						.append((String.valueOf(filteredWineSampleList.get(line).getProperty(WineProperty.values()[5])))
								+ "\t\t");
				filteredWineSamplesTextArea
						.append((String.valueOf(filteredWineSampleList.get(line).getProperty(WineProperty.values()[6])))
								+ "\t\t");
				for (int i = 7; i < WineProperty.values().length; i++) {
					filteredWineSamplesTextArea.append(
							(String.valueOf(filteredWineSampleList.get(line).getProperty(WineProperty.values()[i])))
									+ "\t");
				}
				line++;
			}
		}
	}

	@Override
	public void executeQuery() {
		// TODO implement
		List<Query> queries = new ArrayList<>();
		switch ((String) comboWineTypes.getSelectedItem()) {
		case "RED":
			queries.add(new Query(subQueryList, WineType.RED));
			break;

		case "WHITE":
			queries.add(new Query(subQueryList, WineType.WHITE));
			break;

		case "ALL":
			queries.add(new Query(subQueryList, WineType.ALL));
			break;
		}

		for (Query query : queries) {
			filteredWineSampleList = query.executeQuery(cellar);
		}

	}
}
