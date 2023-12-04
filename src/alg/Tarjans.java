
package alg;

import Ds.WeightedGraph;
import Ds.HashTable;
import Ds.WeightedGraph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;


public class Tarjans {
    
  final int UNVISITED=-1;
  int sccCount,n,id;
  boolean solved;
  int[]ids,lows;
  boolean[]onStack;
  Stack<Integer> stack=new Stack();
  WeightedGraph graph;
  HashTable table=new HashTable(n);
  
  public Tarjans(WeightedGraph graph){
  if(graph==null)
    throw new IllegalArgumentException("null graph");    
  this.graph=graph;
  this.n=graph.size();
  }
 
  
public void findSccs(){
  id=0;   
  sccCount=0;
  solved=false;
  ids=new int[n]; 
  lows=new int[n];
  onStack=new boolean[n];
  Arrays.fill(ids,UNVISITED);
  for(int i=0;i<n;i++){
     if(ids[i]==UNVISITED){
       dfs(i);
     solved=true;  
     }
   }
  
  for(int i=0;i<n;i++){
  table.insert(ids[i],lows[i]);
  }
  
     
}


 
 private void dfs(int at){
 ids[at]=lows[at]=id++;
 stack.push(at);
 onStack[at]=true;
 LinkedList<Edge> edges=graph.getEdges()[at];
 for(Edge edge:edges){
    int to=edge.des;
    if(ids[to]==UNVISITED)
      dfs(to);
    if(onStack[to])
      lows[at]=Math.min(lows[at],lows[to]); 
  }
 if(ids[at]==lows[at]){
   for(int node=stack.pop();;node=stack.pop() ){
      onStack[node]=false;
      lows[node]=ids[at];
      if(node==at)
        break;
   }
   sccCount++;
  }
 
 }
 
 public void printComponents(){
 if(solved==false)
   findSccs();
     System.out.println(table.toString());
 }
 
  
}    

