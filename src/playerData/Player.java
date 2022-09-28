package playerData;


public class Player {
	private String playerName;
	private double securityLevel; 
	private double money; 
	private PropertyManager propertyHolder;
	

	
	public Player(String name) {
		if(name != null && name.compareTo("null") != 0) {
			this.playerName = name;
			securityLevel =4000;
			money = 5000;
			propertyHolder = new PropertyManager();
		}else {
			throw new IllegalArgumentException("Error, player name cannot be null or a nullstring");
		}
			
	}
	
	public String getName() {
		return playerName;
	}
	
	public double getMoney() {
		return money;
	}
	
	public double getSecurityLevel() {
		return securityLevel; 
	}
	
	public void incrementAttribute(String value, double amount) {
		if(value.equalsIgnoreCase("Security")) {
			securityLevel+=amount;
		}else if(value.equalsIgnoreCase("Money")) {
			money+=amount; 
		}
	}
	
	public void decrementAttribute(String value, double amount) {
		if(value.equalsIgnoreCase("Security")) {
			securityLevel-= amount; 
		}else if(value.equalsIgnoreCase("Money")) {
			money-= amount; 
		}
	}
	
	public PropertyManager getPropertyManager() {
		return propertyHolder;
	}
	
	// Value from the switch statement in game is just passed to here
	// Specific methods are called based on what the player chose in game class
	public void processChoice(int value) {
		switch(value) {
		case 1:
			propertyHolder.buildServer(this);
			break;
		case 2:
			propertyHolder.sellServer(this);
			break;
		case 3:
			propertyHolder.sellServer(this);
			break;
		case 4:
			propertyHolder.sellProperty(this);
			break;
		case 5:
			propertyHolder.mortgageProperty(this);
			break;
		}
	}
	
	
}
