/*
 * Card.java implements the card class. Each card has a rank and a suit
 */
package solution;

public class Card implements Comparable<Card> {
	public enum Rank {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;
	}

	public enum Suit {
		h, d, s, c;
	}

	private Rank rank;
	private Suit suit;

	public Card() {
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	/*
	 * Takes in a string and parses it into a Card object. i.e. "3T"
	 */
	public void parseCard(String cardStr) {
		char r = cardStr.charAt(0);
		if (r == ' ') { // ensure char r is not a whitespace
			r = cardStr.charAt(1);
		}
		if (r == '2') {
			this.rank = Rank.TWO;
		} else if (r == '3') {
			this.rank = Rank.THREE;
		} else if (r == '4') {
			this.rank = Rank.FOUR;
		} else if (r == '5') {
			this.rank = Rank.FIVE;
		} else if (r == '6') {
			this.rank = Rank.SIX;
		} else if (r == '7') {
			this.rank = Rank.SEVEN;
		} else if (r == '8') {
			this.rank = Rank.EIGHT;
		} else if (r == '9') {
			this.rank = Rank.NINE;
		} else if (r == 'T' || r == 't') {
			this.rank = Rank.TEN;
		} else if (r == 'J' || r == 'j') {
			this.rank = Rank.JACK;
		} else if (r == 'Q' || r == 'q') {
			this.rank = Rank.QUEEN;
		} else if (r == 'K' || r == 'k') {
			this.rank = Rank.KING;
		} else if (r == 'A' || r == 'a') {
			this.rank = Rank.ACE;
		} else {
			System.out.println("Incorrect card format detected. Ending program.");
			System.exit(-1);
		}

		char s = cardStr.charAt(1);
		if (s == 'h' || s == 'H') {
			this.suit = Suit.h;
		} else if (s == 'd' || s == 'D') {
			this.suit = Suit.d;
		} else if (s == 's' || s == 'S') {
			this.suit = Suit.s;
		} else if (s == 'c' || s == 'C') {
			this.suit = Suit.c;
		} else {
			System.out.println("Incorrect card format detected. Ending program.");
			System.exit(-1);
		}

	}

	@Override
	public String toString() {
		// generate more readable/formatted rank since enums must begin with
		// alphabetical characters
		Rank r = this.getRank();
		Suit s = this.getSuit();

		String rStr = "";

		if (r == Rank.TWO) {
			rStr = "2";
		} else if (r == Rank.THREE) {
			rStr = "3";
		} else if (r == Rank.FOUR) {
			rStr = "4";
		} else if (r == Rank.FIVE) {
			rStr = "5";
		} else if (r == Rank.SIX) {
			rStr = "6";
		} else if (r == Rank.SEVEN) {
			rStr = "7";
		} else if (r == Rank.EIGHT) {
			rStr = "8";
		} else if (r == Rank.NINE) {
			rStr = "9";
		} else if (r == Rank.TEN) {
			rStr = "T";
		} else if (r == Rank.JACK) {
			rStr = "J";
		} else if (r == Rank.QUEEN) {
			rStr = "Q";
		} else if (r == Rank.KING) {
			rStr = "K";
		} else if (r == Rank.ACE) {
			rStr = "A";
		}

		return rStr + s;
	}

	/*
	 * Compares two Card objects based on card value
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Card c) {
		if (this.getRank().ordinal() > c.getRank().ordinal()) {
			return 1;
		} else if (this.getRank().ordinal() < c.getRank().ordinal()) {
			return -1;
		} else {
			return 0; // assuming that suits are all ranked equally
		}
	}

}
