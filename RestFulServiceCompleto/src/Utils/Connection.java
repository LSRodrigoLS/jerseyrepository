package Utils;

import java.sql.DriverManager;


public class Connection {
	
	
	public static java.sql.Connection getConnection(){
		java.sql.Connection con = null;		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/bd_autos_v2", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		
	}
}
