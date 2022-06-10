package com.pckg.jobsequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Vertex holds the job, edges connecting child vertices and adjacentVertex list holds the child vertices 
 * @author Dhamodharan
 *
 * @param <T>
 */
public class Vertex<T> {
	
	long id;
	private T data;
	private List<Edge<T>> edges = new ArrayList<>();
    private List<Vertex<T>> adjacentVertex = new ArrayList<>();
    
    Vertex(long id)
    {
    	this.id =id;
    }
       
	public long getId() {
		return id;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<Edge<T>> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge<T>> edges) {
		this.edges = edges;
	}

	/**
	 * @param e
	 * @param v
	 */
	public void addAdjacentVertex(Edge<T> e, Vertex<T> v)
	{
		edges.add(e);
		adjacentVertex.add(v);
	}

	public List<Vertex<T>> getAdjacentVertex() {
		return adjacentVertex;
	}

	public void setAdjacentVertex(List<Vertex<T>> adjacentVertex) {
		this.adjacentVertex = adjacentVertex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertex [data=" + data + "]";
	}
    
	
	
    
}

