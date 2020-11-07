/*
 * README
 * Author: Larisa Cof
 * Date: 2020-09-27
 * 
 * Assignment:
 * Use the first N (N in the order of hundred words)
 * words from the text to compare the running times 
 * of the ordered array ST (algorithm 3.2) to the Binary 
 * Search Tree algorithm (Algorithm 3.3) (you need only implement
 * the basic operations to put and get keys to/from the ST) Use
 * the FrequencyCounter from page 372 as test program (you may need 
 * to change how you read the words if you do not use the
 * libraries from Sedgewick&Wayne)..
 */

package lab3;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LAB3A2BST {
	
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
		
		BST<String, Integer> ST = new BST<String, Integer>();
		
		int i = 0; // makes the algorithm go thorough N number of words from the text file
	
		//long startBST = System.currentTimeMillis();
		long totalBST = 0;
		
		while (scanner.hasNext() && i < N) { // Build symbol table and count frequencies.
	
			String word = scanner.next();
			long startBST = System.currentTimeMillis();
			if (word.length() < minlen) { // Ignore words shorter than minlen
				continue;
			} 
			if (!ST.contains(word)) {
				ST.put(word, 1); // if this is the first time this word occurs, value = 1
				}
			else {
				ST.put(word, ST.get(word) + 1); // if the word already exists, increase its value
				}
			long endBST = System.currentTimeMillis();
			totalBST += endBST - startBST;
			i++;
		}
		
		System.out.println(totalBST);
		//long endBST = System.currentTimeMillis();
		//long totalBST = endBST - startBST;
		
		// Find a key with the highest frequency count.
		String max = "";
		ST.put(max, 0); 
		for (String word : ST.keys()) {
			if (ST.get(word) > ST.get(max)) {
				max = word;
			}
		}
		
		/*
		 * END FREQCOUNTER
		 */
	
		System.out.println("The most frequent word is <" + max + "> "
				+ "which appears " + ST.get(max) + " times in 'A Tale of Two Cities'. TimeBST: " + totalBST + " ms.");
		
		scanner.close();
		scanlength.close();
	}

}

/*
 * Enter minimum word length: 
 * 1
 * The most frequent word is <the> which appears 7514 times in 'A Tale of Two Cities'. TimeBST: 135 ms.
 */
