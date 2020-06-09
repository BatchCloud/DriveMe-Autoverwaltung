package driveMe.vehicles.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


import org.apache.commons.lang.StringUtils;
import org.jxmapviewer.viewer.GeoPosition;


import driveMe.MapPanel;
import driveMe.customers.model.Customer;
import driveMe.customers.service.CustomerService;
import driveMe.service.DatabaseService;
import driveMe.util.DriveMeUtil;
import driveMe.vehicles.model.Vehicle;

public class VehicleService {
	
	public DriveMeUtil driveMeUtil = new DriveMeUtil();
	
	public static ArrayList<Vehicle> findAllVehicles() {
		Connection con = DatabaseService.MysqlConnection();
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `vehicles` ");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String model = rs.getString("model");
				String brand = rs.getString("brand");
				int ps = rs.getInt("ps");
				int seats = rs.getInt("seats");
				String longitude = rs.getString("longitude");
				String latitude = rs.getString("latitude");
				String image = rs.getString("image");
				String fuel = rs.getString("fuel");

				Vehicle vehicle = new Vehicle(id, model, brand, ps, seats, longitude, latitude, image, fuel);
				vehicles.add(vehicle);
			}
			con.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return vehicles;
	}
	
	public static Vehicle findVehiclesById(int _id) {
		Connection con = DatabaseService.MysqlConnection();
		Vehicle vehicle = null;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `vehicles` WHERE id= '" + _id + "'");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String model = rs.getString("model");
				String brand = rs.getString("brand");
				int ps = rs.getInt("ps");
				int seats = rs.getInt("seats");
				String longitude = rs.getString("longitude");
				String latitude = rs.getString("latitude");
				String image = rs.getString("image");
				String fuel = rs.getString("fuel");
				
				vehicle = new Vehicle(id, model, brand, ps, seats, longitude, latitude, image, fuel);
			}
			con.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vehicle; 
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
	


	public void jumpToLocation(MapPanel map, Vehicle currentVehicle) {
		GeoPosition currentVehiclePostion = new GeoPosition( Double.parseDouble(currentVehicle.getLongitude()), Double.parseDouble(currentVehicle.getLatitude()));
		map.setAddressLocation(currentVehiclePostion);
		map.setZoom(4);
		map.revalidate();
		map.repaint();
	}
	
	
	 
}
