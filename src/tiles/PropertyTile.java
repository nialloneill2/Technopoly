package tiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import cards.*;
import game.Game;
import game.Menu;
import playerData.Player;

public class PropertyTile extends Tile {

	private double propertyCost;
	private int serverLevel = 0;
	private int[] linkedProperties;
	private boolean isMortgaged;
	Player owner;

	public PropertyTile(String name, double cost, int[] properties) {
		super(name);
		serverLevel = 0;
		if (properties != null) {
			linkedProperties = properties;
			Arrays.sort(linkedProperties); // sorts linked properties to make checking group ownership easier
		} else {
			linkedProperties = null;
		}
		propertyCost = cost;
		isMortgaged = false;
	}

	@Override
	public void displayMessage() {
		System.out.println("\nTile Name: " + getTileName());
		System.out.println("Property Cost: " + this.propertyCost);
		System.out.println("Server Level: " + this.serverLevel);
		System.out.println("Mortgaged: " + this.isMortgaged);
	}

	// responsible for asking player to buy or
	// trigger rent
	@Override
	public void triggerEffect(Game currentGame) {
		Player currentPlayer = currentGame.getCurrentPlayer();
		ArrayList<Player> allPlayers = currentGame.getAllPlayers();
		ArrayList<Player> auctionPlayers = new ArrayList<Player>();
		for (Player p : allPlayers) {
			if (p.getName() != currentPlayer.getName()) { // adds players to array list needed for auctioning a property
				auctionPlayers.add(p);
			}
		}
		// displayMessage();

		// first check to see if property is not owned by anyone and if player has
		// enough money to purchase
		if (owner == null && currentPlayer.getMoney() >= this.propertyCost) {
			// brings player to a menu
			Menu options = new Menu(new String[] { "Buy Property", "Skip to initiate auction" }, "Options");
			// get user choice
			int choice = options.getChoice();

			switch (choice) {
			case 0: // buy property
				currentPlayer.decrementAttribute("money", this.propertyCost); // charge player for property

				owner = currentPlayer; // assign current player as owner of this property

				currentPlayer.getPropertyManager().addProperty(this); // adding property to list of owned properties via
																		// property manager

				allPlayers.set(allPlayers.indexOf(currentPlayer), currentPlayer); // replacing currentPLayer in all
																					// Players to reflect new values

				System.out.println(this.getTileName() + " Property Purchased!");
				System.out.println("Your money value is now " + currentPlayer.getMoney());
				System.out.println();
				break;
			case 1:
				initiateAuction(allPlayers, auctionPlayers); // initiate auction
				System.out.println();
				break;
			}
			// if the property doesn't have an owner but current player doesn't have enough
			// money to purchase, auction will be started
		} else if (owner == null && currentPlayer.getMoney() < this.propertyCost) {
			System.out.println("You do not have the funds to purchase this property, Auction initiated");
			initiateAuction(allPlayers, auctionPlayers);
			// trigger rent feature
		} else if (owner.getName() != null && this.isMortgaged == false && owner.getName() != currentPlayer.getName()) {
			System.out.println("You have landed on " + owner.getName() +"'s property!");
			currentPlayer.decrementAttribute("Security", (80 * (this.serverLevel + 1)));
			System.out.println("Your security value is now " + currentPlayer.getSecurityLevel());
			// if currentPlayer security level reaches 0 when they land on opponents tile
			if (currentPlayer.getSecurityLevel() <= 0) {
				owner.incrementAttribute("Money", currentPlayer.getMoney());
			}
			System.out.println();
			// if property is owned by current player
		} else if (owner.getName() != null && this.isMortgaged == false && owner.getName() == currentPlayer.getName()) {
			System.out.println("You have landed on your own property, no rent required");
		}
		// if property is owned and mortgaged
		else if (owner.getName() != null && this.isMortgaged == true) {
			System.out.println(
					"You have landed on an opponent's property, but it is currently mortgaged, no loss in resources");
		}

	}

	private void initiateAuction(ArrayList<Player> allPlayers, ArrayList<Player> allAuctionPlayers) {
		Player highestBidder = null;
		double highestBid = 0;
		double currentBid;
		for (int index = 0; index < allAuctionPlayers.size(); index++) {
			Scanner scan = new Scanner(System.in);
			boolean valid = false;
			while (!valid) {
				System.out.println(
						allAuctionPlayers.get(index).getName() + ", " + "Key 0 to skip or Input Bid more than 0:");
				try {
					currentBid = scan.nextDouble();
					if (currentBid == 0) {
						valid = true;
					} else {
						if (allAuctionPlayers.get(index).getMoney() == 0) {
							System.out.println("Sorry " + allAuctionPlayers.get(index).getName()
									+ ", You don't have any money to bid");
							valid = true;
						} else if (currentBid > highestBid && currentBid <= allAuctionPlayers.get(index).getMoney()) {
							highestBid = currentBid;
							highestBidder = allAuctionPlayers.get(index);
							valid = true;
						} else if (currentBid < highestBid) {
							System.out.println("Your bid was lower than highest amount! Please try again!");
							scan.nextLine();
						} else if (currentBid > highestBid && currentBid > allAuctionPlayers.get(index).getMoney()) {
							System.out.println("Sorry, you don't have the resources to bid");
							valid = true;
						}
					}
				} catch (InputMismatchException e) {
					System.out.println("Please enter valid amount!");
					scan.nextLine();
				}

			}
		}
		this.owner = highestBidder; // assigns the index of the highest bidding player as the owner
		highestBidder.decrementAttribute("Money", highestBid);
		highestBidder.getPropertyManager().addProperty(this);
		for (int index = 0; index < allPlayers.size(); index++) {
			if (allPlayers.get(index).getName() == highestBidder.getName()) {
				allPlayers.set(index, highestBidder);
			}

		}
	}

