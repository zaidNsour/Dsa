
package alg;

import java.util.Stack;


public class Bracket {
    
    
    static boolean is_open_symbol(char ch)
    {
    return ch=='(' ||ch=='{'||ch=='['; 
    }
    
     static boolean is_close_symbol(char ch)
    {
    return ch==')' ||ch=='}'||ch==']'; 
    }
    
    
    static char reversed_bracket(char ch)
    {
    switch(ch){    
    case'}':return'{';
    case')':return'(';
    case']':return'[';  
    }
    return' ';
    }
    
    static boolean brackets_validation(String str)
   {
       
   Stack<Character> stack=new Stack();
    int n=str.length();
    char symbol; 
    
     if(is_close_symbol(str.charAt(0))) 
         return false;
    
    for(int i=0;i<n;i++)
    {
     symbol=str.charAt(i);
        
     if(is_open_symbol(symbol) || stack.isEmpty())
          stack.push(symbol);
     else 
     {
     if(stack.peek()==reversed_bracket(symbol))
        stack.pop();
     else
         stack.push(symbol);
     
     }    
       
    }
    
       return stack.isEmpty();
 }
    
    
    
}
