package HW_4;
//-----------------------------------------------------
//Title: Main class
//Author:Doğuhan Cumaoğlu
//ID: 28705565570
//Section: 2
//Assignment: 4
//Description: Main class of the project.
//-----------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in);
		String str = scan.nextLine(); // For take the txt file name. 
		File input = new File(str); // place of txt
		Scanner oku = new Scanner(input);
		String fsize = oku.nextLine();// size holder 
		String names_of_the_city[] = fsize.split(" "); // Parse the first lines for take the cites names.
		int size = names_of_the_city.length; // According to names_of_the_city array length equals the size 
		EdgeWeightedGraph G = new EdgeWeightedGraph(size); // Crate our graph  size with size we found previous line
		while (oku.hasNext()) { // Parse the line by line for create edges and implements it on Graph
			String N = oku.nextLine(); // Hold the lines 
			String split[] = N.split(" "); // split the lines according to " "
			int to = 0; // Index of the first station
			int where = 0; // Index of the arrival station
			double j = Double.parseDouble(split[2]);  // J hold the distance between
			for (int i = 0; i < size; i++) { 
				if (split[0].equals(names_of_the_city[i])) { // Find the index of starting positions 
					to = i;
				}
				if (split[1].equals(names_of_the_city[i])) { // Find the index of last positions 
					where = i;
				}
			}
			Edge e = new Edge(to, where, j); // Create a edge according to starting and last positions and distance
			G.addEdge(e); // Add edge to Graph g
		}
		KruskalMST mst = new KruskalMST(G); // mst implementations with karuskal algorithm
		int Total_Distance = 0; // distance holder
		int counter = 1;
		for (Edge e : mst.edges()) {
			Total_Distance = (int) (Total_Distance + e.weight()); // distance calculator

		}
		System.out.println(Total_Distance);
		for (Edge e : mst.edges()) {  // Printing 
			Total_Distance = (int) (Total_Distance + e.weight()); 
			System.out.print(names_of_the_city[e.either()]); // Print starting station
			System.out.print(" ");
			System.out.print(names_of_the_city[e.other(e.either())]); // Print last station
			System.out.print(" ");
			
		}
			
	}
}
