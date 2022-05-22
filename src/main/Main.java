package main;

import java.util.ArrayList;

import structures.Edge;
import structures.Graph;
import structures.Vertex;

public class Main {

	public static void main(String[] args) {
		Graph<String> routes = new Graph<>();
		
		
		routes.addVertex("Chiminangos");
		routes.addVertex("Flora Industrial");
		routes.addVertex("Salomia");
		routes.addVertex("Torre de Cali");
		routes.addVertex("Estadio");
		routes.addVertex("Universidades");
		
		routes.addEdge("Chiminangos", "Flora Industrial", 10);
		routes.addEdge("Chiminangos", "Flora Industrial", 10);
		routes.addEdge("Flora Industrial", "Salomia", 12);
		routes.addEdge("Salomia", "Torre de Cali", 13);
		routes.addEdge("Torre de Cali", "Estadio", 15);
		routes.addEdge("Estadio", "Universidades", 15);
		
		
		routes.printEdges();
		
	
		
		/*ArrayList<Edge<String>> fasterWay = routes.fasterWay("Chiminangos", "Salomia");
		
		System.out.println("");
		
		for(Edge<String> e : fasterWay) {
			System.out.println(e.getVertex1().getValue()+" "+e.getVertex2().getValue());
		}*/
		
		
		
		
	}

}
