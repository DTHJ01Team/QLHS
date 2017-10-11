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
import javafx.application.Application;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author User
 */
public class TNHS extends javax.swing.JInternalFrame {

    DbConnect con;
    int flag =0;

    /**
     * Creates new form TNHS
     */
    public TNHS() {
        initComponents();
        con = new DbConnect();
        showData();
        ClearText();
        HideTextBox(false);
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
        jText_Ngay.setText("mm/dd/yyyy");
        jRadio_Nam.setSelected(true);
        jText_Email.setText("----------------@gmail.com");
        jText_Dia.setText("");
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
        
        ResultSet rs = con.getData("SELECT * FROM HOCSINH");
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
    public void insertData (){
        String GioiTinh = "";
        ButtonModel bm = buttonGroup1.getSelection();
        if(jRadio_Nam.isSelected()){
            GioiTinh = jRadio_Nam.getText();
        }
        else{
            GioiTinh = jRadio_Nu.getText();
        
        }
        String[] stringSQL = {jText_MAHS.getText(),jText_Ten.getText(),jText_Ngay.getText(),GioiTinh,
                              jText_Email.getText(),jText_Dia.getText()};
        
        int insert = con.HOCSINH_Insert(stringSQL);
        if(insert > 0){
            JOptionPane.showMessageDialog(this,"Them thanh cong!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Them khong thanh cong!");
        }
        showData();
        ClearText();
    }
    
    public void updateData (){
        String GioiTinh = "";
        ButtonModel bm = buttonGroup1.getSelection();
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
        }
        else{
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jText_Ten = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jText_Email = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jText_Dia = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbtn_them = new javax.swing.JButton();
        jbtn_Luu = new javax.swing.JButton();
        jbtn_Xoa = new javax.swing.JButton();
        jbtn_Sua = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jText_Ngay = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadio_Nam = new javax.swing.JRadioButton();
        jRadio_Nu = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jText_MAHS = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Tiếp Nhận Thông Tin\n");

        jText_Ten.setMinimumSize(new java.awt.Dimension(6, 25));
        jText_Ten.setPreferredSize(new java.awt.Dimension(6, 25));
        jScrollPane2.setViewportView(jText_Ten);

        jText_Email.setMinimumSize(new java.awt.Dimension(6, 25));
        jText_Email.setPreferredSize(new java.awt.Dimension(6, 25));
        jScrollPane3.setViewportView(jText_Email);

        jText_Dia.setMinimumSize(new java.awt.Dimension(6, 25));
        jText_Dia.setPreferredSize(new java.awt.Dimension(6, 25));
        jScrollPane5.setViewportView(jText_Dia);

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

        jText_Ngay.setMinimumSize(new java.awt.Dimension(6, 25));
        jText_Ngay.setPreferredSize(new java.awt.Dimension(6, 25));
        jScrollPane4.setViewportView(jText_Ngay);

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
        jRadio_Nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadio_NamActionPerformed(evt);
            }
        });

        jRadio_Nu.setText("Nữ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Mã HS");

        jText_MAHS.setMinimumSize(new java.awt.Dimension(6, 25));
        jText_MAHS.setPreferredSize(new java.awt.Dimension(6, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 711, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jScrollPane2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(42, 42, 42)
                                    .addComponent(jText_MAHS, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jbtn_them)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_Sua, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jRadio_Nam)
                                .addGap(46, 46, 46)
                                .addComponent(jRadio_Nu))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3))))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_Luu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_them)
                    .addComponent(jbtn_Sua)
                    .addComponent(jbtn_Luu)
                    .addComponent(jbtn_Xoa))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
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
        HideTextBox(true);
        jbtn_Luu.setEnabled(true);
        flag = 3;
        jbtn_them.setEnabled(false);
        jbtn_Sua.setEnabled(false);
        jbtn_Xoa.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn_XoaActionPerformed

    private void jbtn_SuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_SuaActionPerformed
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
            insertData();
            jbtn_them.setEnabled(true);
            jbtn_Xoa.setEnabled(true);
            jbtn_Sua.setEnabled(true);
            
        }
        else if(flag == 2){
            updateData();            
            jbtn_them.setEnabled(true);
            jbtn_Xoa.setEnabled(true);
            jbtn_Sua.setEnabled(true);
        }
        else{
            deleteData();            
            jbtn_them.setEnabled(true);
            jbtn_Xoa.setEnabled(true);
            jbtn_Sua.setEnabled(true);
        }
        ClearText();
        HideTextBox(false);
        jbtn_Luu.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtn_LuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadio_Nam;
    private javax.swing.JRadioButton jRadio_Nu;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextPane jText_Dia;
    private javax.swing.JTextPane jText_Email;
    private javax.swing.JTextField jText_MAHS;
    private javax.swing.JTextPane jText_Ngay;
    private javax.swing.JTextPane jText_Ten;
    private javax.swing.JButton jbtn_Luu;
    private javax.swing.JButton jbtn_Sua;
    private javax.swing.JButton jbtn_Xoa;
    private javax.swing.JButton jbtn_them;
    // End of variables declaration//GEN-END:variables
}
