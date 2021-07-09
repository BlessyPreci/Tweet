package com.tweet.NewTweetApp;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tweet.service.RegistrationService;
import com.tweet.service.UserService;
import com.tweet.util.UserDetails;

//Main Class to start the application
public class App {
	public static void main(String[] args) throws ParseException, IOException {
		Scanner sc = new Scanner(System.in);
		boolean on = true;
		System.out.println("Welcome to Tweet App");
		do {
			System.out.println(
					"=========================================================================================================================\n");
			System.out.println("Please Enter the required option\n");
			System.out.println("Registration- press 1\nLogin- press 2\nForgot Password- press 3\nExit- press 4");

			int option = Integer.parseInt(sc.nextLine());
			switch (option) {
			case 1:
				System.out.println(
						"===========================================REGISTRATION PAGE===========================================================================\n");

				List<UserDetails> userData = new ArrayList<UserDetails>();
				UserService userService = new UserService();
				RegistrationService registerService = new RegistrationService();
				UserDetails ud = registerService.registerUser(sc);

				System.out.println("\nBelow are the User Details you have entered-\n");
				System.out.println(ud);
				System.out.println("To Confirm Registration press 1, else press any other key");
				String confirm = sc.nextLine();
				if (confirm.equals("1")) {
					userData.add(ud);
					userService.setUserData(userData);
				} else {
					System.out.println("\nRegistration cancelled, please Register to Start using Tweet App");
				}
				break;

			case 2:
				System.out.println(
						"====================================LOGIN PAGE=================================================\n");
				RegistrationService registerServ = new RegistrationService();
				boolean validLogin = registerServ.loginUser(sc);
				if (!validLogin) {
					System.out.println("\nSorry, Login Unsuccessfull!, please Register or check your credentials");
				}
				break;

			case 3:
				System.out.println("\nPlease enter your registered email id");
				String emaildIdToRestore = sc.nextLine();
				RegistrationService registerServ3 = new RegistrationService();
				registerServ3.forgotPassword(sc, emaildIdToRestore);
				break;

			case 4:
				System.out.println("press 1 to Exit, else any other key");
				String confirmExit = sc.nextLine();
				if (confirmExit.equals("1")) {
					System.out.println("Thank you!, Keep Tweeting!!");
					on = false;
				}
				break;

			default:
				System.out.println("please Enter Valid Option");
			}

		} while (on == true);

		sc.close();
	}
}
