package lab09;

public class Add extends Expr {
	
	public Add(Expr ex1, Expr ex2) {
		value = ex1.value + ex2.value;
	}
	
}
