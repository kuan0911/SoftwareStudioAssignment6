package main.java;

import java.awt.Color;
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
	
	private final static int width = 1200, height = 650;
	
	public void setup() {

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
			character.showName(mouseX, mouseY);			
		}
		
	}
	
	public void mouseDragged() {
		
		for(Character character :characters){
			character.drag(pmouseX, pmouseY);
		}
		
	}
	
	public void mouseReleased() {
		for(Character character :characters) {
			character.backToAnchor();
		}
	}
	

	private void loadData(){
		data = loadJSONObject(path+file);		
		nodes = data.getJSONArray("nodes" );
		links = data.getJSONArray("links" );
		int colour;		
		for (int i = 0; i < nodes.size(); i++) {		
		colour = unhex(nodes.getJSONObject(i).getString("colour").substring(1,8));		
		Character ch = new Character(this,nodes.getJSONObject(i).getString("name"),100+i%4*50,100+i/4*50,colour);
		characters.add(ch);
		}
		/*
		for (int i = 0; i < links.size(); i++) {
			int target = links.getJSONObject(i).getInt("source");
			int source = links.getJSONObject(i).getInt("target");
			characters.get(target).addTarget(characters.get(source));
			//characters.get(links.getJSONObject(i).getInt("source")).addTarget(characters.get(links.getJSONObject(i).getInt("target")));
			//System.out.println(links.getJSONObject(i).getInt("source")+" "+links.getJSONObject(i).getInt("target"));
		}
		*/
	}

}
