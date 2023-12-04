
package alg;





public class Recursion {
    
    
    
    static int multi(int x,int y){
    if(y==0)
        return 0;
    
    if(y<0)
        return multi(x,y+1)-x;
        
    return multi(x,y-1)+x;   
    }
    
   static int odd_sum(int[]list,int i){
    if(i==list.length)
        return 0;
    if(list[i]%2!=0)
       return odd_sum(list,i+1)+list[i];
    return odd_sum(list,i+1);
    }
   
  private static int product_even_list(int[]list,int i){
   if(i==list.length)
       return 1;
   if(list[i]%2==0)
   return product_even_list(list,i+1)*list[i];
   
   return product_even_list(list,i+1);
   }
  
  public static int product_even_list(int[]list){
  return product_even_list(list,0); 
  }
  
  public static String reverse(String str){
  int i=str.length()-1;
  return reverse(str,i);
  }
  
  private static String reverse(String str,int i){
  if(i==-1)
      return "";
  return str.charAt(i)+reverse(str,i-1);
  } 
  
  
  public static boolean isPalandrome(String str){ //abcdcba
       
   return isPalandrome(str,0,str.length()-1);   
  }
  
 private static boolean isPalandrome(String str,int i,int j){
  if(i>=j)
      return true;
  if(str.charAt(i)!=str.charAt(j))
      return false;
  return isPalandrome(str,i+1,j-1);
  
  }
 
 static class Indexs{
 int x;
int y;
int value;
Indexs(int x,int y,int value){
this.x=x;
this.y=y;
this.value=value;
 }



}
 
 
public static Indexs maxMatrix(int[][]matrix){
  Indexs indexs=new Indexs(0,0,matrix[0][0]);     
return maxMatrix(matrix,0,0,indexs);
}

private static Indexs maxMatrix(int[][]matrix,int i,int j,Indexs indexs){


if(i==matrix.length-1 && j>= matrix[0].length){ 
    System.out.println(indexs.value+" "+indexs.x+","+indexs.y);
    return indexs;
}
if(j>=matrix[0].length){  
    i++;
    j=0;
}

if(matrix[i][j]>indexs.value){
    indexs.value=matrix[i][j];
    indexs.x=i;
    indexs.y=j;
}
 return maxMatrix(matrix,i,j+1,indexs); 

}


public static int minElement(int[]arr){
    
return minElement(0,arr.length-1,arr);
}

private static int min(int x,int y,int z){
int min=x;
if(y<min) 
    min=y;
else if(z<min) 
    min=z;
return min;
}

private static int  minElement(int i,int j,int[] arr){ //1 2 3  4 5  6 7 8 
if(i==j)
 return arr[i];

if(j-i==1){   
return Math.min( arr[i], arr[i+1]);
}

if(j-i==2){
return min(arr[i] , arr[i+1],arr[i+2] );
}

int third=(j-i)/3;
int mid1=i+third;
int mid2=i+2*third;

int min1=minElement(i,mid1,arr);
int min2=minElement(mid1+1,mid2,arr);
int min3=minElement(mid2+1,j,arr);

return min(min1,min2,min3);
        
    }
    




}
    

    

