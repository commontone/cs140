package lab07;

public class StatusBar extends Buffer {
	private Buffer attachedTo;
	public StatusBar(Buffer b) {
		attachedTo = b;
	}
	
	@Override
	public String getText() {
		return ">>> lines: " + attachedTo.getNumlines(); 
	}

}
