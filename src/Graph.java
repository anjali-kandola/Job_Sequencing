package com.pckg.jobsequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Directed Acyclic Graph is build based on the dependencies on the jobs
 * Each Job is created as Vertex and an edge is added from the parent to child jobs
 * @author Dhamodharan
 *
 * @param <T>
 */
public class Graph<T> {
	
	private List<Edge<T>> allEdges;
	private Map<Long,Vertex<T>> allVertex;
	
	public Graph()
	{
		allEdges = new ArrayList<Edge<T>>();
		allVertex = new HashMap<Long,Vertex<T>>();
	}
	
	/**
	 * @param id1
	 * @param id2
	 */
	public void addEdge(long id1, long id2)
	{
		//Get the Vertex1 if already Present or Create a new Vertex
		Vertex<T> vertex1 = null;
		
		if(allVertex.containsKey(id1))
		{
			vertex1 = allVertex.get(id1);
		}
		else
		{
			vertex1 = new Vertex<T>(id1);
			allVertex.put(id1, vertex1);
		}
		
		//Get the Vertex2 if already Present or Create a new Vertex
		Vertex<T> vertex2 = null;
		
		if(allVertex.containsKey(id2))
		{
			vertex2 = allVertex.get(id2);
		}
		else
		{
			vertex2 = new Vertex<T>(id2);
			allVertex.put(id2, vertex2);
		}
		
		Edge<T> edge = new Edge<T>(vertex1, vertex2);
		allEdges.add(edge);
		//add the edge and adjacent vertex to Parent vertex
		vertex1.addAdjacentVertex(edge, vertex2);
	}
	
	
	/**
	 * @param value
	 * @return
	 * Method to check whether vertex is  already created.
	 */
	public long isVertexCreated(String value)
	{
		for (Vertex<T> vertex1 : allVertex.values())
		{
			if (vertex1.getData().equals(value))
			{
				return vertex1.getId() ;
			}
		}
		return 0;
		
	}
	
	
	/**
	 * Method to check whether cyclic dependency exist on the input
	 * Recursively read all the edges of input parameter vertex to check whether vertex1 is already added as child of vertex
	 * @param vertex
	 * @param vertex1
	 * @throws Exception
	 */
	public void checkCyclicDependencyUtil(Vertex<T> vertex, Vertex<T> vertex1) throws Exception
	{
		for (Edge<T> edge : vertex.getEdges())
		{			
			if (edge.getVertex2().getId() == vertex1.getId())
			{
				throw new Exception("Input Error -> Cyclic Dependency Exists");
			}
			checkCyclicDependencyUtil(edge.getVertex2(),vertex1);
		}
	}
	
	/**
	 * @param vertex
	 */
	public void addVertex(Vertex<T> vertex)
	{
		for (Vertex<T> vertex1 : allVertex.values())
		{
			if (vertex1.getData().equals(vertex.getData()))
			{
				return ;
			}
		}
		
		allVertex.put(vertex.getId(), vertex);
		for(Edge<T> edge: vertex.getEdges())
		{
			allEdges.add(edge);
		}
	}
	
	public Vertex<T> getVertex(long id)
	{
	   return allVertex.get(id);	
	}
	
	public List<Edge<T>> getAllEdges()
	{
		return allEdges;
	}

	public Collection<Vertex<T>> getAllVertex()
	{
		return allVertex.values();
	}
	
	/**
	 * @param id
	 * @param data
	 */
	public void setDataForVertex(long id, T data)
	{
		if (allVertex.containsKey(id))
		{
			Vertex<T> vertex = allVertex.get(id);
			vertex.setData(data);
		}
	}
}
