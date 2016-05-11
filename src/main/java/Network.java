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
		this.rX = 635;	//network circle center X position
		this.rY = 380;	//network circle center Y position
		this.r = 500;	////network circle radius
	}
	
	public boolean insideJudge(float x, float y){	//judge if the given position is inside the network circle
		if((PApplet.dist(x, y, rX, rY)<r/2+10)) {
			return true;
		}
		else return false;
	}
	
	public void display(){	//draw network circle
		
		this.parent.noFill();
		this.parent.stroke(100);
		this.parent.strokeWeight(5);
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
