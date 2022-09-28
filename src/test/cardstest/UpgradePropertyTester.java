package test.cardstest;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import cards.CardCreator;
import cards.UpgradeProperty;
import cards.cardtypes;
import datahandler.Board;
import game.Game;
import playerData.Player;
import tiles.JailTile;
import tiles.PropertyTile;
import tiles.Tile;

class UpgradePropertyTester {
	
	@Test
	void testInstaniation() {
		UpgradeProperty tester = new UpgradeProperty("test", "test", 0);
		assertNotNull(tester);
	}

	@Test
	void testNullInstantiation() {
		try {
			UpgradeProperty tester = new UpgradeProperty(null, null, 0);
		} catch (Exception e) {
			assertEquals("Error, UpgradeProperty cannot be null", e.getMessage());
		}
	}

	@Test
	void testDisplayMessage() {
		UpgradeProperty tester = new UpgradeProperty("test", "test", 0);
		tester.printMessage();
		assertNotNull(tester);
	}

	@Test
	void testTriggerEffect() {
		Player p1 = new Player("Bob");
		ArrayList<Player> players = new ArrayList<>();

		players.add(p1);

		PropertyTile pt1 = new PropertyTile("Tile1", 500, null);
		
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(pt1);

		Board b1 = new Board("testBoard",tiles);
		
		p1.getPropertyManager().addProperty(pt1);
       

		CardCreator c1 = new CardCreator("test1");
		c1.createCard("upgrade", "desc", cardtypes.UPGRADECARD, 10);

		CardCreator c2 = new CardCreator("test2");
		c2.createCard("upgrade", "desc", cardtypes.UPGRADECARD, 10);
		
		UpgradeProperty up1 = new UpgradeProperty("test", "test", 0);
		
		Game g1 = new Game(c1.getCards(), c2.getCards(),players, b1);
		
		up1.triggerEffect(g1);
		up1.triggerEffect(g1);
		
		PropertyTile tile = (PropertyTile) p1.getPropertyManager().getOwnedTile(0);
		
		assertEquals(tile.getServerLevel(), 2);

	}
}
