package com.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class Kpartitions {
	/*
	 * 	subarray -> continuous and relative order | n(n+1)/2
		Subsequence ->not continuous , only relative order |2^n-1
		subsets->not continuous , no relative order, only part of array
	 */

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] ar = { 2, 7, 5, 8, 1 };
		int[] br = { 10, 20, 30 };
		// partitions(ar,6);
		// display(ar);
		// find 4 th largest element
		// quickSelect(ar, 3, 0, ar.length - 1);
		// quicksort(ar,0,ar.length-1);
		// display(ar);
		// rotateArrayByk(ar,3);
		// MergeTwoSortedArray(ar,ar);
		// ar = mergeSort(ar,0,ar.length-1);
		// subsetsOfarr(br); //subsequence==subsets
		//subArray(br, 3);
		// display(ar);
		// ceilAndFloorInArray(ar, 33);
		int[] ar = { 10, 5, 9, 1, 11, 8, 6, 15, 5, 12, 2 };
		// longestSubsequenceLength(ar);
		//findAllKSmallestElements(ar, 3);
		// display(ar);
		Integer [][] cr = { {1,3},{2,4},{6,8},{10,14},{7,9}};
		//mergeOverLappingIntervals(cr);
		//KSum(ar,20,3);
	}
	private static void KSum(int[] ar, int target, int k) {
		
		Arrays.sort(ar);
		List<List<Integer>> ans = _KSum(ar,target,0,k);
		System.out.println(ans);
//		ans.stream().
//		forEach(list->list.stream().forEach(l->System.out.print(l+",")));
		
	}

	private static List<List<Integer>> _KSum(int[] ar, int target, int si, int k) {
		if(k == 2) {
			return twoSum(ar,target,si,ar.length-1);
		}
		int n = ar.length;
		List<List<Integer>> res = new ArrayList<>();
		for(int i = si; i<n-(k-1);i++) {
			if(i != si && ar[i] == ar[i-1]) {
				continue;
			}
			int val1 = ar[i]; int targ = target-val1;
			List<List<Integer>> subres = _KSum(ar,targ,i + 1,k-1);
			
			for(List<Integer> list:subres) {
				list.add(val1); //val1 add 
				res.add(list); //rem add in main list
			}
		}
		return res;
	}

	private static List<List<Integer>> twoSum(int[] ar, int target, int si, int end) {
		List<List<Integer>> ans = new ArrayList<>();
		int left = si; int right = end;
		while(left<right) {
			if(left != si && ar[left] == ar[left - 1]) {
                left++;
                continue;
            }
			int sum = ar[left] + ar[right];
			if(sum == target) {
				List<Integer> subres = new ArrayList<>();
                subres.add(ar[left]);
                subres.add(ar[right]);
                ans.add(subres);

                left++;
                right--;
			}else if(sum>target) {
				right--;
			}else {
				left++;
			}
		}
		return ans;
	}

	private static void mergeOverLappingIntervals(Integer[][] cr) {
		Stack<Pair> st = new Stack<Pair>();
		Arrays.sort(cr,(val1, val2) -> Integer.compare(val1[0], val2[0]));
		
		st.push(new Pair(cr[0][0],cr[0][1]));
		
		Pair[] pairs = new Pair[cr.length];
        
		for (int i = 0; i < cr.length; i++) {
            pairs[i] = new Pair(cr[i][0], cr[i][1]);
        }
      //  Arrays.sort(pairs,(a,b)->a.compareTo(b));
		for(int i = 1; i < pairs.length; i++) {
			Pair top = st.peek();
			Pair p = pairs[i];
			
			if(p.sp<top.sp) {
				if(p.ep>top.ep) {
					top.ep = p.ep;
				}
			}else {
				st.push(p);
			}
		}		
		
		while (st.size() > 0) {
            Pair rem = st.pop();
            System.out.println(rem.sp + " " + rem.sp);
        }
		
	}

	static class  Pair implements Comparable<Pair>{
		int sp;
		int ep;
		Pair(int sp,int ep){
			this.sp = sp;
			this.ep = ep;
		}
		
		
		  public int compareTo(Pair other)
		  {	if(this.sp!=other.sp)
		  			return this.sp-other.sp;
		  		else
		  			return this.ep-other.sp;
		  }
		 
	}
	
	//only using 2 D array
	public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (val1, val2) -> Integer.compare(val1[0], val2[0]));
        ArrayList<int[]> list = new ArrayList<>();
        int lsp = intervals[0][0]; // last interval starting point
        int lep = intervals[0][1]; // last interval ending point

        for(int i = 1; i < intervals.length; i++) {
            int sp = intervals[i][0];
            int ep = intervals[i][1];
            
            if(lep < sp) {
                // new interval is found
                int[] sublist = {lsp, lep};
                list.add(sublist);
                lsp = sp;
                lep = ep;
            } else if(lep < ep){
                // partially overlapping
                lep = ep;
            } else {
                // fully overlapping -> nothing to do
            }
        }
        int[] sublist = {lsp, lep};
        list.add(sublist);
        return list.toArray(new int[list.size()][]);
    }

	private static ArrayList<Integer> sieve(int n) {
		boolean[] arr = new boolean[n + 1];
		// false->prime
		for (int i = 2; i * i <= n; i++) {
			if (arr[i] == false) {
				for (int j = 2 * i; j < arr.length; j += i) {
					arr[j] = true;
				}
			}
		}
		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 2; i <= n; i++) {
			if (arr[i] == false) {
				ans.add(i);
			}
		}
		return ans;
	}

	private static void findAllKSmallestElements(int[] ar, int k) {
		// TODO Auto-generated method stub
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		// Step1
		for (int i = 0; i < ar.length; i++) {
			if (i < k) {
				pq.add(ar[i]);
			} else {
				if (ar[i] < pq.peek()) {
					pq.remove();
					pq.add(ar[i]);
				}
			}
		}

		// Step2
		while (pq.size() > 0) {
			System.out.print(pq.remove() + " ");
		}
	}

	
	private static void ceilAndFloorInArray(int[] ar, int num) {
		// TODO Auto-generated method stub
		//10 20 30 40 50 ,, 33-> 30 40
		int ceil = 0;
		int floor = 0;
		int i = 0;
		int j = ar.length - 1;
		while (i <= j) {
			int mid = (i + j) / 2;
			if (num<ar[mid]) {
				ceil = ar[mid];
				j = mid - 1;
			} else if (num > ar[mid]) {
				floor = ar[mid];
				i = mid + 1;
			} else {
				ceil = floor = ar[mid];
				break;
			}

		}
		System.out.println(floor + " " + ceil);
	}


	static void subArray(int[] arr, int n) {

		for (int i = 0; i < n; i++) {

			for (int j = i; j < n; j++) {

				for (int k = i; k <= j; k++)
					System.out.print(arr[k] + " ");
				
			}
			System.out.println();
		}
	}

	private static void subsetsOfarr(int[] ar) {
		// TODO Auto-generated method stub
		long limit = (long) Math.pow(2, ar.length);
		for (int i = 0; i < limit; i++) {
			String set = "";
			int temp = i;
			for (int j = ar.length - 1; j >= 0; j--) {
				int r = temp % 2;
				temp = temp / 2;
				if (r == 0) {
					set = "- \t" + set;
				} else {
					set = ar[j] + "\t" + set;
				}
			}
			System.out.println(set);
		}
	}

	private static int[] mergeSort(int[] ar, int lo, int hi) {
		// TODO Auto-generated method stub
		if (lo == hi) {
			int[] ba = new int[1];
			ba[0] = ar[lo];
			return ba;
		}
		int mid = (hi + lo) / 2;
		int[] fsh = mergeSort(ar, lo, mid);
		int[] ssh = mergeSort(ar, mid + 1, hi);
		int[] fsa = MergeTwoSortedArray(fsh, ssh);
		return fsa;
	}

	private static int[] MergeTwoSortedArray(int[] arr1, int[] arr2) {
		int s1 = arr1.length;
		int s2 = arr2.length;

		int[] res = new int[s1 + s2];

		int i = 0; // itr for arr1
		int j = 0; // itr for arr2
		int k = 0; // help to fill the res

		while (i < s1 || j < s2) {
			int ival = i < s1 ? arr1[i] : Integer.MAX_VALUE;
			int jval = j < s2 ? arr2[j] : Integer.MAX_VALUE;

			if (ival > jval) {
				res[k++] = jval;
				j++;
			} else {
				res[k++] = ival;
				i++;
			}
		}
		return res;
	}

	private static void rotateArrayByk(int[] ar, int k) {
		// TODO Auto-generated method stub
		k = k % ar.length;
		if (k < 0) {
			k = k + ar.length;
		}
		reverse(ar, 0, ar.length - k - 1);
		reverse(ar, ar.length - k, ar.length - 1);
		reverse(ar, 0, ar.length - 1);
	}

	private static void reverse(int[] ar, int lo, int hi) {
		// TODO Auto-generated method stub
		int i = lo;
		int j = hi;
		while (i < j) {
			int temp = ar[i];
			ar[i] = ar[j];
			ar[j] = temp;
			i++;
			j--;
		}

	}

	private static void quicksort(int[] ar, int lo, int hi) {
		// TODO Auto-generated method stub
		if (lo >= hi) {
			return;
		}
		int pivot = ar[hi];
		int indx = partitionElementIndex(ar, pivot, lo, hi);
		quicksort(ar, lo, indx - 1);
		quicksort(ar, indx + 1, hi);
	}

	private static int quickSelect(int[] ar, int k, int lo, int hi) {
		// TODO Auto-generated method stub
		int pivot = ar[hi];
		int indx = partitionElementIndex(ar, pivot, lo, hi);
		if (k > indx) {
			return quickSelect(ar, k, indx + 1, hi);
		} else if (k < indx) {
			return quickSelect(ar, k, lo, indx - 1);
		} else {
			System.out.println(k + " th largest element " + pivot);
			return ar[indx];
		}

	}

	private static int partitionElementIndex(int[] ar, int pivot, int lo, int hi) {
		// TODO Auto-generated method stub
		// 0 ->j-1 <= p, j->i >p
		int j = lo;
		int i = lo;
		while (i <= hi) {
			if (ar[i] > pivot) {
				i++;
			} else {
				int temp = ar[i];
				ar[i] = ar[j];
				ar[j] = temp;
				i++;
				j++;
			}
		}
		return j - 1;
	}

	private static void display(int[] ar) {
		// TODO Auto-generated method stub
		for (Integer i : ar) {
			System.out.print(i + " ");
		}

	}

	private static void partitions(int[] ar, int pivot) {
		// TODO Auto-generated method stub
		// 0 ->j-1 <= p, j->i >p
		int j = 0;
		for (int i = 0; i < ar.length; i++) {
			if (ar[i] > pivot) {
				i++;
			} else {
				int temp = ar[i];
				ar[i] = ar[j];
				ar[j] = temp;
				i++;
				j++;
			}
		}

	}

	public static int count = 1;

	public static void solution(int i, int n, int k, int rssf, ArrayList<ArrayList<Integer>> ans) {
		if (i > n) {
			if (ans.size() == k) {
				System.out.print(count + ". ");
				for (int j = 0; j < ans.size(); j++) {
					ArrayList<Integer> list = ans.get(j);
					System.out.print(list + " ");
				}
				System.out.println();
				count++;
			}
			return;
		}
		// n - 1, k work, add with previous options
		for (int j = 0; j < ans.size(); j++) {
			ArrayList<Integer> list = ans.get(j);
			list.add(i);
			solution(i + 1, n, k, rssf, ans);
			list.remove(list.size() - 1);
		}

		// n - 1, k - 1, start from myself if size + 1 <= k
		if (ans.size() + 1 <= k) {
			ArrayList<Integer> mres = new ArrayList<>();
			mres.add(i);
			ans.add(mres);
			solution(i + 1, n, k, rssf + 1, ans);
			ans.remove(ans.size() - 1);
		}
	}
}
