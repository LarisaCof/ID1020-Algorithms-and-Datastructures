package lab1;
import java.util.Iterator;

public class Assignment3<Item> implements Iterable<Item> {

	private Node first; 				// link to least recently added node
	private Node last; 					// link to most recently added node
	private int N; 						// number of items on the queue

	private class Node { 				// nested class to define nodes
		Item item; 						// item, den data vi lagrar i noden
		Node next; 						// referens till nästa nod
		Node prev; 						// referens till föregående nod

	}

	public boolean isEmpty() { 			// om listan är tom, referens first pekar till null
		return first == null;
	}

	public int size() { 				// antalet element i listan
		return N;
	}

	public void enqueue(Item item) { 	// Lägger till ett element i slutet på listan

		Node oldlast = last; 			// vi länkar den sista noden
		last = new Node(); 				// last refererar nu till den nya noden som är det sista elementet
		last.item = item; 				// lagra datan
		//last.next = null; 				// sista elementet har nullreferens
		last.next = first; //circular
		

		if (isEmpty()) 					// Inga element i listan --> listans första element
			first = last; 				// Vi vill att first ska peka på last pga ny nod
		else
			oldlast.next = last; 		// oldlast.next ska peka på "nya" last
		last.prev = oldlast; 			// det som oldlast pekade på ska last.prev peka på
		N++; 							// öka antalet element i listan med 1
	}

	public Item dequeue() 				// tar bort element från början av listan (FIFO)
	{
		Item item = first.item; 		// vi hämtar den data som first pekar på
		first = first.next; 			// first pekar på andra elementet i listan (first.next)
		first.next.prev = last; // circular
		last.next = first;      // circular
		if (isEmpty()) { 				// om listan är tom sätt nullreferens till last
			last = null;
			first = null;
			
		}
		
//		Item item = last.item; // hämtar data från last
//		Node oldlast = last.prev; 
//		last = oldlast; // tar bort referens från tidigare last, oldlast blir last
//		first.prev = oldlast; //circular
//		oldlast.next = first; // circular
//		
//		if (isEmpty()) { 				// om listan är tom sätt nullreferens till last
//			last = null;
//			first = null;
//			
//		}
		
		N--; 							// minska antalet element i listan med 1
		return item;
	}

	
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first; 	// vi vill itererea genom listan från första elementet

		public boolean hasNext() 		// vi kollar om den nuvarande noden vi är på har nullreferens
		{
			return current != null;
		}

		public Item next() 				// sparar en referens till current data. Förnyar current så att den pekar
										// på nästa nod och returnerar den data som finns på current node.
		{
			Item item = current.item;	// gå till nästa, iterera genom kön
			current = current.next;
			return item;
		}
	} 

	public static void main(String[] args) {
		Assignment3<String> list = new Assignment3<String>();
		String a = "jag";
		String b = "heter";
		String c = "Larisa";
		String d = "Cof";

		list.enqueue(a);
		list.enqueue(b);
		list.enqueue(c);
		list.enqueue(d);
		// FIFO = tar bort element från början av kön
		list.dequeue();
		list.dequeue();
		 
		if (list.isEmpty()) {
			String str = "The list is empty.";
			System.out.println(str);
		} 
		
		else {
			
			  for (String s : list)
			       System.out.print(" |" + s + "| ->");

			}

		}

	}


