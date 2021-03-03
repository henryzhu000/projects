import java.io.File;
import java.util.ArrayList;

public class SearchFile {

	String defaultDirectory = "";
	String fileName;
	int maxSize;

//	public boolean startup() {}

	public boolean startup(String fileName) {

		this.fileName = defaultDirectory + fileName;
		if (FileTool.readOrgional("OrderingSize" + this.fileName)) {
			maxSize = Integer.parseInt(FileTool.size);
		} else {
			return false;
		}

		return true;
	}

	String search(String input) {

		int result = binarySearch(input, 0, maxSize - 1);
		if (result == -1) {
			return "not found";
		} else {
			return "exists";
		}
	}

	int binarySearch(String x, int low, int high) {

		// Repeat until the pointers low and high meet each other
		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (getValue(mid).compareTo(x) == 0)
				return mid;

			if (getValue(mid).compareTo(x) <= 0)
				low = mid + 1;

			else
				high = mid - 1;
		}

		return -1;
	}

	int binarySearchFileNames(FileNameAndId[] input, String search) {
		int low = 0;
		int high = input.length - 1;
		// Repeat until the pointers low and high meet each other
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (input[mid].fileName.compareTo(search) == 0)
				return mid;

			if (input[mid].fileName.compareTo(search) <= 0)
				low = mid + 1;

			else
				high = mid - 1;
		}

		return -1;
	}

	int locationStartA;
	int locationEndB;

	String getValue(int location) {
		String returned = "";
		location = location * 2;
		locationStartA = FileTool.readIntegerSingle("Ordering" + fileName, location);
		locationEndB = FileTool.readIntegerSingle("Ordering" + fileName, location + 1);
		if (FileTool.read("Sorted" + fileName, locationStartA, locationEndB)) {
			returned = new String(FileTool.readResults);
		}
		return returned;
	}

}
