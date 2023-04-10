package com.Project.Image.encryption2;

public class Xoring {
byte[][] O;
	
	public Xoring(){
		
	}
	
	public Xoring(int row, int column) {
		O= new byte[row][column];
	}
	
	public byte[][] xor(byte[][] S, byte[][] T){
		
		for(int r=0;r<S.length;r++) {
			for(int c=0;c<S[0].length;c++) {
				if(r==0 && c==0) { 
					O[r][c]= (byte) (T[r][c] ^ T[T.length-1][T[0].length-1] ^ S[r][c]);
					//O[r][c]= (byte) (O[r][c] ^ S[r][c]);
				}
				
				else if(c==0) {
					O[r][c]= (byte) (T[r][c] ^ O[r-1][O[0].length-1] ^ S[r][c]);
					//O[r][c]= (byte) (O[r][c] ^ S[r][c]);
				}
				else {
					O[r][c]= (byte) (T[r][c] ^ O[r][c-1] ^ S[r][c]);
					//O[r][c]= (byte) (O[r][c] ^ S[r][c]);
				}
			}
		}
		
		return O;
	}

}
