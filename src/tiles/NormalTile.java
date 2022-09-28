package tiles;

import game.Game;

public class NormalTile extends Tile{

	public NormalTile(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void displayMessage() {
		System.out.println( this.getTileName());
		
	}

	@Override
	public void triggerEffect(Game currentGame) {
		String currentPlayerName = currentGame.getCurrentPlayer().getName();
		System.out.println(currentPlayerName + " has landed on a Normal Tile, no effects will occur.");
		
	}

}
