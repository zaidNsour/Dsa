/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alg;

import Ds.WeightedGraph;
import Ds.WeightedGraph.Edge;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class EulerianPathDirectedEdges {
    
  WeightedGraph graph; 
  private  int n;
  private int edgeCount;
  private int[] in,out;
  private LinkedList<Integer>path;
  
  
  EulerianPathDirectedEdges(WeightedGraph graph){
  if(graph==null)
      throw new IllegalArgumentException("graph cannot be null");
    n=graph.size();
    this.graph=graph;
    path=new LinkedList();     
     
    }
    
    public List<Integer> getEulerianPath(){  
    findInOutDegree();
    if(edgeCount==0) return null;
    if(!graphHasEulerianPath()) return null; 
    dfs(findStartNode());
  
    if(path.size()!=edgeCount+1)
        return null; 
    
    return path;
    }
    
    
   private void findInOutDegree(){
    in=new int[n];
    out=new int[n]; 
    edgeCount=0;
    LinkedList<Edge>[]edges=graph.getEdges();
    for(int i=0;i<n;i++)
       for(Edge edge:edges[i]){
         out[edge.src]++;
         in[edge.des]++;
         edgeCount++;
       }
    }
  
   boolean graphHasEulerianPath(){
  int start_nodes=0,end_nodes=0;     
  for(int i=0;i<n;i++){
       if(in[i]-out[i]>1 || out[i]-in[i]>1)
         return false;
       else if(in[i]-out[i]==1)
          end_nodes++;
       else if(out[i]-in[i]==1)
           start_nodes++;
      }
  return((end_nodes==0 &&start_nodes==0) || (end_nodes==1&&start_nodes==1));   
  }
   
   private int findStartNode(){
   int start=0;   
   for(int i=0;i<n;i++){
     if(out[i]-in[i]==1)
        return i;
     if(out[i]>0)
        start=i;
   }
   return start;
  }
   
   
   private void dfs(int node){
       
   while(out[node]!=0){
       
      Edge next_edge=graph.getEdge(node,--out[node]); 
      
       dfs(next_edge.des);
     }
   path.addFirst(node);
   } 
    
    
    
}
