package com.tweet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tweet.util.Tweet;

public class TweetData {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tweetdata";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	//----------------------------method to post tweet and add tweet data to database-----------------------------
	public void postTweet(String emailId, String tweet, String name) {
		Connection connection = null;
		try {

			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement statement = connection.prepareStatement("insert into tweet_data values(?,?,?)");
			statement.setString(1, emailId);
			statement.setString(2, tweet);
			statement.setString(3, name);
			int insertedRows = statement.executeUpdate();

			if (insertedRows >= 1)
				System.out.println("\nTweet Posted!!");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//----------------------------method to get Tweet data of particular user-----------------------------------------
	public List<Tweet> getMyTweets(String emailId) {
		Connection connection = null;
		List<Tweet> tweetData = new ArrayList<Tweet>();
		Tweet twt = null;
		try {

			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement statement = connection.prepareStatement("select * from tweet_data where email_id = ?");
			statement.setString(1, emailId);
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				String username = results.getString(1);
				String tweet = results.getString(2);
				String name = results.getString(3);
				twt = new Tweet(username, tweet, name);
				tweetData.add(twt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tweetData;
	}

	//----------------------------method to get tweet data of all users-----------------------------------------
	public List<Tweet> getAllTweets() {
		Connection connection = null;
		List<Tweet> tweetData = new ArrayList<Tweet>();
		Tweet twt = null;
		try {

			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			PreparedStatement statement = connection.prepareStatement("select * from tweet_data");
			ResultSet results = statement.executeQuery();

			while (results.next()) {
				String username = results.getString(1);
				String tweet = results.getString(2);
				String name = results.getString(3);
				twt = new Tweet(username, tweet, name);
				tweetData.add(twt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tweetData;
	}

}
