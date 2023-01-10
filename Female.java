package LookForLove;

public class Female extends Person {
    public Female (Account loginInfo, Character trait, WishCharacter wish, String description, String[] prompt) {
        super(loginInfo, trait, wish, description, prompt);
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

    public int wishScore(Person other) {
        int score = 0;
        if (wish.fit(other.trait)) {
            score = score + 5;
        }
        
        if (this.wish.getEthnicity().equals(other.trait.getEthnicity())) {
            score = score + 3;
        } 

        if (this.wish.getHeight() > other.trait.getHeight() - 3 && this.wish.getHeight() < other.trait.getHeight() + 3) {
            score = score + 5;
        } else if (this.wish.getHeight() > other.trait.getHeight() - 3) {
            score = score + 3;
        }

        return score;

    }
      
}
