package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
	private static final String url = "jdbc:mysql://localhost/BDEmploye";
	private static final String user = "root";
	private static final String  password = "Aya@ayouya";
	static Connection conn = null;
	
	public static Connection getConnexion() {
	if (conn == null) {
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");

	conn = DriverManager.getConnection (url, user, password);

	} catch (ClassNotFoundException | SQLException e) {

	e.printStackTrace();

	throw new RuntimeException("Error lors de la connexion");

	}
	}
	return conn;
}

}
