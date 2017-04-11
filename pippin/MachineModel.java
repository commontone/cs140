package pippin;

import java.util.Observable;
import java.util.Scanner;

public class MachineModel extends Observable {
	public Instruction[] INSTRUCTIONS = new Instruction[0x10];
	private CPU cpu = new CPU();
	private Memory memory = new Memory();
	private boolean withGUI = false;
	private Code code = new Code();
	private boolean running = false;
	
	private class CPU {
		int accum;
		int pc;
		
	}
	
	public MachineModel() {
		this(false);
	}
	
	public MachineModel(boolean gui) {
		withGUI = gui;
		
		/*
		 * 
		 * Bit 0: Parity Bit, whatever it needs to for there to be an even number of 1's
		 * Bit 1: Immediate Bit, if it is 1 then immediate mode is on
		 * Bit 2: Indirect Bit, if it is 1 then indirect mode is on
		 * Bits 3-6: Instruction bits
		 * Bits 7-31: Argument bits
		 * 
		 * Flags Structure:
		 * Flags can be a decimal value of 0, 2, 4, or 6
		 * 0 means Not Immediate and Direct
		 * 2 means Immediate and Direct
		 * 4 means Not Immediate and Indirect
		 * 6 means Immediate and Indirect
		 * 
		 */
		//INSTRUCTION_MAP entry for "NOP"
		INSTRUCTIONS[0] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags != 0) {
				String fString = "(" + (flags%8 > 3?"1":"0") + 
							(flags%4 > 1?"1":"0") + ")";
				throw new IllegalInstructionException(
					"Illegal flags for this instruction: " + fString);
			}
			
