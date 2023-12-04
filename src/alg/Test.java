
package alg;

import Ds.Graph;
import Ds.Graph.Edge;
import Ds.PriorityQueue;
import Ds.UnionFind;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;




public class Test<T> {
    
   public static  List<Object> dfs(Graph graph,Object start){
    int n=graph.size();
    int startIndex=graph.indexOf(start);
    boolean[] visited=new boolean[n];
    List path=new ArrayList<>();
    Stack<Integer> stack=new Stack();
    stack.push(startIndex);
    while(!stack.isEmpty()){
      int at=stack.pop();
      visited[at]=true;
      path.add(graph.getNode(at));
      for(Edge e:graph.getEdges(at)){
        int to=e.des;
        if(!visited[to]&&!stack.contains(to))
        stack.push(to);
        }
      } 
    return path;
    }
   
   public static List<Object>bfs(Graph graph,String start){
    int n=graph.size();
    int startIndex=graph.indexOf(start);
    boolean[] visited=new boolean[n];
    List path=new ArrayList<>();
    Queue<Integer>queue =new LinkedList<>();
    queue.add(startIndex);
    
    while(!queue.isEmpty()){
      int at=queue.poll();
      visited[at]=true;
      path.add(graph.getNode(at));
      for(Edge edge:graph.getEdges(at)){
        int to=edge.des;
        if(!visited[to] &&!queue.contains(to))
          queue.add(to);
        }
    }
    return path;
   }
   
   
   public static Edge[]kruskals(Graph graph){
    int n=graph.size();
    int m=graph.edgesCount();
    UnionFind union=new UnionFind(n);
    Edge[]edges=new Edge[m];
    Edge[]result=new Edge[n-1];
    int j=0;
    for(int i=0;i<n;i++)
      for(Edge e:graph.getEdges(i)){
        edges[j]=e;
        j++;
        }
    Arrays.sort(edges);
    
    j=0;
    for(Edge e:edges){
      if(!union.connected(e.src, e.des)){
        union.unify(e.src, e.des);
        result[j]=e;
        j++;
        }
      if(union.numComponents()==1)
        break;
      }
    
    return result;
    }
   
   public static Edge[]prim(Graph graph){
    int n=graph.size();
    Edge[]result=new Edge[n-1];
    PriorityQueue<Edge> pq = new PriorityQueue<>();
    boolean[]visited=new boolean[n];
    
    int j=0,at=0,to=0;
    visited[at]=true;
    while(j<n-1){ 
      for(Edge e:graph.getEdges(at)){
        if(!visited[e.des])
          pq.add(e);
        }
      
      Edge edge=pq.poll();
      while(visited[edge.des])
        edge=pq.poll();
      
        
        to=edge.des;
        visited[to]=true;
        result[j]=edge;
        j++;
        at=to;
        }
      
    
     
    return result;
    }
   
   
   
}
