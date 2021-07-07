package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.util.ConnectionFactory;

public class AccountDaoImpl implements AccountDao {

	@Override
	public boolean insertAccount(Account a) {
		String sql= "insert into customer_accounts(account_number, customer_id, account_balance,"
				+ "account_type, account_approved, approved_by)"
				+ "values(?,?,?,?,?,?);";
		
		try (Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getAccountNumber());
			ps.setInt(2,  a.getCustomerId());
			ps.setDouble(3, a.getAccountBalance());
			ps.setString(4, a.getAccountType());
			ps.setBoolean(5, a.isAccountApproved());
			ps.setString(6, a.getApprovedBy());
			
			ps.execute();

		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<Account> selectAllAccounts() {
		List<Account> databaseAccounts = new ArrayList<>();
		
		String sql ="select * from customer_accounts;";
		
		try (Connection conn = ConnectionFactory.getConnection();){
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				databaseAccounts.add(new Account(
			
					rs.getInt("account_number"),
					rs.getInt("customer_id"),
					rs.getDouble("account_balance"),
					rs.getString("account_type"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return databaseAccounts;
	}

	@Override
	public List<Account> selectAllApproved() {
		List<Account> databaseAccounts = new ArrayList<>();
		
		String sql ="select * from customer_accounts where account_approved = true;";
		
		try (Connection conn = ConnectionFactory.getConnection();){
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				databaseAccounts.add(new Account(
			
					rs.getInt("account_number"),
					rs.getInt("customer_id"),
					rs.getDouble("account_balance"),
					rs.getString("account_type"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return databaseAccounts;
	}

	@Override
	public List<Account> selectAllUnapproved() {
		List<Account> databaseAccounts = new ArrayList<>();
		
		String sql ="select * from customer_accounts where account_approved = false;";
		
		try (Connection conn = ConnectionFactory.getConnection();){
			
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				databaseAccounts.add(new Account(
			
					rs.getInt("account_number"),
					rs.getInt("customer_id"),
					rs.getDouble("account_balance"),
					rs.getString("account_type"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return databaseAccounts;
	}

	@Override
	public List<Account> selectByAccountType(String type) {
		List<Account> databaseAccounts = new ArrayList<>();
		
		String sql ="select * from customer_accounts where account_type = ?;";
		
		try (Connection conn = ConnectionFactory.getConnection();){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, type);

			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				databaseAccounts.add(new Account(
			
					rs.getInt("account_number"),
					rs.getInt("customer_id"),
					rs.getDouble("account_balance"),
					rs.getString("account_type"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return databaseAccounts;
	}

	@Override
	public List<Account> selectAccountByCustomerId(int id) {
		List<Account> databaseAccounts = new ArrayList<>();
		
		String sql ="select * from customer_accounts where customer_id = ?;";
		
		try (Connection conn = ConnectionFactory.getConnection();){
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs =ps.executeQuery();
			
			while(rs.next()) {
				databaseAccounts.add(new Account(
			
					rs.getInt("account_number"),
					rs.getInt("customer_id"),
					rs.getDouble("account_balance"),
					rs.getString("account_type"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			));
			}
					
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return databaseAccounts;
	}


	@Override
	public Account selectAccountByAccountNumber(String accountNumber) {
		Account a = null;
		String sql ="select * from customer_accounts where account_number = ? ;";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountNumber);
			
			ResultSet rs =ps.executeQuery();
			
			rs.next();
			a = new Account(
			
					rs.getInt("account_number"),
					rs.getInt("customer_id"),
					rs.getDouble("account_balance"),
					rs.getString("account_type"),
					rs.getBoolean("account_approved"),
					rs.getString("approved_by")
			);
			

			
		}catch(SQLException e) {

		}
		
		return a;
	}

	@Override
	public boolean updateAccountApproval(boolean b, int accountNumber) {
		String sql ="update customer_accounts set account_approved = ? where account_number = ? ;";
		
		try(Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setBoolean(1, b);
			ps.setInt(2, accountNumber);
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean updateAccountBalance(double amount, String accountNumber) {
		String sql ="update customer_accounts set account_balance = ? where account_number = ? ;";
		
		try(Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, accountNumber);
			ps.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteAccount(Account a) {
		String sql ="delete from customer_accounts where account_number = ?;";
		
		try(Connection conn = ConnectionFactory.getConnection();){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1 , a.getAccountNumber());
		}catch(SQLException e) {
			
		}
		return true;
	}

}
