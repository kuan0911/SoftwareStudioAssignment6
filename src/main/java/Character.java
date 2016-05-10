package main.java;

import java.util.ArrayList;

//import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private float AnchorX, AnchorY, CurrentX, CurrentY;
	private String name;
	private int colour;
	private boolean isInside;
	private ArrayList<Character> characters;
	private ArrayList<Integer> linksValue;
	private Network network;

	public Character(MainApplet parent, String name, float x, float y, int colour){

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
		
		if(this.isInside==true) {
			for(Character ch: characters){
				if(ch.getInside()) {
					this.parent.noFill();
					this.parent.stroke(0); 
					this.parent.strokeWeight(this.linksValue.get(characters.indexOf(ch))); 
					this.parent.bezier( this.CurrentX, this.CurrentY,(this.CurrentX+this.network.getRX())/2, (this.CurrentY+this.network.getRY())/2, 
						(ch.CurrentX+this.network.getRX())/2, (ch.CurrentY+this.network.getRY())/2 ,ch.CurrentX, ch.CurrentY);
				}				
			}
		}
		this.parent.fill(colour, 255);
		this.parent.noStroke(); 		
		this.parent.ellipse(this.CurrentX, this.CurrentY, 40, 40);
	}

	public boolean inRegion(int mouseX, int mouseY) {
		
		if(MainApplet.dist(this.CurrentX, this.CurrentY, mouseX, mouseY)<25) {
			this.parent.fill(0);		
			this.parent.textSize(15);			
			this.parent.text(name, this.CurrentX, this.CurrentY); 
			return true;
		}
		else return false;
	}
	
	public boolean drag(int mouseX, int mouseY) {		
			this.CurrentX = mouseX;
			this.CurrentY = mouseY;
			return true;		
	}
	
	public void addTarget(Character ch, int value){
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
	
	public void checkInside(boolean judge){
		this.isInside = judge;
	}
	
	public boolean getInside(){
		return isInside;
	}
	
	public void backToAnchor() {
		this.CurrentX = this.AnchorX;
		this.CurrentY = this.AnchorY;
	}
}
