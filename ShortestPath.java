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
	// adj list of a vertex
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
		void printMatrix(Map map, int[] dist) {
			for (int i=0;i<V;i++) {
				System.out.println(map.sources.get(i) + " " + dist[i]);
			}
		}
		
		/**
		 * find shortest path between each vertex and the source vertex (s)
		 * if we have already reached the destination, stop the program
		 * @param map - map representation of the graph
		 * @param s - the source vertex index in the sources list
		 * @param d - the destination vertex index in the source list
		 * @return - a list of shortest distance
		 */
		int[] dijkstra(Map map, int s, int d) {
			// dist is the output array; it contains the shortest path cost from the vertex to every other vertex
			// initialize dist
			int[] dist = new int[V];
			
			// inQ[i] is false if vertex i is included in the path
			Boolean inQ[] = new Boolean[V];
			
			// the parent index of vertices
			int[] parent = new int[V];
			
			// initialize all dist to infinite and all inQ to true
			for (int i=0;i<V;i++) {
				dist[i] = Integer.MAX_VALUE;
				inQ[i] = true;
			}
			
			// the distance between the source vertex and itself is zero
			dist[s] = 0;
			int v = 0;
			for (int i=0;i<V-1;i++) {
				int u = extractMin(dist, inQ);
				// if we've extracted the destination vertex
				// break the for loop
				if (map.sources.get(u).equals(map.sources.get(d))) {
					break;
				}
				inQ[u] = false;
				// adj list of vertex at index u in the sources list
				adj = map.AllEdge[u];
				
				// for all vertices in adj[u]
				for (int y = 0; y<adj.size(); y++) {
					// update dist[v] only:
					/*
					 * v inQ
					 * weight between sources[u] and sources[v] != 0 
					 * parent vertex is reachable from source vertex (dist[u] != infinity)
					 * the new cost is smaller than the previous cost
					 */
					v = map.sources.indexOf(adj.get(y).destination);
					if(inQ[v] && adj.get(y).weight!=0 && dist[u]!=Integer.MAX_VALUE && dist[u]+adj.get(y).weight<dist[v]) {
						dist[v] = dist[u]+adj.get(y).weight;
						parent[v] = u;
					}
				}
			}
			
			// print path from source to destination using parent index
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
		
		public static void main(String[] args) { 
	        
			String source = null;
			String destination = null;
			Scanner input = new Scanner(System.in);
			
			Map map = new Map();
	        map.createMap();
	        ShortestPath t = new ShortestPath();
	        
	        /**
	         * verify if the input is valid each time
	         */
	        while (true) {
	        	System.out.println("Source: (Please choose one of the following places: \n\"Indoor Track And Tennis\", \"Gym\", \"Schacht Center\", \"30\", \"Ford Hall\",\n" + 
		        		"\"Menden Hall\", \"Sage Hall\", \"Hubbard\", \"Lawrence\", \"Morris\", \"Tyler\", \"Conference Center\",\n" + 
		        		"\"McConnell\", \"Bass\", \"Burton\", \"Wright\", \"Lyman Conservatory\", \"Chapin\", \"Campus Center\",\n" + 
		        		"\"Res 1\", \"John M. Greene\", \"Dewey\", \"Hatfield\", \"Clark\", \"Museum Of Art\", \"Seelye\", \"Lily\",\n" + 
		        		"\"Pierce\", \"College Hall\", \"Res 2\", \"Alumnae House\")");
		        // the program shouldn't be case sensitive
	        	source = WordUtils.capitalize(input.nextLine());
		        if (!map.sources.contains(source)) {
		        	System.out.println("Please start over and enter an existing source.\n");
		        	continue;
		        }
		        
		        System.out.println("Destination: (Please choose one of the following places: \n\"Indoor Track And Tennis\", \"Gym\", \"Schacht Center\", \"30\", \"Ford Hall\",\n" + 
		        		"\"Menden Hall\", \"Sage Hall\", \"Hubbard\", \"Lawrence\", \"Morris\", \"Tyler\", \"Conference Center\",\n" + 
		        		"\"McConnell\", \"Bass\", \"Burton\", \"Wright\", \"Lyman Conservatory\", \"Chapin\", \"Campus Center\",\n" + 
		        		"\"Res 1\", \"John M. Greene\", \"Dewey\", \"Hatfield\", \"Clark\", \"Museum Of Art\", \"Seelye\", \"Lily\",\n" + 
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
