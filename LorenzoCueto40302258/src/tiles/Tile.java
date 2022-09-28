package tiles;

import game.Player;

public abstract class Tile {
	private static int nextTileNumber = 0;
	private int tileNumber;
	private String tileName;
	
	protected Tile(String name){
		tileNumber = setTileNumber();
		tileName = name;
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
	public abstract void triggerEffect(Player player);
	
	private int setTileNumber() {
		nextTileNumber++;
		if(nextTileNumber == 41) {
			nextTileNumber = 1;
		}
		return nextTileNumber;
	}
}
