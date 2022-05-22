package structures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Graph<T extends Comparable<T>> {
	
	private ArrayList<Vertex<T>> graph;
	
	public Graph(){
		graph = new ArrayList<>();
	}
	
	public void addVertex(T value) {
		Vertex<T> addingVertex = new Vertex<>(value);
		if(this.search(addingVertex.getValue())==null) {
			graph.add(addingVertex);
		}
	}
	
	public void addEdge(T v1, T v2, int weight) {
		Vertex<T> vertex1 = this.search(v1);
		Vertex<T> vertex2 = this.search(v2);
		
		if(!vertex1.getAdjacencyList().contains(vertex2) && !vertex2.getAdjacencyList().contains(vertex1)) {
			vertex1.addAdjacency(vertex2, weight);
			vertex2.addAdjacency(vertex1, weight);	
		}
		
	}
	
	public void print() {
		for(Vertex<T> v : graph) {
			System.out.println(v.getValue()+": "+v.getAdjacency());
		}
	}
	
	public void printEdges() {
		for(Vertex<T> v : graph) {
			for(Edge<T> e : v.getEdges()) {
				System.out.println("V1: "+e.getVertex1().getValue()+" V2: "+e.getVertex2().getValue()+" W: "+e.getWeight());
			}
		}
	}
	
	public Vertex<T> search(T value){
		boolean founded = false;
		Vertex<T> vertex = null;
		for(Vertex<T> v : graph) {
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
		return graph.size();
	}
	
	public Vertex<T> getElement(int i){
		return graph.get(i);
	}
	
	public ArrayList<Vertex<T>> getElements(){
		return graph;
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
	
	public ArrayList<Edge<T>> fasterWay(T start,T destination){
        ArrayList<Edge<T>> way = new ArrayList<Edge<T>>();
        
        Vertex<T> startVertex = search(start);
        
        Vertex<T> destinationVertex = search(destination); 
        
        return dijkstra(startVertex,destinationVertex,way);
    }

    private ArrayList<Edge<T>> dijkstra(Vertex<T> start,Vertex<T> fin,ArrayList<Edge<T>> way){
        ArrayList<Edge<T>> aristaArray = start.getEdges();
        Edge<T> min = aristaArray.get(0);

            
        for(int i=0;i< aristaArray.size();i++) {
            if(aristaArray.get(i).getWeight()<min.getWeight()) {
                min=aristaArray.get(i);
            }
        }

        way.add(min);
        
        if(min.getVertex1()==fin||min.getVertex2()==fin) {
        	return way;
        }
            

        Vertex<T> next = null;
        if(min.getVertex1()==start) {
            next = min.getVertex2();
        }
        else {
             next = min.getVertex1();
        }
        
        return dijkstra(next,fin,way);
    }

}
