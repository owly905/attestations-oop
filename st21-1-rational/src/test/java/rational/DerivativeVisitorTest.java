package rational;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static rational.ExprTest.cons;
import static rational.ExprTest.div;
import static rational.ExprTest.minus;
import static rational.ExprTest.mult;
import static rational.ExprTest.neg;
import static rational.ExprTest.plus;
import static rational.ExprTest.pow;
import static rational.ExprTest.sqrt;
import static rational.ExprTest.var;

public class DerivativeVisitorTest {

    private static Expr derive(Expr e, Var v) {
        return e.accept(new DerivativeVisitor(v)).accept(new SimplifyVisitor());
    }

    private final Var vy = var('y');
    private final Const two = cons(2);
    private final Const three = cons(3);
    private final Var vx = var('x');

    @Test
    public void derivativeTest() {
        Expr e = cons(1000);
        Expr f = cons(0);
        assertEquals(f, derive(e, vx));

        e = vx;
        f = cons(1);
        assertEquals(f, derive(e, vx));

        e = mult(two, vx);
        f = two;
        assertEquals(f, derive(e, vx));

        e = plus(three, vx);
        f = cons(1);
        assertEquals(f, derive(e, vx));

        e = plus(vx, three);
        f = cons(1);
        assertEquals(f, derive(e, vx));

        e = plus(mult(two, vx), three);
        f = two;
        assertEquals(f, derive(e, vx));

        e = pow(vx, 2);
        f = mult(two, vx);
        assertEquals(f, derive(e, vx));

        e = pow(mult(vx, vy), 2);
        f = mult(mult(two, mult(vx, vy)), vy);
        assertEquals(f, derive(e, vx));

        e = pow(plus(vx, vy), 2);
        f = mult(two, plus(vx, vy));
        assertEquals(f, derive(e, vx));

        e = minus(vx, cons(5));
        f = cons(1);
        assertEquals(f, derive(e, vx));

        e = minus(pow(vx, 2), vx);
        f = minus(mult(two, vx), cons(1));
        assertEquals(f, derive(e, vx));

        e = neg(vx);
        f = cons(-1);
        assertEquals(f, derive(e, vx));

        e = neg(neg(pow(vx, 2)));
        f = mult(two, vx);
        assertEquals(f, derive(e, vx));

        e = sqrt(vx);
        f = div(cons(1), mult(two, sqrt(vx)));
        assertEquals(f, derive(e, vx));

        e = div(cons(1), pow(vx, 2));
        f = div(neg(mult(two, vx)),
                pow(vx, 4));
        assertEquals(f, derive(e, vx));

        e = pow(vx, -2);
        f = mult(cons(-2), pow(vx, -3));
        assertEquals(f, derive(e, vx));
    }
}
