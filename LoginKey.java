// LoginKey()
// --------------------------------------------------------------------------------------------------------
// Purpose: Is exclusively used in pairs with a String inside a map to gain quick access for information
//	when only the username is available.

public class LoginKey {

	// password to quickly compare it with the given username.
	private String password; 
	// person for access to other informations.
	private Person person;
		
	// constructor
	public LoginKey(String password, Person person) {
		this.password = password;
		this.person = person;
	}

	// getters and setters
	public Person getPerson() {
		return this.person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	// equals to check if both LoginKey's have the same person stored.
	public boolean equals(LoginKey other) {
		return this.person.equals(other.person);
	}

}
