package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;

import com.revature.util.ConnectionFactory;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public boolean insertCustomer(Customer c) {
		
		String sql ="INSERT INTO customer_table (first_name, last_name, account_name,"
				+ "account_password,phone_number,email,date_of_birth,address,"
				+ "apartment,city,state,country,postal_code,account_approved,"
				+ "approved_by) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try (Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2,  c.getLastName());
			ps.setString(3, c.getAccountName());
			ps.setString(4, c.getAccountPassword());
			ps.setString(5, c.getPhoneNumber());
			ps.setString(6, c.getEmail());
			ps.setDate(7, c.getDateOfBirth());
			ps.setString(8, c.getAddress());
			ps.setString(9, c.getApartment());
			ps.setString(10, c.getCity());
			ps.setString(11, c.getState());
			ps.setString(12, c.getCountry());
			ps.setString(13, c.getPostalCode());
			ps.setBoolean(14, c.isAccountApproved());
			ps.setString(15, c.getApprovedBy());
			
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public List<Customer> selectAllCustomers() {
		List<Customer> databaseCustomers = new ArrayList<>();
		
		String sql ="select * from customer_table;";
		try (Connection conn = ConnectionFactory.getConnection();){
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				databaseCustomers.add(new Customer(
			
					rs.getInt("customer_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("account_name"),
					rs.getString("account_password"),
					rs.getString("phone_number"),
					rs.getString("email"),
					rs.getString("date_of_birth"),
					rs.getString("address"),
					rs.getString("apartment"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getString("country"),
					rs.getString("postal_code"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return databaseCustomers;
	}


	@Override
	public List<Customer> selectAllApproved() {
		List<Customer> databaseCustomers = new ArrayList<>();
		
		String sql ="select * from customer_table where account_approved = true;";
		try (Connection conn = ConnectionFactory.getConnection();){
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				databaseCustomers.add(new Customer(
			
					rs.getInt("customer_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("account_name"),
					rs.getString("account_password"),
					rs.getString("phone_number"),
					rs.getString("email"),
					rs.getString("date_of_birth"),
					rs.getString("address"),
					rs.getString("apartment"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getString("country"),
					rs.getString("postal_code"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return databaseCustomers;
	}


	@Override
	public List<Customer> selectAllUnapproved() {
		List<Customer> databaseCustomers = new ArrayList<>();
		
		String sql ="select * from customer_table where account_approved = false;";
		try (Connection conn = ConnectionFactory.getConnection();){
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				databaseCustomers.add(new Customer(
			
					rs.getInt("customer_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("account_name"),
					rs.getString("account_password"),
					rs.getString("phone_number"),
					rs.getString("email"),
					rs.getString("date_of_birth"),
					rs.getString("address"),
					rs.getString("apartment"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getString("country"),
					rs.getString("postal_code"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();

		}

		return databaseCustomers;
	}

	@Override
	public Customer selectCustomerByCustomerId(int id) {
		Customer c = null;
		String sql ="select * from customer_table where customer_id = ?";
		try (Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,id);
			
			ResultSet rs =ps.executeQuery();
			rs.next();
			c = new Customer(
					rs.getInt("customer_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("account_name"),
					rs.getString("account_password"),
					rs.getString("phone_number"),
					rs.getString("email"),
					rs.getString("date_of_birth"),
					rs.getString("address"),
					rs.getString("apartment"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getString("country"),
					rs.getString("postal_code"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
					);
			
					
		}catch(SQLException e) {
			e.printStackTrace();

		}

		return c;
	}

	@Override
	public Customer selectCustomerByAccountName(String accountName) {
		Customer c = null;
		String sql ="select * from customer_table where account_name = ? ;";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			
			ResultSet rs =ps.executeQuery();
			
			rs.next();
			c = new Customer(
					rs.getInt("customer_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("account_name"),
					rs.getString("account_password"),
					rs.getString("phone_number"),
					rs.getString("email"),
					rs.getString("date_of_birth"),
					rs.getString("address"),
					rs.getString("apartment"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getString("country"),
					rs.getString("postal_code"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
					);
			

			
		}catch(SQLException e) {

		}
		
		return c;
	}
	
	@Override
	public Customer selectCustomerByLogin(String accountName, String accountPassword) {
		Customer c = null;
		String sql ="select * from customer_table where account_name = ? and account_password = ? ;";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			ps.setString(2, accountPassword);
			
			ResultSet rs =ps.executeQuery();
			
			rs.next();
			c = new Customer(
					rs.getInt("customer_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("account_name"),
					rs.getString("account_password"),
					rs.getString("phone_number"),
					rs.getString("email"),
					rs.getString("date_of_birth"),
					rs.getString("address"),
					rs.getString("apartment"),
					rs.getString("city"),
					rs.getString("state"),
					rs.getString("country"),
					rs.getString("postal_code"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
					);
			
			
		}catch(SQLException e) {

		}
		return c;
	}

	@Override
	public boolean updateCustomerApproval(boolean b, int customerId) {
		String sql ="update customer_table set account_approved = ? where customer_id = ? ;";
		
		try(Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, b);
			ps.setInt(2, customerId);
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateCustomer(Customer c) {
		
		String sql ="update customer_table set first_name = ?, last_name = ?, account_name = ?,"
				+ "account_password = ?,phone_number = ?,email = ?,date_of_birth = ?,address = ?,"
				+ "apartment = ?,city = ?,state = ?,country = ?,postal_code = ?,account_approved = ?,"
				+ "approved_by = ?) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		try (Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getFirstName());
			ps.setString(2,  c.getLastName());
			ps.setString(3, c.getAccountName());
			ps.setString(4, c.getAccountPassword());
			ps.setString(5, c.getPhoneNumber());
			ps.setString(6, c.getEmail());
			ps.setDate(7, c.getDateOfBirth());
			ps.setString(8, c.getAddress());
			ps.setString(9, c.getApartment());
			ps.setString(10, c.getCity());
			ps.setString(11, c.getState());
			ps.setString(12, c.getCountry());
			ps.setString(13, c.getPostalCode());
			ps.setBoolean(14, c.isAccountApproved());
			ps.setString(15, c.getApprovedBy());
			
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public boolean deleteCustomer(Customer c) {
		String sql ="delete from customer_table where customer_id = ?;";
		
		try(Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1 , c.getId());
		}catch(SQLException e) {
			
		}
		return true;
	}

}
