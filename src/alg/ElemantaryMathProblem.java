
package alg;

import Ds.FlowGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ElemantaryMathProblem {
 private int n,m,a,answersCount;
 private int s,t;
 private FlowGraph graph;
 private FordFulkerson solver;
 private int[]numbers1,numbers2;
 private List<Integer>[]answers;
 private HashMap<Integer,Integer>map;
  
  ElemantaryMathProblem(int[]n1,int[]n2){
    numbers1=n1;
    numbers2=n2;
    m=n1.length;
    answers=new ArrayList[m];
     for(int i=0;i<m;i++)
      answers[i]=new ArrayList<>();
     map=new HashMap<>();
    }
  
  public int solve(){
    a=0;
    for(int i=0;i<m;i++)
      for(int j=0;j<3;j++){
        int val=result(numbers1[i],numbers2[i],j);
        answers[i].add(val);
        if(!map.containsKey(val)){
          map.put(val, a);
          a++;
          }
        }
    
    n=m+a+2;
    s=n-2;
    t=n-1;
    graph=new FlowGraph(n);
    
    for(int i=0;i<m;i++)
      graph.addEdge(s, i,1);
    for(int i=0;i<a;i++)
      graph.addEdge(m+i, t,1);
    for(int i=0;i<m;i++)
      for(int j=0;j<3;j++)
        graph.addEdge(i, m + map.get( answers[i].get(j) ),1);
    solver=new FordFulkerson();
    solver.solve(graph);
    
    answersCount=(int)graph.getFlow();
    return answersCount;
    }
  
  private int result(int n1,int n2,int op){
    if(op==0)
      return n1-n2;
    else if(op==1)
      return n1+n2;
    else 
      return n1*n2;
    }
  
  public boolean isUniqueAnswers(){
    return(answersCount==4);
    }
  
  
  
  
}
