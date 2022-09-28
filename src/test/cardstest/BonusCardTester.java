package test.cardstest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cards.BonusCard;

class BonusCardTester {

	@Test
	void testVPN() {
		BonusCard c1 = new BonusCard("VPN",
				"When a player lands on another player’s tile where they have taken over a server, they will not lose any money or security level", "money", 500);

		assertNotNull(c1);
	}

	@Test
	void testDataLeak() {
		BonusCard c2 = new BonusCard("Data Leak",
				"Unfortunately, some source code from your security software has been leaked, which has increased a random business' security level by X", "security", 500);

		assertNotNull(c2);
	}

	@Test
	void testCryptocurrencySurge() {
		BonusCard c3 = new BonusCard("Cryptocurrency Surge",
				"There has been an increase in value of multiple crypto currencies that your business has invested in, money increased by X", "money", 500);

		assertNotNull(c3);
	}
	
	@Test
	void testprintMessage() {
		BonusCard c4 = new BonusCard(null, null, null, 0);
		c4.printMessage();

		assertNull(c4);
	}

}
