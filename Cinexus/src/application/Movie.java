package application;

public class Movie {
    private int id;
    private String title;
    private int duration;
    private String rating;

    public Movie(int id, String title, int duration, String rating) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.rating = rating;
    }

    
	public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }
}