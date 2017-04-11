package lab09;

public class Mul extends Expr {
	
	public Mul(Expr ex1, Expr ex2) {
		value = ex1.value * ex2.value;
	}
	
}
