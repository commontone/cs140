package lab05;

import java.awt.Rectangle;

public class TextBoxes {
	private TextBox[] boxes;
	private int nextIndex;
	
	public TextBoxes(int n) {
		if(n<0) {
			throw new IllegalArgumentException("n cannot be negative");
		}
		boxes = new TextBox[n];
	}

	public TextBoxes(TextBox[] boxes, int nextIndex) {
		if(boxes==null&&nextIndex!=0) {
			throw new IllegalArgumentException("Array cannot be null and nextIndex be non-zero");
		}
		if(nextIndex<0||nextIndex>boxes.length) {
			throw new IllegalArgumentException("nextIndex cannot be negative nor can nextIndex exceed the array length");
		}
		this.boxes = boxes;
		this.nextIndex = nextIndex;
	}
	
	public TextBoxes(TextBox[] boxez) {
		if(boxez==null) {
			throw new IllegalArgumentException("Array cannot be null");
		}
		boxes = boxez;
		nextIndex = boxes.length;
	}
	
	public boolean addTextBox(String s, Rectangle b) {
		if(nextIndex<boxes.length) {
			boxes[nextIndex] = new TextBox(s,b);
			nextIndex++;
			return true;
		}
		return false;
		
	}
	
	/**
	 * finds the rectangle of the TextBox with the longest text
	 * 
	 * example:
	 * if boxes[1].getText()=="hello" and boxes[2].getText()=="gwq"
	 * then the boxes[1].getRect() will be returned because hello is longer than gwq
	 * 
	 * @return returns the rectangle with the longest TextBox text length
	 */
	public Rectangle longestText() {
		if(boxes==null) {
			return null;
		}
		int index = 0;
		for(int i = 0; i < boxes.length; i++) {
			if(boxes[i].getText().length()>boxes[index].getText().length()) {
				index = i;
			}
		}
		
		return boxes[index].getRect();
	}
	
	public String largestTextBoxArea() {
		if(boxes==null) {
			return null;
		}
		int index = 0;
		for(int i = 0; i < nextIndex; i++) {
			if(boxes[i].getArea()>boxes[index].getArea()) {
				index = i;
			}
		}
		
		return boxes[index].getText();
	}
	
}
