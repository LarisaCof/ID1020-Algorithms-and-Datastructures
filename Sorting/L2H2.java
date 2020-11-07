/*
 * README
 * Author: Larisa Cof
 * Date: 2020-09-20
 * 
 * Compare the execution times for sorting 
 * large arrays of integers with quicksort 
 * and merge sort. When should one select 
 * quicksort over mergesort?
 * 
 * Mergesort - huge datasets, aux space, worst time complexity is quicksorts best time complexity
 * Quicksort - worst case: sorted
 * 
 * In main:
 * Perform 10 trials and compare execution time of 
 * quicksort vs mergesort.
 * 
 */
package lab2;
import java.util.*;

public class L2H2 {
	
		// QUICKSORT
		public static void quicksort(int[] a) {

			quicksort(a, 0, a.length - 1); // anropar qs med första elementet (0 = lo) och sista elementet = hi.
		}

		private static void quicksort(int[] a, int lo, int hi) {
			if (hi <= lo)
				return;
			int j = partition(a, lo, hi); // går igenom array
			quicksort(a, lo, j - 1); // Sortera vänstra delen a[lo .. j-1]
			quicksort(a, j + 1, hi); // Sortera högra delen a[j+1 .. hi].
		}

		private static int partition(int[] a, int lo, int hi) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
			int i = lo, j = hi + 1; // vänster och höger variabler
			int v = a[lo]; // "pivot" elementet, referens
			while (true) { // Scan right, scan left, check for scan complete, and exchange.
				while (less(a[++i], v))
					if (i == hi)
						break;
				while (less(v, a[--j]))
					if (j == lo)
						break;
				if (i >= j)
					break;
				exch(a, i, j);
			}
			exch(a, lo, j);
			return j;
		}

		private static boolean less(int v, int w) {
			return v < w;
		}

		private static void exch(int[] a, int i, int j) {
			int t = a[i];
			a[i] = a[j];
			a[j] = t;
		}

		// MERGESORT
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

		public static void main(String[] args) {
			System.out.println("Enter length: ");
			Scanner inlength = new Scanner(System.in);
			int length = inlength.nextInt();
			int[] array1 = new int[length];
			int[] array2 = new int[length];

			for (int i = 0; i < length; i++) {
				array1[i] = array2[i] = (int) (Math.random() * 1000); // hämtar math.random för att fylla arrayen med slumpade tal
			}

			int trials = 10; // vill göra 10 försök
			
			// lagrar tid för varje trial i en array
			long qTime[] = new long[trials]; // quicksort tider
			long mTime[] = new long[trials]; // mergesort tider

			int j = 0; // antal trials
			for (j = 0; j < trials; j++) {
				System.out.println();
				System.out.println("Trial: " + (j + 1));
				qTime[j] = System.nanoTime(); // gives you time in nanosec, relative to some arbitrary point.
				quicksort(array1);
				qTime[j] = System.nanoTime() - qTime[j]; // endtime - startime to get precise time for quicksort
				System.out.println("Execution time for quicksort: " + qTime[j] + "ns");
				
				mTime[j] = System.nanoTime();
				mergesort(array2);
				mTime[j] = System.nanoTime() - mTime[j]; // endtime - startime to get precise time for mergesort
				System.out.println("Execution time for mergesort: " + mTime[j] + "ns");

			}
			
			inlength.close();

			for (j = 0; j < trials; j++) {
				System.out.println();
				if (qTime[j] < mTime[j]) {

					System.out.println(
							"Trial " + (j + 1) + ":" + " Quicksort was " + (mTime[j] - qTime[j]) + "ns faster than mergesort");
				}
				if (mTime[j] < qTime[j]) {
					System.out.println(
							"Trial " + (j + 1) + ":" + " Mergesort was " + (qTime[j] - mTime[j]) + "ns faster than quicksort");
				}
			}

			long aQ = 0;
			long aM = 0;
			for (int m = 0; m < trials; m++) {
				aQ = aQ + qTime[m];
				aM = aM + mTime[m];
			}

			 long averageQ = aQ / trials;
			 long averageM = aM / trials;

			System.out.println("Average time for Quicksort: " + (averageQ) + "ns");
			System.out.println("Average time for Mergesort: " + (averageM) + "ns");
			
			if (averageQ < averageM) {
				System.out.println("Quicksort was in average " + (averageM - averageQ) + "ns faster than mergesort");
			}
			else {
				System.out.println("Mergesort was in average " + (averageQ - averageM) + "ns faster than quicksort");
				
			}

		}
}
