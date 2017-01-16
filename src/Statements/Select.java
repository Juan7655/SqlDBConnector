/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statements;

/**
 *
 * @author juandavid
 */
public class Select extends Statement{
    
     private final boolean distinct;
     private final String[] columns;
     private final String whereClause;
     private final String[] whereArgs;
     private final String groupBy;
     private final String having;
     private final String orderBy;
     private final String limit;

    /**
     * @param distinct    True if only different values shall be retreated. False to ignore this section.
     * @param columns     Columns to SELECT.
     * @param whereClause The sql sentence corresponding but not including the WHERE section.
     * @param whereArgs   An array of values that will be put instead of the corresponding ? symbols in the WHERE clause.
     * @param groupBy     The value corresponding to the GROUP BY clause. Null to ignore.
     * @param having      The value corresponding to the HAVING clause. Null to ignore.
     * @param orderBy     The value corresponding to the ORDER BY clause. Null to ignore.
     * @param limit       The value corresponding to the LIMIT clause. Null to ignore. Limits to a fixed number the results.
     */
    public Select(boolean distinct, String[] columns, String whereClause, String[] whereArgs,
            String groupBy, String having, String orderBy, String limit) {
        this.distinct = distinct;
        this.columns = columns;
        this.whereClause = whereClause;
        this.whereArgs = whereArgs;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
        this.limit = limit;
    }

    @Override
    public String getStatement() {
        String statement = "SELECT "
                .concat(this.distinct? "DISTINCT " : "")
                .concat(selectCol(columns))
                .concat(" FROM ")
                .concat(getTableName())
                .concat(putWhereArgs(whereClause, whereArgs))
                .concat(queringParams(groupBy, having, orderBy, limit));
    
        return statement;
    }
    
    private String selectCol(String[] columns) {
        if (columns == null)columns = new String[]{"*"};
        String select = "";
        for (String i : columns)select += (!select.equals("")?", ":"")+i;
        
        return select;
    }
    
    private String putWhereArgs(String whereClause, String[] whereArgs){
        String temp = whereClause != null? whereClause.replace("?", "") : "";
        if (whereArgs != null && whereClause != null) {
            if (whereClause.length() - temp.length() != whereArgs.length){
                throw new IllegalArgumentException("Could not match Where Arguments with '?'"
                        + " characters in the input[" 
                        + whereClause.length() + " - "
                        + temp.length() + " != " + whereArgs.length +"] " + whereArgs[0]);
            }
            for (String i : whereArgs)whereClause = whereClause.replaceFirst("\\?", i);
        }
        
        return (whereClause != null? " WHERE " + whereClause : "");
    }
    
    private String queringParams(String groupBy, String having, String orderBy, String limit) {
        String state = "";
        
        if(groupBy != null)state += " GROUP BY " + groupBy;
        if(having != null)state += " HAVING " + having;
        if(orderBy != null)state += " ORDER BY " + orderBy;
        if(limit != null)state += " LIMIT " + limit;

        return state;
    }
}
