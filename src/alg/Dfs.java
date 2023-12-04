
package alg;

import Ds.WeightedGraph;
import Ds.WeightedGraph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;


public class Dfs{

static WeightedGraph graph;
static int n;
static boolean[]visited;
static ArrayList<Integer> paths;
static Stack<Integer>stack=new Stack();




 static ArrayList<Integer>traversal(WeightedGraph graph,int start){
n=graph.size();
visited=new boolean[n];
paths=new ArrayList<>();    
dfs(start,-1,graph,paths);
 return paths;
 }
 
 
 private static void dfs(int at,int parent,WeightedGraph graph,ArrayList<Integer>paths){
 
 visited[at]=true;
 paths.add(at);
 LinkedList<Edge> edges=graph.getEdges()[at];
 for(Edge edge:edges){
   int to=edge.des;
   if(!visited[to])
     dfs(to,at,graph,paths);
   }
 
 }
 
 
 static ArrayList<Integer>traversalUsingStack(WeightedGraph graph,int start){
n=graph.size();
visited=new boolean[n];
paths=new ArrayList<>();    
dfsUsingStack(start,graph,paths);
 return paths;
 }
 
 private static void dfsUsingStack(int at,WeightedGraph graph,ArrayList<Integer>paths){

 stack.push(at);
 while(!stack.isEmpty()){   
  at=stack.pop();
   visited[at]=true;
   paths.add(at);  
 LinkedList<Edge> edges=graph.getEdges()[at]; 
 for(Edge edge:edges){ 
   int to=edge.des;
   if(!visited[to])  
     stack.push(to);
    }
   
  }
 
}
 
 
 
 
 
 public static boolean search(Object elem,WeightedGraph graph){
visited=new boolean[graph.size()];          
 return dfs(0,elem,graph);
 
 }
 
 private static boolean dfs(int at,Object elem,WeightedGraph graph){
 if(graph.getNode(at).equals(elem) )
    return true;
 visited[at]=true;
 LinkedList<Edge> edges=graph.getEdges()[at];
 for(Edge edge:edges){
   int to=edge.des;
   if(!visited[to]) 
    if(dfs(to,elem,graph))
    return true;
   }
 return false;
 }
 
 
  
    
}
