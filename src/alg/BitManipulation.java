
package alg;


public class BitManipulation {
    
    static boolean isEven(int n){
      return (n&1)==0;  
    }
    
  static int numberGreaterByOneBit(int n){ //find smallest number greater than n by convert one bit
    return (n|n+1);
    }
  
  static boolean isSet(int n,int ind){
  int i=1<<(ind-1);
  return (n&i)!=0; 
  }
  
  static int differentBitCount(int n,int m){
  int x=n^m;
  int count=0;
  while(x!=0){
    x=x&(x-1);
    count++;
  }
  return count;
  }
 
    
}
