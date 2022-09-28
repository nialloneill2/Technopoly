/**
 * 
 */
package test.tiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import tiles.*;
import game.*;
import datahandler.*;
import cards.*;
import playerData.*;

import org.junit.jupiter.api.Test;

class PropertyTileTester {

	@Test
	void testPropertyTileInstantiation() {
		PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		assertNotNull(samplePropertyTile);
	}

	
	  @Test 
	  void testPropertyTileNullInstantiation() { try { PropertyTile
	  samplePropertyTile = new PropertyTile(null, 0, null); } catch (Exception e) {
	  assertEquals(e.getMessage(), "Error, Tile name cannot be null"); } }
	 
	@Test
	void testOutputDisplayMessage() {
		PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		samplePropertyTile.displayMessage();

	}

	@Test
	void testGetOwnerIfOwnerNull() {
		try {
			PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Error, cannot get Owner as owner is null");
		}

	}

	@Test
	void testGetServerLevel() {
		PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		assertEquals(samplePropertyTile.getServerLevel(), 0);

	}

	@Test
	void testDowngradeServerLevel() {
		PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		samplePropertyTile.downgradeServerLevel();
	}

	@Test
	void testPropertyCost() {
		PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		assertEquals(samplePropertyTile.getPropertyCost(), 5000);
	}

	@Test
	void testCheckGroupOwnership() {
		PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(samplePropertyTile);

		Player p1 = new Player("Matt");
		Player p2 = new Player("Renzo");
		ArrayList<Player> players = new ArrayList<>();

		players.add(p1);
		players.add(p2);

		samplePropertyTile.checkGroupOwnership(p1);

	}

	@Test
	void testTriggerEffect() {
		PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(samplePropertyTile);

		Board sampleBoard = new Board("testBoard", tiles);

		CardCreator sampleCardsOne = new CardCreator("dummycards1");
		sampleCardsOne.createCard("bonus", "sample", cardtypes.BONUSCARD, "money", 100);

		CardCreator sampleCardsTwo = new CardCreator("dummycards2");
		sampleCardsTwo.createCard("attack", "sample", cardtypes.DAMAGECARD, "money", 100);

		Player p1 = new Player("Matt");
		Player p2 = new Player("Renzo");
		Player p3 = new Player("Jam");
		ArrayList<Player> players = new ArrayList<>();

		players.add(p1);
		players.add(p2);
		players.add(p3);

		Game testGame = new Game(sampleCardsOne.getCards(), sampleCardsTwo.getCards(), players, sampleBoard);
		samplePropertyTile.triggerEffect(testGame);
	}

	

	@Test
	void testSetOwner() {
		PropertyTile samplePropertyTile = new PropertyTile("SampleProperty", 5000, null);
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(samplePropertyTile);

		Board sampleBoard = new Board("testBoard", tiles);

		CardCreator sampleCardsOne = new CardCreator("dummycards1");
		sampleCardsOne.createCard("bonus", "sample", cardtypes.BONUSCARD, "money", 100);

		CardCreator sampleCardsTwo = new CardCreator("dummycards2");
		sampleCardsTwo.createCard("attack", "sample", cardtypes.DAMAGECARD, "money", 100);

		Player p1 = new Player("Matt");
		Player p2 = new Player("Renzo");
		Player p3 = new Player("Jam");
		ArrayList<Player> players = new ArrayList<>();

		players.add(p1);
		players.add(p2);
		players.add(p3);

		Game testGame = new Game(sampleCardsOne.getCards(), sampleCardsTwo.getCards(), players, sampleBoard);
		samplePropertyTile.triggerEffect(testGame);
		samplePropertyTile.setOwner(p3);

	}
	@Test
	void testMortgageProperty() {
		PropertyTile samplePropertyTileMortgage = new PropertyTile("SampleProperty", 5000, null);
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(samplePropertyTileMortgage);
		Player p10 = new Player("Matt");
		Player p20 = new Player("Renzo");
		Player p30 = new Player("Jam");
		ArrayList<Player> players = new ArrayList<>();
		Board sampleBoard = new Board("testBoard", tiles);

		CardCreator sampleCardsOne = new CardCreator("dummycards1");
		sampleCardsOne.createCard("bonus", "sample", cardtypes.BONUSCARD, "money", 100);

		CardCreator sampleCardsTwo = new CardCreator("dummycards2");
		sampleCardsTwo.createCard("attack", "sample", cardtypes.DAMAGECARD, "money", 100);

		players.add(p10);
		players.add(p20);
		players.add(p30);
		Game testGameTwo = new Game(sampleCardsOne.getCards(), sampleCardsTwo.getCards(), players, sampleBoard);
		p10.incrementAttribute("Money", 5000);
		samplePropertyTileMortgage.triggerEffect(testGameTwo);
		samplePropertyTileMortgage.mortgageProperty();
		
		
	}
	

}
