
public class NumOfFiles {
	static int size;

	public static void newFile() {

		if (FileTool.readOrgional("size.txt")) {
			size = Integer.parseInt(FileTool.size);
			size++;
			FileTool.write("size.txt", size + "", false);
		} else {
			FileTool.write("size.txt", "0", false);
		}

	}

}
