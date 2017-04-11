package lab06;

public class Card {
	public static enum Suit {
		Hearts, Diamonds, Clubs, Spades;
	};
	
	private Suit suit;
	int value;
	
	public Card(Suit s, int v) {
		if(v<2||v>11) {
			throw new IllegalArgumentException("Card value must be within [2,11]");
		}
		suit = s;
		value = v;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public boolean equals(Object other) {
		if((other instanceof Card)
				&&((Card)other).getValue()==this.getValue()
				&&((Card)other).getSuit()==this.getSuit())
		{
			return true;
		}
		return false;
	}
	
	public String toString() {
		String s = "";
		
		if(value==11) {
			s+="Ace";
		}
		else {
			s+=value;
		}
		
		s+=" of ";
		
		s+=suit.toString();
		
		return s;
	}
	
	@Override
	public int hashCode() {
		return 31*suit.hashCode() + value;
	}
	
	public static void main(String[] args) {
		Card a = new Card(Suit.Spades, 10);
		Card b = new Card(Suit.Hearts, 4);
		Card c = new Card(Suit.Diamonds, 11);
		Card d = new Card(Suit.Clubs, 2);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	}
}
