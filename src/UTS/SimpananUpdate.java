package UTS;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class simpananUpdateFrame extends JInternalFrame implements ActionListener {
    Connection connection;
    Statement statement;
    ResultSet rs;
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
    
    JTextField t_cari = new JTextField(20);

    /* membuat objek tombol */
    JButton b_update = new JButton("Update");
    JButton b_cari = new JButton("Cari");
    JButton b_keluar = new JButton("Keluar");

    /* membuat kontentner */
    Container container = new Container();

    /* mengenalkan variabel string */
    String kode="";
    String nama="";
    String jenis="";
    String saldo="";
    String keterangan="";
    String cari="";
    String sql;
  
    simpananUpdateFrame(){
	/* memberi label pada window border */
  	super("..::Edit Data Simpanan::..",true,true,true,true);
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb");
            statement = connection.createStatement();
            sql="SELECT * FROM jenis";
            ResultSet rs=statement.executeQuery(sql);
            /* mengatur bentuk kontent */
            container=getContentPane();
            container.setLayout(new GridLayout(8,4));
            
            /* memberi tahu bahwa tombol ini memiliki aksi */
            b_update.addActionListener(this);
            b_cari.addActionListener(this);
            b_keluar.addActionListener(this);
            while(rs.next())
                c_jenis.addItem(rs.getString("nama"));
            
            t_kode.setEditable(false);
            /* menaruh objek pada window */
            container.add(t_cari);
            container.add(b_cari);
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
            container.add(b_update);
            container.add(b_keluar);
            
            pack();
            show();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }
    
    public void actionPerformed(ActionEvent event) {
	/* aksi bila tombol b_update ditekan */
	if (event.getSource()==b_update) {
            try{
                kode = t_kode.getText();
                nama = t_nama.getText();
                keterangan = t_keterangan.getText();
                saldo = t_saldo.getText();
                jenis = c_jenis.getSelectedItem().toString();

                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb");
                Statement statement = connection.createStatement();
                String sql="update simpanan set nama = '" + nama
                        + "',keterangan = '" + keterangan
                        + "',jumlah = '" + saldo
                        + "',jenis = '"+ jenis
                        + "' where kode = '" + kode + "'";
                statement.executeUpdate(sql);
                //statement.close();
                //connection.close();
                JOptionPane.showMessageDialog(this,"Data simpanan berhasil diperbaharui.");
            } catch(Exception e){
                JOptionPane.showMessageDialog(this,"Error :"+e);
            }
	}	
	/* aksi bila tombol b_cari ditekan */
	if (event.getSource()==b_cari) {
            try{
                cari=t_cari.getText();

                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                Connection connection = DriverManager.getConnection(
                        "jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb");
                Statement statement = connection.createStatement();
                String sql="select * from simpanan where kode = '"+cari+"'";
                ResultSet rs=statement.executeQuery(sql);

                /* menampilkan hasil pada text isian */
                if(rs.next()){
                    t_kode.setText(rs.getString("kode"));
                    t_nama.setText(rs.getString("nama"));
                    t_saldo.setText(rs.getString("jumlah"));
                    t_keterangan.setText(rs.getString("keterangan"));
                    c_jenis.setSelectedItem(rs.getString("jenis"));
                }else{
                    JOptionPane.showMessageDialog(this,"Data simpanan dengan kode " + kode + " tidak ditemukan.");
                }
                //statement.close();
                //connection.close();
            } catch(Exception e){
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
public class SimpananUpdate{
    public static void main(String args[]) {
        new simpananUpdateFrame();
    }
}
