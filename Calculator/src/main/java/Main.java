
import java.util.ArrayList;
import java.util.Scanner;

	/*
	The math equation input string is first separated into blocks by '+' or '-'.
	Calculate any multiplication or division in each block so a number can be returned to be added or subtracted
	
	Author Guang Zhu
	*/
public class Main {


	
	
	public static Double multiplyAndDivide(String input) {
		// records whether each number should be multiplied or divided 
		ArrayList<Boolean> multiplyOrDivide = new ArrayList<Boolean>();		
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '*') {
				multiplyOrDivide.add(true);
			} else if (input.charAt(i) == '/') {
				multiplyOrDivide.add(false);
			}
		}
		
		String numberTokens[] = input.split("[*|/]");
		//Multiplication or division is only associated with the second or further array element in the array of numbers. 
		//So if there's only one number in split array then you have no arithmetic to do
		Double returnValue = Double.parseDouble(numberTokens[0]);
		for (int i = 1; i < numberTokens.length; i++) {
			if (multiplyOrDivide.get(i - 1)) {
				returnValue *= Double.parseDouble(numberTokens[i]);
			} else {
				returnValue /= Double.parseDouble(numberTokens[i]);
			}
		}
		return returnValue;
	}
	
	public static Double addAndSubtract(String input) {
		input = input.trim();
		// records whether each number should be added or divided
		ArrayList<Boolean> plusOrMinus = new ArrayList<Boolean>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '+') {
				plusOrMinus.add(true);
			} else if (input.charAt(i) == '-') {
				plusOrMinus.add(false);
			}
		}
		
		String numberTokens[] = input.split("[+|-]");
		Double returnValue = multiplyAndDivide(numberTokens[0]);
		//Addition or subtraction is only associated with the second or further array element in the array of numbers. 
		//So if there's only one number in split array then you have no arithmetic to do
		for (int i = 1; i < numberTokens.length; i++) {
			if (plusOrMinus.get(i - 1)) {
				returnValue += multiplyAndDivide(numberTokens[i]);
			} else {
				returnValue -= multiplyAndDivide(numberTokens[i]);
			}
		}
		return returnValue;
	}

	public static void main(String[] args) {

	//	String input = "3*9+9/2+9.222+2-4/3/1/2*3";
		
		System.out.println("Welcome to Calculator! Type your equation bellow"); 
		System.out.println("");
		Scanner in = new Scanner(System.in);
		String consoleInput;
		while((consoleInput=in.nextLine())!="") {
			System.out.println("answer is: "+addAndSubtract(consoleInput));
			System.out.println("");
		}
		

	}

}
