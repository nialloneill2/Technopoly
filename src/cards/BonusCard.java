package cards;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import game.Game;
import playerData.Player;

public class BonusCard extends Card {
	private double bonusValue;
	private String bonusType;

	public BonusCard(String name, String description, String type, double bonusAmmount) {
		super(name, description);
		bonusValue = bonusAmmount;
		bonusType = type;
	}

	@Override
	public void printMessage() {
		super.printMessage();
		System.out.println("Bonus Card");
		System.out.println("Adds or reduces a Player's money or security levels.");
	}

	@Override
	public void triggerEffect(Game currentGame) {
		Player currentPlayer = currentGame.getCurrentPlayer();
		ArrayList<Player> allPlayers = currentGame.getAllPlayers();
		allPlayers.remove(currentPlayer);
		Player player = allPlayers.get(ThreadLocalRandom.current().nextInt(allPlayers.size()));

		if (bonusType == "Security") {
			System.out.println(
					"Data Leak: Unfortunately, some source code from your security software has been leaked, which has increased a random business' security level by "
							+ bonusValue);
			player.incrementAttribute("Security", this.bonusValue);
			System.out.println("Their security level has increased by " + bonusValue);
			double newSecurityLevel = currentPlayer.getSecurityLevel() + bonusValue;
			System.out.println("New security level: " + newSecurityLevel);
		}

		else if (bonusType == "Money") {

			System.out.println(
					"Cryptocurrency Surge: There has been an increase in value of multiple crypto currencies that your business has invested in, money increased by "
							+ bonusValue);
			currentPlayer.incrementAttribute("Money", this.bonusValue);
			System.out.println("Your money has been increased by " + bonusValue);
			double newBalance = currentPlayer.getMoney() + bonusValue;
			System.out.println("Current balance: " + newBalance);
		}

		else {

			System.out.println("VPN: You will not lose any money or security level. You are secured!");
			
			
			
		}

	}

}
