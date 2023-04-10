package com.Project.Image.pseusoImg;

import com.Project.Image.chaoticMap.*;
//import com.Project.Image.border.*;

public class PseudoImage {
	//int[][] s;
	
	public byte[][] generate(int row, int column, int i){
		ChaoticMap chaoticmap= new ChaoticMap();
		byte[][] s= new byte[row][column];
		
		float x0=0, y0=0, mu=0;
		float temp[]= new float[3];
		
		for(int r=0;r<row;r++) {
			for(int c=0;c<column;c++) {
				temp= chaoticmap.generate(x0, y0, mu, i);
				x0= temp[0];	y0= temp[1];	mu= temp[2];
				
				byte val= (byte) (((((temp[0]+temp[1]) / 2) * (int)(Math.random()*1000)) % 256) - 128);
				s[r][c]= val;
			}
		}
		return s;
	}
}
