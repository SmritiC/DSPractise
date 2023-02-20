package Graph;

import java.util.ArrayList;
import java.util.List;

// for undirected graph only
public class CycleDetector {
	
	private int numOfVertices;
	private List<Edge> edges = new ArrayList<Edge>();
	private List<Subset> subsets = new ArrayList<Subset>();
	
	private int findRoot(int vertex) {
		int parent = subsets.get(vertex).getParent();
		if(parent == vertex) {
			return vertex;
		}
		return findRoot(parent);
	}
	
	private void unionSets(int source, int destination) {
		int absoluteSource = findRoot(source);
		int absoluteDestination = findRoot(destination);		
		subsets.get(absoluteSource).setParent(absoluteDestination);
		
	}
	
	public boolean isCycleDetected(int numOfVertices, List<Edge> edges) {
		this.numOfVertices = numOfVertices;
		this.edges = edges;
		
		for(int vertex=0; vertex < numOfVertices; vertex++) {
			subsets.add(new Subset(vertex));
		}
		
		for(Edge edge : edges) {
			int absoluteSource = findRoot(edge.getSource());
			int absoluteDestination = findRoot(edge.getDestination());
			
			if(absoluteSource == absoluteDestination) {
				return true;
			}
			
			unionSets(absoluteSource, absoluteDestination);
		}
		
		return false;
	}
	

}
