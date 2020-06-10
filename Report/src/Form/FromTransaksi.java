/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author riskie
 */
public class FromTransaksi extends javax.swing.JFrame {
    public String kodebarang, nama_barang, satuan_barang, kategori_barang, kdbarang, stok, harga;
    private DefaultTableModel model;
    public String getKodebarang() {
        return kodebarang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public String getSatuan_barang() {
        return satuan_barang;
    }

    public String getKategori_barang() {
        return kategori_barang;
    }

    public String getKdbarang() {
        return kdbarang;
    }

    public String getStok() {
        return stok;
    }
     public void itemTerpilih(){
        Form_tblBarang tbl_barang = new Form_tblBarang();
        tbl_barang.tambah_barang = this;
        txt_kodeBarang.setText(kodebarang);
        lbl_namaBarang.setText(nama_barang);
        lbl_satuanBarang.setText(satuan_barang);
        lbl_kategori.setText(kategori_barang);
        lbl_stokBarang.setText(stok);
        lbl_harga.setText(harga);
//        txt_kdbarang.setEnabled(false);
//        txt_nmbarang.setEnabled(false);
//        JSatuan.setEnabled(false);
//        JMakanan.setEnabled(false);
//        txt_harga.setEnabled(false);
//        txt_stok.setEnabled(false);
        txt_jumlah.requestFocus();
    }
    /**
     * Creates new form FromTransaksi
     */
    public FromTransaksi() {
        initComponents();
        txt_kodeTransaksi.setEnabled(false);
        otomatis();
        txt_kodeBarang.requestFocus();
//        txt_tanggal.hide();
//        
        model = new DefaultTableModel();
        tbl_transaksi.setModel(model);
        model.addColumn("Kode Transaksi");
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Satuan Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Sub_Total");
        model.addColumn("Tanggal");
       
        load_data();
        
    }
    public void tambah_detail(){
        Date hariIni = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
        
        String no_struk = this.txt_kodeTransaksi.getText();
        String kode_barang = this.txt_kodeBarang.getText();
        String stok = this.lbl_stokBarang.getText();
        String jumlah_stok = this.txt_jumlah.getText();
        String subtotal = this.txt_subtotal.getText();
        String tanggal = ft.format(hariIni);
          
       
            try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "INSERT into tabel_infotransaksi (kode_transaksi, jumlah_stok, sub_total, tanggal, kode_barang) values (?,?,?,?,?)";
            PreparedStatement ps = (PreparedStatement)con.prepareStatement(sql);
            ps.setString(1, no_struk);
            ps.setString(2, jumlah_stok);
            ps.setString(3, subtotal);
            ps.setString(4, tanggal);
            ps.setString(5, kode_barang);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan"+e);
        }
    }
    
    public final void load_data(){
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "SELECT * from tabel_infotransaksi, tabel_barang WHERE tabel_infotransaksi.kode_barang = tabel_barang.kode_barang AND tabel_infotransaksi.kode_transaksi='"+this.txt_kodeTransaksi.getText()+"'";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {
            Object[] ob = new Object[8];
            ob [0] = res.getString("kode_transaksi");
            ob [1] = res.getString("kode_barang");
            ob [2] = res.getString("nama_barang");
            ob [3] = res.getString("satuan_barang");
            ob [4] = res.getString("harga_barang");
            ob [5] = res.getString("jumlah_stok");
            ob [6] = res.getString("sub_total");
            ob [7] = res.getString("tanggal");
            model.addRow(ob);    
            }
            res.close();stt.close();
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan"+e);
        }
        
