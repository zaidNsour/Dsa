
package Ds;

import java.util.Arrays;


public class FenWickTree {
    
   private long[]tree;
   
   public FenWickTree(int sz){
   tree=new long[sz+1];
   }
   
   public FenWickTree(long[]values){
   if(values==null)
       throw new IllegalArgumentException("Values arrays cannot be null!");
   this.tree=values.clone();
   for(int i=1;i<tree.length;i++){
      int j=i+lsb(i);
      if(j<tree.length)
        tree[j]+=tree[i];
     }
   }
   
   private int lsb(int i){
   return Integer.lowestOneBit(i);
   }
   
   public long prefixSum(int i){
    
    long sum=0L;
    while(i!=0){
    sum=sum+tree[i];
    i-=lsb(i);
    }
    return sum;  
   }
   
   public long sum(int i,int j){
   if(j<i)
       throw new IllegalArgumentException("make j>i");
   return prefixSum(j)-prefixSum(i-1);
   }
  
  public void add(int i,long x){    
  while(i<tree.length){
    tree[i]+=x;
    i+=lsb(i);
    }
  } 
  
  public void set(int i,long x){
  long value=sum(i,i);
  add(i,x-value);
  }
  
   @Override
  public String toString(){
   return Arrays.toString(tree);
  }
   
    
}
