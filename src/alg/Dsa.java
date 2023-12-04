
package alg;


import Ds.Graph;
import Ds.Graph.Edge;
import java.util.Arrays;







public class Dsa{ 
    
    
   
public static void main(String[] args){
  




Graph graph=new Graph(5,Graph.UN_DIRECTED);
graph.addEdge("0", "1", 5);
//graph.addEdge("0", "2", 3);
graph.addEdge("1", "2", 10);
graph.addEdge("2", "3", 1);
graph.addEdge("2", "4", 2);
graph.addEdge("3", "4", 3);
Bridge solver=new Bridge();
solver.solve(graph);
for(Edge e:solver.getBridges())
    System.out.println("src: "+graph.getNode(e.src)+" des: "+graph.getNode(e.des));
  System.out.println(solver.getArticulationPoint());
  
   System.out.println(Arrays.toString(solver.ids));
  System.out.println(Arrays.toString(solver.low));








 
 
    
  
  

  
  
  
  
  
    
    
 
   
   
  
  
    
   }

   
         
}                 
 
    

