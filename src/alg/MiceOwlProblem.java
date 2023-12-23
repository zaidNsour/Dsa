/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alg;

import Ds.FlowGraph;
import java.awt.Point;

/**
 *
 * @author DELL
 */
public class MiceOwlProblem {
  int n,m,h,r,s,t;
  Point[]mice;
  Point[]holes;
  int[]holesCapacity;
  FlowGraph graph;
  FordFulkerson solver;
  
  MiceOwlProblem(int r,Point[]mice,Point[]holes,int[]holesCapacity){
    this.r=r;
    this.holes=holes;
    this.mice=mice;
    this.holesCapacity=holesCapacity;
    m=mice.length;
    h=holes.length;
    n=m+h+2;
    graph=new FlowGraph(n);
    s=n-2;
    t=n-1;
  }
  
  public int solve(){
    for(int i=0;i<m;i++)//connect source with mice
      graph.addEdge(s, i, 1);
    for(int i=0;i<h;i++)//connect holes with sink
      graph.addEdge(m+i, t, holesCapacity[i]);
    for(int i=0;i<m;i++)//connect mice with holes
      for(int j=0;j<h;j++)
        if( distance(mice[i],holes[j])<=r )
          graph.addEdge(i, m+j, 1);
    solver=new FordFulkerson();
    solver.solve(graph);    
    return (int)graph.getFlow();
    }
  
  private double distance(Point p1,Point p2){
    return p1.distance(p2);
    }
  
  
}
