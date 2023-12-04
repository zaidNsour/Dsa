
package alg;

import Ds.WeightedGraph;
import Ds.MinIndexedDHeap;
import Ds.WeightedGraph.Edge;
import java.util.LinkedList;


public class Prims {
private WeightedGraph graph;
private int n;
private MinIndexedDHeap ipq;
private boolean[]visited;
private Edge[]mst;
private int minCost;
private int numEdges;
private boolean solved;
 
 Prims(WeightedGraph graph){
 this.graph=graph;
 n=graph.size();
 solved=false;
 }
 
 public Edge[]minSpanningTreeEdges(){
 mst=new Edge[n-1];
 minCost=0;
 numEdges=0;
 visited=new boolean[n];
 ipq=new MinIndexedDHeap(2,n);
 
 relaxEdges(0);
 
 while(!ipq.isEmpty() && numEdges!=n-1){ 
 Edge minEdge=(Edge)ipq.peekMinValue();    
 int i=ipq.pollMinKeyIndex();
 
 mst[numEdges++]=minEdge;
 minCost+=minEdge.cost;
 relaxEdges(i);
 }
 if(numEdges!=n-1)
   return null;
    
 
 solved=true;
 return mst;
 
 }
 
 public int minSpanningTreeCost(){
 if(!solved)
   minSpanningTreeEdges();
 return minCost;
 }
 

 
 private void relaxEdges(int node){
 visited[node]=true;
 LinkedList<Edge>edges=graph.getEdges()[node];
 for(Edge e:edges){
 if(visited[e.des])
   continue;
 
 if(!ipq.contains(e.des))
   ipq.insert(e.des, e);  
 else
   ipq.decrease(e.des, e);
 }
}
 
 
    
    
}
