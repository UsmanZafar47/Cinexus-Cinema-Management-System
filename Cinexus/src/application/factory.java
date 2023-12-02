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
}
