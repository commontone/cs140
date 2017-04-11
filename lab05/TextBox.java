package lab05;

import java.awt.Rectangle;

public class TextBox {
	private String text;
	private Rectangle box;
	
	public TextBox(String t, Rectangle b) {
		text = t;
		box = b;
	}

	public double getWidth() {
		return box.getWidth();
	}
	
	public double getHeight() {
		return box.getHeight();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public int getArea() {
		return box.width*box.height;
	}
	
	public Rectangle getRect() {
		return box;
	}
	
	

}
