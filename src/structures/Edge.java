package structures;

public class Edge<T> {
	private Vertex<T> vertex1;
	private Vertex<T> vertex2;
	private double weight;
	
	public Edge(Vertex<T> vertex1, Vertex<T> vertex2) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
	}

	public Vertex<T> getVertex1() {
		return vertex1;
	}

	public void setVertex1(Vertex<T> v1) {
		this.vertex1 = v1;
	}

	public Vertex<T> getVertex2() {
		return vertex2;
	}

	public void setVertex2(Vertex<T> v2) {
		this.vertex2 = v2;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	

}
