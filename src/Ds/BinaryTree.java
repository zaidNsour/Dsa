/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ds;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author DELL
 */
public class BinaryTree<T extends Comparable<T>>{
    
    public class Node{
    T data;
    Node left,right;
    
    public Node(Node left,Node right,T elem){
    this.data=elem;
    this.left=left;
    this.right=right;
     }
    }
    
    
  private int node_count=0;
  private Node root=null;
  
  public int size(){
  return node_count;
  }
  
  public boolean isEmpty(){
  return size()==0;
  }
  
  public boolean add(T elem){
  if(contains(elem))
      return false;
  root=add(root,elem);
  node_count++;
  return true;
  }
  
  
  private Node add(Node node,T elem){
  if(node==null)
    return node=new Node(null,null,elem);//base case
  
  if(elem.compareTo(node.data)<=0){
      node.left=add(node.left,elem);
  }else if(elem.compareTo(node.data)>0){
      node.right=add(node.right,elem);
  }
  return node;  
  }
  
  
  
  public boolean remove(T elem){
  if(contains(elem)){     
  root=remove(root,elem);
  node_count--;
  return true;
  }
  return false;
  }
  
  private Node remove(Node node,T elem){
  if(node==null)
      return null;
  
  int cmp=elem.compareTo(node.data);
  if(cmp<0)
      node.left=remove(node.left,elem);
  else if(cmp>0)
      node.right=remove(node.right,elem);
  else{ //when find the node
      if(node.left==null){ // when only a right subtree or no subtree at all (leaf node)
          Node right_child=node.right;
          node.data=null;
          node=null;
          return right_child;
      }
      else if(node.right==null){
          Node left_child=node.left;
          node.data=null;
          node=null;
          return left_child;         
      }
      else{
        Node tmp=digLeft(node.right);
        node.data=tmp.data;
        node.right=remove(node.right,tmp.data);    
     }
      
    }
  return node;  
  }
  
  private Node digLeft(Node node){
  Node succ=node.right;
  while(node.left!=null)
      succ=succ.left;
  return succ;
  }
  
  public boolean contains(T elem){
  return contains(root,elem);
  }
  
  private boolean contains(Node node,T elem){
  if(node==null)
      return false;
  int cmp=elem.compareTo(node.data);
  if(cmp<0)
    return  contains(node.left,elem);
  else if(cmp>0)
    return  contains(node.right,elem);
  else
      return true;
  }
  
  public int height(){
  return height(root);
  }
  
  private int height(Node node){
  if(node==null)
      return 0;
  return Math.max( height(node.left),height(node.right) )+1;
  
  }
  
  public void preOrder(){
    preOrder(root);
  }
  
  public void inOrder(){
    inOrder(root);
  }
  
  public void postOrder(){
  postOrder(root);
  }
  
  private void inOrder(Node node){
  if(node==null)
      return;    
  inOrder(node.left);
  System.out.print(node.data+" "); 
  inOrder(node.right);    
  } 
 
  
  private void preOrder(Node node){
  if(node==null)
      return;
    
  System.out.print(node.data+" "); 
  preOrder(node.left);
  preOrder(node.right);    
  } 
  
  private void postOrder(Node node){
  if(node==null)
      return;
   postOrder(node.left);
   postOrder(node.right); 
   System.out.print(node.data+" ");
  } 
  
public void inOrderUsingStack(){
  Stack<Node>stack=new Stack<>();
  HashMap<Node, Boolean> map = new HashMap<>();
  //map.getOrDefault(map, Boolean.FALSE);
  stack.push(root);
  
  while(!stack.empty()){
    Node current=stack.peek();
    if(current.left!=null && !map.getOrDefault(current.left, Boolean.FALSE)){
      stack.push(current.left);
      }
    else if(current.right!=null && !map.getOrDefault(current.right, Boolean.FALSE)){
      map.put(current, Boolean.TRUE);
      System.out.print(current.data+"   ");
      stack.pop();
      stack.push(current.right);
      }
    else{
      System.out.print(current.data+"   ");
      map.put(current, Boolean.TRUE);
      stack.pop();
      }
    }
 
  
  }
  
  
  public void dfs(){
    Stack<Node>stack=new Stack<>(); 
    stack.push(root); 

    while(!stack.empty()){
      Node current=stack.pop();
      System.out.print(current.data+"   ");
      
      if( current.left!=null)
        stack.push(current.left);
      
      if( current.right!=null)
        stack.push(current.right);
    
      }
  }
    
  
  public void bfs(){
  Queue<Node>queue=new LinkedList<>();
  queue.add(root);
  
  while(!queue.isEmpty()){
    Node current=queue.poll();
    System.out.print(current.data+"   ");  
    if( current.left!=null)
     queue.add(current.left);  
    if( current.right!=null)
     queue.add(current.right); 
    }
  
   }
  
  
  
  
  
}
