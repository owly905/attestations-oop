package rational;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static rational.ExprTest.cons;
import static rational.ExprTest.minus;
import static rational.ExprTest.plus;
import static rational.ExprTest.pow;
import static rational.ExprTest.sqrt;
import static rational.ExprTest.var;

public class ExtNewtonTest {

    @Test(timeout = 5_000, expected = IllegalArgumentException.class)
    public void exceptionTest() {
        Expr f = plus(pow(var('x'), 2), cons(1));
        new Newton(f, var('x')).approximate(new Rational(2), new Rational(1, 100));
    }

    @Test(timeout = 10_000, expected = ArithmeticException.class)
    public void negativeSqrtTest() {
        Expr f = sqrt(var('x'));
        new Newton(f, var('x')).approximate(new Rational(2), new Rational(1, 100));
    }

    @Test(timeout = 10_000)
    public void sqrtMinusOneTest() {
        Expr f = minus(sqrt(var('x')), cons(1));
        Rational z = new Newton(f, var('x')).approximate(new Rational(2), new Rational(1, 100));
        assertEquals(new Rational(new BigInteger("3294743945397275582499022113297388077104063"),
                                  new BigInteger("3294851581195505927465902731754075783692288")),
                     z);
    }
}
