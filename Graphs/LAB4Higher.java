package testhigher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LAB4Higher {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Enter where you want to start: ");
		int startingpoint = sc1.nextInt();

		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter where you want to go to: ");
		int endpoint = sc2.nextInt();

		Scanner sc3 = new Scanner(System.in);
		System.out.println("Passing through: ");
		int pass = sc3.nextInt();

		int[] array = new int[264346 * 3]; // antal vertices*3 pga v1, v2, weight

		File file = new File("NYC.txt");
		Scanner sc4 = new Scanner(file);

		for (int i = 0; i < array.length; i++) {

			if (i % 3 == 0) {
				int v1 = sc4.nextInt();// take the index for the searched word
				int v2 = sc4.nextInt();
				int weight = sc4.nextInt();

				array[i] = v1;
				array[i + 1] = v2;
				array[i + 2] = weight;
				// i = i + 2;
			}

		}

		// TEST
//		for (int k : array) {
//
//			System.out.println(k);
//		}

		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(264346); // 264346 = number of vertices
		DirectedEdge edges;
		// becomes undirected since an edge is
		// added between all vertices in the text file
		// and it is already stated that the graph is connected

		for (int k = 0; k < array.length; k++) {

			if (k % 3 == 0) {

				edges = new DirectedEdge(array[k], array[k + 1], array[k + 2]);
				graph.addEdge(edges);

			}

			// k = k + 2;

		}

		DijkstraSP sp = new DijkstraSP(graph, startingpoint); // shortest paths problem in edge-weighted digraphs

		if (sp.hasPathTo(pass)) {
			for (DirectedEdge p : sp.pathTo(pass)) {
				// System.out.print(p); // print as list
				System.out.println(p);
			}

			System.out.println("**** Passed through " + pass + " ****");

			DijkstraSP sp2 = new DijkstraSP(graph, pass); // from passing through point to endpoint

			if (sp2.hasPathTo(endpoint)) {

				for (DirectedEdge e : sp2.pathTo(endpoint)) {
					// System.out.print(" " + e + " "); // print as list
					System.out.println(e);
				}

			}

		} else {
			System.out.println(
					"There is no path between " + startingpoint + " and " + endpoint + " passing through " + pass);
		}

		sc1.close();
		sc2.close();
		sc3.close();
		sc4.close();

	}
}
