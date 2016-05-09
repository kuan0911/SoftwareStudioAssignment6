package main.java;

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

	public Character(MainApplet parent, String name, float x, float y, int colour){

		this.parent = parent;
		this.name = name;
		this.AnchorX = x;
		this.AnchorY = y;
		this.CurrentX = this.AnchorX;
		this.CurrentY = this.AnchorY;
		this.colour = colour;
		this.isInside=false;
	}

	public void display(){
		
		this.parent.fill(colour);
		this.parent.noStroke(); 		
		this.parent.ellipse(this.CurrentX, this.CurrentY, 40, 40);
	}

	public void showName(int mouseX, int mouseY) {
		
		if(MainApplet.dist(this.CurrentX, this.CurrentY, mouseX, mouseY)<15) {
			this.parent.fill(0);		
			this.parent.textSize(10);
			this.parent.text(name, this.CurrentX, this.CurrentY); 
		}
		
	}

	public void drag(int mouseX, int mouseY) {
		if(MainApplet.dist( CurrentX, CurrentY, mouseX, mouseY) < 25) {
			this.CurrentX = mouseX;
			this.CurrentY = mouseY;
		}
		
	}
	
	public void setX(int x){
		this.CurrentX = x;
	}
	
	public void setY(int y) {
		this.CurrentY = y;
	}
	
	public void backToAnchor() {
		this.CurrentX = this.AnchorX;
		this.CurrentY = this.AnchorY;
	}
}
