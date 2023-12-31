
package alg;


import Ds.FlowGraph;
import Ds.Graph;
import Ds.Graph.Edge;
import static alg.BitManipulation.highestPowerof2;
import static alg.BitManipulation.numberGreaterByOneBit;
import java.awt.Point;
import java.util.Arrays;
import java.util.Collections;



public class Dsa{ 
    
    
   
public static void main(String[] args){
  FlowGraph graph=new FlowGraph(11);
  int s=9,t=10;
  graph.addEdge(s, 0, 5);
  graph.addEdge(s, 1, 10);
  graph.addEdge(s, 2, 5);
  graph.addEdge(0, 3, 10);
  graph.addEdge(1, 0, 15);
  graph.addEdge(1, 4, 20);
  graph.addEdge(2, 5, 5);
  graph.addEdge(3, 6, 10);
  graph.addEdge(3, 4, 25);
  graph.addEdge(4, 2, 5);
  graph.addEdge(4, 7, 10);
  graph.addEdge(5, 7, 5);
  graph.addEdge(6, t, 5);
  graph.addEdge(7, 3, 15);
  graph.addEdge(7, 8, 5);
  graph.addEdge(7, t, 15);
  graph.addEdge(8, t, 5);
  
  FlowGraph graph2=new FlowGraph(11);
  graph2.addEdge(s, 0, 5);
  graph2.addEdge(s, 1, 10);
  graph2.addEdge(s, 2, 5);
  graph2.addEdge(0, 3, 10);
  graph2.addEdge(1, 0, 15);
  graph2.addEdge(1, 4, 20);
  graph2.addEdge(2, 5, 5);
  graph2.addEdge(3, 6, 10);
  graph2.addEdge(3, 4, 25);
  graph2.addEdge(4, 2, 5);
  graph2.addEdge(4, 7, 10);
  graph2.addEdge(5, 7, 5);
  graph2.addEdge(6, t, 5);
  graph2.addEdge(7, 3, 15);
  graph2.addEdge(7, 8, 5);
  graph2.addEdge(7, t, 15);
  graph2.addEdge(8, t, 5);
  
  

  EdmondsKarp solver=new EdmondsKarp(graph,true);
  solver.getMaxFlow();
  solver.graph.printGraph();
  System.out.println("");
  
 EdmondsKarp solver2=new EdmondsKarp(graph2);
 solver2.getMaxFlow();
  solver2.graph.printGraph();
 
  
  

  
  }
        
}                 
 
    

