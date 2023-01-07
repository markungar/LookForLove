package LookForLove;

public class Database {
    private Person[] users;
    private int userCount = 0;

    public Database (int arrayMax, int usersInArray) {
        users = new Person[arrayMax];
        userCount = usersInArray;
    }

    public int login(String username, String pass) {
        for (int i = 0; i < userCount; i++) {
            if (username == users[i].loginInfo.getUser() && pass == users[i].loginInfo.getPass()) {
                return i;
            }
        }

        return -1;
    }

    public boolean register(Account loginInfo, Character trait, Character wish, Prompts prompt, int gender) {
        userCount++;

        if (gender == 1) {
            users[userCount + 1] = new Male(loginInfo, trait, wish, prompt);
        } else if (gender == 2) {
            users[userCount + 1] = new Female(loginInfo, trait, wish, prompt);
        } else {
            users[userCount + 1] = new Other(loginInfo, trait, wish, prompt);            
        }

        if (users[userCount] != null) {
            return true;
        } else {
            return false;
        }
    }

    public String checkUsername(String email) {
        for (int i = 0; i < userCount; i++) {
            if (email == users[i].loginInfo.getEmail()) {
                return users[i].loginInfo.getUser();
            }
        }

        return "account not found";
    }


}
