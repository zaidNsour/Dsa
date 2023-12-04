
package alg;

 /*
   
   public static int[] deleteElement(int[]arr,int removed_i){
     int n=arr.length;
     int[]new_arr=new int[n-1];
     int i=0;
     int j=0;
     
     if(n==0)
    throw new IndexOutOfBoundsException(); 
     
     for( i=0,j=0;i<n;i++,j++){
      if(i==removed_i)
       j--;
      else
      new_arr[j]=arr[i];     
     }
     
     return new_arr;
     
    for(int k=0;k<new_arr.length;k++)
       System.out.print(new_arr[k]+" ");          
  }
   
   
   
  static private void merge_strand(int[]arr1,int[]arr2)
    {
    int i,j,k;
    int n1=mid-beg+1;
    //int n2=end-mid;
    
    int []left_arr=new int[n1];
    int []right_arr=new int[n2];
    
    for(i=0;i<n1;i++)
    left_arr[i]=arr[beg+i];
    
    for(j=0;j<n2;j++)
    right_arr[j]=arr[mid+j+1];
    
    i=j=0;
    k=beg;
   while(i<n1&&j<n2)
   {
       
    if(left_arr[i]>=right_arr[j])
    {
       arr[k]=right_arr[j];
       j++;
    }
    else
    {
       arr[k]=left_arr[i];
       i++;
    }
    k++;                 
   }
   
   while(i<n1)
   {
   arr[k]=left_arr[i];
   i++;
   k++;
   }
   
   while(j<n2)
   {
   arr[k]=right_arr[j];
   j++;
   k++;
   }
    
 }
   
   public static void strand_sorting(int[]arr){
   int n=arr.length;    
   int[]temp=new int[n];
   int[]sorted_arr=new int[n];
   int elem=0;
   int i=0;
   int k=1; 
   
   while(i!=n){
   elem=arr[i];
   temp[0]=elem;
   
   for(int j=i+1;j<arr.length;j++){
    if(arr[j]>elem){
    temp[k]=arr[j]; 
    k++;
       }   
     } 
     merge(sorted_arr,temp);
      }
   }
  */ 


public class Sorting {
    
    
    static void print(int[]arr)
    {
    System.out.print("{");   
    for(int i=0;i<arr.length;i++)
    {
     if(i!=arr.length-1)   
     System.out.print(arr[i]+",");
     else
     System.out.print(arr[i]);    
    }
        System.out.println("}");
    }
    
   
    
    static void bubble_sort(int[]arr)
    {
    int temp=0;
    int n=arr.length;
    for(int i=0;i<n;i++)
     {
      for(int j=1;j<(n-i);j++)
      if(arr[j-1]>arr[j])
      {
      temp=arr[j-1];
      arr[j-1]=arr[j];
      arr[j]=temp; 
      }
     } 
    }
    
    static void insertion_sort(int[]arr)
    {
    int n=arr.length;
    for(int i=1;i<n;i++)
      {
        int key=arr[i];
        int j=i-1;
        
        while(j>-1 && key<arr[j])
        {
        arr[j+1]=arr[j]; 
        j--;
        }
        arr[j+1]=key;
      }
    }
    
    
    static void selection_sort(int[]arr)
    {
    int n=arr.length;
    int min_i;
    int temp;
    for(int i=0;i<n;i++)
      {
       min_i=i;   
       for(int j=i+1;j<n;j++)
        {
         if(arr[j]<arr[min_i])
             min_i=j;
        }
       temp=arr[min_i];
       arr[min_i]=arr[i];
       arr[i]=temp;
       
      }
    }
    
    static void odd_even_sort(int[]arr)
    {
        int n=arr.length;
        boolean sorted=false;
             
        while(sorted==false)
        {
        sorted=true;
        
        for(int j=0;j<n-1;j+=2)
          {
            if(arr[j]>arr[j+1])
            {
            int temp=arr[j];
            arr[j]=arr[j+1];
            arr[j+1]=temp;
            sorted=false;
            }
          }
        
        for(int k=1;k<n-1;k+=2)
        {
        if(arr[k]>arr[k+1])
          {
           int temp=arr[k];
            arr[k]=arr[k+1];
            arr[k+1]=temp;
            sorted=false;
          }  
        }
                  
      }
        
    }
    
    
    
