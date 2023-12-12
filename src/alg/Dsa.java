
package alg;


import Ds.Graph;
import Ds.Graph.Edge;
import java.util.Arrays;







public class Dsa{ 
    
    
   
public static void main(String[] args){
  




Graph graph=new Graph(7,Graph.UN_DIRECTED);
graph.addEdge("0", "1", 9);
graph.addEdge("0", "2", 0);
graph.addEdge("0", "3", 5);
graph.addEdge("0", "5", 7);

graph.addEdge("1", "3", -2);
graph.addEdge("1", "4", 3);
graph.addEdge("1", "6", 4);

graph.addEdge("2", "5", 6);

graph.addEdge("3", "5", 2);
graph.addEdge("3", "6", 3);

graph.addEdge("4", "6", 6);
graph.addEdge("6", "3", 3);

graph.addEdge("5", "6", 1);

Prim solver=new Prim(graph);
Edge[]edges=solver.mst();
for(Edge e:edges)
    System.out.println("src:"+graph.getNode(e.src)+" des:"+graph.getNode(e.des)+" with cost:"+e.cost);
  System.out.println("Total cost:"+solver.minCost());
  














 
 
    
  
    
 
   
   
  
  
    
   }

   
         
}                 
 
    

