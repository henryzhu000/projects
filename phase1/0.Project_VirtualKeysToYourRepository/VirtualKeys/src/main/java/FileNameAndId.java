
public class FileNameAndId {
	String fileName;
	int index;
	int fileIncrement;

	FileNameAndId(String fileName, int index, String fileIncrement) {
		this.fileName = fileName;
		this.index = index;
		this.fileIncrement = Integer.parseInt(fileIncrement.substring(8));

	}

	public int compareTo(FileNameAndId input) {
		return fileName.compareTo(input.fileName);
	}
}
