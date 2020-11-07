/* 
 * README
 * 
 * Author: Larisa Cof
 * 
 * Date: 2020-09-19
 * 
 * Assignment 1: 
 * Implement insertionsort. Augment the sorting 
 * process so that all the content of the array 
 * that is being sorted is printed after each 
 * inner loop iteration. Write a unit test in main()
 * which allows the user to define the size of the 
 * input (N) and then input (N) integers from stdin 
 * which is to be sorted.
 * ________________________________________________________
 * 
 * insertionsort performs the insertion sort algorithm.
 * 
 * less is a boolean method that compares two integer 
 * values (is less than).
 * 
 * exch swaps two elements position in the 
 * array when the condition in insertionsort is fulfilled (line 46).
 * 
 * printarray prints the entire array.
 * 
 * In main, the user inserts the number of elements 
 * it would like to sort and then inserts one element at a time,
 * which is inserted in the array. The array is then 
 * sorted with the insertion sort algorithm. After every swap made, 
 * the content of the array is shown (line 46).
 */

package lab2;

import java.util.Scanner;

public class L2A1 {

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
		return v.compareTo(w) < 0; // is less than, returns -1 if v is less than w
	}

	// Swap elements
	private static void exch(Integer[] a, int b, int c) // b = j, c = (j-1), compare
	{
		Integer t = a[b]; // save initial value
		a[b] = a[c]; // exchange
		a[c] = t; // adjust

	}

	// Prints the array
	private static void printarray(Integer[] a) { // Print the array
		for (int i = 0; i < a.length; i++) {
			System.out.print("[" + a[i] + "]" + " ");
		}
		System.out.println();
	}

	// main()

	public static void main(String[] args) {

		System.out.println("Enter the number of elements you wish to sort: ");
		Scanner size = new Scanner(System.in); // user enters number of elements
		int length = size.nextInt(); // store the length of the users array
		Integer[] a = new Integer[length]; // create an array with the same number of elements as "length"

		System.out.println("Enter all the elements you would like to sort: ");
		Scanner elements = new Scanner(System.in);

		for (int i = 0; i < length; i++) {
			a[i] = elements.nextInt(); // storing one element at a time from first to last index in the array
		}

		elements.close();
		size.close();

		System.out.println("Insertion sort: ");
		insertionsort(a);
		System.out.println();
		System.out.println("Sorted in ascending order:");
		printarray(a);

	}

}

/* EXAMPLE FROM EXECUTION 

Enter the number of elements you wish to sort: 
6
Enter all the elements you would like to sort: 
1
2
4
3
5
0
Insertion sort: 
[1] [2] [3] [4] [5] [0] 
[1] [2] [3] [4] [0] [5] 
[1] [2] [3] [0] [4] [5] 
[1] [2] [0] [3] [4] [5] 
[1] [0] [2] [3] [4] [5] 
[0] [1] [2] [3] [4] [5] 

Sorted in ascending order:
[0] [1] [2] [3] [4] [5] 

 */
