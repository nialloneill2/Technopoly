package cards;

import game.Game;
import game.Menu;
import playerData.Player;

import java.util.ArrayList;

public class DamageCard extends Card {
	private double damageValue;
	private String damageType;

	public DamageCard(String name, String description, String dmgType, double damageAmmount) {
		super(name, description);
		damageValue = damageAmmount;
		damageType = dmgType;
	}

	@Override
	public void printMessage() {
		super.printMessage();
		System.out.println("Damage a player.");
		System.out.println("Reduces a Player's money or security levels");
	}

	@Override
	public void triggerEffect(Game game) {
		Player currentPlayer = game.getCurrentPlayer();
		ArrayList<Player> allPlayers = game.getAllPlayers();
		// removes current player from array
		allPlayers.remove(currentPlayer);

		// target player
		ArrayList<Player> targetPlayers = game.getAllPlayers();
		String[] targetChoice = { targetPlayers.get(1).getName(), targetPlayers.get(2).getName(),
				targetPlayers.get(3).getName(), targetPlayers.get(4).getName(), targetPlayers.get(5).getName(),
				targetPlayers.get(6).getName(), targetPlayers.get(7).getName() };
		String target_title = "Choose which player to target";
		Menu targetMenu = new Menu(targetChoice, target_title);
		int inp = targetMenu.getChoice();
		Player inputPlayerName = game.getAllPlayers().get(inp);

		if (inputPlayerName != null) {

			if (damageType == "Security") {
				game.getAllPlayers().get(inp).decrementAttribute("Security", damageValue);
				System.out.println("You have decreased the players security level" + " by" + damageValue);
				game.checkElimination(game.getAllPlayers().get(inp));

			}

			else if (damageType == "Money") {
				game.getAllPlayers().get(inp).decrementAttribute("Money", damageValue);
				System.out.println("You have decreased the players money" + " by" + damageValue);
				game.checkElimination(game.getAllPlayers().get(inp));
			}

		}
	}
}