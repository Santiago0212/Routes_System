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
	
	public void addEdge(T source, T destination) {
		Vertex<T> sourceVertex = this.search(source);
		Vertex<T> destinationVertex = this.search(destination);
		
		if(!sourceVertex.getAdjacencyList().contains(destinationVertex)) {
			sourceVertex.getAdjacencyList().add(destinationVertex);
		}
		
	}
	
	public void print() {
		for(Vertex<T> v : graph) {
			System.out.println(v.getValue()+": "+v.getAdjacency());
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
	
	public void BFS() {
		
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
					v.setColor(Color.GRAY);
					v.setDad(queue.peek());
					queue.offer(v);
				}	
			}
			queue.poll();
		}
		
		boolean stronglyConnected = true;
		
		for(Vertex<T> v : this.getElements()) {
			if(!v.getColor().equals(Color.BLACK)) {
				stronglyConnected = false;
			}
		}
		
		if(stronglyConnected) {
			System.out.println("Strongly connected");
		} else {
			System.out.println("Not strongly connected");
		}
		
	}
	
	public ArrayList<Arista> diztra(Vertex start,Vertex fin){
        ArrayList<Arista> way = new ArrayList<Arista>();

        return diztra(start,fin,way);
    }

    private ArrayList<Arista> diztra(Vertex start,Vertex fin,ArrayList<Arista> way){
        ArrayList<Arista> aristaArray = start.getAristas();
        Arista min = null;
        for(Arista a : aristaArray)
            if(a.isInTheWay()) {
                 min = a;
                break;
            }
        for(int i=0;i< aristaArray.size();i++) {
            if(aristaArray.get(i).isInTheWay()) {
                if(aristaArray.get(i).getWeight()<min.getWeight) {
                    min=aristaArray.get(i);
                }
            }
        }

        way.add(min);
        if(min.getRight()==fin||min.getLeft==fin)
            return way;

        Arista next = null;
        if(min.getRight()==start) {
            next = min.getLeft();
        }
        else {
             next = min.getRight();

        }
        return diztra(next,fin,way);
    }

}
