package lab3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * README
 * Author: Larisa Cof
 * Date: 2020-09-28
 * 
 * Implement a program which takes as input 
 * a text file and allows the user to (repeatedly 
 * without re-reading the input file) ask questions: 
 * i) Which is the k:th most common word
 * ii) Which are the k:th to the k+n:th most common words
 * Use https://introcs.cs.princeton.edu/java/data/leipzig/leipzig1m.txt  
 * as input. The questions should be answered in 
 * constant time (assume k and n << N). You need
 * to be able to explain the choices of data 
 * structures and algorithms, and their complexities.
 * The time to read the input and build the 
 * data structures must not be longer than 4 
 * min (this goal is easy to meet on all 
 * relatively modern PCs if one uses efficient 
 * algorithms and data structures)
 */

public class LAB3Higher {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("higher.txt");
		Scanner scanner = new Scanner(file);

		System.out.println("Enter minimum word length: ");
		Scanner scanlength = new Scanner(System.in);
		int minlen = scanlength.nextInt(); // entered minimum word length

		BST<String, Integer> ST = new BST<String, Integer>();

		long totalBST = 0;
		while (scanner.hasNext()) { // Build symbol table and count frequencies.

			String word1 = scanner.next();
			String word = word1.toLowerCase();
			
			long startBST = System.currentTimeMillis();
			if (word.length() < minlen) { // Ignore words shorter than minlen
				continue;
			}
			if (!ST.contains(word)) {
				ST.put(word, 1); // if this is the first time this word occurs, value = 1
			} else {
				ST.put(word, ST.get(word) + 1); // if the word already exists, increase its value
			}
			long endBST = System.currentTimeMillis();
			totalBST += endBST - startBST;
		}

		// Find a key with the highest frequency count.
		String max = "";
		ST.put(max, 0);
		for (String word : ST.keys()) {
			if (ST.get(word) > ST.get(max)) {
				max = word;
			}
		}

		System.out.println("The most frequent word is <" + max + "> " + "which appears " + ST.get(max)
				+ " times in the text. TimeBST: " + totalBST + " ms.");

		// User enters interval
		System.out.println();
		System.out.println("Enter N for the Nth most common word: ");
		Scanner sc1 = new Scanner(System.in);
		int n = sc1.nextInt();
		System.out.println("Enter interval (from N:th to (N+X):th: ");
		Scanner sc2 = new Scanner(System.in);
		int x = sc2.nextInt();

		int q = 0;
		// Create an array containing all keys
		String[] array = new String[ST.size()];
		for (String word : ST.keys()) {
			array[q] = word;
			q++;
		}

		mergesort(array, ST); // modified to get values from the ST and 
		
		// TESTING
		//for(int tot = 0; tot < 10; tot++)
			//if(ST.get(array[tot]) > 1000)
			//System.out.println("The word < " + array[tot] + " > occured " + ST.get(array[tot]) + " times.");
		
		
		// Prints out the nth to the xth most common words
		for (int k = (n - 1); k < x; k++) {
			System.out.println("The " + (k + 1) + " most common word is <" + array[k] + ">  which appeared "
					+ ST.get(array[k]) + " times.");
		}

		sc1.close();
		sc2.close();
		scanner.close();
		scanlength.close();

	}

	// MERGE SORT
	private static String[] aux;
	{ // auxiliary array for merges
	}

	public static void mergesort(String[] a, BST<String, Integer> ST) {
		aux = new String[a.length]; // Allokera utrymme ENDAST EN gång.
		mergesort(a, 0, a.length - 1, ST);
	}

	private static void mergesort(String[] a, int lo, int hi, BST<String, Integer> ST) { // Sortera a[lo..hi].
		if (hi <= lo)
			return;
		int mid = lo + (hi - lo) / 2;
		mergesort(a, lo, mid, ST); // Sortera vänstra halvan.
		mergesort(a, mid + 1, hi, ST); // Sortera högra halvan.
		merge(a, lo, mid, hi, ST); // Merge results (code on page 271).

	}

	public static void merge(String[] a, int lo, int mid, int hi, BST<String, Integer> ST) { // Merge a[lo..mid] with
																								// a[mid+1..hi].
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++) // Kopiera a[lo..hi] till aux[lo..hi].
			aux[k] = a[k];
		for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
			if (i > mid)
				a[k] = aux[j++];
			else if (j > hi)
				a[k] = aux[i++];
			else if (less(ST.get(aux[j]), ST.get(aux[i])))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];
	}

	static boolean less(Integer v, Integer w) { // jämför VALUE för varje KEY. Vi vill sortera i fallande ordning.
		return v.compareTo(w) > 0; //
	}

}

/*
 * EXECUTION:
 * 
Enter minimum word length: 
1
The most frequent word is <the> which appears 20078 times in the text. TimeBST: 252 ms.

Enter N for the Nth most common word: 
1
Enter interval (from N:th to (N+X):th: 
10
The 1 most common word is <the>  which appeared 20078 times.
The 2 most common word is <of>  which appeared 8787 times.
The 3 most common word is <to>  which appeared 8279 times.
The 4 most common word is <a>  which appeared 7515 times.
The 5 most common word is <in>  which appeared 6996 times.
The 6 most common word is <and>  which appeared 6595 times.
The 7 most common word is <for>  which appeared 3182 times.
The 8 most common word is <that>  which appeared 2982 times.
The 9 most common word is <is>  which appeared 2540 times.
The 10 most common word is <on>  which appeared 2270 times.
 * 
 */
