/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.spi.DirStateFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author HUYNH MINH
 */
public class Thaydoidiemdat extends javax.swing.JFrame {
    DbConnect con;
    int flag;
    /**
     * Creates new form Thaydoidiemdat
     */
    public Thaydoidiemdat() {
        initComponents();
        con = new DbConnect();
        LoadDataCB();
        LoadDiemTK();
        jText_diemmon.setEnabled(false);
        jText_diemtk.setEnabled(false);
    }

    public void LoadDataCB(){
        try{
            ResultSet rs = con.getData("SELECT * from dbo.MONHOC");
            while (rs.next()){
                this.jComboBox1.addItem(rs.getString("Ten"));
            } 
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi",1);
        }
    }
    
    public void LoadDiem(){
        try {
            if(jComboBox1.getSelectedItem().toString()!= null){
                ResultSet rs = con.getData("SELECT MH.TEN, DD.DIEMDAT FROM MONHOC MH INNER JOIN DIEMDATMON DD ON MH.MAMH = DD.MAMH WHERE MH.TEN LIKE N'%" + jComboBox1.getSelectedItem().toString() + "%'");
                while (rs.next()){
                    jText_diemmon.setText(rs.getString("DIEMDAT"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo lỗi",1);
        }
    }
    
    public void LoadDiemTK (){
        try{
            ResultSet rs = con.getData("select * from QUYDINH where MAQD like'DIDA'");
            while (rs.next()){
                jText_diemtk.setText(rs.getString("GiaTriQD"));
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi",1);
        }
    }
    
    public void DM_UpdateData (){
        String[] stringSQL = {jText_diemmon.getText(),jComboBox1.getSelectedItem().toString()};
        
        int insert = con.DIEMMON_Update(stringSQL);
        if(insert > 0){
            JOptionPane.showMessageDialog(this,"Sửa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Sửa không thành công!");
        }
    }
    
    public void TK_UpdateData(){
        String[] stringSQL = {jText_diemtk.getText()};
        
        int insert = con.DIEMTK_Update(stringSQL);
        if(insert > 0){
            JOptionPane.showMessageDialog(this,"Sửa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Sửa không thành công!");
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jbtn_sua1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jbtn_sua2 = new javax.swing.JButton();
        jbtn_luu2 = new javax.swing.JButton();
        jText_diemmon = new javax.swing.JTextField();
        jText_diemtk = new javax.swing.JTextField();
        jbtn_luu1 = new javax.swing.JButton();

        setTitle("Thay đổi điểm đạt");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Thay đổi điểm đạt");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Môn");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Điểm đạt môn");

        jbtn_sua1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtn_sua1.setText("Sửa");
        jbtn_sua1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_sua1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Điểm đạt tổng kết");

        jbtn_sua2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtn_sua2.setText("Sửa");
        jbtn_sua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_sua2ActionPerformed(evt);
            }
        });

        jbtn_luu2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtn_luu2.setText("Lưu");
        jbtn_luu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_luu2ActionPerformed(evt);
            }
        });

        jText_diemtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jText_diemtkActionPerformed(evt);
            }
        });

        jbtn_luu1.setText("Lưu");
        jbtn_luu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_luu1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(34, 34, 34)
                                .addComponent(jText_diemmon, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jbtn_sua1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jbtn_luu1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jText_diemtk, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jbtn_sua2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jbtn_luu2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jText_diemmon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_sua1)
                    .addComponent(jbtn_luu1))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jText_diemtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_sua2)
                    .addComponent(jbtn_luu2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jText_diemtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jText_diemtkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jText_diemtkActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        LoadDiem();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jbtn_sua1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_sua1ActionPerformed
        // TODO add your handling code here:
        jText_diemmon.setEnabled(true);
        jbtn_luu1.setEnabled(true);
        flag = 1;
        jbtn_sua1.setEnabled(false);
    }//GEN-LAST:event_jbtn_sua1ActionPerformed

    private void jbtn_luu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_luu1ActionPerformed
        // TODO add your handling code here:
        jText_diemmon.setEnabled(false);
        if(flag == 1){
            DM_UpdateData();            
            jbtn_sua1.setEnabled(true);
        }
        jbtn_luu1.setEnabled(false);
    }//GEN-LAST:event_jbtn_luu1ActionPerformed

    private void jbtn_sua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_sua2ActionPerformed
        // TODO add your handling code here:
        jText_diemtk.setEnabled(true);
        jbtn_luu2.setEnabled(true);
        flag = 1;
        jbtn_sua2.setEnabled(false);
    }//GEN-LAST:event_jbtn_sua2ActionPerformed

    private void jbtn_luu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_luu2ActionPerformed
        // TODO add your handling code here:
        jText_diemtk.setEnabled(false);
        if(flag == 1){
            TK_UpdateData();            
            jbtn_sua2.setEnabled(true);
        }
        jbtn_luu2.setEnabled(false);
    }//GEN-LAST:event_jbtn_luu2ActionPerformed

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
            java.util.logging.Logger.getLogger(Thaydoidiemdat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Thaydoidiemdat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Thaydoidiemdat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Thaydoidiemdat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Thaydoidiemdat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jText_diemmon;
    private javax.swing.JTextField jText_diemtk;
    private javax.swing.JButton jbtn_luu1;
    private javax.swing.JButton jbtn_luu2;
    private javax.swing.JButton jbtn_sua1;
    private javax.swing.JButton jbtn_sua2;
    // End of variables declaration//GEN-END:variables
}
