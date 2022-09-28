package tiles;

import game.Player;

public class PropertyTile extends Tile{
	
	private double propertyCost;
	private int serverLevel;
	private int[] linkedProperties;
	private boolean isMortgaged;
	Player owner;

	protected PropertyTile(String name, double cost, int[] properties) {
		super(name);
		serverLevel = 0;
		linkedProperties = properties;
		isMortgaged = false;
	}

	@Override
	public void displayMessage() {
		
		
	}

	// responsible for asking player to buy or 
	// trigger rent
	@Override
	public void triggerEffect(Player player) {
		
		
	}

}
