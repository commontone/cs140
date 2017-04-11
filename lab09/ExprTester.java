package lab09;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class ExprTester {
    @Test
    public void simpleAdd() {
        assertEquals(10,
                 new Add(new Const(5), new Const(5)).eval());
    }

    @Test
    public void complexAdd() {
        assertEquals(26,
                 new Add(new Add(new Const(1),
                         new Add(new Const(10), new Const(1))),
                     new Add(new Add(new Add(new Const(3), new Const(3)),
                             new Const(7)),
                         new Const(1))).eval());
    }

    @Test
    public void complexMixed() {
        Expr a = new Add(new Add(new Const(1),
                     new Add(new Const(10), new Const(1))),
                 new Add(new Add(new Add(new Const(3), new Const(3)),
                         new Const(7)),
                     new Const(1)));
        Expr b = new Mul(new Add(new Const(2),
                     new Add(new Const(3), new Mul(new Const(2), new Const(3)))),
                 new Mul(new Not(new Const(0)), new Const(10)));
        Expr c = new Mul(new Not(new Not(new Const(1))), new Const(10));
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        assertEquals(146,
                 new Add(new Add(a, b), c).eval());
    }
}