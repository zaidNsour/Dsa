/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alg;

import Ds.Graph;
import Ds.Graph.Edge;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Bridge {
  
  int id,n,outEdgeCount;
  Graph graph;
  int[]ids;
  int[]low;
  boolean[]visited,isArt;
  List<Edge>bridges;
  
  public void solve(Graph graph){
    bridges=new ArrayList<>();
    this.graph=graph;
    n=graph.size();
    id=0;
    ids=new int[n];
    low=new int[n];
    visited=new boolean[n];
    isArt=new boolean[n];
    for(int i=0;i<n;i++)
      if(!visited[i]){
        outEdgeCount=0;
        dfs(i,i,-1);
        isArt[i]=outEdgeCount>1;
      } 
   }
 
  private void dfs(int root,int at,int parent){
    if(parent==root)
      outEdgeCount++;
    visited[at]=true;
    low[at]=ids[at]=id++;
    for(Edge e:graph.getEdges(at)){
      int to=e.des;
      if(to==parent) continue;
      if(!visited[to]){
        dfs(root,to,at);
        low[at]=Math.min(low[at],low[to]);
        if(ids[at]<low[to]){
          bridges.add(e); //art point fount via Bridge
          isArt[at]=true;
          }
         
        else if(ids[at]==low[to])
          isArt[at]=true; //art point fount via cycle 
        }
      else
        low[at]=Math.min(low[at], ids[to]);
      }
    }
  
  public List<Edge>getBridges(){
    return bridges;
    }
  public List<Object>getArticulationPoint(){
    List<Object>list=new ArrayList<>();
    for(int i=0;i<n;i++)
      if(isArt[i])
        list.add(graph.getNode(i));
    return list;
        
    }
  
  
  
  
}
