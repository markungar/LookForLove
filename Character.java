package LookForLove;

public class Character {
    protected String ethnicity;
    protected int age;
    protected int height;

    public Character (String ethnicity, int age, int height) {
        this.age = age;
        this.ethnicity = ethnicity;
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public int getHeight() {
        return height;
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
        return "\nage: " + age + "\nethnicity: " + ethnicity + "\nheight: " + height;
    }
}
