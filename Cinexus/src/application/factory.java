package application;

public class factory 
{
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
}
