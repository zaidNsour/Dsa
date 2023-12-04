
package Ds;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;



public class Graph{
  
  public final static boolean DIRECTED=true;
  public final static boolean UN_DIRECTED=false;
  public boolean isDirected;
  
  private int size; 
  private int capacity;
  private int edges_count;
  private LinkedList<Edge>[]adjacencylist;
  private double adjacencyMatrix[][];
  private boolean isWeighted;
  private HashMap<Object,Integer> map;
  private Object[]nodes;
  
    
  
  public Graph(int capacity){
  size=0;
  edges_count=0;
  this.capacity=capacity;
  adjacencylist=new LinkedList[capacity];
  for(int i=0;i<capacity;i++)
  adjacencylist[i]=new LinkedList<>();
  map =new HashMap<>();
  nodes=new Object[capacity];
  isWeighted=false;
  isDirected=DIRECTED;
  }
  
  public Graph(int capacity,boolean isDirected){
  this(capacity);  
  this.isDirected=isDirected;
  
  }
  
  public int size(){
  return size;
  }

  public boolean isEmpty(){
  return size()==0;
  }
  
  private void addEdge(int src,int des,int cost){
    if(isDirected==true)
      addEdgeDirected(src,des,cost);
    else{
      addEdgeDirected(des,src,cost);
      addEdgeDirected(src,des,cost);
    }
  }
 
  
  private void addEdgeDirected(int src,int des,int cost){ 
  if( src<0 ||des<0 ||src>=capacity || des>=capacity)
   throw new IllegalArgumentException("invalid index of src or des of edge");
  Edge edge=new Edge(src,des,cost);
  adjacencylist[src].addLast(edge);
  edges_count++;
  
  if(isWeighted==false && cost!=0)
    isWeighted=true;  
  }
  
  
  
  public void addEdge(Object src,Object des,int cost){
    if(!map.containsKey(src)){
      map.put(src,size);
      nodes[size]=src;
      size++;
      }
    if(!map.containsKey(des)){
      map.put(des,size);
      nodes[size]=des;
      size++;
      }
    int srcIndex=map.get(src);
    int desIndex=map.get(des);
    addEdge(srcIndex,desIndex,cost);
    }
  
  public int indexOf(Object obj){
    return map.get(obj);
    }
  
  public Object getNodes(){
    return nodes;
    }
  
  public Object getNode(int index){
    return nodes[index];
    }
  
  
  public LinkedList<Edge>getEdges(int src){
   if(src>=size ||src<0)
     return null;  
   return adjacencylist[src];
 }
  
  
  public Edge getEdge(int src,int des){
      
   if(src>=size || des>=adjacencylist[src].size() || src<0 || des<0)
       return null; 
   return adjacencylist[src].get(des);
 }
  
 public boolean isThereEdge(int src,int des){
 return adjacencylist[src].contains(des);
 }
 
 public boolean isWeighted(){
 return isWeighted;
 } 
 
 public int edgesCount(){
   return edges_count;   
  }
 
 
 public double[][] convertListToMatrix(){

 this.adjacencyMatrix=new double[capacity][capacity];

if(isWeighted==true)
  for(int i=0;i<capacity;i++){
    Arrays.fill(adjacencyMatrix[i],Double.POSITIVE_INFINITY);
    adjacencyMatrix[i][i]=0;
    }


LinkedList<Edge> edges[]=adjacencylist;
for(int i=0;i<capacity;i++)
  for(Edge edge:edges[i]){
     int j=edge.des;
     int cost=edge.cost;
     if(isWeighted==true)
       adjacencyMatrix[i][j]=cost;
     else
       adjacencyMatrix[i][j]=1;
     
  }
return adjacencyMatrix;
}
 
public void printEdge(Edge edge){
   System.out.println("source: "+nodes[edge.src]+" destination: "+nodes[edge.des]+
       " with cost: "+edge.cost );
  } 
 
 
public void printGraph(){
  for(int i=0;i<capacity;i++){
   for(Edge edge:adjacencylist[i])
     printEdge(edge);   
    }
  }
  
  
  
  
  
  public class Edge implements Comparable<Edge> {
  public int src;
  public int des;
  public int cost;
   
  public Edge(int src,int des,int cost){
    this.src=src;
    this.des=des;
    this.cost=cost;
     }
   
  public Edge(int src,int des){
    this.src=src;
    this.des=des;
    this.cost=0;
     }


        @Override
    public int compareTo(Edge edge) {
      if(this.cost>edge.cost)
          return 1;
      else if(this.cost<edge.cost)
          return-1;
      else
          return 0;
        }
        
   @Override
        public String toString(){
        return "source="+src+" destination="+des+" cost="+cost;
        }
   
         
  }
  
  
}
