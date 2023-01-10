package LookForLove;

public class WishCharacter extends Character {
    private int ageMin;
    private int ageMax;
    private String sexuality;

    public WishCharacter(int min, int max, String sexuality,String ethnicity, int height, int age) {
        super(ethnicity, age, height);
        this.sexuality = sexuality;
        ageMin = min;
        ageMax = max;
    }

    public boolean fit (Character other) {
        if (this.ageMin > other.getAge() || this.ageMax < other.getAge()) {
            return false;
        } else {
            return true;
        }
    }

    public int getAgeMax() {
        return ageMax;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public String toString() {
        return "\nage: " + age + "\nethnicity: " + ethnicity + "\nheight: " + height + "wish age range: " + ageMin + " - " + ageMax;
    }
}
