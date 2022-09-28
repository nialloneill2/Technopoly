package test.tiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;



import org.junit.jupiter.api.Test;


import cards.CardCreator;
import cards.cardtypes;
import datahandler.Board;
import game.*;
import playerData.Player;
import tiles.*;

class MoneyTileTester {

	@Test
	void testInstaniation() {
		MoneyTile tester = new MoneyTile("SampleName", 1000);
		assertNotNull(tester);
	}

	@Test
	void testNullInstantiation() {
		try {
			@SuppressWarnings("unused")
			MoneyTile tester = new MoneyTile(null, 1);
		} catch (Exception e) {
			assertEquals("Error, Tile name cannot be null", e.getMessage());
		}
	}

	@Test
	void testDisplayMessage() {
		MoneyTile tester = new MoneyTile("SampleName", 1000);
		tester.displayMessage();
		assertNotNull(tester);
	}

	@Test
	void testTriggerEffect() {
		MoneyTile sampleMoneyTile = new MoneyTile("SampleNameTwo", 1000);
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(sampleMoneyTile);
		
		Board sampleBoard = new Board("testBoard",tiles);
		
		CardCreator sampleCardsOne = new CardCreator("dummycards1");
		sampleCardsOne.createCard("bonus", "sample", cardtypes.BONUSCARD,"money" ,100);
		
		
		
		CardCreator sampleCardsTwo = new CardCreator("dummycards2");
		sampleCardsTwo.createCard("attack","sample",cardtypes.DAMAGECARD,"money",100);
		
		Player p1 = new Player("Matt");
		Player p2 = new Player("Renz");
		ArrayList<Player> players = new ArrayList<>();
		
		players.add(p1);
		players.add(p2);
		
		Game testGame = new Game(sampleCardsOne.getCards(),sampleCardsTwo.getCards(),players,sampleBoard);
		sampleMoneyTile.triggerEffect(testGame);
		assertEquals(testGame.getCurrentPlayer().getMoney(), 6000);
		
		
		
	}

}
