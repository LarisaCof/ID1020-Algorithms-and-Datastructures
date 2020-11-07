/*
 * README
 * 
 * Author: Larisa Cof
 * Date: 2020-09-19
 * 
 * Experiment with the cut-off to insertionsort in merge. 
 * How is the execution time affected by different values 
 * for the cut-off? A suitable range for cut-off values to 
 * test with could be [0-30]. Upload code, tests and a graphs.
 * 
 * Main:
 * The user enters cut-off value and number of elements it wishes to sort.
 * The procedure using mergesort and then insertionsort for the cut-off value
 * is timed in nanoseconds.
 * The initial array and the sorted array are displayed along with the execution time.
 */

package lab2;
import java.util.*;

public class L2A6 {

		// if the cut off value is smaller than the array, 
		// use mergesort. 
		// Otherwise use insertion sort.
		
		public static void sort(int[] a, int cutoff) {
			if (a.length > cutoff) {
				mergesort(a, 0, a.length - 1, cutoff);
			} else {
				insertionsort(a, 1, a.length - 1);
			}

		}

		public static void insertionsort(int[] a, int lo, int hi) {
			int N = hi + 1;

			// traverse through the array from left to right.
			// The elements to the left of i will be sorted, and the array will
			// be completely sorted when i reaches N-1.
			for (int i = lo; i < N; i++) {
				
				// if a[j] < a[j-1] and j > 0 enter the loop
				for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
					exch(a, j, j - 1); // if a[j] < a[j-1] exchange a[j] and a[j-1]

				}
			}
		}

		// Swaps two elements in the array
		private static void exch(int[] a, int b, int c) {
			int t = a[b];
			a[b] = a[c]; // a[j] = a[j-1]
			a[c] = t; // a[j-1] = a[j]
		}

		// Is less than
		private static boolean less(int v, int w) {
			return v < w; // if v is less than w return true
		}
		
		// Print the array
		private static void arrayprint(int[] a) // print out the array
		{
			for (int count = 0; count < a.length; count++) {
				System.out.print("[" + a[count] + "] ");
			}
			System.out.println(" ");
		}
		
		// MERGE SORT
		
		private static int[] aux;
		{ // auxiliary array for merges
		}

		public static void mergesort(int[] a, int lo, int hi, int cutoff) {
			aux = new int[a.length]; // Allokera utrymme ENDAST EN gång.
			if (hi <= lo + cutoff) {
				insertionsort(a, lo, hi);
				return;
			}
			mergesort(a, 0, a.length - 1);
		}

		private static void mergesort(int[] a, int lo, int hi) { // Sortera a[lo..hi].
			if (hi <= lo)
				return;
			int mid = lo + (hi - lo) / 2;
			mergesort(a, lo, mid); // Sortera vänstra halvan.
			mergesort(a, mid + 1, hi); // Sortera högra halvan.
			merge(a, lo, mid, hi); // Merge results (code on page 271). } }
		}

		public static void merge(int[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
			int i = lo, j = mid + 1;
			for (int k = lo; k <= hi; k++) // Kopiera a[lo..hi] till aux[lo..hi].
				aux[k] = a[k];
			for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
				if (i > mid)
					a[k] = aux[j++];
				else if (j > hi)
					a[k] = aux[i++];
				else if (less(aux[j], aux[i]))
					a[k] = aux[j++];
				else
					a[k] = aux[i++];
		}

		// user inserts cut-off value and then size of array. 
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter value for the cut-off:");
			int cutoff = scanner.nextInt();
			System.out.println("How large do you want the comparative array to be?");
			int size = scanner.nextInt();
			int count = 0;
			int[] array = new int[size];
			
			// fill array with random integers from 0-99
			while (count < size) {
				array[count] = (int) (Math.random() * 100);
				count++;
			}

			System.out.println("Initial array: ");
			arrayprint(array);
			
			// Measure the time for this implementation (nanoseconds)
			long sortTimeStart = System.nanoTime();
			sort(array, cutoff);
			long sortTimeEnd = System.nanoTime() - sortTimeStart;
			
			System.out.println();
			System.out.println("Sorted array: ");
			arrayprint(array);
			System.out.println();
			System.out.println("Execution time: " + sortTimeEnd + " nanoseconds");

		}
}

/* EXAMPLE EXECUTION

Enter value for the cut-off:
10
How large do you want the comparative array to be?
75
Initial array: 
[11] [47] [96] [21] [96] [94] [20] [46] [70] [37] [46] [32] [77] [88] [68] [67] [90] [66] [25] [71] [68] [15] [1] [78] [25] [7] [90] [62] [0] [6] [98] [34] [27] [34] [62] [88] [78] [10] [62] [50] [27] [81] [73] [92] [63] [35] [90] [87] [9] [44] [16] [90] [92] [55] [71] [95] [99] [28] [95] [66] [29] [44] [7] [15] [35] [96] [38] [97] [78] [49] [56] [86] [12] [69] [26]  

Sorted array: 
[0] [1] [6] [7] [7] [9] [10] [11] [12] [15] [15] [16] [20] [21] [25] [25] [26] [27] [27] [28] [29] [32] [34] [34] [35] [35] [37] [38] [44] [44] [46] [46] [47] [49] [50] [55] [56] [62] [62] [62] [63] [66] [66] [67] [68] [68] [69] [70] [71] [71] [73] [77] [78] [78] [78] [81] [86] [87] [88] [88] [90] [90] [90] [90] [92] [92] [94] [95] [95] [96] [96] [96] [97] [98] [99]  

Execution time: 144603 nanoseconds

 */
