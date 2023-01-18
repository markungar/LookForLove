//package LookForLove;

public class WishCharacter extends Character {
    
    private int ageMin;
    private int ageMax;
    //declares the minimum age and maximum age someone is willing to date

    public WishCharacter(int min, int max, String sexuality, String ethnicity, int height, int age) {
        super(ethnicity, sexuality, age, height);
        ageMin = min;
        ageMax = max;
    } 
    //Constructs the minimum and maximum age someone is willing to date aswell as the super class and the sexuality that someone needs to have to match

    public int fit (Character other) {
        if (this.ageMax < other.getAge()) {
            return 1;
        } else if (this.ageMin > other.getAge()){
            return -1;
        } else {
		    return 0;
	    }
    }
    //checks if someone is not in the age range of the person looking 

    public int getAgeMax() {
        return ageMax;
    }
    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public int getAgeMin() {
        return ageMin;
    }
    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }
    //get and set statements for age max & age min

    public String toString() {
        return "\nwish ethnicity: " + ethnicity + "\nwish height: " + height + "wish age range: " + ageMin + " - " + ageMax;
    }
}
