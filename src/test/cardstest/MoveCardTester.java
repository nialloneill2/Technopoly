package test.cardstest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import cards.CardCreator;
import cards.MoveCard;
import cards.cardtypes;
import datahandler.Board;
import game.Game;
import playerData.Player;
import tiles.MoveTile;
import tiles.PropertyTile;
import tiles.Tile;

class MoveCardTester {
	@Test
	void checkNormalInstantiation() {
		MoveCard m1 = new MoveCard("VPN", "Allows you to move forward 6 tiles without triggering any effects" , 1);
		assertNotNull(m1);
	}
	@Test
	void checkNegativeTileCreate() {
		try {
			MoveCard m1 = new MoveCard("VPN", "Allows you to move forward 6 tiles without triggering any effects", -1);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error, a Move tile's move value must be equal or greater than 0 and less than or equal to 39");
		}
		
	}
	@Test
	void checkHigherTileCreate() {
		try {
			MoveCard m1 = new MoveCard("VPN", "Allows you to move forward 6 tiles without triggering any effects", 40);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error, a Move tile's move value must be equal or greater than 0 and less than or equal to 39");
		}
		
	}
	@Test
	void checkNullNameInstantiation() {
		try {
			MoveCard m1 = new MoveCard(null, null, -1);
		}catch(Exception e) {
			assertEquals(e.getMessage(),"Error, a Move tile's move value must be equal or greater than 0 and less than or equal to 39");
		}
	}

	@Test
	void testTriggerEffect() {
		MoveTile m1 = new MoveTile("Go to tile 1", 1);
		PropertyTile t1 = new PropertyTile("Tile",400,null);
		MoveCard card1 = new MoveCard("VPN", "Allows you to move forward 6 tiles without triggering any effects", 6);
		
		ArrayList<Tile> tiles = new ArrayList<>();
		tiles.add(m1);
		tiles.add(t1);
		Board b1 = new Board("testBoard",tiles);
		
		CardCreator c1 = new CardCreator("dummycards1");
		c1.createCard("Dummy move", "dummy", cardtypes.MOVECARD ,5);
		
		
		
		CardCreator c2 = new CardCreator("dummycards2");
		c2.createCard("Dummy move","dummy",cardtypes.MOVECARD,5);
		
		Player p1 = new Player("bob");
		Player p2 = new Player("jeff");
		ArrayList<Player> players = new ArrayList<>();
		
		players.add(p1);
		players.add(p2);
		
		Game g1 = new Game(c1.getCards(),c2.getCards(),players,b1);
		System.out.println(g1.getPlayerPositions().get("bob"));
		HashMap<String,Integer> pos = g1.getPlayerPositions();
		card1.triggerEffect(g1);
		
		assertEquals(6 ,g1.getPlayerPositions().get("bob"));
	}

}
