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
	
}
