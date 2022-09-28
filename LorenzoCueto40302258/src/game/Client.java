package game;
import java.util.ArrayList;
import java.util.Scanner;

import datahandler.Board;
import datahandler.Initialiser;

public class Client {

	public static void main(String[] args) {
	
		showClientChoices();
	}

	public static void createGame() {
		System.out.println("How many players are playing?");
		Scanner scan = new Scanner(System.in);
		
		byte numPlayers = scan.nextByte();
		
		ArrayList<Player> players = new ArrayList<>();
		byte numPlayersCreated = 0;
		
		scan.nextLine();
		String playerName;
		while(numPlayersCreated < numPlayers) {
			System.out.println("Enter player " + (numPlayersCreated + 1) + " name");
			playerName = scan.nextLine();
			Player player = new Player(playerName);
			players.add(player);
			numPlayersCreated++;
			
		}
		
		Game game = new Game(players);
		playGame(game);
	}
	
	private static void playGame(Game game) {
		game.continueGame();
		showClientChoices();
	}
	
	private static void showClientChoices() {
	String[] choices = {"Create a game","Exit"};
		
		Menu startMenu = new Menu( choices,"Start Menu") ;
		int choice = startMenu.getChoice();
		
		switch(choice) {
		
		case 0:
			createGame();
			break;
		case 1:
			quit();
			break;
			
		}
	}
	

	
	
	public static void quit() {
		System.exit(0);
	}
}
