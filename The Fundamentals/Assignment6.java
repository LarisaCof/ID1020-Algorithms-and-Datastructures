package lab1;

public class Assignment6 {
	
    private Node first;
    private Node last;
    private int N;

    private static class Node {
        private int item;
        private Node next = null;
    }

    //Adds integer elements to the list in ascending order, starting from the front
    public void enqueue(int item) {
        Node input = new Node();
        input.item = item; // type int

        //If the list is empty, number of elements = 0
        if (N == 0) {
            first = last = input;
            N++;
            return;
        }

        //Current is the used reference for iterating through the list, from the start
        Node current = first;

        //If the input element is the lowest number in the list
        if (input.item < first.item) {
            first = input;
            first.next = current;
            N++;
            return;
        }

        //Iterate through the list until finding an element with higher value than input
        while ((int) input.item > (int) current.item) {

        	//Last possible iteration, insert input as the last element of the list
            if (current == last) {
                last = input;
                current.next = last; //(current blir alltså näst-sist)
                N++;
                return;
            }

            //Checking if we should continue iterating or not
            if (current.next.item < input.item) {
                current = current.next;
            } else {
            	// We can break from here since entering the while loop (input > first)
            	// and input < current.next.item --> break
                break;
            }
        }

        // Insertion
        input.next = current.next;
        current.next = input;
        N++;
    }

    public void dequeue() { // dequeue elements from the front of the list
        if (N == 1) {
            first = last = null;
            N = 0;
            return;
        }
        first = first.next;
        N--;
    }
    

    public String print() {
        StringBuilder sb = new StringBuilder();
        Node current = first;

        if (N == 1) {
            sb.append(current.item);
            return sb.toString();
        }

        while (current.next != null) {
            sb.append(current.item + ", ");
            current = current.next;
        }
        sb.append(current.item);
        return sb.toString();
    }

    public static void main(String[] args) {
 
    	Assignment6 list = new Assignment6();
        
        // Elements
        int a = 1;
        int b = 4;
        int c = 3;
        
        // Testing
        list.enqueue(a); // 1
        list.enqueue(b); // 4
        list.enqueue(c); // 3
        list.dequeue();

            if (list.N > 0) { // if the list is not empty
                System.out.println(list.print());
            } else {
                System.out.println("The list is empty.");
            }
        }
}