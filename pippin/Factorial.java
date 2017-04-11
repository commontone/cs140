package pippin;

public class Factorial {
	public static void main(String[] args) {
		MachineModel test = new MachineModel();
		
		test.setData(0, 8);
		test.setRunning(true);
		//[instruction number]*8 + [parity bit]
		int op = 1*8 + 1;
		int arg = 0;
		test.setCode(op, arg);
		
		op = 2*8 + 1;
		arg = 1;
		test.setCode(op, arg);

		op = 1*8 + 1;
		arg = 0;
		test.setCode(op, arg);

		op = 6*8 + 1 + 2;
		arg = 1;
		test.setCode(op, arg);

		op = 2*8 + 1;
		arg = 0;
		test.setCode(op, arg);

		op = 12*8 + 0;
		arg = 0;
		test.setCode(op, arg);

		op = 6*8 + 1 + 2;
		arg = 1;
		test.setCode(op, arg);

		op = 4*8 + 1;
		arg = 4;
		test.setCode(op, arg);

		op = 1*8 + 1;
		arg = 0;
		test.setCode(op, arg);

		op = 7*8 + 1;
		arg = 1;
		test.setCode(op, arg);

		op = 3*8 + 1 + 2;
		arg = 1;
		test.setCode(op, arg);

		op = 15*8 + 0;
		arg = 0;
		test.setCode(op, arg);
		
		
		test.setRunning(true);
		int result = 0;
		while(test.isRunning()) {
			if(result != test.getData(1)){
				result = test.getData(1);
				System.out.println("0 => " + test.getData(0) + 
						"; 1 => " + result);
			}
			test.step();
		}
	}
}
