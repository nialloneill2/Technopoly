package cards;

import game.Game;
import playerData.Player;
import game.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveCard extends Card {
	private int moveTileAmount;
	
	public MoveCard(String name, String description, int spaces) {
		super(name, description);
		moveTileAmount = spaces;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printMessage() {
		super.printMessage();
	}
	
	@Override
	public void triggerEffect(Game currentGame) {
		Player currentPlayer = currentGame.getCurrentPlayer();
		ArrayList<Player> allPlayers = currentGame.getAllPlayers();
			currentGame.movePlayer(currentPlayer.getName(),moveTileAmount);
			allPlayers.set(allPlayers.indexOf(currentPlayer), currentPlayer);
	}
}
