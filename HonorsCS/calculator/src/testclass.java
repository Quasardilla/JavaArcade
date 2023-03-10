public class testclass {
	public static String splash() {
		String[] splashes = {"Among us!", "The best calculator!", "Also try Calculator!"};
		int rand = (int) Math.floor(Math.random()*(2-0+1)+0);
		return splashes[rand];
	}
}
