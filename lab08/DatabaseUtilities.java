package lab08;

import java.util.Comparator;

public class DatabaseUtilities {

    /**
     * Determines number of students in db
     * @param db the database to operate on
     * @return number of students in db
     */
    public static long numStudents(StudentDatabase db) {
    	long c = db.stream().count();
    	return c;
    }

    /**
     * Determines maximum GPA in db
     * @param db the database to operate on
     * @return 0 if db is empty, otherwise, maximum GPA over db
     */
    public static double maxGPA(StudentDatabase db) {
    	double d = db.stream().mapToDouble(i -> i.getGPA()).max().orElse(0.0);
    	return d;
    }

    /**
     * Determines average GPA in db
     * @param db the database to operate on
     * @return 0 if db is empty, otherwise, average GPA over db
     */
    public static double averageGPA(StudentDatabase db) {
    	double d = db.stream().mapToDouble(i -> i.getGPA()).average().orElse(0.0);
    	return d;
     }

    /**
     * Determines number of students whose GPA is above threshold
     * @param db the database to operate on
     * @param threshold threshold a GPA must be above
     * @return number of students whose GPA is above threshold
     */
    public static long numGPAAbove(StudentDatabase db, double threshold) {
    	long n = db.stream().mapToDouble(i -> i.getGPA()).filter(i -> i > threshold).count();
        return n;
     }

    /**
     * Sorts db according to comp, then prints the students
     * @param db the database to operate on
     * @param comp the comparator to use to compare two Students
     */
    public static void sortAndPrint(StudentDatabase db, Comparator<Student> comp) {
    	db.stream().sorted(comp).forEach(System.out::println);
    }
}