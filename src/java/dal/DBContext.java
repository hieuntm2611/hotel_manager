/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieu2
 */
public abstract class DBContext<T> {

    protected Connection connection;

    public DBContext() {
        try {
            String username = "hieuntm";
            String password = "123456";
            String url = "jdbc:sqlserver://LAPTOP-4FC5PDPH:1433;databaseName=prj301";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public abstract ArrayList<T> all();

    public abstract T get(int id);

    public abstract T insert(T model);

    public abstract void update(T model);

    public abstract void delete(int id);

}
