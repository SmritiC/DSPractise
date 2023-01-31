package Graph;

import java.util.ArrayList;


public class BellmanFord {
	
	
	public static ArrayList<Integer> shortestPath(int source, int[][] edges, int numOfvertices){
		ArrayList<Integer> distance = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();
		
		int numOfEdges = edges.length;
		
		//initialize  path and distance
		for(int vertex=0; vertex<numOfvertices; vertex++) {
			path.add(-1);
			distance.add(Integer.MAX_VALUE);
		}
		
		
		distance.set(source, 0);
		
		
		for(int vertex=1; vertex<numOfvertices; vertex++) {
			for(int edgeIndex=0; edgeIndex < numOfEdges; edgeIndex++) {
				int src = edges[edgeIndex][0];
				int dest = edges[edgeIndex][1];
				int weight = edges[edgeIndex][2];
				
				if(distance.get(src)!=Integer.MAX_VALUE && distance.get(src) + weight < distance.get(dest)) {
					distance.set(dest, distance.get(src) + weight);
				}
			}
		}
		
		//check for negative-weight cycles
		
		for(int edgeIndex=0; edgeIndex < numOfEdges; edgeIndex++) {
			int src = edges[edgeIndex][0];
			int dest = edges[edgeIndex][1];
			int weight = edges[edgeIndex][2];
			
			if(distance.get(src)!=Integer.MAX_VALUE && distance.get(src) + weight < distance.get(dest)) {
				System.out.println("Graph contains negative weight cycle");
			}
		}
		
		
		return distance;
	}

	
	public static void main(String args[]) {
		int source = 0;
		int[][] edges = new int[10][3];
		int numOfVertices = 5;
		
		// add edges and weights
		edges[0][0] = 0;
		edges[0][1] = 1;
		edges[0][2] = 6;
		
		edges[1][0] = 0;
		edges[1][1] = 2;
		edges[1][2] = 7;
		
		edges[2][0] = 1;
		edges[2][1] = 2;
		edges[2][2] = 8;
		
		edges[3][0] = 1;
		edges[3][1] = 3;
		edges[3][2] = -4;
		
		
		edges[4][0] = 1;
		edges[4][1] = 4;
		edges[4][2] = 5;
		
		
		edges[5][0] = 2;
		edges[5][1] = 3;
		edges[5][2] = 9;
		
		
		edges[6][0] = 2;
		edges[6][1] = 4;
		edges[6][2] = -3;
		
		edges[7][0] = 3;
		edges[7][1] = 4;
		edges[7][2] = 7;
		
		edges[8][0] = 3;
		edges[8][1] = 0;
		edges[8][2] = 2;
		
		edges[9][0] = 4;
		edges[9][1] = 1;
		edges[9][2] = -2;

		
		ArrayList<Integer> distance = shortestPath(source, edges, numOfVertices);
		System.out.println(distance.toString());
	}
}
