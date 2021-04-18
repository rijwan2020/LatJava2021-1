/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LatDB02;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Rijwan - 9882405219111022 
 */
public class Model {
    
    public static Object show(String table, String where) {
        try {
            Connection cn = Koneksi.Koneksi();
            Statement st;
            ResultSet rs;
            
            st = cn.createStatement();
            rs = st.executeQuery("SELECT * FROM " + table + " " + where);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void save(String table, String fields[], String values[]){
        String sql = "Insert into " + 
                table +
                " (" + 
                StringUtils.join(fields, ", ") +
                ") VALUES (\"" +
                StringUtils.join(values, "\", \"") +
                "\")";
        
        try {
            Connection cn = Koneksi.Koneksi();
            Statement st;
            
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan.");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void update(String table, String[] id, String fields[], String values[], int panjang){
        String sql = "Update " + table + " SET";
        for (int i = 0; i < panjang; i++) {
            sql = sql + " " + fields[i] + " = \"" + values[i] + "\",";
        }
        sql = sql.substring(0, (sql.length() - 1)) + 
                " WHERE " +
                id[0] +
                " = \"" +
                id[1] + 
                "\"";
        try {
            Connection cn = Koneksi.Koneksi();
            Statement st;
            
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil diperbaharui.");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void delete(String table, String data[]){
        String sql = "DELETE FROM " +
                table +
                " WHERE " +
                data[0] +
                " = \"" +
                data[1] +
                "\"";
        
        try {
            Connection cn = Koneksi.Koneksi();
            Statement st;
            
            st = cn.createStatement();
            st.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Data berhasil dihapus.");
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
