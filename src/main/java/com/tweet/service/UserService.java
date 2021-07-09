package com.tweet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import com.tweet.util.UserDetails;

import com.tweet.dao.*;

public class UserService {

	UserData userData=new UserData();
	
	//------------------------send registered user Data to dao layer-----------------------------------------
	public void setUserData(List<UserDetails> userDetails) throws ParseException
	{
		
		for(UserDetails data:userDetails)
		{
			String firstName=data.getFirstName();
			String lastName=data.getLastName();
			String gender=data.getGender();
			String dob=data.getDob();
			String emailId=data.getEmail();
			String password = data.getPassword();
			SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date date=sdf.parse(dob);
			
			Date sqlDate= new Date(date.getTime());
			userData.addUserData(firstName, lastName, gender, sqlDate, emailId, password);
		}
	}
	
	//---------------------------validate if Login credentials are correct-----------------------------------------
	public boolean validateUser(String username, String password)
	{
		boolean validUser=true;
		UserDetails user=userData.checkLogin(username, password);
		if(user==null)
		{
			validUser=false;
		}
		return validUser;
	}
	
	//--------------------get user data using username and password after login-------------------------------------
	public UserDetails getUserData(String username, String password)
	{
		UserDetails user=userData.checkLogin(username, password);
		return user;
	}
	
	//-------------------------display all users data ----------------------------------------------------------
	public void displayUsers(List<UserDetails> users)
	{
		users.forEach( (user) -> 
		{ System.out.println("\nUser "+(users.indexOf(user)+1)+":- "+user.getFirstName()+" "+user.getLastName()+" -"+user.getStatus()); } );
	}
}
