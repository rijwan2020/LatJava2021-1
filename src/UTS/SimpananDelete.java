package UTS;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class simpananDeleteFrame extends JInternalFrame implements ActionListener {
    
    /* membuat label */
    JLabel l_kode = new JLabel("Kode");
    JLabel l_nama = new JLabel("Nama");
    JLabel l_jenis = new JLabel("Jenis");
    JLabel l_saldo = new JLabel("Saldo");
    JLabel l_keterangan = new JLabel("Keterangan");

    /* membuat text field */
    JTextField t_kode = new JTextField(6);
    JTextField t_nama = new JTextField(25);
    JTextField t_jenis = new JTextField(25);
    JTextField t_saldo = new JTextField(16);
    JTextField t_keterangan = new JTextField(20);
    
    JTextField t_cari = new JTextField(20);

    /* membuat objek tombol */
    JButton b_delete = new JButton("Delete");
    JButton b_cari = new JButton("Cari");
    JButton b_keluar = new JButton("Keluar");

    /* membuat objek kontentner */
    Container container = new Container();

    /* mengenalkan variabel string */
    String kode="";
    String nama="";
    String jenis="";
    String saldo="";
    String keterangan="";
    String cari="";
  
    simpananDeleteFrame(){
        /* membuat label pada window border */
        super("Hapus Data Simpanan",true,true,true,true);

        /* mengatur bentuk kontent */
        container=getContentPane();
        container.setLayout(new GridLayout(8,2));

        /* memberi tahu bahwa tombol ini memiliki aksi */
        b_delete.addActionListener(this);
        b_cari.addActionListener(this);
        b_keluar.addActionListener(this);

        /* agar t_track tak dapat diubah */
        t_kode.setEditable(false);
        t_nama.setEditable(false);
        t_jenis.setEditable(false);
        t_saldo.setEditable(false);
        t_keterangan.setEditable(false);
        /* menaruh objek pada window */
        container.add(t_cari);
        container.add(b_cari);
        container.add(l_kode);
        container.add(t_kode);
        container.add(l_nama);
        container.add(t_nama);
        container.add(l_jenis);
        container.add(t_jenis);
        container.add(l_saldo);
        container.add(t_saldo);
        container.add(l_keterangan);
        container.add(t_keterangan);
        container.add(b_delete);
        container.add(b_keluar);

        pack();
        show();
    }
    public void actionPerformed(ActionEvent event){
        /* aksi bila tombol b_delete ditekan */
        if (event.getSource()==b_delete) {
            try{
                kode=t_kode.getText();
                
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb");
                Statement statement = connection.createStatement();
                String sql="delete from simpanan where kode = '"+kode+"'";
                statement.execute(sql);
                //statement.close();
                //connection.close();
                JOptionPane.showMessageDialog(this,"Data simpanan berhasil dihapus.");
                t_cari.setText("");
                t_kode.setText("");
                t_nama.setText("");
                t_saldo.setText("");
                t_keterangan.setText("");
                t_jenis.setText("");
            } catch(Exception e){
               JOptionPane.showMessageDialog(this,"Error :"+e);
            }
        }	
        
        /* aksi bila tombol b_cari ditekan */
        if (event.getSource()==b_cari) {
            try{
                cari=t_cari.getText();

                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb");
                Statement statement = connection.createStatement();
                String sql="select * from simpanan where kode = '"+cari+"'";
                ResultSet rs=statement.executeQuery(sql);

                if(rs.next()){
                    t_kode.setText(rs.getString("kode"));
                    t_nama.setText(rs.getString("nama"));
                    t_saldo.setText(rs.getString("jumlah"));
                    t_keterangan.setText(rs.getString("keterangan"));
                    t_jenis.setText(rs.getString("jenis"));
                }else{
                    JOptionPane.showMessageDialog(this,"Data simpanan tidak ditemukan.");
                }
                //statement.close();
                //connection.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Error :"+e);
            }
        }
        if (event.getSource()==b_keluar) {
            this.dispose();	
        }
    }
}

/**
 *
 * @author Rijwan - 9882405219111022
 */
public class SimpananDelete{
    public static void main(String args[]) {
        /* memanggil & menjalankan program window diatas */
        new simpananDeleteFrame();
    }
}
