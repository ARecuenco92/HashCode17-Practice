package com.pizza.model;

public class Slice {

	private int R1;
	private int R2;
	private int C1;
	private int C2;
	
	public Slice(int r1, int r2, int c1, int c2){
		this.R1 = r1;
		this.R2 = r2;
		this.C1 = c1;
		this.C2 = c2;
	}
	
	public String toString(){
		return R1+" "+C1+" "+R2+" "+C2;
	}
	
	public int getScore(){
		int rows = Math.abs(R1-R2)+1;
		int cols = Math.abs(C1-C2)+1;
		return rows*cols;
	}
}
