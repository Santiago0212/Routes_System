package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import structures.Graph;
import structures.Node;
import structures.Tree;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			Graph<String> myGraph = createGraph();
			
			System.out.println("Wich vertex do you want to find the minimum tree?: ");
			String vertex = sc.nextLine();
			vertex = vertex.toUpperCase();
			
			myGraph.initDijkstra(myGraph.search(vertex));
			
			Tree<String> routesTree = myGraph.getArbolGeneradorMinimo();
			
			System.out.println("Wich vertex do you want to find the route?: ");
			String destination = sc.nextLine();
			destination = destination.toUpperCase();
			
			ArrayList<String> route = routesTree.getRoute(destination);
			
			for(String s : route) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}
	
	public static Graph<String> createGraph() throws IOException {
		Graph<String> myGraph = new Graph<>();
	
		File data = new File("stations.txt");
	
		BufferedReader bf = new BufferedReader(new FileReader(data));
	
		int stationsNumber = Integer.parseInt(bf.readLine());
		
		String line;
		for(int i=0; i<stationsNumber && (line = bf.readLine())!=null; i++) {
			myGraph.addVertex(line);
		}
		
		int connectionsNumber = Integer.parseInt(bf.readLine());
		
		for(int i=0; i<connectionsNumber && (line = bf.readLine())!=null; i++) {
			String[] connection = line.split("\\|");
			myGraph.addEdge(connection[0],connection[1], (int) Double.parseDouble(connection[2])*100);
		}
		
		bf.close();
		
		return myGraph;
	}
}
