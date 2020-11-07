/*
 * README
 * 
 * Author: Larisa Cof
 * Date: 2020-09-27
 * 
 * FrequencyCounter taken from Algorithms 4th edition with few adjustments. 
 * 
 * Use the first N (N in the 
 * order of hundred words) words from the
 * text to compare the running times of the ordered
 * array ST (algorithm 3.2) to the Binary Search 
 * Tree algorithm (Algorithm 3.3) (you need only 
 * implement the basic operations to put and get keys 
 * to/from the ST) Use the FrequencyCounter from page 372 
 * as test program (you may need to change how you read 
 * the words if you do not use the libraries from Sedgewick&Wayne)..
 * 
 */

package lab3;
import java.util.*;
import java.io.*;

public class LAB3A2BSST {

	public static void main(String[] args) throws FileNotFoundException {

		File file = new File("Inputtext.txt");
		Scanner scanner = new Scanner(file);
		int N = 1000000; // number of words to read from text file

		/*
		 * FREQUENCY COUNTER
		 */
		System.out.println("Enter minimum word length: ");
		Scanner scanlength = new Scanner(System.in);
		int minlen = scanlength.nextInt(); // entered minimum word length
		
		BinarySearchST<String, Integer> ST = new BinarySearchST<String, Integer>();
		
		int i = 0; // makes the algorithm go thorough N number of words from the text file
		
		long startBSST = System.currentTimeMillis();
		//long totalBSST = 0;
		
		while (scanner.hasNext() && i < N) { // Build symbol table and count frequencies.
	
			String word = scanner.next();
			
			//long startBSST = System.currentTimeMillis();
			if (word.length() < minlen) { // Ignore words shorter than minlen
				continue;
			} 
			if (!ST.contains(word)) {
				ST.put(word, 1); // if this is the first time this word occurs, value = 1
				}
			else {
				ST.put(word, ST.get(word) + 1); // if the word already exists, increase its value
				}
			//long endBSST = System.currentTimeMillis();
			//totalBSST += endBSST - startBSST;
			i++;
		}
		
		//System.out.println(totalBSST);
		long endBSST = System.currentTimeMillis();
		long totalBSST = endBSST - startBSST;
		
		
		// Find a key with the highest frequency count.
		String max = "";
		ST.put(max, 0); 
		for (String word : ST.keys()) {
			if (ST.get(word) > ST.get(max)) { // get returns the value associated to each key
				max = word;
			}
		}
		
		/*
		 * END FREQCOUNTER
		 */
		
		System.out.println("The most frequent word is <" + max + "> "
				+ "which appears " + ST.get(max) + " times in 'A Tale of Two Cities'. TimeBSST: " + totalBSST + " ms.");
		
		scanner.close();
		scanlength.close();
	}
}

/*
* EXECUTION:
* 
* Enter minimum word length: 
* 1
* The most frequent word is <the> which appears 7514 times in 'A Tale of Two Cities'. TimeBSST: 512 ms.
*
*/

