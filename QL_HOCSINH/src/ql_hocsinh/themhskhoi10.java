/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class themhskhoi10 extends javax.swing.JFrame {
    DbConnect con;
    int flag;
    /**
     * Creates new form themhskhoi10
     */
    public themhskhoi10() {
        initComponents();
        con = new DbConnect();
        showData();
        LoadDataCB();
        jText_mhs.setEnabled(false);
    }
    
    public void showData (){
        String[] columnNames = {"Mã học sinh","Tên học sinh","Lớp"};
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(columnNames);
        jTable1.setModel(model);
        
        String MAHS = "";
        String Ten = "";
        String Lop = "";
        
        ResultSet rs = con.getData("select H.MAHS, h.HoTen, L.MALOP from LOP L, CTLOP C, HOCSINH H where l.MALOP = c.MALOP and c.MAHS = h.MAHS and l.MALOP like '10%'");
        
        try {
            while (rs.next()){
                MAHS= rs.getString("MAHS");
                Ten = rs.getString("HoTen");
                Lop = rs.getString("MALOP");
                model.addRow(new Object[] {MAHS,Ten,Lop});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo lỗi!", 1);
        }
        
    }
    
    public void LoadDataCB(){
        try{
            ResultSet rs = con.getData("SELECT * from LOP WHERE MALOP LIKE '10%'");
            while (rs.next()){
                this.jComboBox1.addItem(rs.getString("MALOP"));
            } 
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi",1);
        }
    }
    
    public void InsertData(){ 
        String[] stringSQL = {jText_mhs.getText(),jComboBox1.getSelectedItem().toString()};
        
        int insert = con.HS10_Insert(stringSQL);
        if(insert > 0){
            JOptionPane.showMessageDialog(this,"Thêm thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Thêm không thành công!");
        }
        showData();
    }
    
    public void updateData (){
        String[] stringSQL = {jComboBox1.getSelectedItem().toString(),jText_mhs.getText()};
        
        int update = con.HS10_Update(stringSQL);
        if(update > 0){
            JOptionPane.showMessageDialog(this,"Sửa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Sửa không thành công!");
        }
        showData();
    }
    
    public void deleteData (){
        String[] stringSQL = {jText_mhs.getText()};
        
        int delete = con.HS10_Delete(stringSQL);
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
        jText_mhs.setText(jTable1.getValueAt(selectRow,0).toString());
        jComboBox1.setSelectedItem(jTable1.getValueAt(selectRow,2));
    }
    
    public void ClearText (){
        jText_mhs.setText("");
        jComboBox1.setSelectedIndex(0);
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
        jText_mhs = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbtn_luu = new javax.swing.JButton();
        jbtn_them = new javax.swing.JButton();
        jbtn_xoa = new javax.swing.JButton();
        jbtn_sua = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("                 THÊM HỌC SINH KHỐI 10");

        jLabel2.setText("Mã học sinh");

        jLabel3.setText("Lớp");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "MHS", "Tên", "Lớp"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jbtn_luu.setText("Lưu");
        jbtn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_luuActionPerformed(evt);
            }
        });

        jbtn_them.setText("Thêm");
        jbtn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_themActionPerformed(evt);
            }
        });

        jbtn_xoa.setText("Xoá");
        jbtn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_xoaActionPerformed(evt);
            }
        });

        jbtn_sua.setText("Sửa");
        jbtn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_suaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jbtn_them)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jText_mhs, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jText_mhs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_luu)
                    .addComponent(jbtn_xoa)
                    .addComponent(jbtn_sua)
                    .addComponent(jbtn_them))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        getselectData();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jbtn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_themActionPerformed
        // TODO add your handling code here:
        ClearText();
        jText_mhs.setEnabled(true);
        jbtn_luu.setEnabled(true);
        flag = 1;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
    }//GEN-LAST:event_jbtn_themActionPerformed

    private void jbtn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_suaActionPerformed
        // TODO add your handling code here:
        jText_mhs.setEnabled(true);
        jbtn_luu.setEnabled(true);
        flag = 2;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
    }//GEN-LAST:event_jbtn_suaActionPerformed

    private void jbtn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_xoaActionPerformed
        // TODO add your handling code here:
        jText_mhs.setEnabled(true);
        jbtn_luu.setEnabled(true);
        flag = 3;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
    }//GEN-LAST:event_jbtn_xoaActionPerformed

    private void jbtn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_luuActionPerformed
        // TODO add your handling code here:
        if(flag == 1){
            InsertData();
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
            jbtn_sua.setEnabled(true);
        }
        else if(flag == 2){
            updateData();            
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
            jbtn_sua.setEnabled(true);
        }
        else{
            deleteData();            
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
            jbtn_sua.setEnabled(true);
        }
        ClearText();
        jText_mhs.setEnabled(false);
        jbtn_luu.setEnabled(false);
    }//GEN-LAST:event_jbtn_luuActionPerformed

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
            java.util.logging.Logger.getLogger(themhskhoi10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(themhskhoi10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(themhskhoi10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(themhskhoi10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new themhskhoi10().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jText_mhs;
    private javax.swing.JButton jbtn_luu;
    private javax.swing.JButton jbtn_sua;
    private javax.swing.JButton jbtn_them;
    private javax.swing.JButton jbtn_xoa;
    // End of variables declaration//GEN-END:variables
}