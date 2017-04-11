package lab08;

public class Student {
	String firstName;
	String lastName;
	int yearEnrolled;
	double GPA;
	int BNumber;
	int numCredits;
	
	public Student(String firstName, String lastName, int yearEnrolled, double gPA, int bNumber, int numCredits) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearEnrolled = yearEnrolled;
		GPA = gPA;
		BNumber = bNumber;
		this.numCredits = numCredits;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getYearEnrolled() {
		return yearEnrolled;
	}

	public double getGPA() {
		return GPA;
	}

	public int getBNumber() {
		return BNumber;
	}

	public int getNumCredits() {
		return numCredits;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(firstName);
		b.append(" ");
		b.append(lastName);
		b.append(" ");
		b.append(yearEnrolled);
		b.append(" ");
		b.append(GPA);
		b.append(" ");
		b.append(BNumber);
		b.append(" ");
		b.append(numCredits);
		b.append(" ");
		
		return b.toString();
	}
	
}


















