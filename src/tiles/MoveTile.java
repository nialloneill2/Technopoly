package tiles;


import game.Game;
import playerData.Player;


public class MoveTile extends Tile{
	private int targetTileNumber;
	
	public MoveTile(String name,int tileNumber) {
		super(name);
		if(tileNumber > 39 || tileNumber < 0 || name == null) {
			throw new IllegalArgumentException("Error, a Move tile's move value must be equal or greater than 0 and less than or equal to 39");
		}
		targetTileNumber = tileNumber;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void displayMessage() {
		System.out.println("\nPlayer has landed on a move tile!: " + this.getTileName());
		System.out.println("Player will now move to Tile Number: " + targetTileNumber);
		
	}

	@Override
	public void triggerEffect(Game currentGame) {
		Player currentPlayer = currentGame.getCurrentPlayer();
		
		//subtracts this tilenumber from target tile number to get ammount of spaces needed to move
		
		currentGame.movePlayer(currentPlayer.getName(), targetTileNumber-this.getTileNumber());
		
		currentGame.checkPlayerPosition(currentPlayer);//triggers the effect of the tile the player landed on
		
	}

}
