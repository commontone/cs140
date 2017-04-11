package lab07;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileBuffer extends Buffer {
	private String filename;
	
	public FileBuffer(String n) {
		filename = n;
		Path path = Paths.get(filename);
		//path.get(filename);
		try {
			lines = java.nio.file.Files.readAllLines(path);
		} catch (Exception e) {
			System.out.println(e);
			lines = new ArrayList<>();
		}
	}
	
	@Override
	public void save() throws UnsupportedOperationException {
		Path path = Paths.get(filename);
		byte[] bytes = getText().getBytes();
		try {
			java.nio.file.Files.write(path, bytes);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}
