package lab08;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StudentDatabase {
	private List<Student> students = new ArrayList<>();
	
	public void add(Student s) {
		students.add(s);
	}
	
	public Stream<Student> stream() {
		return students.stream();
	}
	
	
}
