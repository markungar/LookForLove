package LookForLove;

public abstract class Person {
    protected Account loginInfo;
    protected Character trait;
    protected Character wish;
    protected Prompts prompt;

    public Person (Account loginInfo, Character trait, Character wish, Prompts prompt) {
        this.loginInfo = loginInfo;
        this.trait = trait;
        this.wish = wish;
        this.prompt = prompt;
    }
}
