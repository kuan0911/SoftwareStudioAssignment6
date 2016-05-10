package main.java;

//import java.awt.Color;
import java.util.ArrayList;

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
	private int insideNum;
	private Character draggedCh;
	private boolean isDragged;
	
	private final static int width = 1200, height = 650;
	
	public void setup() {

		insideNum=0;
		isDragged=false;
		size(width, height);
		characters = new ArrayList<Character>();
		network = new Network(this);
		smooth();
		loadData();
		
	}
	
	public void draw() {
		
		background(255);
		textSize(36);
		fill(100, 100, 120);
		text("Star Wars", 550, 70);
		network.display();
		for(Character character :characters) {			
			character.display(); // let the character handle its own display	
			character.inRegion(mouseX, mouseY);			
		}
		
	}
	
	public void mouseDragged() {
		
		if(isDragged==false) {
			for(Character character :characters){
				if(character.inRegion(pmouseX, pmouseY)) {
					this.isDragged=true;
					draggedCh = character;
					break;
				}
			}
		}
		else {
			draggedCh.drag(pmouseX, pmouseY);
		}
		
	}
	
	public void mouseReleased() {
		this.isDragged=false;
		for(Character character :characters) {
			if(character.getInside()==false) {
				if(network.insideJudge(character.getX(), character.getY())) {
					character.checkInside(true);
					insideNum++;
				}
				else {
					character.backToAnchor();
				}
			}
			else {
				if(network.insideJudge(character.getX(), character.getY())==false){
					character.checkInside(false);
					character.backToAnchor();
					insideNum--;
				}
			}
		}
		if(insideNum>0) {
			double arc = 360/insideNum;
			int count=0;
			for(Character character :characters) {
				if(character.getInside()){
					character.setX(network.getRX()+network.getR()*(float)Math.cos(Math.PI*arc*count/180));
					character.setY(network.getRY()+network.getR()*(float)Math.sin(Math.PI*arc*count/180));
					count++;
					if(count==insideNum) break;
				}
			}
		}
	}
	

	private void loadData(){
		data = loadJSONObject(path+file);		
		nodes = data.getJSONArray("nodes" );
		links = data.getJSONArray("links" );
		int colour;		
		for (int i = 0; i < nodes.size(); i++) {		
			colour = unhex(nodes.getJSONObject(i).getString("colour").substring(1));		
			Character ch = new Character(this,nodes.getJSONObject(i).getString("name"),100+i%4*50,100+i/4*50,colour);
			characters.add(ch);
		}
		
		for (int i = 0; i < links.size(); i++) {
			int target = links.getJSONObject(i).getInt("source");
			int source = links.getJSONObject(i).getInt("target");
			characters.get(target).addTarget(characters.get(source));
			//characters.get(links.getJSONObject(i).getInt("source")).addTarget(characters.get(links.getJSONObject(i).getInt("target")));
			//System.out.println(links.getJSONObject(i).getInt("source")+" "+links.getJSONObject(i).getInt("target"));
		}
		
	}

}
