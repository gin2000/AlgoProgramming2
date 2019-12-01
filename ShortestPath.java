package dijkstra;

/*
 * This program implements Dijkstra's algorithm
 * it calculates the shortest path from the source vertex to every other vertex in a graph
 * 
 * code reference: 
 * https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/ 
 */

public class ShortestPath {
	// The number of vertices in the graph 
	static final int V = 9;
	
	// gives the index of the vertex of minimum distance from the source (vertex is inQ)
	int extractMin(int dist[], Boolean inQ[]) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		/*
		 * int min = dist[0]; int minIndex = 0;
		 * This line does not work because dist[0] might not be inQ already
		 */		
		for (int i=0;i<V;i++) {
			if (inQ[i] && dist[i]<min) {
				min = dist[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	
	// prints the distance between each vertex in the graph and the source vertex
	void printMatrix(int[] dist){
		for (int i=0;i<V;i++) {
			System.out.println(i + " " + dist[i]);
		}
	}
	
	/*
	 *  find shortest path between each vertex and the source vertex (s)
	 *  using an list (w[][]) as a representation of the graph, where
	 *  w[i][j] is the cost between adjacent vertex i and j
	 *  w[i][j] = 0 if vertex i, j are not direct neighbors 
	 */
	int[] dijkstra(int w[][], int s) {
		// dist is the output array; it contains the shortest path cost from the vertex to every other vertex
		// initialize dist
		int[] dist = new int[V];
		
		// inQ[i] is false if vertex i is included in the MST
		Boolean inQ[] = new Boolean[V];
		
		// initialize all dist to inf and all inQ to true
		for (int i=0;i<V;i++) {
			dist[i] = Integer.MAX_VALUE;
			inQ[i] = true;
		}
		
		// the distance between the source vertex and itself is zero
		dist[s] = 0;
		
		for (int i=0;i<V-1;i++) {
			int u = extractMin(dist, inQ);
			inQ[u] = false;
			
			// for all vertices in adj[u]
			for (int v=0;v<V;v++) {
				// update dist[v] only:
				/*
				 * v inQ
				 * w[u][v] != 0 
				 * parent vertex is reachable from source vertex (dist[u] != inf)
				 * the new cost is smaller than the previous cost
				 */
				if(inQ[v] && w[u][v]!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+w[u][v]<dist[v]) {
					dist[v] = dist[u]+w[u][v];
				}
			}
		}
		return dist;
	}
	
	
	// test
	public static void main(String[] args) 
    { 
        /* Let us create the example graph discussed above */
        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 }, 
                                      { 4, 0, 8, 0, 0, 0, 0, 11, 0 }, 
                                      { 0, 8, 0, 7, 0, 4, 0, 0, 2 }, 
                                      { 0, 0, 7, 0, 9, 14, 0, 0, 0 }, 
                                      { 0, 0, 0, 9, 0, 10, 0, 0, 0 }, 
                                      { 0, 0, 4, 14, 10, 0, 2, 0, 0 }, 
                                      { 0, 0, 0, 0, 0, 2, 0, 1, 6 }, 
                                      { 8, 11, 0, 0, 0, 0, 1, 0, 7 }, 
                                      { 0, 0, 2, 0, 0, 0, 6, 7, 0 } }; 
        ShortestPath t = new ShortestPath(); 
        int[] dist = t.dijkstra(graph, 0);
        t.printMatrix(dist);
    } 
}
