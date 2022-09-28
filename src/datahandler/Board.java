package datahandler;

import java.util.ArrayList;

import tiles.*;

public class Board {
	
	private ArrayList<Tile> tiles;
	private String boardName;
	
	
    
   public Board(String name, ArrayList<Tile> tile ) {
		tiles = tile;
		boardName = name;
		
	}
	
	public ArrayList<Tile> getTiles(int tileNumber) {
		return tiles;
		
	}
	
	public String getBoardName() {
		return boardName;
		
	}
	
	public Tile getTile(int index) {
		return tiles.get(index);
	}

}
