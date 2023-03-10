//By Nate Levison
//Public Domain (With Credit)
//Version 2.2.0.1 - Aug 23

import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class Calculator {
	
	public static void main(String[] args) {
		
		ImageIcon icon = new ImageIcon("src/icon.png");
		
		JOptionPane.showMessageDialog(null, "Welcome to Nate Levison's \nCalculator!\n \n \n \n" + testclass.splash(), "Calculator", JOptionPane.ERROR_MESSAGE, icon);
		
		boolean again = true;
		
		while(again == true) {
			
			JOptionPane.showMessageDialog(null,runMath(),"Calculator",JOptionPane.QUESTION_MESSAGE);
			
			//again = Boolean.parseBoolean(JOptionPane.showInputDialog(null,"Do you want to run again? True or False"));
			int againint = JOptionPane.showConfirmDialog(null, "Do you want to run again?", "Rerunner", JOptionPane.YES_NO_OPTION);
			if (againint == JOptionPane.YES_OPTION) {
			    again = true;
			} else {
			    again = false;
			    System.exit(0);
			}
		}
		
	}
	
	
	public static String runMath() {
		
		double solution1;
		double solution2;
		
		int ID = 0;
		
		String finalans;
		String vars[] = { "a", "b", "c" };
		String choices[] = { "Addition", "Subtraction", "Multiplication", "Division", "Mean", "Median", "Mode", "Power", "Quadratic Formula", "Pythag Theorem" };
		String opChoice= (String) JOptionPane.showInputDialog(null, "What operation do you want to open?", "Operation Prompt", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		
		System.out.println(opChoice);
		for(int i = 0; i < choices.length; i++) {
			if(opChoice == choices[i]) {
				ID = i + 1;
			}
		}
		
		//Operation ID? 1 = Addition, 2 = Subtraction, 3 = Multiply, 4 = Division, 5 = Mean, 6 = Median, 7 = Mode, 8 = Power, 9 = Quadratic Formula, 10 = Pythag
		solution1 = 0;
		solution2 = 0;
		
		switch (ID) {
		case 1: {
				System.out.println("How many numbers do you want to add?");
				double[] inputs = new double[Integer.valueOf(JOptionPane.showInputDialog(null,"How many numbers do you want to add?", "Input", JOptionPane.QUESTION_MESSAGE))];
				for(int i = 0; i < inputs.length; i++) {
					System.out.println("Input number " + i);
					inputs[i] = (double) Double.valueOf(JOptionPane.showInputDialog(null,"Input number " + (i + 1)));
				}
				for(int i = 0; i < inputs.length; i++) {
					solution1 += inputs[i];
				}
				finalans = "The sum of those " + inputs.length + " numbers is " + solution1;
				copy(solution1+"", null);
				return finalans;
				}	
		case 2: {
				System.out.println("How many numbers do you want to subtract?");
				System.out.println("All numbers are subtracted from the first number, in inputted order.");
				double[] inputs = new double[Integer.valueOf(JOptionPane.showInputDialog(null,"How many numbers do you want to subtract? \nAll numbers are subtracted from the first number, in inputted order."))];
				for(int i = 0; i < inputs.length; i++) {
					System.out.println("Input number " + i);
					inputs[i] = Double.valueOf(JOptionPane.showInputDialog(null,"Input number " + (i + 1)));
				}
				solution1 = inputs[0];
				for(int i = 1; i < inputs.length; i++) {
					solution1 -= inputs[i];
				}
				finalans = "The difference of those " + inputs.length + " numbers is " + solution1 + ".\nAnswer copied to clipboard.";
				copy(solution1+"", null);
				return finalans;
				}
		case 3: {
				System.out.println("How many numbers do you want to multiply?");
				double[] inputs = new double[Integer.valueOf(JOptionPane.showInputDialog(null,"How many numbers do you want to add?"))];
				for(int i = 0; i < inputs.length; i++) {
					System.out.println("Input number " + i);
					inputs[i] = Double.valueOf(JOptionPane.showInputDialog(null,"Input number " + (i + 1)));
				}
				solution1 = inputs[0];
				for(int i = 1; i < inputs.length; i++) {
					solution1 *= inputs[i];
				}
				finalans = "The product of those " + inputs.length + " numbers is " + solution1 + ".\nAnswer copied to clipboard.";
				copy(solution1+"", null);
				return finalans;
				}
		case 4: {
				double[] inputs = new double[Integer.valueOf(JOptionPane.showInputDialog(null,"How many numbers do you want to divide? \nAll numbers are divided from the first number, in inputted order."))];
				for(int i = 0; i < inputs.length; i++) {
					inputs[i] = Double.valueOf(JOptionPane.showInputDialog(null,"Input number " + (i + 1)));
				}
				solution1 = inputs[0];
				for(int i = 1; i < inputs.length; i++) {
					solution1 /= inputs[i];
				}
				finalans = "The quotient of those " + inputs.length + " numbers is " + solution1 + ".\nAnswer copied to clipbaord.";
				copy(solution1+"", null);
				return finalans;
				}
		case 5: {
				double[] inputs = new double[Integer.valueOf(JOptionPane.showInputDialog(null,"How many numbers do you want to average?"))]; 
				for(int i = 0; i < inputs.length; i++) {
					inputs[i] = Double.valueOf(JOptionPane.showInputDialog(null,"Input number " + (i + 1)));
				}
				for(int i = 0; i < inputs.length; i++) {
					solution1 += inputs[i];
				}
				solution1 /= inputs.length;
				finalans = "The mean of those " + inputs.length + " numbers is " + solution1 + ".\nAnswer copied to clipboard.";
				copy(solution1+"", null);
				return finalans;
				}
		case 6: {
				double[] inputs = new double[Integer.valueOf(JOptionPane.showInputDialog(null, "How many numbers do you want to find the median of?"))];
				for(int i  = 0; i < inputs.length; i++) {
					inputs[i] = Double.valueOf(JOptionPane.showInputDialog(null,"Input number " + (i + 1)));
				}
				Arrays.sort(inputs);
				if(inputs.length % 2 == 0) {
					solution1 = (inputs[inputs.length / 2] + inputs[inputs.length / 2 - 1]) / 2;
				} else solution1 = inputs[inputs.length / 2];
				finalans = "The median of those " + inputs.length + " numbers is " + solution1 + ".\nAnswer copied to clipbard.";
				copy(solution1+"", null);
				return finalans;
				}
		case 7: {
				double[] inputs = new double[Integer.valueOf(JOptionPane.showInputDialog(null, "How many numbers do you want to find the mode of?"))];
				for(int i = 0; i < inputs.length; i++) {
					inputs[i] = Double.valueOf(JOptionPane.showInputDialog(null,"Input number " + (i + 1)));
				}
				Arrays.sort(inputs);
			    int count2 = 0;
			    int count1 = 0;
			    
			    double popular1 = 0;
			    double popular2 = 0;
			    
			    for(int i = 0; i < inputs.length; i++) {
			            popular1 = inputs[i];
			            count1 = 1;
			        for(int j = i + 1; j < inputs.length; j++) {
			        	if(popular1 == inputs[j]) {
			            	count1++;
			            }
			        }
			        if(count1 > count2) {
			            popular2 = popular1;
			            count2 = count1;
			        } else if(count1 == count2) {
			            popular2 = Math.min(popular2, popular1); //when there are two winners, the lowest one is the mode
			        }
			    }  
			    finalans = "The mode of those " + inputs.length + " numbers is " + popular2 + ", with a staggering " + count2 + " appearances.\nAnswer copied to clipboard.";
			    copy(popular2+"", null);
			    return finalans;
			    }
		case 8: {
				solution1 = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What number do you want to raise?"));
				double savevar = solution1;
				
				solution2 = Integer.valueOf(JOptionPane.showInputDialog(null, "What power do you want to raise it to?"));
				for(int i  = 1; i < solution2; i++) {
					solution1 *= savevar;
				}
				finalans = savevar + " raised to the power of " + solution2 + " is " + solution1 + ".";
				copy(solution1 + "", null);
				return finalans;
				}
		case 9: {
				JOptionPane.showMessageDialog(null, "Note: equation must be in format ax^2 + bx + c = 0, where a is not 0");
				double inputs[] = new double[3];
				
				inputs[0] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the coefficient of x^2?"));
				inputs[1] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the coefficient of x?"));
				inputs[2] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the added constant at the end?"));
				
				solution1 = 0 - inputs[1] + (Math.sqrt((inputs[1] * inputs[1]) - 4 * (inputs[0] * inputs[2])));
				solution2 = 0 - inputs[1] - (Math.sqrt((inputs[1] * inputs[1]) - 4 * (inputs[0] * inputs[2])));
				
				solution1 /= (2 * inputs[0]);
				solution2 /= (2 * inputs[0]);
				
				finalans = "The value of x in the expression " + inputs[0] + "x^2 + " + inputs[1] + "x + " + inputs[2] + "\n can either be " + solution1 + " or " + solution2 + ".\nAnswer copied to clipboard.";
				copy(solution1 + " or " + solution2, null);
				return finalans;
				}
		case 10:{ 
				String whichvar = (String) JOptionPane.showInputDialog(null, "What side are you solving for?", "Pythag Options", JOptionPane.QUESTION_MESSAGE, null, vars, vars[2]);
				double inputs[] = new double[2];
				if(whichvar == "a") {
					
					inputs[0] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the value of leg b?"));
					inputs[1] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the value of the hypoteneuse?"));
					solution1 = Math.sqrt(inputs[1] * inputs[1] - inputs[0] * inputs[0]);
					
				} else if(whichvar == "b") { 
					
					inputs[0] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the value of leg a?"));
					inputs[1] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the value of the hypoteneuse?"));
					solution1 = Math.sqrt(inputs[1] * inputs[1] - inputs[0] * inputs[0]);
					
				} else {
					
					inputs[0] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the value of leg a?"));
					inputs[1] = (double) Double.valueOf(JOptionPane.showInputDialog(null, "What is the value of leg b?"));
					solution1 = Math.sqrt(inputs[0] * inputs[0] + inputs[1] * inputs[1]);
					
				}
				finalans = "The value of " + whichvar + " is " + solution1 + ".\nAnswer copied to clipboard.";
				copy(solution1 + "",null);
				return finalans;
				}	
		default: return "No solution to give.";
		}
		
	}
	
	public static void desc() {

		String ops[] = {"Addition", "Subtraction", "Multiplication", "Division", "Mean", "Median", "Mode", "Power", "Quadratic Formula", "Pythag Theorem"};
		String chosen = (String) JOptionPane.showInputDialog(null, "Choose", "Help Desk", JOptionPane.INFORMATION_MESSAGE, null, ops, ops[0]);
		System.out.println(chosen);
		System.out.println(ops[1] == chosen);
		if(ops[0] == chosen) {
			alert("Addition: adds all numbers given.");
		} else if(ops[1] == chosen) {
			alert("Subtraction: subtracts all numbers given, from index 0 to final index, in order.");
		} else if(ops[2] == chosen) {
			alert("Multiplication: multiplies all numbers given.");
		} else if(ops[3] == chosen) {
			alert("Division: divides all numbers given from index 0 to final index, in order.");
		} else if(ops[4] == chosen) {
			alert("Mean: the average of all numbers given.");
		} else if(ops[5] == chosen) {
			alert("Median: the median of all numbers given.");
		} else if(ops[6] == chosen) {
			alert("Mode: the mode of all numbers given.");
		} else if(ops[7] == chosen) {
			alert("Power: raises first number to the power of second number.");
		} else if(ops[8] == chosen) {
			alert("Quadratic Formula: the value of x if ax^2 + bx + c = 0, and a is not = 0.");
		} else if(ops[9] == chosen) {
			alert("Pythag: the value of either a or c if given the other two variables (a, b, hypotenuse)");
		}
		
	}
			
	public static void alert(String input) {

		//lazy moment
		JOptionPane.showMessageDialog(null, input);
		
	}
		
	public static void copy(String s, ClipboardOwner owner) {

	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    Transferable transferable = new StringSelection(s);
	    clipboard.setContents(transferable, owner);
	    
	}
}