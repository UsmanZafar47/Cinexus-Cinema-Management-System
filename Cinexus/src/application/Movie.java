package application;

import dbhandler.MovieDatabaseConnecter;

public class Movie {
    private int id;
    private String title;
    private int duration;
    private String rating;
	MovieDatabaseConnecter MovieDB = new MovieDatabaseConnecter();

    public Movie(int id, String title, int duration, String rating) {
        this.setId(id);
        this.setTitle(title);
        this.setDuration(duration);
        this.setRating(rating);
    }

	public Movie(int id) {
		this.setId(id);
		this.setTitle(MovieDB.get(id, "title"));
		this.setDuration(Integer.parseInt(MovieDB.get(id, "duration")));
		this.setRating(MovieDB.get(id, "rating"));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
}