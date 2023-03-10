// package LookForLove; 

public class Female extends Person {
    
    public Female (Account loginInfo, Character trait, WishCharacter wish, String description, String[] prompts) {
        super(loginInfo, trait, wish, description, prompts);
    }
    //constructor for female that extends person
    

    public int totalScore(Person other) {
        return promptsScore(other) + wishScore(other);
    }

    public int promptsScore(Person other) {
        int score = 0;
        if (this.prompts[0].equals(other.prompts[0])) {
            score = score + 7;
        }

        if (this.prompts[1].equals(other.prompts[1])) {
            score = score + 7;
        }

        if (this.prompts[2].equals(other.prompts[2])) {
            score = score + 7;
        }

        if (this.prompts[3].equals(other.prompts[3])) {
            score = score + 7;
        }

        return score;
    }
    //adds 7 to the score for each prompt that matches up with the user

    public int wishScore(Person other) {
        int score = 0;
        if (wish.fit(other.trait) == 0) {
            score = score + 5;
        }
        //adds 5 points to the score if the age is between their min and max
        
        if (this.wish.getEthnicity().equals(other.trait.getEthnicity())) {
            score = score + 3;
        } 
        //adds 3 points if the ethnicity matches their preffered

        if (this.wish.getHeight() > other.trait.getHeight() - 3 && this.wish.getHeight() < other.trait.getHeight() + 3) {
            score = score + 5;
        } else if (this.wish.getHeight() > other.trait.getHeight() - 3) {
            score = score + 3;
        }
        //adds 5 points if the person is around their preffered height, and 3 of it's more

        return score;

    }
      
}