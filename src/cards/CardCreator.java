package cards;
import java.util.Queue;
import java.util.LinkedList;

public class CardCreator {

	private Queue<Card> cards;
	private String cardPackName;
	
	public CardCreator(String name){
		if(name != null) {
			this.cardPackName = name;
			this.cards = new LinkedList<Card>();
		}
		else {
			throw new IllegalArgumentException("Card Pack name can't be null");
		}
	}
	
	//Responsible for creating Bonus and Damage Cards
	public boolean createCard(String name, String description, cardtypes cardType, String type, double value){
		if(name == null || description == null || cardType == null || type == null )
			return false;
		Card card = null;
		try {
			switch(cardType) {
			default:
				break;
			case BONUSCARD:
				card = new BonusCard(name,description,type,value);
				break;
			case DAMAGECARD:
				card = new DamageCard(name,description,type,value);
				break;
			
			}
			if(card!= null) {
	
				cards.add(card);

				return true;
			}
		}catch(Exception e) {
			return false;
		}
	
		return false;
	}
	//Responsible for Creating other types of Cards that don't require a type for bonus/damage
	public boolean createCard(String name, String description,cardtypes cardType, int value){
		if(name == null || description == null || cardType == null  )
			return false;
		Card card = null;
		try {
			switch(cardType) {
			default:
				break;
			case MOVECARD:
				card = new MoveCard(name,description, value);
				break;
			case UPGRADECARD:
				card = new UpgradeProperty(name,description,value);
				break;
			case DOWNGRADECARD:
				card = new DowngradeProperty(name,description,value);
				break;
				
			}
			if(card!= null) {
				cards.add(card);
				return true;
			}
		}catch(Exception e) {
			return false;
		}
		return false;
	}
	
	
	public String getName() {
		return cardPackName;
	}
	
	public Queue<Card> getCards() {

		return cards;
	}
	
}
