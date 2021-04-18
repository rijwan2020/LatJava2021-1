package UTS;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class jenisSimpananInsertFrame extends JInternalFrame implements ActionListener {
    Connection connection;
    Statement statement;
    String sql;
    /* membuat label */
    JLabel l_kode = new JLabel("Kode");
    JLabel l_nama = new JLabel("Nama");

    /* membuat text field */
    JTextField t_kode = new JTextField(6);
    JTextField t_nama = new JTextField(25);

    /* membuat tombol */
    JButton b_insert = new JButton("Simpan");
    JButton b_keluar = new JButton("Keluar");

    /* membuat kontentner */  
    Container container = new Container();

    /* deklarasi variabel string */
    String kode="";
    String nama="";

    jenisSimpananInsertFrame(){
        /* memberi label pada window border */
        super("..::Tambah Data Jenis Simpanan::..",true,true,true,true);	
        try {
            /* mengatur bentuk kontent */
            container=getContentPane();
            container.setLayout(new GridLayout(7,2));

            /* memberi tahu bahwa tombol ini memiliki aksi */
            b_insert.addActionListener(this);
            b_keluar.addActionListener(this);

            /*menaruh objek pada window */
            container.add(l_kode);
            container.add(t_kode);
            container.add(l_nama);
            container.add(t_nama);
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

                sql="insert into jenis (kode, nama)"
                        + " values ('"+kode+"','"+nama+"');";
                statement.executeUpdate(sql);
                //statement.close();
                //connection.close();
                JOptionPane.showMessageDialog(this, "Data jenis simpanan berhasil disimpan.");
                t_kode.setText("");
                t_nama.setText("");
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
public class JenisSimpananInsert{
    public static void main(String args[]) {
    /* memanggil & menjalankan program window diatas */
        new jenisSimpananInsertFrame();
    }
}
