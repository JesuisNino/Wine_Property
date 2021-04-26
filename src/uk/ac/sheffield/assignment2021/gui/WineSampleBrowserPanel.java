package uk.ac.sheffield.assignment2021.gui;

import java.util.ArrayList;
import java.util.List;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractWineSampleCellar;
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
    	});
    	buttonClearFilters.addActionListener(e -> {
    		clearFilters();
    	});
    }

    @Override
    public void addFilter() {
        // TODO implement
    	WineProperty wineProperty = WineProperty.fromName((String)comboQueryProperties.getSelectedItem());
    	String operator = (String)comboOperators.getSelectedItem();
    	Double values = Double.valueOf(value.getText());
    	
    	subQueryList.add(new SubQuery(wineProperty,operator,values));
    	String subQueriesText = subQueryList.toString().replace("[","").replace("]","").replace(",", ";");
    	
    	subQueriesTextArea.setText(subQueriesText);
    	statisticsTextArea.setText(operator);
    	filteredWineSamplesTextArea.setText(filteredWineSampleList.toString());
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
//    	 for (SubQuery subQuery : subQueryList)
//         {
//    		 for (int i=0;i<cellar.getWineSampleCount(WineType.ALL);i++) {
//    	            if(subQuery.wineMatchesSubQuery(filteredWineSampleList.get(i)))
//    	            	filteredWineSampleList.remove(i);
//    	            }
//
//                
//    	}
       
    }
}
