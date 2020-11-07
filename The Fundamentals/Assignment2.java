/*
 * README
 * 
 * Author: Larisa Cof
 * In JAVA implement a recursive and an iterative version of a 
 * function which reads characters from stdin until a newline 
 * character is read and then prints them on stdout in reverse order. 
 * By using the "pop" method, we add an element on top of the stack. 
 * When we later use "pop" we remove the element from the stack in LIFO order. 
 * Lastly we print all element in reverse since the last item is the first one popped. 
 */

package lab1;
import java.util.Scanner;

public class Assignment2 <Item>  {
	private Item[] a = (Item[]) new Object[1]; 			// stack items
	private int N = 0; 									// number of items

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	private void resize(int max) { 						// Move stack to a new array of size max if stack is full.
		Item[] temp = (Item[]) new Object[max]; 		// när metoden anropas, bildas en array av storlek max som anges vid
														// anropet
		for (int i = 0; i < N; i++)
			temp[i] = a[i]; 							// kopierar stackelementen till vår nya array temp
		a = temp; 										// måste ha referens till temp eftersom den kan variera i storlek (kan ej a)
	}

	public void push(Item item) { 						// Add item to top of stack.
		if (N == a.length)
			resize(2 * a.length); 						// om antalet stackelement = a, anropa resize och fördubbla storleken (max =
														// 2a)
		a[N++] = item; 									// nästa plats fylls med nästa element, vi "pushar"
	}

	public Item pop() { 								// Remove item from top of stack.
		Item item = a[--N]; 							// går nedåt i stacken från toppen
		a[N] = null; 									
														// Vi skriver över den angivna platsen i minnet till null (pop) så att vi kan
														// återanvända det minnet
		if (N > 0 && N == a.length / 4)
			resize(a.length / 2); 						// om antalet element i stacken är större än noll och är en kvarts andel av a,
														// anropa resize, sparar minne 
		return item;
	}


	// ITERATIVE
	
	public static void main(String[] args) {

		Assignment2<Character> stack = new Assignment2<Character>(); // skapar en stack, interface implementation
		System.out.println("Enter input: ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int length = s.length(); 						// antalet karaktärer i string s

		for (int i = 0; i < length; i++) {
			stack.push(s.charAt(i)); 					// lägg till element i stacken i ordning från strängen
		}												// charAt --> index sträng

		System.out.print("Your input reversed: ");
		for (int i = 0; i < length; i++) {

			System.out.print(stack.pop()); 			// skriv ut varje element som popas
		}
		
		sc.close();

	}
}
