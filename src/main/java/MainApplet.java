package main.java;

//import java.awt.Color;
import java.util.ArrayList;

import controlP5.ControlP5;
import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private Network network;
	private int insideNum;	//the number of character inside the network circle
	private Character draggedCh;	//the character that is being dragged
	private boolean isDragged;	//if the mouse is dragging or not
	private ControlP5 cp5;	
	private int episodeNum=1;	//current episode
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		//-----initial variable--------
		insideNum=0;
		isDragged=false;
		size(width, height);
		cp5 = new ControlP5(this);
		this.initButtons();	//initial buttons
		
		characters = new ArrayList<Character>();
		network = new Network(this);
		smooth();
		loadData();	//load data
		
	}
	
	public void draw() {
		
		background(255);
		textSize(36);
		fill(100, 100, 120);
		text("Star Wars "+episodeNum, 550, 70);	//show title
		network.display();	//show network lines
		for(Character character :characters) {	//show all characters
			character.display(); // let the character handle its own display							
		}
		for(Character character :characters) 
			character.inRegion(mouseX, mouseY);	//check if the mouse is point to the character
	}
	
	public void mouseDragged() {
		
		if(isDragged==false) {	//if the mouse is just being pressed, decide which character to be dragged
			for(Character character :characters){
				if(character.inRegion(pmouseX, pmouseY)) {	//if mouse is in the character's region
					this.isDragged=true;	//set character's status of dragged to true
					draggedCh = character;	//store the selected character
					break;	//break loop if the selected character was found
				}
			}
		}
		else {
			draggedCh.drag(pmouseX, pmouseY);	//mouse position is equal to the character's position when mouse is pressed
		}
		
	}
	
	public void mouseReleased() {	//decide character's position when mouse is released
		this.isDragged=false;	//set isDragged back to false
		insideNum=0;	//reset insideNum
		for(Character character :characters) {	//count the characters inside the network circle
				if(network.insideJudge(character.getX(), character.getY())) {
					character.checkInside(true);	//set character's status to inside network circle
					insideNum++;	//count the number
				}
				else {
					character.backToAnchor();	//back to its position if it is outside the circle
					character.checkInside(false);	//set character's status to outside network circle
				}			
		}
		this.putInCircle(insideNum);	//set the position of the characters that is inside the network circle
		
	}
	
	public void putInCircle(int insideNum) {	//let selected characters arrange in the network circle
		if(insideNum>0) {
			double arc = 360/insideNum;	//calculate angle between each character
			int count=0;
			for(Character character :characters) {	//set each character's position by using cosine and sine
				if(character.getInside()){
					character.setX(network.getRX()+network.getR()*(float)Math.cos(Math.PI*arc*count/180));
					character.setY(network.getRY()+network.getR()*(float)Math.sin(Math.PI*arc*count/180));
					count++;
					if(count==insideNum) break;
				}
			}
		}
	}
	
	public void initButtons(){	//add 4 buttons
		cp5. addButton("buttonA")
		. setLabel("ADD ALL")
		. setPosition(width-200, 100)
		. setSize(100, 50);
		
		cp5. addButton("buttonB")
		. setLabel("CLEAR ALL")
		. setPosition(width-200, 200)
		. setSize(100, 50);
		
		cp5. addButton("buttonC")
		. setLabel("PREVIOUS EPISODE")
		. setPosition(width-200, 300)
		. setSize(100, 50);
		
		cp5. addButton("buttonD")
		. setLabel("NEXT EPISODE")
		. setPosition(width-200, 400)
		. setSize(100, 50);
	}
	
	public void buttonA(){ 	//add all button
		for(Character character :characters)	//set all characters' inside network circle status to true
			character.checkInside(true);	
		this.putInCircle(characters.size());	//re arrange characters' position
	}

	public void buttonB(){ 
		for(Character character :characters) {	//set all characters' inside network circle status to false
			character.checkInside(false);
			character.backToAnchor();	//put back all characters
		}
	}
	
	public void buttonC(){ 	//previous episode button
		if(episodeNum>1) episodeNum--;	//subtract current episode number if available
		file = "starwars-episode-"+episodeNum+"-interactions.json";	//change load file
		loadData();	//reload
	}
	
	public void buttonD(){	//next episode button
		if(episodeNum<7) episodeNum++;	//add current episode number if available
		file = "starwars-episode-"+episodeNum+"-interactions.json";	//change load file
		loadData();	//reload
	}	
	
	private void loadData(){
		characters.clear();	//clear variable
		data = loadJSONObject(path+file);	
		nodes = data.getJSONArray("nodes" );
		links = data.getJSONArray("links" );
		int colour;		
		for (int i = 0; i < nodes.size(); i++) {		
			colour = unhex(nodes.getJSONObject(i).getString("colour").substring(1));	//read colour	
			Character ch = new Character(this,nodes.getJSONObject(i).getString("name"),100+i%4*50,100+i/4*50,colour);	//read character's name
			characters.add(ch);	//store character
		}
		
		for (int i = 0; i < links.size(); i++) {
			int target = links.getJSONObject(i).getInt("source");
			int source = links.getJSONObject(i).getInt("target");
			int value = links.getJSONObject(i).getInt("value");
			characters.get(target).addTarget(characters.get(source),value);	//add link to the character		
		}
		
	}

}
