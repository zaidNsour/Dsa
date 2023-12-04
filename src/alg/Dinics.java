/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alg;

import Ds.FlowGraph;
import Ds.FlowGraph.Edge;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 *
 * @author DELL
 */
public class Dinics {
private Double INF=Double.MAX_VALUE;    
private int s,t,maxFlow,n;
private FlowGraph graph;
private boolean solved;   
private Queue<Integer>q;   
private int[]level;
private int[] next;

public Dinics(FlowGraph graph,int s,int t){
this.s=s;
this.t=t;
this.graph=graph;
n=graph.size();

}

public int maxFlow(){
if(solved==false){    
solve();
solved=true;
}
return maxFlow;
}

private void solve(){
level=new int[n];
next=new int[n];
while(bfs()){
  Arrays.fill(next,0);
  for(double f=dfs(s,next,INF);f!=0;f=dfs(s,next,INF)){
    maxFlow+=f;
    }
  }
}

private boolean bfs(){
q=new ArrayDeque<>(n);    
Arrays.fill(level,-1);
level[s]=0;
q.offer(s);
while(!q.isEmpty()){
  int node=q.poll();
  for(Edge e:graph.getEdges(node)){
    int remain=e.remainingCapacity();
    if(remain>0 && level[e.des]==-1){
      level[e.des]=level[node]+1;
      q.offer(e.des);
      }
    }
  }
return level[t]!=-1;
}

private double dfs(int at,int[]next,double flow){
if(at==t) return flow;
int numEdges=graph.getEdges(at).size();

for(;next[at]<numEdges;next[at]++){
  Edge e=graph.getEdges(at).get(next[at]);
  int remain=e.remainingCapacity();
  if(remain>0 && level[e.des]==level[at]+1){
    double bottleneck=dfs(e.des,next,Math.min(flow,remain));
    if(bottleneck>0){
      graph.addEdgeFlow(e,(int)bottleneck);
      return bottleneck;
      }
    }
  }
return 0;
}





}
