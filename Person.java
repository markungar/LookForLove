package LookForLove;

public abstract class Person {
    protected Account loginInfo;
    protected Character trait;
    protected WishCharacter wish;
    protected String description;
    protected String[] prompts = new String[4];

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

    public boolean setPrompts(String favMovie, String favSport, String favSeason, String favGenre) {
        prompts[0] = favMovie;
        prompts[1] = favSport;
        prompts[2] = favSeason;
        prompts[3] = favGenre;
        return prompts;
    }

    public int compareTo(Person other) {
        return this.trait.age - other.trait.age;
    }
    
    public boolean equals(Person other) {
        if (this.loginInfo.getPassword() == other.loginInfo.getPassword() && this.loginInfo.getUsername().equals(other.loginInfo.getUsername())) {
            return true;
        } else {
            return false;
        }
    }
    
    public abstract int totalScore(Person other);
    public abstract int promptsScore(Person other);
    public abstract int wishScore(Person other);


}
