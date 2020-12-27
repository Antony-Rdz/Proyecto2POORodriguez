import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class Conexion {

	private final String URL = "jdbc:mysql://localhost:3306/datosprograma?useSSL=false";
	private final String USERNAME = "root";
	private final String PASSWORD = "Melopomelo13";
	private static Connection conect = null;
	@Test
	public Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conect = DriverManager.getConnection(this.URL, this.USERNAME, this.PASSWORD);
		} catch (Exception e) {
			System.out.println(e);
		}

		return conect;
	}
	
}
