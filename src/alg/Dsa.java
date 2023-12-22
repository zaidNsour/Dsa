
package alg;


import Ds.FlowGraph;
import Ds.Graph;
import Ds.Graph.Edge;
import java.util.Arrays;



public class Dsa{ 
    
    
   
public static void main(String[] args){
  
  FlowGraph graph=new FlowGraph(6);
  int s=4,t=5;
  graph.addEdge(s, 0, 10);
  graph.addEdge(s, 2, 10);
  graph.addEdge(0, 1, 4);
  graph.addEdge(0, 2, 2);
  graph.addEdge(0, 3, 8);
  graph.addEdge(1, t, 10);
  graph.addEdge(2, 3, 9);
  graph.addEdge(3, 1, 6);
  graph.addEdge(3, t, 10);
 
  FordFulkerson solver=new FordFulkerson();
  solver.solve(graph);
  graph.printGraph();
 
  
    
  }

   
         
}                 
 
    

