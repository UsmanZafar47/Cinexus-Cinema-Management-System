package application;

import dbhandler.ShowingDatabaseConnecter;

public class Showings {
	private int showtime_id;
	private int movie_id;
	private int cinema_id;
    private String showtime;
    ShowingDatabaseConnecter ShowingsDB = new ShowingDatabaseConnecter();

	public Showings(int movie, int cinema, String date) {
		this.setShowtime_id(-1);
		this.setMovie_id(movie);
		this.setCinema_id(cinema);
		this.setShowtime(""+date);
		ShowingsDB.InsertinDB(this);
		this.setShowtime_id(getShowtime_id());
	}
	
	public Showings(int id) {
		this.setShowtime_id(id);
		this.setMovie_id(Integer.parseInt(ShowingsDB.get(id, "movie_id")));
		this.setCinema_id(Integer.parseInt(ShowingsDB.get(id, "cinema_id")));
		this.setShowtime(ShowingsDB.get(id, "showtime"));
	}

	public int getShowtime_id() {
		if (showtime_id == -1)
			this.setShowtime_id(ShowingsDB.getID(getMovie_id(), getCinema_id()));
		return showtime_id;
	}
	public void setShowtime_id(int showtime_id) {
		this.showtime_id = showtime_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public int getCinema_id() {
		return cinema_id;
	}
	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}
	public String getShowtime() {
		return showtime;
	}
	public void setShowtime(String showtime) {
		this.showtime = showtime;
	}
}