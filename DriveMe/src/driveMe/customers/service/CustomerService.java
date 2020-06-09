package driveMe.customers.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import driveMe.customers.model.Customer;
import driveMe.service.DatabaseService;
import driveMe.vehicles.model.Vehicle;

public class CustomerService {

	
	public static ArrayList<Customer> findAllCustomers() {
		Connection con = DatabaseService.MysqlConnection();
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
	
	public static Customer findCustomerById(int id) {
		Connection con = DatabaseService.MysqlConnection();
		Customer customer = null;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id,username,firstname,lastname,birthday FROM `customers` WHERE verified= '1' AND id= '" + id + "'");
			
			while (rs.next()) {
				int userid = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String username = rs.getString("username");
				Date birthday = rs.getDate("birthday");
				
				customer = new Customer(userid, firstname, lastname, username, birthday);
			}
			
			con.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	public static Customer findCustomerByUsername(String usernameid) {
		Connection con = DatabaseService.MysqlConnection();
		Customer customer = null;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT id,username,firstname,lastname,birthday FROM `customers` WHERE verified= '1' AND username= '" + usernameid + "'");
			
			while (rs.next()) {
				int userid = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String username = rs.getString("username");
				Date birthday = rs.getDate("birthday");
				
				customer = new Customer(userid, firstname, lastname, username, birthday);
			}
			
			con.close();		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return customer;
	}

	
	
	public boolean saveCustomer(Customer newCustomer) {
		if (newCustomer != null) {
			Connection con = DatabaseService.MysqlConnection();
			
			try {
				String query = "INSERT into customers (firstname, lastname, username, birthday, token, since, verified) values (?, ?, ?, ?, ?, ?, ?)";
				
				String fistname = newCustomer.getFirstname();
				String lastname = newCustomer.getLastname();
				String username = newCustomer.getUsername();
				Date birthday = newCustomer.getBirthday();
				
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				if(StringUtils.isBlank(fistname)) {
					fistname = "";
				}
				if(StringUtils.isBlank(lastname)) {
					lastname = "";
				}
				if(StringUtils.isBlank(username)) {
					username = "";
				}
				
		
				
				preparedStmt.setString(1, fistname);
				preparedStmt.setString(2, lastname);
				preparedStmt.setString(3, username);
				preparedStmt.setDate(4, new java.sql.Date(birthday.getTime()));
				preparedStmt.setString(5, "");
				preparedStmt.setDate(6, new java.sql.Date(new Date().getTime()) );
				preparedStmt.setBoolean(7, true);
				preparedStmt.execute();
				con.close();

				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
}
