
package alg;

import java.util.Iterator;


public class DynamicArr <T> implements Iterable {

 private T[] arr;
 public int len=0;
 public int capacity=0;
 
 public DynamicArr()
{
this(16);
}

public DynamicArr(int capacity)
{
if(capacity<0)
throw new IllegalArgumentException("Illegal capacity: "+capacity);    
this.capacity=capacity;
arr=(T[])new Object[capacity];
}

public int size()
{
return len;
}

public boolean isEmpty()
{
return size()==0;
}

public T get(int i)
{
return arr[i];
}

public void set(int i,T elem)
{
arr[i]=elem;
}

public void clear()
{   
for(int i=0;i<capacity;i++)
 {
  arr[i]=null; 
  len=0;
 }

}

 public void add(T elem)
{
 if(len+1>=capacity)
 {
  if(capacity==0)
      capacity=1;
  else
      capacity*=2;
  
  T[] new_arr=(T[])new Object[capacity];
  
  for(int i=0;i<len;i++)
      new_arr[i]=arr[i];
  arr=new_arr;     
 }
 
 arr[len++]=elem;
 
}

public T removeAt(int rm_i)
{
if(rm_i>=len || rm_i<0)
    throw new IndexOutOfBoundsException();

T data=arr[rm_i];
T[] new_arr=(T[]) new Object[len-1];

for(int i=0,j=0;i<len;i++,j++)
  {
  if(i==rm_i) // 5 elements removeAt(2) i=0,j=0>>>i=1,j=1>>>i=2,j=2>>>i=rm_i>>>i=2,j=1
      j--;    //>>>i=3,j=2 >>>new_arr[2]=arr[3]>>>so we skip element with index 2  
  else
      new_arr[j]=arr[i];
  }

arr=new_arr;
capacity=--len;

return data;

}

public boolean remove(Object obj)
{
for(int i=0;i<len;i++)
 {
   if(arr[i].equals(obj))
   {
   removeAt(i);
   return true;
   }       
 } 
 return false;
}

public int indexOf(Object obj)
{
for(int i=0;i<len;i++)
 {
   if(arr[i].equals(obj))
   return i;    
 }
return -1;
}

public boolean contains(Object obj)
{
return indexOf(obj)!=-1;
}
    
    @Override
    public Iterator iterator() {
        return new java.util.Iterator<T>() {           
            int index=0;
            @Override
            public boolean hasNext() {
             return index<len;   
            }

            @Override
            public T next() {
            return arr[index++];   
            }
        };
    }

    @Override
    public String toString() {
        if(len==0)
           return"[]";
        else
        {
          StringBuilder sb=new StringBuilder(len).append("[");
          for(int i=0;i<len-1;i++)
              sb.append(arr[i]+",");
          return sb.append(arr[len-1]+"]").toString();
        }
    }
    
    public void display()
    {
        System.out.println(this.toString());
    }
    
    
   
    
    
}
