/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author DELL
 */
public class SuffixArray {
    
    
private int n;
private int[] text;
private int[]sa;
private int[]lcp;
private boolean constructed_sa=false;
private boolean constructed_lcp=false;
private String str;

public SuffixArray(String str){   
 this(toIntArray(str)); 
 this.str=str;

}

public SuffixArray(int[]text){
if(text==null)
    throw new IllegalArgumentException("Text cannot be null!");
this.text=text;
this.n=text.length;
}



public int getTextLength(){
return n;
}

public int[] getSa(){
buildSuffixArray();
return sa;
}

public int[] getLcpArray(){
buildLcpArray();
return lcp;
}

private void buildSuffixArray(){
if(constructed_sa)
    return;
construct();
constructed_sa=true;
}

private void buildLcpArray(){
if(constructed_lcp)
    return;
buildSuffixArray();
kasai();
constructed_lcp=true;
}

private static int[]toIntArray(String s){
if(s==null)
  return null;
int[]arr=new int[s.length()];
for(int i=0;i<s.length();i++)
  arr[i]=s.charAt(i);
return arr;
}


public void construct(){ 

int[]ind=new int[n];    
Suffix suffx[]=new Suffix[n];


for(int i=0;i<n;i++)
    suffx[i]=new Suffix(i,text[i]-'$',0);

for(int i=0;i<n;i++){
   suffx[i].next=(i+1<n ? suffx[i+1].rank:-1);
}

Arrays.sort(suffx);

for(int len=4;len<2*n;len*=2){
   int rankValue=0,prev=suffx[0].rank;
   suffx[0].rank=rankValue;
   ind[suffx[0].index]=0;

   for(int i=1;i<n;i++){
      if(suffx[i].rank==prev && suffx[i].next==suffx[i-1].next){
         prev=suffx[i].rank;
         suffx[i].rank=rankValue;
      }
      else{
         prev=suffx[i].rank;
         suffx[i].rank=++rankValue;
      }
      ind[suffx[i].index]=i;   
   }
   
   for(int i=0;i<n;i++){
      int nextP=suffx[i].index +len/2;
      suffx[i].next=nextP<n?suffx[ind[nextP]].rank:-1;
   }
   
   Arrays.sort(suffx);

}

sa=new int[n];

for(int i=0;i<n;i++){
sa[i]=suffx[i].index;
}
    
}

 private void kasai() {
    lcp = new int[n];
    int[] inv = new int[n];
    for (int i = 0; i < n; i++) inv[sa[i]] = i;
    for (int i = 0, len = 0; i < n; i++) {
      if (inv[i] > 0) { 
        int k = sa[inv[i] - 1];
        while ((i + len < n) && (k + len < n) && text[i + len] == text[k + len]) len++;
        lcp[inv[i]] = len;
        if (len > 0) len--;
      }
    }
  }
 
 
 public List<String>LRS(){
     
 buildLcpArray();    
 List<Integer>lrs_pos=new ArrayList<>();
 List<String>lrs=new ArrayList<>();
 int maxIndex=0;
 //lrs_pos.add(0);
 for(int i=1;i<lcp.length;i++){
    if(lcp[i]>lcp[maxIndex]){
      lrs_pos.clear();
      maxIndex=i;
      lrs_pos.add(i);  
    }
    else if(lcp[i]==lcp[maxIndex]){  
      lrs_pos.add(i);
    } 
   }
 
  for(int i:lrs_pos)
    lrs.add(str.substring(sa[i], sa[i]+lcp[i]));
  
  return lrs;
  
 }


public class Suffix implements Comparable<Suffix>{
int index;
int rank;
int next;
public Suffix(int index,int rank,int next){
this.index=index;
this.rank=rank;
this.next=next;
}

@Override
public int compareTo(Suffix s){
if(rank!=s.rank)
   return Integer.compare(rank, s.rank);
return Integer.compare(next, s.next);
}


}
    
}
