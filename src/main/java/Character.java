package main.java;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	public float x, y, radius;
	public String name;
	public int colour;

	public Character(MainApplet parent, String name, float x, float y, int colour){

		this.parent = parent;
		this.name = name;
		this.x = x;
		this.y = y;
		this.colour = colour;
		
	}

	public void display(){
		
		this.parent.fill(colour);
		this.parent.noStroke(); 		
		this.parent.ellipse(x, y, 30, 30);
	}

	public void showName(int mouseX, int mouseY) {
		
		if(this.parent.dist(x,y,mouseX,mouseY)<15) {
			this.parent.fill(0);		
			this.parent.textSize(10);
			this.parent.text(name, x, y); 
		}
		
	}

	public void drag(int mouseX, int mouseY) {
		if(this.parent.dist(x,y,mouseX,mouseY)<15) {
			this.x = mouseX;
			this.y = mouseY;
		}
		
	}
	
}
