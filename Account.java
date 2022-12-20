package LookForLove;

public class Account {
    protected String firstName;
    protected String lastName;
    protected int phoneNumber;
    protected String emailAddress;
    protected int age;
    
    private String username;
    private String password;

    public Account (String firstName, String lastName, int phoneNumber, String emailAddress, int age, String username, String password) {
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
}
