
package alg;


import Ds.WeightedGraph;
import Ds.UnionFind;
import java.util.ArrayList;
import java.util.LinkedList;
import Ds.PriorityQueue;



public class SpanningTree<T>{
   
  static void kruskals(WeightedGraph graph){
  int size=graph.size(); 
  int edges_count=graph.edgesCount();
  UnionFind union=new UnionFind(size);
  LinkedList<WeightedGraph.Edge>[] list=graph.getEdges();
  
  WeightedGraph.Edge[] edges=new WeightedGraph.Edge[edges_count];
  ArrayList< WeightedGraph.Edge>edges_after=new ArrayList<>();
  
  int k=0;
  for(int i=0;i<size;i++){
    for(int j=0;j<list[i].size();j++){
     if(list[i].get(j)!=null){  
     edges[k]=list[i].get(j); 
     k++;
     }
   }
  }
  quick_sort(edges,0,edges_count-1); //sorting edge asc depend on the cost 
  
  
  int i=0; int j=0;
 while(union.numComponents()!=1){
      
  if(!union.connected(edges[i].src, edges[i].des))
  {    
  union.unify(edges[i].src, edges[i].des);
  edges_after.add(j,edges[i]);
  j++;
  }
  i++;
 
 }
                   
   for(int n=0;n<edges_after.size();n++){
      int src_vertices=edges_after.get(n).src;
      int des_vertices=edges_after.get(n).des;
       System.out.println("vertix: "+src_vertices+" is connected to "+des_vertices+
       " with weight: "+edges_after.get(n).cost);
      }       
 }
  
   void prim(WeightedGraph graph){
       
     int size=graph.size(); 
     int edges_count=graph.edgesCount();  
  
    T[]arr=(T[])graph.getNodes();
    T[]arr2=(T[])new Object[size];
    WeightedGraph.Edge[]edges=new WeightedGraph.Edge[size-1];
    LinkedList<WeightedGraph.Edge>[] list=graph.getEdges();
    PriorityQueue queue=new PriorityQueue();
     
    int k=0;
  for(int i=0;i<size;i++){
      arr2[i]=arr[i];
    for(int j=0;j<list[i].size();j++){
     if(list[i].get(j)!=null){    
     queue.add(list[i].get(j)); 
     }
   }
    WeightedGraph.Edge e=(WeightedGraph.Edge)queue.poll();
    if(arr2[e.des]==null || arr2[e.src]==null){
    edges[k]=e;
    k++;
    }
  }
   
   
  for(int i=0;i<edges.length;i++)
           System.out.println(edges[i].src+" "+edges[i].des+" "+edges[i].cost);
   
           
  
  }
  
  
  
  
  
  
  private static int partition(WeightedGraph.Edge[] arr,int start,int end)
    {
    int i=start-1;
    int pivot=arr[end].cost;
    WeightedGraph.Edge temp;
    for(int j=start;j<end;j++)
    {
    if(arr[j].cost<=pivot)
     {
       i++;
       temp=arr[i];
       arr[i]=arr[j];//if arr[j]<pivot then swap the arr[i] and arr[j]
       arr[j]=temp;
     }
    }
    temp=arr[i+1];
    arr[i+1]=arr[end];//put the pivot in right place
    arr[end]=temp;
    return i+1;  
    }
   
   public static void quick_sort(WeightedGraph.Edge[] arr,int start,int end){
   if(start<end)
    {
    int pivot=partition(arr,start,end);
    quick_sort(arr,start,pivot-1);
    quick_sort(arr,pivot+1,end); 
    }  
   }
  
    
}
