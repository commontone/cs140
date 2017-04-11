package lab07;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ScratchBuffer extends Buffer {
	public ScratchBuffer() {
		lines = new ArrayList<>();
	}
	
	@Override
	public void save() throws UnsupportedOperationException {
		Path path = Paths.get("scratch.txt");
		byte[] bytes = getText().getBytes();
		try {
			java.nio.file.Files.write(path, bytes);
		} catch (Exception e) {
			System.out.println("Scratch Exception");
			System.out.println(e);
		}
	}
}
