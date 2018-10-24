package edu.csus.csc130.fall2017.assignment4;

import java.util.Stack;
import java.util.Iterator;

public class DepthFirstSearchStackImpl {
	//to mark vertices that are connected to the source
	private boolean[] marked;
	//the number of marked vertices
	private int count;

	public DepthFirstSearchStackImpl(Graph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}

	/**
	 * Modified by : Oscar Esparza
	 * Implement this method use stack instead of recursion
	 * java.util.Stack can be used
	 */
	private void dfs(Graph G, int v) {
		@SuppressWarnings("unchecked")
		Iterator<Integer>[] adj = (Iterator[]) new Iterator[G.V()];
		Stack<Integer> dfs = new Stack<Integer>();
		marked = new boolean[G.V()];
		marked[v] = true;
		
		for(int i = 0; i < G.V(); i++)
			adj[i] = G.adj(i).iterator();
		
		dfs.push(v);
		
		while (!(dfs.isEmpty())){
			int i = dfs.peek();
			
			if(adj[i].hasNext()) {
				int j = adj[i].next();
				
				//check if marked, if not then mark it
				if(!marked[j]) {
					marked[j] = true;
					dfs.push(j);
				}
			}
			else // no adj
				dfs.pop();
		}
		
	}

	public boolean marked(int w) {
		return marked[w];
	}

	public int count() {
		return count;
	}	
}
