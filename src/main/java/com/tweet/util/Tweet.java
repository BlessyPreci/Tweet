package com.tweet.util;

public class Tweet {

	private String username;
	private String tweet;
	private String name;
	
	public Tweet(String username, String tweet, String name) {
		super();
		this.tweet = tweet;
		this.username = username;
		this.name = name;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Tweet [tweet=" + tweet + ", username=" + username + ", name=" + name + "]";
	}
	
	
	
	
}
