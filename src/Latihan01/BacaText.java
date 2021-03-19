/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Latihan01;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rijwan - 9882405219111022
 */
public class BacaText {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BufferedReader data = null;
        try {
            File nFile = new File("ListKota.txt");
            data = new BufferedReader(new FileReader(nFile));
            String baris;
            try {
                while ((baris = data.readLine()) != null) {
                    System.out.println(baris);
                }
            } catch (IOException ex) {
                Logger.getLogger(BacaText.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BacaText.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                data.close();
            } catch (IOException ex) {
                Logger.getLogger(BacaText.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
