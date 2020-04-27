package libraryWindowGUI;

import java.sql.*;

import javax.swing.JOptionPane;

public class PostgresqlConnection {
	Connection conn=null;
	public static Connection dbConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "novax@123");
//			JOptionPane.showMessageDialog(null, "database connection Successful.. !!");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
