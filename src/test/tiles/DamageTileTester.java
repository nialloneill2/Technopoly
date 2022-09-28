package test.tiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import tiles.*;
import datahandler.*;
import game.Game;
import playerData.Player;
import cards.*;

import org.junit.jupiter.api.Test;

class DamageTileTester {

	@Test
	void testNormalInstantiationMoney() {
		DamageTile d1 = new DamageTile("DamageT","money",500);
		assertNotNull(d1);
	}
	
	@Test
	void testNormalInstantiationSecurity() {
		DamageTile d1 = new DamageTile("DamageT","security",500);
		assertNotNull(d1);
	}
	
	@Test
	void testNullInstantiation() {

		try {
			@SuppressWarnings("unused")
			DamageTile d1 = new DamageTile(null,null,500);
		}catch(Exception e) {
			assertEquals(e.getMessage(), "Error, Tile name cannot be null");
		}
		
	}
	
	@Test
	void testNullStrings() {
		try {
			@SuppressWarnings("unused")
			DamageTile d1 = new DamageTile("null","null",500);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error, Tile name cannot be null");
		}
	}
	
	@Test
	void testValueLessThan0() {
		try {
			@SuppressWarnings("unused")
			DamageTile d1 = new DamageTile("DamageT","money",-20);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error values for Damage Tile cannot be null, loss cannot be < 0 and type must be money or security");
		}
	}

	@Test
	void testInvalidType() {
		try {
			@SuppressWarnings("unused")
			DamageTile d1 = new DamageTile("DamageT","invalid",500);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error values for Damage Tile cannot be null, loss cannot be < 0 and type must be money or security");
		}
	}
	@Test
	void testLoseMoney() {
		DamageTile d1 = new DamageTile("Damagets","money",500);
		PropertyTile t1 = new PropertyTile("Tile",400,null);
		
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(d1);
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
		d1.displayMessage();
		d1.triggerEffect(g1);
	
		assertEquals(p1.getMoney(),4500);
	}
	
	@Test
	void testLoseSecurity() {
		
		DamageTile d1 = new DamageTile("Damaget","security",500);
		PropertyTile t1 = new PropertyTile("Tile",400,null);
		
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(d1);
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
		//d1.displayMessage();
		d1.triggerEffect(g1);
	
		assertEquals(p1.getSecurityLevel(),4500);
	}
}
