/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javax.swing.ButtonModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author User
 */
public class TNHS extends javax.swing.JInternalFrame {

    DbConnect con;
    int flag =0;
    String tmax="";
    String tmin="";
    float max;
    float min;
    /**
     * Creates new form TNHS
     */
    public TNHS() {
        initComponents();
        con = new DbConnect();
        showData();
        HideTextBox(false);
        ShowTT();
        ShowTuoi();
        max = Float.parseFloat(tmax);
        min = Float.parseFloat(tmin);
    }
    public void HideTextBox (boolean a){
        jText_MAHS.setEnabled(a);
        jText_Ten.setEnabled(a);
        jText_Ngay.setEnabled(a);
        jText_Email.setEnabled(a);
        jText_Dia.setEnabled(a);
    }
    
    public void ClearText (){
        jText_MAHS.setText("");
        jText_Ten.setText("");
        jText_Ngay.setText("dd-mm-yyyy");
        jRadio_Nam.setSelected(true);
        jText_Email.setText("---@gmail.com");
        jText_Dia.setText("");
    }
    
    public void DemoText (){
        jText_MAHS.setText("---Nhập mã học sinh---");
        jText_Ten.setText("---Nhập họ tên hoc sinh---");
        jText_Ngay.setText("---Nhập ngày tháng năm sinh---");
        jRadio_Nam.setSelected(true);
        jText_Email.setText("Ex: ---@gmail.com");
        jText_Dia.setText("---Nhập địa chỉ---");
    }
    
    public void showData (){
        String[] columnNames = {"Mã HS","Họ Tên","Ngày Sinh","Giới Tính","Email","DiaChi"};
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(columnNames);
        jTable1.setModel(model);
        
        /*Kích thước cột của Table*/
        TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = jTable1.getColumnModel().getColumn(i);
        if (i == 0) {
            column.setPreferredWidth(20); 
        } else if(i == 3){
            column.setPreferredWidth(30);
        } else if(i == 2){
            column.setPreferredWidth(50);
        } else{
            column.setPreferredWidth(100);    
        }
        }
        /*--------------*/
        String MSHS = "";
        String Ten = "";
        String Ngay = "";
        String Sex = "";
        String Email = "";
        String DiaChi = "";
        
