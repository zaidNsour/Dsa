
package alg;


import Ds.FlowGraph;
import Ds.Graph;
import Ds.Graph.Edge;
import java.awt.Point;
import java.util.Arrays;



public class Dsa{ 
    
    
   
public static void main(String[] args){

Point m1=new Point(1,3);
Point m2=new Point(1,4); 
Point m3=new Point(1,6); 
Point m4=new Point(1,8); 

Point h1=new Point(1,0);
Point h2=new Point(2,5);

int[]holesCapacity={2,2};

Point[]mice={m1,m2,m3,m4};
Point[]holes={h1,h2};

MiceOwlProblem solver=new MiceOwlProblem(4,mice,holes,holesCapacity);
solver.solve();
solver.graph.printGraph();
  
 
  
    
  }

   
         
}                 
 
    

