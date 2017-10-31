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
import javax.swing.JTextField;

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
    
    //VIET HÀM CHO FORM TIEP NHAN HOC SINH
    public int HOCSINH_Insert (String[] stringSQL){
        int row=0;
        String insert ="set dateformat dmy INSERT INTO HOCSINH (MAHS,HoTen,NgaySinh,GioiTinh,Email,DiaChi) VALUES(?,?,?,?,?,?)";
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
        String insert ="set dateformat dmy UPDATE HOCSINH SET HoTen=?, NgaySinh=?, GioiTinh=?, Email=?, DiaChi=? WHERE MAHS=?";
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
    //KET THUC VIET HAM TIEP NHAN HOC SINH
    
    //VIET HAM CHO THAY DOI MON HOC
    public int MONHOC_Insert (String[] stringSQL){
        int row=0;
        String insert ="INSERT INTO MONHOC (MAMH,Ten) VALUES(?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int MONHOC_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE MONHOC SET Ten=? WHERE MAMH=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int MONHOC_Delete (String[] stringSQL){
        int row=0;
        String insert = "DELETE FROM MONHOC WHERE MAMH=?";
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
    //KET THUC VIET HAM THAY DOI MON HOC
    
    
    //VIET HAM CHO Thaydoimonhoc_QD
    public int QDMH_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE QUYDINH SET GiaTriQD=? WHERE MAQD like 'SLMH'";
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
    //KET THUC VIET HAM CHO Thaydoimonhoc_QD
    
    //BAT DAU VIET HAM CHO Thaydoidiemdat
    public int DIEMMON_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE DIEMDATMON SET DiemDat=? FROM MONHOC WHERE DIEMDATMON.MAMH = MONHOC.MAMH and MONHOC.Ten like ?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int DIEMTK_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE QUYDINH SET GiaTriQD=? WHERE MAQD like 'DIDA'";
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
    
    
    //KET THUC VIET HAM CHO Thaydoidiemdat
    
    //BAT DAU VIET HAM CHO Thaydoidotuoi
    public int TuoiMin_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE QUYDINH SET GiaTriQD=? WHERE MAQD like 'TMIN'";
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
    
    public int TuoiMax_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE QUYDINH SET GiaTriQD=? WHERE MAQD like 'TMAX'";
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
    //KET THUC VIET HAM CHO Thaydoidotuoi
    
    //BAT DAU VIET HAM CHO Thaydoisiso
    public int Lop_Insert (String[] stringSQL){
        int row=0;
        String insert ="INSERT INTO LOP (MALOP,SL,MAKHOI) VALUES(?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int Lop_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE LOP SET SL=? WHERE MALOP=? and MAKHOI=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int Lop_Delete (String[] stringSQL){
        int row=0;
        String insert = "DELETE FROM LOP WHERE MALOP=? and MAKHOI=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    //KET THUC VIET HAM CHO Thaydoisiso
    
    //VIET HAM CHO Thaydoisiso_QD
    public int SSL_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE QUYDINH SET GiaTriQD=? WHERE MAQD like 'SLLH'";
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
    //KET THUC VIET HAM CHO Thaydoisiso_QD
    
    //VIET HAM CHO themhskhoi10-11-12
    public int HS_Insert (String[] stringSQL){
        int row=0;
        String insert ="INSERT INTO CTLOP (MAHS,MALOP) VALUES(?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int HS_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE CTLOP SET MALOP=? WHERE MAHS=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int HS_Delete (String[] stringSQL){
        int row=0;
        String insert = "DELETE FROM CTLOP WHERE MAHS=?";
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
    //KET THUC VIET HAM CHO themhskhoi10-11-12
    
    //BAT DAU VIET HAM CHO NhapDiemMonHoc
    public int ND_Insert (String[] stringSQL){
        int row=0;
        String insert ="INSERT INTO DIEMKIEMTRA (MAHS,MAMH,MAHK,[15P],[45P]) VALUES(?,?,?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            ps.setString(4,stringSQL[3]);
            ps.setString(5,stringSQL[4]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int ND_Update (String[] stringSQL){
        int row=0;
        String insert ="UPDATE DIEMKIEMTRA SET [15P]=?, [45P] = ? WHERE MAHS=? and MAMH =? and MAHK=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            ps.setString(4,stringSQL[3]);
            ps.setString(5,stringSQL[4]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int ND_Delete (String[] stringSQL){
        int row=0;
        String insert = "DELETE FROM DIEMKIEMTRA WHERE MAHS=? and MAMH=? and MAHK=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int DTB_Update (String[] stringSQL){
        int row=0;
        String insert ="update DIEMKIEMTRA set DTBM = (select (([15P] + [45P]*2)/3))";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    //KET THUC VIET HAM CHO NhapDiemMonHoc
    
    
    //BAT DAU VIET HAM CHO TinhDiemHocKy
    public int DHK_Insert (String[] stringSQL){
        int row=0;
        String insert ="INSERT INTO DIEMHOCKY (MAHS,MALOP,MAHK) VALUES(?,?,?)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int DHK_Delete (String[] stringSQL){
        int row=0;
        String insert = "DELETE FROM DIEMHOCKY WHERE MAHS=? and MALOP=? and MAHK=?";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            ps.setString(1,stringSQL[0]);
            ps.setString(2,stringSQL[1]);
            ps.setString(3,stringSQL[2]);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    
    public int DTBHK_Update (String[] stringSQL){
        int row=0;
        String insert ="update DIEMHOCKY set DTBHK = (select CONVERT(decimal(5,2),sum(DTBM)/count(MAHS)) from DIEMKIEMTRA D where D.MAHS = DIEMHOCKY.MAHS and D.MAHK = DIEMHOCKY.MAHK group by MAHS, MAHK)";
        PreparedStatement ps;
        try {
            ps = con.prepareCall(insert);
            
            row = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }
    //KET THUC VIET HAM TinhDiemHocKy
}
