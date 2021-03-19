/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Latihan02_ToDoList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rijwan - 9882405219111022
 */
public class ToDoMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ToDo TD=new ToDo();
        TD.todoLists=new ArrayList<>();
        TD.input=new Scanner(System.in);

        String filePath = System.console() == null ? "/src/todolist.txt" :"/todolist.txt";
        TD.fileName = System.getProperty("user.dir") + filePath;
        //TD.fileName="daftar.txt";
        System.out.println("FILE: " + TD.fileName);
        // run the program (main loop)
        while (true) {
            TD.showMenu();
        }

    }
    
}
