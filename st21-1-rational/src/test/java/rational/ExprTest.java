package rational;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ExprTest {

    public static Plus plus(Expr e1, Expr e2) {
        return new Plus(e1, e2);
    }

    public static Mult mult(Expr e1, Expr e2) {
        return new Mult(e1, e2);
    }

    public static Minus minus(Expr e1, Expr e2) {
        return new Minus(e1, e2);
    }

    public static Div div(Expr e1, Expr e2) {
        return new Div(e1, e2);
    }

    public static Power pow(Expr e, int n) {
        return new Power(e, n);
    }

    public static Neg neg(Expr e) {
        return new Neg(e);
    }

    public static Sqrt sqrt(Expr e) {
        return new Sqrt(e);
    }

    public static Const cons(Rational e) {
        return new Const(e);
    }

    public static Const cons(long n, long d) {
        return new Const(new Rational(n, d));
    }

    public static Const cons(long n) {
        return new Const(new Rational(n));
    }

    public static Var var(char n) {
        return new Var(n);
    }

    @Test
    public void constTest() {
        Expr c = cons(1, 2);
        assertEquals("1/2", c.toString());
        Expr c2 = cons(1, 2);
        assertEquals(c, c2);
        Expr c3 = cons(-1, 2);
        assertNotEquals(c, c3);
    }

    @Test
    public void varTest() {
        Expr v = var('x');
        assertEquals(var('x'), v);
        assertNotEquals(var('X'), v);
        assertEquals("x", v.toString());
    }

    @Test
    public void negTest() {
        Expr c = cons(1, 2);
        Expr v = var('x');
        Expr nC = neg(c);
        Expr nV = neg(v);
        assertEquals("(-" + c + ")", nC.toString());
        assertEquals(neg(cons(1, 2)), nC);
        assertEquals("(-x)", nV.toString());
        assertEquals(neg(var('x')), nV);
    }

    @Test
    public void plusTest() {
        Expr e = plus(cons(0), cons(1, 2));
        assertEquals("(0+1/2)", e.toString());
        assertEquals(plus(cons(0), cons(1, 2)), e);
        assertEquals(plus(cons(1, 2), cons(0)), e);
    }

    @Test
    public void minusTest() {
        Expr m = minus(cons(0), cons(1, 2));
        assertEquals("(0-1/2)", m.toString());
        assertEquals(minus(cons(0), cons(1, 2)), m);
        assertNotEquals(minus(cons(1, 2), cons(0)), m);
    }

    @Test
    public void multTest() {
        Expr m = mult(cons(0), cons(1, 2));
        assertEquals("(0*1/2)", m.toString());
        assertEquals(mult(cons(0), cons(1, 2)), m);
        assertEquals(mult(cons(1, 2), cons(0)), m);
    }

    @Test
    public void divTest() {
        Expr d = div(cons(1), cons(1, 2));
        assertEquals("(1)/(1/2)", d.toString());
        assertEquals(div(cons(1), cons(1, 2)), d);
        assertNotEquals(div(cons(1, 2), cons(1)), d);
    }

    @Test
    public void sqrtTest() {
        Expr s = sqrt(cons(1));
        assertEquals("sqrt(1)", s.toString());
        assertEquals(sqrt(cons(1)), s);
        Expr s2 = sqrt((pow(var('x'), 2)));
        assertEquals("sqrt((x)^2)", s2.toString());
        assertEquals(sqrt((pow(var('x'), 2))), s2);
    }

    @Test
    public void powerTest() {
        Expr p = pow(cons(1, 2), 2);
        assertEquals("(1/2)^2", p.toString());
        assertEquals(pow(cons(1, 2), 2), p);
    }
}