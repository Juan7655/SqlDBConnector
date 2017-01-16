/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqldbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author juandavid
 */
public class DbConnection {

    private boolean opened = false;
    private Connection conn;
    private final String dbName;
    private final String username;
    private final String password;
    private String url;
    private final TipoDB tipoDb;

    public DbConnection(String dbName, String username, String password, String url, TipoDB tipoDb) {
        this.dbName = dbName;
        this.username = username;
        this.password = password;
        this.url = url;
        this.tipoDb = tipoDb;
    }

    protected void open() {
        String extra;
        try {
            if (conn != null)if (!conn.isClosed())return;

                switch (this.tipoDb) {
                case MYSQL:
                extra = "?autoReconnect=true&useSSL=false";
                Class.forName("com.mysql.jdbc.Driver");
                break;
                case SQLSERVER:
                extra = ";integratedSecurity=true";
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                break;
                case DB2:
                extra = ":retrieveMessagesFromServerOnGetMessage=true;";
                Class.forName("com.ibm.db2.jcc.DB2Driver");
                break;
                case POSTGRES:
                extra = "";
                Class.forName("org.postgresql.Driver");
                break;
                default:
                extra = "";
                throw new AssertionError("Valor para Base de Datos no válido",
                new IllegalArgumentException("No se reconoce a " + this.tipoDb
                + "como un tipo de Base de Datos válido"));
                }
            this.url = "jdbc:"
                    .concat(this.tipoDb.toString().toLowerCase())
                    .concat(this.tipoDb.equals(TipoDB.POSTGRES)?"ql":"")
                    .concat("://")
                    .concat(url) 
                    .concat(this.tipoDb.equals(TipoDB.SQLSERVER)?";databaseName=":"/")
                    .concat(this.dbName + extra);
            
            conn = DriverManager.getConnection(url, username, password);
            this.opened = true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Not connected: " + e);
        }
    }

    protected void close() throws SQLException {
        this.opened = false;
        if(this.conn != null)this.conn.close();
    }

    protected boolean isConnectionOpen() {
        return this.opened;
    }

    protected Object executeQuery(String sql) throws SQLException {
        return this.conn.prepareStatement(sql)
                .executeQuery();
    }

    protected int executeUpdate(String sql) throws SQLException {
        return this.conn.prepareStatement(sql)
                .executeUpdate();
    }
}
