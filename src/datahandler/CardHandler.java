package datahandler;
import cards.Card;
import java.util.Queue;

public class CardHandler {
	
	private Queue<Card> attackCards;
	private Queue<Card>securityCards;

	public CardHandler(Queue<Card> attPack, Queue<Card> secPack) {
		if(attPack != null && secPack != null && attPack.size() > 0 && secPack.size() > 0) {
			attackCards = attPack;
			securityCards = secPack;
		}
		//makes sure no packs are null else throws exception
		else if(attPack == null || secPack == null || (secPack == null && attPack == null)){
			throw new IllegalArgumentException("Error, card packs cannot be null");
		}
		//makes sure no packs are empty, else throws exception
		else if(attPack.size() == 0 || secPack.size() == 0 ||(attPack.size() ==0 && secPack.size() == 0)) {
			throw new IllegalArgumentException("Error, card packs must be greater than length 0");
		}

	}

	//When attack card is drawn, it will return to the end of the queue
	public Card drawAttCard() {

		Card cardHead = attackCards.poll();
		attackCards.add(cardHead);
		return cardHead;
	}
	//When security card is drawn, it will return to the end of the queue
	public Card drawSecCard() {
		
		Card cardHead = securityCards.poll();
		securityCards.add(cardHead);
		return cardHead;
	}
	
}
