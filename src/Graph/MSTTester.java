package Graph;

import java.util.ArrayList;
import java.util.List;

public class MSTTester {
	
	
	private static boolean cycleDetectionTest() {
		CycleDetector cycleDetector = new CycleDetector();
		int numOfVertices = 4;
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(0,1));
		edges.add(new Edge(0,3));
		edges.add(new Edge(2,3));
		edges.add(new Edge(1,2));
		
		boolean expectedResult = true;
		return (cycleDetector.isCycleDetected(numOfVertices, edges) == expectedResult)? true: false;	
	}

	private static boolean cyclePathCompressorTest() {
		PathCompressor cycleDetector = new PathCompressor();
		int numOfVertices = 4;
		List<Edge> edges = new ArrayList<Edge>();
		edges.add(new Edge(0,1));
		edges.add(new Edge(0,3));
		edges.add(new Edge(2,3));
		edges.add(new Edge(1,2));
		
		boolean expectedResult = true;
		return (cycleDetector.isCycleDetected(numOfVertices, edges) == expectedResult)? true: false;	
	}
	
	private static boolean kruskalsMSTTest() {
		KruskalsMST MSTBuilder = new KruskalsMST();
		int numOfVertices = 4;
		List<Edge> edges = new ArrayList<Edge>(List.of(
                new Edge(0, 1, 10),               
                new Edge(0, 2, 6),   
                new Edge(0, 3, 5),
                new Edge(1, 3, 15),
                new Edge(2, 3, 4)
        ));
		
		List<Edge> expectedResult = new ArrayList<Edge>(List.of(
                new Edge(2, 3, 4),                  
                new Edge(0, 3, 5),
                new Edge(0, 1, 10)
        ));
		List<Edge> mst = MSTBuilder.findMST(numOfVertices, edges);
		
		
		
		if(mst.size() != expectedResult.size()) {
			return false;
		}
			
		for(int edgeIndex = 0; edgeIndex < mst.size(); edgeIndex++) {
			System.out.println(mst.get(edgeIndex).toString());
			if(!mst.get(edgeIndex).equals( expectedResult.get(edgeIndex))) {
				return false;
			}
				
		}
		return true;	
	}
	
	public static void main(String args[]) {
		if(cycleDetectionTest()) {
			System.out.println("Cycle Detection algorithm - Brute force Passed!!!");
		}
		
		if(cyclePathCompressorTest()) {
			System.out.println("Cycle Detection algorithm - Path Compressor by Rank Passed!!!");
		}
		
		if(kruskalsMSTTest()) {
			System.out.println("Kruskal's MST algorithm Passed!!!");
		}
		
		
	}
}
