
public class SortThread implements Runnable {
	String fileName = "";
	boolean complete = false;

	public void run() {

		Main.sortFilePart2(fileName);

		complete = true;
		System.out.println("complete, type anything to continue");
	}
}
