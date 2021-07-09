package com.tweet.util;

public class UserDetails {

private String firstName;
private String lastName;
private String gender;
private String dob;
private String email;
private String password;
private String status;

public UserDetails(String firstName, String lastName, String gender, String dob, String email, String password, String status) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.gender = gender;
	this.dob = dob;
	this.email = email;
	this.password = password;
	this.status=status;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getDob() {
	return dob;
}

public void setDob(String dob) {
	this.dob = dob;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

@Override
public String toString() {
	return "UserDetails :\nFirst Name= " + firstName + "\nLast Name= " + lastName + "\nGender= " + gender + "\nDate of Birth= " + dob
			+ "\nEmail Id= " + email + "\nPassword= " + password ;
}



}
