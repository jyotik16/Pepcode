package com.Test;

public class NormalQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static class CustomQueue {
	    int[] data;
	    int front;
	    int size;

	    public CustomQueue(int cap) {
	      data = new int[cap];
	      front = 0;
	      size = 0;
	    }

	    int size() {
	    	return size;
	    }

	    void display() {
	    	 for(int i=0;i<this.size;i++) {
		    	  int indx = (i + front) % data.length;
		    	  System.out.print(data[indx]+" ");
		      }
		      System.out.println();
	    }

	    void add(int val) {
	      if(size() == data.length) {
	    	  System.out.print("Queue overflow");
	      }else {
	    	  int indx = (front + size)%data.length;
	    	  data[indx] = val;
	    	  size++;
	      }
	    }

	    int remove() {
	      // write ur code here
	    	if(size() == 0) {
		    	  System.out.print("Queue underflow");
		    	  return -1;
		      }else {
		    	  int val = data[front];
		    	  front = (front + 1)%data.length;
		    	  size--;
		    	  return val;
		      }
	    }

	    int peek() {
	    	 if(size() == 0) {
		    	  System.out.print("Queue underflow");
		    	  return -1;
		      }else {
		    	  int val = data[front];		    	  
		    	  return val;
		      }
	    }
	  }

}
