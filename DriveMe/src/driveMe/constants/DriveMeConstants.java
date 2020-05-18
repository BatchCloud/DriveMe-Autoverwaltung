package driveMe.constants;

import java.awt.Color;

public class DriveMeConstants {
	
	public class Picture
	{
		public static final String PATH = "/driveMe/assets/";
	}
	
	public static class Colour
	{
		public final static Color primaryColor = new Color(105,157,217);
		public final static Color secondColor = new Color(238,238,238);	
	}

	public class VehicleContent
	{
		public static final String VEHICLE_KEY = "vehicleContent";
		public static final String MAP_KEY = "vehicleContent";
	}
	
	
	public class Database{
		public class Vehicle{
			public static final String MODEL = "model";
			public static final String BRAND = "brand";
			public static final String PS = "ps";
			public static final String SEATS = "seats";
			public static final String LONGITUDE = "longitude";
			public static final String LATITUDE = "latitude";
			public static final String IMAGE = "image";
			public static final String FUEL = "fuel";
		}
	}
	
}
