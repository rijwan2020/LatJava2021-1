/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Latihan02_ToDoList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rijwan - 9882405219111022
 */
public class ToDo {
    String fileName;
    public ArrayList <String> todoLists;
    boolean isEditing = false;
    Scanner input;
    
    public void clearScreen(){
        final String os = System.getProperty("os.name");
        if(os.contains("Windows")){
            try {
                try {
                    new ProcessBuilder("cmd", "/c", "cls")
                            .inheritIO()
                            .start()
                            .waitFor();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ToDo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(ToDo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                Runtime.getRuntime().exec("clear");
                System.out.print("\033[H\\033[2J");
                System.out.flush();
            } catch (IOException ex) {
                Logger.getLogger(ToDo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void showMenu(){
        System.out.println("=== TODO LIST APP ===");
        System.out.println("[1] Lihat Todo List");
        System.out.println("[2] Tambah Todo List");
        System.out.println("[3] Edit Todo List");
        System.out.println("[4] Hapus Todo List");
        System.out.println("[0] Keluar");
        System.out.println("---------------------");
        System.out.print("Pilih menu> ");
        
        String selectedMenu = input.nextLine();
        switch (selectedMenu) {
        case "1":
            showToDoList();
            break;
        case "2":
            addToDoList();
            break;
        case "3":
            editToDoList();
            break;
        case "4":
            deleteToDoList();
            break;
        case "0":
            System.exit(0);
            default:
            System.out.println("Kamu salah pilih menu!");
            backToMenu();
            break;
        }
    }
    
    public void backToMenu(){
        System.out.println("");
        System.out.print("Tekan [Enter] untuk kembali..");
        input.nextLine();
        clearScreen();
    }
    
    public void readToDoList(){
        try {
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);
            // load isi file ke dalam array todoLists
            todoLists.clear();
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                todoLists.add(data);
            }
        } catch (FileNotFoundException ex) {
           Logger.getLogger(ToDo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void showToDoList(){
        clearScreen();
        readToDoList();
        if (todoLists.size() > 0) {
            System.out.println("TODO LIST:");
            int index = 0;
            for (String data : todoLists) {
                System.out.println(String.format("[%d] %s", index, data));
                index++;
            }
        } else {
            System.out.println("Tidak ada data!");
        }
        if (!isEditing) {
            backToMenu();
        }
    }
    
    public void addToDoList(){
        clearScreen();
        System.out.println("Apa yang ingin kamu kerjakan?");
        System.out.print("Jawab: ");
        String newTodoList = input.nextLine();
        try {
            try ( // tulis file
                FileWriter fileWriter = new FileWriter(fileName, true)) {
                fileWriter.append(String.format("%s%n", newTodoList));
            }
            System.out.println("Berhasil ditambahkan!");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan karena: " + e.getMessage());
        }
        backToMenu();
    }
    
    public void editToDoList(){
        isEditing = true;
        showToDoList();
        try {
            System.out.println("-----------------");
            System.out.print("Pilih Indeks> ");
            int index = Integer.parseInt(input.nextLine());
            if (index > todoLists.size()) {
                throw new IndexOutOfBoundsException("Kamu memasukan data yang salah!");
            } else {
                System.out.print("Data baru: ");
                String newData = input.nextLine();
                // update data
                todoLists.set(index, newData);
                System.out.println(todoLists.toString());
                try {
                    // write new data
                    try (FileWriter fileWriter = new FileWriter(fileName, false)) {
                    // write new data
                        for (String data : todoLists) {
                            fileWriter.append(String.format("%s%n", data));
                        }
                    }
                    System.out.println("Berhasil diubah!");
                } catch (IOException e) {
                    System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                }
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        isEditing = false;
        backToMenu();
    }
    
    public void deleteToDoList(){
        isEditing = true;
        showToDoList();
        System.out.println("-----------------");
        System.out.print("Pilih Indeks> ");
        int index = Integer.parseInt(input.nextLine());
        try {
            if (index > todoLists.size()) {
                throw new IndexOutOfBoundsException("Kamu memasukan data yang salah!");
            } else {
                System.out.println("Kamu akan menghapus:");
                System.out.println(
                    String.format("[%d] %s", 
                    index, 
                    todoLists.get(index))
                );
                System.out.println("Apa kamu yakin?");
                System.out.print("Jawab (y/t): ");
                String jawab = input.nextLine();
                if (jawab.equalsIgnoreCase("y")) {
                    // hapus data
                    todoLists.remove(index);
                    // tulis ulang file
                    try {
                        // write new data
                        try (FileWriter fileWriter = new
                        FileWriter(fileName, false)) {
                            // write new data
                            for (String data : todoLists) {
                                fileWriter.append(String.format("%s%n", data));
                            }
                        }
                        System.out.println("Berhasil dihapus!");
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan karena: " 
                                + e.getMessage());
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
        System.out.println(e.getMessage());
        }
        isEditing = false;
        backToMenu();
    }
}
