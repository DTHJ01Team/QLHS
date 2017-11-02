/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnhQuoc
 */
public class TinhDiemHocKy extends javax.swing.JFrame {
    DbConnect con;
    int flag= 0;
    /**
     * Creates new form TinhDiemHocKy
     */
    public TinhDiemHocKy() {
        initComponents();
        con = new DbConnect();
        LoadDataCB();
        jText_ten.setEnabled(false);
        UpdateDTB();
        showData();
        ShowTT();
        LoadTen();
        jcb_lop.setEnabled(false);
    }
    public void LoadDataCB(){
        try{
            ResultSet rss = con.getData("SELECT * FROM HOCSINH");
            ResultSet rsss = con.getData("SELECT * FROM LOP");
            while (rss.next()){
                this.jcb_hs.addItem(rss.getString("MAHS"));
            }
            while (rsss.next()){
                this.jcb_lop.addItem(rsss.getString("MALOP"));
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e, "Thông báo lỗi",1);
        }
    }
    
    public void LoadTen(){
        try {
                ResultSet rs = con.getData("SELECT * FROM HOCSINH H, CTLOP C WHERE H.MAHS = c.MAHS and c.MAHS like N'%" + jcb_hs.getSelectedItem().toString() + "%'");
                ResultSet rss = con.getData ("SELECT * FROM HOCSINH WHERE MAHS LIKE N'%"+ jcb_hs.getSelectedItem().toString() +"%'");
                while (rs.next()){
                    jcb_lop.setSelectedItem(rs.getString("MALOP"));
                }
                while (rss.next()){
                    jText_ten.setText(rss.getString("HoTen"));
                }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo lỗi",1);
        }
    }
    
