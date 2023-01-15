//package LookForLove;

public class Account {
    protected String firstName;
    protected String lastName;
    protected long phoneNumber;
    protected String emailAddress;
    //initilizes non sensitive info
    
    private String username;
    private String password;
    //initilizes sensitive info
    
    public Account (String firstName, String lastName, long phoneNumber, String emailAddress, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password; 
    }
    //constructor for account. declaring the full name, phone number, email, username and password

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }
    //all get statements
    
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

    public void setPhoneNumber(long num) {
        phoneNumber = num;
    }
    //all set statements
    
    public void setFirstName(String fName) {
        firstName = fName;
    }
    
    public void setLastName(String lName) {
        lastName = lName;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
    }
    //account to string that displays the full name, phone number and email address for the person

    
}