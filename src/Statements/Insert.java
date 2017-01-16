/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author juandavid
 */
public class Insert extends Statement {
    
    private String[][] values;
    private String[] columns;
    private final boolean allText;

    public Insert(String[] columns, String[][] values, boolean allText) {
        if(columns.length != values[0].length)
            throw new IllegalArgumentException("In Insert Statement, number of columns do not match");
        
        this.columns = columns;
        this.values = values;
        this.allText = allText;
    }
    
    public Insert(HashMap<String, String> values, boolean allText) {
        int size = values.keySet().size();
        
        this.columns = new String[size];
        this.values = new String[1][size];
        this.allText = allText;
        
        int i = 0;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            this.columns[i] = entry.getKey();
            this.values[0][i++] = entry.getValue();
        }
    }
    
    @Override
    public String getStatement() {
        String statement = "INSERT INTO ";
        statement += getTableName()
                + arrayToString(this.columns, false)
                +" VALUES"+setValues(allText);
                
        return statement;
    }
    
    private String arrayToString(String[] array, boolean allText){
        String statement = "";
        
        for (String value : array) {
            if(!statement.equals("")) statement += ", ";
            if(allText) value = "'" + value + "'";
            
            statement += value;
        }
        
        return "(" + statement + ")";
    }
    
    private String setValues(boolean allText){
        String statement = "";
        
        for (String[] value : this.values) {
            if(!statement.equals("")) statement += ", ";
            statement += arrayToString(value, allText);
        }
        
        return statement;
    }
}
