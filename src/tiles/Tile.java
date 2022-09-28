package tiles;

import game.Game;


public abstract class Tile {
	private static int nextTileNumber = -1;// next tileNumber is -1 so firsttile can be tile 0
	private int tileNumber;
	private String tileName;
	
	protected Tile(String name){
		if(name != null && name.compareTo("null") != 0) {
			tileNumber = setTileNumber();
			tileName = name;
		}
		else {
			throw new IllegalArgumentException("Error, Tile name cannot be null");
		}
		
	}
	
	public String getTileName() {
		return tileName;
	}
	
	public int getTileNumber() {
		return tileNumber;
	}

	/*
	 * Displays message that player has landed on tile,
	 * shows tile information such as tile name etc
	 */
	public abstract void displayMessage();
	
	/*
	 * Triggers affect on player that is passed to tile
	 * method differs based on tile
	 */
	public abstract void triggerEffect(Game currentGame);
	
	private int setTileNumber() {
		nextTileNumber += 1;
		if(nextTileNumber == 40) {
			nextTileNumber = 0;
		}
		return nextTileNumber;
	}
}
