package com.Project.Image.encryption;

import com.Project.Image.encryption2.*;
import java.util.*;

public class Shuffling {
	long[][] D;
	byte[][] R;
	byte[][] T;
	byte[][] ans;
	String ld= "";
	long ldBits;
	
	public Shuffling(){
		
	}
	
	public Shuffling(int row, int column) {
		D= new long[row][column];
		R= new byte[row][column];
		T= new byte[row][column];
		ans= new byte[row][column];
	}
	
	public void D(int row, int column){
		long val=1; 
		for(int r=0;r<row;r++) {
			for(int c=0;c<column;c++) {
				D[r][c]= val;
				val++;
			}
		}
	}
	
	public void binary(long decimal) {
		while(decimal>0) {
			int digit= (decimal%2 ==0)? 0 : 1;
			char bit= (char) (digit + '0');
			ld= bit + ld;
			
			decimal/= 2;
		}
	}
	/*
	public void sortByColumn(int[][] temp, int c) {
		Arrays.sort(temp, new Comparator<int[]>() {
			@Override
			public int compare(int[] entry1, int[] entry2) {
				if(entry1[c] > entry2[c])
					return 1;
				else
					return -1;
			}
		});
	}
	*/
	public void sorted(byte[][] R) {
		for(int r=0;r<R.length;r++){
			Arrays.sort(R[r]);						//sorted row-wise
		} 
		/*
		int[][] temp= new int[R.length][R[0].length];
		for(int r=0;r<R.length;r++) {
			for(int c=0;c<R[0].length;c++) {
				temp[r][c]= R[r][c];
			}
		}
		//sort column-wise
		//for(int c=0;c<R[0].length;c++) {
			sortByColumn(temp, 0);
		//}
			
		for(int r=0;r<R.length;r++) {
			for(int c=0;c<R[0].length;c++) {
				R[r][c]= (byte) temp[r][c];
			}
		}
		*/
	}

	public byte[][] shuffle(byte[][] P, byte[][] S, int row, int column, int i){
		
		Xoring x= new Xoring(row, column);
		
		D(row, column);
		
		binary(row*column);
		ldBits= ld.length();
		
		for(int r=0;r<row;r++) {
			for(int c=0;c<column;c++) {
				R[r][c]= (byte) (((int)Math.pow(2, 8+ldBits)) * S[r][c]);
				R[r][c]+= (byte) (((int)Math.pow(2, 8)) * D[r][c]);
				R[r][c]+= P[r][c];
			}
		}
		sorted(R);
		
		for(int r=0;r<row;r++) {
			for(int c=0;c<column;c++) {
				T[r][c]= (byte) (R[r][c] & ((int)Math.pow(2, 8)-1));
			}
		}
		
		ans= x.xor(S, T);
		
		return ans;
	}
}
