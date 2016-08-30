package com.weekfourproject;

import java.util.Scanner;

public class MovieTheater 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		String userMenuInput = null;
		boolean menuCorrect = false;

		System.out.println("Welcome to the Movie Database!");
		
		do {
			System.out.println("Press 1 to read the movie database." + "\nPress 2 to add to the movie database." + "\nPress 3 to delete a movie from the database." + "\nPress 4 to update a movie.");
			userMenuInput = sc.nextLine();
			switch (userMenuInput) {
			case "1":
				DAO.readFromDB();
				break;
			case "2":
				//DAO.writeToDB();
				break;
			case "3":
				DAO.removeFromDB();
				break;
			case "4":
				DAO.updateDB(); 
				break; 
			default:
				System.out.println("You've entered an invalid option");
				menuCorrect = true;
				break;
			}

		} while (menuCorrect);

	}
} 