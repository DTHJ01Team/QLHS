/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import Process.checkSymbol;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HUYNH MINH
 */
public class Thaydoisiso extends javax.swing.JFrame implements ActionListener{
    DbConnect con;
    int flag;
    String SL;
    float DK;
    /**
     * Creates new form Thaydoisiso
     */
    public Thaydoisiso() {
        initComponents();
        con = new DbConnect();
        showData();
        ShowTT();
        DK = Float.parseFloat(SL);
        HideTextBox(false);
        jbtn_QD.addActionListener(this);
    }
    
    public void showData (){
        String[] columnNames = {"Mã lớp","Khối","Sỉ số lớp"};
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(columnNames);
        jTable1.setModel(model);
        
        String MALOP = "";
        String KHOI = "";
        String SS = "";
        
        ResultSet rs = con.getData("SELECT * FROM LOP");
        ResultSet rss = con.getData("select * from QUYDINH where MAQD like'SLLH'");
        try {
            while (rs.next()){
                MALOP = rs.getString("MALOP");
                KHOI = rs.getString("MAKHOI");
                SS = rs.getString("SL");
                model.addRow(new Object[] {MALOP,KHOI,SS});
            }
            while (rss.next()){
                SL = rss.getString("GiaTriQD");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainTheme.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void HideTextBox (boolean a){
        jText_lop.setEnabled(a);
        jText_ss.setEnabled(a);
    }
    
    public void ShowTT (){
        jLabel_tt.setText("Hiện có "+jTable1.getRowCount()+" lớp học!");
    }
    
    public void InsertData(){
        String KHOI = "";
        if(jRadio_k10.isSelected()){
            KHOI = "K10";
        }
        else if (jRadio_k11.isSelected()){
            KHOI = "K11";
        }
        else {
            KHOI = "K12";
        }
        
        String[] stringSQL = {jText_lop.getText(),jText_ss.getText(), KHOI};
        
        int insert = con.Lop_Insert(stringSQL);
        if(insert > 0){
            JOptionPane.showMessageDialog(this,"Thêm thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Thêm không thành công!");
        }
        showData();
    }
    
    public void updateData (){
        String KHOI = "";
        if(jRadio_k10.isSelected()){
            KHOI = "K10";
        }
        else if (jRadio_k11.isSelected()){
            KHOI = "K11";
        }
        else {
            KHOI = "K12";
        }
        String[] stringSQL = {jText_ss.getText(),jText_lop.getText(),KHOI};
        
        int update = con.Lop_Update(stringSQL);
        if(update > 0){
            JOptionPane.showMessageDialog(this,"Sửa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Sửa không thành công!");
        }
        showData();
    }
    
    public void deleteData (){
        String KHOI = "";
        if(jRadio_k10.isSelected()){
            KHOI = "K10";
        }
        else if (jRadio_k11.isSelected()){
            KHOI = "K11";
        }
        else {
            KHOI = "K12";
        }
        String[] stringSQL = {jText_lop.getText(),KHOI};
        
        int delete = con.Lop_Delete(stringSQL);
        if(delete > 0){
            JOptionPane.showMessageDialog(this,"Xóa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Xóa không thành công!");
        }
        showData();
    }
    
    public void getselectData(){
        int selectRow = jTable1.getSelectedRow();
        jText_lop.setText(jTable1.getValueAt(selectRow,0).toString());
        if (jTable1.getValueAt(selectRow, 1).toString().equals("K10")){
            jRadio_k10.setSelected(true);
            jRadio_k11.setSelected(false);
            jRadio_k12.setSelected(false);
        }
        else if(jTable1.getValueAt(selectRow, 1).toString().equals("K11")){
            jRadio_k10.setSelected(false);
            jRadio_k11.setSelected(true);
            jRadio_k12.setSelected(false);
        }
        else {
            jRadio_k10.setSelected(false);
            jRadio_k11.setSelected(false);
            jRadio_k12.setSelected(true);
        }
        jText_ss.setText(jTable1.getValueAt(selectRow,2).toString());
    }
    
    public void ClearText (){
        jText_lop.setText("");
        jText_ss.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadio_k10 = new javax.swing.JRadioButton();
        jRadio_k11 = new javax.swing.JRadioButton();
        jRadio_k12 = new javax.swing.JRadioButton();
        jText_lop = new javax.swing.JTextField();
        jText_ss = new javax.swing.JTextField();
        jbtn_luu = new javax.swing.JButton();
        jbtn_sua = new javax.swing.JButton();
        jbtn_xoa = new javax.swing.JButton();
        jLabel_tt = new javax.swing.JLabel();
        jbtn_them = new javax.swing.JButton();
        jbtn_QD = new javax.swing.JButton();

        setTitle("Danh sách lớp");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Thay đổ sỉ số lớp");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Mã lớp");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Khối");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Số lượng");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã lớp", "Khối", "Số lượng"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jRadio_k10.setText("Khối 10");
        jRadio_k10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_k10MouseClicked(evt);
            }
        });

        jRadio_k11.setText("Khối 11");
        jRadio_k11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_k11MouseClicked(evt);
            }
        });

        jRadio_k12.setText("Khối 12");
        jRadio_k12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_k12MouseClicked(evt);
            }
        });

        jText_ss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_ssActionPerformed(evt);
            }
        });

        jbtn_luu.setText("Lưu");
        jbtn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_luuActionPerformed(evt);
            }
        });

        jbtn_sua.setText("Sửa");
        jbtn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_suaActionPerformed(evt);
            }
        });

        jbtn_xoa.setText("Xóa");
        jbtn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_xoaActionPerformed(evt);
            }
        });

        jLabel_tt.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel_tt.setText("jLabel5");

        jbtn_them.setText("Thêm");
        jbtn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_themActionPerformed(evt);
            }
        });

        jbtn_QD.setText("Quy định");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_tt)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 23, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jText_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jRadio_k10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jRadio_k11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jRadio_k12))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jText_ss, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(jbtn_QD, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbtn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jbtn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jText_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadio_k10)
                    .addComponent(jRadio_k11)
                    .addComponent(jRadio_k12))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jText_ss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtn_QD))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_luu)
                    .addComponent(jbtn_sua)
                    .addComponent(jbtn_xoa)
                    .addComponent(jbtn_them))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_tt)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jText_ssActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_ssActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_ssActionPerformed

    private void jbtn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_themActionPerformed
        // TODO add your handling code here:
        ClearText();
        HideTextBox(true);
        jbtn_luu.setEnabled(true);
        flag = 1;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
    }//GEN-LAST:event_jbtn_themActionPerformed

    private void jbtn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_suaActionPerformed
        // TODO add your handling code here:
        HideTextBox(true);
        jbtn_luu.setEnabled(true);
        flag = 2;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
    }//GEN-LAST:event_jbtn_suaActionPerformed

    private void jbtn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_xoaActionPerformed
        // TODO add your handling code here:
        HideTextBox(true);
        jbtn_luu.setEnabled(true);
        flag = 3;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
    }//GEN-LAST:event_jbtn_xoaActionPerformed

    private void jbtn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_luuActionPerformed
        // TODO add your handling code here:
        if(flag == 1){
            if(jTable1.getRowCount()< DK){
                InsertData();
            } else{
                JOptionPane.showMessageDialog(this,"Vượt quá số lượng lớp đăng ký!\n Vui lòng xem lại quy định!");
            }
                if(this.jText_lop.getText().length() == 0)
                                JOptionPane.showMessageDialog(null,"Tên học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                         if(checkSymbol.checkSb(this.jText_lop.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else
                             if(this.jText_ss.getText().length() == 0)
                                JOptionPane.showMessageDialog(null,"Tên học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                         if(checkSymbol.checkSb(this.jText_ss.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else
                             if(checkSymbol.checkNum(this.jText_ss.getText())==false)
                           JOptionPane.showMessageDialog(null, "Phát hiện có ký tự-không phải là số!","Thông báo lỗi!", 1);
            else                            
                InsertData();
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
            jbtn_sua.setEnabled(true);
        }
        else if(flag == 2){
             if(this.jText_lop.getText().length() == 0)
                                JOptionPane.showMessageDialog(null,"Tên học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                         if(checkSymbol.checkSb(this.jText_lop.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else
                             if(this.jText_ss.getText().length() == 0)
                                JOptionPane.showMessageDialog(null,"Tên học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                         if(checkSymbol.checkSb(this.jText_ss.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else
                             if(checkSymbol.checkNum(this.jText_ss.getText())==false)
                           JOptionPane.showMessageDialog(null, "Phát hiện có ký tự-không phải là số!","Thông báo lỗi!", 1);
            else      
            updateData();            
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
            jbtn_sua.setEnabled(true);
        }
        else{
            
            if(this.jText_lop.getText().length() == 0)
                JOptionPane.showMessageDialog(null,"Bạn cần chọn học sinh để xóa","Thông báo lỗi!", 1);
            else
            {
                if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa?", "Thông báo!", 2)==0)
                    deleteData();
            }          
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
            jbtn_sua.setEnabled(true);
        }
        ClearText();
        HideTextBox(false);
        jbtn_luu.setEnabled(false);
    }//GEN-LAST:event_jbtn_luuActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        getselectData();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jRadio_k10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadio_k10MouseClicked
        // TODO add your handling code here:
        jRadio_k10.setSelected(true);
        jRadio_k11.setSelected(false);
        jRadio_k12.setSelected(false);
    }//GEN-LAST:event_jRadio_k10MouseClicked

    private void jRadio_k11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadio_k11MouseClicked
        // TODO add your handling code here:
        jRadio_k10.setSelected(false);
        jRadio_k11.setSelected(true);
        jRadio_k12.setSelected(false);
    }//GEN-LAST:event_jRadio_k11MouseClicked

    private void jRadio_k12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadio_k12MouseClicked
        // TODO add your handling code here:
        jRadio_k10.setSelected(false);
        jRadio_k11.setSelected(false);
        jRadio_k12.setSelected(true);
    }//GEN-LAST:event_jRadio_k12MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Thaydoisiso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Thaydoisiso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Thaydoisiso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Thaydoisiso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Thaydoisiso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_tt;
    private javax.swing.JRadioButton jRadio_k10;
    private javax.swing.JRadioButton jRadio_k11;
    private javax.swing.JRadioButton jRadio_k12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jText_lop;
    private javax.swing.JTextField jText_ss;
    private javax.swing.JButton jbtn_QD;
    private javax.swing.JButton jbtn_luu;
    private javax.swing.JButton jbtn_sua;
    private javax.swing.JButton jbtn_them;
    private javax.swing.JButton jbtn_xoa;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
        new Thaydoisiso_QD().setVisible(true);
    }
}
