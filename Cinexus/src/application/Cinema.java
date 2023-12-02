package application;

public class Cinema 
{
    private static int cinema_id;
    private static String name;
    private static String location;
    private static int manager_id;
    
    public Cinema(int cinema_id, String name, String location, int manager_id)
    {
    	setCinemaid(cinema_id);
    	setName(name);
    	setLocation(location);
    	setManager_id(manager_id);
    }
    
	public static int getCinemaid() {
		return cinema_id;
	}
	public void setCinemaid(int cinema_id) {
		this.cinema_id = cinema_id;
	}
	public static String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static String getLocation() {
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
}
