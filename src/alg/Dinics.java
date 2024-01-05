
package alg;

import Ds.FlowGraph;
import Ds.FlowGraph.Edge;
import java.util.Arrays;

import java.util.LinkedList;

import java.util.Queue;

/**
 *
 * @author DELL
 */
public class Dinics {
 private static final double INF=Double.POSITIVE_INFINITY; 
 private int n,s,t;
 double maxFlow;
 public FlowGraph graph;
 private Queue<Integer>queue;
 private int[]levels;
 private int[]next;
  
  Dinics(FlowGraph graph){
    this.graph=graph;
    n=graph.size();
    s=n-2;
    t=n-1;
    maxFlow=0;
    levels=new int[n];
    next=new int[n];
    }
  
  public double getMaxFlow(){
    
    while(labeledGraphByLevels()){
      Arrays.fill(next,0);
      for( double f=dfs(s,INF,next);f!=0;f=dfs(s,INF,next) ){
        maxFlow+=f;
        }
      }
    graph.addFlow(maxFlow);
    return maxFlow;
    }
  private boolean labeledGraphByLevels(){
    Arrays.fill(levels,-1);
    queue=new LinkedList<>();
    queue.add(s);
    levels[s]=-1;
    while(!queue.isEmpty()){
      int at=queue.poll();  
      for(Edge e:graph.getEdges(at)){
        int to=e.des;
        if(e.residualCapacity() > 0 && levels[to] == -1){
          levels[to]=levels[at]+1;
          queue.offer(to);
          }
        }  
      }
      return levels[t]!=-1;
     }
  
  private double dfs(int at,double flow,int[]next){
    if(at==t)
      return flow;
    int numEdges=graph.getEdges(at).size();
    
    for(;next[at]<numEdges;next[at]++){
      Edge e=graph.getEdges(at).get(next[at]);
      int to=e.des;
      if(e.residualCapacity()>0 && levels[to]==levels[at]+1){
        double bottleNeck=dfs( to,Math.min(flow,e.residualCapacity()),next );
        if(bottleNeck > 0){
          e.addEdgeFlow(bottleNeck);
          return bottleNeck;
          }
        }
      }
    return 0;
    }
  
  
}
