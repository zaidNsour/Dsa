
package alg;

import Ds.Graph;
import Ds.Graph.Edge;
import java.util.LinkedList;
import java.util.List;


public class EulerianPath {
  Graph graph;
  int n,m;
  int[]in,out;
  LinkedList<Integer>path;
  boolean isThereCircut;
  
  public List<Integer>findEulerianPath(Graph graph){
    this.graph=graph;
    n=graph.size();
    m=graph.edgesCount();
    in=new int[n];
    out=new int[n];
    path=new LinkedList<>();
    isThereCircut=true;
    
    for(int i=0;i<n;i++)
      for(Edge e:graph.getEdges(i)){
        out[e.src]++;
        in[e.des]++;
        }
    if(!hasEulerianPath())
      return null;
    dfs(getStartNode());
    if(path.size()==m+1)
      return path;
    return null;  
    }
  
  private boolean hasEulerianPath(){
    int startNodeCount=0;
    int endNodeCount=0;
    for(int at=0;at<n;at++){
      if(out[at]-in[at]>1 || in[at]-out[at]>1){
        isThereCircut=false;
        return false;
        
      }
      
      if(out[at]-in[at]==1){
        startNodeCount++;
        isThereCircut=false;
        }
      else if(in[at]-out[at]==1){
        endNodeCount++;
        isThereCircut=false;
        }
      }
    return(startNodeCount==0&&endNodeCount==0) ||
          (startNodeCount==1&&endNodeCount==1);
    }
  
  private int getStartNode(){
    int s=0;
    for(int at=0;at<n;at++){
      if(out[at]-in[at]==1)
        return at;
      if(out[at]>0)
        s=at;
      }
    return s;
    }
  
  private void dfs(int at){
    while(out[at]>0){
      Edge nextEdge=graph.getEdges(at).get(--out[at]);
      dfs(nextEdge.des);
      }
    path.addFirst(at);
    }
  
  
  
  
}