        ResultSet rs = con.getData("SELECT MAHS, HoTen, convert (varchar(10),NgaySinh,105) as [NgaySinh],GioiTinh,Email,DiaChi FROM HOCSINH");
        try {
            while (rs.next()){
                MSHS = rs.getString("MAHS");
                Ten = rs.getString("HoTen");
                Ngay = rs.getString("NgaySinh");
                Sex = rs.getString("GioiTinh");
                Email = rs.getString("Email");
                DiaChi = rs.getString("DiaChi");
                
                model.addRow(new Object[] {MSHS,Ten,Ngay,Sex,Email,DiaChi});
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainTheme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ShowTuoi (){
        ResultSet rs = con.getData("SELECT * FROM QUYDINH WHERE MAQD LIKE '%TMAX%'");
        ResultSet rss = con.getData("SELECT * FROM QUYDINH WHERE MAQD LIKE '%TMIN%'");
        try{
            while (rs.next()){
                tmax = rs.getString("GiaTriQD");
            }
            while (rss.next()){
                tmin = rss.getString("GiaTriQD");
            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo lỗi", 1);
        }
    }
    
    public void ShowTT (){
        jLabel_tt.setText("Hiện có "+jTable1.getRowCount()+" học sinh!");
    }
    
    public void insertData (){
        String GioiTinh = "";
        if(jRadio_Nam.isSelected()){
            GioiTinh = jRadio_Nam.getText();
            jRadio_Nu.setSelected(false);
        }
        else{
            GioiTinh = jRadio_Nu.getText();
            jRadio_Nam.setSelected(false);
        }
        String[] stringSQL = {jText_MAHS.getText(),jText_Ten.getText(),jText_Ngay.getText(),GioiTinh,
                              jText_Email.getText(),jText_Dia.getText()};
        
        int insert = con.HOCSINH_Insert(stringSQL);
        if(insert > 0){
            JOptionPane.showMessageDialog(this,"Thêm học sinh thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Thêm không thành công!");
        }
        showData();
        ClearText();
    }
    
    public void updateData (){
        String GioiTinh = "";
        if(jRadio_Nam.isSelected()){
            GioiTinh = jRadio_Nam.getText();
        }
        else{
            GioiTinh = jRadio_Nu.getText();
        }
        String[] stringSQL = {jText_Ten.getText(),jText_Ngay.getText(),GioiTinh,
                              jText_Email.getText(),jText_Dia.getText(),jText_MAHS.getText()};
        
        int update = con.HOCSINH_Update(stringSQL);
        if(update > 0){
            JOptionPane.showMessageDialog(this,"Sửa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Sửa không thành công!");
        }
        showData();
        ClearText();
    }
    
    public void deleteData (){
        String[] stringSQL = {jText_MAHS.getText()};
        
        int delete = con.HOCSINH_Delete(stringSQL);
      
        if(delete > 0){
            JOptionPane.showMessageDialog(this,"Xóa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Xóa không thành công!");
        }
        showData();
        ClearText();
    }
    
    public void getselectData(){
        int selectRow = jTable1.getSelectedRow();
        jText_MAHS.setText(jTable1.getValueAt(selectRow,0).toString());
        jText_Ten.setText(jTable1.getValueAt(selectRow,1).toString());
        jText_Ngay.setText(jTable1.getValueAt(selectRow,2).toString());
        if (jTable1.getValueAt(selectRow,3).toString().equals("Nam")){
            jRadio_Nam.setSelected(true);
            jRadio_Nu.setSelected(false);
        }
        else{
            jRadio_Nam.setSelected(false);
            jRadio_Nu.setSelected(true);
        }
        jText_Email.setText(jTable1.getValueAt(selectRow,4).toString());
        jText_Dia.setText(jTable1.getValueAt(selectRow,5).toString());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbtn_them = new javax.swing.JButton();
        jbtn_Luu = new javax.swing.JButton();
        jbtn_Xoa = new javax.swing.JButton();
        jbtn_Sua = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadio_Nam = new javax.swing.JRadioButton();
        jRadio_Nu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jText_MAHS = new javax.swing.JTextField();
        jLabel_tt = new javax.swing.JLabel();
        jText_Ngay = new javax.swing.JTextField();
        jText_Ten = new javax.swing.JTextField();
        jText_Email = new javax.swing.JTextField();
        jText_Dia = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Tiếp Nhận Thông Tin\n");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã HS", "Họ Tên", "Ngày Sinh", "Giới Tính", "Mail", "Địa Chỉ"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTable1);

        jbtn_them.setText("Thêm");
        jbtn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_themActionPerformed(evt);
            }
        });

