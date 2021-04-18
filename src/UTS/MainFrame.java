/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author Rijwan - 9882405219111022
 */

class MenuFrame extends JFrame implements ActionListener{

    JMenuBar menu = new JMenuBar();
    JMenu simpanan = new JMenu("Simpanan");
    JMenu jenis = new JMenu("Jenis Simpanan");
    JMenu help = new JMenu("Help");
    
    JMenuItem simpanan_view = new JMenuItem("Lihat Data",'L');
    JMenuItem simpanan_insert = new JMenuItem("Tambah Data",'T');
    JMenuItem simpanan_update = new JMenuItem("Ubah Data",'U');
    JMenuItem simpanan_delete = new JMenuItem("Hapus Data",'H');
    JMenuItem jenis_view = new JMenuItem("Lihat Data",'L');
    JMenuItem jenis_insert = new JMenuItem("Tambah Data",'T');
    JMenuItem jenis_update = new JMenuItem("Ubah Data",'U');
    JMenuItem jenis_delete = new JMenuItem("Hapus Data",'H');
    JMenuItem help_quit = new JMenuItem("Keluar",'K');
    JMenuItem help_about = new JMenuItem("About",'A');

    Container container = new Container();
    
    MenuFrame(){
        super(".:: Pengelolaan Data Simpanan ::.");
  	setSize(640,480);
  	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  	
	setJMenuBar(menu);
	simpanan.setMnemonic('S');
	jenis.setMnemonic('J');
        help.setMnemonic('H');
	
	simpanan_view.addActionListener(this);
	simpanan_insert.addActionListener(this);
	simpanan_update.addActionListener(this);
	simpanan_delete.addActionListener(this);
	jenis_view.addActionListener(this);
	jenis_insert.addActionListener(this);
	jenis_update.addActionListener(this);
	jenis_delete.addActionListener(this);
	help_quit.addActionListener(this);
        help_about.addActionListener(this);
	
        // menu simpanan
	menu.add(simpanan);
	simpanan.add(simpanan_view);
	simpanan.add(simpanan_insert);
	simpanan.add(simpanan_update);
	simpanan.add(simpanan_delete);
        
        // menu jenis simpanan
	menu.add(jenis);
	jenis.add(jenis_view);
	jenis.add(jenis_insert);
	jenis.add(jenis_update);
	jenis.add(jenis_delete);
        
        // menu help
        menu.add(help);
        help.add(help_about);
        help.addSeparator();
        help.add(help_quit);
        
	menu.setSize(400,100);
	menu.setLocation(60,60);
	menu.setVisible(true);
	
	container=getContentPane();
	container.setLayout(new FlowLayout());
	
	show();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == simpanan_view) {
            frameSimpananView();
        }
        if (e.getSource() == simpanan_insert) {
            frameSimpananInsert();
        }
        if (e.getSource() == simpanan_update) {
            frameSimpananUpdate();
        }
        if (e.getSource() == simpanan_delete) {
            frameSimpananDelete();
        }
        if (e.getSource() == jenis_view) {
            frameJenisSimpananView();
        }
        if (e.getSource() == jenis_insert) {
            frameJenisSimpananInsert();
        }
        if (e.getSource() == jenis_update) {
            frameJenisSimpananUpdate();
        }
        if (e.getSource() == jenis_delete) {
            frameJenisSimpananDelete();
        }
        if (e.getSource() == help_about) {
            frameAbout();
        }
        if (e.getSource() == help_quit) {
            System.exit(0);
        }
    }
    
    private void frameAbout(){
  	JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	AboutFrame iframeAbout = new AboutFrame();
	desktop.add(iframeAbout);
	setContentPane(desktop);
    }
    
    private void frameSimpananView(){
        JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	simpananViewFrame iframeSelect = new simpananViewFrame();
	desktop.add(iframeSelect);
	setContentPane(desktop);
    }
    
    private void frameSimpananInsert(){
        JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	simpananInsertFrame iframeInsert = new simpananInsertFrame();
	desktop.add(iframeInsert);
	setContentPane(desktop);
    }
    
    private void frameSimpananUpdate(){
        JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	simpananUpdateFrame iframeUpdate = new simpananUpdateFrame();
	desktop.add(iframeUpdate);
	setContentPane(desktop);
    }
    
    private void frameSimpananDelete(){
        JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	simpananDeleteFrame iframeUpdate = new simpananDeleteFrame();
	desktop.add(iframeUpdate);
	setContentPane(desktop);
    }
    
    private void frameJenisSimpananView(){
        JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	jenisSimpananViewFrame iframeSelect = new jenisSimpananViewFrame();
	desktop.add(iframeSelect);
	setContentPane(desktop);
    }
    
    private void frameJenisSimpananInsert(){
        JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	jenisSimpananInsertFrame iframeInsert = new jenisSimpananInsertFrame();
	desktop.add(iframeInsert);
	setContentPane(desktop);
    }
    
    private void frameJenisSimpananUpdate(){
        JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	jenisSimpananUpdateFrame iframeUpdate = new jenisSimpananUpdateFrame();
	desktop.add(iframeUpdate);
	setContentPane(desktop);
    }
    
    private void frameJenisSimpananDelete(){
        JDesktopPane desktop = new JDesktopPane();
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
	jenisSimpananDeleteFrame iframeUpdate = new jenisSimpananDeleteFrame();
	desktop.add(iframeUpdate);
	setContentPane(desktop);
    }
    
}

/**
 *
 * @author Rijwan - 9882405219111022
 */
class MainFrame{
    public static void main(String args[]) {
        new MenuFrame();
    }
}
