/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class PriorityQueue<T extends Comparable<T>> {
    
  private int heap_size=0;
  private int heap_capacity=0;
  private List<T> heap=null;
  private Map<T,TreeSet<Integer>> map=new HashMap<>();
  
  public PriorityQueue(){
  this(1);
  }
  
  public PriorityQueue(int size){
  heap=new ArrayList<>(size);
  }
  
  public PriorityQueue(T[]arr){
  heap_size=heap_capacity=arr.length;
  heap=new ArrayList<T>(heap_capacity);
  
  for(int i=0;i<heap_size;i++){
  mapAdd(arr[i],i);    
  heap.add(arr[i]); 
    }
  for(int i=Math.max(0,(heap_size/2)-1);i>=0;i--)//heapify process o(n)
     sink(i); 
  }
  
  
  public PriorityQueue(Collection<T> elems){
  this(elems.size());
  for(T elem:elems)
      add(elem);
  }
  
  public int size(){
  return heap_size;
  }
  
  public boolean isEmpty(){ 
  return heap_size==0;
  }
  
  public void clear(){
  for(int i=0;i<heap_capacity;i++)
  heap.set(i, null);
  heap_size=0;
  map.clear();  
  }
  
  public T peek(){
  if(isEmpty())
      return null;
  return heap.get(0);
  }
  
 
  public T poll(){
  return removeAt(0);
  }
 
  
  public boolean contains(T elem){//map lookup to check the conaintment o(1)
  if(elem==null)
      return false;
  
 return map.containsKey(elem);
  }
  
  public void add(T elem){
  if(elem==null)
      throw new IllegalArgumentException();    
  if(heap_size<heap_capacity)
      heap.set(heap_size, elem);
  else{
  heap.add(elem);
  heap_capacity++;
    }
  
  mapAdd(elem,heap_size);
  swim(heap_size);
  heap_size++;
  }
  
 private boolean less(int i,int j){
 T node1=heap.get(i);
 T node2=heap.get(j);
 return node1.compareTo(node2)<=0;
 } 
 
 private void swim(int k){ //upstream 
 int parent=(k-1)/2;
 while(k>0 &&less(k,parent)){
 swap(parent,k);
 k=parent;
 parent=(k-1)/2;
   }
 }
 
 
 private void sink(int k){//downstream
 while(true){
 int left=2*k+1;
 int right=2*k+2;
 int smallest=left;
 
 if(right<heap_size&&less(right,left))
     smallest=right;
 if(left>=heap_size || less(k,smallest))
     break;
 
 swap(smallest,k);
 k=smallest;
   }
 }

 public void swap(int i,int j){
 T i_elem=heap.get(i);
 T j_elem=heap.get(j);
 heap.set(i, j_elem);
 heap.set(j, i_elem);
 
 mapSwap(i_elem,j_elem,i,j);
 }

 
public boolean remove(T elem){
if(elem==null)
    return false;

Integer index=mapGet(elem);
if(index!=null)
    removeAt(index);
 
 return index!=null; 
}
 
 
private T removeAt(int i){
if(isEmpty())
    return null;
heap_size--;
T removed_data=heap.get(i);
swap(i,heap_size);

heap.set(heap_size,null);
mapRemove(removed_data,heap_size);
if(i==heap_size)
    return removed_data;
T elem=heap.get(i);

sink(i);//try sink first

if(heap.get(i).equals(elem))
    swim(i);
return removed_data;
} 
 
public boolean isMinHeap(int k){
if(k>=heap_size)
    return true;

int left=2*k+1;
int right=2*k+2;
if(left<heap_size && !less(k,left))
    return false;
if(right<heap_size && !less(k,right))
    return false;

return isMinHeap(left) && isMinHeap(right);
}

private void mapAdd(T value,int index){
TreeSet<Integer> set=map.get(value);
if(set==null) //insert new value to map
 {
   set=new TreeSet<>();
   set.add(index);
   map.put(value, set);
 }
else
  set.add(index);
}

private void mapRemove(T value,int index){
TreeSet<Integer> set=map.get(value);
set.remove(index);
if(set.size()==0)
    map.remove(value);
}

private Integer mapGet(T value){
TreeSet<Integer> set=map.get(value);
if(set!=null)
    return set.last();
return null;
}

private void mapSwap(T v1,T v2,int v1_index,int v2_index){
Set<Integer>set1=map.get(v1);
Set<Integer>set2=map.get(v2);

set1.remove(v1_index);
set2.remove(v2_index);

set1.add(v2_index);
set2.add(v1_index);
}

  @Override
  public String toString()
{
return heap.toString();
}



}
