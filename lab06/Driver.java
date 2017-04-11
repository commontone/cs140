package lab06;

public class Driver {
	public static void main(String[] args) {
		Card a = new Card(Card.Suit.Clubs, 10);
		Card b = new Card(Card.Suit.Clubs, 10);
		Card c = new Card(Card.Suit.Clubs, 11);
		BlackjackMethod method = new BlackjackMethod();
		ComposedHand hand = new ComposedHand(method);
		
		hand.addCard(a);
		hand.addCard(b);
		hand.addCard(c);
		
		int result = hand.value();
		int expected = 21;
		System.out.println("Expected 21. Result: "+result);
		System.out.println("Cards: "+hand.toString());
	}
}
