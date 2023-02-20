package Graph;

public class Edge {

	private int source;
	private int destination;
	private int weight;
	
	public Edge(int source, int destination, int weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	
	public Edge(int source, int destination) {
		this.source = source;
		this.destination = destination;
	}
	
	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return "Edge - source:" + source + " destination:" + destination + " weight" + weight;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Edge edge = (Edge) obj;
        if(this.source == edge.getSource() && this.destination == edge.getDestination() && this.weight == edge.getWeight()) {
        	return true;
        }
        return false;
	}

}
