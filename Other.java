//package LookForLove;

public class Other extends Person {
    public Other (Account loginInfo, Character trait, WishCharacter wish, String description, String[] prompt) {
        super(loginInfo, trait, wish, description, prompt);
    }

    public int totalScore(Person other) {
        return promptsScore(other) + wishScore(other);
    }

    public int promptsScore(Person other) {
        int score = 0;
        if (this.prompts[0].equals(other.prompts[0])) {
            score = score + 5;
        }

        if (this.prompts[1].equals(other.prompts[1])) {
            score = score + 5;
        }

        if (this.prompts[2].equals(other.prompts[2])) {
            score = score + 5;
        }

        if (this.prompts[3].equals(other.prompts[3])) {
            score = score + 5;
        }

        return score;
    }

    //adds 5 to the score for each prompt that matches up with the user

    public int wishScore(Person other) {
        int score = 0;
        if (wish.fit(other.trait) == 0) {
            score = score + 11;
        }
        //adds 11 points to the score if the age is between their min and max
        
        if (this.wish.getEthnicity().equals(other.trait.getEthnicity())) {
            score = score + 2;
        } 
        //adds 2 to the score if the matches ethnicity matches the users wish 

        if (this.wish.getHeight() > other.trait.getHeight() - 3 && this.wish.getHeight() < other.trait.getHeight() + 3) {
            score = score + 4;
        } else if (this.wish.getHeight() > other.trait.getHeight() - 3) {
            score = score + 3;
        }
        //adds 4 points if the person is around their preffered height, and 3 of it's more

        return score;

    }
}