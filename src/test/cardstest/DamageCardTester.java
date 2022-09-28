package test.cardstest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import cards.DamageCard
;
public class DamageCardTester {
	
	@Test
	void testSecurityBreach() {
		DamageCard c1 = new DamageCard("Security Breach",
				"You have detected vulnerabilities on another player's system, target opponent player to steal X security level from them","security",500);

		assertNotNull(c1);
	}
	
	@Test
	void testprintMessage() {
		DamageCard c4 = new DamageCard(null, null, null, 0);
		c4.printMessage();

		assertNull(c4);
	}

}
