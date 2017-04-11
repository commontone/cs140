package lab09;

public class Not extends Expr{
	public Not(Expr ex1) {
		if(value == 0) {
			value = 1;
		} else {
			value = 0;
		}
	}
}


