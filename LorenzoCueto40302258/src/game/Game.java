package game;
import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class Game {

	private ArrayList<Player> allPlayers;
	private int currentPlayerIndex;
	private HashMap<String,Integer> playerPositions;
	
	public Game(ArrayList<Player> inputPlayers) {
		if(inputPlayers != null) {
			allPlayers = inputPlayers;
			currentPlayerIndex = 0;
			playerPositions = new HashMap<>();
			allPlayers.forEach(i->{
				playerPositions.put(i.getName(), 0);
			});
		}
	}
	
	public void continueGame() {
		Player currentPlayer = allPlayers.get(currentPlayerIndex);
		boolean gameOver = false;
		while(allPlayers.size() > 1 && !gameOver) {
			System.out.println("It is " + currentPlayer.getName() +"'s turn");
			int menuChoice = showChoice();
			
			switch(menuChoice) {
			case 0:
				rollDice(currentPlayer);
				break;
			case 1:
				buildServer(currentPlayer);
				break;
			case 2:
				initiateTrade(currentPlayer);
				break;
			case 3:
				useWildCard(currentPlayer);
				break;
			case 4:
				gameOver = true;
				allPlayers = null;
				break;
			}
			if(allPlayers == null)
				break;
			else if(currentPlayerIndex == allPlayers.size()-1 )
				currentPlayerIndex = 0;
			else {
				currentPlayerIndex++;
			}
			currentPlayer = allPlayers.get(currentPlayerIndex);
			
		}
		return;
	}
	
	private int showChoice() {
		String[] gameChoices = {"Roll dice","Buy Server","Initiate Trade","Use Wildcard","Return to Menu"};
		Menu gameOptions = new Menu(gameChoices,"Select a choice");
		return gameOptions.getChoice();
	}
	
	private void rollDice(Player player) {
		System.out.println("The game rolls two dice");
		System.out.println("The game adds the combined total of the dice to the current players position");
		Random random = new Random();
		int dice1 = random.nextInt(7);		 
		int dice2 =random.nextInt(7);
		
	
		System.out.println(player.getName() +" has rolled " + dice1 +", " + dice2);
		System.out.println(player.getName() +" will now move " + (dice1 + dice2) + " tiles");
		playerPositions.put(player.getName(), dice1+dice2);
		System.out.println(playerPositions + "\n\n");
	}
	
	private void buildServer(Player player) {
		System.out.println(player.getName() +"'s properties will be shown if they have any" );
		System.out.println("Player will then select which property to build a server on");
		System.out.println("Game will check if player owns all other associated properties before\n"
				+ "the player can build a server");
		System.out.println("If player owns all the properties, game asks how many servers");
		System.out.println("Game will then add the ammount of servers if user has sufficient money\n\n");
	}
	
	private void initiateTrade(Player player) {
		System.out.println("A list of all players will be shown");
		System.out.println(player.getName() +" will have to select one player from the list of players" );
		System.out.println("Game will then get the player based on a selection");
		System.out.println("Player will then offer money and properties that they would like to trade");
		System.out.println("The targetted player will then have to choose if they reject or accept the offer\n\n");
	}
	
	private void useWildCard(Player player) {
		System.out.println(player.getName() +" will use wildcard if they drawn a wildcard from a wildcard tile" );
		System.out.println("Different effects will happen based on the wildcard");
		System.out.println("A wildcard will either affect the current player or a target player which the current player can choose");
		System.out.println("Effect of wildcard will be triggered\n\n");
	}
	
	
}
