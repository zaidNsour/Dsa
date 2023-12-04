
package Ds;

import java.util.HashMap;
import java.util.LinkedList;

public class FlowGraph {
    
  private int size;
  private int edgesCount;
  LinkedList<Edge>[]adjacencylist;
  HashMap<Integer, Integer>[]positions;
  private int delta;
  
public FlowGraph(int size){
  edgesCount=0;
  this.size=size;
  adjacencylist=new LinkedList[size];
  for(int i=0;i<size;i++)
   adjacencylist[i]=new LinkedList<>();
  
  positions=new HashMap[size];
  for(int i=0;i<size;i++)
   positions[i]=new HashMap<Integer, Integer>();
   delta=-1;

  }
  
  public int size(){
  return size;
  }

  public boolean isEmpty(){
  return size==0;
  }
  
  
  public void addEdge(int src,int des,int capacity){ 
  if( src<0 ||des<0 ||src>=size() || des>=size())
    throw new IllegalArgumentException("invalid index of src or des of edge");
  if(capacity<=0)
    throw new IllegalArgumentException("capacity cant be <=0");  
  Edge edge=new Edge(src,des,capacity,0);
  Edge negativeEdge=new Edge(des,src,0,0);
  edge.reverse=negativeEdge;
  negativeEdge.reverse=edge;
  
  adjacencylist[src].addLast(edge);
  positions[src].put(des, adjacencylist[src].size()-1);
  adjacencylist[des].addLast(negativeEdge);
  positions[des].put(src, adjacencylist[des].size()-1);
  
  delta=Math.max(delta,capacity);

  edgesCount+=2;
  }
  
  public void addEdgeFlow(Edge e,int val){  
      
    if(e.flow+val>e.capacity)
       return;
    e.flow+=val;
    e.getReverseEdge().flow-=val;        
 }
  
  public void addEdgeFlow(int src,int des,int val){
  
    Edge e=getEdge(src,des);
    
    if(e.flow+val>e.capacity)
       return;
    
    e.flow+=val;
    e.getReverseEdge().flow-=val;        
 }
  
  
   
  public LinkedList<Edge>[] getEdges(){
  
   return adjacencylist;
 }
  
 public LinkedList<Edge> getEdges(int node){
  
   return adjacencylist[node];
 }
 
 public Edge getEdge(int src,int des){
 int index=positions[src].get(des);
 return adjacencylist[src].get(index);

 }

   
  public void printGraph(){
         
  for(int i=0;i<size;i++){
    for(int j=0;j<adjacencylist[i].size();j++){
       if(!adjacencylist[i].get(j).isNegativeFlowEdge()) 
       System.out.println(adjacencylist[i].get(j));
      } 
    }
  }
  
  public int getDelta(){
  return highestPowerOf2(delta);
}
  
  
public static int highestPowerOf2(int n){    
int count=0;    
while(n!=0){
n=n>>1;
count++;
}
if(count==0)return 0;
return 1<<count-1;      
}  
  

  
  public class Edge{
  public int src;
  public int des;
  public int capacity;
  public int flow;
  public Edge reverse;
   
   Edge(int src,int des,int capacity,int flow){
    this.src=src;
    this.des=des;
    this.capacity=capacity;
    this.flow=flow;
     }
   
  public int remainingCapacity(){
   return capacity-flow;
   }
   
   
  public Edge getReverseEdge(){
   return reverse;
   }
   
  public boolean isNegativeFlowEdge(){
   return capacity==0;
   }
       
   @Override
        public String toString(){
        return "source="+src+" destination="+des+" flow/capacity="+flow+"/"+capacity;
        }
       
  }
  
}
