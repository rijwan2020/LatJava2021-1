package UTS;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class simpananInsertFrame extends JInternalFrame implements ActionListener {
    Connection connection;
    Statement statement;
    String sql;
    /* membuat label */
    JLabel l_kode = new JLabel("Kode");
    JLabel l_nama = new JLabel("Nama");
    JLabel l_jenis = new JLabel("Jenis");
    JLabel l_saldo = new JLabel("Saldo");
    JLabel l_keterangan = new JLabel("Keterangan");

    /* membuat text field */
    JTextField t_kode = new JTextField(6);
    JTextField t_nama = new JTextField(25);
    JComboBox c_jenis= new JComboBox();
    JTextField t_saldo = new JTextField(16);
    JTextField t_keterangan = new JTextField(20);

    /* membuat tombol */
    JButton b_insert = new JButton("Simpan");
    JButton b_keluar = new JButton("Keluar");

    /* membuat kontentner */  
    Container container = new Container();

    /* deklarasi variabel string */
    String kode="";
    String nama="";
    String jenis="";
    String saldo="";
    String keterangan="";

    simpananInsertFrame(){
        /* memberi label pada window border */
        super("..::Tambah Data Simpanan::..",true,true,true,true);	
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb");
            statement = connection.createStatement();
            sql="SELECT * FROM jenis";
            ResultSet rs=statement.executeQuery(sql);
            /* mengatur bentuk kontent */
            container=getContentPane();
            container.setLayout(new GridLayout(7,2));

            /* memberi tahu bahwa tombol ini memiliki aksi */
            b_insert.addActionListener(this);
            b_keluar.addActionListener(this);
            while(rs.next())
                c_jenis.addItem(rs.getString("nama"));

            /*menaruh objek pada window */
            container.add(l_kode);
            container.add(t_kode);
            container.add(l_nama);
            container.add(t_nama);
            container.add(l_jenis);
            container.add(c_jenis);
            container.add(l_saldo);
            container.add(t_saldo);
            container.add(l_keterangan);
            container.add(t_keterangan);
            container.add(b_insert);
            container.add(b_keluar);

            pack();
            show();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Error :"+ex);
        } 
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        /* aksi bila tombol b_insert ditekan */
        if (event.getSource()==b_insert) {
            try{
                kode = t_kode.getText();
                nama = t_nama.getText();
                saldo = t_saldo.getText();
                keterangan = t_keterangan.getText();
                jenis = c_jenis.getSelectedItem().toString();

                sql="insert into simpanan (kode, nama, jumlah, jenis, keterangan)"
                        + " values ('"+kode+"','"+nama+"','"+saldo
                        + "','"+jenis+"','"+keterangan+"');";
                statement.executeUpdate(sql);
                //statement.close();
                //connection.close();
                JOptionPane.showMessageDialog(this, "Data simpanan berhasil disimpan.");
                t_kode.setText("");
                t_nama.setText("");
                t_saldo.setText("");
                t_keterangan.setText("");
            }
            catch(Exception e){
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
public class SimpananInsert{
    public static void main(String args[]) {
    /* memanggil & menjalankan program window diatas */
        new simpananInsertFrame();
    }
}
