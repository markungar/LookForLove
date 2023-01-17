// package LookForLove;

public class Character {

    protected String ethnicity;
    protected int age;
    protected int height;
    protected String sexuality;
    //initilizes characteristics of a person

    public Character (String ethnicity, String sexuality, int age, int height) {
        this.sexuality = sexuality;
    	this.age = age;
        this.ethnicity = ethnicity;
        this.height = height;
    }
    //constructor for Character

    public int getAge() {
        return age;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public int getHeight() {
        return height;
    }

    public String getSexuality() {
        return sexuality;
    }
    //get statements

    public int setAge(int age) {
        this.age = age;
        return age;
    }

    public String setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
        return ethnicity;
    }

    public int setHeight(int height) {
        this.height = height;
        return height;
    }

    public void setSexuality(String sexuality) {
        this.sexuality = sexuality;
    }
    //all set statements
    
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
    //matches the percent of wishes that match another persons traits (0/3, 1/3, 2/3, 3/3)

    public String toString() {
        return "\nage: " + age + "\nethnicity: " + ethnicity + "\nheight: " + height;
    }
    //displays all variables for character
}