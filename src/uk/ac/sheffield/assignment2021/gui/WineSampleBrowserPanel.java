package uk.ac.sheffield.assignment2021.gui;

import java.util.ArrayList;
import java.util.List;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.Query;
import uk.ac.sheffield.assignment2021.codeprovided.SubQuery;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineSample;
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
		});
		buttonClearFilters.addActionListener(e -> {
			clearFilters();
		});
		comboWineTypes.addActionListener(e -> {
			executeQuery();
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
		statisticsTextArea.setText(operator);
	}

	@Override
	public void clearFilters() {
		// TODO implement
		subQueryList.clear();
		subQueriesTextArea.setText(null);
		statisticsTextArea.setText(null);
		filteredWineSamplesTextArea.setText(null);
	}

	@Override
	public void updateStatistics() {
		// TODO implement
	}

	@Override
	public void updateWineDetailsBox() {
		// TODO implement
	}

	@Override
	public void executeQuery() {
		// TODO implement
		filteredWineSamplesTextArea.setText(null);
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

		filteredWineSamplesTextArea.append("WineType\t");
		filteredWineSamplesTextArea.append("ID\t");

		for (int i = 0; i < WineProperty.values().length; i++) {
			filteredWineSamplesTextArea.append(WineProperty.values()[i].toString()+"\t");
		}

		int line = 0;

		while (line < filteredWineSampleList.size()) {
			filteredWineSamplesTextArea.append("\n");
			System.out.println(filteredWineSamplesTextArea.getCaretPosition()+"\t");
			filteredWineSamplesTextArea.append(filteredWineSampleList.get(line).getWineType().toString()+"\t");
			filteredWineSamplesTextArea.append(String.valueOf(filteredWineSampleList.get(line).getId())+"\t");
			for (int i = 0; i < WineProperty.values().length; i++) {
				filteredWineSamplesTextArea.append((String.valueOf(filteredWineSampleList.get(line).getProperty(WineProperty.values()[i])))+"\t");
			}
			line++;
		}

	}
}
