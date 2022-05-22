package structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph<T extends Comparable<T>> {
	
	private ArrayList<Vertex<T>> verticesGraph;
	private ArrayList<Edge<T>> edgeGraph;
	
	public Graph(){
		verticesGraph = new ArrayList<>();
		edgeGraph = new ArrayList<>();
	}
	
	public void addVertex(T value) {
		Vertex<T> addingVertex = new Vertex<>(value);
		if(this.search(addingVertex.getValue())==null) {
			verticesGraph.add(addingVertex);
		}
	}
	
	public void addEdge(T v1, T v2, int weight) {
		Vertex<T> vertex1 = this.search(v1);
		Vertex<T> vertex2 = this.search(v2);
		
		if(!vertex1.getAdjacencyList().contains(vertex2) && !vertex2.getAdjacencyList().contains(vertex1)) {
			Edge edge1=new Edge(vertex1,vertex2,weight);
			Edge edge2=new Edge(vertex2,vertex1,weight);
			edgeGraph.add(edge1);
			edgeGraph.add(edge2);
			vertex1.addAdjacency(vertex2, weight);
			vertex2.addAdjacency(vertex1, weight);	
		}
		
	}
	
	public void print() {
		for(Vertex<T> v : verticesGraph) {
			System.out.println(v.getValue()+": "+v.getAdjacency());
		}
	}
	
	public void printEdges() {
		for(Edge<T> e :edgeGraph) {
			System.out.println("V1: "+e.getVertex1().getValue()+" V2: "+e.getVertex2().getValue()+" W: "+e.getWeight());
		}
		
	}
	
	public Vertex<T> search(T value){
		boolean founded = false;
		Vertex<T> vertex = null;
		for(Vertex<T> v : verticesGraph) {
			if(v.getValue().compareTo(value)==0) {
				founded = true;
				vertex = v;
			}
		}
		if(founded) {
			return vertex;
		} else {
			return null;
		}
	}
	
	public int getSize() {
		return verticesGraph.size();
	}
	
	public Vertex<T> getElement(int i){
		return verticesGraph.get(i);
	}
	
	public ArrayList<Vertex<T>> getElements(){
		return verticesGraph;
	}
	
	public Vertex<T> BFS(T searchingValue) {
		
		Vertex<T> searchingVertex = new Vertex<T>(searchingValue);
		
		Vertex<T> foundedVertex = null;
		
		for(Vertex<T> v : this.getElements()) {
			v.setColor(Color.WHITE);
		}
		
		Queue<Vertex<T>> queue = new LinkedList<>();
		
		
		Vertex<T> initialVertex = this.getElement(0);
		initialVertex.setColor(Color.GRAY);
		queue.offer(initialVertex);
			
		while(!queue.isEmpty()) {
			queue.peek().setColor(Color.BLACK);
			for(Vertex<T> v : queue.peek().getAdjacencyList()) {
				if(v.getColor().equals(Color.WHITE)) {
					if(v.getValue().compareTo(searchingVertex.getValue())==0) {
						foundedVertex = v;
					}
					
					v.setColor(Color.GRAY);
					v.setDad(queue.peek());
					queue.offer(v);
				}	
			}
			queue.poll();
		}
		
		return foundedVertex;
		
	}
	
}
