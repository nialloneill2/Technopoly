package test.cardstest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;

import org.junit.jupiter.api.Test;

import cards.CardCreator;
import cards.*;

class CardCreatorTester {

	
	@Test
	void testInstantiation() {
		CardCreator test = new CardCreator("test");
		assertNotNull(test);
	}
	
	@Test
	void testNullInstantiation() {
		try {
			@SuppressWarnings("unused")
			CardCreator test = new CardCreator(null);
		}catch(Exception e) {
			assertEquals("Card Pack name can't be null",e.getMessage());
		}
		
	}
	@Test
	void createCardNullBonusDamage() {
		CardCreator c1 = new CardCreator("test");
		c1.createCard(null, null, null,null,10);
		Queue<Card> cards = c1.getCards();

		Card createdCard = cards.poll();
		
		assertNull(createdCard);
	}
	@Test
	void createCardNullMoveUpgradeDown() {
		CardCreator c1 = new CardCreator("test");
		c1.createCard(null, null, null,0);
		Queue<Card> cards = c1.getCards();

		Card createdCard = cards.poll();
		
		assertNull(createdCard);
	}
	
	
	@Test 
	void createBonusMoneyTest() {
		CardCreator c1 = new CardCreator("test");
		c1.createCard("bonus", "desc", cardtypes.BONUSCARD,"money",10);
		Queue<Card> cards = c1.getCards();

		Card createdCard = cards.poll();
		
		assertNotNull(createdCard);
	}
	@Test 
	void createBonusSecTest() {
		CardCreator c1 = new CardCreator("test");
		c1.createCard("bonus", "desc", cardtypes.BONUSCARD,"security",10);
		Queue<Card> cards = c1.getCards();

		Card createdCard = cards.poll();
		
		assertNotNull(createdCard);
	}
	@Test
	void createDamageCardTest() {
		CardCreator c1 = new CardCreator("test");
		c1.createCard("bonus", "desc", cardtypes.DAMAGECARD,"money",10);
		Queue<Card> cards = c1.getCards();
	
		Card createdCard = cards.poll();
		
		assertNotNull(createdCard);
	}
	
	@Test
	void createMoveCardTest() {
		CardCreator c1 = new CardCreator("test");
		c1.createCard("bonus", "desc", cardtypes.MOVECARD,10);
		Queue<Card> cards = c1.getCards();
		Card createdCard = cards.poll();
		
		assertNotNull(createdCard);
	}
	
	@Test
	void createUpgradeTest() {
		CardCreator c1 = new CardCreator("test");
		c1.createCard("bonus", "desc", cardtypes.UPGRADECARD,10);
		Queue<Card> cards = c1.getCards();
		Card createdCard = cards.poll();
		
		assertNotNull(createdCard);
	}
	@Test
	void createDownpgradeTest() {
		CardCreator c1 = new CardCreator("test");
		c1.createCard("bonus", "desc", cardtypes.DOWNGRADECARD,10);
		Queue<Card> cards = c1.getCards();
		Card createdCard = cards.poll();
		
		assertNotNull(createdCard);
	}
	

}
