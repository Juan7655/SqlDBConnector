/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqldbconnect;

import Statements.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author juandavid
 */
abstract class Database {

    private final ArrayList<Statement> sqlStatements = new ArrayList<>();
    private final DbConnection connection;
    private ResultSet lastQuery;

    public Database(String database, String username, String password, String url, TipoDB tipoDb) {
        this.connection = new DbConnection(database, username, password, url, tipoDb);
    }

    protected Object execute(int id) {
        Statement statement = this.sqlStatements.get(id);
        Object result;

        try {
            if (statement instanceof Select) {
                result = lastQuery = (ResultSet) this.connection.executeQuery(statement.getStatement());
            } else {
                result = this.connection.executeUpdate(statement.getStatement());
            }

            this.sqlStatements.remove(id);
            return result;
        } catch (SQLException e) {
            System.err.println("SQL Error executing statements. Error in statement " + id);
            System.err.println("Statement: " + this.sqlStatements.get(id).getStatement());
            System.err.println(e);

            return -1;
        }
    }

    protected int executeAll() {
        int result = 0, lastExecute = -1;
        try {
            for (Statement statement : sqlStatements) {
                lastExecute = sqlStatements.indexOf(statement);
                if (statement instanceof Select) {
                    this.lastQuery = (ResultSet) this.connection.executeQuery(statement.getStatement());
                } else {
                    result += this.connection.executeUpdate(statement.getStatement());
                }
            }
            this.sqlStatements.clear();

            return result;
        } catch (SQLException e) {
            System.err.println("SQL Error executing statements. Error in statement " + lastExecute);
            System.err.println("Statement: " + this.sqlStatements.get(lastExecute).getStatement());
            System.err.println(e);

            return -1;
        }
    }

    protected void removeAll() {
        this.sqlStatements.clear();
    }

    protected void removeLast() {
        this.sqlStatements.remove(this.sqlStatements.size() - 1);
    }

    protected void addInsert(String table, String[] columns, String[][] values, boolean allText) {
        Insert statement = new Insert(columns, values, allText);
        statement.setTable(table);
        this.sqlStatements.add(statement);
    }

    protected void addInsert(String table, HashMap<String, String> values, boolean allText) {
        Insert statement = new Insert(values, allText);
        statement.setTable(table);
        this.sqlStatements.add(statement);
    }

    protected void addUpdate(String table, HashMap<String, String> values, boolean allText, HashMap<String, String> condition, boolean exact) {
        Update statement = new Update(values, allText, condition, exact);
        statement.setTable(table);
        this.sqlStatements.add(statement);
    }

    protected void addUpdate(String table, HashMap<String, String> values, HashMap<String, String> condition) {
        Update statement = new Update(values, condition);
        statement.setTable(table);
        this.sqlStatements.add(statement);
    }

    protected void addDelete(String table, HashMap<String, String> condition, boolean exact) {
        Delete statement = new Delete(condition, exact);
        statement.setTable(table);
        this.sqlStatements.add(statement);
    }

    protected void addSelect(boolean distinct, String[] columns, String table, String whereClause, String[] whereArgs,
            String groupBy, String having, String orderBy, String limit) {
        Select statement = new Select(distinct, columns, whereClause, whereArgs,
                groupBy, having, orderBy, limit);
        statement.setTable(table);
        this.sqlStatements.add(statement);
    }

    protected Statement getStatement(int id) {
        return this.sqlStatements.get(id);
    }

    protected boolean isConnected() {
        return this.connection.isConnectionOpen();
    }

    protected void openConnection() {
        this.connection.open();
    }

    protected void closeConnection(){
        try{
        this.connection.close();
        }catch(SQLException e){
            System.err.println("Could not close connection");
            System.err.println(e);
        }
    }
}
