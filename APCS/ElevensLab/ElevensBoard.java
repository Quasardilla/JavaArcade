package ElevensLab;

import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard extends GameBoard {

	
	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	public ElevensBoard() {
		super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	}

	/**
	 * Check for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	private boolean containsPairSum11(List<Integer> selectedCards) {
		for(int i = 0; i < selectedCards.size(); i++)
			for(int j = i + 1; j < selectedCards.size(); j++)
				if(cards[selectedCards.get(i)].pointValue() + cards[selectedCards.get(j)].pointValue() == 11)
					return true;
		return false;
	}

	/**
	 * Check for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return true if the board entries in selectedCards
	 *              include a jack, a queen, and a king; false otherwise.
	 */
	private boolean containsJQK(List<Integer> selectedCards) {
		boolean jack = false, queen = false, king = false;

		for(int i = 0; i < selectedCards.size(); i++) {
			if(cards[selectedCards.get(i)].rank().equals("jack"))
				jack = true;
			else if(cards[selectedCards.get(i)].rank().equals("queen"))
				queen = true;
			else if(cards[selectedCards.get(i)].rank().equals("king"))
				king = true;
		}

		if(jack && queen && king)
			return true;

		return false;
	}


	@Override
	public boolean isLegal(List<Integer> selectedCards) {
		return containsPairSum11(selectedCards) || containsJQK(selectedCards);
	}
}
