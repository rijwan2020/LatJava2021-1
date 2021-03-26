/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LatDB01;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Rijwan - 9882405219111022
 */
public class LatDB {
    Connection cn;
    Statement st;
    
    LatDB(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            cn = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/alsen/Documents/Kampus/Semester 4/1 Pemrograman Platform 2/Lat2021/LatDB.accdb");
            st = cn.createStatement();
            JOptionPane.showMessageDialog(null, "Koneksi berhasil");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LatDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void showDataBarang(){
        try {
            String sql = "SELECT * FROM TBarang";
            ResultSet hasil = st.executeQuery(sql);
            System.out.println("Kode \tNama \tHarga \tJenis");
            while (hasil.next()) {                
                System.out.println(
                        hasil.getString("kode") + "\t" +
                        hasil.getString("nama") + "\t" +
                        hasil.getString("harga") + "\t" +
                        hasil.getString("jenis")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(LatDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        LatDB p = new LatDB();
        p.showDataBarang();
    }
}
