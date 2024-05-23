package rational;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static rational.ExprTest.cons;
import static rational.ExprTest.div;
import static rational.ExprTest.minus;
import static rational.ExprTest.mult;
import static rational.ExprTest.neg;
import static rational.ExprTest.plus;
import static rational.ExprTest.pow;
import static rational.ExprTest.sqrt;
import static rational.ExprTest.var;

public class EvalVisitorTest {

    @Test
    public void evalTest() {
        Expr e = cons(1, 2);
        assertEquals(new Rational(1, 2), e.accept(new EvalVisitor(Collections.emptyMap(), Rational.ZERO)));

        e = plus(e, neg(e));
        assertEquals(new Rational(0), e.accept(new EvalVisitor(Collections.emptyMap(), Rational.ZERO)));

        e = plus(e, cons(2));
        assertEquals(new Rational(2), e.accept(new EvalVisitor(Collections.emptyMap(), Rational.ZERO)));

        e = minus(plus(cons(1, 2), cons(1, 2)),
                  cons(-5));
        assertEquals(new Rational(6), e.accept(new EvalVisitor(Collections.emptyMap(), Rational.ZERO)));

        e = mult(cons(0), cons(1));
        assertEquals(new Rational(0), e.accept(new EvalVisitor(Collections.emptyMap(), Rational.ZERO)));

        e = mult(cons(5), cons(1));
        assertEquals(new Rational(5), e.accept(new EvalVisitor(Collections.emptyMap(), Rational.ZERO)));

        e = mult(cons(5), var('x'));
        Map<Var, Rational> vars = new HashMap<>();
        vars.put(var('x'), new Rational(5));
        assertEquals(new Rational(25), e.accept(new EvalVisitor(vars, Rational.ZERO)));

        e = pow(cons(2, 3), 3);
        assertEquals(new Rational(8, 27), e.accept(new EvalVisitor(Collections.emptyMap(), Rational.ZERO)));

        e = pow(var('x'), 3);
        assertEquals(new Rational(125), e.accept(new EvalVisitor(vars, Rational.ZERO)));

        e = pow(var('x'), -2);
        assertEquals(new Rational(1, 25), e.accept(new EvalVisitor(vars, Rational.ZERO)));

        e = pow(plus(pow(var('x'), 2), var('y')), 2);
        vars.put(var('y'), new Rational(3));
        assertEquals(new Rational(784), e.accept(new EvalVisitor(vars, Rational.ZERO)));

        e = div(var('x'), var('y'));
        vars.put(var('x'), new Rational(4));
        vars.put(var('y'), new Rational(2));
        assertEquals(new Rational(2), e.accept(new EvalVisitor(vars, Rational.ZERO)));

        final Rational eps = new Rational(1, 1000);
        final Rational number = new Rational(4);
        vars.put(var('x'), number);
        e = sqrt(var('x'));
        assertTrue(e.accept(new EvalVisitor(vars, eps)).pow(2).div(number).sub(Rational.ONE).abs().compareTo(eps) < 0);
    }
}
