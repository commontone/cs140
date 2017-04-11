package lab06;

import java.util.ArrayList;

public class BlackjackMethod implements ComputationMethod {
	public int compute(ArrayList<Card> cards) {
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
