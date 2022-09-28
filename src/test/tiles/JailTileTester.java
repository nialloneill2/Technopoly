package test.tiles;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import cards.CardCreator;
import cards.cardtypes;
import datahandler.Board;
import game.Game;
import playerData.Player;
import tiles.JailTile;
import tiles.Tile;

class JailTileTester {

	@Test
	void testInstaniation() {
		JailTile tester = new JailTile("SampleName");
		assertNotNull(tester);
	}

	@Test
	void testNullInstantiation() {
		try {
			JailTile tester = new JailTile(null);
		} catch (Exception e) {
			assertEquals("Error, JailTile cannot be null", e.getMessage());
		}
	}

	@Test
	void testDisplayMessage() {
		JailTile tester = new JailTile("SampleName");
		tester.displayMessage();
		assertNotNull(tester);
	}

	@Test
	void testTriggerEffect() {
		JailTile sampleJailTile = new JailTile("SampleName");
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(sampleJailTile);
		
		Board sampleBoard = new Board("testBoard",tiles);
		
		CardCreator sampleCardsOne = new CardCreator("dummycards1");
		sampleCardsOne.createCard("bonus", "sample", cardtypes.BONUSCARD,"money" ,100);
		
		
		
		CardCreator sampleCardsTwo = new CardCreator("dummycards2");
		sampleCardsTwo.createCard("attack","sample",cardtypes.DAMAGECARD,"money",100);
		
		Player p1 = new Player("Bob");

		ArrayList<Player> players = new ArrayList<>();
		
		players.add(p1);
		
		Game testGame = new Game(sampleCardsOne.getCards(),sampleCardsTwo.getCards(),players,sampleBoard);
		sampleJailTile.triggerEffect(testGame);
	    assertTrue(sampleJailTile.getJail("Bob"));
		
		
		
	}
	
	@Test
	void testIncrementJailTurn() {
		JailTile sampleJailTile = new JailTile("SampleName");
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(sampleJailTile);
		
		Board sampleBoard = new Board("testBoard",tiles);
		
		CardCreator sampleCardsOne = new CardCreator("dummycards1");
		sampleCardsOne.createCard("bonus", "sample", cardtypes.BONUSCARD,"money" ,100);
		
		
		
		CardCreator sampleCardsTwo = new CardCreator("dummycards2");
		sampleCardsTwo.createCard("attack","sample",cardtypes.DAMAGECARD,"money",100);
		
		Player p1 = new Player("Bob");

		ArrayList<Player> players = new ArrayList<>();
		
		players.add(p1);
		
		Game testGame = new Game(sampleCardsOne.getCards(),sampleCardsTwo.getCards(),players,sampleBoard);
		sampleJailTile.triggerEffect(testGame);
		sampleJailTile.incrementalJailTurn("Bob");
	    assertEquals(sampleJailTile.getJailTime("Bob"), 2);
		
	}
	
	@Test
	void testLeaveJail() {
		JailTile sampleJailTile = new JailTile("SampleName");
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(sampleJailTile);
		
		Board sampleBoard = new Board("testBoard",tiles);
		
		CardCreator sampleCardsOne = new CardCreator("dummycards1");
		sampleCardsOne.createCard("bonus", "sample", cardtypes.BONUSCARD,"money" ,100);
		
		
		
		CardCreator sampleCardsTwo = new CardCreator("dummycards2");
		sampleCardsTwo.createCard("attack","sample",cardtypes.DAMAGECARD,"money",100);
		
		Player p1 = new Player("Bob");

		ArrayList<Player> players = new ArrayList<>();
		
		players.add(p1);
		
		Game testGame = new Game(sampleCardsOne.getCards(),sampleCardsTwo.getCards(),players,sampleBoard);
		sampleJailTile.triggerEffect(testGame);
		sampleJailTile.incrementalJailTurn("Bob");
		sampleJailTile.incrementalJailTurn("Bob");
		sampleJailTile.incrementalJailTurn("Bob");

		assertFalse(sampleJailTile.getJail("Bob"));
		
	}

}
