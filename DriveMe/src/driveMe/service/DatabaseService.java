package driveMe.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import driveMe.customers.model.Customer;
import driveMe.customers.service.CustomerService;
import driveMe.vehicles.model.Vehicle;
import driveMe.vehicles.service.VehicleService;

public class DatabaseService {

	@SuppressWarnings("deprecation")
	public static Connection MysqlConnection() {
		final String hostname = "192.168.2.8";
		final String port = "3306";
		final String dbname = "driveMe";
		final String user = "driveMe";
		final String password = "r2KRyrJ2aMPbtY1r";

		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.err.println("Unable to load driver.");
			e.printStackTrace();
		}

		try {
			String url = "jdbc:mariadb://" + hostname + ":" + port + "/" + dbname;
			conn = DriverManager.getConnection(url, user, password);

		} catch (SQLException sqle) {
			System.out.println("SQLException: " + sqle.getMessage());
			System.out.println("SQLState: " + sqle.getSQLState());
			System.out.println("VendorError: " + sqle.getErrorCode());
			sqle.printStackTrace();
		}

		return conn;
	}

}
