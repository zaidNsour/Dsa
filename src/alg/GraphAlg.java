
package alg;

import Ds.WeightedGraph;
import Ds.WeightedGraph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GraphAlg<T> {
    
public  void display(int[]arr){
   for(int i=0;i<arr.length;i++)
      System.out.print(arr[i]+"");
   }     
 
    
private static int dfs(int i,int at,boolean[]v, int[]ordering,WeightedGraph graph){
v[at]=true;
LinkedList<Edge>edges=graph.getEdges()[at];
for(Edge edge:edges){
  int to=edge.des;  
  if(v[to]==false)
    i=dfs(i,to,v,ordering,graph);  
  }
 ordering[i]=at;
 return i-1;
}    
    
 public static  int[]topSort(WeightedGraph graph){
 int n=graph.size();
 boolean[] v=new boolean[n];
 int[] ordering=new int[n];
 int i=n-1;
 
 for(int at=0;at<n;at++){
  if(v[at]==false)
   i=dfs(i,at,v,ordering,graph);
  if(i==-1)
    break;
   }  
 return ordering;
}

public Integer[]dagShortestPath(WeightedGraph graph){
int[]topSort=topSort(graph);
int start=topSort[0];
int n=graph.size();
Integer[]dist=new Integer[n];
dist[start]=0;
for(int i=0;i<n;i++){
  int nodeIndex=topSort[i];
  if(dist[nodeIndex]!=null){
    LinkedList<Edge>edges=graph.getEdges()[nodeIndex];
    if(edges!=null){
      for(Edge edge:edges){
        int newDist=dist[nodeIndex]+edge.cost;
        if(dist[edge.des]==null)
           dist[edge.des]=newDist;
        else
           dist[edge.des]=Math.min(dist[edge.des], newDist);
        }
      }
    }
  }
return dist;
}


public static double[] bellmanFord(WeightedGraph graph,int start){
    int n=graph.size();
    double []dist=new double[n];
    Arrays.fill(dist, Double.POSITIVE_INFINITY);
    dist[start]=0;
    LinkedList<Edge>[]edges=graph.getEdges();
    boolean relaxed=false;
    
  while(relaxed==false){
    relaxed=true;
    for(int i=0;i<n-1;i++){
      for(int j=0;j<n;j++)
         for(Edge edge:edges[j])
            if(dist[edge.src]+edge.cost<dist[edge.des]){
              dist[edge.des]=dist[edge.src]+edge.cost;
              relaxed=false;
            }        
     }
    
    for(int i=0;i<n-1;i++){
      for(int j=0;j<n;j++)
         for(Edge edge:edges[j])
            if( dist[edge.src]+edge.cost<dist[edge.des] ){
              dist[edge.des]=Double.NEGATIVE_INFINITY; //to indicate there is negative cycle 
              relaxed=false;
            }   
     }
    
 }  
   return dist;     
}

private static double[][] convertListToMatrix(WeightedGraph graph){
int n=graph.size();
double a[][]=new double[n][n];

for(int i=0;i<n;i++){
Arrays.fill(a[i], Double.POSITIVE_INFINITY);
a[i][i]=0;
}


for(int i=0;i<n;i++){
    int e=0;  
   for(int j=0;j<n;j++){  
     if(graph.getEdge(i,e)!=null)  
      if(graph.getEdge(i,e).des==j){
        a[i][j]=graph.getEdge(i, e).cost;
        e++;    
     }  
   }
}

return a;

}










}
