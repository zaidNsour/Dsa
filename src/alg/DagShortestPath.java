
package alg;

import Ds.Graph;
import Ds.Graph.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DagShortestPath {
  Graph graph;
  int n;
  List<Integer>order;
  List<Object>path;
  TopologicalSorting solver;
  Integer[]dist;
  int[]parent;
  public void solve(Graph graph,int s){
    this.graph=graph;
    n=graph.size();
    solver=new TopologicalSorting();
    order=solver.solve(graph);
    dist=new Integer[n];
    parent=new int[n];
    
    dist[s]=0;
    parent[s]=-1;
    for(int i=0;i<n;i++){
     int at=order.get(i);
     for(Edge e:graph.getEdges(at)){
        int to=e.des;
        if( dist[to]==null|| dist[at]+e.cost<dist[to]){
          parent[to]=at;
          dist[to]=dist[at]+e.cost;
          }
        }
      }
  }
  
  public List<Object>shortestPath(int s,int e){
    path=new ArrayList<>();
    for(int at=e;at!=-1;at=parent[at])
      path.add(graph.getNode(at));
    Collections.reverse(path);
    return path;
    }
  
  
  
}
