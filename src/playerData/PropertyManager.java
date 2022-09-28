package playerData;
import tiles.Tile;
import tiles.PropertyTile;
import game.Menu;
import java.util.ArrayList;

public class PropertyManager {
private ArrayList<Tile> ownedProperties;

	protected PropertyManager() {
		ownedProperties = new ArrayList<>();
	}
	//private method responsible for getting a choice of 
	private int selectOwnedProperty() {
		if(ownedProperties.size() == 0 || ownedProperties == null) {
			System.out.println("You cannot do that, you have no owned Tiles");
			return -1;
		}
		String[] ownedTileNames = new String[ownedProperties.size()];
		for(int i = 0; i < ownedProperties.size();i++) {
			ownedTileNames[i] = ownedProperties.get(i).getTileName() + " Tile Number: " + ownedProperties.get(i).getTileNumber();
		}
		Menu propertySelection = new Menu(ownedTileNames,"Select an owned tile");
		
		return propertySelection.getChoice();
	}
	
	//upgrades serverLevel from propertyTile
	protected void buildServer(Player player) {
		int choiceIndex = selectOwnedProperty();
		if(choiceIndex == -1) {
			return;
		}
		
		PropertyTile selectedTile = (PropertyTile) ownedProperties.get(choiceIndex);
		
		if(selectedTile.getOwner().trim().compareTo(player.getName().trim())==0) 
			selectedTile.upgradeServerLevel();
		else {
			System.out.println("Error! you don't own that tile");
		}
	}
	//downgrades serverLevel from PropertyTile
	protected void sellServer(Player player) {
		int choiceIndex = selectOwnedProperty();
		if(choiceIndex == -1) {
			return;
		}
		PropertyTile selectedTile = (PropertyTile) ownedProperties.get(choiceIndex);
		
		if(selectedTile.getOwner().trim().compareTo(player.getName().trim())==0) 
			selectedTile.downgradeServerLevel();
		else {
			System.out.println("Error! you don't own that tile");
		}
	}
	
	//responsible for removing selectedProperty from ownedProperties which is being sold
	protected void sellProperty(Player player) {
		int choiceIndex = selectOwnedProperty();
		if(choiceIndex == -1) {
			return;
		}
		PropertyTile selectedTile = (PropertyTile) ownedProperties.get(choiceIndex);
		
		if(selectedTile.getOwner().trim().compareTo(player.getName().trim())==0) {
			selectedTile.removeOwner();
			ownedProperties.remove(choiceIndex);
		}
			
		else {
			System.out.println("Error! you don't own that tile");
		}
		
	}
	//responsible for adding bought properties to owned properties
	// returns true if property was successfully added, else returns false
	public boolean addProperty(PropertyTile boughtProperty) {
		if(boughtProperty != null) {
			ownedProperties.add(boughtProperty);
			return true;
		}
			return false;
	}
	
	public boolean removeProperty(PropertyTile soldProperty) {
		if(soldProperty != null) {
			ownedProperties.remove(soldProperty);
			return true;
		}
			return false;
	}
	
	
	protected void mortgageProperty(Player player) {
		int choiceIndex = selectOwnedProperty();
		if(choiceIndex == -1) {
			return;
		}
		PropertyTile selectedTile = (PropertyTile) ownedProperties.get(choiceIndex);
		if(selectedTile.getOwner().trim().compareTo(player.getName().trim())==0) 
			selectedTile.mortgageProperty();

	}
	
	public Tile getOwnedTile(int tileNumber) {
		for(Tile propertyTile : ownedProperties) {
			if(propertyTile.getTileNumber() == tileNumber)
				return propertyTile;
		}
		return null;
	}

	public int[] getOwnedTiles() {// iterates through owned properties and returns an array of all the tileNumbers
		int[] tileNumbers = new int[ownedProperties.size()];
		for(int i = 0; i < ownedProperties.size();i++) {
			tileNumbers[i] = ownedProperties.get(i).getTileNumber();
		}
		
		return tileNumbers;
	}
}
