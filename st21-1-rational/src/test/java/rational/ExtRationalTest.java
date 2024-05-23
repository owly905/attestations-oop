package rational;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExtRationalTest {

    @Test
    public void longParameterTest() {
        long l1 = 10L;
        long l2 = 1L;
        assertEquals(new Rational(11), new Rational(l1).add(l2));
        assertEquals(new Rational(9), new Rational(l1).sub(l2));
        assertEquals(new Rational(l1), new Rational(l2).mult(l1));
        assertEquals(new Rational(1, 10), new Rational(l2).div(l1));
        assertTrue(new Rational(l1).greaterThan(l2));
        assertFalse(new Rational(l2).greaterThan(l1));
        assertTrue(new Rational(l1).greaterThanOrEquals(l1));
        assertFalse(new Rational(l1).greaterThan(l1));
        assertTrue(new Rational(l1).lessThanOrEquals(l1));
        assertFalse(new Rational(l1).lessThan(l1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void divisionByZero() { new Rational(1).div(Rational.ZERO); }

    @Test(expected = ArithmeticException.class)
    public void sqrtNegativeTest() {
        new Rational(-1).sqrt(Rational.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sqrtNegativeEpsTest() {
        Rational.ONE.sqrt(new Rational(-1));
    }

    @Test
    public void hashCodeTest() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 2);
        assertEquals(r1.hashCode(), r1.hashCode());
        assertEquals(r1.hashCode(), r2.hashCode());
    }
}
