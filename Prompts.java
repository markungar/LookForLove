package LookForLove;

public class Prompts {
    protected String favMovie;
    protected String favSeason;
    protected String favFood;
    protected String favGenre;
    protected String[] prompts = new String[4]; 

    public Prompts (String favMovie, String favSeason, String favFood, String favArtist) {
        this.favMovie = favMovie;
        this.favSeason = favSeason;
        this.favFood = favFood;
        this.favArtist = favArtist;
    }
    
    public void createPrompt(int index, String prompt) {
        prompts[index] = prompt;
    }
}
