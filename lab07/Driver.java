package lab07;

public class Driver {
	public static void main(String[] args) {
		Buffer buffer;
		
		if(args.length==0) {
			buffer = new ScratchBuffer();
		}
		else {
			buffer = new FileBuffer(args[0]);
		}
		System.out.println(">>> Original Text:");
		buffer.draw();
		buffer.type("\nTest One");
		buffer.save();
		System.out.println(">>> Saved. The saved text is now:");
		buffer.draw();
		
	}
}
