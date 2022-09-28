package datahandler;

import java.util.ArrayList;
import java.util.Queue;

import cards.*;
import tiles.*;
import game.Menu;

public class Initialiser {

	private ArrayList<CardCreator> benefitPacks;
	private ArrayList<CardCreator> lossPacks;
	private ArrayList<Board> allBoards;
	private static Initialiser init = new Initialiser();

	private Initialiser() {
		benefitPacks = new ArrayList<CardCreator>();
		lossPacks = new ArrayList<CardCreator>();
		allBoards = new ArrayList<Board>();
	}

	/*Method is repsonsible for housing all hardcoded CardCreator objects and creating Cards with 
	 * HardCoded values. Created Card packs are added to either benefitPacks or LossPacks depending
	 * on what type of Cardpack it is.
	 */
	private void initialiseCards() {

		CardCreator pack1 = new CardCreator("SecPack1");
		pack1.createCard("VPN", "Allows you to move forward 6 tiles without triggering any effects ", 
				cardtypes.MOVECARD, "money",500);
		pack1.createCard("Encryption",
				"You have improved your security practices! Increase security by 500", cardtypes.BONUSCARD,"security",500);
		pack1.createCard("Discovered vulnerability",
				"You discovered a vulnerability in a company you have already hacked! Increase server level by 2",
				cardtypes.UPGRADECARD,2);
		pack1.createCard("Agile Methodology","A recent switch in working methodology has paid off, move current position forward by 3 tiles",
				cardtypes.MOVECARD,5);
		pack1.createCard("Stolen funds", "You have gained access to a company's financial assets! Increase money by 500", cardtypes.BONUSCARD,"money",500);
		pack1.createCard("Implemented 2FA ", "Implementing 2FA has increased your own security by 500!", cardtypes.BONUSCARD,5);
		pack1.createCard("Compromised admin account", "You discovered a vulnerability in a company you have already hacked! Increase server level by 2",
				cardtypes.UPGRADECARD,2);
		benefitPacks.add(pack1);

		CardCreator pack2 = new CardCreator("AttPack1");
		pack2.createCard("Security Breach",
				"You have detected vulnerabilities on another player's system, target opponent player to steal X security level from them",
				cardtypes.DAMAGECARD,"security",500);
		pack2.createCard("Business Credentials Compromise",
				"You have successfully lured an unsuspecting employee to hand over financial credentials, select target player to steal 500 money from them",
				cardtypes.DAMAGECARD,"money",500);
		pack2.createCard("DDoS Attack",
				"You have the ability to perfrom a denial of service attack on an opponent, lowering their server level by 2",
				cardtypes.DOWNGRADECARD,2);
		pack2.createCard("Malware Leak",
				"A company that you have hacked has regained control over one of their servers, reduce one properties serverlevel by one",
				cardtypes.DOWNGRADECARD,1);

		pack2.createCard("Remote Access Trojan",
				"You have infilitrated a rival and can directly control their machines, enter a tile number to move a player to ",
				cardtypes.MOVECARD,0);
		pack2.createCard("Cryptocurrency Surge",
				"You have gained access to another player's crypto wallet, steal 500 money from a targetted player",
				cardtypes.DAMAGECARD,"money",500);
		
		lossPacks.add(pack2); 
	}

