/* 
 * README
 * 
 * Author: Larisa Cof
 * 
 * Date: 2020-09-19
 * 
 * Augment the test code so that the array is sorted in descending order 
 * instead of ascending order (you may add O(N) operations)
 * Clarification: You should not change the sorting method, 
 * nor should you sort the array an extra time. You may traverse 
 * the array once before sorting and once after sorting. During 
 * these traversals you may not move any elements.
 * 
 * In main: 
 * First traverse through the array once 
 * and turn all elements to negative. Then the array is sorted 
 * with insertion sort, which then will show the algorithm in 
 * descending order (ascending order in terms of the negative numbers). 
 * Lastly, I traverse through the array a second time,
 * multiplying every element with -1 in order to switch 
 * back to the initially inserted values.
 * 
 */
package lab2;
import java.util.Scanner;

public class L2H1 {
		
		// Sort a[] into increasing order
		public static void sort(Integer[] a) {

			int N = a.length; // number of elements in the array

			// Go through all elements in the array
			for (int i = 1; i < N; i++) {

				// Enter this loop for all j's that fulfill the conditions for this i
				for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {

					// for all a[j] < a[j-1] swap places in the array
					exch(a, j, j - 1);

					// print content of the array after every swap
					show(a);
				}

			}
		}

		private static boolean less(Integer v, Integer w) {
			return v.compareTo(w) < 0;
		}

		// exch(),
		private static void exch(Integer[] a, int b, int c) // b = j, c = (j-1), compare
		{
			Integer t = a[b]; // save initial value
			a[b] = a[c]; // exchange
			a[c] = t; // adjust
		}

		private static void show(Integer[] a) { // Print the array
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}

		// main().

		public static void main(String[] args) { 

			System.out.println("Enter the number of elements you wish to sort: ");
			Scanner size = new Scanner(System.in); // user enters number of elements
			int length = size.nextInt(); // store the length of users array in an int
			Integer[] a = new Integer[length]; // create array with the same number of elements as length

			System.out.println("Enter all the elements you would like to sort: ");
			Scanner elements = new Scanner(System.in);
		
			for (int i = 0; i < length; i++) {
				a[i] = elements.nextInt();
				a[i] = 0 - a[i]; // turn all elements negative

			}

			elements.close();
			size.close();
			
			System.out.println();
			System.out.println("Insertion sort (descending order): ");

			sort(a);

			for (int i = 0; i < length; i++) {

					a[i] = -1 * a[i]; // turn all elements back to initial values.
				
			}

			System.out.println();
			System.out.println("Sorted in descending order: ");
			show(a);

		}
}
