package com.Test;

public class DynamicStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static class CustomStack {
	    int[] data;
	    int tos;

	    public CustomStack(int cap) {
	      data = new int[cap];
	      tos = -1;
	    }

	    int size() {
	      return tos+1;
	    }

	    void display() {
	        
	      for(int i=this.size()-1; i>=0;i--){
	          System.out.print(data[i]+" ");
	      }
	      System.out.println();
	    }

	    void push(int val) {
		if (tos == data.length - 1) {
			int[] ndata = new int[2 * data.length];
			for (int i = 0; i < data.length; i++) {
				ndata[i] = data[i];
			}
			data = ndata;
			tos++;
			data[tos] = val;
		} else {
			tos++;
			data[tos] = val;
		}
	    }

	    int pop() {
	      if(size()==0){
	          System.out.println("Stack underflow");
	          return -1;
	      }else{
	          int val = data[tos];
	          tos--;
	          return val;
	      }
	    }

	    int top() {
	       if(size()==0){
	          System.out.println("Stack underflow");
	          return -1;
	      }else{
	          int val = data[tos];
	          return val;
	      }
	    }
	  }

}
