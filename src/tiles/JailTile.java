package tiles;

import java.util.ArrayList;
import java.util.HashMap;

import game.Game;
import playerData.Player;

public class JailTile extends Tile {

	private HashMap<String, Integer> jailHolder = new HashMap<String, Integer>();

	public JailTile(String name) {
		super(name);

	}

	@Override
	public void displayMessage() {
		System.out.println("You have landed on: " + this.getTileName());
		System.out.println("Your Systems have been crippled by a ransomware attack!");
		System.out.println("You cannot move for 3 turns\n");
	}

	@Override
	public void triggerEffect(Game currentGame) {
		ArrayList<Player> allPlayers = currentGame.getAllPlayers();
		Player currentPlayer = currentGame.getCurrentPlayer();
		jailHolder.putIfAbsent(currentPlayer.getName(), 1);
		allPlayers.set(allPlayers.indexOf(currentPlayer), currentPlayer);
	}
	
	public void leaveJail(String playerName) {
		if (jailHolder.containsKey(playerName)) {
			jailHolder.remove(playerName); 
		}
	}


	public void incrementalJailTurn(String playerName) {
		if (jailHolder.get(playerName) > 2) {
			jailHolder.remove(playerName);
		} else {
			jailHolder.merge(playerName, 1, Integer::sum);
		}
	}

	public boolean getJail(String playerName) {
		if (jailHolder.containsKey(playerName)) {
			return true;
		} else {
			return false;
		}
	}

	public int getJailTime(String playerName) {
		if (jailHolder.containsKey(playerName)) {
			int jailCounter = jailHolder.get(playerName);
			return jailCounter;
		} else {
			return -1;
		}
	}
}
