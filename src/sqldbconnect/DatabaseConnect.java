/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqldbconnect;

/**
 *
 * @author juandavid
 */
public abstract class DatabaseConnect extends Database{

    public DatabaseConnect(String database, String username, String password, String url, TipoDB tipoDb) {
        super(database, username, password, url, tipoDb);
    } 
}