        int total = 0;
        for (int i = 0; i < tbl_transaksi.getRowCount(); i++) {
            int amount = Integer.parseInt((String)tbl_transaksi.getValueAt(i, 6));
            total += amount;
        }
        txt_total.setText(""+total);
    }
    public void update_stok(){
        int x, y, z;
        x = Integer.parseInt(lbl_stokBarang.getText());
        y = Integer.parseInt(txt_jumlah.getText());
        z = x-y;
        String kode_barang = this.txt_kodeBarang.getText();
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "UPDATE tabel_barang set stok_barang =? WHERE kode_barang=?";
            PreparedStatement ps = (PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1, z);
            ps.setString(2, kode_barang);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan"+e);
        }
    }
    public void AutoSum(){
        int a, b, c;
       
            a = Integer.parseInt(lbl_harga.getText());
            b =  Integer.parseInt(txt_jumlah.getText());
            c = a*b;
            txt_subtotal.setText(""+c);
        
            }
    public void cari_kode(){
        int i = tbl_transaksi.getSelectedRow();
        if (i ==-1) {
            return;
        }
        String kode_barang = (String)model.getValueAt(i, 1);
        txt_kodeBarang.setText(kode_barang);
    }
    
    public void show_data(){
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "SELECT * from tabel_infotransaksi, tabel_barang, tabel_kategori WHERE tabel_infotransaksi.kode_barang = tabel_barang.kode_barang AND tabel_barang.kode_kategori=tabel_kategori.kode_kategori AND tabel_infotransaksi.kode_barang='"+this.txt_kodeBarang.getText()+"'";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {                
             this.lbl_namaBarang.setText(res.getString("nama_barang"));
             this.lbl_satuanBarang.setText(res.getString("satuan_barang"));
             this.lbl_kategori.setText(res.getString("nama_kategori"));
             this.lbl_harga.setText(res.getString("harga_barang"));
             this.txt_subtotal.setText(res.getString("sub_total"));
             this.lbl_stokBarang.setText(res.getString("stok_barang"));
             this.txt_jumlah.setText(res.getString("jumlah_stok"));
             this.txt_tanggal.setText(res.getString("tanggal"));
             
            }
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan" +e);
        }
    }
    
    public void show_sisa(){
        try {     
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "SELECT * from tabel_barang WHERE kode_barang = '"+this.txt_kodeBarang.getText()+"'";
            ResultSet res  = stt.executeQuery(sql);
            while (res.next()) {                
                this.lbl_stokBarang.setText(res.getString("stok_barang"));
                
            }
            res.close(); stt.close();
        } catch (Exception e) {
        }
    }
    public void Batal(){
        int x, y, z;
        x = Integer.parseInt(lbl_stokBarang.getText());
        y = Integer.parseInt(txt_jumlah.getText());
        z = x+y;
        String kode_barang = this.txt_kodeBarang.getText();
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "UPDATE tabel_barang set stok_barang=? WHERE kode_barang=?";
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setInt(1, z);
            ps.setString(2, kode_barang);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan"+e);
        }
        
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "DELETE from tabel_infotransaksi WHERE kode_transaksi='"+this.txt_kodeTransaksi.getText()+"' AND tanggal ='"+this.txt_tanggal.getText()+"'";
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan"+e);
        }finally{
            load_data();
            JOptionPane.showMessageDialog(this,"Sukses Hapus Data...");
        }
    }
    
   public void hitung_kembali(){
       int d, e, f;
       d = Integer.parseInt(txt_total.getText());
       e = Integer.parseInt(txt_bayar.getText());
       if (e<d) {
           JOptionPane.showMessageDialog(this,"Maaf Lur Uangnya Kurang");
           txt_bayar.requestFocus();
           txt_bayar.setText("");
       } else {
           f = e-d;
           txt_kembali.setText(""+f);
       }
       
   }
    public void simpan(){
        String kode_transaksi = this.txt_kodeTransaksi.getText();
        String total = this.txt_total.getText();
        String bayar = this.txt_bayar.getText();
        String kembalian = this.txt_kembali.getText();
        
        
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tanggal = new Date();
        String tanggal_jual = dateFormat.format(tanggal);
        
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql = "INSERT into tabel_transaksi (kode_transaksi, tanggal_jual, total, cash, kembali) values (?, ?, ?, ?, ?)";
            PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
            ps.setString(1, kode_transaksi);
            ps.setString(2, tanggal_jual);
            ps.setString(3, total);
            ps.setString(4, bayar);
            ps.setString(5, kembalian);
            ps.executeUpdate();
           
            
            int t = tbl_transaksi.getRowCount();
            for(int i=0; i < t; i++){
               String kd_transaksi = tbl_transaksi.getValueAt(i, 0).toString();
               String kodeBarang = tbl_transaksi.getValueAt(i, 1).toString();
               String jml = tbl_transaksi.getValueAt(i, 5).toString();
               String sub_total  = tbl_transaksi.getValueAt(i, 6).toString();
               String tgl = tbl_transaksi.getValueAt(i, 7).toString();
               
               
               Connection conn = config.conn.configDB();
               Statement st = conn.createStatement();
               String query = "INSERT into tabel_detailtransaksi (kode_transaksi, jumlah_stok, subtotal, tanggal, kode_barang) values (?,?,?,?,?)";
               PreparedStatement pst=(PreparedStatement)conn.prepareStatement(query);
               pst.setString(1, kd_transaksi);
               pst.setString(2, jml);
               pst.setString(3, sub_total);
               pst.setString(4, tgl);
               pst.setString(5, kodeBarang);
               pst.executeUpdate();
               pst.close();
               
            }
      
        } catch (Exception e) {
            System.out.println("Terjadi Keasalahan"+e);
        }finally{
            JOptionPane.showMessageDialog(this,"Data Telah Tersimpan"); 
        }
        txt_bayar.setText("");
        txt_kembali.setText("");
        txt_total.setText("");
        txt_kodeBarang.requestFocus();
        delete_tabel();
        load_data();
        otomatis();   
    }
    private void delete_tabel(){
              try {
                Connection con = config.conn.configDB();
                Statement stt = con.createStatement();
                String sql = "DELETE from tabel_infotransaksi WHERE kode_transaksi='"+this.txt_kodeTransaksi.getText()+"'";
                PreparedStatement ps=(PreparedStatement)con.prepareStatement(sql);
                ps.executeUpdate();
                ps.close();
        } catch (Exception e) {
            System.out.println("Terjadi Kesalahan"+e);
        }finally{
        }
    }
    private void cetak_nota(){
        try {
            Connection con = config.conn.configDB();
            String file = "src/Report/laporan_transaksi.jasper";
            HashMap param = new HashMap();
            param.put("kode_transaksi", txt_kodeTransaksi.getText());
            JasperPrint jPrint = JasperFillManager.fillReport(file, param, con);
            JasperViewer.viewReport(jPrint,false);
        } catch (Exception e) {
            System.err.println("Terjadi Kesalahan"+e);
        }
    }
