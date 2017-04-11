package lab07;

import java.util.List;

public class Buffer {
	List<String> lines;
	
	public int getNumlines() {
		return lines.size();
	}
	
	public String getText() {
		return String.join("\n", this.lines);
	}
	
	public void draw() {
		System.out.println(this.getText());
	}
	
	public void save() throws UnsupportedOperationException {
		
	}
	
	public void type(String toType) {
		if(lines.size()==0) {
			lines.add("");
		}
		for(int i = 0; i < toType.length(); i++) {
			if(toType.charAt(i)=='\n') {
				lines.add("");
			} else {
				lines.set(lines.size()-1, lines.get(lines.size()-1) + String.valueOf(toType.charAt(i)));
			}
		}
	}
}
