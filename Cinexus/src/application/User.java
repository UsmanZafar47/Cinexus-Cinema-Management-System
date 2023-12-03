package application;

import dbhandler.UserDatabaseConnecter;

public class User {
	private int id;
	private String Username;
	private String Password;
	private String Role;
	private String Email;
	private String CNIC;
	private String Name;
	UserDatabaseConnecter UserDB = new UserDatabaseConnecter();

	public User() {
		this.id = -1;
	}

	public User(User user) {
		this.id = user.id;
		this.Username = user.Username;
		this.Password = user.Password;
		this.Role = user.Role;
		this.Email = user.Email;
		this.CNIC = user.CNIC;
		this.Name = user.Name;
	}


	public User(String username, String password) 
	{
		this.Username = username;
		this.Password = password; 
		this.id = getUserID();
		this.setName(UserDB.get(id, "name"));
		this.setEmail(UserDB.get(id, "email"));
		this.setCNIC(UserDB.get(id, "cnic"));
		this.setRole(UserDB.get(id, "role"));
	}

	public User(int id) {
		this.id = id;
		this.setName(UserDB.get(id, "name"));
		this.setUsername(UserDB.get(id, "username"));
		this.setPassword(UserDB.get(id, "password"));
		this.setEmail(UserDB.get(id, "email"));
		this.setCNIC(UserDB.get(id, "cnic"));
		this.setRole(UserDB.get(id, "role"));
	}

	private void setPassword(String string) {
		this.Password = string;
	}

	private void setUsername(String string) {
		this.Username = string;
	}

	public int getUserID() {
		if (this.id == 0 || this.id == -1) {
			this.id = UserDB.getID(this.Username, this.Password);
		}
		return id;
	}

	public String getUserRole() {
		if (this.Role == null)
			this.Role = UserDB.get(this.id, "role");
		return Role;
	}

	public void setRole(String string) {
		this.Role = string;
	}

	public User newUser(String name, String username, String password, String cnic, String email, String role) {
		this.Username = username;
		this.setName(name);
		this.Password = password;
		this.setCNIC(cnic);
		this.setEmail(email);
		this.Role = role;

		UserDB.InsertUser(this);

		this.id = getUserID();

		return this;
	}

	public String getEmail() {
		return Email;
	}
	public String getUsername() {
		return Email;
	}
	public String getPassword() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

	public String getCNIC() {
		return CNIC;
	}

	public void setCNIC(String cNIC) {
		CNIC = cNIC;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
}
