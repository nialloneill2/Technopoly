package test.tiles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import cards.CardCreator;
import cards.cardtypes;
import datahandler.Board;
import game.Game;
import playerData.Player;
import tiles.PropertyTile;
import tiles.*;

class WildCardTileTester {

	@Test
	void testSecurityDraw() {
		WildCardTile w1 = new WildCardTile("wild tile","security");
		PropertyTile t1 = new PropertyTile("Tile",400,null);
		
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(w1);
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
		
		Game g1 = new Game(c2.getCards(),c1.getCards(),players,b1);
		g1.checkPlayerPosition(p1);
	}
	@Test
	void testAttackDraw() {
		WildCardTile w1 = new WildCardTile("wild tile","attack");
		PropertyTile t1 = new PropertyTile("Tile",400,null);
		
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(w1);
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
		
		Game g1 = new Game(c2.getCards(),c1.getCards(),players,b1);
		g1.checkPlayerPosition(p1);
	}

}
