package driveMe.service;

import java.sql.*;

public class databaseService {

	public static void main(String[] args) {

		final String hostname = "192.168.2.8";
		final String port = "3306";
		final String dbname = "driveMe";
		final String user = "driveMe";
		final String password = "r2KRyrJ2aMPbtY1r";

		Connection con = MysqlConnection(hostname, port, dbname, user, password);

		try {
			Statement st = con.createStatement();
			st.executeUpdate("create table users (\r\n" + 
					"    id int unsigned auto_increment not null,\r\n" + 
					"    first_name varchar(32) not null,\r\n" + 
					"    last_name varchar(32) not null,\r\n" + 
					"    date_created timestamp default now(),\r\n" + 
					"    is_admin boolean,\r\n" + 
					"    num_points int,\r\n" + 
					"    primary key (id)\r\n" + 
					");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	public static Connection MysqlConnection(String hostname, String port, String dbname, String username,
			String password) {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("Unable to load driver.");
			e.printStackTrace();
		}

		try {
			String url = "jdbc:mariadb://" + hostname + ":" + port + "/" + dbname;
			conn = DriverManager.getConnection(url, username, password);

		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("SQLState: " + sqle.getSQLState());
			System.out.println("VendorError: " + sqle.getErrorCode());
			sqle.printStackTrace();
		}

		return conn;
	}

}
