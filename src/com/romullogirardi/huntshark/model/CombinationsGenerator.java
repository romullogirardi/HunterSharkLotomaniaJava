package com.romullogirardi.huntshark.model;

public abstract class CombinationsGenerator {
	
	//ATTRIBUTES
	private Object[] elements;
	private int k;
	
	//CONSTRUCTOR
	public CombinationsGenerator(Object[] elements, int k) {
		this.elements = elements;
		this.k = k;
	}
	
	//METHODS
	public void generateCombinations(){

		// get the length of the array
		// e.g. for {'A','B','C','D'} => N = 4
		int n = elements.length;
		
		if(this.k > n){
			System.out.println("Invalid input, K > N");
			return;
		}

		// calculate the possible combinations
		// e.g. c(4,2)
		c(n,this.k);

		// get the combination by index
		// e.g. 01 --> AB , 23 --> CD
		int combination[] = new int[this.k];

		// position of current index
		// if (r = 1) r*
		// index ==> 0 | 1 | 2
		// element ==> A | B | C
		int r = 0;
		int index = 0;
		while(r >= 0){
		
		// possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
		// possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"
		// for r = 0 ==> index < (4+ (0 - 2)) = 2
		if(index <= (n + (r - this.k))){

			combination[r] = index;
			
			// if we are at the last position print and increase the index
			if(r == this.k - 1){
				//do something with the combination e.g. add to list or print
				processCombination(elements, combination);
				index++;
			}
			else{
				// select index for next position
				index = combination[r]+1;
				r++;
			}
		}
		else{
			r--;
			if(r > 0)
				index = combination[r]+1;
			else
				index = combination[0]+1;
			}
		}
	}
	
	private int c(int n, int r){
	
		int nf=fact(n);
		int rf=fact(r);
		int nrf=fact(n-r);
		int npr=nf/nrf;
		int ncr=npr/rf;
		return ncr;
	}
	
	private int fact(int n) {
		
		if(n == 0)
			return 1;
		else
			return n * fact(n-1);
	}
	
	public abstract void processCombination(Object[] elements, int[] combination);
}