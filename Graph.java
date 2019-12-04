package com.ginchen.algo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	/**
	 * Edge class that creates object edge
	 * @author jialinzhang
	 *
	 */
    static class Edge {
    	/**
    	 * weight of each edge
    	 */
    	int weight;
    	
    	/**
    	 * source vertex
    	 */
    	String source;
    	
    	/**
    	 * destination vertex
    	 */
    	String destination;
    	
    	/**
    	 * create object edge
    	 * @param source - source vertex
    	 * @param destination - destination vertex
    	 * @param weight - weight of the source and destination
    	 */
    	public Edge(String source, String destination, int weight) {
    		this.source = source;
    		this.destination = destination;
    		this.weight = weight;
    	}
    	
    }
    
    /**
     * Create object map
     * @author jialinzhang
     *
     */
    static class Map {
    	
    	/**
    	 * adjacency list of a vertex
    	 */
    	LinkedList<Edge> Adj;
    	
    	/**
    	 * a linked list of adjacency lists
    	 */
    	LinkedList<Edge> [] AllEdge;
    	
    	/**
    	 * a list of all vertices
    	 */
    	List<String> sources = (List<String>) Arrays.asList("Indoor Track And Tennis", "Gym", "Schacht Center", "30", "Ford Hall",
    			"Menden Hall", "Sage Hall", "Hubbard", "Lawrence", "Morris", "Tyler", "Conference Center",
    			"McConnell", "Bass", "Burton", "Wright", "Lyman Conservatory", "Chapin", "Campus Center",
    			"Res 1", "John M. Greene", "Dewey", "Hatfield", "Clark", "Museum Of Art", "Seelye", "Lily",
    			"Pierce", "College Hall", "Res 2", "Alumnae House");
    	
    	/**
    	 * creates object map
    	 */
    	public Map() {
    		AllEdge = new LinkedList[sources.size()];
    		for (int i = 0; i < sources.size(); i++) {
    			AllEdge[i] = new LinkedList<Edge>();
    		}
    		
    	}
    	
    	/**
    	 * add edge to an adjacency list
    	 * @param source - source vertex
    	 * @param destination - destination vertex
    	 * @param weight - weight between source and destination
    	 */
    	public void addEdge(String source, String destination, int weight) {
    		Edge newEdge = new Edge(source, destination, weight);
    		int i = 0;
    		// first determine whether the source has already been added in AllEdge
    		while(!AllEdge[i].isEmpty()) {
    			if (AllEdge[i].getFirst().source == source) {
    				AllEdge[i].add(newEdge);
    				return;
    			}
    			i++;
    		}
    		// if not, create a new adj list starts with the new edge
    		AllEdge[i].addFirst(newEdge);
    	}
    	
    	/**
    	 * create a map based on the given graph
    	 */
    	public void createMap() {
    		addEdge("Indoor Track And Tennis", "Gym", 2);
    		addEdge("Indoor Track And Tennis", "30", 4);
    		addEdge("Gym", "Indoor Track And Tennis", 2);
    		addEdge("Gym", "Schacht Center", 2);
    		addEdge("Gym", "Sage Hall", 4);
    		addEdge("Schacht Center", "Gym", 2);
    		addEdge("Schacht Center", "Menden Hall", 3);
    		addEdge("Schacht Center", "30", 2);
    		addEdge("30", "Indoor Track And Tennis", 4);
    		addEdge("30", "Schacht Center", 2);
    		addEdge("30", "Ford Hall", 4);
    		addEdge("Ford Hall", "30", 4);
    		addEdge("Ford Hall", "Menden Hall", 2);
    		addEdge("Ford Hall", "Lawrence", 2);
    		addEdge("Ford Hall", "Hubbard", 5);
    		addEdge("Menden Hall", "Sage Hall", 3);
    		addEdge("Menden Hall", "Schacht Center", 3);
    		addEdge("Menden Hall", "Ford Hall", 2);
    		addEdge("Menden Hall", "Morris", 2);
    		addEdge("Sage Hall", "Menden Hall", 3);
    		addEdge("Sage Hall", "Gym", 4);
    		addEdge("Hubbard", "Lawrence", 5);
    		addEdge("Hubbard", "Seelye", 3);
    		addEdge("Hubbard", "Ford Hall", 5);
    		addEdge("Lawrence", "Morris", 2);
    		addEdge("Lawrence", "Ford Hall", 2);
    		addEdge("Lawrence", "Hubbard", 5);
    		addEdge("Morris", "Tyler", 1);
    		addEdge("Morris", "Lawrence", 2);
    		addEdge("Morris", "McConnell", 2);
    		addEdge("Morris", "Bass", 2);
    		addEdge("Morris", "Menden Hall", 2);
    		addEdge("Tyler", "Conference Center", 3);
    		addEdge("Tyler", "Morris", 1);
    		addEdge("Conference Center", "Tyler", 3);
    		addEdge("Conference Center", "McConnell", 3);
    		addEdge("McConnell", "Conference Center", 3);
    		addEdge("McConnell", "Burton", 1);
    		addEdge("McConnell", "Bass", 1);
    		addEdge("McConnell", "Morris", 2);
    		addEdge("Bass", "McConnell", 1);
    		addEdge("Bass", "Morris", 2);
    		addEdge("Burton", "McConnell", 1);
    		addEdge("Burton", "Wright", 4);
    		addEdge("Wright", "Lyman Conservatory", 5);
    		addEdge("Wright", "Chapin", 3);
    		addEdge("Wright", "Campus Center", 3);
    		addEdge("Wright", "Burton", 4);
    		addEdge("Lyman Conservatory", "Chapin", 3);
    		addEdge("Lyman Conservatory", "Wright", 5);
    		addEdge("Chapin", "Wright", 3);
    		addEdge("Chapin", "Lyman Conservatory", 3);
    		addEdge("Chapin", "Campus Center", 3);
    		addEdge("Chapin", "Res 1", 3);
    		addEdge("Campus Center", "Res 1", 3);
    		addEdge("Campus Center", "Chapin", 3);
    		addEdge("Campus Center", "Wright", 3);
    		addEdge("Campus Center", "John M. Greene", 2);
    		addEdge("Res 1", "Chapin", 3);
    		addEdge("Res 1", "Campus Center", 3);
    		addEdge("John M. Greene", "Campus Center", 2);
    		addEdge("John M. Greene", "Dewey", 2);
    		addEdge("Dewey", "John M. Greene", 2);
    		addEdge("Dewey", "Clark", 2);
    		addEdge("Dewey", "Hatfield", 1);
    		addEdge("Dewey", "Museum Of Art", 6);
    		addEdge("Hatfield", "Dewey", 1);
    		addEdge("Hatfield", "Seelye", 7);
    		addEdge("Clark", "Dewey", 2);
    		addEdge("Clark", "Museum Of Art", 5);
    		addEdge("Clark", "Res 2", 3);
    		addEdge("Museum Of Art", "Dewey", 6);
    		addEdge("Museum Of Art", "Clark", 5);
    		addEdge("Museum Of Art", "Seelye", 4);
    		addEdge("Museum Of Art", "College Hall", 6);
    		addEdge("Museum Of Art", "Alumnae House", 3);
    		addEdge("Seelye", "Hatfield", 7);
    		addEdge("Seelye", "Hubbard", 3);
    		addEdge("Seelye", "Lily", 3);
    		addEdge("Seelye", "Museum Of Art", 4);
    		addEdge("Lily","Pierce", 1);
    		addEdge("Lily","Seelye", 3);
			addEdge("Pierce", "College Hall", 3);
			addEdge("Pierce", "Lily", 1);
			addEdge("College Hall", "Museum Of Art", 6);
			addEdge("College Hall", "Pierce", 3);
			addEdge("Res 2", "Clark", 3);
			addEdge("Res 2", "Alumnae House", 5);
			addEdge("Alumnae House", "Museum Of Art", 3);
			addEdge("Alumnae House", "Res 2", 5);	
    	}
    	
    	/**
    	 * print all edges and corresponding weights
    	 */
    	public void print() {
    		for (int i = 0; i<AllEdge.length; i++) {
    			LinkedList<Edge> list = AllEdge[i];
    			for (int j = 0; j<list.size(); j++) {
    				System.out.println("Source: " + list.get(j).source + " Dest: " + list.get(j).destination + 
    						" Weight: " + list.get(j).weight + "\n");
    			}
    		}
    	}
    	
    }
	
}
