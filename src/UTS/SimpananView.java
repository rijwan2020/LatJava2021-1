package UTS;

import javax.swing.*;
import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import java.sql.*;

class simpananViewFrame extends JInternalFrame implements Printable{
    JTable tableView;
    int n;
    Object[][] data = new Object[0][0];
	
    simpananViewFrame() {
	super("..::Data Simpanan::..",true,true,true,true);
	try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String koneksi="jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb";
            Connection connection = DriverManager.getConnection(koneksi);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql="select * from simpanan";
            ResultSet rs=statement.executeQuery(sql);
			
            final String[] headers = {"Kode", "Nama", "Jenis", "Saldo", "Keterangan"};
            rs.last();
            n=rs.getRow();
            data = new Object[n][5];
            int p=0;
            rs.beforeFirst();
            while (rs.next()) {
		data[p][0] = rs.getString("kode");
		data[p][1] = rs.getString("nama");
		data[p][2] = rs.getString("jenis");
		data[p][3] = rs.getString("jumlah");
		data[p][4] = rs.getString("keterangan");
                p++;
            }
            statement.close();
            connection.close();
			
            tableView = new JTable(data, headers);
            JScrollPane scrollpane = new JScrollPane(tableView);
            scrollpane.setPreferredSize(new Dimension(500, 200));
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(BorderLayout.CENTER,scrollpane);
            pack();

            JButton printButton= new JButton();
            printButton.setText("Cetak Data");			
            getContentPane().add(BorderLayout.SOUTH,printButton);
			
            RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
			
            printButton.addActionListener((ActionEvent evt) -> {
                PrinterJob pj=PrinterJob.getPrinterJob();
                pj.setPrintable(simpananViewFrame.this);
                pj.printDialog();
                try{
                    pj.print();
                }catch (Exception PrintException) {
                    JOptionPane.showMessageDialog(null,PrintException);
                }
            });
			
            setVisible(true);
	} catch (ClassNotFoundException | SQLException DBException) {
            JOptionPane.showMessageDialog(this,"Error : " + DBException);
	}
    }
	
    public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) throws PrinterException {			
	int i;
	int ukuranfont=10;
	int x=0;
	int y=ukuranfont*3;

	Graphics2D g = (Graphics2D) graphics;
	g.setFont(new Font("Arial Narrow",Font.ITALIC,ukuranfont));
	g.setColor(Color.black);

	double height=pageFormat.getImageableHeight();
	double width=pageFormat.getImageableWidth();
	int panjangareadokumen=(int)height-(ukuranfont*4);
	int jumlahdata=n;
	int jumlahbarisdata=jumlahdata*(ukuranfont+2);
	int TotalHalaman=(int)jumlahbarisdata/panjangareadokumen;

	if (pageIndex>TotalHalaman)
		return NO_SUCH_PAGE;

	g.translate(pageFormat.getImageableX(),
	pageFormat.getImageableY());

	//Cetak Header ...............
	g.drawString("Track",0,ukuranfont);
	g.drawString("Artis",25,ukuranfont);
	g.drawString("Judul",100,ukuranfont);
	g.drawString("Album",220,ukuranfont);
	g.drawString("Tahun",380,ukuranfont);
        g.drawString("Genre",450,ukuranfont);
	g.drawString("====================================================================================",x,ukuranfont*2);

	g.translate(0f,-pageIndex*(int)(height-(ukuranfont*2)));
	g.setClip(
                0,
                (int)(height-(ukuranfont*2))*pageIndex,
                (int)width,
                (int)(height-(ukuranfont*2))
        );

	for(i=0;i<n;i++){
            g.drawString(""+data[i][0],0,y);
            g.drawString(""+data[i][1],25,y);
            g.drawString(""+data[i][2],100,y);
            g.drawString(""+data[i][3],220,y);
            g.drawString(""+data[i][4],380,y);
            g.drawString(""+data[i][5],450,y);
            y+=ukuranfont+2;
	}
     	return Printable.PAGE_EXISTS;
   }
}

/**
 *
 * @author Rijwan - 9882405219111022
 */
public class SimpananView{
   public static void main(String[] args) {
	new simpananViewFrame();
   }
}
