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
public class Delete extends Statement {
    
    private final HashMap<String, String> condition;
    private final boolean exact;

    public Delete(HashMap<String, String> condition, boolean exact) {
        this.condition = condition;
        this.exact = exact;
    }

    @Override
    public String getStatement() {
    String statement = "DELETE FROM "
                .concat(getTableName())
                .concat(" WHERE " + getConditionString());
        
        return statement;
    }
    
    private String getConditionString(){
        String conditionStr = "";
        
        for (Map.Entry<String, String> en : this.condition.entrySet()) {
            if(!conditionStr.equals("")) conditionStr += " AND ";
            conditionStr += en.getKey()
                    .concat((this.exact)?" = ":" LIKE ")
                    .concat("'" + en.getValue() + "'");
        }
        
        return conditionStr;
    }
}