	/*Method is responsible for housing all hard coded Tile objects and assigning them to a board.
	 * All board objects are added to allBoards property.
	 */
	private void initialiseBoards() {

		// individual tiles are added to this arrayList
		ArrayList<Tile> tileset1 = new ArrayList<>(); 
 // 3 = 500 2 = 700  1 = 800 
		//Normal Tile
				NormalTile tile0 = new NormalTile("Start");
				tileset1.add(tile0);
				
				//Money Tile
			    MoneyTile tile1 = new MoneyTile("Rob Bank",500);
			    tileset1.add(tile1);
			    
				//Company Tile
				PropertyTile tile2 = new PropertyTile("Airbyte Europe",700,new int[] {2,4});
				tileset1.add(tile2);
				
				//Wild card tile
				WildCardTile tile3 = new WildCardTile( "Attack tile", "Attack" );
				tileset1.add(tile3);
				
				//Company Tile
				PropertyTile tile4 = new PropertyTile("Airbyte Asia",700,new int[] {2,4});
				tileset1.add(tile4);
				
				//Other Property
				PropertyTile tile5 = new PropertyTile("BT USA",700,new int[] {5,25});
				tileset1.add(tile5);
				
				//Company Tile
				PropertyTile tile6 = new PropertyTile("Alfatek Asia",500,new int[] {6,8,9});
				tileset1.add(tile6);
				
				
				WildCardTile tile7 = new WildCardTile( "Security Tile", "Security" );
				tileset1.add(tile7);
				
				//Company Tile
				PropertyTile tile8 = new PropertyTile("Alfatek Europe",500,new int[] {6,8,9});
				PropertyTile tile9 = new PropertyTile("Alfatek USA",500,new int[] {6,8,9});
				tileset1.add(tile8);
				tileset1.add(tile9);
				
				MoveTile tile10 = new MoveTile("Under Attack!",30);
				tileset1.add(tile10);
				
				//Company Tile
				PropertyTile tile11 = new PropertyTile("Axle Asia",500,new int[] {11,12,13});
				PropertyTile tile12 = new PropertyTile("Axle Europe",500,new int[] {11,12,13});
				PropertyTile tile13 = new PropertyTile("Axle Africa",500,new int[] {11,12,13});
				tileset1.add(tile11);
				tileset1.add(tile12);
				tileset1.add(tile13);
				
				
				WildCardTile tile14 = new WildCardTile( "Security Tile 2", "Security" );
				tileset1.add(tile14);
				
				//Other property
				PropertyTile tile15 = new PropertyTile("Virgin Media Africa",700, new int[] {15,35});
				tileset1.add(tile15);
				
				//Company Tile
				PropertyTile tile16 = new PropertyTile("Aurora IT Australia",700,new int[] {16,18});
				tileset1.add(tile16);
				
				
				WildCardTile tile17 = new WildCardTile("Attack tile", "Attack");
				tileset1.add(tile17);
				
				//Company Tile
				PropertyTile tile18 = new PropertyTile("Aurora IT North America",700,new int[] {16,18});
				tileset1.add(tile18);
				
				//Unique Property
				PropertyTile tile19 = new PropertyTile("Phishing Website",800,null);
				tileset1.add(tile19);
				
				
				NormalTile tile20 = new NormalTile("Secure Gateway");
				tileset1.add(tile20);
				
				//Company Tile
				PropertyTile tile21 = new PropertyTile("2Gig Tech South America",500,new int[] {21,22,24});
				PropertyTile tile22 = new PropertyTile("2Gig Tech Asia",500,new int[] {21,22,24});
				tileset1.add(tile21);
				tileset1.add(tile22);
				
				//Money tile
				MoneyTile tile23 = new MoneyTile("Rob Bank",500);
				tileset1.add(tile23);
				
				//Company Tile
				PropertyTile tile24 = new PropertyTile("2Gig Tech Europe",500,new int[] {21,22,24});
				tileset1.add(tile24);
				
				//Unique property
				PropertyTile tile25 = new PropertyTile("BT Africa",700,new int[] {5,25});
				tileset1.add(tile25);
				
				//Company Tile
				PropertyTile tile26 = new PropertyTile("Excelero Antartica",500,new int[] {26,28,29});
				tileset1.add(tile26);
				
				
				WildCardTile tile27 = new WildCardTile( "Security Tile", "Security" );
				tileset1.add(tile27);
				
				//Company Tile
				PropertyTile tile28 = new PropertyTile("Excelero Europe",500,new int[] {26,28,29});
				PropertyTile tile29 = new PropertyTile("Excelero USA",500,new int[] {26,28,29});
				tileset1.add(tile28);
				tileset1.add(tile29);
				
				JailTile tile30 = new JailTile("Ransomware Attack");
				tileset1.add(tile30);
				
				//Company Tile
				PropertyTile tile31 = new PropertyTile("Ideas2 IT Australia",700,new int[] {31,33});
				tileset1.add(tile31);
				
				
				DamageTile tile32 = new DamageTile("Data Breach","security",500);
				tileset1.add(tile32);
				
				//Company Tile
				PropertyTile tile33 = new PropertyTile("Ideas2 IT Asia",700,new int[] {31,33});
				tileset1.add(tile33);
				
				
				WildCardTile tile34 = new WildCardTile( "Attack tile", "Attack" );
				tileset1.add(tile34);
				
				//Other property
				PropertyTile tile35 = new PropertyTile("Virgin Media USA",700, new int[] {15,35});
				tileset1.add(tile35);
				
				//Company Tile
				PropertyTile tile36 = new PropertyTile("Innominds Europe",500,new int[] {36,38,39});
				tileset1.add(tile36);
				
				//Unique property
				PropertyTile tile37 = new PropertyTile("Password Stealer",800,null);
				tileset1.add(tile37);
				
				//Company Tile
				PropertyTile tile38= new PropertyTile("Innominds Asia",500,new int[] {36,38,39});
				PropertyTile tile39 = new PropertyTile("Innominds Australia",500,new int[] {36,38,39});
				tileset1.add(tile38);
				tileset1.add(tile39);
				
				// The arrayList of tiles is passed to the board class in it's constructor when it is implemented.
				Board mainBoard = new Board("MainBoard", tileset1);
				allBoards.add(mainBoard);
				
			}
		
	

	
	// This method loads the hard coded objects into the properties/ returns singleton object.
	public static Initialiser getInit() {
		init.initialiseCards();
		init.initialiseBoards();
		return init;
	}

