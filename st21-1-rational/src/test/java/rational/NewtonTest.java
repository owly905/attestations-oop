package rational;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;
import static rational.ExprTest.cons;
import static rational.ExprTest.minus;
import static rational.ExprTest.mult;
import static rational.ExprTest.plus;
import static rational.ExprTest.pow;
import static rational.ExprTest.var;

public class NewtonTest {

    private final Rational EPS = new Rational(1, 1000);

    @Test
    public void newton1Test() {
        final Rational zero = new Rational(-1);

        Expr f = plus(var('a'), cons(1));
        Newton newton = new Newton(f, var('a'));

        Rational tmp = newton.approximate(new Rational(-2), EPS);
        assertTrue(tmp.sub(zero).abs().compareTo(EPS) < 0);
        tmp = newton.approximate(new Rational(2), EPS);
        assertTrue(tmp.sub(zero).abs().compareTo(EPS) < 0);
    }

    @Test
    public void newton2Test() {
        final Rational zero1 = new Rational(-1);
        final Rational zero2 = new Rational(1);

        Expr f = minus(pow(var('x'), 2), cons(1));
        Newton newton = new Newton(f, var('x'));

        Rational tmp = newton.approximate(new Rational(-2), EPS);
        assertTrue(tmp.sub(zero1).abs().compareTo(EPS) < 0);
        tmp = newton.approximate(new Rational(2), EPS);
        assertTrue(tmp.sub(zero2).abs().compareTo(EPS) < 0);
    }

    @Test
    public void newton3Test() {
        final Rational zero1 = new Rational(new BigInteger("-599230778770914461"), new BigInteger("202048704613095120"));
        final Rational zero2 = new Rational(new BigInteger("-27193830655"), new BigInteger("25387040763"));
        final Rational zero3 = new Rational(new BigInteger("3959849087592484746446510643973785483926598717261045029"),
                                                  new BigInteger("5031934468141107584610511879167566954859722211819014085"));

        Expr f = minus(
                plus(pow(var('x'), 3),
                     mult(cons(13, 4),
                          pow(var('x'), 2))),
                cons(5, 2));
        Newton newton = new Newton(f, var('x'));

        Rational tmp = newton.approximate(new Rational(-3), EPS);
        assertTrue(tmp.sub(zero1).abs().compareTo(EPS) < 0);
        tmp = newton.approximate(new Rational(-1), EPS);
        assertTrue(tmp.sub(zero2).abs().compareTo(EPS) < 0);
        tmp = newton.approximate(new Rational(1), EPS);
        assertTrue(tmp.sub(zero3).abs().compareTo(EPS) < 0);
    }
}
