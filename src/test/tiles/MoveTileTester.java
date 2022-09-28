package test.tiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;


import org.junit.jupiter.api.Test;

import tiles.*;
import datahandler.*;
import game.Game;
import playerData.Player;
import cards.*;

class MoveTileTester {

	
	@Test
	void checkNormalInstantiation() {
		MoveTile m1 = new MoveTile("Go to tile 1",1);
		assertNotNull(m1);
	}
	@Test
	void checkNegativeTileCreate() {
		try {
			@SuppressWarnings("unused")
			MoveTile m1 = new MoveTile("Go to tile 1ddd",-1);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error, a Move tile's move value must be equal or greater than 0 and less than or equal to 39");
		}
		
	}
	@Test
	void checkHigherTileCreate() {
		try {
			@SuppressWarnings("unused")
			MoveTile m1 = new MoveTile("Go to tile 1ddd",40);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error, a Move tile's move value must be equal or greater than 0 and less than or equal to 39");
		}
		
	}
	
	@Test
	void checkNullNameInstantiation() {
		try {
			@SuppressWarnings("unused")
			MoveTile m1 = new MoveTile(null,2);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error, Tile name cannot be null");
		}
	}
	@Test
	void testTriggerEffect() {
		// Target tile is set to 3 because this is the 3rd Tile object made in this j unit test case
		MoveTile m1 = new MoveTile("Go to tile 1",3);
		PropertyTile t1 = new PropertyTile("Tile",400,null);
		
		System.out.println(m1.getTileNumber());
		System.out.println(t1.getTileNumber());
		
		
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(m1);
		tiles.add(t1);
		Board b1 = new Board("testBoard",tiles);
		
		CardCreator c1 = new CardCreator("dummycards1");
		c1.createCard("Dummy bonus", "dummy", cardtypes.BONUSCARD,"money" ,500);
		
		
		
		CardCreator c2 = new CardCreator("dummycards2");
		c2.createCard("Dummy dmg","dummy",cardtypes.DAMAGECARD,"money",500);
		
		Player p1 = new Player("bob");
		Player p2 = new Player("jeff");
		ArrayList<Player> players = new ArrayList<>();
		
		players.add(p1);
		players.add(p2);
		
		Game g1 = new Game(c1.getCards(),c2.getCards(),players,b1);
		@SuppressWarnings("unused")
		HashMap<String,Integer> pos = g1.getPlayerPositions();
		m1.triggerEffect(g1);
		
		assertEquals(g1.getPlayerPositions().get("bob"), 1);
	}
	
	@Test
	void testMoveToJail() {
		// jail tile tile number is 6 because this is the 6th tile class made in this j unit test
		MoveTile m1 = new MoveTile("Go to tile 6",6);
		JailTile j1 = new JailTile("Jail");
		
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(m1);
		tiles.add(j1);
		Board b1 = new Board("testBoard",tiles);
		
		CardCreator c1 = new CardCreator("dummycards1");
		c1.createCard("Dummy bonus", "dummy", cardtypes.BONUSCARD,"money" ,500);
		
		
		
		CardCreator c2 = new CardCreator("dummycards2");
		c2.createCard("Dummy dmg","dummy",cardtypes.DAMAGECARD,"money",500);
		
		Player p1 = new Player("bob");
		Player p2 = new Player("jeff");
		ArrayList<Player> players = new ArrayList<>();
		
		players.add(p1);
		players.add(p2);
		
		Game g1 = new Game(c1.getCards(),c2.getCards(),players,b1);
		m1.triggerEffect(g1);
		
		assertEquals(j1.getJail(p1.getName()),true);
	}
}
