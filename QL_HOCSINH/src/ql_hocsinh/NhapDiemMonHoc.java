/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import Process.checkSymbol;
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
public class NhapDiemMonHoc extends javax.swing.JInternalFrame {
    DbConnect con;
    int flag = 0;
    /**
     * Creates new form NhapDiemMonHoc
     */
    public NhapDiemMonHoc() {
        initComponents();
        con = new DbConnect();
        LoadDataCB();
        jText_ten.setEnabled(false);
        jText_mon.setEnabled(false);
        jText_d15.setEnabled(false);
        jText_d45.setEnabled(false);
        jcb_lop.setEnabled(false);
        UpdateDTB();
        showData();
        ShowTT();
        LoadTen();
    }
    
    public void ClearText (){
        jcb_hs.setSelectedIndex(0);
        jcb_mon.setSelectedIndex(0);
        jText_d15.setText("");
        jText_d45.setText("");
    }
    
    public void LoadDataCB(){
        try{
            ResultSet rs = con.getData("SELECT * from MONHOC");
            ResultSet rss = con.getData("SELECT * FROM HOCSINH");
            ResultSet rsss = con.getData("SELECT * FROM LOP");
            while (rs.next()){
                this.jcb_mon.addItem(rs.getString("MAMH"));
            }
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
    
    public void LoadMon (){
        try {
            ResultSet rs = con.getData ("SELECT * FROM MONHOC WHERE MAMH LIKE N'%"+ jcb_mon.getSelectedItem().toString() +"%'");
            while (rs.next()){
                    jText_mon.setText(rs.getString("Ten"));
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, ex, "Thông báo lỗi",1);
        }
    }
    
    
    public void UpdateDTB (){
        String[] stringSQL = {};
        con.DTB_Update(stringSQL);
    }
    
    public void ShowTT (){
        jLabel_tt.setText("Hiện có "+jTable1.getRowCount()+" học sinh!");
    }
        
    public void showData (){
        String[] columnNames = {"MAHS","Họ tên","Học kỳ","Mã môn","Điểm 15'","Điểm 45'","DTB môn"};
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(columnNames);
        jTable1.setModel(model);
        
        String MA ="";
        String Ten = "";
        String HK = "";
        String MH = "";
        String d15  = "";
        String d45 = "";
        String DTB = "";
        
        ResultSet rs = con.getData("select h.MAHS, h.HoTen, d.MAHK, m.MAMH, d.[15P], d.[45P], DTBM = CONVERT(decimal(5,2),D.DTBM) from HOCSINH H, DIEMKIEMTRA D, MONHOC M where h.MAHS = d.MAHS and D.MAMH = m.MAMH");        
        try {
            while (rs.next()){
                MA = rs.getString("MAHS");
                Ten = rs.getString("HoTen");
                HK = rs.getString("MAHK");
                MH = rs.getString("MAMH");
                d15 = rs.getString("15P");
                d45 = rs.getString("45P");
                DTB = rs.getString("DTBM");
                
                model.addRow(new Object[] {MA,Ten,HK,MH,d15,d45,DTB});
            }
        } catch (SQLException ex) {
            Logger.getLogger(MainTheme.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void InsertData(){ 
        String HK = "";
        if(jRadio_hk1.isSelected())
            HK = "HK1";
        else
            HK = "HK2";
        String[] stringSQL = {jcb_hs.getSelectedItem().toString(),jcb_mon.getSelectedItem().toString(),HK,jText_d15.getText(),jText_d45.getText()};
        
        int insert = con.ND_Insert(stringSQL);
        if(insert > 0){
            JOptionPane.showMessageDialog(this,"Thêm thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Thêm không thành công!");
        }
        UpdateDTB();
        showData();
    }
    
    public void updateData (){
        String HK = "";
        if(jRadio_hk1.isSelected())
            HK = "HK1";
        else
            HK = "HK2";
        
        String[] stringSQL = {jText_d15.getText(),jText_d45.getText(),jcb_hs.getSelectedItem().toString(),jcb_mon.getSelectedItem().toString(), HK};
        
        int update = con.ND_Update(stringSQL);
        if(update > 0){
            JOptionPane.showMessageDialog(this,"Sửa thành công!");
        }
        else {
            JOptionPane.showMessageDialog(this,"Sửa không thành công!");
        }
        UpdateDTB();
        showData();
    }
    
    public void deleteData (){
        String HK = "";
        if(jRadio_hk1.isSelected())
            HK = "HK1";
        else
            HK = "HK2";
        
        String[] stringSQL = {jcb_hs.getSelectedItem().toString(),jcb_mon.getSelectedItem().toString(), HK};
        
        int delete = con.ND_Delete(stringSQL);
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
        jcb_hs.setSelectedItem(jTable1.getValueAt(selectRow, 0));
        jText_ten.setText(jTable1.getValueAt(selectRow, 1).toString());
        if (jTable1.getValueAt(selectRow,2).toString().equals("HK1")){
            jRadio_hk1.setSelected(true);
            jRadio_hk2.setSelected(false);
        }
        else{
            jRadio_hk1.setSelected(false);
            jRadio_hk2.setSelected(true);
        }
        jcb_mon.setSelectedItem(jTable1.getValueAt(selectRow,3));
        jText_d15.setText(jTable1.getValueAt(selectRow,4).toString());
        jText_d45.setText(jTable1.getValueAt(selectRow,5).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadio_hk2 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jText_ten = new javax.swing.JTextField();
        jcb_lop = new javax.swing.JComboBox<>();
        jcb_mon = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jRadio_hk1 = new javax.swing.JRadioButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jText_d15 = new javax.swing.JTextField();
        jText_d45 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel_tt = new javax.swing.JLabel();
        jbtn_them = new javax.swing.JButton();
        jbtn_sua = new javax.swing.JButton();
        jbtn_xoa = new javax.swing.JButton();
        jbtn_luu = new javax.swing.JButton();
        jcb_hs = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jText_mon = new javax.swing.JTextField();

        setClosable(true);

        jRadio_hk2.setText("II");
        jRadio_hk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_hk2MouseClicked(evt);
            }
        });

        jLabel2.setText("Họ Tên");

        jLabel5.setText("Lớp");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Họ Tên", "Học kỳ", "Môn học", "Điểm 15'", "Điểm 45'", "Điểm TB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
        jLabel6.setText("Bảng Điểm Môn Học");

        jLabel1.setText("Học Kỳ");

        jcb_lop.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jcb_lop.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jcb_mon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jcb_mon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcb_monItemStateChanged(evt);
            }
        });
        jcb_mon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_monActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã môn");

        jRadio_hk1.setText("I");
        jRadio_hk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadio_hk1MouseClicked(evt);
            }
        });

        jLabel7.setText("Điểm 15'");

        jLabel8.setText("Điểm 1 tiết");

        jLabel3.setText("Mã học sinh");

        jLabel_tt.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel_tt.setText("jLabel9");

        jbtn_them.setText("Thêm");
        jbtn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_themActionPerformed(evt);
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
        jcb_hs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_hsActionPerformed(evt);
            }
        });

        jLabel9.setText("Tên môn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel_tt))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jbtn_them)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jRadio_hk1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcb_hs, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jRadio_hk2)
                                .addComponent(jcb_mon, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(28, 28, 28))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jText_d45, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jText_d15, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jText_ten, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jText_mon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jText_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jcb_hs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jText_mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcb_mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jText_d15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadio_hk1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadio_hk2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(jText_d45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jcb_lop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtn_them)
                    .addComponent(jbtn_sua)
                    .addComponent(jbtn_xoa)
                    .addComponent(jbtn_luu))
                .addGap(8, 8, 8)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_tt)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcb_monActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_monActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_monActionPerformed

    private void jcb_hsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcb_hsItemStateChanged
        // TODO add your handling code here:
        LoadTen();
        ShowTT();
    }//GEN-LAST:event_jcb_hsItemStateChanged

    private void jbtn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_themActionPerformed
        // TODO add your handling code here:
        ClearText();
        jbtn_luu.setEnabled(true);
        flag = 1;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
        jText_d15.setEnabled(true);
        jText_d45.setEnabled(true);
    }//GEN-LAST:event_jbtn_themActionPerformed

    private void jbtn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_suaActionPerformed
        // TODO add your handling code here:
        jbtn_luu.setEnabled(true);
        flag = 2;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
        jText_d15.setEnabled(true);
        jText_d45.setEnabled(true);
    }//GEN-LAST:event_jbtn_suaActionPerformed

    private void jbtn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_xoaActionPerformed
        // TODO add your handling code here:
        jbtn_luu.setEnabled(true);
        flag = 3;
        jbtn_them.setEnabled(false);
        jbtn_sua.setEnabled(false);
        jbtn_xoa.setEnabled(false);
        
    }//GEN-LAST:event_jbtn_xoaActionPerformed

    private void jbtn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_luuActionPerformed
        // TODO add your handling code here:
        if(flag == 1){
            if(this.jText_d15.getText().length() == 0)
                JOptionPane.showMessageDialog(null," Điểm không được để trống!","Thông báo lỗi!", 1);
            else               
                  if(checkSymbol.checkNum(this.jText_d15.getText())==false)
                           JOptionPane.showMessageDialog(null, "Phát hiện có ký tự-không phải là số!","Thông báo lỗi!", 1);
            else                  
                         if(checkSymbol.checkSb(this.jText_d15.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else
                             if(this.jText_d45.getText().length() == 0)
                JOptionPane.showMessageDialog(null," Điểm không được để trống!","Thông báo lỗi!", 1);
            else               
                  if(checkSymbol.checkNum(this.jText_d45.getText())==false)
                           JOptionPane.showMessageDialog(null, "Phát hiện có ký tự-không phải là số!","Thông báo lỗi!", 1);
            else                  
                         if(checkSymbol.checkSb(this.jText_d45.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else                             
            InsertData();
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
            jbtn_sua.setEnabled(true);    
        }
        else if(flag == 2){
            if(this.jText_d15.getText().length() == 0)
                JOptionPane.showMessageDialog(null," Điểm không được để trống!","Thông báo lỗi!", 1);
            else               
                  if(checkSymbol.checkNum(this.jText_d15.getText())==false)
                           JOptionPane.showMessageDialog(null, "Phát hiện có ký tự-không phải là số!","Thông báo lỗi!", 1);
            else                  
                         if(checkSymbol.checkSb(this.jText_d15.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else
                             if(this.jText_d45.getText().length() == 0)
                JOptionPane.showMessageDialog(null," Điểm không được để trống!","Thông báo lỗi!", 1);
            else               
                  if(checkSymbol.checkNum(this.jText_d45.getText())==false)
                           JOptionPane.showMessageDialog(null, "Phát hiện có ký tự-không phải là số!","Thông báo lỗi!", 1);
            else                  
                         if(checkSymbol.checkSb(this.jText_d45.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else
            updateData();            
            jbtn_them.setEnabled(true);
            jbtn_xoa.setEnabled(true);
            jbtn_sua.setEnabled(true);
        }
        else{           
           if(this.jText_ten.getText().length() == 0)
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
        jbtn_luu.setEnabled(false);
        jText_d15.setEnabled(false);
        jText_d45.setEnabled(false);
    }//GEN-LAST:event_jbtn_luuActionPerformed

    private void jcb_monItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcb_monItemStateChanged
        // TODO add your handling code here:
        LoadMon();
    }//GEN-LAST:event_jcb_monItemStateChanged

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

    private void jcb_hsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_hsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcb_hsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel_tt;
    private javax.swing.JRadioButton jRadio_hk1;
    private javax.swing.JRadioButton jRadio_hk2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jText_d15;
    private javax.swing.JTextField jText_d45;
    private javax.swing.JTextField jText_mon;
    private javax.swing.JTextField jText_ten;
    private javax.swing.JButton jbtn_luu;
    private javax.swing.JButton jbtn_sua;
    private javax.swing.JButton jbtn_them;
    private javax.swing.JButton jbtn_xoa;
    private javax.swing.JComboBox<String> jcb_hs;
    private javax.swing.JComboBox<String> jcb_lop;
    private javax.swing.JComboBox<String> jcb_mon;
    // End of variables declaration//GEN-END:variables
}
