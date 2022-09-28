package game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import playerData.Player;
import cards.Card;
import datahandler.Board;
import datahandler.CardHandler;
import tiles.Tile;
import tiles.WildCardTile;
import tiles.JailTile;
import tiles.PropertyTile;
import playerData.PropertyManager;

import java.util.HashMap;
import java.util.Queue;



public class Game {

	
	// private properties

	private ArrayList<Player> allPlayers;	// at least two players
	private HashMap<String,Integer> playerPositions;

	private int currentTurn;
	private Board board;
	private CardHandler dealer;
	
	
	
	
	// Initialiser
	
	public Game(Queue<Card> attackPack, Queue<Card> secPack, ArrayList<Player> inputPlayers, Board inputBoard) {
		if(inputPlayers != null) {
			allPlayers = inputPlayers;
			currentTurn = 0;
			board = inputBoard;
			
			playerPositions = new HashMap<>();
			allPlayers.forEach(i->{
				playerPositions.put(i.getName(), 0);
			});
		}
		
		dealer = new CardHandler(attackPack,secPack);
	}

	public void continueGame() {
		Player currentPlayer = allPlayers.get(currentTurn);
		
		boolean gameOver = false;
		while(allPlayers.size() > 1 && !gameOver) {
			showPlayerPositions();// shows initial positions of players
			System.out.println("=========It is " + currentPlayer.getName() +"'s turn=========");
			System.out.println(currentPlayer.getName() + " has Security Level: " + currentPlayer.getSecurityLevel() + " Money: " + currentPlayer.getMoney());
			int menuChoice = showChoice();
			
			JailTile jailTile = (JailTile) board.getTile(30);//jail tile is saved for checking if player is in jail
			
			boolean isMoving = false; // loops the switch statement until player wants to roll the dice
			
			do {// added do while loop, continues showing choice unless player wants to move
				
				switch(menuChoice) {
				case 0:
					// roll two dices
					Random random = new Random();
					int dice1 = random.nextInt(7);		 
					int dice2 =random.nextInt(7);
					// move to new position
					System.out.println(currentPlayer.getName() + " rolled: " + (dice1+dice2));
					
					//Will only allow player to move if they are not in jail
					if(!jailTile.getJail(currentPlayer.getName())) {
						
						movePlayer(currentPlayer.getName(), dice1 + dice2);
					}
					else {
						dice1 = 6;
						dice2 = 6;
						System.out.println(currentPlayer.getName() + " is currently in a ransomware attack!");
						System.out.println(currentPlayer.getName() + " cannot move until they recovered from the ransomeware attack!");
						System.out.println("\n"+"There are " + ( 3-jailTile.getJailTime(currentPlayer.getName()) ) + " turns remaining till they have recovered!" +"\n"); 
						if(dice1 + dice2 == 12) {
							System.out.println(currentPlayer.getName() + " has rolled two 6s!, they have broken out of the ransomware attack early");
							jailTile.leaveJail(currentPlayer.getName());
							movePlayer(currentPlayer.getName(), 1);
						}
						else {
							jailTile.incrementalJailTurn(currentPlayer.getName().trim());
							
							//checks if player has left after increment
							if(jailTile.getJail(currentPlayer.getName().trim()) == false){
								System.out.println(currentPlayer.getName().trim() + " has recovered from the ransomware attack!" + "\n");
							}
						}
						
					}
					
					// print new position
					showPlayerPositions();
					checkPlayerPosition(currentPlayer);
					checkElimination(currentPlayer);
					isMoving = true;
					break;
				case 1:
					// Build Server
					currentPlayer.processChoice(1);
					break;
				case 2:
					// Sell Server
					currentPlayer.processChoice(2);
					break;
				case 3:
					// Buying property code goes here
					currentPlayer.processChoice(3);
					break;
				case 4:
					// selling property code goes here
					currentPlayer.processChoice(4);
					break;
				case 5:
					// selling property code goes here
					currentPlayer.processChoice(5);
					break;
				case 6:
					//initiateTrade code goes here
					initiateTrade(currentPlayer);
					break;
				case 7:
					// Return to Menu
					gameOver = true;
					allPlayers = null;
					break;
				} // end switch
				if(!isMoving && !gameOver) {
					showPlayerPositions();// shows initial positions of players
					System.out.println("=========It is " + currentPlayer.getName() +"'s turn=========");
					System.out.println(currentPlayer.getName() + " has Security Level: " + currentPlayer.getSecurityLevel() + " Money: " + currentPlayer.getMoney());
					menuChoice = showChoice();
				}
		
			}while(!isMoving && !gameOver);
			
			
			
			if(allPlayers == null)//breaks out of loop if there are no players
				break;
			else if(currentTurn == allPlayers.size()-1 )// responsible for setting currentTurn to 0 
				currentTurn = 0;
			else {
				currentTurn++;
			}
			currentPlayer = allPlayers.get(currentTurn);
			
		}
		return;
	}

