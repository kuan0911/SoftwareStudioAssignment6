package main.java;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private int rX, rY, r;

	public Network(PApplet parent){
		this.parent = parent;
		this.rX = 635;
		this.rY = 380;
		this.r = 500;
	}
	
	public boolean insideJudge(float x, float y){
		if(Math.pow(x-635, 2)+Math.pow(y-380, 2)<=Math.pow(500/2, 2)+2) {
			return true;
		}
		else return false;
	}
	
	public void display(){
		this.parent.fill(0);
		this.parent.ellipse(rX, rY, r, r);
		this.parent.fill(255);
		this.parent.ellipse(rX, rY, r-30, r-30);
	}
	
	public int getRX(){
		return rX;
	}
	
	public int getRY(){
		return rY;
	}
	
	public int getR(){
		return r/2;
	}
	
}
