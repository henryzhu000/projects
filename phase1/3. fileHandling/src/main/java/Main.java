
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * This program can read, write or append to a file
 * Writing to an existing file will overwrite it
 * Appending to an existing file will add additional data to it
 * If appending is used on a file that doesn't exist, then it'll create the file and insert the data
 * 
 * @Author Guang Zhu
 * */

public class Main {

	// can write or append. boolean append=false means write, true means append
	public static boolean write(String fileName, String input, boolean append) {

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			fw = new FileWriter(fileName, append);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			out.println(input);
			out.close();
			bw.close();
			fw.close();
		} catch (Exception e) {
			// closes connection and unwritten buffer in case an exception is thrown
			try {
				bw.flush();
				out.close();
				bw.close();
				fw.close();
			} catch (Exception ee) {
			}

			return false;
		}
		return true;
	}

	public static boolean read(String fileName) {
		File file = new File(fileName);
		// check if file exists before reading from it
		if (file.exists()) {
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
				String st;
				while ((st = br.readLine()) != null) {
					System.out.println(st);
				}
				br.close();
			} catch (Exception e) {
				try {
					br.close();
				} catch (Exception ee) {
				}
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String consoleInput;
		System.out.println("would you like to a)read b)write or c)append to a file?");
		int state;
		// while loop exits when user types in a b or c, otherwise will keep asking for
		// input
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
				} else if (consoleInput.charAt(0) == 'c') {
					state = 2;
					break;
				} else {
					System.out.println("please type a,b, or c");
				}
			} else {
				System.out.println("please type a,b, or c");
			}
		}
		// asks user for file url, ask for additional console input if user selected
		// write or append
		System.out.println("type in a file url");
		String fileName;
		while (true) {
			fileName = in.nextLine();
			if (state == 0) {
				if (read(fileName)) {
					break;
				}
			} else {
				System.out.println("type in an input");
				consoleInput = in.nextLine();
				if (state == 1) {
					if (write(fileName, consoleInput, false)) {
						break;
					}
				} else if (state == 2) {
					if (write(fileName, consoleInput, true)) {
						break;
					}
				}
			}
			System.out.println("file io error, please try again");
		}

	}

}
