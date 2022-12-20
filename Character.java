package LookForLove;

public class Character {
    protected String gender;
    protected int age;
    protected String ethnicity;
    protected double height;
    protected int wishAgeMin;
    protected int wishAgeMax;

    public Character (String gender, int age, String ethnicity, double height, int wishAgeMin, int wishAgeMax) {
        this.gender = gender;
        this.age = age;
        this.ethnicity = ethnicity;
        this.height = height;
        this.wishAgeMin = wishAgeMin;
        this.wishAgeMax = wishAgeMax;
    }

    public boolean fit(Character wish) {
        boolean fits = true;

        if (this.gender != wish.gender) {
            fits = false;
        } else if (wish.wishAgeMax < this.age || wish.wishAgeMin > this.age) {
            fits = false;
        }   
         
        return fits;
    }

    public double matchPercent(Character wish) {
        double matchCount = 0;
        int TOTAL = 2;

        if (this.ethnicity == wish.ethnicity) {
            matchCount++;
        }

        if (this.height >= wish.height-2 && this.height <= wish.height+2) {
            matchCount++;
        }

        return (matchCount/TOTAL) * 100;
    }

    public String toString() {
        return "gender: " + gender + "\nage: " + age + "\nethnicity: " + ethnicity + "\nheight: " + height + "\nWish age range: " + wishAgeMin + " - " + wishAgeMax;
    }
}
