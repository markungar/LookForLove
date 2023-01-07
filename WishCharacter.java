package LookForLove;

public class WishCharacter extends Character {
    private int ageMin;
    private int ageMax;
    private String attraction;

    public WishCharacter(String gender, String ethnicity, double height, int min, int max, String attraction) {
        super(gender, ethnicity, height);
        ageMin = min;
        ageMax = max;
        this.attraction = attraction;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public String getAgeAttraction() {
        return attraction;
    }
}
