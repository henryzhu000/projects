
import java.util.ArrayList;
import java.util.Scanner;

/*
 * This program finds the longest sequence of ascending numbers 
 * in a list populated either through console input or via random generator
 * 
 * 1,2,10 is ascending
 * 10,2,1 is not ascending
 * and 1,1,2 is only ascending on 1,2 because 1,1 is equal but not ascending
 * 
 * @Author Guang Zhu
 * */
public class Main {

	public static void main(String[] args) {

		ArrayList<Integer> max = new ArrayList<Integer>();
		ArrayList<Integer> currentCount = new ArrayList<Integer>();
		ArrayList<Integer> randomList = new ArrayList<Integer>();

		Scanner in = new Scanner(System.in);
		String consoleInput;
		System.out.println("This program will find the longest sequence of ascending numbers in a number list");
		System.out.println("would you like to a)input a list of numbers or b) generate random numbers");
		int state;

		// while loop exits when user types in a or b, otherwise will keep asking for input
		while (true) {
			consoleInput = in.nextLine();
			consoleInput = consoleInput.toLowerCase();
			if (consoleInput.length() == 1) {
				if (consoleInput.charAt(0) == 'a') {
					state = 0;
					break;
				} else if (consoleInput.charAt(0) == 'b') {
					state = 1;
					break;
				} else {
					System.out.println("please type a or b");
				}
			} else {
				System.out.println("please type a or b");
			}
		}

		//if user selects to input an list via the console
		if (state == 0) {
			System.out.println("type in your numbers bellow seperated by a comma. for example, 10,1,2");
			consoleInput = in.nextLine();
			String[] list = consoleInput.split(",");
			for (int i = 0; i < list.length; i++) {
				if (list[i].length() > 0) {
					randomList.add(Integer.parseInt(list[i]));
				}
			}
		}

		//if user selects to generate a random list
		else if (state == 1) {
			System.out.println("please input the number of random numbers to generate");
			System.out.println("the random numbers generated here range from 0-100");
			consoleInput = in.nextLine();
			int length = Integer.parseInt(consoleInput);
			int random;
			for (int i = 0; i < length; i++) {
				random = (int) (Math.random() * 101);
				randomList.add(random);
				System.out.print(random + ",");
			}
			System.out.println("");
		}

		//searches list for longest sequence of ascending numbers
		int lastNum = randomList.get(0);
		currentCount.add(lastNum);
		for (int i = 1; i < randomList.size(); i++) {
			//if current number is larger than last, add it to current sequence 
			if (randomList.get(i) > lastNum) {
				currentCount.add(randomList.get(i));
			} else { 
				//if current number is smaller than last, end current sequence. 
				//check if current sequence array size is larger than the previous max sequence's array size. if so, replace max sequence with current sequence
				//create new current sequence array list. and add the current number to current sequence, so it's beginning array size is 1
				if (currentCount.size() > max.size()) {
					max = currentCount;
				}
				currentCount = new ArrayList<Integer>();
				currentCount.add(randomList.get(i));
			}
			lastNum = randomList.get(i);
		}
		//if for loop ended, check if current sequence array size is larger than previous largest array size sequence
		if (currentCount.size() > max.size()) {
			max = currentCount;
		}
		System.out.println("");
		System.out.println("Longest sequence bellow. If 2 sequences have the same length, the first is shown");
		System.out.println(max);

	}

}
