
package alg;


import Ds.Graph;
import Ds.Graph.Edge;
import java.util.Arrays;







public class Dsa{ 
    
    
   
public static void main(String[] args){
  




Graph graph=new Graph(8,Graph.DIRECTED);
graph.addEdge("a", "b", 5);
graph.addEdge("a", "c", 3);
graph.addEdge("b", "a", 10);
graph.addEdge("b", "d", 1);
graph.addEdge("c", "d", 2);
graph.addEdge("d", "e", 3);
graph.addEdge("d", "f", 5);
graph.addEdge("e", "c", 3);
graph.addEdge("e", "f", 10);
graph.addEdge("e", "g", 1);
graph.addEdge("f", "i", 2);
graph.addEdge("g", "f", 3);
graph.addEdge("i", "g", 3);

Tarjans solver=new Tarjans(); 
int[]low=solver.findSccs(graph);
for(int i=0;i<graph.size();i++)
    System.out.println("node "+graph.getNode(i) +" component: "+low[i]);
  System.out.println(solver.getSccsCount());





 
 
    
  
    
 
   
   
  
  
    
   }

   
         
}                 
 
    

