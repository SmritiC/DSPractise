package Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalsMST {
	
	private int numOfVertices;
	private List<Edge> edges = new ArrayList<Edge>();
	private List<Subset> subsets = new ArrayList<Subset>();
	private List<Edge> mst = new ArrayList<Edge>();
	
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
	
	public List<Edge> findMST(int numOfVertices, List<Edge> edges) {
		this.numOfVertices = numOfVertices;
		this.edges = edges;
		
		for(int vertex=0; vertex < numOfVertices; vertex++) {
			subsets.add(new Subset(vertex, 0));
		}
		
		Collections.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge firstEdge, Edge secEdge) {
				return firstEdge.getWeight() - secEdge.getWeight();
			}
		});
		
		int mstEdges = 0;
		int currentVertex = 0;
		
		while(mstEdges < numOfVertices-1) {
			Edge edge = edges.get(currentVertex);
			int absoluteSource = findPathCompressor(edge.getSource());
			int absoluteDestination = findPathCompressor(edge.getDestination());
			
			if(absoluteSource != absoluteDestination) {
				mst.add(edge);
				unionByRank(absoluteSource, absoluteDestination);
				mstEdges++;
			}
			currentVertex++;
			
		}
		
		return mst;
	}
}