	// returns owner name of player object
	public String getOwner() {
		return owner.getName();
	}

	// returns server level of property
	public int getServerLevel() {
		return this.serverLevel;
	}

	// returns cost of property
	public double getPropertyCost() {
		return this.propertyCost;
	}

	// Responsible for checking a player owns all associated tiles before they can
	// build on a tile
	public boolean checkGroupOwnership(Player player) {
		int[] playerOwnedProperties = player.getPropertyManager().getOwnedTiles();

		if (playerOwnedProperties != null && playerOwnedProperties.length > 0) {
			Arrays.sort(playerOwnedProperties);
			return Arrays.equals(linkedProperties, playerOwnedProperties);
		}

		return false;
	}

	public void upgradeServerLevel() {
		// if there are linked properties and owner needs to own all associated tiles
		// and has enough money
		if (this.serverLevel < 5 && this.linkedProperties != null && this.checkGroupOwnership(owner)
				&& owner.getMoney() >= (100 * (this.serverLevel + 1))) {
			this.serverLevel += 1;
			System.out.println("Server Level Upgraded to " + this.serverLevel);
			owner.decrementAttribute("Money", (100 * this.serverLevel));
			System.out.println("Deducted: " + (100 * this.serverLevel) + ", your money is now " + owner.getMoney());
			if (this.serverLevel == 5) {
				System.out.println("Mainframe is now in effect!");
			}
			// if unique property and maximum is not exceeded
		} else if (this.serverLevel < 5 && this.linkedProperties == null
				&& owner.getMoney() >= (100 * (this.serverLevel + 1))) {
			this.serverLevel += 1;
			System.out.println("Server Level Upgraded to " + this.serverLevel);
			owner.decrementAttribute("Money", (100 * this.serverLevel));
			System.out.println("Deducted: " + (100 * this.serverLevel) + ", your money is now " + owner.getMoney());
			if (this.serverLevel == 5) {
				System.out.println("Mainframe is now in effect");
			}
			// if property is linked, but owner does not own the rest of the properties
		} else if (this.linkedProperties != null && this.checkGroupOwnership(owner) == false) {
			System.out.println("Unable to build server as you do not own the rest of the linked properties");
		} else if (owner.getMoney() < (100 * (this.serverLevel + 1))) {
			System.out.println("Unable to upgrade property level! Insufficient money");
			// if mainframe server is built
		} else if (this.serverLevel == 5) {
			System.out.println("Unable to upgrade property level as the maximum server level has been reached");
		}

	}

	public boolean handleCard(Card card, int value) {
		if (card instanceof DowngradeProperty) {
			this.serverLevel -= value;
			return true;
		} else if (card instanceof UpgradeProperty) {
			this.serverLevel += value;
			return true;
		}
		return false;
	}

	public void downgradeServerLevel() {
		if ((this.serverLevel + 1) > 1) {
			this.serverLevel -= 1;
			owner.incrementAttribute("Money", ((propertyCost * this.serverLevel) * 0.5));
			System.out
					.println(this.getTileName() + "Server level decreased by 1, your money is now " + owner.getMoney());

		} else {
			System.out.println("No current servers on property");
		}
	}

	public void setOwner(Player newOwner) {
		if (owner.getName() != null) {
			owner = newOwner;
			System.out.println("Transferred ownership of " + this.getTileName() + " to: " + newOwner.getName());
		} else {
			System.out.println("Property does not have an owner");
		}
	}

	public void removeOwner() {
		if (owner.getName() != null) {
			owner.incrementAttribute("Money", (this.propertyCost * this.serverLevel));
			System.out.println("Removed " + owner.getName() + " as owner, you have gained " + this.propertyCost + " towards your money");
			this.serverLevel = 0;
			owner = null;
		}
	}

	public void mortgageProperty() {
		if (this.isMortgaged == false) {
			owner.incrementAttribute("Money", (this.propertyCost * 0.5));
			System.out.println("Mortgaged property!");
			System.out.println("Your money value is now: " + owner.getMoney());
			this.isMortgaged = true;
		} else if (this.isMortgaged == true) {
			if (owner.getMoney() >= ((this.propertyCost * 0.5) + this.propertyCost)) {
				owner.decrementAttribute("Money",(this.propertyCost * 0.5));
				System.out.println("UnMortgaged Property!");
			} else if(owner.getMoney() < ((this.propertyCost * 0.5))) {
				System.out.println("Unable to unMortgage property, due to lack of funds");
			}
		}
	}

}
