import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

public class FileTool {

	// can write or append. boolean append=false means write, true means append
	public static boolean writeInteger(String fileName, ArrayList<Integer> input, boolean append) {
		FileOutputStream fos;
		DataOutputStream dos;
		try {
			fos = new FileOutputStream(fileName);
			dos = new DataOutputStream(fos);

			for (int i = 0; i < input.size(); i++) {
				dos.writeInt(input.get(i));
			}
			fos.close();
			dos.close();

		} catch (Exception e) {
			// closes connection and unwritten buffer in case an exception is thrown
			try {
			} catch (Exception ee) {
			}

			return false;
		}
		return true;
	}

	public static int readIntegerSingle(String fileName, int location) {
		int returned = -1;

		try {
			DataInputStream dataIn = new DataInputStream(new FileInputStream(fileName));
			dataIn.skip(location * 4);
			returned = dataIn.readInt();
			dataIn.close();
		} catch (Exception e) {

		}

		return returned;
	}

	// can write or append. boolean append=false means write, true means append
	public static boolean write(String fileName, String input, boolean append) {

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
			fw = new FileWriter(fileName, append);
			bw = new BufferedWriter(fw);
			out = new PrintWriter(bw);
			// fw.write(input);
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

	static ArrayList<String> returned;

	public static Boolean read(String fileName) {
		returned = new ArrayList<String>();
		File file = new File(fileName);
		String splitted[];
		BufferedReader br;
		// check if file exists before reading from it
		if (file.exists()) {
			try {
				br = new BufferedReader(new FileReader(file));
				String st;
				while ((st = br.readLine()) != null) {

					if (st.length() > 0) {
						splitted = st.split(" ");
						for (int i = 0; i < splitted.length; i++) {
							returned.add(splitted[i].trim());
						}
					}
				}
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
		try {
			br.close();
		} catch (Exception e) {
		}

		return true;
	}

	public static char[] readResults;

	public static Boolean read(String fileName, int location, int size) {

		File file = new File(fileName);
		BufferedReader br;
		readResults = new char[size];
		// check if file exists before reading from it
		if (file.exists()) {
			try {
				br = new BufferedReader(new FileReader(file));
				br.skip(location);
				br.read(readResults);

			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}

		try {
			br.close();
		} catch (Exception e) {
		}
		return true;
	}

	static String size;

	public static boolean readOrgional(String fileName) {
		File file = new File(fileName);
		BufferedReader br;
		// check if file exists before reading from it
		if (file.exists()) {
			try {
				br = new BufferedReader(new FileReader(file));
				String st;
				while ((st = br.readLine()) != null) {
					size = st;

				}
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
		try {
			br.close();
		} catch (Exception e) {
		}
		return true;
	}

}
