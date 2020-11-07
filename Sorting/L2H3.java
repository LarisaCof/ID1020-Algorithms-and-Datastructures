/*
 * README
 * Author: Larisa Cof
 * Date: 2020-09-20
 * 
 * Compare the execution times of 
 * quicksort where the first element 
 * in each sub-array is selected as 
 * partitioning element to that of quicksort 
 * with median-of-three partitioning
 * 
 * In main:
 * Fill array with random integers, 0-99.
 * Measure time for quicksort vs quicksort MO3.
 * Print results.s
 */

package lab2;
import java.util.Scanner;

public class L2H3 {

	// QUICKSORT MO3
	
	public static void quicksortMedianOfThree(int[] a) {

		quicksortM(a, 0, a.length - 1); 
	}

	private static void quicksortM(int[] a, int left, int right) {
		if (right <= left) // "cursors have passed each other"
			return;

		int pivot = medianOfThree(a, left, right); // MO3
		int j = partition(a, left, right, pivot); 
		quicksortM(a, 0, j - 1); // Sort left part a[lo .. j-1]
		quicksortM(a, j + 1, right); // Sort right part a[j+1 .. hi].

	}

	private static int partition(int[] a, int left, int right, int pivot) {
		int leftCursor = left-1; // ta med första
		int rightCursor = right; // pivot

		while (less(leftCursor, rightCursor)) { 
			while (less(a[++leftCursor], pivot));
			while (less(0, rightCursor) && less(pivot, a[--rightCursor]));
			if (leftCursor >= rightCursor) {
				break;
			} else {
				exch(a, leftCursor, rightCursor);
			}
		}
		exch(a, leftCursor, right);
		return leftCursor;
	}

	public static int medianOfThree(int[] a, int left, int right) {
		int mid = (left + right) / 2;

		if (less(a[mid], a[left])) // put mid and left in order
			exch(a, left, mid);
		
		if (less(a[right], a[left])) // put right and left in order
			exch(a, left, right);

		if (less(a[right], a[mid])) // put right and mid in order
			exch(a, mid, right);

		exch(a, mid, right); // put pivot element to the right of the array
		return a[right]; // return the pivot element
	}

	private static boolean less(int v, int w) {
		return v < w;
	}

	private static void exch(int[] a, int left, int right) {
		int t = a[left];
		a[left] = a[right];
		a[right] = t;
	}

	private static void show(int[] a) { // Print the array
		for (int i = 0; i < a.length; i++) {
			System.out.print("[" + a[i] + "]" + " ");
		}
		System.out.println();
	}
	
	// QUICKSORT, PARTITIONING ELEMENT AT FIRST POS
	
	public static void quicksort(int[] a) {

		quicksort(a, 0, a.length - 1); 
	}

	private static void quicksort(int[] a, int left, int right) {
		if (right <= left)
			return;
		int j = partition(a, left, right); // pivot element is left
		quicksort(a, left, j - 1); // Sort left part vänstra delen a[lo .. j-1]
		quicksort(a, j + 1, right); // Sort right part a[j+1 .. hi].
	}

	private static int partition(int[] a, int left, int right) { // Partition into a[lo..i-1], a[i], a[i+1..hi].
		int leftCursor = left; // pivot
		int rightCursor = right+1; // få med sista elementet
		int pivot = a[left]; // "pivot" element
		while (true) { // Scan right, scan left, check for scan complete, and exchange.
			while (less(a[++leftCursor], pivot))
				if (leftCursor == right)
					break;
			while (less(pivot, a[--rightCursor]))
				if (rightCursor == left)
					break;
			if (leftCursor >= rightCursor)
				break;
			exch(a, leftCursor, rightCursor);
		}
		exch(a, left, rightCursor);
		return rightCursor;
	}	

	public static void main(String[] args) {

		System.out.println("Enter length: ");
		Scanner inlength = new Scanner(System.in);
		Scanner scanner = new Scanner(System.in);
		int length = inlength.nextInt();
		int[] array1 = new int[length];
		int[] array2 = new int[length];

//		for (int i = 0; i < length; i++) {
//			array1[i] = array2[i] = (int) (Math.random() * 100); // fill array with random numbers from 0-99
//		}
		
		 //Test for worst case
		for (int i = 0; i < length; i++) {
			array1[i] = array2[i] = i;
		}

		System.out.println();
		System.out.println("Initial array: ");
		show(array1);
		
		long timeQMO3 = System.nanoTime();
		quicksortMedianOfThree(array1);
		timeQMO3 = System.nanoTime() - timeQMO3;
		
		System.out.println();
		System.out.println("Sorted with quicksort MO3: ");
		show(array1);
		
		long timeQ = System.nanoTime();
		quicksort(array2);
		timeQ = System.nanoTime() - timeQ;
		
		System.out.println();
		System.out.println("Sorted with quicksort: ");
		show(array2);
		
		System.out.println("Time for QS: " + timeQ);
		System.out.println("Time for QMO3: " + timeQMO3);
		
		if (timeQ < timeQMO3) { 
			long diff1 = (timeQMO3-timeQ);
			System.out.println("Quicksort was " + diff1 + " ns faster than QMO3"); 
			}
		if (timeQMO3 < timeQ) { 
			long diff2 = timeQ-timeQMO3;
			System.out.println("QMO3 was " + diff2 + " ns faster than quicksort"); 
			}
		
		inlength.close();
	}
}

/*

Enter length: 
70

Initial array: 
[0] [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12] [13] [14] [15] [16] [17] [18] [19] [20] [21] [22] [23] [24] [25] [26] [27] [28] [29] [30] [31] [32] [33] [34] [35] [36] [37] [38] [39] [40] [41] [42] [43] [44] [45] [46] [47] [48] [49] [50] [51] [52] [53] [54] [55] [56] [57] [58] [59] [60] [61] [62] [63] [64] [65] [66] [67] [68] [69] 

Sorted with quicksort MO3: 
[0] [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12] [13] [14] [15] [16] [17] [18] [19] [20] [21] [22] [23] [24] [25] [26] [27] [28] [29] [30] [31] [32] [33] [34] [35] [36] [37] [38] [39] [40] [41] [42] [43] [44] [45] [46] [47] [48] [49] [50] [51] [52] [53] [54] [55] [56] [57] [58] [59] [60] [61] [62] [63] [64] [65] [66] [67] [68] [69] 

Sorted with quicksort: 
[0] [1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12] [13] [14] [15] [16] [17] [18] [19] [20] [21] [22] [23] [24] [25] [26] [27] [28] [29] [30] [31] [32] [33] [34] [35] [36] [37] [38] [39] [40] [41] [42] [43] [44] [45] [46] [47] [48] [49] [50] [51] [52] [53] [54] [55] [56] [57] [58] [59] [60] [61] [62] [63] [64] [65] [66] [67] [68] [69] 
Time for QS: 71795
Time for QMO3: 93202829287
Quicksort was 93202757492 ns faster than QMO3

 */
