package playerData;

import tiles.*;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PropertyManagerTester {

	
	@Test
	void testAddProperty() {
		Player p1 = new Player("test");
		PropertyTile t1 = new PropertyTile("Tile1",500,null);
		
		p1.getPropertyManager().addProperty(t1);
		
		if(!(p1.getPropertyManager().getOwnedTiles().length == 1)) {
			fail();
		}
		
	}
	@Test
	void testgetOwnedTile() {
		Player p1 = new Player("test");
		PropertyTile t1 = new PropertyTile("Tile1",500,null);
		
		p1.getPropertyManager().addProperty(t1);
		Tile takenTile = p1.getPropertyManager().getOwnedTile(t1.getTileNumber());
		
		assertEquals(t1.hashCode(),takenTile.hashCode());
	}
	
	@Test
	void testgetOwnedTileNotOwned() {
		Player p1 = new Player("test");
		PropertyTile t1 = new PropertyTile("Tile1",500,null);
		
		p1.getPropertyManager().addProperty(t1);
		Tile takenTile = p1.getPropertyManager().getOwnedTile(25);
		
		assertNull(takenTile);
	}
	
	@Test 
	void testGetOwnedTiles(){
		Player p1 = new Player("test");
		PropertyTile t1 = new PropertyTile("Tile1",500,null);
		PropertyTile t2 = new PropertyTile("Tile2",500,null);
		System.out.println(t1.getTileNumber());
		
		p1.getPropertyManager().addProperty(t1);
		p1.getPropertyManager().addProperty(t2);
		
		//checks for tile numbers 3 and 4 because the tiles are the 3rd and 4th tiles created in this class
		assertArrayEquals(new int[]{3,4},p1.getPropertyManager().getOwnedTiles());
	}

	@Test 
	void testGetOwnedTilesEmpty(){
		Player p1 = new Player("test");
		assertNull(p1.getPropertyManager().getOwnedTile(0));
	}


}
