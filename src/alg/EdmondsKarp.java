
package alg;

import Ds.FlowGraph;
import Ds.FlowGraph.Edge;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class EdmondsKarp {
  static final double INF=Double.POSITIVE_INFINITY;
  int s,t,n;
  FlowGraph graph;
  boolean[]visited;
  Edge[]prevEdges;
  double[]bottleNeck;
  Queue<Integer>queue;
  boolean usedCapacityScaling=false;
  int delta=1;
  
  EdmondsKarp(FlowGraph graph){
    this.graph=graph;
    n=graph.size();
    s=n-2;
    t=n-1;
    visited=new boolean[n];
    prevEdges=new Edge[n];
    bottleNeck=new double[n];
    queue=new LinkedList<>();
    
    }
  
  EdmondsKarp(FlowGraph graph,boolean usedCapacityScaling){
    this(graph);
    this.usedCapacityScaling=usedCapacityScaling;  
    }
  
  public double getMaxFlow(){
    if(usedCapacityScaling)
      for(double f=0,delta=graph.getDelta();delta>0;delta/=2){
      do{
        f=bfs();
        graph.addFlow(f);
        Arrays.fill(visited,false);
        }while(f!=0);
      }
    else
      for(double f=bfs();f!=0;f=bfs()){
        Arrays.fill(visited,false);
        graph.addFlow(f);
        }
    
    return graph.getFlow();
    }
  
  private double bfs(){
    queue.add(s);
    bottleNeck[s]=INF;
    prevEdges[s]=null;
    visited[s]=true;
    while( !queue.isEmpty() ){
      int at=queue.poll();
      if(at==t){
        for(Edge e=prevEdges[t];e!=null;e=prevEdges[e.src]){
          
          e.addEdgeFlow(bottleNeck[t]);
        }
        return bottleNeck[t];
        } 
      for(Edge e:graph.getEdges(at)){
        int to=e.des;
        double resCapacity=e.residualCapacity();
          
        if(resCapacity>=delta && !visited[to]){
          visited[to]=true;
          prevEdges[to]=e;
          bottleNeck[to]=Math.min(bottleNeck[at],resCapacity);
          queue.add(to);
          }
        }
      }
    return 0;
    }
  
}
