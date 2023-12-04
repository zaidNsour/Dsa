
package alg;

import Ds.FlowGraph;
import Ds.FlowGraph.Edge;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MaxFlowFord {
    
 int source,sink,maxFlow;
FlowGraph graph;
private boolean solved;
private boolean[]visited;
private Stack<Integer>path;

int u,delta;
boolean useCapacityScalling;

MaxFlowFord(FlowGraph graph,int source,int sink){
if(graph==null)
throw new IllegalArgumentException("null graph!");    
this.graph=graph;
 visited = new boolean[graph.size()];
 path=new Stack<>();
this.source=source;
this.sink=sink;
maxFlow=0;
solved=false;
useCapacityScalling=false;
 }

public int maxFlow(){
if(solved==false){    
dfs(source,sink,path);
solved=true;
}
return maxFlow;
}

public int maxFlowWithCapacityScalling(){   
useCapacityScalling=true; 
delta=graph.getDelta();

while(delta!=0){
dfs(source,sink,path);

delta/=2;
}

solved=true;
return maxFlow;
}

private void dfs(int node,int sink,Stack<Integer>path){
visited[node]=true;
path.push(node); 

if(node==sink){
  relaxationEdges(path);
  path.pop(); 
  visited[node]=false;
  return;
 }
else{
  LinkedList<Edge>edges=graph.getEdges(node);
  for(Edge e:edges){
    if(visited[e.des] || e.remainingCapacity()==0)
      continue;  
    dfs(e.des,sink,path);
    }   
 }
if(!path.empty())
    path.pop(); 

visited[node]=false;
}

private void dfsWithCapacityScalling(int node,int sink,Stack<Integer>path){
visited[node]=true;
path.push(node); 

if(node==sink){
  relaxationEdges(path);
  path.pop(); 
  visited[node]=false;
  return;
 }

else{
  LinkedList<Edge>edges=graph.getEdges(node);
  for(Edge e:edges){
    if(visited[e.des] || e.remainingCapacity()<delta)
      continue;  
    dfs(e.des,sink,path);
    }   
 }
if(!path.empty())
    path.pop(); 

visited[node]=false;
}

private void relaxationEdges(Stack<Integer>path){
if(path.size()<2)
  throw new IllegalArgumentException("path size can't be less than 2");   

int minBottleneck=min(path);

int i=0;
for(;i<path.size()-1;i++){
   int src=path.get(i);
   int des=path.get(i+1);
   graph.addEdgeFlow(src,des,minBottleneck);
  }
maxFlow+=minBottleneck;   
}

private int min(Stack<Integer>path){
int src=path.get(0);
int des=path.get(1);       
int min =graph.getEdge(src,des).remainingCapacity();

for(int i=1;i<path.size()-1;i++){
  src=path.get(i);
  des=path.get(i+1);
  int newMin=graph.getEdge(src,des).remainingCapacity();
  if(newMin<min)
     min=newMin;
  }
return min;
}

 



}




