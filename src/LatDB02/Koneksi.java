/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LatDB02;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Rijwan - 9882405219111022 
 */
public class Koneksi {
    Connection koneksi;
    
    public static Connection Koneksi(){
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection koneksi = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/alsen/Documents/Kampus/Semester 4/1 Pemrograman Platform 2/Lat2021/LatDB.accdb");
            return koneksi;
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);

            return null;
        }
    }
}
