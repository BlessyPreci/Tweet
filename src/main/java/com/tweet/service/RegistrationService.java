package com.tweet.service;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tweet.dao.UserData;
import com.tweet.util.UserDetails;

public class RegistrationService {
	UserData userData = new UserData();

	//---------------------------------- get Data for registration from user-----------------------------------
	public UserDetails registerUser(Scanner sc) {

		System.out.println("Enter the below fields required for Registration");
		System.out.println("Enter First Name (Required)");
		String firstName = sc.nextLine();
		while (!isValid(firstName)) {
			System.out.println("First Name should not be null");
			firstName = sc.nextLine();
		}

		System.out.println("\nEnter Last Name (Optional)");
		String lastName = sc.nextLine();

		System.out.println("\nEnter Gender Option from below Options (Required)");
		System.out.println("1.Male 2.Female 3.Other");
		String gender = sc.nextLine();
		while (!isValid(gender)) {
			System.out.println("Gender should not be null");
			gender = sc.nextLine();
		}

		System.out.println("\nEnter Your Date of Birth in format dd-MM-yyyy (optional)");
		String dob = sc.nextLine();

		System.out.println("\nEnter Your Email id (will be used as user name for login)");
		String emailId = sc.nextLine();
		boolean validEmail = isEmailValid(emailId);
		while (validEmail == false) {
			System.out.println("username should be Valid and should not be null");
			emailId = sc.nextLine();
			validEmail = isEmailValid(emailId);
		}

		System.out.println("\nEnter Password (Required)");
		System.out.println(
				"Password should be combination of letter with uppercase, lowercase and number with minimum length 8");
		String password = sc.nextLine();
		boolean validPass = isPasswordValid(password);
		while (validPass == false) {
			System.out.println("password should be Valid and should not be null");
			password = sc.nextLine();
			validPass = isPasswordValid(password);
		}
		System.out.println(
				"--------------------------------------------------------------------------------------------------------------");
		UserDetails ud = new UserDetails(firstName, lastName, gender, dob, emailId, password, "Not Active");
		return ud;
	}

	//------------------------------------ get login details from registered user--------------------------------------------
	public boolean loginUser(Scanner sc) {
		UserService userServ = new UserService();
		TweetService tweetServ = new TweetService();
		System.out.println("Enter your User Name(your registered email id)");
		String username = sc.nextLine();
		boolean validEmail = isEmailValid(username);
		while (validEmail == false) {
			System.out.println("username should be Valid and should not be null");
			username = sc.nextLine();
			validEmail = isEmailValid(username);
		}

		System.out.println("\nEnter your password");
		String password = sc.nextLine();

		boolean validUser = userServ.validateUser(username, password);
		if (validUser) {
			System.out.println("\nLogin SuccessFull!!");
			// Mark user status as active in database after user login
			userData.changeStatus(username, true);
			UserDetails user = userServ.getUserData(username, password);
			tweetServ.TweetMenu(user, sc);
		}
		return validUser;
	}

	//------------------ Check Email for forgot password and send new password to dao layer--------------------------
	public void forgotPassword(Scanner sc, String emailId) {

		boolean validEmail = isEmailValid(emailId);
		while (validEmail == false) {
			System.out.println("username should be Valid and should not be null");
			emailId = sc.nextLine();
			validEmail = isEmailValid(emailId);
		}
		boolean emailExists = userData.ifEmailExists(emailId);

		if (emailExists) {
			System.out.println("please Enter New Password");
			String newPass = sc.nextLine();
			userData.updatePassword(emailId, newPass);
		} else {
			System.out.println("This email id is not registered, please Enter Registered Email Id");
			emailId = sc.nextLine();
			forgotPassword(sc, emailId);
		}
	}

	public boolean isValid(String value) {
		boolean f = true;
		if (value == null || value.equals("") || value.equals(" "))
			f = false;
		else
			f = true;

		return f;
	}

	public boolean isEmailValid(String emailId) {
		boolean validEmail = true;
		String validEmailId = "^[a-zA-z0-9+_.-]+@[a-zA-Z0-9.-]+$";
		Pattern p2 = Pattern.compile(validEmailId);
		Matcher m2 = p2.matcher(emailId);

		if (!isValid(emailId) || !m2.matches()) {
			validEmail = false;
		}
		return validEmail;
	}

	public boolean isPasswordValid(String password) {
		boolean validPass = true;
		String validPassword = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,20}$";
		Pattern p = Pattern.compile(validPassword);
		Matcher m = p.matcher(password);

		if (!isValid(password) || !m.matches()) {
			validPass = false;
		}
		return validPass;
	}
}
