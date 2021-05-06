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
        // Initialize the lists
    	List<Query> queries = new ArrayList<>();
    	List<SubQuery> subQueryList = new ArrayList<>();
    	List<Integer> countSelect = new ArrayList<>();
    	List<Integer> countWhere = new ArrayList<>();
    	
    	// Initialize the variables
    	WineProperty wineProperty = null;
        String operator = null;
        Double value = null;
        WineType type = null;
        
        // Locate keywords in the input sentence
        for(int i=0;i<queryTokens.size();i++) {
        	if(queryTokens.get(i).equals("select"))countSelect.add(i);
        	if(queryTokens.get(i).equals("where"))countWhere.add(i);
        }
        
        // Parse input sentence into queries
        int index = 0;
       
        for(int i=0;i<queryTokens.size();i++) {           
            // Confirm the type of wine required by the input sentence  	        	
        	if(queryTokens.get(i).equalsIgnoreCase("red") && type != WineType.WHITE) {
        		type = WineType.RED;       		 	
        	}else if(queryTokens.get(i).equalsIgnoreCase("white") && type != WineType.RED){
        		type = WineType.WHITE;
        	}else if(queryTokens.get(i).equalsIgnoreCase("red") && type == WineType.WHITE) {
        		type = WineType.ALL;
        	}else if(queryTokens.get(i).equalsIgnoreCase("white") && type == WineType.RED) {
        		type = WineType.ALL;
        	}
        	
            // Confirm the subqueries required by the input sentence		
        	if(SubQuery.isValidOperator(queryTokens.get(i))) {            			
        		wineProperty = WineProperty.fromFileIdentifier(queryTokens.get(i-1));   			
        		operator = queryTokens.get(i);          			
        		value = Double.valueOf(queryTokens.get(i+1));		
        		subQueryList.add(new SubQuery(wineProperty,operator,value));            	
        	}
        	             	
            // Confirm whether multiple sentences are entered, and need to be parsed into multiple queries 
        	if(countSelect.size()!=1 && i==countSelect.get(index+1) || i==queryTokens.size()-1) {
        		// If entered wrong sentence, code will throw new
        		if(wineProperty == null||operator == null||value == null||type == null) {
            		throw new IllegalArgumentException("Entered the wrong sentence!");
            	}
        		queries.add(new Query(subQueryList,type));	
        		subQueryList = new ArrayList<>();
        		type = null;
        		while(index < countSelect.size()-2)index++;
        	}
        	
        }
      	
    	return queries;
    }
}
