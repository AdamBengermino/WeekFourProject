package com.weekfourproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class DAO 
{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 

	static final String DB_URL = "jdbc:mysql://localhost:3306/?user=root&autoReconnect=true&useSSL=false";
	static final String USER = "root";
	static final String PASSWORD = "root";
	
	static Connection CONN= null;
	static Statement STMT= null;
	static PreparedStatement PREP_STMT = null;
	static ResultSet RES_SET= null;
	
	static Scanner sc = new Scanner(System.in);

	
	public static void connToDB()
	{
		try {
			
			Class.forName(JDBC_DRIVER);
			System.out.println("Trying to connect to the Database...");
			CONN = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected to the database.");
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to connect to the databsae.");
			e.printStackTrace();
		}
	}
	
	
	public static void readFromDB()
	{
		
		connToDB();
		
		ArrayList<Movie>ourMovies = new ArrayList<>();
		try 
		{
			STMT = CONN.createStatement();
			RES_SET = STMT.executeQuery("SELECT * FROM movies.movies;");
			
				while(RES_SET.next())
				{
					Movie movieInDB = new Movie();
					movieInDB.setMovieID(RES_SET.getString("movie_id"));
					movieInDB.setMovieTitle(RES_SET.getString("title"));
					movieInDB.setMovieRating(RES_SET.getString("rating"));
					movieInDB.setMovieGenre(RES_SET.getString("genre"));
					movieInDB.setMovieLength(RES_SET.getString("length"));
				
					ourMovies.add(movieInDB);
				}//while
			
				for (Movie movie : ourMovies) 
				{
					System.out.println(movie.toString());
				}//for
					
		}//try 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public static void writeToDB(Movie movie) 
	
	{

		Movie movieToAdd = new Movie();

		movieToAdd = movie;
		
		connToDB();
		
		try 
		{
			PREP_STMT = CONN.prepareStatement(insertToDB);
			
			PREP_STMT.setString(1, movieToAdd.getMovieTitle());
			PREP_STMT.setString(2, movieToAdd.getMovieRating());
			PREP_STMT.setString(3, movieToAdd.getMovieGenre());
			PREP_STMT.setString(4, movieToAdd.getMovieLength());
			
			PREP_STMT.executeUpdate();
			
		} 
		
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private static String insertToDB = "INSERT INTO `movies`.`movies`"
			+ "(title, rating, genre, length)"
			+ " VALUES "
			+ "(?, ?, ?, ?)";

	private static Movie aboutTheMovie() 
	{

		Movie movieToAdd = new Movie();

		System.out.println("What is the movie title?");
		movieToAdd.setMovieTitle(sc.nextLine());

		System.out.println("What is the movie's rating?");
		movieToAdd.setMovieRating(sc.nextLine());

		System.out.println("What is the movie's genre?");
		movieToAdd.setMovieGenre(sc.nextLine());
		
		System.out.println("What is the movie's length?");
		movieToAdd.setMovieLength(sc.nextLine());


		return movieToAdd;

	}
	
	public static void removeFromDB()
	{
		Movie movieToDelete = new Movie();
		
		movieToDelete = removedMovie(); 
		
		connToDB();
		
		try
			{
				PREP_STMT =CONN.prepareStatement(deleteFromDB);
				PREP_STMT.setString(1, movieToDelete.getMovieID());
				PREP_STMT.executeUpdate();
			}
		
			catch (SQLException e)
			{
				
				e.printStackTrace();
			}
	} 
	
	
	public static String deleteFromDB = "DELETE FROM `movies`.`movies`"
		
				+ "WHERE"
				+ "(movie_id)"
				+ "=(?)";
	
	public static Movie removedMovie()
	
	{		
			Movie movieToDelete = new Movie(); 
			
			System.out.println("What is ID of the movie you'd like to delete?");
			movieToDelete.setMovieID(sc.nextLine());
			
			return movieToDelete;
	}
	
	public static void updateDB() 
	
	{

		Movie movieToUpdate = new Movie();

		movieToUpdate = changeTheMovie();
		
		connToDB();
		
		try 
		{
			PREP_STMT = CONN.prepareStatement(updateToDB);
			
			PREP_STMT.setString(1, movieToUpdate.getMovieID());
			PREP_STMT.setString(2, movieToUpdate.getMovieTitle());
			PREP_STMT.setString(3, movieToUpdate.getMovieRating());
			PREP_STMT.setString(4, movieToUpdate.getMovieGenre());
			PREP_STMT.setString(5, movieToUpdate.getMovieLength());
			PREP_STMT.setString(6, movieToUpdate.getMovieID());
			
			PREP_STMT.executeUpdate();
			
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}

	}
	private static String updateToDB = "UPDATE `movies`.`movies`"
			+ "SET"
			+ " movie_id= ?, title= ?, rating= ?, genre= ?, length= ?"
			+ " WHERE "
			+ "`movie_id`"
			+ "= ?";

	private static Movie changeTheMovie() {

		Movie movieToUpdate = new Movie();
		
		System.out.println("What is the movie ID?");
		movieToUpdate.setMovieID(sc.nextLine());

		System.out.println("What is the movie title?");
		movieToUpdate.setMovieTitle(sc.nextLine());

		System.out.println("What is the movie's rating?");
		movieToUpdate.setMovieRating(sc.nextLine());

		System.out.println("What is the movie's genre?");
		movieToUpdate.setMovieGenre(sc.nextLine());
		
		System.out.println("What is the movie's length?");
		movieToUpdate.setMovieLength(sc.nextLine());


		return movieToUpdate;

	}
}//class
	
	
	
