package LookForLove;

public class Character {
    protected String gender;
    protected String ethnicity;
    protected double height;

    public Character (String gender, String ethnicity, double height) {
        this.gender = gender;
        this.ethnicity = ethnicity;
        this.height = height;
    }

    /*public boolean fit(WishCharacter wish) {
        boolean fits = true;

        if (this.gender != wish.gender) {
            fits = false;
        } else if (wish.getAgeMax() < this.age || wish.wishAgeMin > this.age) {
            fits = false;
        }   
         
        return fits;
    }*/

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