	/*Shows all benefit cards in a menu when called and allows user to select one.
	 * SelectedCard is returned after selection.
	 */
	public Queue<Card> getBenefitCards(){
		if(benefitPacks == null || benefitPacks.size() == 0)
			return null;
		
		String[] choices = new String[benefitPacks.size()];
		for(int i = 0; i < benefitPacks.size();i++) {
			choices[i] = benefitPacks.get(i).getName();
		}
		Menu cardChoice = new Menu(choices,"Select a benefit card pack");
		CardCreator selectedCards = benefitPacks.get(cardChoice.getChoice());
		
		return selectedCards.getCards();
	}
	public Queue<Card> getBenefitCards(int index){
		
		try {
			if(benefitPacks!= null && benefitPacks.get(index)!= null) {
				return benefitPacks.get(index).getCards();
			}
			
			return null;
		}catch(Exception e) {
			System.out.println("Error no BenefitCards index exist with that name or error input");
			return null;
		}
	
	}
	
	/*Shows all loss cards in a menu when called and allows user to select one.
	 * SelectedCard is returned after selection.
	 */
	public Queue<Card> getLossCards(){
		if(lossPacks == null || lossPacks.size() == 0)
			return null;
		String[] choices = new String[benefitPacks.size()];
		for(int i = 0; i < lossPacks.size();i++) {
			choices[i] = lossPacks.get(i).getName();
		}
		Menu cardChoice = new Menu(choices,"Select a loss card pack");
		CardCreator selectedCards = lossPacks.get(cardChoice.getChoice());
		return selectedCards.getCards();
	}
	
	
	public Queue<Card> getLossCards(int index){
		try {
			if(lossPacks!= null && lossPacks.get(index)!= null) {
				return lossPacks.get(index).getCards();
			}
			
			return null;
		}catch(Exception e) {
			System.out.println("Error no LossCards index exist with that name or error input");
			return null;
		}
	
	}

	public Board getBoards(String input) {
		try {
			
			for(Board b: allBoards) {
				if(b.getBoardName().compareTo(input) == 0) {
					return b;
				}
			}
			return null;
		}catch(Exception e) {
			System.out.println("Error no boards index exist with that name or error input");
			return null;
		}
	}
}
