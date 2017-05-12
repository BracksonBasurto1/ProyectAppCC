package com.tecsup.lab9.bean;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.tecsup.lab8.db.ConectionDB;

@ManagedBean(name = "managerData", eager = true)
@SessionScoped
public class ManagerData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String inUser;
	private String inPassword;
	private String inFirstname;
	private String inLastname;
	private String inEmail;

	private Employee employee;
    //private ArrayList<Employee> employees;

	
	public String getInUser() {
		return inUser;
	}

	public void setInUser(String inUser) {
		this.inUser = inUser;
	}
	public String getInPassword(){
		return inPassword;
	}
	public void setInPassword(String inPassword){
		this.inPassword = inPassword;
	}

	public String getInFirstname() {
		return inFirstname;
	}

	public void setInFirstname(String inFirstname) {
		this.inFirstname = inFirstname;
	}

	public String getInLastname() {
		return inLastname;
	}

	public void setInLastname(String inLastname) {
		this.inLastname = inLastname;
	}

	public String getInEmail() {
		return inEmail;
	}

	public void setInEmail(String inEmail) {
		this.inEmail = inEmail;
	}

	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Employee> getEmployees() {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection con = ConectionDB.getConnection();
		String stm = "Select employee_id, first_name, last_name, email from users";
		ArrayList<Employee> records = new ArrayList<Employee>();
		//employees = new ArrayList<Employee>();
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setUser(rs.getString(1));
				emp.setPassword(rs.getString(2));
				emp.setFirstname(rs.getString(3));
				emp.setLastname(rs.getString(4));
				emp.setEmail(rs.getString(5));
				records.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return records;
		//return employees;
	}

	public String chooseEmployee(){
		
	    this.setInUser(getEmployee().getUser());
	    this.setInPassword(getEmployee().getPassword());
	    this.setInFirstname(getEmployee().getFirstname());
	    this.setInLastname(getEmployee().getLastname());
	    this.setInEmail(getEmployee().getEmail());
	    
	    return null;
	 }
	
	/**
	 * 
	 * @return
	 */
	public String createEmployee() {

		PreparedStatement pst = null;
		Connection con = ConectionDB.getConnection();
		String stm = "INSERT INTO test (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL) VALUES (?,?,?,?)";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, getInUser());
			pst.setString(2, getInPassword());
			pst.setString(3, getInFirstname());
			pst.setString(4, getInLastname());
			pst.setString(5, getInEmail());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		resetField();

		return null;
	}


	/**
	 * 
	 * @return
	 */
	public String deleteEmployee() {
		
		PreparedStatement pst = null;
		Connection con = ConectionDB.getConnection();
		String stm = "DELETE FROM test WHERE EMPLOYEE_ID =? ";
		try {
			pst = con.prepareStatement(stm);
			//pst.setInt(1, getEmployee().getId());
			pst.setString(1, getInUser());
			pst.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		resetField();
		
		return null;
	}

	
	
	public String updateEmployee() {

		PreparedStatement pst = null;
		Connection con = ConectionDB.getConnection();
		String stm = "UPDATE EMPLOYEES_2 SET FIRST_NAME=?, LAST_NAME = ?, SALARY = ? WHERE EMPLOYEE_ID = ?";
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, getInUser());
			pst.setString(2, getInPassword());
			pst.setString(3, getInFirstname());
			pst.setString(4, getInLastname());
			pst.setString(5, getInEmail());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		resetField();
		
		return null;
	}

	private void resetField(){
		
		this.inUser = "";
		this.inPassword = "";
		this.inFirstname = "";
		this.inLastname = "";
		this.inEmail = "";
	}

	
	 public String login() {
		 PreparedStatement pst = null;
		Connection con = ConectionDB.getConnection();
		String stm ="select user ";
		
		try{
			pst = con.prepareStatement(stm);
			pst.setString(1, getInUser());
			pst.setString(2, getInPassword());
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		resetField();	
return null;
	}
	 
	
}
