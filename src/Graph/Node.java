package Graph;


public class Node {
	
	int vertex;
	int weight;
	
	public int getVertex() {
		return vertex;
	}

	public void setVertex(int vertex) {
		this.vertex = vertex;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}

}
