package application;
import dbhandler.UserDatabaseConnecter;


public class User 
{
	int id;
	String Username;
	String Password;
	String Role;
	UserDatabaseConnecter UserDB = new UserDatabaseConnecter();
	
	public User() 
	{
		this.id = -1;
	}
	public User(User user) 
	{
		this.id = user.id;
		this.Username = user.Username;
		this.Password = user.Password;
		this.Role = user.Role;
	}
	public User(String username, String password) 
	{
		this.Username = username;
		this.Password = password;
		this.id = getUserID(this);
		this.Role = getUserRole(this);
	}
	
	public int getUserID(User user)
	{
		String uID = UserDB.getID(user.Username, user.Password);
		if (uID.equals("Invalid"))
			return -1;
		int userID = Integer.parseInt(uID);
		return userID;
	}
	
	public String getUserRole(User user)
	{
		return UserDB.get(user.id, "role");
    }
}
