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
public class Update extends Statement{
    
    private final HashMap<String, String> values;
    private final boolean allText;
    private final HashMap<String, String> condition;
    private final boolean exact;

    public Update(HashMap<String, String> values, boolean allText, HashMap<String, String> condition, boolean exact) {
        this.values = values;
        this.allText = allText;
        this.condition = condition;
        this.exact = exact;
    }

    public Update(HashMap<String, String> values, HashMap<String, String> condition) {
        this.values = values;
        this.condition = condition;
        this.allText = false;
        this.exact = true;
    }
    
    @Override
    public String getStatement() {
        String statement = "UPDATE "
                .concat(getTableName())
                .concat(" SET " + getConditionString(this.values, true))
                .concat(" WHERE " + getConditionString(this.condition, false));
        
        return statement;
    }
    
    private String getConditionString(HashMap<String, String> values, boolean sets){
        String text = "";
        String union = sets?", ":" AND ";
        
        for (Map.Entry<String, String> en : values.entrySet()) {
            if(!text.equals("")) text += union;
            text += en.getKey()
                    .concat(this.exact || sets?" = ":" LIKE ")
                    .concat(allText?"'" + en.getValue() + "'":en.getValue());
            }
        
        return text;
    }
}
