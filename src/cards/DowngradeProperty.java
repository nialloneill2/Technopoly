package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import game.Game;
import game.Menu;
import playerData.Player;
import tiles.PropertyTile;

public class DowngradeProperty extends Card {
	private int downgradeLevel;
	private boolean target;

	public DowngradeProperty(String name, String description, int levelAmmount) {
		super(name, description);
		downgradeLevel = levelAmmount;

	}

	@Override
	public void printMessage() {
		super.printMessage();
		System.out.println("Downgrade Property");
		System.out.println("Reduces a Player's property level by one");
	}

	@Override
	public void triggerEffect(Game currentGame) {
		ArrayList<Player> allPlayers = currentGame.getAllPlayers();
		Player currentPlayer = currentGame.getCurrentPlayer();
		int[] currentTileNumbers = currentPlayer.getPropertyManager().getOwnedTiles();

		// The target of the card is the current player
		if (target) {

			boolean min = false;

			// Assigns valid numbers to a new array
			ArrayList<Integer> validNumbers = new ArrayList<> ();
			for (int d = 0; d < currentTileNumbers.length; d++) {
				PropertyTile tile = (PropertyTile) currentPlayer.getPropertyManager().getOwnedTile(currentTileNumbers[d]);
				if (tile.getServerLevel() > 0) {
					validNumbers.add(d, tile.getTileNumber());
				}
			}

			int[] validNum = validNumbers.stream().mapToInt(i->i).toArray();
			
			if (validNum.length == 0) {
				min = true;
			}

			if (!min) {

				int randomTile = new Random().nextInt(validNum.length);
				PropertyTile tile = (PropertyTile) currentPlayer.getPropertyManager().getOwnedTile(currentTileNumbers[randomTile]);
				tile.handleCard(this, 1);
				allPlayers.set(allPlayers.indexOf(currentPlayer), currentPlayer);
				System.out.println("Tile: " + randomTile + " Has been downgraded.");
			} else {
				System.out.println("None of your servers can be downgraded at all. Good job?*");
				allPlayers.set(allPlayers.indexOf(currentPlayer), currentPlayer);
			}

			// The target of the card is other players
		} else {
			allPlayers.remove(currentPlayer);

			String[] playerNames = new String[7];
			for (int i = 0; i < playerNames.length; i++) {
				playerNames[i] = allPlayers.get(i).getName();
			}
			
			boolean foundValidPlayer = false;
			while (!foundValidPlayer) {
				Menu menuPlayer = new Menu(playerNames, "Pick any player: ");
				int inputPlayer = menuPlayer.getChoice();
				
				Player inputPlayerName = currentGame.getAllPlayers().get(inputPlayer);

				int[] playerTileNumbers = allPlayers.get(inputPlayer).getPropertyManager().getOwnedTiles();

				// Assigns valid numbers to a new array
				String[] Choices = new String[40];
				int[] numChoices = new int[40];
				for (int d = 0; d < playerTileNumbers.length; d++) {
					PropertyTile tile = (PropertyTile) allPlayers.get(inputPlayer).getPropertyManager().getOwnedTile(playerTileNumbers[d]);
					if (tile.getServerLevel() > 0) {
						Choices[d] = tile.getTileName();
						numChoices[d] = tile.getTileNumber();
					} 
				}
				
				boolean min = false;

				if (Choices.length == 0) {
					min = true;
				}

				if (!min) {

					Menu menuTarget = new Menu(Choices, "Pick a tile to downgrade: ");
					int inputTarget = menuTarget.getChoice();

					PropertyTile tile = (PropertyTile) currentPlayer.getPropertyManager().getOwnedTile(numChoices[inputTarget]);

					tile.handleCard(this, -1);
					allPlayers.set(allPlayers.indexOf(inputPlayer), inputPlayerName);
					foundValidPlayer = true;

				} else {
					System.out.println("None of this players servers have been upgraded!*");
				}
			}
		}
	}
}
