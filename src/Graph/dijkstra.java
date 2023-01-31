package Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class dijkstra {
	
	
	public static ArrayList<Integer> shortestPath(int source, int[][] edges, int numOfvertices){
		ArrayList<Integer> distance = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();
		ArrayList<ArrayList<Node>> adjList = new ArrayList<ArrayList<Node>>();
		Set<Integer> finalizedVertices = new HashSet<Integer>();
		
		//initialize AdjList, path and distance
		for(int vertex=0; vertex<numOfvertices; vertex++) {
			adjList.add(new ArrayList<>());
			path.add(-1);
			distance.add(Integer.MAX_VALUE);
		}
		
		// create AdjList
		for(int[] edge : edges) {
			adjList.get(edge[0]).add(new Node(edge[1], edge[2]));
		}
		
		distance.set(source, 0);
		PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<Map.Entry<Integer, Integer>>(Comparator.comparing(entry -> entry.getValue()));
		pq.add(Map.entry(source, 0));
		
		while(finalizedVertices.size() != numOfvertices && !pq.isEmpty()) {
			Map.Entry<Integer, Integer> currentNode = pq.remove();
			int currentVertex = currentNode.getKey();
			if(!finalizedVertices.contains(currentVertex)) {
				finalizedVertices.add(currentVertex);
				//check adjacents
				for(Node neigbhor : adjList.get(currentVertex)) {
					int neighborVertex = neigbhor.getVertex();
					if(!finalizedVertices.contains(neighborVertex)) {
						int newDistance = distance.get(currentVertex) + neigbhor.getWeight();
						if(distance.get(neighborVertex) > newDistance){
							distance.set(neighborVertex, newDistance);
							path.set(neighborVertex, currentVertex);
							pq.add(Map.entry(neighborVertex, newDistance));
						}
					}
				}
			}
			
		}
		
		return distance;
	}
	
	public static void main(String args[]) {
		int source = 0;
		int[][] edges = new int[6][3];
		int numOfVertices = 5;
		
		// add edges and weights
		edges[0][0] = 0;
		edges[0][1] = 1;
		edges[0][2] = 4;
		
		edges[1][0] = 0;
		edges[1][1] = 2;
		edges[1][2] = 1;
		
		edges[2][0] = 1;
		edges[2][1] = 4;
		edges[2][2] = 4;
		
		edges[3][0] = 2;
		edges[3][1] = 1;
		edges[3][2] = 2;
		
		
		edges[4][0] = 2;
		edges[4][1] = 3;
		edges[4][2] = 4;
		
		
		edges[5][0] = 3;
		edges[5][1] = 4;
		edges[5][2] = 4;

		
		ArrayList<Integer> distance = shortestPath(source, edges, numOfVertices);
		System.out.println(distance.toString());
	}

}