    public void showData (){
        String[] columnNames = {"Mã học sinh","Họ tên","Lớp","Học kỳ","Điểm TBHK"};
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(columnNames);
        jTable1.setModel(model);
        
        String MA ="";
        String Ten ="";
        String Lop = "";
        String HK = "";
        String DTB = "";
        
        ResultSet rs = con.getData("select H.MAHS , H.HoTen, D.MALOP, D.MAHK, DTBHK from DIEMHOCKY D, HOCSINH H where D.MAHS = H.MAHS");        
        try {
            while (rs.next()){
                MA = rs.getString("MAHS");
                Ten = rs.getString("HoTen");
                Lop = rs.getString("MALOP");
                HK = rs.getString("MAHK");
                DTB = rs.getString("DTBHK");
                
                model.addRow(new Object[] {MA,Ten,Lop,HK,DTB});
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainTheme.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void ShowTT (){
        jLabel_tt.setText("Hiện có "+jTable1.getRowCount()+" học sinh!");
    }

    public void getselectData(){
        int selectRow = jTable1.getSelectedRow();
        jcb_hs.setSelectedItem(jTable1.getValueAt(selectRow, 0));
        jText_ten.setText(jTable1.getValueAt(selectRow, 1).toString());
        jcb_lop.setSelectedItem(jTable1.getValueAt(selectRow, 2));
        if (jTable1.getValueAt(selectRow,2).toString().equals("HK1")){
            jRadio_hk1.setSelected(true);
            jRadio_hk2.setSelected(false);
        }
        else{
            jRadio_hk1.setSelected(false);
            jRadio_hk2.setSelected(true);
        }
    }
    
    public void ClearText (){
        jcb_hs.setSelectedIndex(0);
    }
    
    public void UpdateDTB (){
        String HK = "";
        if(jRadio_hk1.isSelected())
        {
            HK = "HK1";
            jRadio_hk2.setSelected(false);
        }
        else
        {
            HK = "HK2";
            jRadio_hk1.setSelected(false);
        }
        String[] stringSQL = {jcb_hs.getSelectedItem().toString(),HK};
        con.DTBHK_Update(stringSQL); 
    }
    
    public void InsertData(){ 
        String HK = "";
        if(jRadio_hk1.isSelected())
        {
            HK = "HK1";
            jRadio_hk2.setSelected(false);
        }
        else
        {
            HK = "HK2";
            jRadio_hk1.setSelected(false);
        }
        String[] stringSQL = {jcb_hs.getSelectedItem().toString(),jcb_lop.getSelectedItem().toString(),HK};
        
        int insert = con.DHK_Insert(stringSQL);
        if(insert > 0){
            JOptionPane.showMessageDialog(this,"Thêm thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Thêm không thành công!");
        }
        UpdateDTB();
        showData();
    }
    
    public void deleteData (){
        String HK = "";
        if(jRadio_hk1.isSelected())
        {
            HK = "HK1";
            jRadio_hk2.setSelected(false);
        }
        else
        {
            HK = "HK2";
            jRadio_hk1.setSelected(false);
        }
        
        String[] stringSQL = {jcb_hs.getSelectedItem().toString(), jcb_lop.getSelectedItem().toString(), HK};
        
        int delete = con.DHK_Delete(stringSQL);
        if(delete > 0){
            JOptionPane.showMessageDialog(this,"Xóa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Xóa không thành công!");
        }
        showData();
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
        jLabel_tt = new javax.swing.JLabel();
        jText_ten = new javax.swing.JTextField();
        jbtn_them = new javax.swing.JButton();
        jbtn_xoa = new javax.swing.JButton();
        jbtn_luu = new javax.swing.JButton();
        jcb_hs = new javax.swing.JComboBox<>();
        jcb_lop = new javax.swing.JComboBox<>();
        jRadio_hk2 = new javax.swing.JRadioButton();
        jRadio_hk1 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel1.setText("Học Kỳ");

        jLabel_tt.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel_tt.setText("jLabel9");

        jbtn_them.setText("Thêm");
        jbtn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_themActionPerformed(evt);
            }
        });

        jbtn_xoa.setText("Xóa");
        jbtn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_xoaActionPerformed(evt);
            }
        });

        jbtn_luu.setText("Lưu");
        jbtn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_luuActionPerformed(evt);
            }
        });

        jcb_hs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcb_hsItemStateChanged(evt);
            }
        });

        jcb_lop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jcb_lop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jRadio_hk2.setText("II");
        jRadio_hk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_hk2MouseClicked(evt);
            }
        });

        jRadio_hk1.setText("I");
        jRadio_hk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_hk1MouseClicked(evt);
            }
        });

        jLabel2.setText("Họ Tên");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Lớp");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Họ Tên", "Lớp", "Học kỳ", "Điểm TB Học kỳ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Bảng Điểm Học Kỳ");

        jLabel3.setText("Mã học sinh");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel_tt)
                .addContainerGap(377, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(124, 124, 124))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(1, 13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jcb_hs, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jRadio_hk1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadio_hk2)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jText_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jbtn_them)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jText_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcb_hs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadio_hk1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadio_hk2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_them)
                    .addComponent(jbtn_xoa)
                    .addComponent(jbtn_luu))
                .addGap(13, 13, 13)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_tt)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_themActionPerformed
        // TODO add your handling code here:
        ClearText();
        jbtn_luu.setEnabled(true);
        flag = 1;
        jbtn_them.setEnabled(false);
        jbtn_xoa.setEnabled(false);
        
    }//GEN-LAST:event_jbtn_themActionPerformed

    private void jbtn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_xoaActionPerformed
        // TODO add your handling code here:
        jbtn_luu.setEnabled(true);
        flag = 3;
        jbtn_them.setEnabled(false);
        jbtn_xoa.setEnabled(false);

    }//GEN-LAST:event_jbtn_xoaActionPerformed

    private void jbtn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_luuActionPerformed
        // TODO add your handling code here:
        if(flag == 1){
            InsertData();
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
        }
        else{
            deleteData();
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
        }
        ClearText();
        jbtn_luu.setEnabled(false);
        
    }//GEN-LAST:event_jbtn_luuActionPerformed

    private void jcb_hsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcb_hsItemStateChanged
        // TODO add your handling code here:
        LoadTen();
        ShowTT();
    }//GEN-LAST:event_jcb_hsItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        getselectData();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jRadio_hk1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadio_hk1MouseClicked
        // TODO add your handling code here:
        jRadio_hk1.setSelected(true);
        jRadio_hk2.setSelected(false);
    }//GEN-LAST:event_jRadio_hk1MouseClicked

    private void jRadio_hk2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadio_hk2MouseClicked
        // TODO add your handling code here:
        jRadio_hk1.setSelected(false);
        jRadio_hk2.setSelected(true);
    }//GEN-LAST:event_jRadio_hk2MouseClicked

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
            java.util.logging.Logger.getLogger(TinhDiemHocKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TinhDiemHocKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TinhDiemHocKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TinhDiemHocKy.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TinhDiemHocKy().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_tt;
    private javax.swing.JRadioButton jRadio_hk1;
    private javax.swing.JRadioButton jRadio_hk2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jText_ten;
    private javax.swing.JButton jbtn_luu;
    private javax.swing.JButton jbtn_them;
    private javax.swing.JButton jbtn_xoa;
    private javax.swing.JComboBox<String> jcb_hs;
    private javax.swing.JComboBox<String> jcb_lop;
    // End of variables declaration//GEN-END:variables
}
