package driveMe.vehicles.service;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import driveMe.service.DatabaseService;
import driveMe.vehicles.model.Vehicle;

public class VehiclesService {
	
	public static ArrayList<Vehicle> findVehiclesByAll() {
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

}
