package game;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

import cards.Card;
import datahandler.Board;
import datahandler.Initialiser;
import playerData.Player;


public class Client {

	public static void main(String[] args) {
	
		showClientChoices();
		
	}

	public static void createGame() {
		Scanner scan = new Scanner(System.in);
		byte numPlayers = 0;
		
		//Ensures that a minimum of 2 players can play the game
		do {
			System.out.println("How many players are playing?");
			numPlayers = scan.nextByte();
			
			if(numPlayers < 2)
				System.out.println("Error, a game must have a minimum of 2 players");
		}while(numPlayers < 2);
		
		
		ArrayList<Player> players = new ArrayList<>();
		byte numPlayersCreated = 0; // keeps track of how many players created
		
		scan.nextLine();//refreshes inputstream so it can take string input
		String playerName;
		boolean alreadyExists;

		while(numPlayersCreated < numPlayers) {
			alreadyExists = false;
			
			System.out.println("Enter player " + (numPlayersCreated + 1) + " name");
			playerName = scan.nextLine();
			if(players.size() >0) {
				
				//scans through existing players, making sure no duplicate names are entered
				for(int i = 0; i < players.size();i++) {
					if(playerName.trim().compareToIgnoreCase(players.get(i).getName().trim()) == 0) {
						System.out.println("Error, name entered cannot be the same as previous names");
						alreadyExists = true;
					}
				}
				
	
			}
			if(!alreadyExists) {
				Player player = new Player(playerName);
				players.add(player);
				numPlayersCreated++;
			}

		}
		// 
		try {
			Initialiser i = Initialiser.getInit();
			Queue<Card> benefitCards = i.getBenefitCards();
			Queue<Card> lossCards = i.getLossCards();
			Game game = new Game(lossCards,benefitCards,players,i.getBoards("MainBoard"));
			playGame(game);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			showClientChoices();
		}
		
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
		System.out.println("You have chosen Exit, goodbye!");
		System.exit(0);
	}
}
