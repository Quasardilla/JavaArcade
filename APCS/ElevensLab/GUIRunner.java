package ElevensLab;

/**
 * This is a class that plays the GUI version of the Thirteens game.
 * See accompanying documents for a description of how Thirteens is played.
 */
public class GUIRunner {

	/**
	 * Plays the GUI version of Thirteens.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		// GameBoard board = new ThirteensBoard();
		GameBoard board = new ElevensBoard();
		CardGameGUI gui = new CardGameGUI(board);
		gui.displayGame();
	}
}
