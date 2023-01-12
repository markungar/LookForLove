public class PersonScorePair {

	public Person person;
	public int score;

	public PersonScorePair(Person person, int score) {

		this.person = person;
		this.score = score;

	}

	public boolean compareTo(PersonScorePair other) {

		if (this.score < other.score) {
			return -1;
		}
		else if (this.score > other.score) {
			return 1;
		}
		return 0;
	}

} 