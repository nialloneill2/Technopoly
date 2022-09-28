package tiles;

import game.Game;
import playerData.Player;


public class DamageTile extends Tile {
	private String dmgType;
	private double decrement;

	public DamageTile(String name, String type, double loss) {
		super(name);
		if(loss > 0 && type != null && (type.compareTo("money") == 0  || type.compareTo("security") == 0 ) && type.compareTo("null") != 0) {
			dmgType = type;
			decrement = loss;
		}
		else 
			throw new IllegalArgumentException("Error values for Damage Tile cannot be null, loss cannot be < 0 and type must be money or security");
	
		// TODO Auto-generated constructor stub
	}

	@Override
	public void displayMessage() {
		System.out.println(this.getTileName());
		switch(dmgType) {
		case "money":
			System.out.println("Your bank account has been compromised! The landed player will lose " + this.dmgType);
			break;
		case "security":
			System.out.println("Your security has been compromised! The landed player will lose " + this.dmgType);
			break;
		}
		
	}

	@Override
	public void triggerEffect(Game currentGame) {
		Player currentPlayer = currentGame.getCurrentPlayer();
		System.out.println(currentPlayer.getName() + " has landed on a Damage Tile!");
		currentPlayer.decrementAttribute(dmgType, decrement);
		System.out.println(currentPlayer.getName() + " has lost " + this.decrement + " " + this.dmgType);
	}

}
