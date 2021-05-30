
/*
 * Player.java implements a Poker Player class. 
 * Using a Player class instead of an array in order to insure that the Player can still be identified correctly
 * should their entered ID not be consecutive
 */
package solution;

public class Player implements Comparable<Player> {

	private int playerNum;

	private Hand hand;

	public Player(int playerNum) {
		this.playerNum = playerNum;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	@Override
	public int compareTo(Player o) {
		// compare two players based on their hand rank
		return this.getHand().compareTo(o.getHand());
	}

}
