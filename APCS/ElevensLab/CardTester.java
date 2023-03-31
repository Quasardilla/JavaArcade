package ElevensLab;

/**
 * This is a class that tests the Card class.
 */
public class CardTester {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		Card c1 = new Card("jack", "diamonds", 11);  
		Card c2 = new Card("ace", "hearts", 1);
		Card c3 = new Card("7", "clubs", 7);
		Card c4 = new Card("jack", "diamonds", 11);
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
	   
	   System.out.println(c1.matches(c2));
	   System.out.println(c1.matches(c4));
	}
}
