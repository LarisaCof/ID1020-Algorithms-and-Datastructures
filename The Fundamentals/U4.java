package lab1;

import java.util.Iterator;

public class U4<Item> implements Iterable<Item> {
	private Node first; 					// link to least recently added node
	private Node last; 						// link to most recently added node
	private int N; 							// number of items on the queue

	private class Node { 					// nested class to define nodes
		Item item;
		Node next;
		Node prev;
	}

	public boolean isEmpty() { 				
		return last == null;
	}

	/*
	 * Number of elements in the list
	 */

	public int size() {
		return N;
	}

	/*
	 * We have two different implementations for adding an element to the queue
	 * depending on if we want to add it to the front or back end of the queue.
	 */

	public void enqueuefront(Item item) { 	// Add item to the front of the list.
		Node oldfirst = first; 				// behåll en referens till första noden
		first = new Node(); 				// skapa den nya första noden
		first.item = item;

		if (isEmpty()) {
			last = first; 					// null
		} else {
			oldfirst.prev = first; 			// den gamla första nodens prev-referens ska peka på nya första
			first.prev = last; 				// Nya första nodens prev ska peka på sista noden (circular)
			last.next = first; 				// Sista nodens next pekar på first (circular)
			first.next = oldfirst; 			// första nodens next pekar på den föregående första noden (andra noden)
		}
		System.out.println("Added from front: " + item);
		N++; // öka antalet element i kön
	}

	public void enqueueback(Item item) { 	// Add item to the end of the list.
		Node oldlast = last;
		last = new Node();
		last.item = item;

		if (isEmpty()) {
			first = last;
		} else {
			last.next = first; //circular
			last.prev = oldlast;
			first.prev = last; // circular
			oldlast.next = last;

		}
		System.out.println("Added from back: " + item);
		N++;
	}

	/*
	 * We have two different implementations for removing an element from the queue
	 * depending on if we want to remove it from the front or back end of the queue.
	 */

	public Item dequeuefront() { 			// Remove item from the beginning of the list.
		Item item = first.item; 			// ta datan från första elementet (det som ska tas bort)
		last.next = first; 					// circular
		first = first.next; 				// nya firstreferensen pekar på andra noden

		if (isEmpty()) {
			first = null;
			last = null;
		}

		else {
			first.prev = last; 				// last referear till nya first (förra 2a noden)
		}

		N--; 								// minska antalet element i kön
		System.out.println("Removed from front: " + item);
		return item;
	}

	public Item dequeueback() { 			// Remove item from the back end of the list.
		Item item = last.item; 				// ta datan från sista elementet
		last.next = first; 					// circular
		last = last.prev; 					// nya lastreferensen

		if (isEmpty()) {
			first = null;
			last = null;
		}

		else {
			first.prev = last; 				// first.prev pekar på nya last
		}

		N--;
		System.out.println("Removed from back: " + item);
		return item;
	}


	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = first;
		private int i = 0;

		public boolean hasNext() {
			return i < N; 					// make sure that the program does not print out to infinity
		}

		public void remove() {
		}

		public Item next() {
			Item item = current.item;
			current = current.next;
			i++; 							// make sure that the program does not print out to infinity
			return item;
		}
	}

	public static void main(String[] args) {
		U4<String> list = new U4<String>();
		String s = "jag";
		String t = "heter";
		String u = "Larisa";
		String v = "Cof";

		list.enqueuefront(s); 
		list.enqueueback(t);
		list.enqueueback(u);
		list.enqueuefront(v);
		list.dequeuefront();
		list.dequeueback();
	
		if (list.isEmpty()) {
			String x = "The list is empty.";
			System.out.println(x);
		}
		
		for (String q : list) {
		System.out.print("|" + q + "|");

		}
		
	}

}
