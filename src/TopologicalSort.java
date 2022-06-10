package com.pckg.jobsequence;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Dhamodharan
 *
 * 
 * Given a Directed Acyclic Graph, do a topologicalSort on this Graph
 *
 * Do Depth First Search by visiting each vertex. Put the vertex which are completely explored in a stack
 * Pop from the stack to get the sorted order 
 */

public class TopologicalSort<T> {
	
	/**
	 * Main method to be invoked to do topological sorting.
	 */
	public Deque<Vertex<T>> topologicalSort(Graph<T> graph)
	{
		//Build a stack to store the sorted jobs
		Deque<Vertex<T>> stack = new ArrayDeque<>();
		Set<Vertex<T>> visited = new HashSet<>();
		
		for (Vertex<T> vertex :  graph.getAllVertex())
		{
			if(visited.contains(vertex))
			{
			continue;	
			}
			
			topSortUtil(vertex,stack,visited);
		}
		
		return stack;
		
	}
		
	private void topSortUtil(Vertex<T> vertex, Deque<Vertex<T>> stack, Set<Vertex<T>> visited)
	{
		visited.add(vertex);
		for(Vertex<T> childVertex : vertex.getAdjacentVertex())
		{
			if(visited.contains(childVertex))
			{
				continue;
			}
			topSortUtil(childVertex,stack,visited);
		}
		stack.offerFirst(vertex);
	}
	
	

}
