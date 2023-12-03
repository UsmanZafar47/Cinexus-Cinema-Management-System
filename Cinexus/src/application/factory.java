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
	static User ExistingUser(int id)
	{
		User newUser = new User(id);
		return newUser;
	}
	//------------- Cinema
	static Cinema createNewCinema(String name, String location, int noseats, int manager_id)
	{
		Cinema newCinema = new Cinema(name, location, noseats, manager_id);
		return newCinema;
	}
	static Cinema ExistingCinema(int Cinema_id)
	{
		Cinema newCinema = new Cinema(Cinema_id);
		return newCinema;
	}
	//-------------- Showings
	public static Showings createNewShowing(int movie, int cinema, String date) {
		Showings newShow = new Showings(movie, cinema, date);
		return newShow;
	}
	public static Showings ExistingShowing(int showing_id) {
		Showings newShow = new Showings(showing_id);
		return newShow;
	}
	//-------------- Movies
	static Movie ExistingMovies(int Movie_id)
	{
		Movie newMovie = new Movie(Movie_id);
		return newMovie;
	}
	//-------------- Tickets
	public static Tickets createTempTicket(User user, int showingID, int seatCount) {
		Tickets newticket = new Tickets(user, showingID, seatCount);
		return newticket;
	}
	public static Tickets createTicket(Tickets ticket) {
		Tickets newticket = new Tickets(ticket);
		return newticket;
	}
}
