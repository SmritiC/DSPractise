package Graph;

import java.util.ArrayList;
import java.util.List;

public class PathCompressor {
	
	private int numOfVertices;
	private List<Edge> edges = new ArrayList<Edge>();
	private List<Subset> subsets = new ArrayList<Subset>();
	
	private int findPathCompressor(int vertex) {
		int parent = subsets.get(vertex).getParent();
		if(parent == vertex) {
			return vertex;
		}
		int newParent = findPathCompressor(parent);
		subsets.get(vertex).setParent(newParent);
		return newParent;
	}
	
	private void unionByRank(int source, int destination) {
		Subset sourceSubset = subsets.get(source);
		Subset destinationSubset = subsets.get(destination);
		
		if(sourceSubset.getRank() < destinationSubset.getRank()) {
			sourceSubset.setParent(destination);
		}
		else if(sourceSubset.getRank() > destinationSubset.getRank()) {
			destinationSubset.setParent(source);
		}
		else {
			destinationSubset.setParent(source);
			sourceSubset.setRank(sourceSubset.getRank()+1);
		}
		
	}
	
	public boolean isCycleDetected(int numOfVertices, List<Edge> edges) {
		this.numOfVertices = numOfVertices;
		this.edges = edges;
		
		for(int vertex=0; vertex < numOfVertices; vertex++) {
			subsets.add(new Subset(vertex, 0));
		}
		
		for(Edge edge : edges) {
			int absoluteSource = findPathCompressor(edge.getSource());
			int absoluteDestination = findPathCompressor(edge.getDestination());
			
			if(absoluteSource == absoluteDestination) {
				return true;
			}
			
			unionByRank(absoluteSource, absoluteDestination);
		}
		
		return false;
	}
}
