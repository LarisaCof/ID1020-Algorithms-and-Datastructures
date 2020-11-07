/*
 * README
 * Author: Larisa Cof	
 * Date: 2020-10-06
 * 
 * 3. Write a program that can answer if there is a path between any to vertices.
 * 
 * I create a BST in order to keep track of which state is associated to which number.
 * Then, I build a digraph based on a fixed number (in this case, the number of vertices).
 * I go through the database file a second time and add an edge between all states that are on the same line. 
 * When the graph is done, I then create an object of DirectedDFS to which I send
 * the graph and the source vertex to.
 * Lastly, I check if there is a path from the startingpoint to the endpoint.
 * 
 */

package labnumber4;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Lab4A3 {
	public static void main(String[] args) throws IOException {

		System.out.println("Enter the state you want to start from:");
		Scanner sc1 = new Scanner(System.in);
		String startingpoint = sc1.nextLine();

		System.out.println("Enter the state you want to go to:");
		String endpoint = sc1.nextLine();
		
		BST<String, Integer> bst = new BST<String, Integer>();
		
		File file = new File("database.dat");
		Scanner sc2 = new Scanner(file);
		int i = 0;
		
		while (sc2.hasNext()) { // 49 states, 0-48

			String states = sc2.next(); // read string from file
			if (!bst.contains(states)) {
				bst.put(states, i); // add every state to bst (once)
				i++;
			}
		}

		// want to build a graph based on all states from bst
		Digraph graph = new Digraph(49); // build a graph with 49 vertices
		Scanner sc3 = new Scanner(file);

		while (sc3.hasNext()) { // add an edge between states on the same line in database

			String vertex = sc3.next();
			String vertex2 = sc3.next();
			graph.addEdge(bst.get(vertex), bst.get(vertex2));

		}
		
		int x = bst.get(startingpoint);
		int y = bst.get(endpoint);

		DirectedDFS findpath = new DirectedDFS(graph, x); // x sent as source
		System.out.print(graph.toString());
		
		if (findpath.marked(y)) { // does the path (source - y) exist (are all vertices on the path marked)
			System.out.println();
			System.out.println("There exists a path from " + startingpoint + " to " + endpoint);
		} else {
			System.out.println("There is no path from " + startingpoint + " to " + endpoint);
		}

		sc1.close();
		sc2.close();
		sc3.close();

	}
}

// EXAMPLE EXECUTION (PATH FOUND):
// Enter the state you want to start from:
// CA
// Enter the state you want to go to:
// WA
//
// There exist a path from CA to WA


// EXAMPLE EXECUTION (NO PATH):
// Enter the state you want to start from:
// CA
// Enter the state you want to go to:
// AL
// There is no path from CA to AL


