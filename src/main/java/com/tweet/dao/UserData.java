package com.tweet.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tweet.util.UserDetails;

public class UserData {

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tweetdata";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	//--------------------------- Add user data to table user_details-----------------------------------------------------
	public void addUserData(String firstName, String lastName, String gender, Date dob, String emailId,  String password) {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement statement = connection.prepareStatement("insert into user_details values(?,?,?,?,?,?,?)");
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, gender);
			statement.setDate(4, dob);
			statement.setString(5, emailId);
			statement.setString(6, password);
			statement.setString(7, "Not Active");
			int insertedRows = statement.executeUpdate();

			if (insertedRows >= 1)
				System.out.println("User Registered Successfully!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//---------------------get logged in user data and check if user exists and log in user----------------------
	public UserDetails checkLogin(String username, String password) {
		Connection connection = null;
		UserDetails user = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement statement = connection
					.prepareStatement("select * from user_details where email_id = ? and password = ?");
			statement.setString(1, username);
			statement.setString(2, password);

			ResultSet results = statement.executeQuery();

			if (results.next()) {
				String firstname = results.getString(1);
				String lastname = results.getString(2);
				String gender = results.getString(3);
				String dob = results.getString(4);
				String emaild = results.getString(5);
				String pass = results.getString(6);
				String status = results.getString(7);
				user = new UserDetails(firstname, lastname, gender, dob, emaild, pass,status);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return user;
	}

	//---------------------------------get all users data----------------------------------------------------
	public List<UserDetails> getAllUsers() {
		Connection connection = null;
		List<UserDetails> users = new ArrayList<UserDetails>();
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement statement = connection.prepareStatement("select * from user_details");

			ResultSet results = statement.executeQuery();

			while (results.next()) {
				String firstname = results.getString(1);
				String lastname = results.getString(2);
				String gender = results.getString(3);
				String dob = results.getString(4);
				String emaild = results.getString(5);
				String pass = results.getString(6);
				String status = results.getString(7);
				UserDetails user = new UserDetails(firstname, lastname, gender, dob, emaild, pass,status);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return users;
	}

	//------------------------------------update password in database-------------------------------------
	public void updatePassword(String emailId, String password) {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement statement = connection
					.prepareStatement("UPDATE user_details SET password = ? where email_id = ?");
			statement.setString(1, password);
			statement.setString(2, emailId);
			statement.executeUpdate();

			System.out.println("Password Updated successfully!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//--------------------------check is username exists in database---------------------------------------------
	public boolean ifEmailExists(String emailId) {
		boolean validEmail = false;
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement statement = connection.prepareStatement("select * from user_details where email_id=?");
			statement.setString(1, emailId);

			ResultSet results = statement.executeQuery();

			if (results.next())
				validEmail = true;
			else
				validEmail = false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return validEmail;
	}

	//-------------------------------Upadate User status in database------------------------------------------------
	public void changeStatus(String emailId, boolean isActive)
	{
		Connection connection = null;
		PreparedStatement statement=null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			if(isActive)
			{
			statement = connection.prepareStatement("UPDATE user_details SET status='Active' where email_id=?");
			statement.setString(1, emailId);
			}
			else
			{
			statement = connection.prepareStatement("UPDATE user_details SET status='Not Active' where email_id=?");
			statement.setString(1, emailId);
			}
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
