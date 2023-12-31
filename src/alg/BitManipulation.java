
package alg;


public class BitManipulation {
    
  public static boolean isEven(int n){
      return (n&1)==0;  
    }
    
  public static int numberGreaterByOneBit(int n){ //find smallest number greater than n by convert one bit
    return (n|n+1);
    }
  
   public static boolean isSet(int n,int ind){
  int i=1<<(ind-1);
  return (n&i)!=0; 
  }
  
   public static int differentBitCount(int n,int m){
  int x=n^m;
  int count=0;
  while(x!=0){
    x=x&(x-1);
    count++;
  }
  return count;
  }
  
  public static int highestPowerof2(int N)
  {
     
    // if N is a power of two simply return it
    if ((N & (N - 1)) == 0)
      return N;
 
    // else set only the most significant bit
    return (1 << (Integer.toBinaryString(N).length() - 1));
  }
    
}
