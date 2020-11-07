/*
 * README
 * Author: Larisa Cof
 * Date: 2020-09-27
 * 
 * Assignment:
 * Write an "index"-program which allows
 * the user to ask the question "on which 
 * positions in the text (i.e. the number of 
 * characters from the beginning) you find the word X". 
 * The program should list the position of all 
 * occurrences of X as answer to the query. In 
 * this assignment you may use the Java library 
 * (built-in) lists.
 * 
 */

package lab3;
import java.util.Scanner;
import java.io.*;

public class LAB3A4LATEST {

		public static void main(String[] args) throws FileNotFoundException, IOException
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the word you want to search for: ");
			String wordinput1 = sc.nextLine(); // the word we want to search for in the text
			
			String wordinput = wordinput1.toLowerCase();
			// Reads data from the file word by word.
			FileReader file = new FileReader("Inputtext.txt");

			// with bufferedreader we can read one line at a time with readLine()
			BufferedReader bufr = new BufferedReader(file);

			String line;
			int current = 0;
			int linenr = 1;
			
			while ((line = bufr.readLine()) != null)  // go through all lines
			{
				String lineToLower = line.toLowerCase();
				//System.out.println(lineToLower);
				int wordindex = lineToLower.indexOf(wordinput);// take the index for the searched word
				char[] array = lineToLower.toCharArray(); // put all characters on the line into a char array
				
				while(wordindex > -1) // If the word is on this line (if the word does not exist on this line wordindex = -1). 
				{
					if(wordindex == 0) // if searched word is on index 0
					{
						int totalindex = current + wordindex;   // total index
						System.out.println("The word < " + wordinput1 + " > was found at index " + totalindex + ", line " + linenr);   
						wordindex++;
	
					}
					// if char before is not a letter and if it is the last word on the line
					if(!(Character.isLetter(array[wordindex - 1])) && ((wordindex + wordinput.length()) >= line.length())) 
					{
						int totalindex = current + wordindex;   // total index
						System.out.println("The word < " + wordinput1 + " > was found at index " + totalindex + ", line " + linenr); 
				
					}
					// if char before is not a letter and if there is not a char on next index/no character after the searched words length
					else if(!(Character.isLetter(array[wordindex - 1])) && !(Character.isLetter(array[wordindex + wordinput.length()])))
					{
						int totalindex = current + wordindex;   // total index
						System.out.println("The word < " + wordinput1 + " > was found at index " + totalindex + ", line " + linenr);  
				
					}
					
					wordindex = line.indexOf(wordinput, wordindex + 1); // see if there are more of this word on this line
				}
				
				current += line.length(); 
				linenr++;
				
				
				}	
			
			sc.close();
			bufr.close();
			
	        }
		}

/*
 * EXAMPLE EXECUTION 1: Finding the word multiple times on the same line
 * 
Enter the word you want to search for: 
with
The word < with > was found at index 131,line 3
The word < with > was found at index 270, line 6
The word < with > was found at index 3048, line 132			X
The word < with > was found at index 3077, line 132			X
The word < with > was found at index 3139, line 133			X
The word < with > was found at index 3168,line 133			X
The word < with > was found at index 4366, line 154
*
* EXAMPLE EXECUTION 2: Finding all occurrences of a word, for example "Volunteers" and "volunteers".
* 
Enter the word you want to search for: 
Volunteers
The word < Volunteers > was found at index 301952,line 6544
The word < Volunteers > was found at index 752093, line 16107
The word < Volunteers > was found at index 755254, line 16161
The word < Volunteers > was found at index 756006, line 16175
The word < Volunteers > was found at index 756063, line 16178		X 
The word < Volunteers > was found at index 756107, line 16178		X
The word < Volunteers > was found at index 757334, line 16202

Line 16178: Volunteers and financial support to provide volunteers with the
 */
