package LookForLove;

public abstract class Person {
    protected Account loginInfo;
    protected Character trait;
    protected WishCharacter wish;
    protected String description;
    protected String[] prompts;

    public Person (Account loginInfo, Character trait, WishCharacter wish, String description, String[] prompts) {
        this.loginInfo = loginInfo;
        this.trait = trait;
        this.wish = wish;
        this.description = description;
        this.prompts = prompts;
    }

    public String toString() {
        return loginInfo.toString() + "\n" + trait + "\n" + wish + "\n" + prompts;
    }

    public Account getLoginInfo() {
        return loginInfo;
    }

    public Character getTrait() {
        return trait;
    }
    
    public Character getWish() {
        return wish;
    }

    public String getDescription() {
        return description;
    }

    public String[] getPrompts() {
        return prompts;
    }

    public Account setLoginInfo(Account loginInfo) {
        this.loginInfo = loginInfo;
        return loginInfo;
    }

    public Character setTrait(Character trait) {
        this.trait = trait;
        return trait;
    }
    
    public WishCharacter setWish(WishCharacter wish) {
        this.wish = wish;
        return wish;
    }

    public String setDescription(String description) {
        this.description = description;
        return description;
    }

    public String[] setPrompts(String[] prompts) {
        this.prompts = prompts;
        return prompts;
    }

    public boolean equals(Person other) {
        if (this.loginInfo.getPass() == other.loginInfo.getPass() && this.loginInfo.getUser().equals(other.loginInfo.getUser())) {
            return true;
        } else {
            return false;
        }
    }
    
    public abstract int totalScore(Person other);
    public abstract int promptsScore(Person other);
    public abstract int wishScore(Person other);


}
