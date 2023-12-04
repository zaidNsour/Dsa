
package Ds;


public class UnionFind{
    
private int size;//number of elements in this union find 
public int[]sz;//the size of each of the  components
private int[]id; // if id[i]=i the i is the root 
private int num_components;

public UnionFind(int size){
    if(size<0)
        throw new IllegalArgumentException("size<=0 is not allowed!");
    this.size=this.num_components=size;
    sz=new int[size];
    id=new int[size];
    
    for(int i=0;i<size;i++){
    id[i]=i;
    sz[i]=1;
  }
}

public int find(int p){
int root=p;
while(root!=id[root])
    root=id[root];

while(p!=root){
int next=id[p];
id[p]=root;
p=next;
  } 
return root;
}

public boolean connected(int p,int q){
return find(p)==find(q);
}

public int componentSize(int p){
return sz[find(p)];
}

public int size(){
return size;
}

public int numComponents(){
return num_components;
}

public void unify(int p,int q){
int root1=find(p);
int root2=find(q);

if(root1==root2)//two root in the same group
    return;
if(sz[root1]<sz[root2]){
  sz[root2]+=sz[root1];
  id[root1]=root2;  
  }
else{
  sz[root1]+=sz[root2];
  id[root2]=root1; 
 }
num_components--;

}


}
