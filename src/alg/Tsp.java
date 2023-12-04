
package alg;

import Ds.WeightedGraph;
import java.util.List;


public class Tsp {
double[][]adjMatrix;
int n;
int s;
List<Integer> set;
List<Integer>tour;
int tourCost;
boolean solved=false;

Tsp(WeightedGraph graph,int start){
  this(graph.convertListToMatrix(),start);
}

Tsp(double[][]adjMatrix,int start){
   this.adjMatrix=adjMatrix;
   this.s=start;
   n=adjMatrix.length;
   
}

public List<Integer>getTour(){
if(!solved)
  solve();
return tour;
}

public double getTourCost(){
if(!solved)
  solve();
return tourCost;
}

private void solve(){
 for(int i=0;i<n;i++)
    if(i!=s)
      set.add(i);
 solve(s,set);
       
}

private void solve(int start,List<Integer> set){
for(int i=0;i<set.size();i++){
    
    
  }
}

    
    
    
}
