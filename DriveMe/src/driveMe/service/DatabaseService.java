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

	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		Timestamp from = new Timestamp(120, 1, 18, 15, 00, 00, 00);
		@SuppressWarnings("deprecation")
		Timestamp to = new Timestamp(120, 1, 18, 15, 30, 00, 00);

		System.out.println(rentVehicle(1, 1, from, to));
	}

	
	
	public static boolean rentVehicle(int customerid, int vehicleid, Timestamp from, Timestamp to) {

		Customer customer = CustomerService.findCustomerById(customerid);
		Vehicle vehicle = VehicleService.findVehiclesById(vehicleid);

		if (customer != null && vehicle != null) {
			Connection con = DatabaseService.MysqlConnection();

			try {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("SELECT rentedfrom,rentedto FROM `rentedvehicles` ");
				boolean isRentable = false;
				while (rs.next()) {
					Timestamp reservedFrom = rs.getTimestamp("rentedfrom");
					Timestamp reservedTo = rs.getTimestamp("rentedto");

					try {
						if ((from.after(reservedFrom) || from.equals(reservedFrom))
								&& (from.before(reservedTo) || from.equals(reservedTo))) {
							isRentable = false;
							if ((to.after(reservedFrom) || to.equals(reservedFrom))
									&& (to.before(reservedTo) || to.equals(reservedTo))) {
								isRentable = false;
							}
						} else if (from.after(reservedTo)) {
							isRentable = true;
						} else if (to.before(reservedFrom)) {
							isRentable = true;
						}

						if (!isRentable) {
							break;
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (isRentable) {
					String query = "INSERT into rentedvehicles (userid, vehicleid, rentedfrom, rentedto, pricetype) values (?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					preparedStmt.setInt(1, customer.getId());
					preparedStmt.setInt(2, vehicle.getId());
					preparedStmt.setTimestamp(3, from);
					preparedStmt.setTimestamp(4, to);
					preparedStmt.setInt(5, 1);

					preparedStmt.execute();
					con.close();

					return true;
				} else {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean saveVehicle(Vehicle vehicle) {
		if (vehicle != null) {
			Connection con = DatabaseService.MysqlConnection();
			
			try {
				String query = "INSERT into vehicles (model, brand, ps, seats, longitude, latitude, image, fuel) values (?, ?, ?, ?, ?, ?, ?, ?)";
				
				String model = vehicle.getModel();
				String brand = vehicle.getBrand();
				int ps = vehicle.getPs();
				int seats = vehicle.getSeats();
				String lon = vehicle.getLongitude();
				String lat = vehicle.getLatitude();
				String image = vehicle.getImage();
				String fuel = vehicle.getFuel();
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				if(StringUtils.isBlank(model)) {
					model = "";
				}
				if(StringUtils.isBlank(brand)) {
					brand = "";
				}
				if(StringUtils.isBlank(lon)) {
					lon = "";
				}
				if(StringUtils.isBlank(lat)) {
					lat = "";
				}
				if(StringUtils.isBlank(image)) {
					image = "";
				}
				if(StringUtils.isBlank(fuel)) {
					fuel = "";
				}
				
				preparedStmt.setString(1, model);
				preparedStmt.setString(2, brand);
				preparedStmt.setInt(3, ps);
				preparedStmt.setInt(4, seats);
				preparedStmt.setString(5, lon);
				preparedStmt.setString(6, lat);
				preparedStmt.setString(7, image);
				preparedStmt.setString(8, fuel);
				
				preparedStmt.execute();
				con.close();

				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
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
