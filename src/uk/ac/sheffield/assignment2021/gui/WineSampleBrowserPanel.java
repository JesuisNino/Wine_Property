package uk.ac.sheffield.assignment2021.gui;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
import uk.ac.sheffield.assignment2021.codeprovided.SubQuery;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
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
    	});
    	buttonClearFilters.addActionListener(e -> {
    		clearFilters();
    	});
    	comboWineTypes.addActionListener(comboWineTypes);
    }

    @Override
    public void addFilter() {
        // TODO implement
    	WineProperty wineProperty = WineProperty.fromFileIdentifier((String)comboQueryProperties.getSelectedItem());
    	String operator = (String)comboOperators.getSelectedItem();
    	Double values = Double.valueOf(value.getText());
    	subQueryList.add(new SubQuery(wineProperty,operator,values));
    }

    @Override
    public void clearFilters() {
        // TODO implement
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
    }
}
