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
public abstract class Statement {
    
    private String table;
    
    public abstract String getStatement();
    
    public void setTable(String newTable){
        this.table = newTable;
    }
    
    public String getTableName(){
        return this.table;
    }
}
