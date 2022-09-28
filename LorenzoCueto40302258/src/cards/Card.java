package cards;
import game.Player;

public abstract class Card {

	private String cardName;
	private String cardDescription;
	private boolean targetSelf;
	
	protected Card(String name, String description, boolean selfTarget) {
		cardName = name;
		cardDescription = description;
		targetSelf = selfTarget;
	}
	
	/*
	 * Displays information about tile such as name and
	 * a description of what the card does
	 */
	public abstract void printMessage();
	
	/*
	 * Triggers card affect on input player
	 */
	public abstract void triggerEffect(Player player);
	
}