	private int showChoice() {
		String[] gameChoices = {"Roll dice","Buy Server","Sell Server","Buy Property","Sell Property","Mortgage Property","Initiate Trade","Return to Menu"};
		Menu gameOptions = new Menu(gameChoices,"Select a choice");
		return gameOptions.getChoice();
	}
	
	public Player getCurrentPlayer() {
		return allPlayers.get(currentTurn);
	}
	
	public ArrayList<Player> getAllPlayers() {
		return allPlayers;
	}
	
	public HashMap<String,Integer> getPlayerPositions(){
		return playerPositions;
	}
	
	
	public void checkPlayerPosition(Player playerIn) {
		String playerName = playerIn.getName();
		System.out.println(playerName + " has landed on: ");
		
		// gets the specific Tile object from board
		Tile landedTile = board.getTile(playerPositions.get(playerName));
		
		landedTile.displayMessage();
		landedTile.triggerEffect(this);
		
		// responsible for checking if Tile is wildcard
		if(landedTile instanceof WildCardTile) {
			Card drawnCard = null;
			String[] choices = {"Yes","No"};
			Menu drawChoice = new Menu(choices, "You have landed on a WildCard Tile");
			int selection = drawChoice.getChoice();
			
			switch(choices[selection]) {
			case "Yes":
				String drawType = ((WildCardTile) landedTile).getTileType();
				switch(drawType) {
				case "security":
					drawnCard = dealer.drawSecCard();
					break;
				case "attack":
					drawnCard = dealer.drawAttCard();
					break;
				}
				if(drawnCard != null)
					drawnCard.triggerEffect(this);
				break;
				
			case "No":
				System.out.println("You have chosen not to draw a card, The game will continue as normal");
				break;
			}
			
		}
		

	}

	

	public void showPlayerPositions() {
		String toPrint = "";
		String playerName;
		int playerTileNum;
		
		// composite each line of the message in each loop
		int allPlayersNum = allPlayers.size();
		for (int i = 0; i < allPlayersNum; i++) {
			// get current player name and position
			playerName = allPlayers.get(i).getName();
			playerTileNum = playerPositions.get(playerName);
			// composite current line
			toPrint += playerName + " is at tile " + playerTileNum + "; ";
		}
		
		// print
		System.out.println(toPrint);
	}
	
	public void movePlayer(String playerName, int value) {
		if(playerPositions.keySet().contains(playerName)) {//check if player is actually in the game and has a position
			
			int playerCurrentPos = playerPositions.get(playerName);
			playerCurrentPos += value;
			
			if(playerCurrentPos > 39) {// ensures player position isn't on a value greater than 39
				int difference = playerCurrentPos -39;
				playerCurrentPos = 0 + difference;
			}
			playerPositions.put(playerName, playerCurrentPos);
		}
	
	}
	
