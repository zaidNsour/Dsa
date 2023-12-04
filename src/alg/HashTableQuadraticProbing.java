
package alg;

import java.util.ArrayList;
import java.util.List;




public class HashTableQuadraticProbing<K,V> {
   
    private double load_factor;
    private int capacity,threshold;
    private int used_buckets=0;
    private int key_count=0;
    private K[] keys;
    private V[] values;
    private boolean contain_flag=false;
    private final K TOMBSTONE=(K)new Object(); 
    private static final int DEFAULT_CAPACITY=8;
    private static final double DEFAULT_MAX_FACTOR=0.45;
    
    public HashTableQuadraticProbing(){
    this(DEFAULT_CAPACITY,DEFAULT_MAX_FACTOR);
    }
    public HashTableQuadraticProbing(int capacity){
    this(capacity,DEFAULT_MAX_FACTOR);
    }
    public HashTableQuadraticProbing(int capacity,double load_factor){
    if(capacity<=0)
        throw new IllegalArgumentException("Illegal capacity!");
    if(load_factor<=0 || Double.isNaN(load_factor) || Double.isInfinite(load_factor))
        throw new IllegalArgumentException("Illegal load factor!");
    
    this.load_factor=load_factor;
    this.capacity=Math.max(DEFAULT_CAPACITY, next2Power(capacity) );
   threshold=(int)(this.capacity*load_factor);
   
   keys=(K[])new Object[this.capacity];
   values=(V[])new Object[this.capacity];
    }
    
    private static int next2Power(int n){
    return Integer.highestOneBit(n)<< 1;
    }
    
    private static int p(int x){
    return (x*x+x)>>1;
    }
    
    private int normalizeIndex(int key_hash){
    return(key_hash & 0x7FFFFFFF)%capacity;
    }
    
    public void clear(){
    for(int i=0;i<capacity;i++){
    values[i]=null;
    keys[i]=null;
     }
    key_count=used_buckets=0;
    }
    
    public int size(){
    return key_count;
    }
    
    public boolean isEmpty(){
    return key_count==0;
    }
    
    
    public V insert(K key,V value){
    if(key==null)
        throw new IllegalArgumentException("null key!");
    if(used_buckets>=threshold)
        resizeTable();
   final int hash=normalizeIndex(key.hashCode());
   int i= hash, j= -1, x= 1; 
   
   do{
       
   if(keys[i]==TOMBSTONE){ //the current slot was previously deleted
    if(j==-1)  j=i;
    }
   
   else if(keys[i]!=null){ //current slot already contain key   
     if(keys[i].equals(key)){
     V old_val=values[i];
      if(j==-1)
      values[i]=value;
      else{
      keys[i]=TOMBSTONE; //swap with last tombstone
      values[i]=null;
      keys[j]=key;
      values[j]=value;
      }
     return old_val;
     }                   
   }
   
   else{ //current slot is null
    if(j==-1){
     used_buckets++;
     key_count++;
     keys[i]=key;
     values[i]=value;
    }
   else{
    key_count++;
    keys[i]=key;
    values[i]=value;
    }
   return null; //to indicate that are no prev element 
   }
   
   i=normalizeIndex(hash + p(x++)); //when there is another key keep probing 
  }
   
 while(true);   
 }  
    
 public boolean containKey(K key){
 get(key);
 return contain_flag;
 }   
 
 public V get(K key){
 if(key==null)
     throw new IllegalArgumentException("null key!");
 final int hash=normalizeIndex(key.hashCode());
 
 int i=hash,j=-1,x=1;
 do{
     
  if(keys[i]==TOMBSTONE){
   if(j==-1) j=i;
   }
  
  else if(keys[i]!=null){
      
   if(keys[i].equals(key)){
    contain_flag=true;
     if(j!=-1){
       keys[j]=keys[i];
       values[j]=values[i];
       keys[i]=TOMBSTONE;
       values[i]=null;
       return values[j];
     }
     else
      return values[i];     
    } 
   }
  
  else{
   contain_flag=false;
   return null;
   }
  
  i=normalizeIndex(hash+p(x++));
  
  }while(true);
 
 }
    
 
 public V remove(K key){
 if(key==null)
   throw new IllegalArgumentException("null key!"); 
 final int hash=normalizeIndex(key.hashCode()); 
 
 int i=hash,x=1;
 for(;;i=normalizeIndex(hash+p(x++))){
  if(keys[i]==TOMBSTONE) continue;
  if(keys[i]==null) return null;
  if(keys[i].equals(key)){
    key_count--;
    V old_val=values[i];
    keys[i]=TOMBSTONE;
    values[i]=null;
    return old_val;
   } 
  }
 
 }
 
 public void resizeTable(){
 capacity*=2;
 threshold=(int)(capacity*load_factor);
 K[] old_keys=(K[])new Object[capacity];
 V[] old_values=(V[])new Object[capacity];
 
 K[] temp_keys=keys; //swapping keys pointer
 keys=old_keys;
 old_keys=temp_keys;
 
 V[] temp_values=values;
 values=old_values;
 old_values=temp_values;
 
 key_count=used_buckets=0;
 
 for(int i=0;i<old_keys.length;i++){
   if(old_keys[i]!=null&&old_keys[i]!=TOMBSTONE)
     insert(old_keys[i],old_values[i]);
   old_keys[i]=null;
   old_values[i]=null;
  }
 
 }
 
 public List<K> keys(){
 List<K>list=new ArrayList<>(size());
 for(int i=0;i<capacity;i++)
   if(keys[i]!=null && keys[i]!=TOMBSTONE)
     list.add(keys[i]); 
 return list;
 }
 
 public List<V> values(){
 List<V>list=new ArrayList<>(size());
 for(int i=0;i<capacity;i++)
     if(keys[i]!=null && keys[i]!=TOMBSTONE)
       list.add(values[i]);
 return list;
 }
 
 
 
 
 
}


