package structures;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T extends Comparable<T>> {
	
	private ArrayList<Vertex<T>> verticesGraph;
	private ArrayList<Edge<T>> edgeGraph;
	
	public Graph(){
		verticesGraph = new ArrayList<>();
		edgeGraph = new ArrayList<>();
	}
	
	public void addVertex(T value, int num) {
		Vertex<T> addingVertex = new Vertex<>(value, num);
		if(this.search(addingVertex.getValue())==null) {
			verticesGraph.add(addingVertex);
		}
	}
	
	public void addEdge(T v1, T v2, int weight) {
		Vertex<T> vertex1 = this.search(v1);
		Vertex<T> vertex2 = this.search(v2);
		
		if(!vertex1.getAdjacencyList().contains(vertex2) && !vertex2.getAdjacencyList().contains(vertex1)) {
			Edge<T> edge1=new Edge<T>(vertex1,vertex2,weight);
			Edge<T> edge2=new Edge<T>(vertex2,vertex1,weight);
			edgeGraph.add(edge1);
			edgeGraph.add(edge2);
			vertex1.addAdjacency(vertex2, weight);
			vertex2.addAdjacency(vertex1, weight);	
		}
	}
	
	public int getWeight(Vertex<T> vertex1,Vertex<T> vertex2) {
		int weight=0;
		for(int i=0;i<edgeGraph.size();i++) {
			if(edgeGraph.get(i).getVertex1()==vertex1) {
				if(edgeGraph.get(i).getVertex2()==vertex2) {
					weight=edgeGraph.get(i).getWeight();
				}
			}
		}
		return weight;
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
	
	public void printDistancias() {
		for(Vertex<T> v : verticesGraph) {
			System.out.println(v.getValue()+"(-)"+distancias.get(v));
		}
		for(int i=0;i<arregloCamino.size();i++) {
			if(distancias.get(arregloCamino.get(i).getVertex2())<arregloCamino.get(i).getWeight()) {
				arregloCamino.remove(i);
			}
		}
	}
	
	
	public Vertex<T> search(T value){
		boolean founded = false;
		Vertex<T> vertex = null;
		for(Vertex<T> v : verticesGraph) {
			if(v.getValue().compareTo(value)==0) {
				founded = true;
				vertex = v;
				break;
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
		
	private HashMap<Vertex<T>,Integer> distancias;
	private ArrayList<Edge<T>> arregloCamino;
	private Tree<T> arbolGeneradorMinimo;
	
	

    public int[][] floyd() {
    	
    	int dist[][]; 

        dist = new int[verticesGraph.size()][verticesGraph.size()];

        for(Vertex<T> v : this.getElements()) {
            v.setColor(Color.WHITE);
        }

        for(int i=0;i<verticesGraph.size();i++) {
            for(int j=0;j<verticesGraph.size();j++) {
                if(i!=j) {
                    dist[i][j]=1000000000;
                }
            }
        }

        for(int i=0;i<edgeGraph.size();i++) {
            Vertex<T> aux1=edgeGraph.get(i).getVertex1();
            Vertex<T> aux2=edgeGraph.get(i).getVertex2();
            dist[aux1.getNum()][aux2.getNum()]=edgeGraph.get(i).getWeight();
            dist[aux2.getNum()][aux1.getNum()]=edgeGraph.get(i).getWeight();
        }

        for(int k=0;k<verticesGraph.size();k++) {
            for(int i=0;i<verticesGraph.size();i++) {
                for(int j=0;j<verticesGraph.size();j++) {
                    if(dist[i][j]>dist[i][k]+dist[k][j]) {
                        dist[i][j]=dist[i][k]+dist[k][j];
                    }
                }
            }
        }
        return dist;
    }
	
	public void initDijkstra(Vertex<T> initial) {
		
		for(Vertex<T> v : this.getElements()) {
			v.setColor(Color.WHITE);
		}
		

		distancias=new  HashMap<Vertex<T>,Integer>();
		arregloCamino=new ArrayList<Edge<T>>();
		arbolGeneradorMinimo = new Tree<T>(new Node<T>(initial.getValue()));
		
		for(int i=0;i<verticesGraph.size();i++) {
			if(verticesGraph.get(i).equals(initial)) {
				distancias.put(verticesGraph.get(i),0);
			}else {
				distancias.put(verticesGraph.get(i),Integer.MAX_VALUE);
			}
		}
		dijkstra(initial);
	}
	
	public void dijkstra(Vertex<T> current) {
        for(int i=0;i<current.getAdjacencyList().size();i++) {
            Vertex<T> aux=current.getAdjacencyList().get(i);


            int distanciaActual=distancias.get(current);	

            int weight=getWeight(current, aux);

            if((distanciaActual+weight)<distancias.get(aux)) {

                for(int j=0;j<current.getAdjacencyEdges().size();j++) {
                    if(current.getAdjacencyEdges().get(j).getVertex2()==aux) {
                        arregloCamino.add(current.getAdjacencyEdges().get(j));
                        T nodeValue = current.getAdjacencyEdges().get(j).getVertex2().getValue();
						Node<T> node = new Node<T>(nodeValue);
						
						T dadValue = current.getAdjacencyEdges().get(j).getVertex1().getValue();
						
						node.setDad(arbolGeneradorMinimo.find(dadValue));
					
						arbolGeneradorMinimo.find(dadValue).getChildren().add(node);
                    }
                }

                distancias.remove(aux);
                distancias.put(aux, distanciaActual+weight);
            }
        }
        current.setColor(Color.BLACK);
        
        for(int i=0;i<current.getAdjacencyList().size();i++) {
        	if(current.getAdjacencyList().get(i).getColor()==Color.WHITE) {
        		dijkstra(current.getAdjacencyList().get(i));
        	}     	
        }
        
    }

	public HashMap<Vertex<T>, Integer> getDistancias() {
		return distancias;
	}

	public void setDistancias(HashMap<Vertex<T>, Integer> distancias) {
		this.distancias = distancias;
	}

	public ArrayList<Edge<T>> getArregloCamino() {
		return arregloCamino;
	}

	public void setArregloCamino(ArrayList<Edge<T>> arregloCamino) {
		this.arregloCamino = arregloCamino;
	}

	public Tree<T> getArbolGeneradorMinimo() {
		return arbolGeneradorMinimo;
	}
}
