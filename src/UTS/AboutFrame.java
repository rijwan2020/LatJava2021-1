package UTS;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.*;

class AboutFrame extends JInternalFrame{
    JTable tableView;
    int n;
    Object[][] data = new Object[0][0];
	
    AboutFrame() {
	super("..::About Us::..",false,true,false,false);
        setSize(250,350);
	try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            String koneksi="jdbc:ucanaccess://C:\\Users\\alsen\\Documents\\Kampus\\Semester 4\\1 Pemrograman Platform 2\\Lat2021/DBUTS.accdb";
            Connection connection = DriverManager.getConnection(koneksi);
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql="select * from developer";
            ResultSet rs=statement.executeQuery(sql);
			
            final String[] headers = {"NAMA", "NPM"};
            rs.last();
            n=rs.getRow();
            data = new Object[n][2];
            int p=0;
            rs.beforeFirst();
            while (rs.next()) {
		data[p][0] = rs.getString(1);
		data[p][1] = rs.getString(2);
		p++;
            }
            statement.close();
            connection.close();
			
            tableView = new JTable(data, headers);
            JScrollPane scrollpane = new JScrollPane(tableView);
            scrollpane.setPreferredSize(new Dimension(500, 120));
            getContentPane().setLayout(new BorderLayout());
            getContentPane().add(BorderLayout.CENTER,scrollpane);
            pack();
            setVisible(true);
        } catch (Exception DBException) {
            JOptionPane.showMessageDialog(this,"Error : " + DBException);
        }
    }
}
           