        jbtn_Luu.setText("Lưu");
        jbtn_Luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_LuuActionPerformed(evt);
            }
        });

        jbtn_Xoa.setText("Xoá");
        jbtn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_XoaActionPerformed(evt);
            }
        });

        jbtn_Sua.setText("Sửa");
        jbtn_Sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_SuaActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Họ Tên");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ngày Sinh");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Giới Tính");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Mail");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Địa Chỉ");

        jRadio_Nam.setText("Nam");
        jRadio_Nam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_NamMouseClicked(evt);
            }
        });
        jRadio_Nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadio_NamActionPerformed(evt);
            }
        });

        jRadio_Nu.setText("Nữ");
        jRadio_Nu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_NuMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Mã HS");

        jText_MAHS.setText("---Nhập mã học sinh---");
        jText_MAHS.setMinimumSize(new java.awt.Dimension(6, 25));
        jText_MAHS.setPreferredSize(new java.awt.Dimension(6, 25));
        jText_MAHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_MAHSActionPerformed(evt);
            }
        });

        jLabel_tt.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel_tt.setText("jLabel7");

        jText_Ngay.setText("---Nhập ngày tháng năm sinh---");

        jText_Ten.setText("---Nhập họ tên hoc sinh---");

        jText_Email.setText("Ex: ---@gmail.com");

        jText_Dia.setText("---Nhập địa chỉ---");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jText_Ngay))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jText_Ten))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(42, 42, 42)
                                        .addComponent(jText_MAHS, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(jbtn_them)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jRadio_Nam)
                                .addGap(46, 46, 46)
                                .addComponent(jRadio_Nu))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jText_Email, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jText_Dia))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_tt)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jText_MAHS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jRadio_Nam)
                                .addComponent(jRadio_Nu)))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jText_Ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jText_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jText_Dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jText_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_them)
                    .addComponent(jbtn_Sua)
                    .addComponent(jbtn_Luu)
                    .addComponent(jbtn_Xoa))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel_tt)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_themActionPerformed
        ClearText();
        HideTextBox(true);
        jbtn_Luu.setEnabled(true);
        flag = 1;
        jbtn_them.setEnabled(false);
        jbtn_Sua.setEnabled(false);
        jbtn_Xoa.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn_themActionPerformed

    private void jRadio_NamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadio_NamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadio_NamActionPerformed

    private void jbtn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_XoaActionPerformed
        ClearText();
        HideTextBox(true);
        jbtn_Luu.setEnabled(true);
        flag = 3;
        jbtn_them.setEnabled(false);
        jbtn_Sua.setEnabled(false);
        jbtn_Xoa.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn_XoaActionPerformed

    private void jbtn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_SuaActionPerformed
        ClearText();
        HideTextBox(true);
        jbtn_Luu.setEnabled(true);
        flag = 2;
        jbtn_them.setEnabled(false);
        jbtn_Sua.setEnabled(false);
        jbtn_Xoa.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn_SuaActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        getselectData();
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jbtn_LuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_LuuActionPerformed
        if(flag == 1){
            if(this.jText_MAHS.getText().length() == 0)
                JOptionPane.showMessageDialog(null,"Mã học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                if (this.jText_MAHS.getText().length() > 10)
                    JOptionPane.showMessageDialog(null,"Mã học sinh không vượt quá 10 ký tự!","Thông báo lỗi!", 1);
            else
                    if(this.jText_Ten.getText().length() == 0)
                        JOptionPane.showMessageDialog(null,"Tên học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                        if(this.jText_Dia.getText().length() == 0)
                            JOptionPane.showMessageDialog(null,"Địa chỉ không được để trống!","Thông báo lỗi!", 1);
            else
                insertData();
            jbtn_them.setEnabled(true);
            jbtn_Xoa.setEnabled(true);
            jbtn_Sua.setEnabled(true);
            
        }
        else if(flag == 2){
            if(this.jText_MAHS.getText().length() == 0)
                JOptionPane.showMessageDialog(null,"Mã học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                if (this.jText_MAHS.getText().length() > 10)
                    JOptionPane.showMessageDialog(null,"Mã học sinh không vượt quá 10 ký tự!","Thông báo lỗi!", 1);
            else
                    if(this.jText_Ten.getText().length() == 0)
                        JOptionPane.showMessageDialog(null,"Tên học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                        if(this.jText_Dia.getText().length() == 0)
                            JOptionPane.showMessageDialog(null,"Địa chỉ không được để trống!","Thông báo lỗi!", 1);
            else
                updateData();            
            jbtn_them.setEnabled(true);
            jbtn_Xoa.setEnabled(true);
            jbtn_Sua.setEnabled(true);
        }
        else{
            if(this.jText_MAHS.getText().length() == 0)
                JOptionPane.showMessageDialog(null,"Bạn cần chọn học sinh để xóa","Thông báo lỗi!", 1);
            else
            {
                if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Thông báo!", 2)==0)
                    deleteData();
            }
          
            jbtn_them.setEnabled(true);
            jbtn_Xoa.setEnabled(true);
            jbtn_Sua.setEnabled(true);
        }
        DemoText();
        HideTextBox(false);
        jbtn_Luu.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn_LuuActionPerformed

    private void jRadio_NamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadio_NamMouseClicked
        // TODO add your handling code here:
        jRadio_Nam.setSelected(true);
        jRadio_Nu.setSelected(false);
    }//GEN-LAST:event_jRadio_NamMouseClicked

    private void jRadio_NuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadio_NuMouseClicked
        // TODO add your handling code here:
        jRadio_Nam.setSelected(false);
        jRadio_Nu.setSelected(true);
    }//GEN-LAST:event_jRadio_NuMouseClicked

    private void jText_MAHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_MAHSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_MAHSActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_tt;
    private javax.swing.JRadioButton jRadio_Nam;
    private javax.swing.JRadioButton jRadio_Nu;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jText_Dia;
    private javax.swing.JTextField jText_Email;
    private javax.swing.JTextField jText_MAHS;
    private javax.swing.JTextField jText_Ngay;
    private javax.swing.JTextField jText_Ten;
    private javax.swing.JButton jbtn_Luu;
    private javax.swing.JButton jbtn_Sua;
    private javax.swing.JButton jbtn_Xoa;
    private javax.swing.JButton jbtn_them;
    // End of variables declaration//GEN-END:variables
}
