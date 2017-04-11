package pippin;


public interface Instruction {
	void execute(int arg, int flags);
	
	public static int numOnes(int k) {
		int i = k;
		i = i - ((i >>> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		return (((i + (i >>> 4)) & 0x0F0F0F0F) * 0x01010101) >>> 24;
		/*
		 int b = 0;
		 
		String s = Integer.toUnsignedString(k,2);
		for(int i = 0; i<s.length(); i++) {
			if (s.charAt(i)=='1') {
				b++;
			}
		}
		return b;
		*/
	}
	
	public static void checkParity(int k) {
		if(numOnes(k)%2==1) {
			throw new ParityCheckException("The instruction is corrupted");
		}
	}
}
