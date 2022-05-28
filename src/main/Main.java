package main;

import java.util.ArrayList;
import java.util.Scanner;
import structures.Graph;
import structures.Node;
import structures.Tree;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Graph<String> myGraph = new Graph<>();
		
		System.out.println("\nEnter the number of vertex to be entered in the graph: ");
		int numVertex=sc.nextInt();
		sc.nextLine();
		
		for(int i=0;i<numVertex;i++) {
			System.out.print("Vertex "+(i+1)+" -> ");
			String vertex=sc.nextLine();
			myGraph.addVertex(vertex);
		}
		
		System.out.println("\nEnter the number of edges to be entered in the graph: ");
		int numEdges=sc.nextInt();
		
		sc.nextLine();
		for(int i=0;i<numEdges;i++) {
			System.out.println("Edge "+(i+1)+": ");
			System.out.print("First vertex -> ");
			String edge1=sc.nextLine();
			System.out.println("Second vertex -> ");
			String edge2=sc.nextLine();
			System.out.println("Weight - >");
			int weight = sc.nextInt();
			sc.nextLine();
			myGraph.addEdge(edge1,edge2,weight);
		}
			

		
		System.out.println("Wich vertex do you want to find the minimum tree?: ");
		String vertex = sc.nextLine();
		
		
		myGraph.initDijkstra(myGraph.search(vertex));
		
		Tree<String> routesTree = myGraph.getArbolGeneradorMinimo();
		
		System.out.println("Wich vertex do you want to find the route?: ");
		String destination = sc.nextLine();
		
		ArrayList<String> route = routesTree.getRoute(destination);
		
		for(String s : route) {
			System.out.println(s);
		}
		/*
		ArrayList<Node<String>> preOrder = routesTree.preOrder();
		
		for(Node<String> s : preOrder) {
			System.out.println("----"+s.getValue()+"----");
			for(Node<String> c : s.getChildren()) {
				System.out.println(c.getValue());
			}
			System.out.println("");
		}
		*/
	}

}
