import java.util.ArrayList;
import java.util.Scanner;

/*
 * This program has had unimplemented methods implemented, and some existing code was debugged
 * Bugs that were fixed include 
 * 1.expenses array list was re initilized everytime the options menu showed up, so now it's only initialized once at startup 
 * 2.option selection would system.exit on any input other than 1-6, so now you only get an error message if you pick something other than 1-6
 * 3.option selection was done recursively which could cause stack issues in large call quantities so recursion has been replaced with a while loop
 * 4.option 6 didn't call system.exit so System.exit was added
 * 
 * search and sorting was implemented
 * there's a sorted and unsorted array. without an unsorted array, the sorted array would have to do a sort for every added element
 * so the unsorted array acts as a buffer until sort is called, then the unsorted array gets sorted into the sorted array
 * 
 * @Author Guang Zhu
 * */

public class Main {
	static ArrayList<Integer> expenses = new ArrayList<Integer>();
	static Scanner sc = new Scanner(System.in);
	static int sortedArray[];


	static void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	public static void sort(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}

	public static void main(String[] args) {

		initial();
		/* System.out.println("Hello World!"); */
		System.out.println("\n**************************************\n");
		System.out.println("\tWelcome to TheDesk \n");
		System.out.println("**************************************");
		optionsSelection();

	}

	public static void initial() {

		expenses.add(1000);
		expenses.add(2300);
		expenses.add(45000);
		expenses.add(32000);
		expenses.add(110);
	}

	public static boolean linearSearch(int numToSearch) {
		for (int i = 0; i < expenses.size(); i++) {
			if (numToSearch == expenses.get(i)) {
				return true;
			}
		}
		return false;
	}

	static int binarySearch(int array[], int x, int low, int high) {

		// Repeat until the pointers low and high meet each other
		while (low <= high) {
			int mid = low + (high - low) / 2;

			if (array[mid] == x)
				return mid;

			if (array[mid] < x)
				low = mid + 1;

			else
				high = mid - 1;
		}

		return -1;
	}

	public static void displayExpenses() {
		System.out.print("sorted list: ");
		if (sortedArray != null) {
			for (int i = 0; i < sortedArray.length; i++) {
				System.out.print(sortedArray[i] + ", ");
			}
		} else {
			System.out.print("{empty}");
		}
		System.out.print("\nunsorted list: ");
		for (int i = 0; i < expenses.size(); i++) {
			System.out.print(expenses.get(i) + ", ");
		}
		if (expenses.size() == 0) {
			System.out.print("{empty}");
		}
		System.out.println("");
	}

	private static void optionsSelection() {
		String[] arr = { "1. I wish to review my expenditure", "2. I wish to add my expenditure",
				"3. I wish to delete my expenditure", "4. I wish to sort the expenditures",
				"5. I wish to search for a particular expenditure", "6. Close the application" };
		int[] arr1 = { 1, 2, 3, 4, 5, 6 };
		int slen = arr1.length;
		for (int i = 0; i < slen; i++) {

			System.out.println(arr[i]);
			// display the all the Strings mentioned in the String array
		}

		System.out.println("\nEnter your choice:\t");
		int options = sc.nextInt();
		while (true) {
			switch (options) {
			case 1:
				System.out.println("Your saved expenses are listed below: \n");
				displayExpenses();
				optionsSelection();
				break;
			case 2:
				System.out.println("Enter the value to add your Expense: \n");
				int value = sc.nextInt();
				expenses.add(value);
				System.out.println("Your value is updated\n");
				displayExpenses();
				optionsSelection();

				break;
			case 3:
				System.out.println(
						"You are about the delete all your expenses! \nConfirm again by selecting the same option...\n");
				int con_choice = sc.nextInt();
				if (con_choice == options) {
					expenses.clear();
					sortedArray = null;
					System.out.println("All your expenses are erased!\n");
				} else {
					System.out.println("Oops... try again!");
				}
				optionsSelection();
				break;
			case 4:
				sortExpenses();
				optionsSelection();
				break;
			case 5:
				searchExpenses();
				optionsSelection();
				break;
			case 6:
				closeApp();
				break;
			default:
				System.out.println("You have made an invalid choice! try again");
				optionsSelection();
				break;
			}

		}
	}

	private static void closeApp() {
		System.out.println("Closing your application... \nThank you!");
		System.exit(0);
	}

	private static void searchExpenses() {
		System.out.println("Enter the expense you need to search:\t");
		int input = sc.nextInt();
		int searchSortedArrayResult = -1;
		if (sortedArray != null) {
			searchSortedArrayResult = binarySearch(sortedArray, input, 0, sortedArray.length-1);
		}
		if (searchSortedArrayResult != -1) {
			System.out.println("expense exists in the sorted list at position: " + searchSortedArrayResult);
		} else {
			if (linearSearch(input)) {
				System.out.println("expense exists in the unsorted list");
			} else {
				System.out.println("doesn't exist in the list");
			}
		}
	}

	private static void sortExpenses() {
		sortedArray = new int[expenses.size()];
		for (int i = 0; i < sortedArray.length; i++) {
			sortedArray[i] = expenses.get(i);
		}
		sort(sortedArray, 0, sortedArray.length - 1);
		expenses = new ArrayList<Integer>();
	}
}
