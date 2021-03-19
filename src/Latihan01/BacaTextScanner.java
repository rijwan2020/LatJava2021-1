/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Latihan01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rijwan - 9882405219111022
 */
public class BacaTextScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            File nFile = new File("ListKota.txt");
            Scanner data = new Scanner(nFile);
            while (data.hasNextLine()) {                
                String row = data.nextLine();
                System.out.println(row);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BacaTextScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
