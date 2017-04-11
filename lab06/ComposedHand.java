package lab06;

import java.util.ArrayList;

public class ComposedHand {
	ArrayList<Card> cards = new ArrayList<>();
	ComputationMethod method;
	
	public ComposedHand(ComputationMethod m) {
		method = m;
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}
	
	public void clear() {
		cards.clear();
	}
	
	public String toString() {
		return cards.toString();
	}
	
	public int value() {
		return method.compute(cards);
	}
}
