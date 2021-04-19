package uk.ac.sheffield.assignment2021;

import uk.ac.sheffield.assignment2021.codeprovided.AbstractQueryParser;
import uk.ac.sheffield.assignment2021.codeprovided.Query;
import uk.ac.sheffield.assignment2021.codeprovided.SubQuery;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;

import java.util.ArrayList;
import java.util.List;

public class QueryParser extends AbstractQueryParser {
    @Override
    public List<Query> readQueries(List<String> queryTokens) throws IllegalArgumentException {
        // TODO implement
    	List<Query> query = new ArrayList<>();
    	List<SubQuery> subQueryList = new ArrayList<>();
       
    	WineProperty wineProperty = null;
        String operator = null;
        Double value = null;
    	
        for(int i=queryTokens.indexOf("where")+1;i<queryTokens.size();i++) {
    		if(SubQuery.isValidOperator(queryTokens.get(i))) {
    			wineProperty = WineProperty.fromFileIdentifier(queryTokens.get(i-1));
    			operator = queryTokens.get(i);
    			value = Double.valueOf(queryTokens.get(i+1));
    			subQueryList.add(new SubQuery(wineProperty,operator,value));
    		}	
    	}
    	
        WineType wineType = null;
    	if(queryTokens.contains("red") && queryTokens.contains("white")) {
    		wineType = WineType.ALL;
    		query.add(new Query(subQueryList,wineType));
    	}else if(queryTokens.contains("red") && !queryTokens.contains("white")) {
    		wineType = WineType.RED;
    		query.add(new Query(subQueryList,wineType));
    	}else if(queryTokens.contains("white") && !queryTokens.contains("red")){
    		wineType = WineType.WHITE;
    		query.add(new Query(subQueryList,wineType));
    	}
    	
    	if(wineProperty == null||operator == null||value == null||wineType == null) {
    		throw new IllegalArgumentException("Entered the wrong sentence!");
    	}
    	return query;
    }
}
