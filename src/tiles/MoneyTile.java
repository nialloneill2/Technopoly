package tiles;

import game.Game;
import playerData.Player;

public class MoneyTile extends Tile {
	
	private double bonus; 
	
	public MoneyTile(String name, double bonusAmount) {
		super(name);
		bonus = bonusAmount;
	}

	@Override
	public void displayMessage() {
		System.out.println("You have landed on: " + this.getTileName());
		System.out.println(this.bonus + " has been granted to you");
		
	}

	@Override
	public void triggerEffect(Game currentGame) {
		Player currentPlayer = currentGame.getCurrentPlayer(); 
		
		currentPlayer.incrementAttribute("Money", this.bonus);
		
	}
	

}
