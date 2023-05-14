package ElevensLab;

import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ThirteensBoard extends GameBoard{

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};


	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	public ThirteensBoard() {
		super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	}

    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        return containsPairSum13(selectedCards);
    }

    /**
	 * Check for an 13-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return true if the board entries in selectedCards
	 *              contain an 11-pair; false otherwise.
	 */
	private boolean containsPairSum13(List<Integer> selectedCards) {
		for(int i = 0; i < selectedCards.size(); i++)
			for(int j = i + 1; j < selectedCards.size(); j++)
				if(super.cards[selectedCards.get(i)].pointValue() + super.cards[selectedCards.get(j)].pointValue() == 13)
					return true;
		return false;
	}
    
}