    static private void merge(int[]arr,int beg,int mid,int end)
    {
    int i,j,k;
    int n1=mid-beg+1;
    int n2=end-mid;
    
    int []left_arr=new int[n1];
    int []right_arr=new int[n2];
    
    for(i=0;i<n1;i++)
    left_arr[i]=arr[beg+i];
    
    for(j=0;j<n2;j++)
    right_arr[j]=arr[mid+j+1];
    
    i=j=0;
    k=beg;
   while(i<n1&&j<n2)
   {
       
    if(left_arr[i]>=right_arr[j])
    {
       arr[k]=right_arr[j];
       j++;
    }
    else
    {
       arr[k]=left_arr[i];
       i++;
    }
    k++;                 
   }
   
   while(i<n1)
   {
   arr[k]=left_arr[i];
   i++;
   k++;
   }
   
   while(j<n2)
   {
   arr[k]=right_arr[j];
   j++;
   k++;
   }
    
 }
    
    static void merge_sort(int[]arr,int beg,int end)
    {
     if(beg<end){   
     int mid=beg+(end-beg)/2;
     merge_sort(arr,beg,mid);
     merge_sort(arr,mid+1,end);
     merge(arr,beg,mid,end);
     }  
    }
    
   private static int partition(int[]arr,int start,int end)
    {
    int i=start-1;
    int pivot=arr[end];
    int temp;
    for(int j=start;j<end;j++)
    {
    if(arr[j]<=pivot)
     {
       i++;
       temp=arr[i];
       arr[i]=arr[j];//if arr[j]<pivot then swap the arr[i] and arr[j]
       arr[j]=temp;
     }
    }
    temp=arr[i+1];
    arr[i+1]=arr[end];//put the pivot in right place
    arr[end]=temp;
    return i+1;  
    }
   
   public static void quick_sort(int[]arr,int start,int end)
   {
   if(start<end)
    {
    int pivot=partition(arr,start,end);
    quick_sort(arr,start,pivot-1);
    quick_sort(arr,pivot+1,end); 
    }  
   }
   
   public static void cocktail_sort(int[]arr)
   {
   int temp=0;
   int n=arr.length;
   int start=0;
   int end=n;
   boolean sorted=false;
   
   while(sorted==false)
     {
      sorted=true;  
      
      for(int j=start+1;j<end;j++)
      if(arr[j-1]>arr[j])
      {
      temp=arr[j-1];
      arr[j-1]=arr[j];
      arr[j]=temp;
      sorted=false;     
      }
      end--;
      
      for(int j=end-1;j>start;j--)
      if(arr[j]<arr[j-1])
      {
      temp=arr[j-1];
      arr[j-1]=arr[j];
      arr[j]=temp;
      sorted=false;     
      }
      start++;    
     }    
   }
   
   
   private static int max(int[]arr){
   int max=arr[0];
   for(int i=1;i<arr.length;i++)
   if(arr[i]>max)
    max=arr[i];   
   
   return max;      
   }
   
   
   public static void count_sort(int[]arr){
       
   int max=max(arr); 
   int n=arr.length;
   int[]count=new int[max+1];
   int[]sorted_arr=new int[n];
   int index=0;
   
   for(int i=0;i<max+1;i++){
   count[i]=0;
   }
       
   for(int i=0;i<n;i++){
  
   count[arr[i]]++;
    }
   
   for(int i=1;i<max+1;i++){//comulative freq
    count[i]+=count[i-1]; 
    }
   
    n--;
   while(n>=0){
   index=count[arr[n]]-1;    
   sorted_arr[index]=arr[n];
   count[arr[n]]--;
   n--;
     }
   
   for(int i=0;i<arr.length;i++){
   arr[i]=sorted_arr[i];
     }
   }
   
  
   
 
   
   
   
    
}