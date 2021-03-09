import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This application lets users input an email and verify if the format is correct.
 * If format is correct, it then checks if a matching email exists in the email search list.
 * If it doesn't exist, users can insert their inputed email into the search list.
 * 
 * @Author Guang Zhu
 * */

public class Main {

	final String regex = "\\w+\\@\\w+\\.\\w+";
	final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
	HashMap<String, String> emailList = new HashMap<String, String>();

	Scanner in = new Scanner(System.in);
	String consoleInput;

	public boolean validate(String stringToValidate) {
		Matcher matcher = pattern.matcher(stringToValidate);
		return matcher.find();
	}

	public void searchEmail(String input) {
		if (emailList.get(input) == null) {
			System.out
					.println("email doesn't exist, press any key to add email to search list, press only enter for no");

			consoleInput = in.nextLine();

			if (consoleInput.length() > 0) {
				emailList.put(input, "");
				System.out.println(input + " added to search list");
			} else {
				System.out.println("skipped adding");
			}
		} else {
			System.out.println("email exists");
		}
	}

	public void userInput() {

		String instructions = "Welcome to email search and validator. Start by typing in an email.\n";
		instructions += "It will then tell you if the email format is correct and then if it already exists in the search list.\n";
		instructions += "If it doesn't exist in the search list, you'll be asked if you want to add it to the search list.\n";
		instructions += "Type exit to close application\n";
		System.out.println(instructions);
		System.out.println("please enter an email address bellow:");
		while ((consoleInput = in.nextLine()) != "") {
			if (consoleInput.equals("exit")) {
				System.exit(0);
			} else if (validate(consoleInput)) {
				searchEmail(consoleInput);
			} else {
				System.out.println("invalid email format");
			}
			System.out.println("\nplease enter an email address bellow:");
		}
		in.close();
	}

	public static void main(String[] args) {
		Main m = new Main();
		m.userInput();
	}

}
