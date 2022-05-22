package main;

import java.util.ArrayList;

import structures.Edge;
import structures.Graph;
import structures.Vertex;

public class Main {

	public static void main(String[] args) {
		Graph<String> routes = new Graph<>();
		
		
		/*routes.addVertex("Chiminangos");
		routes.addVertex("Flora Industrial");
		routes.addVertex("Salomia");
		routes.addVertex("Torre de Cali");
		routes.addVertex("Estadio");
		routes.addVertex("Universidades");
		routes.addVertex("Unidad Deportiva");
		
		routes.addEdge("Chiminangos", "Flora Industrial", 10);
		routes.addEdge("Chiminangos", "Flora Industrial", 10);
		routes.addEdge("Flora Industrial", "Salomia", 12);
		routes.addEdge("Salomia", "Torre de Cali", 13);
		routes.addEdge("Torre de Cali", "Estadio", 15);
		routes.addEdge("Estadio", "Universidades", 15);
		routes.addEdge("Torre de Cali", "Unidad Deportiva", 5);
		routes.addEdge("Unidad Deportiva", "Universidades", 5);*/
		
		routes.addVertex("A");
		routes.addVertex("B");
		routes.addVertex("C");
		routes.addVertex("D");
		routes.addVertex("E");
		
		routes.addEdge("A", "C", 1);
		routes.addEdge("C", "B", 7);
		routes.addEdge("A", "B", 3);
		routes.addEdge("C", "D", 2);
		routes.addEdge("B", "D", 5);
		routes.addEdge("D", "E", 7);
		routes.addEdge("E", "B", 1);
		
		
		routes.printEdges();
		
		
		routes.initDijktra(routes.search("C"));

		routes.printDistancias();
	
		
		/*ArrayList<Edge<String>> fasterWay = routes.fasterWay("Chiminangos", "Salomia");
		
		System.out.println("");
		
		for(Edge<String> e : fasterWay) {
			System.out.println(e.getVertex1().getValue()+" "+e.getVertex2().getValue());
		}*/
		
		
		
		
	}

}
