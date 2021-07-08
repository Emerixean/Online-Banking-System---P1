package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.util.ConnectionFactory;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee selectEmployeeByLogin(String accountName, String accountPassword) {
		Employee e = null;
		String sql ="select * from employee_table where account_name = ? and account_password = ? ;";
		
		try(Connection conn = ConnectionFactory.getConnection()){
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			ps.setString(2, accountPassword);
			
			ResultSet rs =ps.executeQuery();
			
			rs.next();
			e = new Employee(
					rs.getInt("employee_id"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getString("account_name"),
					rs.getString("account_password")

					);
			
			
		}catch(SQLException exception) {

		}
		return e;
	}
}