//    private void tampil_tanggal(){
//        Date dt = new Date();
//        SimpleDateFormat s =  new SimpleDateFormat("dd-MM-yyyy");
//        txtanggal.
//        
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txt_kodeTransaksi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txt_kodeBarang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_namaBarang = new javax.swing.JLabel();
        lbl_satuanBarang = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_kategori = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_stokBarang = new javax.swing.JLabel();
        txt_jumlah = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btn_cariData = new javax.swing.JButton();
        lbl_harga = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_subtotal = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JTextField();
        txt_jumlahBarang = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        btn_tmbhItem = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        txt_bayar = new javax.swing.JTextField();
        txt_kembali = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 153));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORM TRANSAKSI");

        jPanel2.setBackground(new java.awt.Color(153, 153, 255));
        jPanel2.setInheritsPopupMenu(true);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel2.setText("Kode Transaksi");

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel3.setText("Total");

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel4.setText("Tanggal");

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        txt_kodeBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_kodeBarangKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel5.setText("Kode Barang");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel6.setText("Nama Barang");

        lbl_namaBarang.setBackground(new java.awt.Color(0, 0, 0));
        lbl_namaBarang.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbl_namaBarang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lbl_satuanBarang.setBackground(new java.awt.Color(255, 255, 255));
        lbl_satuanBarang.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbl_satuanBarang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel7.setText("Satuan Barang");

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel8.setText("Kategori Barang");

        lbl_kategori.setBackground(new java.awt.Color(255, 255, 255));
        lbl_kategori.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbl_kategori.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel9.setText("Stok Barang");

        lbl_stokBarang.setBackground(new java.awt.Color(255, 255, 255));
        lbl_stokBarang.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbl_stokBarang.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txt_jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_jumlahKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel10.setText("Jumlah");

        btn_cariData.setText("...");
        btn_cariData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariDataActionPerformed(evt);
            }
        });

        lbl_harga.setBackground(new java.awt.Color(255, 255, 255));
        lbl_harga.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbl_harga.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel14.setText("Harga Barang");

        txt_subtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_subtotalActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel15.setText("Subtotal");

        txt_tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tanggalKeyPressed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

        tbl_transaksi.setBackground(new java.awt.Color(153, 153, 255));
        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_transaksi);

        btn_tmbhItem.setText("TAMBAH");
        btn_tmbhItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tmbhItemActionPerformed(evt);
            }
        });

        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_bayarKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel12.setText("Bayar");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel13.setText("Kembalian");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btn_tmbhItem, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_tmbhItem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_batal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(txt_jumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(txt_kodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_cariData, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lbl_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_stokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lbl_satuanBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lbl_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 100, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_kodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(lbl_namaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cariData))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_stokBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_satuanBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_subtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_harga, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(lbl_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_jumlahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_kodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(108, 108, 108))
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_kodeTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1117, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariDataActionPerformed
         Form_tblBarang tabel_barang = new Form_tblBarang();
        tabel_barang.tambah_barang = this;
        tabel_barang.setVisible(true);
        tabel_barang.setResizable(false);
//        Jml_stok.setEnabled(true);
    }//GEN-LAST:event_btn_cariDataActionPerformed

    private void txt_kodeBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kodeBarangKeyPressed
       if (evt.getKeyCode() == KeyEvent.VK_ENTER) {    
           data_cari();
           txt_jumlah.requestFocus(); 
        }
    }//GEN-LAST:event_txt_kodeBarangKeyPressed

    private void txt_subtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_subtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_subtotalActionPerformed

    private void btn_tmbhItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tmbhItemActionPerformed
        int jml=Integer.parseInt(txt_jumlah.getText());  
        int stok_sekarang=Integer.parseInt(lbl_stokBarang.getText());
        if (jml > stok_sekarang) {
            JOptionPane.showMessageDialog(null, "Stok barang tidak mencukupi");
            txt_jumlah.requestFocus();
            txt_jumlah.setText("");
            txt_subtotal.setText("");
            
        } else {
            tambah_detail();
            update_stok();
            load_data();
            refresh_data();           
       }
    }//GEN-LAST:event_btn_tmbhItemActionPerformed

    private void txt_jumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_jumlahKeyPressed
        if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
                  AutoSum(); 
                  txt_jumlahBarang.setText(txt_jumlah.getText());
        }
        
    }//GEN-LAST:event_txt_jumlahKeyPressed
