package test.datahandlertest;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cards.*;
import datahandler.CardHandler;
import datahandler.Initialiser;

class CardHandlerTester {

	private Initialiser i = Initialiser.getInit();
	@BeforeEach
	void init() {
		@SuppressWarnings("unused")
		Initialiser i = Initialiser.getInit();
	}
	
	@Test
	void testInstanciation() {

		Queue<Card> benefitCards = i.getBenefitCards(1);
		Queue<Card> lossCards = i.getLossCards(1);
		CardHandler tester = new CardHandler(lossCards,benefitCards);
		
		assertNotNull(tester);
	}
	@Test
	void testNullInstantiation() {
		try {
			@SuppressWarnings("unused")
			CardHandler test = new CardHandler(null,null);
		}catch(Exception e) {
			assertEquals("Error, card packs cannot be null",e.getMessage());
		}
	
	}
	@Test
	void testNullAttPack() {
		try {
			@SuppressWarnings("unused")
			CardHandler test = new CardHandler(null,i.getBenefitCards(1));
		}catch(Exception e) {
			assertEquals("Error, card packs cannot be null",e.getMessage());
		}
	
	}
	@Test
	void testNullSecPack() {
		try {
			@SuppressWarnings("unused")
			CardHandler test = new CardHandler(i.getLossCards(1),null);
		}catch(Exception e) {
			assertEquals("Error, card packs cannot be null",e.getMessage());
		}
	
	}
	
	@Test
	void testDrawAttackCard() {
		BonusCard b1 = new BonusCard("VPN", "When a player lands on another player’s tile where they have taken over a server, they will not lose any money or security level", "money",500);
		Queue<Card> testPack = new LinkedList<Card>();
		testPack.add(b1);
		CardHandler test = new CardHandler(i.getLossCards(1),testPack);
		Card returnedCard = test.drawSecCard();
		
		assertEquals(b1.hashCode(),returnedCard.hashCode());
	}
	@Test
	void testDrawSecCard() {
		DamageCard d1 = new DamageCard("Security Breach",
				"You have detected vulnerabilities on another player's system, target opponent player to steal X security level from them","money",500);
		Queue<Card> testPack = new LinkedList<Card>();
		testPack.add(d1);
		CardHandler test = new CardHandler(testPack,i.getBenefitCards(1));
		Card returnedCard = test.drawAttCard();
		
		assertEquals(d1.hashCode(),returnedCard.hashCode());
	}
	@Test
	void testSecurityShuffle() {
		BonusCard b1 = new BonusCard("BonusCard1","desc","money",500);
		BonusCard b2 = new BonusCard("BonusCard2","desc","security",500);
		
		DamageCard d1 = new DamageCard("DamageCard","desc","security",500);
		Queue<Card> testPack = new LinkedList<Card>();
		testPack.add(b1);
		testPack.add(b2);
		
		Queue<Card> testPack2 = new LinkedList<Card>();
		testPack2.add(d1);
		
		CardHandler dealer = new CardHandler(testPack2,testPack);
		
		Card drawnCard = null;
		for(int i = 0; i < testPack.size()+1;i++) {
			drawnCard = dealer.drawSecCard();
		}
		assertEquals(b1.hashCode(),drawnCard.hashCode());
		
	}
	
	@Test
	void testDamageShuffle() {
		BonusCard b1 = new BonusCard("BonusCard1","desc","money",500);
	
		
		DamageCard d1 = new DamageCard("DamageCard","desc","security",500);
		DamageCard d2 = new DamageCard("DamageCard2","desc","security",500);
		Queue<Card> testPack = new LinkedList<Card>();
		testPack.add(b1);

		
		Queue<Card> testPack2 = new LinkedList<Card>();
		testPack2.add(d1);
		testPack2.add(d2);
		CardHandler dealer = new CardHandler(testPack2,testPack);
		
		Card drawnCard = null;
		for(int i = 0; i < testPack2.size()+1;i++) {
			drawnCard = dealer.drawAttCard();
		}
		assertEquals(d1.hashCode(),drawnCard.hashCode());
		
	}
	
	@Test
	void testEmptyAttPack() {
		try {
			@SuppressWarnings("unused")
			CardHandler test = new CardHandler(new LinkedList<Card>(),i.getBenefitCards(1));
		}catch(Exception e) {
			assertEquals("Error, card packs must be greater than length 0",e.getMessage());
		}
	
	}
	@Test
	void testEmptySecPack() {
		try {
			@SuppressWarnings("unused")
			CardHandler test = new CardHandler(i.getLossCards(1),new LinkedList<Card>());
		}catch(Exception e) {
			assertEquals("Error, card packs must be greater than length 0",e.getMessage());
		}
	
	}
	
	@Test
	void bothEmptyPack() {
		try {
			@SuppressWarnings("unused")
			CardHandler test = new CardHandler(new LinkedList<Card>(),new LinkedList<Card>());
		}catch(Exception e) {
			assertEquals("Error, card packs must be greater than length 0",e.getMessage());
		}
	
	}

}
