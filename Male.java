// package LookForLove;

public class Male extends Person {
    public Male (Account loginInfo, Character trait, WishCharacter wish, String description, String[] prompt) {
        super(loginInfo, trait, wish, description, prompt);
    }
    //Constructor for male extending person
    //no new variables, just declares super class

    public int totalScore(Person other) {
        return promptsScore(other) + wishScore(other);
    }
    //calculates the total of both prompts score and wish score in order to determine how well two people match with each other

    public int promptsScore(Person other) {
        int score = 0;
        if (this.prompts[0].equals(other.prompts[0])) {
            score = score + 4;
        }

        if (this.prompts[1].equals(other.prompts[1])) {
            score = score + 4;
        }

        if (this.prompts[2].equals(other.prompts[2])) {
            score = score + 4;
        }

        if (this.prompts[3].equals(other.prompts[3])) {
            score = score + 4;
        }

        return score;
    }
    //adds 4 points to the match score if two people have the same favorite prompt answers (for each prompt)

    public int wishScore(Person other) {
        int score = 0;
        if (wish.fit(other.trait) == 0) {
            score = score + 8;
        }
        //adds 8 points to the score if the age is between their min and max
        
        if (this.wish.getEthnicity().equals(other.trait.getEthnicity())) {
            score = score + 6;
        } 
        //adds 6 points if the matches ethnicity is equal to the users preferred ethnicity

        if (this.wish.getHeight() > other.trait.getHeight() - 3 && this.wish.getHeight() < other.trait.getHeight() + 3) {
            score = score + 7;
        } else if (this.wish.getHeight() > other.trait.getHeight() - 3) {
            score = score + 2;
        }
        //adds 7 points if the person is around their preffered height, and 2 of it's more

        return score;

    }
    
}