package LookForLove;

public class Prompts {
    protected String favMovie;
    protected String favSeason;
    protected String favSport;
    protected String favGenre;
    protected String[] prompts = new String[4]; 

    public Prompts (String favMovie, String favSeason, String favSport, String favGenre) {
        this.favMovie = favMovie;
        this.favSeason = favSeason;
        this.favFood = favFood;
        this.favGenre = favGenre;
        
        prompts[0] = favMovie;
        prompts[1] = favSeason;
        prompts[2] = favSport;
        prompts[3] = favGenre;
    }
    
    public void createPrompt(int index, String prompt) {
        prompts[index] = prompt;
    }
}
