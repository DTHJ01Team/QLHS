/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql_hocsinh;

import Process.checkSymbol;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AnhQuoc
 */
public class TraCuuHocSinh extends javax.swing.JInternalFrame {
    DbConnect con;
    /**
     * Creates new form TraCuuHocSinh
     */
    public TraCuuHocSinh() {
        initComponents();
        con = new DbConnect();
        ShowTT();
    }
    
    public void showData (){
        String[] columnNames = {"Họ tên","Lớp","Điểm HK1","Điểm HK2"};
        DefaultTableModel model = new DefaultTableModel();
        
        model.setColumnIdentifiers(columnNames);
        jTable2.setModel(model);
        
        String Ten = "";
        String Lop = "";
        String HK1 = "";
        String HK2 = "";
        ResultSet rs = con.getData("select H.HoTen, L.MALOP, [HK1] = (select DTBHK from DIEMHOCKY D, HOCSINH H where D.MAHS = H.MAHS and H.HoTen like N'%"+jTextField_ten.getText()+"%' and MAHK like 'HK1'), [HK2] = (select DTBHK from DIEMHOCKY D, HOCSINH H where D.MAHS = H.MAHS and H.HoTen like N'%"+jTextField_ten.getText()+"%' and MAHK like 'HK2')\n" +
                                    "from LOP L, DIEMHOCKY D, HOCSINH H\n" +
                                    "where L.MALOP = D.MALOP and D.MAHS = H.MAHS and H.HoTen like N'%"+jTextField_ten.getText()+"%'\n" +
                                    "group by H.HoTen, L.MALOP");
        try {
            while (rs.next()){
                Ten= rs.getString("HoTen");
                Lop = rs.getString("MALOP");
                HK1 = rs.getString("HK1");
                HK2 = rs.getString("HK2");
                model.addRow(new Object[] {Ten,Lop,HK1,HK2});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Thông báo lỗi!", 1);
        }
        
    }
    
    public void ShowTT (){
        jLabel_tt.setText("Hiện có "+jTable2.getRowCount()+" học sinh!");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtn_tim = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField_ten = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel_tt = new javax.swing.JLabel();

        setClosable(true);

        jbtn_tim.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jbtn_tim.setText("Tìm");
        jbtn_tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtn_timActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Họ Tên", "Lớp", "TB Học Kỳ I", "TB Học Kỳ II"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Họ Tên");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Danh Sách Học Sinh");

        jTextField_ten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_tenKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_tenKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("TRA CỨU HỌC SINH");

        jLabel_tt.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel_tt.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(80, 80, 80)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(38, 38, 38))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbtn_tim, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(121, 121, 121))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(86, 86, 86))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_tt)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jbtn_tim)
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_tt)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtn_timActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtn_timActionPerformed
         if(this.jTextField_ten.getText().length() == 0)
                                JOptionPane.showMessageDialog(null,"Tên học sinh không được để trống!","Thông báo lỗi!", 1);
            else
                         if(checkSymbol.checkSb(this.jTextField_ten.getText())==true)
                                      JOptionPane.showMessageDialog(null, "Phát hiện có ký tự đặc biệt!","Thông báo lỗi!", 1);
            else
                                      if(checkSymbol.checkNum(this.jTextField_ten.getText())==true)
                                            JOptionPane.showMessageDialog(null, "Phát hiện có số-không phải là ký tự!","Thông báo lỗi!", 1);
            else                                                
        // TODO add your handling code here:
        ShowTT();
        showData();
    }//GEN-LAST:event_jbtn_timActionPerformed

    private void jTextField_tenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_tenKeyPressed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_jTextField_tenKeyPressed

    private void jTextField_tenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_tenKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField_tenKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel_tt;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField_ten;
    private javax.swing.JButton jbtn_tim;
    // End of variables declaration//GEN-END:variables
}
