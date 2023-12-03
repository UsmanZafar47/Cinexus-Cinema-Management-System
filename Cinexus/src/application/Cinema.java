package application;

import dbhandler.CinemaDatabaseConnecter;

public class Cinema 
{
    private static int cinema_id;
    private static String name;
    private static String location;
    private static int manager_id;
    private static int seatprice;
    private static int noSeats;
	CinemaDatabaseConnecter CinemaDB = new CinemaDatabaseConnecter();
    
	public Cinema(int id) {
		this.setCinemaid(id);
		this.setName(CinemaDB.get(id, "name"));
		this.setLocation(CinemaDB.get(id, "location"));
		this.setManager_id(Integer.parseInt(CinemaDB.get(id, "manager_id")));
		this.setSeatprice(Integer.parseInt(CinemaDB.get(id, "seatprice")));
		this.setNoSeats(Integer.parseInt(CinemaDB.get(id, "noSeats")));
	}
	
    public Cinema(int cinema_id, String name, String location, int manager_id)
    {
    	setCinemaid(cinema_id);
    	setName(name);
    	setLocation(location);
    	setManager_id(manager_id);
    }
    
    public Cinema(String name, String location, int noSeats, int manager_id)
    {
    	setName(name);
    	setLocation(location);
    	setManager_id(manager_id);

    	CinemaDB.InsertCinema(this);
		setCinemaid(CinemaDB.getID(this.name, this.location, this.manager_id));
		while(noSeats>0) {
			CinemaDB.AddSeats(this.getCinemaid());
			noSeats--;
		}
	}
    
	public int getCinemaid() 
	{
		return cinema_id;
	}
	public void setCinemaid(int cinema_id) {
		this.cinema_id = cinema_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}

	public int getSeatprice() {
		return seatprice;
	}

	public void setSeatprice(int seatprice) {
		Cinema.seatprice = seatprice;
	}

	public int getNoSeats() {
		return noSeats;
	}

	public void setNoSeats(int noSeats) {
		Cinema.noSeats = noSeats;
	}
}
