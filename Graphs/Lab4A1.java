/*
 * README
 * Author: Larisa Cof	
 * Date: 2020-10-06
 * 
 * 1. Write a program based on DFS which can 
 * answer questions of the type: "Find the a 
 * path from X to Y" Which should result in a 
 * list of vertices traversed from X to Y if 
 * there is a path.
 * 
 * I create a BST in order to keep track of which state is associated to which number.
 * Then, I build a graph based on a fixed number (in this case, the number of vertices).
 * I go through the database file a second time and add an edge between all states that are on the same line. 
 * When the graph is done, I then create an object of DepthFirstPaths to which I send
 * the graph and the source vertex to.
 * Lastly, I iterate through my DFP object to print out all vertices that are on the path 
 * from the source vertex to the "goal". 
 * 
 */

package labnumber4;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Lab4A1 {

	public static void main(String[] args) throws IOException {

		System.out.println("Enter the state you want to start from:");
		Scanner sc1 = new Scanner(System.in);
		String startingpoint = sc1.nextLine();

		System.out.println("Enter the state you want to go to:");
		String endpoint = sc1.nextLine();

		BST<String, Integer> bst = new BST<String, Integer>(); // create bst to associate all vertices with a unique
																// number

		File file = new File("database.dat");
		Scanner sc2 = new Scanner(file);
		
		int i = 0; // value given to each vertex
		while (sc2.hasNext()) { 

			String states = sc2.next(); // read string from file
			if (!bst.contains(states)) {
				bst.put(states, i); // add every state to bst (once)
				i++;
			}
		}

		// want to build a graph based on all states from bst

		Graph graph = new Graph(49); // build a graph with 49 vertices
		Scanner sc3 = new Scanner(file); // read file from beginning

		while (sc3.hasNext()) { // add an edge between states on the same line in database

			String vertex = sc3.next();
			String vertex2 = sc3.next();
			graph.addEdge(bst.get(vertex), bst.get(vertex2));

		}
		
		//Test to print out graph
//		System.out.print(graph.toString());

		int x = bst.get(startingpoint);
		int y = bst.get(endpoint);

		DepthFirstPaths findpath = new DepthFirstPaths(graph, x);


		// if there is a path between x and y, print it.
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

// EXAMPLE EXECTUTION:
// Enter the state you want to start from:
// CA
// Enter the state you want to go to:
// WA
// The path from CA to WA: 
// CA | OR | WA | 
// WA reached!

