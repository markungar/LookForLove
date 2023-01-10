package LookForLove;

public class Account {
    protected String firstName;
    protected String lastName;
    protected int phoneNumber;
    protected String emailAddress;
    
    private String username;
    private String password;

    public Account (String firstName, String lastName, int phoneNumber, String emailAddress, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password; 
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public String getEmailAddress() {
        return emailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String setUsername(String user) {
        username = user;
        return username;
    }

    public String setPassword(String pass) {
        password = pass;
        return password;
    }
    public String setEmailAddress(String email) {
        emailAddress = email;
        return emailAddress;
    }

    public int setPhoneNumber(int num) {
        phoneNumber = num;
        return phoneNumber;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
    }

    
}
