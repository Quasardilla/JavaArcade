public class Solve {
	public static double Math(double[] inputs, int ID) {
		switch (ID) {
		case 1: return add(inputs);
		case 2: return subtract(inputs);
		case 3: return multiply(inputs);
		case 4: return divide(inputs);
		default: return 0;
		}
	}
	public static double add(double[] in) {
		double x = 0;
		for(int i = 0; i < in.length; i++) {
			x += in[i];
		}
		return x;
	}
	public static double subtract(double[] in) {
		double x = in[in.length];
		for(int i = 1; i < in.length; i++) {
			x -= in[i];
		}
		return x;
	}
	public static double multiply(double[] in) {
		double x = 1;
		for(int i = 1; i < in.length; i++) {
			x *= in[i];
		}
		return x;
	}
	public static double divide(double[] in) {
		double x = in[0];
		for(int i = 1; i < in.length; i++) {
			x /= in[i];
		}
		return x;
	}
	public static boolean isPrime(int x) {
		double y = x * 1.0;
		int count = 0;
		for(int i = 1; i < x / 2; i++) {
			System.out.println(x + " divided by " + i + " is " + (y / i));
			if((int) y / i == y / i) {
				count++;
				System.out.println("Divisible by " + i + ".");
			} else System.out.println("Not divisible by " + i + ".");
		}
		
	
		if(count > 0) {
			return false;
		} else return true;
	}
}
