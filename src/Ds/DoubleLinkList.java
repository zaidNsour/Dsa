/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ds;

/**
 *
 * @author DELL
 */
public class DoubleLinkList<T> {
    
    private class node<T>{
        
     T data;
     node<T> prev,next;
     
     public node(T data,node<T> prev,node<T> next)
     {
     this.data=data;
     this.prev=prev;
     this.next=next;
     }
     
     @Override
     public String toString()
     {
         return data.toString();
     }
    }
    
    private int size=0;
    private node<T> head=null;
    private node<T> tail=null;
    
    public void clear()
    {
    node<T> trav=head;
    while(trav!=null){
    node<T> next=trav.next;
    trav.prev=trav.next=null;
    trav.data=null;
    trav=next;
    }
    head=tail=trav=null;
    size=0;
    }
    
    public int size()
    {
    return size;
    }
    
    public boolean isEmpty()
    {
    return size()==0;
    }
    
    public void addFirst(T elem)
    {
    if(isEmpty())
      head=tail=new node<T>(elem,null,null); 
    else
      {
      head.prev=new node<T>(elem,null,head);
      head=head.prev;
      }
    
    size++;
    }
    
    public void addLast(T elem)
    {
    if(isEmpty())
     head=tail=new node<T>(elem,null,null); 
    else
     {
    tail.next=new node<T>(elem,tail,null);
    tail=tail.next;
     }
    
    size++;
    }
    
    public T peekFirst()
    {
    if(isEmpty())
        throw new RuntimeException("Empty list!");
    return head.data;
    }
    
   public T peekLast()
    {
    if(isEmpty())
        throw new RuntimeException("Empty list!");
    return tail.data;
    }
   
   public T removeFirst()
   {
   if(isEmpty())
     throw new RuntimeException("Empty list!");
   T data=head.data;
   head=head.next;
   --size;
   if(isEmpty())
    tail=null;
   else
    head.prev=null;//do a memory clean of the prev node   
     
   return data;
   }
   
   public T removeLast()
   {
    if(isEmpty())
     throw new RuntimeException("Empty list!");
    T data=tail.data;
    tail=tail.prev;
    --size;
    if(isEmpty())
      head=null;
    else
        tail.next=null;
    
    return data;
   }
   
   private T remove(node<T> n)
   {
   if(n.prev==null)
      return removeFirst();
   if(n.next==null)
       return removeLast();
   
   n.next.prev=n.prev;
   n.prev.next=n.next;
   
  T data=n.data;
  n.data=null;
  n.prev=n.next=null;
  --size;
  
  return data;
   }
   
   public T removeAt(int index)
   {
   if(index<0 || index>=size())
   throw new IllegalArgumentException();
   
   int i;
   node<T> trav;
   
   if(index<size/2)
   {
     for( i=0,trav=head;i!=index;i++)
         trav.next=trav;
   }
   
   else
   {
   for( i=size()-1,trav=tail;i!=index;i--)
         trav.prev=trav;
   }
   
   return remove(trav);
   
   }
   
   public boolean remove(Object n)
   {
   node<T> trav=head;
   while(trav!=null)
    {
   if(n.equals(trav)) 
      {
      remove(trav);
      return true;
      }
    }
   return false;
   }
   
   public int indexOf(Object n)
   {
   int index=0;
   node<T> trav=head;
   for(trav=head;index<size-1;index++,trav=trav.next)
    {
     if(n.equals(trav.data))
      return index;
    }
   return -1;
   }
   
    @Override
   public String toString()
   {
      StringBuilder sb=new StringBuilder(); 
      sb.append("[");
      node<T> trav=head;
      while(trav!=null)
      {
      sb.append(trav.data+",");
      trav=trav.next;
      }
      sb.append("]");
      return sb.toString();
   }
    
    
}
