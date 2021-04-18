package UTS;

import javax.swing.*;
import java.awt.print.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import java.sql.*;

class jenisSimpananViewFrame extends JInternalFrame{
    JTable tableView;
    int n;
    Object[][] data = new Object[0][0];
	
    jenisSimpananViewFrame() {
	super("..::Data Simpanan::..",true,true,true,true);
	try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String koneksi="jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb";
            Connection connection = DriverManager.getConnection(koneksi);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql="select * from jenis";
            ResultSet rs=statement.executeQuery(sql);
			
            final String[] headers = {"Kode", "Nama"};
            rs.last();
            n=rs.getRow();
            data = new Object[n][2];
            int p=0;
            rs.beforeFirst();
            while (rs.next()) {
		data[p][0] = rs.getString(2);
		data[p][1] = rs.getString(1);
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

            RepaintManager.currentManager(this).setDoubleBufferingEnabled(false);
			
            setVisible(true);
	} catch (ClassNotFoundException | SQLException DBException) {
            JOptionPane.showMessageDialog(this,"Error : " + DBException);
	}
    }
}

/**
 *
 * @author Rijwan - 9882405219111022
 */
public class JenisSimpananView{
   public static void main(String[] args) {
	new jenisSimpananViewFrame();
   }
}
