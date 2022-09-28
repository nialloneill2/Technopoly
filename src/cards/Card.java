package cards;
import game.Game;

public abstract class Card {

	private String cardName;
	private String cardDescription;
	
	
	protected Card(String name, String description) {
		cardName = name;
		cardDescription = description;
	
	}
	
	/*
	 * Displays information about tile such as name and
	 * a description of what the card does
	 */
	public void printMessage() {
		System.out.println(cardName);
		System.out.println(cardDescription);
	}
	
	/*
	 * Triggers card affect on input player
	 */
	public abstract void triggerEffect(Game currentGame);
	
}
