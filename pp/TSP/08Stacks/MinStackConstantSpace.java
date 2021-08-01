package com.Test;

import java.util.Stack;

public class MinStackConstantSpace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static class MinStack {
	    Stack<Integer> data;
	    int min;

	    public MinStack() {
	      data = new Stack<>();
	    }

	    int size() {
	      return data.size();
	    }

	    void push(int val) {
	        if(data.size()==0){
	            data.push(val); 
	            min = val; 
	            return;
	        }
	      if(val<min){
	          data.push(val+val-min);
	          min = val;
	      }else{
	          data.push(val);
	      }
	    }

	    int pop() {
	      // write your code here
	      if(data.size()==0){
	          System.out.println("Stack underflow");
	          return -1;
	      }else{
	          if(data.peek() < min){
	              int val = min;
	              min = 2*val-data.pop();
	              return val;
	          }else{
	              return data.pop();
	          }
	          
	      }
	    }

	    int top() {
	      // write your code here
	      if(data.size()==0){
	          System.out.println("Stack underflow");
	          return -1;
	      }else{
	          if(data.peek()< min){
	              return min;
	          }else{
	              return data.peek();
	          }
	      }
	    }

	    int min() {
	      // write your code here
	      if(data.size()==0){
	          System.out.println("Stack underflow");
	          return -1;
	      }else{
	          return min;
	      }
	    }
	  }

}
