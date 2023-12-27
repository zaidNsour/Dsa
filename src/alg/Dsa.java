
package alg;


import Ds.FlowGraph;
import Ds.Graph;
import Ds.Graph.Edge;
import java.awt.Point;
import java.util.Arrays;



public class Dsa{ 
    
    
   
public static void main(String[] args){
  int[]n1={2,2,2,2};
  int[]n2={2,4,4,2};
  
  ElemantaryMathProblem solver=new ElemantaryMathProblem(n1,n2);
  System.out.println(solver.solve());
  System.out.println(solver.isUniqueAnswers());
  
  
  }

   
         
}                 
 
    

