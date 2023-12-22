/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ds;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class FlowGraph {
  List<Edge>adjList[];
  int size,edgeCount;
  double flow;
  
  public FlowGraph(int size){
    this.size=size;
    edgeCount=0;
    flow=0;
    adjList=new ArrayList[size];
    for(int i=0;i<size;i++)
      adjList[i]=new ArrayList<>();
    }
  
  public int size(){
    return size;
    }
  
  public boolean isEmpty(){
    return size==0;
    }
  
  public Edge getEdge(int src,int des){
    
    if(src>=size || des>=size() || src<0 || des<0)
      return null;
   
    for(Edge e:adjList[src])
      if(e.des==des)
        return e;
    
    return null;
    }
  
  public List<Edge> getEdges(int src){
    if(src>=size || src<0 )
      return null;
    return adjList[src];
    }
  
  public int edgeCount(){
    return edgeCount;
    }
  
  public double getFlow() {
      return flow;
    }

  public void addFlow(double flow) {
     this.flow += flow;
    }
  
  public void printGraph(){
    System.out.println("flow="+flow);
    for(int i=0;i<size;i++)
      for(Edge e:adjList[i])
        if(!e.isResidualEdge())
          System.out.println(e);
    }
  public void addEdge(int src,int des,double capacity){
    if(src>=size || des>=size || src<0 || des<0 || capacity<=0)
      return;
    Edge edge=new Edge(src,des,capacity);
    Edge resEdge=new Edge(des,src,0);
    adjList[src].add(edge);
    adjList[des].add(resEdge);
    edge.reverse=resEdge;
    resEdge.reverse=edge;
    edgeCount++;
 
    }
  
  
  
  public class Edge implements Comparable<Edge>{
    public int src,des;
    double flow,capacity;
    Edge reverse;
    
    public Edge(int src,int des,double capacity){
      this.src=src;
      this.des=des;
      this.capacity=capacity;
      flow=0;
      }
    
    public double residualCapacity(){
      return capacity-flow;
      }
    
    public boolean isResidualEdge(){
      return capacity==0;
      }
    
    public void addEdgeFlow(double addedFlow){
      if(flow+addedFlow > capacity)
        return;
      flow+=addedFlow;
      reverse.flow-=addedFlow;
      }

    
    @Override
    public int compareTo(Edge e) {
     if(this.residualCapacity() > e.residualCapacity())
       return 1;
     else if(this.residualCapacity() < e.residualCapacity())
       return -1;
     else
       return 0;
    }
    
    @Override
    public String toString(){
     return "source="+src+" des="+des+" flow/capacity="+flow+"/"+capacity;
    }
    
  }
  
  
  
  
}
