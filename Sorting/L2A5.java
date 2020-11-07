/*
 * README
 * 
 * Author: Larisa Cof
 * Date: 2020-09-19
 * 
 * When should one select mergesort over insertionsort?
 * Merge sort should be selected when working with 
 * huge datasets. It has a better time complexity 
 * for average and worst case than insertion sort.
 * OBS! Insertion sort better memory complexity (merge uses aux array O(N)). 
 * 
 * 
 * Compare the execution times for sorting large 
 * arrays of integers with insertionsort and merge sort. 
 * When should one select mergesort over insertionsort? 
 * Upload code, tests and a graphs depicting the 
 * execution times as a function of input (what parameters 
 * in the input could be relevant?).
 * 
 * Main:
 * User enters wanted number of elements.
 * The array is filled with random numbers from 0-99.
 * Ten trials are performed where the execution time 
 * for each algorithm (insertion sort and merge sort) 
 * is measured in nanoseconds.
 * The execution times are compared for each trial.
 * The average time is calculated for each sorting algorithm
 * and the algorithm with the fastest average time is displayed.
 *
 */

package lab2;

import java.util.*;

public class L2A5 {

	// INSERTION SORT
	public static void insertionsort(int[] array) {

		int N = array.length; // number of elements in the array

		// Go through all elements in the array
		for (int i = 1; i < N; i++) {

			// Enter this loop for all j's that fulfill the conditions for this i
			for (int j = i; j > 0 && less(array[j], array[j - 1]); j--) {

				// for all a[j] < a[j-1] swap places in the array
				exch(array, j, j - 1);

			}
		}
	}

	// Is less than
	private static boolean less(Integer v, Integer w) {
		return v.compareTo(w) < 0; // IS LESS THAN, returns -1 if v is less than w
	}

	// Swap two elements in the array
	private static void exch(int[] array, int b, int c) // b = j, c = (j-1), compare
	{
		Integer t = array[b]; // save initial value
		array[b] = array[c]; // exchange
		array[c] = t; // adjust
	}

	// MERGE SORT
	private static int[] aux;
	{ // auxiliary array for merges
	}

	public static void mergesort(int[] a) {
		aux = new int[a.length]; // Allokera utrymme ENDAST EN gång.
		mergesort(a, 0, a.length - 1);
	}

	private static void mergesort(int[] a, int lo, int hi) { // Sortera a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		mergesort(a, lo, mid); // Sortera vänstra halvan.
		mergesort(a, mid + 1, hi); // Sortera högra halvan.
		merge(a, lo, mid, hi); // Merge results (code on page 271). 
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

	public static void main(String[] args) {
		System.out.println("Enter length: ");
		Scanner inlength = new Scanner(System.in);
		int length = inlength.nextInt();
		int[] array = new int[length];

		for (int i = 0; i < length; i++) {
			array[i] = (int) (Math.random() * 100); // hämtar math.random för att fylla arrayen med slumpade tal
		}

		int trials = 10; // vill göra 10 försök

		// lagrar tid för varje trial i en array
		long iTime[] = new long[trials]; // insertionsort tider
		long mTime[] = new long[trials]; // mergesort tider

		int j = 0; // antal trials
		for (j = 0; j < trials; j++) {
			System.out.println();
			
			System.out.println("Trial: " + (j + 1));
			
			iTime[j] = System.nanoTime(); // gives you time in nanosec, relative to some arbitrary point.
			insertionsort(array);
			iTime[j] = System.nanoTime() - iTime[j]; // endtime - startime to get precise time for quicksort
			System.out.println("Execution time for insertionsort: " + iTime[j] + "ns");
			
			mTime[j] = System.nanoTime();
			mergesort(array);
			mTime[j] = System.nanoTime() - mTime[j]; // endtime - startime to get precise time for mergesort
			System.out.println("Execution time for mergesort: " + mTime[j] + "ns");

		}

		inlength.close();

		for (j = 0; j < trials; j++) {
			System.out.println();
			if (iTime[j] < mTime[j]) {

				System.out.println("Trial " + (j + 1) + ":" + " Insertionsort was " + (mTime[j] - iTime[j])
						+ "ns faster than mergesort");
			} else if (mTime[j] < iTime[j]) {
				System.out.println("Trial " + (j + 1) + ":" + " Mergesort was " + (iTime[j] - mTime[j])
						+ "ns faster than insertionsort");
			}
		}

		long aI = 0; 
		long aM = 0; 
		for (int m = 0; m < trials; m++) {
			aI = aI + iTime[m];
			aM = aM + mTime[m];
		}

		long averageI = aI / trials; // average for insertion sort
		long averageM = aM / trials; // average for merge sort

		System.out.println("Average time for insertion sort: " + (averageI) + "ns");
		System.out.println("Average time for merge sort: " + (averageM) + "ns");

		if (averageI < averageM) {
			System.out.println("Insertion sort was in average " + (averageM - averageI) + "ns faster than merge sort");
		} else {
			System.out.println("Merge sort was in average " + (averageI - averageM) + "ns faster than insertion sort");

		}
	}
}

/* EXAMPLE EXECUTION

Enter length: 
50

Trial: 1
Execution time for insertionsort: 687817ns
Execution time for mergesort: 136904ns

Trial: 2
Execution time for insertionsort: 20760ns
Execution time for mergesort: 74947ns

Trial: 3
Execution time for insertionsort: 8045ns
Execution time for mergesort: 63124ns

Trial: 4
Execution time for insertionsort: 7150ns
Execution time for mergesort: 62687ns

Trial: 5
Execution time for insertionsort: 7760ns
Execution time for mergesort: 62643ns

Trial: 6
Execution time for insertionsort: 8132ns
Execution time for mergesort: 698034ns

Trial: 7
Execution time for insertionsort: 7739ns
Execution time for mergesort: 54901ns

Trial: 8
Execution time for insertionsort: 7438ns
Execution time for mergesort: 60330ns

Trial: 9
Execution time for insertionsort: 13430ns
Execution time for mergesort: 65319ns

Trial: 10
Execution time for insertionsort: 7299ns
Execution time for mergesort: 81173ns

Trial 1: Mergesort was 550913ns faster than insertionsort

Trial 2: Insertionsort was 54187ns faster than mergesort

Trial 3: Insertionsort was 55079ns faster than mergesort

Trial 4: Insertionsort was 55537ns faster than mergesort

Trial 5: Insertionsort was 54883ns faster than mergesort

Trial 6: Insertionsort was 689902ns faster than mergesort

Trial 7: Insertionsort was 47162ns faster than mergesort

Trial 8: Insertionsort was 52892ns faster than mergesort

Trial 9: Insertionsort was 51889ns faster than mergesort

Trial 10: Insertionsort was 73874ns faster than mergesort
Average time for insertion sort: 77557ns
Average time for merge sort: 136006ns
Insertion sort was in average 58449ns faster than merge sort

 */
