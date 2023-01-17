public abstract class Person {
    protected Account loginInfo;
    protected Character trait;
    protected WishCharacter wish;
    protected String description;
    //declares all characteristics of a person, including their account details, trait details, ideal partner details and a brief description of themselves 
    
	public Person leftChild;
	public Person rightChild;
	public Person parent;

    protected String[] prompts = new String[4];
    //declares prompt array

    public Person (Account loginInfo, Character trait, WishCharacter wish, String description, String[] prompts) {
        this.loginInfo = loginInfo;
        this.trait = trait;
        this.wish = wish;
        this.description = description;
        
        this.prompts[0] = prompts[0];
        this.prompts[1] = prompts[1];
        this.prompts[2] = prompts[2];
        this.prompts[3] = prompts[3];
        //sets the persons prompt answers in correct order
    }
    //constructor for person, initilizing all fields

    public String toString() {
        return loginInfo + "\n" + trait + "\n" + wish + "\ndescription: " + description + "\nfavMovie: " + prompts[0];
    }
    //to string for trait, wish, description and prompts of specified person

    public Account getLoginInfo() {
        return loginInfo;
    }

    public Character getTrait() {
        return trait;
    }
    
    public WishCharacter getWish() {
        return wish;
    }

    public String getDescription() {
        return description;
    }

    public String[] getPrompts() {
        return prompts;
    }
    
    //ALL GET METHODS

    public void setLoginInfo(Account loginInfo) {
        this.loginInfo = loginInfo;
    }

    public void setTrait(Character trait) {
        this.trait = trait;
    }
    
    public void setWish(WishCharacter wish) {
        this.wish = wish;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMovie(String movie) {
    	prompts[0] = movie;
    }
    
    public void setSport(String sport) {
    	prompts[1] = sport;
    }
    
    public void setSeason(String season) {
    	prompts[2] = season;
    }
    
    public void setGenre(String genre) {
    	prompts[3] = genre;
    }
    
    //ALL SET METHODS
    
    public boolean setPrompts(String favMovie, String favSport, String favSeason, String favGenre) {
        try
        {
            prompts[0] = favMovie;
            prompts[1] = favSport;
            prompts[2] = favSeason;
            prompts[3] = favGenre;
        }
        
        catch (ArrayIndexOutOfBoundsException e) 
        {
            return false;
        }
        
        return true;
    }
    //sets/changes someones prompt answers without use of the constructor 

    public int compareTo(Person other) {
        return this.trait.age - other.trait.age;
    }
    //compares this persons age to another persons age, returning the age differential
    
    public boolean equals(Person other) {
        if (this.loginInfo.getPassword() == other.loginInfo.getPassword() && this.loginInfo.getUsername().equals(other.loginInfo.getUsername())) {
            return true;
        } else {
            return false;
        }
    }
    //checks if the certain account has the same username as someone elses, same with password
    
    public abstract int totalScore(Person other);
    //calculates the total score of prompts and wishes added together
    
    public abstract int promptsScore(Person other);
    //calculates the matching prompts between two people, and displays a score of points that change due to the gender of the person
    
    public abstract int wishScore(Person other);
    //calculates the wish score (age range etc.) of two people, and displays a score of points that change due to the gender of the person
    


}
