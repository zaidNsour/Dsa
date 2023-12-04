
package alg;

import Ds.FlowGraph;
import Ds.FlowGraph.Edge;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;


public class EdmondsKarp {
    
private int s,t,maxFlow,n;
FlowGraph graph;
private boolean solved;
private boolean[]visited;
private Edge[] prev;
private Queue<Integer>q;


EdmondsKarp(FlowGraph graph,int s,int t){
this.graph=graph;
this.n=graph.size();
this.s=s;
this.t=t;
visited=new boolean[n];
}

public int maxFlow(){
if(solved==false){    
solve();
solved=true;
}
return maxFlow;
}

private void solve(){
int flow;
do{
  Arrays.fill(visited,false);
  flow=bfs();
  maxFlow+=flow;
  }while(flow!=0);  
}

private int bfs(){
prev=new Edge[n];
q=new ArrayDeque(n);
visited[s]=true;
q.offer(s);

while(!q.isEmpty()){
  int node=q.poll();
  if(node==t)break;
  for(Edge e:graph.getEdges()[node]){
    int remain=e.remainingCapacity();
    if(remain>0 && !visited[e.des]){
      visited[e.des]=true;
      prev[e.des]=e;
      q.offer(e.des);
      }
    }
  }
if(prev[t]==null)
  return 0;

double bottleneck=Long.MAX_VALUE;
for(Edge e=prev[t];e!=null;e=prev[e.src])
  bottleneck=Math.min(bottleneck, e.remainingCapacity());

for(Edge e=prev[t];e!=null;e=prev[e.src])
  graph.addEdgeFlow(e,(int)bottleneck);

return (int)bottleneck;
}





}

    

