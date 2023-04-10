package com.Project.Image.chaoticMap;

public class ChaoticMap {
	private float mu;
	private float w;
	private float v1;
	private float v2;
	private float x0;
	private float y0;
	private final int[] k= {1,0,1,0,1,1,1,0,1,1,1,1,0,0,0,1,0,1,1,0,1,1,1,0,0,0,1,0,0,1,0,1,1,0,1,0,0,0,1,0,0,0,1,1,1,1,0,1,1,0,0,
			1,1,1,0,1,0,0,0,1,0,1,1,1,1,0,0,0,1,1,0,1,0,0,0,0,0,1,0,1,1,0,0,1,0,1,0,1,0,0,1,0,0,1,1,0,1,1,0,1,
			0,0,0,0,1,0,1,1,0,1,0,1,1,1,0,0,0,1,1,0,0,0,1,1,0,1,0,0,0,1,1,1,0,0,0,1,0,1,0,0,0,0,1,0,1,0,0,1,1,
			1,0,1,1,0,1,1,0,1,0,0,0,0,1,1,0,1,0,1,0,1,1,1,1,0,0,1,0,1,0,0,1,1,1,1,1,0,0,0,1,0,1,0,0,0,1,1,0,1,
			0,1,1,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,1,0,0,1,0,0,1,0,0,0,0};
	
	public int add(int i, int j) {
		int ans=0;
		while(i<=j) {
			ans+= k[i] * Math.pow(2, i-1);
			i++;
		}
		return ans;
	}
	
	public ChaoticMap(){
		x0= (float) ((1/Math.pow(2, 52)) * add(0,51));
		y0= (float) ((1/Math.pow(2, 104)) * add(52,103));
		mu= (float) ((1/Math.pow(2, 156)) * (add(104,155)));
		w= (float) (1/(Math.pow(2, 208)) * add(156,207));
		v1= (float) (1/Math.pow(2, 208) * add(208,219));
		v2= (float) (1/Math.pow(2, 220) * add(220,231));
	}
	public float[] generate(float x0, float y0, float mu, int i) {
		float[] ans= new float[3];
		float v= (i==1)? v1 : v2;
		mu= (mu==0)? this.mu : mu;
		x0= (x0==0)? this.x0 : x0;
		y0= (y0==0)? this.y0 : y0;
		
		ans[0]= (x0 + w*v) % 1;								//x
		ans[1]= (y0 + w*v) % 1;								//y
		ans[2]= (float) (((mu + w*v) % 0.4) + 0.5);			//mui
		
		ans[0]= (ans[0]==0)? 0.4f : ans[0];
		ans[1]= (ans[1]==0)? 0.4f : ans[1];
		
		return ans;
	}
}
