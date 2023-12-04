
package Ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

 


public class HashTable<K,V> implements Iterable<K> {
    
    
public class Entry<K,V>{
int hash;
K key;
V value;
public Entry(K key,V value){
this.key=key;
this.value=value;
this.hash=key.hashCode();//we can override this method
 }

public boolean equals(Entry<K,V> other){
if(hash!=other.hash)
    return false;
return key.equals(other.key);
 }

@Override
public String toString(){
return key+"=>"+value;
 }

} 


private static final int DEFAULT_CAPACITY=3;
private static final double DEFAULT_LOAD_FACTOR=0.75;
private double max_load_factor;
private int capacity,threshold,size=0;
private LinkedList<Entry<K,V>>[]table;

public HashTable(){
this(DEFAULT_CAPACITY,DEFAULT_LOAD_FACTOR);
}

public HashTable(int capacity){
this(capacity,DEFAULT_LOAD_FACTOR);
}

public HashTable(int capacity,double max_load_factor){
if(capacity<0)
throw new IllegalArgumentException("Illegal capacity!");  
if(max_load_factor<0 || Double.isNaN(max_load_factor) || Double.isInfinite(max_load_factor))
throw new IllegalArgumentException("Illegal max load factor");
      
this.capacity=Math.max(capacity, DEFAULT_CAPACITY);
this.max_load_factor=max_load_factor;
this.threshold=(int)(max_load_factor*this.capacity);
table=new LinkedList[this.capacity];
}

public int size(){
return size;
}
public boolean isEmpty(){
return size==0;
}
        
private int normalizeIndex(int key_hash){
return (key_hash & 0x7FFFFFFF)%capacity;
}

public void clear(){
Arrays.fill(table, null);
size=0;
}

public boolean hasKey(K key){
int bucket_index=normalizeIndex(key.hashCode());
return bucketSeekEntry(bucket_index,key)!=null;
}

public V insert(K key,V value){
if(key==null)
    throw new IllegalArgumentException("null key!");
Entry<K,V>new_entry=new Entry<>(key,value);
int bucket_index=normalizeIndex(new_entry.hash);
return bucketInsertEntry(bucket_index,new_entry);
}

public V get(K key){
if(key==null)
    return null;
int bucket_index=normalizeIndex(key.hashCode());
Entry<K,V>entry=bucketSeekEntry(bucket_index,key);
if(entry!=null)
    return entry.value;
return null;
}

public V remove(K key){
if(key==null)
    return null;
int bucket_index=normalizeIndex(key.hashCode());
return bucketRemoveEntry(bucket_index,key);
}

private V bucketRemoveEntry(int bucket_index,K key){
Entry<K,V>entry=bucketSeekEntry(bucket_index,key);
if(entry!=null){
LinkedList<Entry<K,V>> links=table[bucket_index];
links.remove(entry);
--size;
return entry.value;
  }
else
    return null;
}

private V bucketInsertEntry(int bucket_index,Entry<K,V>entry){
LinkedList<Entry<K,V>>bucket=table[bucket_index];
if(bucket==null)
    table[bucket_index]= bucket =new LinkedList<>();
Entry<K,V>exist_entry=bucketSeekEntry(bucket_index,entry.key);
if(exist_entry==null){
 bucket.add(entry); 
  if(++size>threshold)
    resizeTable(); 
  return null;//use null to indicate that was no previous entry
  }
else{
 V old_val=exist_entry.value;
 exist_entry.value=entry.value;//big respect 
 return old_val; 
  }

}

private Entry<K,V>bucketSeekEntry(int bucket_index,K key){
if(key==null)
    return null;
LinkedList<Entry<K,V>>bucket=table[bucket_index];
if(bucket==null)
    return null;
for(Entry<K,V>entry:bucket)
   if(entry.key.equals(key))
     return entry;
return null;
}

private void resizeTable(){
capacity*=2;
threshold=(int)(capacity*max_load_factor);
LinkedList<Entry<K,V>>[]new_table=new LinkedList[capacity];
for(int i=0;i<table.length;i++){
  if(table[i]!=null){
   for(Entry<K,V>entry:table[i]){
    int bucket_index=normalizeIndex(entry.hash);
    LinkedList<Entry<K,V>> bucket=new_table[bucket_index];
    if(bucket==null)
      new_table[bucket_index]=bucket=new LinkedList<>();  
    bucket.add(entry);
      }//end of for loop
   table[i].clear();//for avoid memory leak
   table[i]=null;
    }//end of if statment
  }//end of for loop
table=new_table;
}


public List keys(){
List<K>keys=new ArrayList<>(size());
for(LinkedList<Entry<K,V>>bucket:table)
  if(bucket!=null)
    for(Entry<K,V> entry:bucket)
       keys.add(entry.key);
return keys;
}

public List Values(){
List<V>values=new ArrayList<>(size());
for(LinkedList<Entry<K,V>>bucket:table)
  if(bucket!=null)
    for(Entry<K,V> entry:bucket)
       values.add(entry.value);
return values;
}

@Override
public String toString(){
StringBuilder sb=new StringBuilder();
sb.append("{");
for(int i=0;i<capacity;i++){
if(table[i]==null)
    continue;
for(Entry<K,V> entry:table[i])
    sb.append(entry+",");
 }
sb.append("}");
return sb.toString();
}

 @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEach(Consumer<? super K> cnsmr) {
        Iterable.super.forEach(cnsmr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<K> spliterator() {
        return Iterable.super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }
 
 
}
