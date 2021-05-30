/*
 * Game.java implements the game class. Each game keeps track of a number of inputed players and calculates the winner of each Poker game.
 */
package solution;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

	private Player[] players;

	public Game() {

	}

	public void newGame() {
		// Read in number of players (0-24) and initialize ArrayList capacity
		Scanner sc = new Scanner(System.in);
		// read in number of players first to initialize array
		int numPlayers = sc.nextInt();
		if (numPlayers <= 0 || numPlayers > 24) {
			System.out.println("Please insert a valid number between 1 and 24 (inclusive). Exiting program.");
			System.exit(-1);
		}
		String[] input = new String[numPlayers];
		// initialize players member variable. Use array due to known size
		players = new Player[numPlayers];
		sc.nextLine();
		// store all lines with players and their hands into an array of Strings
		for (int i = 0; i < input.length; i++) {
			input[i] = sc.nextLine();
		}
		sc.close();

		this.parsePlayers(input);
		this.sortPlayerHands();

		// Determine ranks of each Player's hands by sorting players by hand rank
		Arrays.sort(players);
		this.printWinners();

	}

	/*
	 * Takes in raw array of input strings and converts them to player objects
	 */
	private void parsePlayers(String[] rawInput) {
		int i = 0;
		for (String s : rawInput) {
			Hand aHand = new Hand();
			// split each line into id and hand
			int id = Integer.parseInt((s.substring(0, 2)).replaceAll("\\s+", ""));
			String cardStr = (s.substring(2));

			// create a new Player and add to ArrayList
			Player aPlayer = new Player(id);
			aHand.parseHand(cardStr);
			aPlayer.setHand(aHand);

			players[i] = aPlayer;
			i++;
		}
	}

	/*
	 * Sorts the hand of Cards of every player largest to smallest
	 */
	private void sortPlayerHands() {
		for (Player p : players) {
			Hand aHand = p.getHand();
			aHand.sortHand();
			p.setHand(aHand);
		}
	}

	/*
	 * Prints out the winner(s) of the Poker game
	 */
	private void printWinners() {
		// this.printAllHands();
		Hand winningHand = this.players[this.players.length - 1].getHand();
		String result = "";
		for (int i = this.players.length - 1; i >= 0; i--) {
			Player aPlayer = this.players[i];
			// determine ties
			if (aPlayer.getHand().compareTo(winningHand) == 0) {
				result = aPlayer.getPlayerNum() + " " + result;
			} else {
				break; // break out of for loop since there will be no more winners since the array is
						// sorted
			}

		}
		System.out.print(result);
	}

	/*
	 * Debugging method. Prints hands of all players
	 */
	@SuppressWarnings("unused")
	private void printAllHands() {
		for (Player p : players) {
			System.out.println(p.getHand().toString() + " " + p.getHand().getHandRank());
		}
	}

}
