## JobSequencing

It was given as mini project of Design and Analysis of Algorithms to sequence the jobs.


Give the input through txt file in - src/input.txt

Input format Example -
  
  q => a
  
  d => e
  
  b => c
  
  b => a
  
  c => a 


Jobs are sorted by building a Directed Acyclic Graph and after that doing a Topological Sort to get the jobs in sorted seqeunce.
 
 Steps followed -
 
 1 - Create classes Edge, Vertex which are the attributes of graph. 
 
 2 - Creata a class Graph with Edges and Vertcies as data memebers.
 
 3 - Read the input from the file, Parse it and build the Directed Acyclic Graph with Vertices and Edges.
 
 4 - Do a topolical sort on the Directed Acyclic Graph to get the results in sorted order.