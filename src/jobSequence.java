package com.pckg.jobsequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Deque;

/**
 * @author Dhamodharan
 * Main Method to perform job Sequencing based on the dependency
 */
public class jobSequence {
	
	
	public static void main(String args[]) throws Exception
	{
		//Read the input from file
		File file = new File("src/input.txt");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String readLine="";
		
		try
		{
			StringBuilder builder = new StringBuilder();
			while((readLine = bufferedReader.readLine())!=null)
			{
				builder.append(readLine);
				builder.append(",");
			}
			//method to perform job Sequencing
			getJobSequence(builder.toString());
			bufferedReader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
	}
	
	public static void getJobSequence(String jobSequence) throws Exception
	{
		//Initialise a Graph
		Graph<String> graph = new Graph<String>();
		String[] tokens= jobSequence.split(",");
		
		long vertex1Indx;
		long index =1;
		
		//Read the tokens and split it based on delimiter and loop through each job to build the graph
		for (String input : tokens)
		{
			vertex1Indx = 0;
			String[] splited = input.split("=>");
			
			for (int i=0; i < splited.length; i++)
			{				
				if (i==0)
				{
					if(splited[0].trim().length()>0)
					{
						//check whether vertex already created
						long id = graph.isVertexCreated(splited[0].trim());
						if(id ==0)
						{
							Vertex<String> vertex = new Vertex<String>(index);
							vertex.setData(splited[0].trim());
							vertex1Indx = index;
							index+=1;
							graph.addVertex(vertex);
						}
						else
						{
							vertex1Indx = id;
						}
						
					}
				}
				else
				{
					if(splited[i].trim().length() >0)
					{
						
					    long id = graph.isVertexCreated(splited[i].trim());
					    if(id==0)
							{
							Vertex<String> vertex = new Vertex<String>(index);
							vertex.setData(splited[i].trim());
							index+=1;
							graph.addVertex(vertex);
							//Add edge from the parent vertex to child
							graph.addEdge( vertex.getId(),vertex1Indx);
							}
					    else
					    {					    	
					    	if (vertex1Indx == id)
					    	{
					    		throw new JobSequenceException("Input Error - Jobs Cannot Depend on themself's");
					    	}					  
					    	//Check whether cyclic dependency exist on the jobs and throw exception if cyclic dependency exists
					    	graph.checkCyclicDependencyUtil(graph.getVertex(vertex1Indx), graph.getVertex(id));
					    	graph.addEdge(id,vertex1Indx);
					    }
					}
     			}
				
			}
			
		}
		
		TopologicalSort<String> sort  = new TopologicalSort<String>();
		//Pass the graph to topological sort
		Deque<Vertex<String>> result = sort.topologicalSort(graph);
		
		while(!result.isEmpty()) {
			System.out.print(result.poll().getData());
		}
		
	}

}
