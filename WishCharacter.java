//package LookForLove;

public class WishCharacter extends Character {
    private int ageMin;
    private int ageMax;

    public WishCharacter(int min, int max, String sexuality, String ethnicity, int height, int age) {
        super(ethnicity, age, height);
        this.sexuality = sexuality;
        ageMin = min;
        ageMax = max;
    } 

    public int fit (Character other) {
        if (this.ageMax <= other.getAge()) {
            return 1;
        } else if (this.ageMin > other.getAge()){
            return -1;
        } else {
		return 0;
	}
    }

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

    public String toString() {
        return "\nage: " + age + "\nethnicity: " + ethnicity + "\nheight: " + height + "wish age range: " + ageMin + " - " + ageMax;
    }
}