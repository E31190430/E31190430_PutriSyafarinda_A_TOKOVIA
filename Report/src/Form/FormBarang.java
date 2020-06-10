/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author WhoAmI
 */
public class FormBarang extends javax.swing.JFrame {

    public FormBarang() {
        initComponents();
        tampil_combo(); //Menampilkan data ke combo box
        load_table();
        lebarKolom();
        tmbol_pasif();
    }
     public void lebarKolom(){
         TableColumn column;
         tbBarang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
         column = tbBarang.getColumnModel().getColumn(0);
         column.setPreferredWidth(40);
         column = tbBarang.getColumnModel().getColumn(1);
         column.setPreferredWidth(70);
         column = tbBarang.getColumnModel().getColumn(2);
         column.setPreferredWidth(150);
         column = tbBarang.getColumnModel().getColumn(3);
         column.setPreferredWidth(60);
         column = tbBarang.getColumnModel().getColumn(4);
         column.setPreferredWidth(80);
         column = tbBarang.getColumnModel().getColumn(5);
         column.setPreferredWidth(50);
         column = tbBarang.getColumnModel().getColumn(6);
         column.setPreferredWidth(120);
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_kdbarang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nmbarang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JSatuan = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        JMakanan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt_stok = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_harga = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBarang = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btn_simpan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        kdbrng = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(255, 153, 153));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Kode Barang");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Satuan Barang");

        JSatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Satuan/Pcs", "Botol", "PCS", "KG", "Buah", "Lembar" }));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Kategori Barang");

        JMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMakananActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Stok Barang");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Harga Barang ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(JSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txt_kdbarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_nmbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(105, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_kdbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nmbarang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(JMakanan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tbBarang.setBackground(new java.awt.Color(153, 153, 255));
        tbBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBarang);

        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        txt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cariKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel8.setText("Pencarian");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txt_cari)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        btn_simpan.setBackground(new java.awt.Color(255, 255, 255));
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setText("BATAL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btn_edit.setBackground(new java.awt.Color(255, 255, 255));
        btn_edit.setText("EDIT");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_hapus.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        kdbrng.setEditable(false);
        kdbrng.setForeground(new java.awt.Color(255, 255, 255));
        kdbrng.setCaretColor(new java.awt.Color(255, 255, 255));
        kdbrng.setEnabled(false);
        kdbrng.setFocusable(false);
        kdbrng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kdbrngActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(kdbrng, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(kdbrng, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setBackground(new java.awt.Color(255, 153, 153));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM BARANG");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        simpan_data();
    }//GEN-LAST:event_btn_simpanActionPerformed
    //Simpan Data
    private void simpan_data(){
       
        if (txt_nmbarang.equals("") || txt_kdbarang.equals("") || txt_stok.equals("") || txt_harga.equals("") || JSatuan.getSelectedItem() == "Satuan/Pcs" || JMakanan.getSelectedIndex()==0 ) {
            JOptionPane.showMessageDialog(null, "Maaf Data Harus Diisi... ");
            txt_kdbarang.requestFocus();
            loadData();
        } else {
                   try {
                    String sql = "INSERT INTO tabel_barang VALUES ('"+txt_kdbarang.getText()+"','"
                        +txt_nmbarang.getText()+"','"
                        +JSatuan.getSelectedItem()+"','"
                        +txt_stok.getText()+"','"
                        +txt_harga.getText()+"','"
                        +kdbrng.getText()+"')";
                    Connection con = config.conn.configDB();
                    Statement stt = con.createStatement();
                    int res = stt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this, "Penyimpanan Data Berhasil ");
                    load_table();
                    lebarKolom();
        } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
        }
            loadData(); 
        }
 
      
//            try {
//                    String sql = "UPDATE tabel_barang SET stok_barang = stok_barang +'"+Jml_stok.getText()+"' WHERE kode_barang = '"+txt_kdbarang.getText()+"'";
//                    Connection con = config.conn.configDB();
//                    Statement stt = con.createStatement();
//                    int res = stt.executeUpdate(sql);
//                    JOptionPane.showMessageDialog(this, "Penyimpanan Data Berhasil Ditambah ");
//                    load_table();
//                    lebarKolom();
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//              loadData();
       
  
        
   }
   //Edit Data
    private void edit_data(){
        try {
                    String sql = "UPDATE tabel_barang set nama_barang = '"+txt_nmbarang.getText()+
                            "', satuan_barang='"+JSatuan.getSelectedItem()+
                            "',stok_barang='"+txt_stok.getText()+
                            "',harga_barang='"+txt_harga.getText()+
                            "',kode_kategori='"+kdbrng.getText()+
                            "'WHERE kode_barang='"+txt_kdbarang.getText()+"'";
                    Connection con = config.conn.configDB();
                    Statement stt = con.createStatement();
                    int res = stt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(this, "Data Berhasil Diganti ");
                    load_table();
                    loadData();
                    txt_kdbarang.setEnabled(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //Hapus Data
    private void hapus_data(){
        try {
            String sql = "DELETE FROM tabel_barang WHERE kode_barang ='"+txt_kdbarang.getText()+"'";
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            int res = stt.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Data Berhasil Dihapus ");
            load_table();
            loadData();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

    private void JMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMakananActionPerformed
     tampil();
    }//GEN-LAST:event_JMakananActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void tbBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBarangMouseClicked
       int baris = tbBarang.getSelectedRow();
       txt_kdbarang.setEnabled(false);
       txt_kdbarang.setText(tbBarang.getModel().getValueAt(baris, 1).toString());
       txt_nmbarang.setText(tbBarang.getModel().getValueAt(baris, 2).toString());
       JSatuan.setSelectedItem(tbBarang.getModel().getValueAt(baris, 3).toString());
       JMakanan.setSelectedItem(tbBarang.getModel().getValueAt(baris, 4).toString());
       txt_stok.setText(tbBarang.getModel().getValueAt(baris, 5).toString());
       txt_harga.setText(tbBarang.getModel().getValueAt(baris, 6).toString());
       tmbol_aktif();
    }//GEN-LAST:event_tbBarangMouseClicked

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
       
        if (txt_nmbarang.equals("") || txt_kdbarang.equals("") || txt_stok.equals("") || txt_harga.equals("") || JSatuan.getSelectedItem() == "Satuan/Pcs" || JMakanan.getSelectedIndex()==0 ) {
            JOptionPane.showMessageDialog(null, "Maaf Data Harus dipilih terlebih dahulu... ");
        } else {
            int selectedOption = JOptionPane.showConfirmDialog(null,
            "Apakah Data Benar Diganti ? ?", "Edit Data", JOptionPane.YES_NO_OPTION);
                    if (selectedOption == JOptionPane.YES_OPTION) {
                        edit_data();
                        lebarKolom();
                        tmbol_pasif();
                    }else{
                        loadData();
                    }
         }       
    }//GEN-LAST:event_btn_editActionPerformed

    private void kdbrngActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kdbrngActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbrngActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       loadData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
  
    }//GEN-LAST:event_txt_cariActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
         if (txt_nmbarang.equals("") || txt_kdbarang.equals("") || txt_stok.equals("") || txt_harga.equals("") || JSatuan.getSelectedItem() == "Satuan/Pcs" || JMakanan.getSelectedIndex()==0 ) {
            JOptionPane.showMessageDialog(null, "Maaf Data Harus dipilih terlebih dahulu... ");
        } else {
            int selectedOption = JOptionPane.showConfirmDialog(null,
            "Apakah Data Benar Dihapus ? ?", "Hapus Data", JOptionPane.YES_NO_OPTION);
                if (selectedOption == JOptionPane.YES_OPTION) {
                    hapus_data();
                    lebarKolom();
                    tmbol_pasif();
                }else{
                    loadData();
                }
        } 
    
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void txt_cariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cariKeyPressed
       data_cari();
       lebarKolom();
    }//GEN-LAST:event_txt_cariKeyPressed
   private void data_cari(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan");
        model.addColumn("Kategori");
        model.addColumn("Stok");
        model.addColumn("Harga Barang");
        try {
           int  no = 1;
           Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
 
            String sql = "SELECT tabel_barang.kode_barang, tabel_barang.nama_barang, tabel_kategori.nama_kategori, tabel_barang.satuan_barang, tabel_barang.stok_barang, tabel_barang.harga_barang FROM tabel_barang, tabel_kategori WHERE tabel_kategori.kode_kategori=tabel_barang.kode_kategori AND (nama_barang like '%"+txt_cari.getText()+"%' or kode_barang like '%"+txt_cari.getText()+"%')";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {                
                model.addRow(new Object[]{
                    no++,
                    res.getString("kode_barang"),
                    res.getString("nama_barang"),
                    res.getString("satuan_barang"),
                    res.getString("nama_kategori"),
                    res.getString("stok_barang"),
                    res.getString("harga_barang"),
                });
            }
            
            tbBarang.setModel(model);
        
        } catch (Exception e) {
       }
   }
    //Load Data menjadi kosong
    public void loadData(){
        txt_kdbarang.setText("");
        txt_nmbarang.setText("");
        JSatuan.setSelectedIndex(0);
        JMakanan.setSelectedIndex(0);
        txt_stok.setText("");
        txt_harga.setText("");
//        Jml_stok.setText("");
        txt_kdbarang.setEnabled(true);
        txt_nmbarang.setEnabled(true);
        JSatuan.setEnabled(true);
        JMakanan.setEnabled(true);
        txt_harga.setEnabled(true);
        txt_stok.setEnabled(true);
//        Jml_stok.setEnabled(false);
        txt_kdbarang.requestFocus();
    }
    public void tampil_combo(){
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "SELECT nama_kategori from tabel_kategori order by kode_kategori asc";
            ResultSet res = stt.executeQuery(sql);
            JMakanan.addItem("--Kategori--");
            while (res.next()) {                
                Object[] ob = new Object[2];
                ob[0] = res.getString(1);
                JMakanan.addItem((String) ob[0]);
                
            }
            res.close(); stt.close();
        } catch (Exception e) {
        }
    }
    //menampilkan data dari combo box
    public void tampil(){
           
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "SELECT kode_kategori from tabel_kategori where nama_kategori='"+JMakanan.getSelectedItem()+"'";
             ResultSet res = stt.executeQuery(sql);
           
            while (res.next()) {                
                Object[] ob = new Object[1];
                ob[0] = res.getString(1);
                
                kdbrng.setText((String) ob[0]);
            }
            res.close(); stt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void load_table(){
        //Membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("No");
        model.addColumn("Kode");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan");
        model.addColumn("Kategori");
        model.addColumn("Stok");
        model.addColumn("Harga Barang");
        
        //Menampilkan data database ke dalam tabel
        try {
            int no = 1; 
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "SELECT tabel_barang.kode_barang, tabel_barang.nama_barang, tabel_kategori.nama_kategori, tabel_barang.satuan_barang, tabel_barang.stok_barang, tabel_barang.harga_barang FROM tabel_barang, tabel_kategori WHERE tabel_kategori.kode_kategori=tabel_barang.kode_kategori";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {                
                model.addRow(new Object[]{
                    no++,
                    res.getString("kode_barang"),
                    res.getString("nama_barang"),
                    res.getString("satuan_barang"),
                    res.getString("nama_kategori"),
                    res.getString("stok_barang"),
                    res.getString("harga_barang"),
                });
            }
            tbBarang.setModel(model);
        } catch (Exception e) {
        }
    }
    private void tmbol_aktif(){
        btn_edit.setEnabled(true);
        btn_hapus.setEnabled(true);
        btn_simpan.setEnabled(false);
    }
    private void tmbol_pasif(){
        btn_edit.setEnabled(false);
        btn_hapus.setEnabled(false);
        btn_simpan.setEnabled(true);
//        Jml_stok.setEnabled(false);
    }
   
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
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JMakanan;
    private javax.swing.JComboBox<String> JSatuan;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kdbrng;
    private javax.swing.JTable tbBarang;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_harga;
    private javax.swing.JTextField txt_kdbarang;
    private javax.swing.JTextField txt_nmbarang;
    private javax.swing.JTextField txt_stok;
    // End of variables declaration//GEN-END:variables
}
