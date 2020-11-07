/*
 * README
 * Author: Larisa Cof	
 * Date: 2020-10-06
 * 
 * 2. Change the program (in A1) to use BFS.
 * 
 * I create a BST in order to keep track of which state is associated to which number.
 * Then, I build a graph based on a fixed number (in this case, the number of vertices).
 * I go through the database file a second time and add an edge between all states that are on the same line. 
 * When the graph is done, I then create an object of BreadthFirstPaths to which I send
 * the graph and the source vertex to.
 * Lastly, I iterate through my BFP object to print out all vertices that are on the path 
 * from the source vertex to the "goal". 
 * 
 */

package labnumber4;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Lab4A2 {
	
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
		Graph graph = new Graph(49); // build a graph with 49 vertices
		Scanner sc3 = new Scanner(file);

		while (sc3.hasNext()) { // add an edge between states on the same line in database

			String vertex2 = sc3.next();
			String vertex = sc3.next();
			graph.addEdge(bst.get(vertex2), bst.get(vertex));

		}

		int x = bst.get(startingpoint);
		int y = bst.get(endpoint);
		

		BreadthFirstPaths findpath = new BreadthFirstPaths(graph, x); // check for shortest path from source x
		
		if (findpath.hasPathTo(y)) {
			System.out.println("The path from " + startingpoint + " to " + endpoint + ": ");
			
			// Iterate through the path from x to y
			for (int v : findpath.pathTo(y)) {
				
				// iterate through all the keys in the BST
				for (String key : bst.keys()) {
					
					// if a vertex is equal to the keys value (unique number), print the key.
					if (v == bst.get(key))
						System.out.print(key + " | ");
					
				}
			
			}
			System.out.println();
			System.out.println(endpoint + " reached!");
		} else {
			System.out.println("There is no path between " + startingpoint + " and " + endpoint);
		}

		sc1.close();
		sc2.close();
		sc3.close();

	}

}

// EXAMPLE EXECUTION
// Enter the state you want to start from:
// AL
// Enter the state you want to go to:
// LA
// The path from AL to LA: 
// AL | MS | LA | 
// LA reached!
