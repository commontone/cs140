package lab05;

import static org.junit.Assert.assertEquals;

import java.awt.Rectangle;

import org.junit.Test;

public class TextBoxTest {
	
	
	
	@Test
	public void test1() {
		TextBoxes tbs = new TextBoxes(5);
		tbs.addTextBox("2426", new Rectangle(5,2));
		tbs.addTextBox("242", new Rectangle(3,1));
		tbs.addTextBox("84974", new Rectangle(53,12));
		tbs.addTextBox("2746584268", new Rectangle(5,3));
		tbs.addTextBox("2", new Rectangle(1,1));
		
		Rectangle expected = new Rectangle(5,3);
		Rectangle result = tbs.longestText();
		assertEquals(expected, result);
		//fail("Not yet implemented");
	}
	
	@Test
	public void test2() {
		TextBoxes tbs = null;
		
		Rectangle expected = new Rectangle(5,3);
		Rectangle result = tbs.longestText();
		assertEquals(expected, result);
		//fail("Not yet implemented");
	}

}
