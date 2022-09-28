package tiles;



import game.Game;


public class WildCardTile extends Tile {
	private String tileType;

	public WildCardTile(String name, String type) {
		super(name);
		tileType = type;
	}

	
	public String getTileType() {
		return tileType;
	}
	
	@Override
	public void displayMessage() {
		System.out.println("You have landed on a wildcard tile: " + this.getTileName());
	}

	
	// just prints message, card is drawn in Game class
	@Override
	public void triggerEffect(Game currentGame) {
		System.out.println("You must now choose if you would like to draw a " + tileType + " card or not");
	}

}
