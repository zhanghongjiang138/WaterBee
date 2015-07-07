package com.Test;

import java.util.Arrays;
import java.util.Comparator;

public class Comparable1 implements Comparable<Comparable1> ,Comparator<Comparable1> {
	private int number;
    public int getNumber(){
    	return this.number;
    }
    public Comparable1(int num){
    	this.number=num;
    }
	public static void main(String[] args) {
		Comparable1 c0=new Comparable1(1);
		Comparable1 c1=new Comparable1(5);
		Comparable1 c2=new Comparable1(2);
		Comparable1 c3=new Comparable1(7);
		Comparable1 c4=new Comparable1(9);
		Comparable1 c5=new Comparable1(0);
		Comparable1[] cc={c0,c1,c2,c3,c4,c5};
		Arrays.sort(cc);
		for(Comparable1 com:cc){
			System.out.println(com.getNumber());
		}
	}

	@Override
	public int compareTo(Comparable1 c1) {
		
		return this.number-c1.getNumber();
	}
	@Override
	public int compare(Comparable1 o1, Comparable1 o2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
