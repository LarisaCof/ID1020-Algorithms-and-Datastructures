/* 
 * README
 * 
 * Author: Larisa Cof
 * 
 * Date: 2020-09-19
 * 
 * Augment the above implementation so that 
 * it prints the number of swaps performed 
 * when sorting the array.
 *________________________________________________________
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
 * Main:
 * User enters array length and elements. 
 * The array is filled and then sorted in ascending order using insertionsort.
 * The algorithm is printed along with all steps and the number of swaps.
 */

package lab2;

import java.util.Scanner;

public class L2A2 {

	public static int count = 0; // int for number of swaps

	// Sort a[] into ascending order (insertion sort algorithm)
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

	// Swap two elements in the array
	private static void exch(Integer[] a, int b, int c)
	{
		Integer t = a[b]; // save initial value
		a[b] = a[c]; // exchange
		a[c] = t; // adjust
		count++; // increase number of swaps
	}
	
	// Print the array
	private static void printarray(Integer[] a) { // Print the array
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	// main

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

		System.out.println();
		System.out.println("Insertion sort (ascending order): ");

		insertionsort(a);

		System.out.println();
		System.out.println("Number of swaps: " + count); // swaps
		System.out.println();
		System.out.println("Sorted in ascending order:"); // final result
		printarray(a);

	}
}

/* EXAMPLE OF EXECUTION

Enter the number of elements you wish to sort: 
6
Enter all the elements you would like to sort: 
1
2
4
3
5
0

Insertion sort (ascending order): 
1 2 3 4 5 0 
1 2 3 4 0 5 
1 2 3 0 4 5 
1 2 0 3 4 5 
1 0 2 3 4 5 
0 1 2 3 4 5 

Number of swaps: 6

Sorted in ascending order:
0 1 2 3 4 5 

*/
