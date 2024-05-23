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

public class LukasiewiczVisitorTest {

    @Test
    public void lukasiewiczTest() {
        Visitor<String> visitor = new LukasiewiczVisitor();

        Expr e = cons(1, 2);
        assertEquals("1/2", e.accept(visitor));

        e = plus(e, neg(e));
        assertEquals("+ 1/2 ~ 1/2", e.accept(visitor));

        e = plus(e, cons(2));
        assertEquals("+ + 1/2 ~ 1/2 2", e.accept(visitor));

        e = minus(plus(cons(1, 2), cons(1, 2)),
                  cons(-5));
        assertEquals("- + 1/2 1/2 -5", e.accept(visitor));

        e = mult(cons(0), cons(1));
        assertEquals("* 0 1", e.accept(visitor));

        e = mult(cons(5), cons(1));
        assertEquals("* 5 1", e.accept(visitor));

        e = mult(cons(5), var('x'));
        assertEquals("* 5 x", e.accept(visitor));

        e = pow(cons(5), 2);
        assertEquals("^ 5 2", e.accept(new LukasiewiczVisitor()));

        e = pow(plus(cons(3), cons(5)), -2);
        assertEquals("^ + 3 5 -2", e.accept(new LukasiewiczVisitor()));

        e = sqrt(cons(4));
        assertEquals("sqrt 4", e.accept(new LukasiewiczVisitor()));

        e = pow(sqrt(cons(4)), 2);
        assertEquals("^ sqrt 4 2", e.accept(new LukasiewiczVisitor()));

        e = div(cons(1), cons(2));
        assertEquals("/ 1 2", e.accept(new LukasiewiczVisitor()));

        e = div(plus(cons(3), cons(1)), cons(2));
        assertEquals("/ + 3 1 2", e.accept(new LukasiewiczVisitor()));
    }
}