			cpu.pc++;
		};
		
		//INSTRUCTION_MAP entry for "LOD"
		INSTRUCTIONS[0x1] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			
			if(flags==0) { //if Direct
				cpu.accum = memory.getData(arg);
			}
			else if(flags==2) { //if Immediate
				cpu.accum = arg;
			}
			else if(flags==4) { //if Indirect
				cpu.accum = memory.getData(memory.getData(arg));
			}
			else if(flags==6) { //if Immediate and Indirect
				throw new IllegalInstructionException();
			}
			
			cpu.pc++;			
		};

		//INSTRUCTION_MAP entry for "STO"
		INSTRUCTIONS[0x2] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			
			if(flags==0) { //if Direct
				memory.setData(arg, cpu.accum);
			}
			else if(flags==4) { //if Indirect
				memory.setData(memory.getData(arg), cpu.accum);
			}
			else {
				throw new IllegalInstructionException();
			}
			
			cpu.pc++;			
		};
		
		//INSTRUCTION_MAP entry for "JUMP"
		INSTRUCTIONS[0x3] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			/*
			if(flags != 0) {
				String fString = "(" + (flags%8 > 3?"1":"0") + 
							(flags%4 > 1?"1":"0") + ")";
				throw new IllegalInstructionException(
					"Illegal flags for this instruction: " + fString);
			}
			*/
			if(flags==0) { //if Direct
				cpu.pc = cpu.pc + arg;
			}
			else if(flags==2) { //if Immediate
				cpu.pc = arg;
			}
			else if(flags==4) { //if Indirect
				cpu.pc = cpu.pc + memory.getData(arg);
			}
			else if(flags==6) { //if Immediate and Indirect
				cpu.pc = memory.getData(arg);
			}
			
			//cpu.pc++;			
		};
		
		//INSTRUCTION_MAP entry for "JMPZ"
		INSTRUCTIONS[0x4] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			/*
			if(flags != 0) {
				String fString = "(" + (flags%8 > 3?"1":"0") + 
							(flags%4 > 1?"1":"0") + ")";
				throw new IllegalInstructionException(
					"Illegal flags for this instruction: " + fString);
			}
			*/
			if(cpu.accum == 0) {
				if(flags==0) { //if Direct
					cpu.pc = cpu.pc + arg;
				}
				else if(flags==2) { //if Immediate
					cpu.pc = arg;
				}
				else if(flags==4) { //if Indirect
					cpu.pc = cpu.pc + memory.getData(arg);
				}
				else if(flags==6) { //if Immediate and Indirect
					cpu.pc = memory.getData(arg);
				}
			}
			else {
				cpu.pc++;
			}
		};
		
		//INSTRUCTION entry for ADD (add)
		INSTRUCTIONS[0x5] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags == 0) { // direct addressing
				cpu.accum += memory.getData(arg);
			} else if(flags == 2) { // immediate addressing
				cpu.accum += arg;
			} else if(flags == 4) { // indirect addressing
				cpu.accum += memory.getData(memory.getData(arg));				
			} else { // here the illegal case is "11"
				String fString = "(" + (flags%8 > 3?"1":"0") 
							+ (flags%4 > 1?"1":"0") + ")";
				throw new IllegalInstructionException(
					"Illegal flags for this instruction: " + fString);
			}
			cpu.pc++;			
		};
		
		//INSTRUCTION entry for SUB (subtract)
		INSTRUCTIONS[0x6] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags == 0) { // direct addressing
				cpu.accum -= memory.getData(arg);
			} else if(flags == 2) { // immediate addressing
				cpu.accum -= arg;
			} else if(flags == 4) { // indirect addressing
				cpu.accum -= memory.getData(memory.getData(arg));				
			} else { // here the illegal case is "11"
				String fString = "(" + (flags%8 > 3?"1":"0") 
							+ (flags%4 > 1?"1":"0") + ")";
				throw new IllegalInstructionException(
					"Illegal flags for this instruction: " + fString);
			}
			cpu.pc++;			
		};
		
		//INSTRUCTION entry for MUL (multiply)
		INSTRUCTIONS[0x7] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags == 0) { // direct addressing
				cpu.accum *= memory.getData(arg);
			} else if(flags == 2) { // immediate addressing
				cpu.accum *= arg;
			} else if(flags == 4) { // indirect addressing
				cpu.accum *= memory.getData(memory.getData(arg));				
			} else { // here the illegal case is "11"
				String fString = "(" + (flags%8 > 3?"1":"0") 
							+ (flags%4 > 1?"1":"0") + ")";
				throw new IllegalInstructionException(
					"Illegal flags for this instruction: " + fString);
			}
			cpu.pc++;			
		};
		
		//INSTRUCTION entry for DIV (divide)
		INSTRUCTIONS[0x8] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags == 0) { // direct addressing
				if(memory.getData(arg)!=0) {
					cpu.accum /= memory.getData(arg);
				}
				else {
					throw new DivideByZeroException();
				}
			} else if(flags == 2) { // immediate addressing
				if(arg!=0) {
					cpu.accum /= arg;
				}
				else {
					throw new DivideByZeroException();
				}
			} else if(flags == 4) { // indirect addressing
				if(memory.getData(memory.getData(arg))!=0) {
					cpu.accum /= memory.getData(memory.getData(arg));
				}
				else {
					throw new DivideByZeroException();
				}
			} else { // here the illegal case is "11"
				String fString = "(" + (flags%8 > 3?"1":"0") 
							+ (flags%4 > 1?"1":"0") + ")";
				throw new IllegalInstructionException(
					"Illegal flags for this instruction: " + fString);
			}
			cpu.pc++;			
		};
		
		//INSTRUCTION_MAP entry for "AND"
		INSTRUCTIONS[0x9] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags==0) { //if Direct
				if(cpu.accum!=0 && memory.getData(arg)!=0) {
					cpu.accum = 1;
				} else {
					cpu.accum = 0;
				}
			}
			else if(flags==2) { //if Immediate
				if(cpu.accum!=0 && arg!=0) {
					cpu.accum = 1;
				} else {
					cpu.accum = 0;
				}
			}
			else {
				throw new IllegalInstructionException("Illegal flags for this instruction");
			}
			
			cpu.pc++;			
		};
		
		//INSTRUCTION_MAP entry for "NOT"
		INSTRUCTIONS[0xA] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags==0) { //if Direct
				if(cpu.accum==0) {
					cpu.accum = 1;
				} else {
					cpu.accum = 0;
				}
			} else {
				throw new IllegalInstructionException("Illegal flags for this instruction");
			}
			
			cpu.pc++;			
		};
		
		//INSTRUCTION_MAP entry for "CMPL"
		INSTRUCTIONS[0xB] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags==0) { //if Direct
				if(memory.getData(arg)<0) {
					cpu.accum = 1;
				}
				else {
					cpu.accum = 0;
				}
			} else {
				throw new IllegalInstructionException("Illegal flags for this instruction");
			}
			
			cpu.pc++;			
		};
		
		//INSTRUCTION_MAP entry for "CMPZ"
		INSTRUCTIONS[0xC] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags==0) { //if Direct
				if(memory.getData(arg) == 0) {
					cpu.accum = 1;
				}
				else {
					cpu.accum = 0;
				}
			} else {
				throw new IllegalInstructionException("Illegal flags for this instruction");
			}
			
			cpu.pc++;			
		};
		
		//INSTRUCTION_MAP entry for "FOR"
		INSTRUCTIONS[0xD] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			int leap = 1;
			if(flags==0) { //if Direct
				int iter = memory.getData(arg)%0x1000;
				int inst = memory.getData(arg)/0x1000;
				int start = cpu.pc;
				for(int i = 0; i < iter; i++) {
					cpu.pc++;
					for(int j = 0; j < inst; j++) {
						step();
						//System.out.println("Inst left: " + (inst-j)+"\nPC: " + cpu.pc);
					}
					cpu.pc = start;
				}
				leap = inst;
				
			} else if(flags==2) { //if Immediate
				int iter = arg%0x1000;
				int inst = arg/0x1000;
				int start = cpu.pc;
				for(int i = 0; i < iter; i++) {
					cpu.pc++;
					for(int j = 0; j < inst; j++) {
						step();
						//System.out.println("Inst left: " + (inst-j)+"\nPC: " + cpu.pc);
					}
					cpu.pc = start;
				}
				leap = inst;
				
			} else {
				throw new IllegalInstructionException("Illegal flags for this instruction");
			}
			
			cpu.pc+=leap;			
		};
		
		//INSTRUCTION_MAP entry for "HALT"
		INSTRUCTIONS[0xF] = (arg, flags) -> {
			flags = flags & 0x6; // remove parity bit that will have been verified
			if(flags==0) { //if Direct
				halt();
			} else {
				throw new IllegalInstructionException("Illegal flags for this instruction");
			}
			
			cpu.pc++;			
		};
		
		
	}
	
	public void setData(int i, int j) {
		memory.setData(i, j);		
	}
	public Instruction get(int i) {
		return INSTRUCTIONS[i];
	}
	public int[] getData() {
		return memory.getData();
	}
	public int getData(int i) {
		return memory.getData(i);
	}
	public int getPC() {
		return cpu.pc;
	}
	public int getAccum() {
		return cpu.accum;
	}
	
	public Code getCode() {
		return code;
	}
	
	public int getChangedIndex() {
		return memory.getChangedIndex();
	}
	
	public void setPC(int i) {
		cpu.pc = i;
	}
	public void setAccum(int i) {
		cpu.accum = i;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void setRunning(boolean bool) {
		running = bool;
	}
	
	public void setCode(int op, int arg) {
		code.setCode(op, arg);
	}
	
	public void clear() {
		memory.clear();
		code.clear();
		cpu.accum = 0;
		cpu.pc = 0;
	}
	
	public void step() {
		try {
			int opPart = code.getOpPart(cpu.pc);
			int arg = code.getArg(cpu.pc);
			Instruction.checkParity(opPart); //Exception?
			INSTRUCTIONS[opPart/8].execute(arg, opPart%8);
		} catch (Exception e) {
			halt();
			throw e;
		}
		
	}
	
	public void halt() {
		if(!withGUI) {
			System.exit(0);
		}
		else {
			running = false;
		}
	}
	
}
