package structures;

import java.util.ArrayList;

public class Node<T> {
	T value;
	Node<T> dad;
	
	ArrayList<Node<T>> children;
	
	public Node(T value) {
		this.value = value;
		this.children = new ArrayList<>();
	}

	public T getValue() {
		return value;
	}

	public Node<T> getDad() {
		return dad;
	}

	public void setDad(Node<T> dad) {
		this.dad = dad;
	}

	public ArrayList<Node<T>> getChildren() {
		return children;
	}
	
}
