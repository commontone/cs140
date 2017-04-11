package lab08;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class Tester {
	StudentDatabase db = new StudentDatabase();;
	
	@Before
	public void setup() {
		Student s1 = new Student("Robert", "Burton", 2015, 3.5, 589937, 22);
		Student s2 = new Student("Cynthia", "Hacker", 2012, 3.2, 865385, 74);
		Student s3 = new Student("John", "Mason", 2014, 3.7, 978367, 62);
		Student s4 = new Student("Amanda", "Binds", 2010, 2.6, 156527, 97);
		db.add(s1);
		db.add(s2);
		db.add(s3);
		db.add(s4);
	}
	
	@Test
	public void test1() {
		long result = DatabaseUtilities.numStudents(db);
		long expected = 4L;
		assertEquals(expected, result);
	}
	
	@Test
	public void test2() {
		double result = DatabaseUtilities.maxGPA(db);
		double expected = 3.7;
		assertEquals(expected, result, 0.001);
	}
	
	@Test
	public void test3() {
		double result = DatabaseUtilities.averageGPA(db);
		double expected = 3.25;
		assertEquals(expected, result, 0.001);
	}
	
	@Test
	public void test4() {
		long result = DatabaseUtilities.numGPAAbove(db, 3.0);
		long expected = 3;
		assertEquals(expected, result);
	}
	
	
	
}
