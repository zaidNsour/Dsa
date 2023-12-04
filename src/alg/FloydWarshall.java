
package alg;

import Ds.Graph;
import Ds.Graph.Edge;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


public class FloydWarshall {
    
private static int n;
 private static double m[][];
 private static Integer next[][];
 private static boolean construct=false;

public static double[][]shortestPathMatrix(Graph graph){ 
  n=graph.size();
  m=new double[n][n];
  next=new Integer[n][n];
  
  m=graph.convertListToMatrix(); //convert adj list to adj matrix
  for(int i=0;i<n;i++)
      for(int j=0;j<n;j++)
        if(m[i][j]!=Double.POSITIVE_INFINITY)
        next[i][j]=j;
     
  for(int k=0;k<n;k++){
    for(int i=0;i<n;i++)
      for(int j=0;j<n;j++){
        if(m[i][k]+m[k][j]<m[i][j]){    
          m[i][j]=m[i][k]+m[k][j]; 
          next[i][j]=next[i][k];
        }
      }   
    }

  for(int k=0;k<n;k++){ //to check if graph effected with negative cycles
    for(int i=0;i<n;i++)
      for(int j=0;j<n;j++){
        if(m[i][k]+m[k][j]<m[i][j]){ 
          m[i][j]=Double.NEGATIVE_INFINITY; 
          next[i][j]=-1;
        }
      }
    } 
    construct=true;
    return m;
  }

public static List<Object>reconstructShortestPath(int s,int e,Graph graph){
  if(construct==false)
    shortestPathMatrix(graph);

List<Object>path=new ArrayList<>();
  if(m[s][e]==Double.NEGATIVE_INFINITY)
    return path;
  for(int at=s;;at=next[at][e]){
    if(at==-1)
      return null;
    path.add(graph.getNode(at));
      
    if(at==next[at][e])
      break;    
    }
  return path;
  }
     
    
}
