public class LoginKey {

	private String password; 
	private Person person;
		
	public LoginKey(String password, Person person) {
		this.password = password;
		this.person = person;
	}

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

	public boolean equals(LoginKey other) {
		return this.person.equals(other.person);
	}

}