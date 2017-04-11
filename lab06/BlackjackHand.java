package lab06;

public class BlackjackHand extends CardHand {

	@Override
	public int value() {
		int val = 0;
		
		for(Card c : cards) {
			if(c.getValue()==11 && (val+11>21)) {
				val+=1;
			} else {
				val+=c.getValue();
			}
		}
		
		return val;
	}

}
