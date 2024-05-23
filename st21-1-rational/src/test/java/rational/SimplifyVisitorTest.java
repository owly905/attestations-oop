package rational;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

public class SimplifyVisitorTest {

    @Test
    public void simplifyTest() {
        final Const zero = cons(0);
        final Const einHalb = cons(1, 2);
        final Const eins = cons(1);
        final Var vx = var('x');

        Visitor<Expr> visitor = new SimplifyVisitor();
        assertEquals(zero, zero.accept(visitor));
        assertEquals(einHalb, einHalb.accept(visitor));

        Expr e = plus(zero, zero);
        assertEquals(zero, e.accept(visitor));

        e = plus(zero, einHalb);
        assertEquals(einHalb, e.accept(visitor));

        e = plus(einHalb, zero);
        assertEquals(einHalb, e.accept(visitor));

        e = plus(zero, mult(cons(2), cons(2)));
        Expr r = e.accept(visitor);
        assertTrue(r instanceof Mult);
        assertEquals(cons(2), ((Mult) r).op1);
        assertEquals(cons(2), ((Mult) r).op2);

        e = minus(einHalb, zero);
        assertEquals(einHalb, e.accept(visitor));

        e = minus(zero, einHalb);
        r = e.accept(visitor);
        assertTrue(r instanceof Neg);
        assertEquals(einHalb, ((Neg) r).op);

        e = minus(cons(1), cons(2));
        r = e.accept(visitor);
        assertTrue(r instanceof Minus);
        assertEquals(cons(1), ((Minus) r).op1);
        assertEquals(cons(2), ((Minus) r).op2);

        e = minus(cons(1), neg(cons(2)));
        r = e.accept(visitor);
        assertTrue(r instanceof Minus);
        assertEquals(cons(1), ((Minus) r).op1);
        assertEquals(cons(-2), ((Minus) r).op2);

        e = minus(cons(1), neg(vx));
        r = e.accept(visitor);
        assertTrue(r instanceof Plus);
        assertEquals(cons(1), ((Plus) r).op1);
        assertEquals(vx, ((Plus) r).op2);

        e = mult(zero, plus(einHalb, einHalb));
        assertEquals(zero, e.accept(visitor));

        e = mult(plus(einHalb, einHalb), zero);
        assertEquals(zero, e.accept(visitor));

        e = mult(eins, einHalb);
        assertEquals(einHalb, e.accept(visitor));

        e = mult(einHalb, eins);
        assertEquals(einHalb, e.accept(visitor));

        e = mult(eins, plus(zero, mult(plus(vx, zero), eins)));
        assertEquals(vx, e.accept(visitor));

        e = neg(neg(eins));
        assertEquals(eins, e.accept(visitor));

        e = neg(eins);
        assertEquals(cons(-1), e.accept(visitor));

        e = pow(vx, 0);
        assertEquals(eins, e.accept(visitor));

        e = pow(vx, 1);
        assertEquals(vx, e.accept(visitor));

        e = pow(new Const(Rational.ZERO), 5);
        assertEquals(new Const(Rational.ZERO), e.accept(visitor));

        e = pow(cons(1), 5);
        assertEquals(cons(1), e.accept(visitor));

        e = pow(mult(new Const(Rational.ZERO), vx), 5);
        assertEquals(new Const(Rational.ZERO), e.accept(visitor));

        e = pow(cons(1), 5);
        assertEquals(cons(1), e.accept(visitor));

        e = pow(vx, 5);
        r = e.accept(visitor);
        assertTrue(r instanceof Power);
        assertEquals(vx, ((Power) r).base);
        assertEquals(5, ((Power) r).exponent);

        e = pow(sqrt(vx), 2);
        assertEquals(pow(sqrt(vx),2), e.accept(new SimplifyVisitor()));

        e = pow(sqrt(vx), 3);
        assertNotEquals(vx, e.accept(new SimplifyVisitor()));
        assertEquals(pow(sqrt(vx), 3), e.accept(new SimplifyVisitor()));

        e = pow(pow(pow(vx, 2), 2), 2);
        assertEquals(pow(vx, 8), e.accept(new SimplifyVisitor()));

        e = sqrt(new Const(Rational.ZERO));
        assertEquals(new Const(Rational.ZERO), e.accept(visitor));

        e = sqrt(cons(1));
        assertEquals(cons(1), e.accept(visitor));

        e = sqrt(cons(4));
        r = e.accept(visitor);
        assertTrue(r instanceof Sqrt);
        assertEquals(cons(4), ((Sqrt) r).op);

        e = sqrt(pow(vx, 2));
        assertEquals(sqrt(pow(vx, 2)), e.accept(new SimplifyVisitor()));

        e = sqrt(pow(vx, 3));
        assertNotEquals(vx, e.accept(new SimplifyVisitor()));
        assertEquals(sqrt(pow(vx, 3)), e.accept(new SimplifyVisitor()));

        e = div(vx, cons(1));
        assertEquals(vx, e.accept(visitor));

        e = div(new Const(Rational.ZERO), vx);
        assertEquals(new Const(Rational.ZERO), e.accept(visitor));

        e = div(vx, var('y'));
        r = e.accept(visitor);
        assertTrue(r instanceof Div);
        assertEquals(vx, ((Div) e).op1);
        assertEquals(var('y'), ((Div) e).op2);
    }
}