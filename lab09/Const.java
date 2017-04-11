package lab09;

public class Const extends Expr {
	
	public Const(int a) {
		value = a;
	}
	
	public int eval() {
		return value;
	}
}
