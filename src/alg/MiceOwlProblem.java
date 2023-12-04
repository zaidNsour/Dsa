
package alg;

import Ds.FlowGraph;
import java.awt.Point;
import java.util.List;

 class Mouse{
 int x,y;
 Point p;
 
 Mouse(int x,int y){
 this.x=x;
 this.y=y;
 p=new Point(x,y);
   }
 
 @Override
 public String toString(){
 return "position= ("+x+","+y+")";
 }
 
 }
 
 class Hole{
 int x,y;
 Point p;
 int capacity;
 
 Hole(int x,int y,int capacity){
 this.x=x;
 this.y=y;
 p=new Point(x,y);
 this.capacity=capacity;
   }
 
 @Override
 public String toString(){
 return "position= ("+x+","+y+")";
 }
 
 }

public class MiceOwlProblem {   
 int r;
 List<Mouse>mice;
 List<Hole>holes;
 int maxMice;
 int size,m,h,source,sink;
 FlowGraph graph;
 MaxFlowFord solver;
 
 MiceOwlProblem(List<Mouse> mice,List<Hole>holes,int r){
 this.mice=mice;
 this.holes=holes;
 this.r=r;
 m=mice.size();
 h=holes.size();
 size=m+h+2;
 source=size-2;
 sink=size-1;
 graph=new FlowGraph(size);
 }
 
 public int maxMice(){
 for(int i=0;i<m;i++){
   graph.addEdge(source, i,1);
   }
 for(int i=0;i<h;i++){
   graph.addEdge(m+i, sink, holes.get(i).capacity);
   }
 for(int i=0;i<m;i++){
   for(int j=0;j<h;j++){
     if( distance( mice.get(i).p,holes.get(j).p ) <= r)  
       graph.addEdge(i, m+j, 1);
     }
   }
 solver=new MaxFlowFord(graph,source,sink);
 maxMice=solver.maxFlow();
 return maxMice;
 }
 
 public int distance(Point x,Point y){
 return (int) x.distance(y);
 }
 
       
}



/*
for test

Mouse m1=new Mouse(10,50);
Mouse m2=new Mouse(10,40);
Mouse m3=new Mouse(10,30);
Mouse m4=new Mouse(10,20);
Mouse m5=new Mouse(10,10);
List<Mouse>mice=new ArrayList();
mice.add(m1);
mice.add(m2);
mice.add(m3);
mice.add(m4);
mice.add(m5);

Hole h1=new Hole(30,10,1);
Hole h2=new Hole(30,30,2);
Hole h3=new Hole(30,50,1);
List<Hole>holes=new ArrayList();
holes.add(h1);
holes.add(h2);
holes.add(h3);   

MiceOwlProblem solver=new MiceOwlProblem(mice,holes,23); 

System.out.println(solver.maxMice());


*/