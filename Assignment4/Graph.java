package edu.csus.csc130.fall2017.assignment4;

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * This Graph implementation maintains a vertex-indexed array of lists of integers. 
 * Every edge appears twice: if an edge connects v and w, then w appears in v’s list and v appears in w’s list. 
 */
public class Graph {
	private int V = 0; // number of vertices
	private int E; // number of edges
	private List<Integer>[] adj; // adjacency lists
	
	//create a V-vertex graph with no edges
	@SuppressWarnings("unchecked")
	public Graph(int V)  {
		this.V = V; 
		this.E = 0;
		adj = new LinkedList[V]; // Create array of lists.
		// Initialize all lists to empty
		for (int v = 0; v < V; v++) {
			adj[v] = new LinkedList<Integer>(); 
		}
	}
	
	/**
	 * reads a graph from an input file, in the format V followed by E followed by a list of 
	 * pairs of int values between 0 and V-1.
	 */
	@SuppressWarnings("unchecked")
	public Graph(String inFileName) {
		Scanner sc = null;
		try {
			URL url = Graph.class.getResource(inFileName);
			sc = new Scanner(new File(url.getPath()));
			int numVertices = sc.nextInt();
			int numEdges = sc.nextInt();
			
			this.V = numVertices; 
			adj = new LinkedList[V]; // Create array of lists.
			// Initialize all lists to empty
			for (int v = 0; v < V; v++) {
				adj[v] = new LinkedList<Integer>(); 
			}
			
			for (int i=0; i<numEdges; i++) {
				int vertexA = sc.nextInt();
				int vertexB = sc.nextInt();
				addEdge(vertexA, vertexB);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}
	
	//return number of vertices
	public int V() {
		return V;
	}
	
	//return number of edges
	public int E() {
		return E;
	}
	
	//add edge v-w to this graph
	public void addEdge(int v, int w) {
		adj[v].add(0, w);
		adj[w].add(0, v);
		E++;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(V + " vertices, " + E + " edges\n");
		for (int v = 0; v < V; v++) {
			sb.append(v + ": ");
			for (int w : this.adj(v)) {
				sb.append(w + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	//vertices adjacent to v
	public Iterable<Integer> adj(int v)  {
		return adj[v];
	}
	
	@SuppressWarnings("unused")
	public static int degree(Graph G, int v) {
		int degree = 0;
		for (int w : G.adj(v)) {
			degree++;
		}
		return degree;
	}

	public static int maxDegree(Graph G) {
		int max = 0;
		for (int v = 0; v < G.V(); v++) {
			int degree = degree(G, v);
			if (degree > max) max = degree;
		}
		return max;
	}

	public static int avgDegree(Graph G) {
		return 2 * G.E() / G.V();
	}

	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int v = 0; v < G.V(); v++)
			for (int w : G.adj(v))
				if (v == w)
					count++;
		return count / 2; 
	}	
}
