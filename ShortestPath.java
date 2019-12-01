package com.ginchen.algo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import com.ginchen.algo2.Graph.Edge;
import com.ginchen.algo2.Graph.Map;

public class ShortestPath {
	// The number of vertices in the graph
	
	static final int V = 31;
	
	LinkedList<Edge> adj;
		
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
		void printMatrix(Map map, int[] dist){
			for (int i=0;i<V;i++) {
				System.out.println(map.sources[i] + " " + dist[i]);
			}
		}
		
		/*
		 *  find shortest path between each vertex and the source vertex (s)
		 *  using an list (w[][]) as a representation of the graph, where
		 *  w[i][j] is the cost between adjacent vertex i and j
		 *  w[i][j] = 0 if vertex i, j are not direct neighbors 
		 */
		int[] dijkstra(Map map, int s, int d) {
			// dist is the output array; it contains the shortest path cost from the vertex to every other vertex
			// initialize dist
			int[] dist = new int[V];
			
			// inQ[i] is false if vertex i is included in the MST
			Boolean inQ[] = new Boolean[V];
			
			int[] parent = new int[V];
			
			// initialize all dist to inf and all inQ to true
			for (int i=0;i<V;i++) {
				dist[i] = Integer.MAX_VALUE;
				inQ[i] = true;
			}
			
			// the distance between the source vertex and itself is zero
			dist[s] = 0;
			int v = 0;
			for (int i=0;i<V-1;i++) {
				int u = extractMin(dist, inQ);
				if (map.sources[u].equals(map.sources[d])) {
					break;
				}
				inQ[u] = false;
				adj = map.AllEdge[u];
				
				// for all vertices in adj[u]
				for (int y = 0; y<adj.size(); y++) {
					// update dist[v] only:
					/*
					 * v inQ
					 * w[u][v] != 0 
					 * parent vertex is reachable from source vertex (dist[u] != inf)
					 * the new cost is smaller than the previous cost
					 */
					for (int z = 0; z<map.sources.length; z++) {
						if (map.sources[z].equals(adj.get(y).destination)){
							v = z;
						}
					}
					if(inQ[v] && adj.get(y).weight!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+adj.get(y).weight<dist[v]) {
						dist[v] = dist[u]+adj.get(y).weight;
						parent[v] = u;
					}
				}
			}
			
			// print path from source to destination
			LinkedList<Integer> path = new LinkedList<>();
			int p = d;
			while(p != s) {
				path.addFirst(p);
				p = parent[p];
			}
			path.addFirst(s);
			for (int pathIndex : path) {
				System.out.println(map.sources[pathIndex]);
			}
			
			return dist;
		}
		
		
		// test
		public static void main(String[] args) 
	    { 
	        /* Let us create the example graph discussed above */
			int s = 0;
			int dest = 0;
			Scanner input = new Scanner(System.in);
			Map map = new Map();
	        map.createMap();
	        ShortestPath t = new ShortestPath();
	        System.out.println("Source: ");
	        String source = input.nextLine();
	        System.out.println("Destination: ");
	        String destination = input.nextLine();
	        for (int i = 0; i<map.sources.length; i++) {
	        	if (map.sources[i].equals(source)) {
	        		s = i;
	        	}
	        	if (map.sources[i].equals(destination)) {
	        		dest = i;
	        	}
	        }
	        int[] dist = t.dijkstra(map, s, dest);
	        System.out.println(dist[dest]);
	    } 
}
