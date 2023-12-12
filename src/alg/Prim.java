
package alg;

import Ds.Graph;
import Ds.Graph.Edge;
import Ds.MinIndexedDHeap;


public class Prim {
  Graph graph;
  int n,edgeCount,minCost;
  boolean visited[];
  MinIndexedDHeap<Edge> ipq;
  Edge[]mst;
  boolean solved;
  
  Prim(Graph graph){
    this.graph=graph;
    solved=false;
    }
  
  public Edge[] mst(){
    if(!solved)
      solver();
    return mst; 
    }
  public int minCost(){
    if(!solved)
      solver();
    return minCost;
    }
  
  private void solver(){
    n=graph.size();
    edgeCount=0;
    minCost=0;
    visited=new boolean[n];
    mst=new Edge[n-1];
    ipq=new MinIndexedDHeap<>(2,n);
    relaxEdges(0);
    while(!ipq.isEmpty() && edgeCount!=n-1){
      int to=ipq.peekMinKeyIndex();
      Edge e=ipq.pollMinValue();
      mst[edgeCount++]=e;
      minCost+=e.cost;
      relaxEdges(to);
      }
    if( edgeCount!=(n-1) ){
      mst=null;
      minCost=0;
      }  
    solved=true;
    
    }
  
  private void relaxEdges(int at){
    visited[at]=true;
    for(Edge e:graph.getEdges(at)){
      int to=e.des;
      if(visited[to])
        continue;
      if(!ipq.contains(to))
        ipq.insert(to,e);
      else
        ipq.decrease(to, e);
        
      }
    
    }
  
  
  
  
  
}
