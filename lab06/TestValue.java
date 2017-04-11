package lab06;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestValue {
	
	@Test
	public void test1() {
		Card a = new Card(Card.Suit.Clubs, 2);
		Card b = new Card(Card.Suit.Clubs, 4);
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(a);
		hand.addCard(b);
		
		int result = hand.value();
		int expected = 6;
		assertEquals(expected, result);
	}
	
	@Test
	public void test2() {
		Card a = new Card(Card.Suit.Clubs, 11);
		Card b = new Card(Card.Suit.Clubs, 10);
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(a);
		hand.addCard(b);
		
		int result = hand.value();
		int expected = 21;
		assertEquals(expected, result);
	}
	@Test
	public void test3() {
		Card a = new Card(Card.Suit.Clubs, 11);
		Card b = new Card(Card.Suit.Clubs, 11);
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(a);
		hand.addCard(b);
		
		int result = hand.value();
		int expected = 12;
		assertEquals(expected, result);
	}
	@Test
	public void test4() {
		Card a = new Card(Card.Suit.Clubs, 11);
		Card b = new Card(Card.Suit.Clubs, 11);
		Card c = new Card(Card.Suit.Clubs, 11);
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(a);
		hand.addCard(b);
		hand.addCard(c);
		
		int result = hand.value();
		int expected = 13;
		assertEquals(expected, result);
	}
	@Test
	public void test5() {
		Card a = new Card(Card.Suit.Clubs, 10);
		Card b = new Card(Card.Suit.Clubs, 10);
		Card c = new Card(Card.Suit.Clubs, 2);
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(a);
		hand.addCard(b);
		hand.addCard(c);
		
		int result = hand.value();
		int expected = 22;
		assertEquals(expected, result);
	}
	@Test
	public void test6() {
		Card a = new Card(Card.Suit.Clubs, 10);
		Card b = new Card(Card.Suit.Clubs, 10);
		Card c = new Card(Card.Suit.Clubs, 11);
		BlackjackHand hand = new BlackjackHand();
		
		hand.addCard(a);
		hand.addCard(b);
		hand.addCard(c);
		
		int result = hand.value();
		int expected = 21;
		assertEquals(expected, result);
	}
}
