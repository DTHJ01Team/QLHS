/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DbConnect {
    Connection con;
    public DbConnect (){ 
        try {
            String URL = "jdbc:sqlserver://ANHQUOC-PC\\ANHQUOCPC:1433;databaseName=QL_HOCSINH;user=anhquoc;password=123";
            con = DriverManager.getConnection(URL);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getData(String stringSQL){
        ResultSet rs = null;
        Statement state;
        try {
            state = con.createStatement();
            rs = state.executeQuery(stringSQL);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
}
