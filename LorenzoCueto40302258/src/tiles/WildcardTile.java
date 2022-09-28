package tiles;

import game.Player;

public class WildcardTile extends Tile {
	private String tileType;

	protected WildcardTile(String name, String type) {
		super(name);
		tileType = type;
	}

	@Override
	public void displayMessage() {
		// TODO Auto-generated method stub
		
	}

	
	// just prints message, card is drawn in Game class
	@Override
	public void triggerEffect(Player player) {
		// TODO Auto-generated method stub
		
	}

}
