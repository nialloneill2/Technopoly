package cards;

import java.util.ArrayList;
import java.util.Arrays;

import game.Game;
import game.Menu;
import playerData.Player;
import tiles.PropertyTile;

public class UpgradeProperty extends Card {
	private int upgradeLevel;

	public UpgradeProperty(String name, String description, int levelAmmount) {
		super(name, description);
		upgradeLevel = levelAmmount;
	}

	@Override
	public void printMessage() {
		super.printMessage();
		System.out.println("Upgrade Property");
		System.out.println("Increases a Player's server level by one");
	}

	@Override
	public void triggerEffect(Game currentGame) {
		ArrayList<Player> allPlayers = currentGame.getAllPlayers();
		Player currentPlayer = currentGame.getCurrentPlayer();
		int[] tileNumbers = currentPlayer.getPropertyManager().getOwnedTiles();

        ArrayList<String> Choices = new ArrayList<> ();
        ArrayList<Integer> numChoices = new ArrayList<>();
        
		for (int d = 0; d < tileNumbers.length; d++) {
			PropertyTile tile = (PropertyTile) currentPlayer.getPropertyManager().getOwnedTile(tileNumbers[d]);
			if (tile.getServerLevel() < 5) {
				Choices.add(d, tile.getTileName());
				numChoices.add(d, tile.getTileNumber());
			}
		}
		
		String[] Names = Choices.toArray(new String[Choices.size()]);
		int[] Values = numChoices.stream().mapToInt(i->i).toArray();

		boolean max = false;

		if (Names.length == 0) {
			max = true;
		}

		if (!max) {

			Menu menu = new Menu(Names, "Pick a tile to upgrade");
			int input = menu.getChoice();
			
			PropertyTile tile = (PropertyTile) currentPlayer.getPropertyManager().getOwnedTile(Values[input]);

			tile.handleCard(this, 1);
			
			allPlayers.set(allPlayers.indexOf(currentPlayer), currentPlayer);

		} else {
			System.out.println(
					"All of your servers have reached the maximum level and cannot be upgraded further. Good job!*");
		}
	}
}
