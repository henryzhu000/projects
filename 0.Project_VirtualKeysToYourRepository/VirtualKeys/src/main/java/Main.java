import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static Thread thread;

	public static void option2Thread(String input) {
		SortThread sT = new SortThread();
		sT.fileName = input;
		thread = new Thread(sT, "T1");
		thread.start();
		System.out.println("type anything to cancel the file addition proccess midway");
		String consoleInput;

		consoleInput = in.nextLine();
		if (sT.complete) {
		} else {
			thread.stop();
			if (undoFileCreation != null) {
				for (int i = 0; i < undoFileCreation.size(); i++) {
					try {
						File file = new File(undoFileCreation.get(i));
						file.delete();
					} catch (Exception e) {
					}
				}
			}
			System.out.println("successfully cancelled");

		}

	}

	public static boolean sortFile(String fileName) {
		if (!FileTool.read(fileName)) {
			return false;
		}
		// sortFilePart2(fileName);
		option2Thread(fileName);
		return true;
	}

	static ArrayList<String> undoFileCreation;

	public static void sortFilePart2(String fileName) {
		undoFileCreation = new ArrayList<String>();
		try {

			String[] listToSort = new String[FileTool.returned.size()];
			for (int i = 0; i < FileTool.returned.size(); i++) {
				listToSort[i] = FileTool.returned.get(i);
			}
			Sorting.sort(listToSort, 0, listToSort.length - 1);
			ArrayList<Integer> orderingArray = new ArrayList<Integer>();
			StringBuffer newSorted = new StringBuffer("");
			int location = 0;
			for (int i = 0; i < FileTool.returned.size(); i++) {

				if (listToSort[i].length() != 0) {
					orderingArray.add(location);
					orderingArray.add(listToSort[i].length());
					newSorted.append(listToSort[i]);
					location += listToSort[i].length();
				}

			}
			NumOfFiles.newFile();

			FileTool.writeInteger("Ordering" + NumOfFiles.size, orderingArray, false);
			undoFileCreation.add("Ordering" + NumOfFiles.size);
			FileTool.write("Sorted" + NumOfFiles.size, newSorted.toString(), false);
			undoFileCreation.add("Sorted" + NumOfFiles.size);
			FileTool.write("OrderingSize" + NumOfFiles.size, orderingArray.size() / 2 + "", false);
			undoFileCreation.add("OrderingSize" + NumOfFiles.size);
			FileTool.write("fileName" + NumOfFiles.size, fileName, false);
			undoFileCreation.add("fileName" + NumOfFiles.size);
		} catch (OutOfMemoryError e) {
			System.out.println("not enough memory. Please try a smaller file as this is only a prototype currently");
		}
	}

	public static ArrayList<String> fileTraverse() {
		String[] pathnames;
		ArrayList<String> returned = new ArrayList<String>();
		
		// Creates a new File instance by converting the given pathname string
		// into an abstract pathname
		File f = new File(".");

		// Populates the array with names of files and directories
		pathnames = f.list();

		// For each pathname in the pathnames array
		int count = 0;
		for (String pathname : pathnames) {
			// Print the names of files and directories
			if (pathname.length() >= 8) {
				if (pathname.substring(0, 8).equals("fileName")) {
					returned.add(pathname);
					count++;
				}
			}
		}

		return returned;
	}

	static FileNameAndId fileNameList[];

	public static void fileNameList() {
		ArrayList<String> list = fileTraverse();
		fileNameList = new FileNameAndId[list.size()];
		for (int i = 0; i < list.size(); i++) {
			FileTool.readOrgional(list.get(i));
			fileNameList[i] = new FileNameAndId(FileTool.size, i, list.get(i));
		}
		Sorting.sortClass(fileNameList, 0, fileNameList.length - 1);
		for (int i = 0; i < fileNameList.length; i++) {
			System.out.println(i + ")" + fileNameList[i].fileName);
		}
	}

	public static void fileNameListWithoutDisplay() {
		ArrayList<String> list = fileTraverse();
		fileNameList = new FileNameAndId[list.size()];
		for (int i = 0; i < list.size(); i++) {
			FileTool.readOrgional(list.get(i));
			fileNameList[i] = new FileNameAndId(FileTool.size, i, list.get(i));
		}
		Sorting.sortClass(fileNameList, 0, fileNameList.length - 1);
	}

	public static void displayWelcomeMessage() {

		System.out.println("Virtual keys to your repository");
		System.out.println("");
		System.out.println("by guang zhu");
		System.out.println("");
		System.out.println("Your options are: ");
		if (fileNameList.length > 0) {
			System.out.println("1) display files");
			System.out.println("2) add txt file to repo to be sorted for later search");
			System.out.println("3) search file by index");
			System.out.println("4) search file by file name");
			System.out.println("5) delete file by index");
			System.out.println("6) delete file by file name");
		} else {
			System.out.println(
					"2) add txt file to repo to be sorted for later search. Other options will become avaliable once you add at least one file to repo");
		}
		System.out.println("7) exit");
	}

	static Scanner in = new Scanner(System.in);

	public static void option3() {

		String consoleInput;
		int index;
		System.out.println("enter an index between 0 and " + (fileNameList.length - 1));
		while (true) {
			consoleInput = in.nextLine();
			try {
				index = Integer.parseInt(consoleInput);
				if (index >= 0 && index < fileNameList.length) {
					break;
				}
			} catch (Exception e) {
				System.out.println("try again");
			}
		}
		System.out.println(
				"what word in the file would you like to search. the words don't contain space or next paragraph so if your search contains that, it'll yield no results");
		while (true) {
			consoleInput = in.nextLine();
			consoleInput.trim();
			if (consoleInput.length() > 0) {
				break;
			}
		}
		SearchFile sf = new SearchFile();
		sf.startup(fileNameList[index].fileIncrement + "");
		System.out.println(sf.search(consoleInput));

	}

	public static void option4() {

		String consoleInput;
		int index;
		System.out.println("enter an file name to search");
		while (true) {
			consoleInput = in.nextLine();
			if (consoleInput.length() > 0) {
				break;
			}
		}
		SearchFile sf = new SearchFile();
		index = sf.binarySearchFileNames(fileNameList, consoleInput);
		if (index != -1) {
			System.out.println(
					"what word in the file would you like to search. the words don't contain space or next paragraph so if your search contains that, it'll yield no results");
			while (true) {
				consoleInput = in.nextLine();
				if (consoleInput.length() > 0) {
					break;
				}
			}
			sf.startup(fileNameList[index].fileIncrement + "");
			System.out.println(sf.search(consoleInput));
		} else {
			System.out.println("not found");
		}
	}

	public static void option2() {
		System.out.println("type file to add to repo");
		String consoleInput = in.nextLine();
		int index;
		if (fileNameList.length == 0) {
			index = -1;
		} else {
			SearchFile sf = new SearchFile();
			index = sf.binarySearchFileNames(fileNameList, consoleInput);
		}
		if (index == -1) {
			if (sortFile(consoleInput)) {
				fileNameListWithoutDisplay();
			}

			else {
				System.out.println("file doesn't exist");
			}
		} else {
			System.out.println("file already exists");
		}

	}

	public static void option5() {
		int index = 0;
		boolean complete = false;
		System.out.println("please type an index number");
		String consoleInput = in.nextLine();
		try {
			index = Integer.parseInt(consoleInput);
			index = fileNameList[index].fileIncrement;
			complete = true;
		} catch (Exception e) {
			System.out.println("incorrect input");
			complete = false;
		}

		if (complete) {
			int incompleteCount = 0;

			File file = new File("fileName" + index);
			if (!file.delete()) {
				incompleteCount++;
			}
			file = new File("Ordering" + index);
			if (!file.delete()) {
				incompleteCount++;
			}
			file = new File("OrderingSize" + index);
			if (!file.delete()) {
				incompleteCount++;
			}
			file = new File("Sorted" + index);
			if (!file.delete()) {
				incompleteCount++;
			}
			if (incompleteCount == 0) {
				System.out.println("successfully deleted");
				fileNameListWithoutDisplay();
			} else {
				System.out.println("unsuccessfull at deleting " + incompleteCount);
			}
		}

	}

	public static void option6() {
		int index = 0;
		boolean complete = false;
		SearchFile sf = new SearchFile();

		System.out.println("please type a file name");
		String consoleInput = in.nextLine();
		try {
			index = sf.binarySearchFileNames(fileNameList, consoleInput);
			index = fileNameList[index].fileIncrement;
			complete = true;
		} catch (Exception e) {
			System.out.println("incorrect input");
			complete = false;
		}
		if (complete) {
			int incompleteCount = 0;

			File file = new File("fileName" + index);
			if (!file.delete()) {
				incompleteCount++;
			}
			file = new File("Ordering" + index);
			if (!file.delete()) {
				incompleteCount++;
			}
			file = new File("OrderingSize" + index);
			if (!file.delete()) {
				incompleteCount++;
			}
			file = new File("Sorted" + index);
			if (!file.delete()) {
				incompleteCount++;
			}
			if (incompleteCount == 0) {
				System.out.println("successfully deleted");
				fileNameListWithoutDisplay();
			} else {
				System.out.println("unsuccessfull at deleting " + incompleteCount);
			}
		}
	}

	public static void breakTime() {
		System.out.println("type anything to continue");
		in.nextLine();
	}

	public static void userInteraction() {
		fileNameListWithoutDisplay();
		String consoleInput;

		while (true) {
			displayWelcomeMessage();
			consoleInput = in.nextLine();
			if (consoleInput.length() == 1 && fileNameList.length > 0) {
				if (consoleInput.charAt(0) == '1') {
					fileNameList();
				} else if (consoleInput.charAt(0) == '2') {
					option2();
				} else if (consoleInput.charAt(0) == '3') {
					option3();
				} else if (consoleInput.charAt(0) == '4') {
					option4();
				} else if (consoleInput.charAt(0) == '5') {
					option5();
				} else if (consoleInput.charAt(0) == '6') {
					option6();
				} else if (consoleInput.charAt(0) == '7') {
					System.exit(0);
				}
			} else if (consoleInput.length() == 1 && fileNameList.length == 0) {
				if (consoleInput.charAt(0) == '2') {
					option2();
				} else if (consoleInput.charAt(0) == '7') {
					System.exit(0);
				}
			} else {
				if (fileNameList.length > 0) {
					System.out.println("please type a number between 1 and 8");
				} else {
					System.out.println("please type 2 or 7");
				}
			}
			if (consoleInput.charAt(0) == '1') {
				breakTime();
			}
		}

	}

	public static void main(String[] args) {
		/*
		 * sortFile("D:\\desktopmostly\\desktop\\Desktop\\htnshn2.txt");
		 * sortFile("D:\\desktopmostly\\desktop\\Desktop\\New Text Document.txt");
		 * sortFile("D:\\desktopmostly\\desktop\\Desktop\\response.txt");
		 * sortFile("D:\\desktopmostly\\desktop\\Desktop\\htnshn.txt");
		 */
		// String temp; for(int i=0;i<1000000;i++) {temp=Tools.intTo2Char(i);
		// if(i!=Tools.twoCharToInt(temp)) {System.out.println("not same"); break;} }
		// System.out.println((char)2);

		userInteraction();

	}

}
