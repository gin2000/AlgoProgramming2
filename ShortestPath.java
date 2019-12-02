package com.ginchen.algo2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.commons.text.WordUtils;

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
				System.out.println(map.sources.get(i) + " " + dist[i]);
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
				if (map.sources.get(u).equals(map.sources.get(d))) {
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
					v = map.sources.indexOf(adj.get(y).destination);
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
				System.out.print(map.sources.get(pathIndex) + "; ");
			}
			
			return dist;
		}
		
		
		// test
		public static void main(String[] args) 
	    { 
	        /* Let us create the example graph discussed above */
			String source = null;
			String destination = null;
			Scanner input = new Scanner(System.in);
			
			Map map = new Map();
	        map.createMap();
	        ShortestPath t = new ShortestPath();
	        
	        while (true) {
	        	System.out.println("Source: (Please choose one of the following places: \n\"Indoor Track And Tennis\", \"Gym\", \"Schacht Center\", \"30\", \"Ford Hall\",\n" + 
		        		"\"Menden Hall\", \"Sage Hall\", \"Hubbard\", \"Lawrence\", \"Morris\", \"Tyler\", \"Conference Center\",\n" + 
		        		"\"McConnell\", \"Bass\", \"Burton\", \"Wright\", \"Lyman Conservatory\", \"Chapin\", \"Campus Center\",\n" + 
		        		"\"Res 1\", \"John M. Greene\", \"Dewey\", \"Hatfield\", \"Clark\", \"Museum Of Art\", \"Seelye\", \"Lily\",\n" + 
		        		"\"Pierce\", \"College Hall\", \"Res 2\", \"Alumnae House\")");
		        source = WordUtils.capitalize(input.nextLine());
		        if (!map.sources.contains(source)) {
		        	System.out.println("Please start over and enter an existing source.\n");
		        	continue;
		        }
		        
		        System.out.println("Destination: (Please choose one of the following places: \n\"Indoor Track and Tennis\", \"Gym\", \"Schacht Center\", \"30\", \"Ford Hall\",\n" + 
		        		"\"Menden Hall\", \"Sage Hall\", \"Hubbard\", \"Lawrence\", \"Morris\", \"Tyler\", \"Conference Center\",\n" + 
		        		"\"McConnell\", \"Bass\", \"Burton\", \"Wright\", \"Lyman Conservatory\", \"Chapin\", \"Campus Center\",\n" + 
		        		"\"Res 1\", \"John M. Greene\", \"Dewey\", \"Hatfield\", \"Clark\", \"Museum of Art\", \"Seelye\", \"Lily\",\n" + 
		        		"\"Pierce\", \"College Hall\", \"Res 2\", \"Alumnae House\")");
		        destination = WordUtils.capitalize(input.nextLine());
		        if (!map.sources.contains(destination)) {
		        	System.out.println("Please start over and enter an existing destination.\n");
		        	continue;
		        }
		        break;
		        
	        }
	        int src = map.sources.indexOf(source);
	        int dest = map.sources.indexOf(destination);
	        
	        System.out.println("The path is: ");
	        int[] dist = t.dijkstra(map, src, dest);
	        System.out.println("\nDistance from source to destination = " + dist[dest]);
	    } 
}
