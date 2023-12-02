package application;
import dbhandler.UserDatabaseConnecter;


public class User 
{
	private int id;
	private String Username;
	private String Password;
	private String Role;
	private String Email;
	private String CNIC;
	private String Name;
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
		this.id = getUserID();
		this.Role = getUserRole();
	}
	public User newUser(String name, String username, String password, String cnic, String email, String role) 
	{
		this.Username = username;
		this.Name = name;
		this.Password = password;
		this.CNIC = cnic;
		this.Email = email;
		this.Role = role;
		
		UserDB.InsertUser(this);
		
		this.id = getUserID();
		
		return this;
	}
	public int getUserID()
	{
		if (this.id == 0 || this.id == -1)
		{
			this.id = UserDB.getID(this.Username, this.Password);
		}
		return id;
	}
	public String getUsername()
	{
		return this.Username;
    }
	public String getPassword()
	{
		return this.Password;
    }
	public String getName()
	{
		return this.Name;
    }
	public String getEmail()
	{
		return this.Email;
    }
	public String getcnic()
	{
		return this.CNIC;
    }

	public String getUserRole()
	{
		if (this.Role == null)
		{
			this.Role = UserDB.get(this.id, "role");
		}
		return Role;
    }
}