private void tampil_jumlah(){
    
}
    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
       this.cari_kode();
       this.show_data();
       this.show_sisa();
    }//GEN-LAST:event_tbl_transaksiMouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
       Batal();
       refresh_data();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void txt_tanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tanggalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tanggalKeyPressed

    private void txt_bayarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyPressed
        if (evt.getKeyCode()== KeyEvent.VK_ENTER) {
               hitung_kembali();
        }
    }//GEN-LAST:event_txt_bayarKeyPressed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        simpan();
        int pesan=JOptionPane.showConfirmDialog(null, "Print Nota Transaksi?","Cetak",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);

            if(pesan==JOptionPane.YES_OPTION){
                cetak_nota();
            }else { 
                JOptionPane.showMessageDialog(null, "Simpan Transaksi Berhasil");
            }
             refresh_data();
    }//GEN-LAST:event_btn_simpanActionPerformed
    private void data_cari(){
        if (txt_kodeBarang.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf Data Harus dipilih terlebih dahulu... ");
            refresh_data();
        } else {
            try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
 
            String sql = "SELECT tabel_barang.kode_barang, tabel_barang.nama_barang, tabel_kategori.nama_kategori, tabel_barang.satuan_barang, tabel_barang.stok_barang, tabel_barang.harga_barang FROM tabel_barang, tabel_kategori WHERE tabel_kategori.kode_kategori=tabel_barang.kode_kategori AND (kode_barang ='"+txt_kodeBarang.getText()+"')";
            ResultSet res = stt.executeQuery(sql);
            while (res.next()) {               
                txt_kodeBarang.setText(res.getString("kode_barang"));
                lbl_namaBarang.setText(res.getString("nama_barang"));
                lbl_satuanBarang.setText(res.getString("satuan_barang"));
                lbl_kategori.setText(res.getString("nama_kategori"));
                lbl_stokBarang.setText(res.getString("stok_barang"));
                lbl_harga.setText(res.getString("harga_barang"));
            }  
          res.close();stt.close();
        } catch (Exception e) {
                System.out.println(e);
        }
     }        
    }
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
            java.util.logging.Logger.getLogger(FromTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FromTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FromTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FromTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FromTransaksi().setVisible(true);
            }
        });
    }
private void refresh_data(){
    txt_kodeBarang.setText("");
    lbl_namaBarang.setText("");
    lbl_satuanBarang.setText("");
    lbl_kategori.setText("");
    lbl_stokBarang.setText("");
    lbl_harga.setText("");
    txt_jumlah.setText("");
    txt_subtotal.setText("");
}
    private void otomatis(){
        try {
            Connection con = config.conn.configDB();
            Statement stt = con.createStatement();
            String sql="select right (kode_transaksi,2)+1 from tabel_transaksi";
            ResultSet res = stt.executeQuery(sql);
            if(res.next()){
                res.last();
                String no=res.getString(1);
                while (no.length()<3){
                    no="0"+no;
                    txt_kodeTransaksi.setText("KT"+no);}
                }
            else
            {
                txt_kodeTransaksi.setText("KT001"); 
        }
            res.close(); stt.close();
        } catch (Exception e) 
        {
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cariData;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_tmbhItem;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_harga;
    private javax.swing.JLabel lbl_kategori;
    private javax.swing.JLabel lbl_namaBarang;
    private javax.swing.JLabel lbl_satuanBarang;
    private javax.swing.JLabel lbl_stokBarang;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_jumlah;
    private javax.swing.JTextField txt_jumlahBarang;
    private javax.swing.JTextField txt_kembali;
    private javax.swing.JTextField txt_kodeBarang;
    private javax.swing.JTextField txt_kodeTransaksi;
    private javax.swing.JTextField txt_subtotal;
    private javax.swing.JTextField txt_tanggal;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
