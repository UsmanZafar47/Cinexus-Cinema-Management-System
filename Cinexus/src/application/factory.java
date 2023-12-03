package application;

import java.time.LocalDate;

public class factory 
{
	//------------ Users
	static User createUser()
	{
		User newUser = new User();
		return newUser;
	}
	static User createUser(User user)
	{
		User newUser = new User(user);
		return newUser;
	}
	static User createUser(String username, String password)
	{
		User newUser = new User(username, password);
		return newUser;
	}
	static User createNewUser(String name, String username, String password, String cnic, String email, String role)
	{
		User newUser = new User();
		newUser.newUser(name, username, password, cnic, email, role);
		return newUser;
	}
	
	//------------- Cinema
	static Cinema createNewCinema(String name, String location, int noseats, int manager_id)
	{
		Cinema newCinema = new Cinema(name, location, noseats, manager_id);
		return newCinema;
	}
	
	//-------------- Showings
	public static Showings createNewShowing(int movie, int cinema, String date) {
		Showings newShow = new Showings(movie, cinema, date);
		return newShow;
	}
}
