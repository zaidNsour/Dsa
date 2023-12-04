
package alg;

import Ds.WeightedGraph;
import Ds.WeightedGraph.Edge;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Bfs{
    
private static WeightedGraph graph;
private static int n;
private static boolean[]visited;
private static ArrayList<Integer> paths;
private static Queue<Integer>queue=new LinkedList<>();
private static boolean isFound=false;

public static ArrayList<Integer>traversal(WeightedGraph graph,int start){
n=graph.size();
visited=new boolean[n];
paths=new ArrayList<>();    
bfs(start,graph,paths,-1);
 return paths;
 }

static boolean isTherePath(WeightedGraph graph,Object start,Object key){
n=graph.size();    
int s=graph.indexOf(start);
int k=graph.indexOf(key);  
visited=new boolean[n];
paths=new ArrayList<>(); 
bfs(s,graph,paths,k);
return isFound;

}

private static void bfs(int at,WeightedGraph graph,ArrayList paths,int key){
queue.add(at);
while(!queue.isEmpty()){
at=queue.remove();

if(at==key)
   isFound=true;

if(!visited[at])    
 paths.add(at); 

visited[at]=true;
LinkedList<Edge>edges=graph.getEdges()[at];
for(Edge edge:edges){
   int to=edge.des;
   if(!visited[to])
     queue.add(to);
   }

  }

 }




}
  





