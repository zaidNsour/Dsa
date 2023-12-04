
package Ds;




import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class WeightedGraph<T>{
    
    
  private int size; 
  private int capacity;
  private int edges_count;
  LinkedList<Edge>[]adjacencylist;
  T[]vertices;
  static double adjacencyMatrix[][];
  
  
 public WeightedGraph(int capacity){
  size=0;
  edges_count=0;
  this.capacity=capacity;
  vertices=(T[])new Object[capacity];
  adjacencylist=new LinkedList[capacity];
  for(int i=0;i<capacity;i++)
   adjacencylist[i]=new LinkedList<>();
  }
  
  
  public int size(){
  return size;
  }

  public boolean isEmpty(){
  return size()==0;
  }
  
  public void addNode(T node){
  if(size<=capacity){    
  vertices[size]=node;
  size++;
    }
  else
      throw new IllegalArgumentException("capacity is full");
  }
  
   public T[] getNodes(){
  return vertices;
  }
  
  public T getNode(int index){
  if(index<0 || index>=size)
      return null;
  return vertices[index];
  }
  
  public int indexOf(Object elem){
  for(int i=0;i<vertices.length;i++){
   if(elem.equals(vertices[i]))
       return i;
   }
  return -1;
  }
  
  
  public void addEdge(int src,int des,int cost){ 
  if( src<0 ||des<0 ||src>=size() || des>=size())
   throw new IllegalArgumentException("invalid index of src or des of edge");
  Edge edge=new Edge(src,des,cost);
  adjacencylist[src].addLast(edge);
  edges_count++;
  }
  
  public void addEdge(T src,T des,int cost){
  int src_i=indexOf(src);
  int des_i=indexOf(des);
  addEdge(src_i,des_i,cost);
  }
  
  public LinkedList<Edge>[] getEdges(){
  
   return adjacencylist;
 }
  
  public Edge getEdge(int id,int j){
      
   if(id>=size || j>=adjacencylist[id].size() || id<0 || j<0)
       return null;
   
   return adjacencylist[id].get(j);
 }
  
 public boolean isThereEdge(int i,int j){ //dev
 return getEdge(i,j)!=null;
 } 
 
  
  public int edgesCount(){
   return edges_count;   
  }
  
  
  public double[][] convertListToMatrix(){

 adjacencyMatrix=new double[size][size];

for(int i=0;i<size;i++){
Arrays.fill(adjacencyMatrix[i], Double.POSITIVE_INFINITY);
adjacencyMatrix[i][i]=0;
}

LinkedList<Edge> edges[]=adjacencylist;
for(int i=0;i<size;i++)
  for(Edge edge:edges[i]){
     int j=edge.des;
     int cost=edge.cost;
     adjacencyMatrix[i][j]=cost;
  }
return adjacencyMatrix;
}
   
  public void printGraph(){
  for(int i=0;i<size;i++){
  LinkedList<Edge>list=adjacencylist[i];
   for(int j=0;j<list.size();j++){
      T src_vertices=vertices[list.get(j).src];
      T des_vertices=vertices[list.get(j).des];
       System.out.println("vertix: "+src_vertices+" is connected to "+des_vertices+
       " with weight: "+list.get(j).cost);
      } 
    }
  }
  
  
 public class Edge implements Comparable<Edge> {
  public int src;
  public int des;
  public int cost;
   
   Edge(int src,int des,int cost){
    this.src=src;
    this.des=des;
    this.cost=cost;
     }
   
   Edge(int src,int des){
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
