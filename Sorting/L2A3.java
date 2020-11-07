/* 
 * README
 * 
 * Author: Larisa Cof
 * 
 * Date: 2019-09-19
 * 
 * Add a method which counts the number 
 * of inversions in the input array and prints
 * a list of all inversions on the format 
 * [i,a[i]], [j, a[j]] where i and j are 
 * indices and a[i], a[j] are the values 
 * of the elements. Call the method from main() 
 * before the array is sorted. Calculate the 
 * time complexity for the algorithm.
 * ____________________________________________
 * 
 * NUMBER OF INVERSIONS:
 * Pair of elements that are in the wrong place. 
 * If a[i] > a[j] but i < j --> inversion.
 * Note: for insertion sort, the number of 
 * inversions is equal to the number of swaps.
 * 
 * insertionsort performs the insertion sort algorithm.
 * 
 * less is a boolean method that compares two integer 
 * values (is less than).
 * 
 * exch swaps two elements position in the 
 * array when the condition in insertionsort is fulfilled.
 * The count variable keeps track of the number of swaps that are made.
 * Count is increased everytime exch() is called.
 * 
 * printarray prints the entire array.
 * 
 * numOfInversions finds all inversions in the array and counts them.
 * If a[i] > a[j] but i < j --> inversion.
 * 
 * Main:
 * User enters number of elements and input elements.
 * The inversions are calculated prior to the sort.
 * The array is then sorted and the algorithm is shown with all steps.
 * Number of inversions and swaps are printed.
 * 
 */

package lab2;
import java.util.Scanner;

public class L2A3 {

	public static int count = 0; // number of swaps
	// Sort a[] into increasing order

	public static void insertionsort(Integer[] a) {

		int N = a.length; // number of elements in the array

		// Go through all elements in the array
		for (int i = 1; i < N; i++) {

			// Enter this loop for all j's that fulfill the conditions for this i
			for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {

				// for all a[j] < a[j-1] swap places in the array
				exch(a, j, j - 1);

				// print content of the array after every swap
				printarray(a);
			}
		}
	}

	// Is less than
	private static boolean less(Integer v, Integer w) {
		return v.compareTo(w) < 0;
	}

	// Swaps two elements in the array
	private static void exch(Integer[] a, int b, int c) //
	{
		Integer t = a[b]; // save initial value
		a[b] = a[c]; // exchange
		a[c] = t; // adjust
		count++; // increment count (swaps)
	}
	
	// Prints the array
	private static void printarray(Integer[] a) { // Print the array, on a single line
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	// Finds and counts the number of inversions
	static int numOfInversions(Integer[] a, int arrayLength) {
		int inversionCounter = 0; // counter to show the number of inversions

		for (int i = 0; i < arrayLength; i++)
			for (int j = i + 1; j < arrayLength; j++)
				if (a[i] > a[j]) {
					inversionCounter++; // increase number of inversions

					System.out.print('\n' + "[" + a[i] + "," + a[j] + "] "); // if a[i] > a[j] but i < j --> inversion
																				
				}
		System.out.println("");
		return inversionCounter;
	}

	// Main
	public static void main(String[] args) {

		System.out.println("Enter the number of elements you wish to sort: ");
		Scanner size = new Scanner(System.in); // user enters number of elements
		int length = size.nextInt(); // store the length of users array in an int
		Integer[] a = new Integer[length]; // create array with the same number of elements as length

		System.out.println("Enter all the elements you would like to sort: ");
		Scanner elements = new Scanner(System.in);

		for (int i = 0; i < length; i++) {
			a[i] = elements.nextInt(); // fill the array
		}

		elements.close();
		size.close();

		System.out.println("All inversions:");
		int inv = numOfInversions(a, length); // call the method before the array is sorted

		System.out.println();
		System.out.println("Insertion sort (ascending order): ");

		insertionsort(a);

		System.out.println();
		System.out.println("Sorted in ascending order.");
		printarray(a);
		System.out.println();
		System.out.println("Number of swaps: " + count);
		System.out.println("Number of inversions: " + inv);
	}
}

/* EXAMPLE EXECUTION
Enter the number of elements you wish to sort: 
6
Enter all the elements you would like to sort: 
1
2
4
3
5
0
All inversions:

[1,0] 
[2,0] 
[4,3] 
[4,0] 
[3,0] 
[5,0] 

Insertion sort (ascending order): 
1 2 3 4 5 0 
1 2 3 4 0 5 
1 2 3 0 4 5 
1 2 0 3 4 5 
1 0 2 3 4 5 
0 1 2 3 4 5 

Sorted in ascending order.
0 1 2 3 4 5 

Number of swaps: 6
Number of inversions: 6

 */
