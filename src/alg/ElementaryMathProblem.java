
package alg;

import Ds.FlowGraph;
import java.util.ArrayList;
import java.util.List;


public class ElementaryMathProblem {
    
List<Integer>n1,n2;
List<Integer>answers;
int[]answersPos;
int opCount,answersCount;
int size;
FlowGraph graph;
MaxFlowFord solver;
int source,sink;

ElementaryMathProblem(List<Integer>n1,List<Integer>n2){
this.n1=n1;
this.n2=n2;
opCount=n1.size();
answers=new ArrayList();
answersPos=new int[3*opCount];
findAnswers();
answersCount=answers.size();
int size=opCount+answersCount+2;
graph=new FlowGraph(size);
source=size-2;
sink=size-1;
}

public int solve(){
for(int i=0;i<opCount;i++){
  graph.addEdge(source, i, 1);
  }
for(int i=0;i<answersCount;i++){
  graph.addEdge(opCount+i, sink, 1);
  }
int k=0;
for(int i=0;i<opCount;i++)
  for(int j=0;j<3;j++){
    graph.addEdge(i,opCount+answersPos[k],1);
    k++;
    }

 solver=new MaxFlowFord(graph,source,sink);
 return solver.maxFlow();
  }

private void findAnswers(){
int k=0,index=0;    
for(int i=0;i<opCount;i++)
  for(int j=0;j<3;j++){
    int ans=operation(j,n1.get(i),n2.get(i));
    if(!answers.contains(ans)){
      answers.add(ans);
      answersPos[k]=index;
      index++;
      }
    else{
      answersPos[k]=answers.indexOf(ans);
      }
    k++;
    }  
}

private int operation(int op,int num1,int num2){
if(op==0) return num1+num2;
else if(op==1) return num1-num2;
return num1*num2;
}


    
}
