/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alg;

import Ds.Graph;
import Ds.Graph.Edge;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author DELL
 */
public class Tarjans {
  static final int UN_VISITED=-1;
  private Graph graph;
  private int n,id,sccCount;
  private int[]low,ids;
  private boolean[]onStack;
  private Stack<Integer>stack;
  
  public int[]findSccs(Graph graph){
    this.graph=graph;
    n=graph.size();
    id=0;
    sccCount=0;
    low=new int[n];
    ids=new int[n];
    onStack=new boolean[n];
    stack=new Stack<>();
    Arrays.fill(ids, UN_VISITED);
    for(int at=0;at<n;at++)
      if(ids[at]==UN_VISITED)
        dfs(at);
    return low;
    }
  public int getSccsCount(){
    return sccCount;
    }
  
  private void dfs(int at){
    stack.push(at);
    onStack[at]=true;
    ids[at]=low[at]=id++;
    for(Edge e:graph.getEdges(at)){
      int to=e.des;
      if(ids[to]==UN_VISITED)
        dfs(to);
      if(onStack[to])
        low[at]=Math.min(low[at],low[to]);
      }
    if(ids[at]==low[at]){
      for(int i=stack.pop(); ;i=stack.pop()){
        onStack[i]=false;
        low[i]=ids[at];
        if(at==i)
          break;
        }
      sccCount++;
      }
    
    }
    
  
  
  
}
