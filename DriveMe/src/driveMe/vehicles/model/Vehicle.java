package driveMe.vehicles.model;

import java.sql.Date;

public class Vehicle {

	int id;
	String model;
	String brand;
	int ps;
	int seats;
	String longitude;
	String 	latitude;
	String image;
	String fuel;

	
	public Vehicle (int id, String model, String brand, int ps, int seats, String longitude, 
					String latitude, String image, String fuel)
	{
		this.id = id;
		this.model = model;
		this.brand = brand;
		this.ps = ps;
		this.seats = seats;
		this.longitude = longitude;
		this.latitude = latitude;
		this.image = image;
		this.fuel = fuel;

	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}

	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	
}
