package ma.shopping.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBcon {

	private static Connection connection = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		
		//String url = "jdbc:mysql://localhost:3306/ecommerce";
		//String username = "root";
		//String password = "onethata";
		
		if(connection == null){
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","onethata");
			System.out.println("Connected");
		}
		return connection;
	}
}