	public void checkElimination(Player player) {
		// check if the player exist
		if (allPlayers.indexOf(player) < 0) { // if doesn't exist (index = -1)
			// do something more here? return with a message?
			return;
		}
		
		// remove the player if his SecurityLevel is below 0
		if (player.getSecurityLevel() < 0) {
			allPlayers.remove(player);
		}
	}
	
	
	
	
	private void initiateTrade(Player player) {
		
		// all players name in a string array
		String[] allPlayersName = new String[allPlayers.size()];
		for (int i = 0; i < allPlayers.size(); i++) {
			allPlayersName[i] = allPlayers.get(i).getName();
		}
		
		// now select the target player (playerT)
		Menu playersOptions = new Menu(allPlayersName,"Select another player to trade with");
		Player playerT = allPlayers.get(playersOptions.getChoice());
		
		// ERROR: in case selecting current player as target player
		if (player == playerT) {
			System.out.println("ERROR! You can't trade with yourself!" + '\n');
			return;
		}
		
		// UI## display money and list properties of current player
		System.out.println("### You have money: $" + player.getMoney());
		System.out.println("### " + playerT.getName() + " has money: $" + playerT.getMoney() + '\n');
		//System.out.println("### Select a property you own form the list below to start:: " + '\n');
		
		// generate owned property list
		PropertyManager playerPrman = player.getPropertyManager();
		PropertyManager playerTPrman = playerT.getPropertyManager();
		
		int[] playerOwnedNum = playerPrman.getOwnedTiles();
		// return to main menu if player owns no property
		if (playerOwnedNum.length == 0) {
			System.out.println("ERROR! You own nothing! You can't trade! returning..." + '\n');
			return;
		}
		String[] playerOwnedName = new String[playerOwnedNum.length];
		
        for (int i = 0; i < playerOwnedNum.length; i++) {
        	playerOwnedName[i] = playerPrman.getOwnedTile(playerOwnedNum[i]).getTileName();
        }
        
        // menu
		Menu playerOwnedOptions = new Menu(playerOwnedName,"Select a property you own form the list below to start::");
		//Tile property = playerPrman.getOwnedTile(playerOwnedOptions.getChoice());
        PropertyTile property = (PropertyTile) playerPrman.getOwnedTile(playerOwnedNum[playerOwnedOptions.getChoice()]);
		
		// UI## make offer
		System.out.println("### Entre the money you want to offer:: " + '\n');
		double offerMax = playerT.getMoney();
		double offerIn = 0;
		
		Scanner input = new Scanner(System.in);
		boolean ok = false;
		do {
			try {
				offerIn = input.nextDouble();
				 if(offerIn >= 0 && offerIn <= offerMax ) {
					 ok = true;
				 }
				 else {
					 System.out.println("!!! " + playerT.getName() + " only has $" + offerMax);
					 System.out.println("!!! Enter a value between 0 and " + offerMax);
				 }
				 
			}catch(Exception e ) {
				System.out.println("Error input, try again!");
				input.nextLine();
			}
		}while(!ok);
		//input.close();
		
		// UI## accept or not. If not accept return to main menu.
		System.out.println(playerT.getName() + ", do you want to buy property " + property.getTileName() + " for $" + offerIn + " ?");
		
		String[] offerOptions = {"ACCEPT", "REJECT (return to main menu)"};
		Menu offerChoice = new Menu(offerOptions, "accept or reject?");
		
		if (offerChoice.getChoice() == 0) { // if accept
			System.out.println("%% offer accepted" + '\n');
			// transfer owner from Current to Target
			property.setOwner(playerT);
			playerPrman.removeProperty(property);
			playerTPrman.addProperty(property);
			
			// transfer money from Target to Current
			player.incrementAttribute("Money", offerIn);
			playerT.decrementAttribute("Money", offerIn);
			System.out.println("transfered $" + offerIn + " to " + playerT.getName() + '\n');
			
			return;
		} else {
			System.out.println("%% You rejected the offer, return to the main menu" + '\n');
			return;
		}
		
		// end
	}
}
