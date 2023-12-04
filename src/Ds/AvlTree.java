/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ds;

/**
 *
 * @author DELL
 * 
 * 
 */


public class AvlTree<T extends Comparable<T>> {
   
  private class Node{
   int bf;
   int height;
   T value;
   Node left,right;
   public Node(T value){
   this.value=value;
   } 
 }
  
  private Node root;
   private int nodeCount=0;
   
   public int height(){
   if(root==null)
     return 0;
   return root.height;
   }
   
   public int size(){
   return nodeCount;
   }
   
   public boolean isEmpty(){
   return size()==0;
   }
   
   public boolean contains(T value){
   return contains(root,value);
   }
   
   private boolean contains(Node node,T value){
   if(node==null) return false;
   int cmp=value.compareTo(node.value);
   if(cmp<0)
      return contains(node.left,value);
   else if(cmp>0)
      return contains(node.right,value);
   
   return true;
   }
   
   public boolean insert(T value){
   if(value==null)
     return false;
   if(!contains(root,value)){
   root=insert(root,value);
   nodeCount++;
   return true;
    }
   return false;
   }
   
   private Node insert(Node node,T value){
   if(node==null) return new Node(value);      
   int cmp=value.compareTo(node.value);
   if(cmp<0)
     node.left=insert(node.left,value);
   else
    node.right=insert(node.right,value);
   update(node); //update bf and height values
   return balance(node); //re-balance tree
   }
   
  private void update(Node node){
  int leftNodeHeight=(node.left==null)?-1:node.left.height;
  int rightNodeHeight=(node.right==null)?-1:node.right.height;
  node.height=Math.max(leftNodeHeight, rightNodeHeight)+ 1 ;
  node.bf=rightNodeHeight-leftNodeHeight;
  } 
  
  private Node balance(Node node){
  if(node.bf==-2){ //left heavy sub-tree
    if(node.left.bf<=0)
      return leftLeftCase(node);
    else
      return leftRightCase(node); 
    }
  else if(node.bf==+2){ //right heavy sub-tree
    if(node.right.bf>=0)
      return rightRightCase(node);
    else
      return rightLeftCase(node);
  }
  
  return node;
  }
  
  private Node leftLeftCase(Node node){
  return rightRotation(node);  
  }
  
  private Node leftRightCase(Node node){
  node.left=leftRotation(node.left);
  return leftLeftCase(node);  
  }
  
  private Node rightRightCase(Node node){
  return leftRotation(node);
  }
  
  private Node rightLeftCase(Node node){
  node.right=rightRotation(node.right);
  return rightRightCase(node);
  }
  
  private Node leftRotation(Node node){
  Node newParent=node.right;
  node.right=newParent.left;
  newParent.left=node;
  update(node);
  update(newParent);
  return newParent;
  }
  
   private Node rightRotation(Node node){
  Node newParent=node.left;
  node.left=newParent.right;
  newParent.right=node;
  update(node);
  update(newParent);
  return newParent;
  }
   
  public boolean remove(T elem){
  if(elem==null) return false;
  if(contains(root,elem)){
     root=remove(root,elem);
     nodeCount--;
     return true;
    }
  return false; 
  }
  
  private Node remove(Node node,T elem){
  if(node==null) return null;
  int cmp=elem.compareTo(node.value);
  if(cmp<0)
    node.left=remove(node.left,elem);
  if(cmp>0)
    node.right=remove(node.right,elem);
  else{
      if(node.left==null) //node has right or no subtree 
        return node.right;
      else if(node.right==null)//node has a left subtree
        return node.left;
      else{ //node has both left and right subtree
        if(node.left.height>node.right.height){
          T succesorValue=findMax(node.left);
          node.value=succesorValue;
          node.left=remove(node.left,succesorValue);
       }
        else{
         T succesorValue=findMin(node.right); 
         node.value=succesorValue;
         node.right=remove(node.right,succesorValue);
       } 
    }
  }
 update(node);
 return balance(node); 
}
  
private T findMin(Node node){
while(node.left!=null)
    node=node.left;
return node.value;
}

private T findMax(Node node){
while(node.right!=null)
    node=node.right;
return node.value;
}

 public void inOrder(){
    inOrder(root);
  }
  
  
private void inOrder(Node node){
  if(node==null)
      return;    
  inOrder(node.left);
  System.out.print(node.value+" "); 
  inOrder(node.right);    
  }   
  
  
 
   
    
    
}

