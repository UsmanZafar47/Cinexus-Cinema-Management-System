package application;

import dbhandler.TicketDatabaseConnecter;

public class Tickets 
{
	private int ticketID;
	private User user;
	private int ShowingID;
	private Showings show;
	private Cinema cinema;
	private Movie movie;
	private int SeatCount;
	private int price;
	private String status;
	
	TicketDatabaseConnecter TicketDB = new TicketDatabaseConnecter();
	
	public Tickets (User user, int showingID, int seatCount) 
	{
		setTicketID(-1);
		this.user = factory.ExistingUser(user.getUserID());
		this.ShowingID = showingID;
		this.show = factory.ExistingShowing(showingID);
		this.setCinema(factory.ExistingCinema(show.getCinema_id()));
		this.setMovie(factory.ExistingMovies(show.getMovie_id()));
		this.SeatCount = seatCount;
		setPrice((SeatCount*cinema.getSeatprice()));
		setStatus("Pending");
	}
	public Tickets (int ticketid) 
	{
		setTicketID(ticketid);
		this.user = factory.ExistingUser(user.getUserID());
		this.ShowingID = Integer.parseInt(TicketDB.get(ticketid, "showtime_id"));
		this.show = factory.ExistingShowing(ShowingID);
		this.setCinema(factory.ExistingCinema(show.getCinema_id()));
		this.setMovie(factory.ExistingMovies(show.getMovie_id()));
		this.SeatCount = Integer.parseInt(TicketDB.get(ticketid, "seatCount"));
		setPrice((SeatCount*cinema.getSeatprice()));
		setStatus("Pending");
	}
	public Tickets (Tickets ticket) 
	{
		setTicketID(ticket.ticketID);
		this.user = ticket.user;
		this.show = ticket.show;
		this.SeatCount = ticket.SeatCount;
		this.ShowingID = ticket.ShowingID;
		this.cinema = ticket.cinema;
		this.movie = ticket.movie;
		this.price = ticket.price;
		this.status = ticket.status;
	}
	
	public int getTicketID() {
		if (ticketID < 0)
			this.setTicketID(TicketDB.getID(ShowingID, user.getUserID()));
		return ticketID;
	}
	public void setTicketID(int ticketID)
	{
		this.ticketID = ticketID;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Showings getShowing() {
		return show;
	}
	public void setShowing(Showings show) {
		this.show = show;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSeatCount() {
		return SeatCount;
	}
	public void setSeatCount(int SeatCount) {
		this.SeatCount = SeatCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int InsertintoDB(String status)
	{
		if (status.equals("Reserved"))
				TicketDB.InsertinDB(this, status);
		else
		{
			TicketDB.MakePayment(this.user.getUserID(), this.getPrice());
			TicketDB.InsertinDB(this, status);
		}
		return this.getTicketID();
	}
}
