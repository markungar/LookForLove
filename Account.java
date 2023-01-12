package LookForLove;

public class Account {
    protected String firstName;
    protected String lastName;
    protected int phoneNumber;
    protected String emailAddress;
    //initilizes non sensitive info
    
    private String username;
    private String password;
    //initilizes sensitive info
    
    public Account (String firstName, String lastName, int phoneNumber, String emailAddress, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password; 
    }
    //constructor for account

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

    public int getPhoneNumber() {
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

    public int setPhoneNumber(int num) {
        phoneNumber = num;
        return phoneNumber;
    }
    //all set statements
    
    public String setFirstName(String fName) {
        firstName = fName;
        return firstName;
    }
    
    public String setLastName(String lName) {
        lastName = lName;
        return lastName;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
    }
    //account to string that displays the full name, phone number and email address for the person

    
}
