package lab1;
import java.util.Iterator;


public class Assignment5<Item> implements Iterable<Item> {
	private Node first; 					// minst senast tillagda nod
	private Node last; 						// senast tillagda nod
	private int N; 							// antal element i kön

	private class Node { 					// nästlad klass för att definiera metoder
		Item item; 							// data i noden
		Node next; 							// referens till nästa nod
		Node prev; 							// referens till föregående nod
	}

	public boolean isEmpty() {
		return first == null; 				// listan är tom, first -> null
	}

	public int size() {
		return N; 							// antal element i kön
	}

	public void enqueue(Item item) { 		// lägger till ett element
		
		Node oldlast = last;
		last = new Node();
		last.item = item;

		if (isEmpty()) {
			first = last;
		} else {
			last.next = first;
			last.prev = oldlast;
			first.prev = last;
			oldlast.next = last;

		}

		N++;

	}

	public void dequeue(int k) { 			// tar bort det k:te elementet
		
		if (isEmpty()) {
			first = null;
			last = null;
		}
		
		else {
		
		if (k == 1) {
			first = first.next; 				// nya firstreferensen pekar på andra noden
		}
		
		Node current = first; 				// current pekar på första noden (initialt)
		int count = 1; 						// vi använder count för att "räkna" upp till k
		while (count < k) 					// räkna tills count = k
		{
			current = current.next; 		// current pekar på nästa nod tills vi når k
			count++; 						// öka för varje steg
		}
		
		current.next.prev = current.prev;
		current.prev.next = current.next;	// När vi når element k, ändra referenserna
		
		
		N--; 								// minskar antalet element med 1
		
		}

	}

	// See page 155 for iterator() implementation.
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		private int i = 0;					// förhindrar att loopen printar oändligt

		public boolean hasNext() {
			return i < N;					// förhindrar att loopen printar oändligt
		}

		public void remove() {

		}

		public Item next() {
			Item item = current.item;
			current = current.next;
			i++;							// förhindrar att loopen printar oändligt
			return item;
		}
	}


	public static void main(String[] args) {

		Assignment5<String> list = new Assignment5<String>();
		String a = "hej";
		String b = "på";
		String c = "dig";
		String d = "Larisa";
		list.enqueue(a);
		list.enqueue(b);
		list.enqueue(c);
		list.enqueue(d);
		list.dequeue(3); 

		if (list.isEmpty()) {
			String t = "The list is empty.";
			System.out.println(t);
		}

		else {
			for (String q : list) {
				System.out.print("|" + q + "|");
			}

		}

	}

}
