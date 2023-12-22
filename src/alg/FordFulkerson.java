/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alg;

import Ds.FlowGraph;
import Ds.FlowGraph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author DELL
 */
public class FordFulkerson {
  static final double INF=Double.POSITIVE_INFINITY;
  int s,t,n;
  FlowGraph graph;
  boolean[]visited;
  
  public void solve(FlowGraph graph){
    this.graph=graph;
    n=graph.size();
    s=n-2;
    t=n-1;
    visited=new boolean[n];
    
    for(double f=dfs(s,INF);f!=0;f=dfs(s,INF)){
      Arrays.fill(visited,false);
      graph.addFlow(f);
      }
    }
  
  private double dfs(int at,double flow){
    if(at==t)
     return flow;
    visited[at]=true;
    for(Edge e:graph.getEdges(at)){
      int to=e.des;
      if(e.residualCapacity()>0 && !visited[to]){
        double bottleNeck=dfs( to,Math.min( flow,e.residualCapacity() ) );
        if(bottleNeck>0){
          e.addEdgeFlow(bottleNeck);
          return bottleNeck;
          }
        }
      }   
    return 0;   
    } 
}
