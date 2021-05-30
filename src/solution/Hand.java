/*
 * Hand.java implements the hand class. Each hand contains 3 Cards.
 */

package solution;

import java.util.Arrays;
import java.util.HashMap;

public class Hand implements Comparable<Hand> {

	public enum HandRank {
		HIGH_CARD, PAIR, FLUSH, STRAIGHT, THREE_OF_A_KIND, STRAIGHT_FLUSH;
	}

	private Card[] cards;

	public Hand() {

	}

	/*
	 * Takes in a string containing 3 cards and parses them into an array of Cards
	 */
	public void parseHand(String handString) {
		this.cards = new Card[3];
		int i = 0;
		String[] split = handString.split("\\s+");
		if (split.length > 4) {
			System.out.println("Incorrect number of cards inputed. Exiting program");
			System.exit(-1);
		}
		for (String s : split) {
			if (s.trim().length() > 0) {
				Card aCard = new Card();
				aCard.parseCard(s);
				this.cards[i] = aCard;
				i++;
			}
		}
	}

	/*
	 * Sorts the array of cards from smallest to largest
	 */
	public void sortHand() {
		Arrays.sort(this.cards);
	}

	/*
	 * Returns the rank of the hand based on the cards
	 */
	public HandRank getHandRank() {
		if (this.isStraightFlush()) {
			return HandRank.STRAIGHT_FLUSH;
		} else if (this.isThreeOfAKind()) {
			return HandRank.THREE_OF_A_KIND;
		} else if (this.isStraight()) {
			return HandRank.STRAIGHT;
		} else if (this.isFlush()) {
			return HandRank.FLUSH;
		} else if (this.isPair()) {
			return HandRank.PAIR;
		} else {
			return HandRank.HIGH_CARD;
		}
	}

	/*
	 * Returns the number of cards with the same rank
	 */
	public int getNumRank() {
		int largest = 1;
		HashMap<Card.Rank, Integer> map = new HashMap<Card.Rank, Integer>();
		for (Card c : this.cards) {
			if (!map.containsKey(c.getRank())) {
				map.put(c.getRank(), 1);
			} else {
				map.put(c.getRank(), map.get(c.getRank()) + 1); // increment number of times it shows up
				// keep track of largest number
				int num = map.get(c.getRank());
				if (num > largest) {
					largest = num;
				}
			}
		}
		return largest;
	}

	/*
	 * Returns the highest number of cards with the same suit
	 */
	public int getNumSameSuit() {
		int largest = 1;
		HashMap<Card.Suit, Integer> map = new HashMap<Card.Suit, Integer>();
		for (Card c : this.cards) {
			if (!map.containsKey(c.getSuit())) {
				map.put(c.getSuit(), 1);
			} else {
				map.put(c.getSuit(), map.get(c.getSuit()) + 1);
			}
			int num = map.get(c.getSuit());
			if (num > largest) {
				largest = num;
			}
		}
		return largest;
	}

	/*
	 * Methods to determine rank
	 */

	// All 3 cards form a run and have same suit
	public boolean isStraightFlush() {
		if (this.isStraight() && this.isFlush())
			return true;
		return false;
	}

	// All 3 cards have same rank
	public boolean isThreeOfAKind() {
		if (this.getNumRank() == 3)
			return true;
		return false;
	}

	// All 3 cards form a run.
	public boolean isStraight() {
		// assuming the hand is already sorted and that the input will always have 3
		// cards smallest to largest...
		int one = this.cards[0].getRank().ordinal();
		int two = this.cards[1].getRank().ordinal();
		int three = this.cards[2].getRank().ordinal();
		final int aceCard = 12;
		final int twoCard = 0;
		// note that Ace (12) can be high or low
		if (one + 1 == two || (one == aceCard && two == twoCard)) {
			if (two + 1 == three) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	// All 3 cards have same suit
	public boolean isFlush() {
		if (this.getNumSameSuit() == 3)
			return true;
		return false;
	}

	// 2 cards have same rank
	public boolean isPair() {
		if (this.getNumRank() == 2)
			return true;
		return false;
	}

	/*
	 * Compares two hands based on their individual hand ranks, and if a tie, card
	 * ranks.
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Hand o) {
		if (this.getHandRank().ordinal() > o.getHandRank().ordinal()) {
			return 1;
		} else if (this.getHandRank().ordinal() < o.getHandRank().ordinal()) {
			return -1;
		} else if (this.getHandRank().ordinal() == o.getHandRank().ordinal()) {
			// When comparing hands of the same type, the winner is the hand whose highest
			// card is ranked higher.
			if (this.getHandRank() == HandRank.PAIR) {
				// If two hands are both a pair, the winning hand is the hand w/ higher pair.
				Card highestOne = this.getPairCard()[0];
				Card highestTwo = o.getPairCard()[0];
				Card exclOne = this.getPairCard()[1];
				Card exclTwo = o.getPairCard()[1];

				if (highestOne.compareTo(highestTwo) == 0) { // if pair is equal, use remaining card
					return exclOne.compareTo(exclTwo);
				}
				return (highestOne.compareTo(highestTwo));
			} else {
				// last card in array should be highest. If tied, compare next two
				Card highestOne = this.cards[2];
				Card highestTwo = o.cards[2];
				if (highestOne.compareTo(highestTwo) == 0) {
					if (this.cards[1].compareTo(o.cards[1]) == 0) {
						return this.cards[0].compareTo(o.cards[0]);
					}
					return this.cards[1].compareTo(o.cards[1]);
				}
				return (highestOne.compareTo(highestTwo));
			}

		}
		return 0;
	}

	/*
	 * Helper method for compareTo. Returns card that is part of the pair in a hand,
	 * and the card excluded from the pair
	 */
	private Card[] getPairCard() {
		Card pair[] = new Card[2];
		if (this.cards[0].getRank() == this.cards[1].getRank()) {
			pair[0] = this.cards[0];
			pair[1] = this.cards[2];
		} else {
			pair[0] = this.cards[1];
			pair[1] = this.cards[0];
		}
		return pair;
	}

	@Override
	public String toString() {
		String result = "";
		for (Card c : this.cards) {
			result = c + " " + result;
		}
		return result;
	}

}
