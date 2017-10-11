/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    public int HOCSINH_Insert (String[] stringSQL){
        int row=0;
        String insert ="INSERT INTO HOCSINH (MAHS,HoTen,NgaySinh,GioiTinh,Email,DiaChi) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            ps.setString(4,stringSQL[3]);
            ps.setString(5,stringSQL[4]);
            ps.setString(6,stringSQL[5]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int HOCSINH_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE HOCSINH SET HoTen=?, NgaySinh=?, GioiTinh=?, Email=?, DiaChi=? WHERE MAHS=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            ps.setString(4,stringSQL[3]);
            ps.setString(5,stringSQL[4]);
            ps.setString(6,stringSQL[5]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int HOCSINH_Delete (String[] stringSQL){
        int row=0;
        String insert = "DELETE FROM HOCSINH WHERE MAHS=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
}
