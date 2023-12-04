/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alg;

import Ds.Graph;
import Ds.Graph.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author DELL
 */
public class TopologicalSorting {
  int n;
  Graph graph;
  List<Integer>result;
  boolean[]visited;
  
  public List<Integer>solve(Graph graph){
  this.graph=graph;
  n=graph.size();
  result=new ArrayList<>();
  visited=new boolean[n];
  
  for(int i=0;i<n;i++){
    if(result.size()==n)
      break;
    if(!visited[i])
      dfs(i);
    }
  Collections.reverse(result);
  return result;
  }
  
  private void dfs(int at){
    visited[at]=true;
    
    for(Edge e:graph.getEdges(at))
      if(!visited[e.des])
        dfs(e.des);
    result.add(at);
    }
  
}
