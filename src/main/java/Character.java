package main.java;

import java.util.ArrayList;

//import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private float AnchorX, AnchorY, CurrentX, CurrentY;// AnchorX and Y are the fix position outside the network circle
	private String name;
	private int colour;
	private boolean isInside;	//character's status for whether it is inside the network circle 
	private ArrayList<Character> characters;	//target character for drawing network lines
	private ArrayList<Integer> linksValue;	//links' weight
	private Network network;

	public Character(MainApplet parent, String name, float x, float y, int colour){
		//-------set initial value-------
		this.parent = parent;
		this.name = name;
		this.AnchorX = x;
		this.AnchorY = y;
		this.CurrentX = this.AnchorX;
		this.CurrentY = this.AnchorY;
		this.colour = colour;
		this.isInside = false;
		this.characters = new ArrayList<Character>();
		this.linksValue = new ArrayList<Integer>();
		this.network = new Network(this.parent);
	}

	public void display(){
		//---------------draw network lines---------------
		if(this.isInside==true) {
			for(Character ch: characters){
				if(ch.getInside()) {
					this.parent.noFill();
					this.parent.stroke(0); 
					this.parent.strokeWeight(this.linksValue.get(characters.indexOf(ch))/4); //set stroke thickness
					this.parent.bezier( this.CurrentX, this.CurrentY,(this.CurrentX+this.network.getRX())/2, (this.CurrentY+this.network.getRY())/2, 
						(ch.CurrentX+this.network.getRX())/2, (ch.CurrentY+this.network.getRY())/2 ,ch.CurrentX, ch.CurrentY);	//draw curve
				}				
			}
		}
		//---------------draw character circles-----------
		this.parent.fill(colour, 255);	//set character's color
		this.parent.noStroke();	//eliminate edge 		
		this.parent.ellipse(this.CurrentX, this.CurrentY, 40, 40);	//draw circle
	}

	public boolean inRegion(int mouseX, int mouseY) {	//judge if the mouse is point to the character's circle
		
		if(MainApplet.dist(this.CurrentX, this.CurrentY, mouseX, mouseY)<25) {	//calculate the distance between mouse and character's center
			this.parent.fill(0);		
			this.parent.textSize(15);			
			this.parent.text(name, this.CurrentX, this.CurrentY); //show name tag if mouse point to the character
			return true;
		}
		else return false;
	}
	
	public boolean drag(int mouseX, int mouseY) {	//let the character follow the mouse 	
			this.CurrentX = mouseX;
			this.CurrentY = mouseY;
			return true;		
	}
	
	public void addTarget(Character ch, int value){	//add target to the character to draw network lines
		this.characters.add(ch);
		this.linksValue.add(value);
	}
	
	public float getX(){
		return this.CurrentX;
	}
	
	public float getY(){
		return this.CurrentY;
	}
	
	public void setX(float x){
		this.CurrentX = x;
	}
	
	public void setY(float y) {
		this.CurrentY = y;
	}
	
	public void checkInside(boolean judge){	//return if character is inside the network circle or not
		this.isInside = judge;
	}
	
	public boolean getInside(){
		return isInside;
	}
	
	public void backToAnchor() {	//put character back to the fix position outside the circle
		this.CurrentX = this.AnchorX;
		this.CurrentY = this.AnchorY;
	}
}
