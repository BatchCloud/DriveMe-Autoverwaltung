package driveMe.costumers.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import driveMe.costumers.model.Customer;
import driveMe.service.databaseService;

public class CustomerService {

	
	public static ArrayList<Customer> findCostumerByAll() {
		Connection con = databaseService.MysqlConnection();
		ArrayList<Customer> costumers = new ArrayList<Customer>();
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id,username,firstname,lastname,birthday FROM `customers` WHERE verified= '1'");
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String username = rs.getString("username");
				Date birthday = rs.getDate("birthday");
				
				Customer costumer = new Customer(id, firstname, lastname, username, birthday);
				costumers.add(costumer);
			}
			con.close();		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return costumers;
	}
	
	public static Customer findCostumerById(int id) {
		Connection con = databaseService.MysqlConnection();
		Customer costumer = null;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id,username,firstname,lastname,birthday FROM `customers` WHERE verified= '1' AND id= '" + id + "'");
			
			while (rs.next()) {
				int userid = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String username = rs.getString("username");
				Date birthday = rs.getDate("birthday");
				
				costumer = new Customer(userid, firstname, lastname, username, birthday);
			}
			
			con.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return costumer;
	}

	public static Customer findCostumerByUsername(String usernameid) {
		Connection con = databaseService.MysqlConnection();
		Customer costumer = null;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id,username,firstname,lastname,birthday FROM `customers` WHERE verified= '1' AND username= '" + usernameid + "'");
			
			while (rs.next()) {
				int userid = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String username = rs.getString("username");
				Date birthday = rs.getDate("birthday");
				
				costumer = new Customer(userid, firstname, lastname, username, birthday);
			}
			
			con.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return costumer;
	}
	
	
}
