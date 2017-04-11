package pippin;

public class Test {
	public static void main(String[] args) {
		int a = 1014313;
		int b = 1111;
		int c = 4624;
		
		System.out.println(a + "\nones: " + Instruction.numOnes(a) + "\ncheck: ");
		Instruction.checkParity(a);
		System.out.println(b + "\nones: " + Instruction.numOnes(b) + "\ncheck: ");
		Instruction.checkParity(b);
		System.out.println(c + "\nones: " + Instruction.numOnes(c) + "\ncheck: ");
		Instruction.checkParity(c);
	}
}
