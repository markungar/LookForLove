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

    public String getUser() {
        return username;
    }

    public String getPass() {
        return password;
    }
    public String getEmail() {
        return emailAddress;
    }

    public int getPhoneNum() {
        return phoneNumber;
    }

    public String setUser(String user) {
        username = user;
        return username;
    }

    public String setPass(String pass) {
        password = pass;
        return password;
    }
    public String setEmail(String email) {
        emailAddress = email;
        return emailAddress;
    }

    public int setPhoneNum(int num) {
        phoneNumber = num;
        return phoneNumber;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
    }

    
}
