
package alg;

import Ds.Graph;
import Ds.MinIndexedDHeap;
import Ds.Graph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DijkstrasShortestPath {
  
private  Graph graph;
private  int n,m;
private  double[] dist;
private  int[]parent;
private  boolean[]visited;



public double[] dijkstras(Graph graph,int s){
m=graph.edgesCount();
n=graph.size();
dist=new double[n];
parent=new int[n];
visited=new boolean[n]; 
int degree=m/n;
MinIndexedDHeap<Double> ipq=new MinIndexedDHeap<>(degree,n);
ipq.insert(s,0.0);
Arrays.fill(dist,Double.POSITIVE_INFINITY);
dist[s]=0;
parent[s]=-1;

while(!ipq.isEmpty()){
  int at=ipq.peekMinKeyIndex(); 
  visited[at]=true;
  double minVal=ipq.pollMinValue();
  if(minVal>dist[at]) continue;
  
  for(Edge e:graph.getEdges(at)){
    int to=e.des;
    if(visited[to]) continue;
    double newDist=dist[at]+e.cost;
    if(newDist<dist[to]){
      dist[to]=newDist;
      parent[to]=at;
  
    if(!ipq.contains(to))
      ipq.insert(to, newDist);
    else
      ipq.decrease(to, newDist);
            
     }
   }
  
 }
 return dist;
}

public List<Object> reconstructPath(Graph graph,int s,int e){
if(s<0 || s>graph.size())
    throw new IllegalArgumentException("Invalid node index");
if(e<0 || e>graph.size())
    throw new IllegalArgumentException("Invalid node index");

List<Object>path=new ArrayList<>();
for(int at=e;at!=-1;at=parent[at])
    path.add( graph.getNode(at) );
Collections.reverse(path);
return path;
}

    
    
}
