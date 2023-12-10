
package alg;


import Ds.Graph;
import Ds.Graph.Edge;
import java.util.Arrays;







public class Dsa{ 
    
    
   
public static void main(String[] args){
  




Graph graph=new Graph(8,Graph.DIRECTED);
graph.addEdge("1", "2", 5);
graph.addEdge("1", "3", 3);
graph.addEdge("2", "2", 10);
graph.addEdge("2", "4", 1);
graph.addEdge("2", "4", 2);
graph.addEdge("3", "1", 3);
graph.addEdge("3", "2", 5);
graph.addEdge("3", "5", 3);
graph.addEdge("4", "3", 10);
graph.addEdge("4", "6", 1);
graph.addEdge("5", "6", 2);
graph.addEdge("6", "3", 3);

EulerianPath solver=new EulerianPath();

for(int at:solver.findEulerianPath(graph))
    System.out.print(","+graph.getNode(at));









 
 
    
  
    
 
   
   
  
  
    
   }

   
         
}                 
 
    

