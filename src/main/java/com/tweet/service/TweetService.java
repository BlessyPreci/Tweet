package com.tweet.service;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tweet.dao.TweetData;
import com.tweet.dao.UserData;
import com.tweet.util.Tweet;
import com.tweet.util.UserDetails;

public class TweetService {

	TweetData tweetData=new TweetData();
	UserData users=new UserData();
	UserService userServ=new UserService();
	RegistrationService rs=new RegistrationService();
	
	//----------------------Represents Tweet Menu to logged in user------------------------------------------------
	public void TweetMenu(UserDetails user, Scanner sc) 
	{
		String fullname=user.getFirstName()+" "+user.getLastName();
		String emailId=user.getEmail();
		String validPass ="^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{8,20}$";
		Pattern p3 = Pattern.compile(validPass);
		boolean login=true;
		System.out.println("\n------------------------------------ Welcome "+fullname+"-------------------------------------------");
		
		do 
		{
			System.out.println("=======================================================================================================");
			System.out.println("\nPost a Tweet -press 1"
					         + "\nView My Tweets -press 2"
					         + "\nView All Tweets -press 3"
					         + "\nView All Users -press 4"
					         + "\nReset Password -press 5"
					         + "\nLogout -press 6");
			
			int option=Integer.parseInt(sc.nextLine());
			
			switch(option)
			{
			case 1:
				System.out.println("\nEnter The tweet you want to post(max 200 characters)");
				String tweet=sc.nextLine();
				tweetData.postTweet(emailId, tweet,fullname);
				System.out.println("Your Tweet:- "+tweet);
				break;
				
			case 2:
				System.out.println("\nYou have posted following tweets till now, keep tweeting");
				List<Tweet> tweetList=tweetData.getMyTweets(emailId);
				retrieveMyTweets(tweetList);
				System.out.println("-------------------------------------------------------------------------------\n");
				break;
				
			case 3:
				System.out.println("\nTweets of all Users: ");
				List<Tweet> allTweets=tweetData.getAllTweets();
				retrieveAllTweets(allTweets);
				System.out.println("-----------------------------------------------------------------------------------\n");
				break;
				
			case 4:
				System.out.println("\nBelow are all the users in Tweet App-");
				List<UserDetails> usersList=users.getAllUsers();
				userServ.displayUsers(usersList);
				System.out.println("----------------------------------------------------------------------------------\n");
				break;
				
			case 5:
				System.out.println("please enter your old password:");
				String oldPass=sc.nextLine();
				boolean validPassword=userServ.validateUser(emailId, oldPass);
				if(validPassword)
				{
					System.out.println("\nEnter New Password");
					String newPass= sc.nextLine();
					 Matcher m3 = p3.matcher(newPass);
				   	 
				   	 if(!rs.isValid(newPass) || !m3.matches())
				   	 {
				   		 System.out.println("Password should be coombination of letter with uppercase, lowercase and number with minimum length 8");
				   		 System.out.println("password should be Valid and should not be null");
				   		 newPass =sc.nextLine();
				   	 }
					users.updatePassword(emailId, newPass);
				}
				break;
				
			case 6:
				System.out.println("Are you sure want to Log out?, press 1 if yes");
				String confirm=sc.nextLine();
					if(confirm.equals("1"))
					{
					users.changeStatus(emailId, false);
					login=false;
					System.out.println("You have been logged out Sucessfully!");
					}
				break;
			}
		
		}while(login==true);
			
	}
	
	public void retrieveMyTweets(List<Tweet> tweetData)
	{
		tweetData.forEach( (tweets) -> 
		{ System.out.println("\nTweet "+(tweetData.indexOf(tweets)+1)+": "+ tweets.getTweet()); } );
	}
	public void retrieveAllTweets(List<Tweet> tweetData)
	{
		tweetData.forEach( (tweets) -> 
		{ System.out.println("\nTweet By "+tweets.getName()+": "+ tweets.getTweet()); } );
	}
}

