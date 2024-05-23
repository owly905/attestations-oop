package rational;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RationalTest {

    @Test
    public void constructorTest() {
        Rational r1 = new Rational(BigInteger.ONE, BigInteger.TWO);
        assertEquals(BigInteger.ONE, r1.getNum());
        assertEquals(BigInteger.TWO, r1.getDenom());

        Rational r2 = new Rational(1, 2);
        assertEquals(BigInteger.ONE, r2.getNum());
        assertEquals(BigInteger.TWO, r2.getDenom());

        Rational r3 = new Rational(BigInteger.valueOf(-1), BigInteger.valueOf(2));
        assertEquals(BigInteger.valueOf(-1), r3.getNum());
        assertEquals(BigInteger.TWO, r3.getDenom());

        Rational r4 = new Rational(BigInteger.valueOf(1), BigInteger.valueOf(-2));
        assertEquals(BigInteger.valueOf(-1), r4.getNum());
        assertEquals(BigInteger.TWO, r4.getDenom());

        Rational r5 = new Rational(BigInteger.valueOf(-1), BigInteger.valueOf(-2));
        assertEquals(BigInteger.ONE, r5.getNum());
        assertEquals(BigInteger.TWO, r5.getDenom());

        Rational r6 = new Rational(BigInteger.TEN);
        assertEquals(BigInteger.TEN, r6.getNum());
        assertEquals(BigInteger.ONE, r6.getDenom());
    }

    @Test
    public void gcdTest() {
        Rational r1 = new Rational(2, 4);
        assertEquals(BigInteger.ONE, r1.getNum());
        assertEquals(BigInteger.TWO, r1.getDenom());

        Rational r2 = new Rational(-2, 4);
        assertEquals(BigInteger.valueOf(-1), r2.getNum());
        assertEquals(BigInteger.TWO, r2.getDenom());

        Rational r3 = new Rational(2, -4);
        assertEquals(BigInteger.valueOf(-1), r3.getNum());
        assertEquals(BigInteger.TWO, r3.getDenom());

        Rational r4 = new Rational(-2, -4);
        assertEquals(BigInteger.ONE, r4.getNum());
        assertEquals(BigInteger.TWO, r4.getDenom());
    }

    @Test
    public void addTest() {
        Rational r1 = new Rational(1, 2);
        assertEquals(BigInteger.ONE, r1.add(r1).getNum());
        assertEquals(BigInteger.ONE, r1.add(r1).getDenom());

        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        assertEquals(BigInteger.ONE, r2.add(r3).getNum());
        assertEquals(BigInteger.ONE, r2.add(r3).getDenom());

        Rational r4 = new Rational(2, 5);
        assertEquals(BigInteger.valueOf(13), r2.add(r4).getNum());
        assertEquals(BigInteger.valueOf(20), r2.add(r4).getDenom());

        Rational r5 = new Rational(-1, 4);
        assertEquals(BigInteger.ZERO, r2.add(r5).getNum());
        assertEquals(BigInteger.ONE, r2.add(r5).getDenom());

        Rational r6 = new Rational(-5);
        assertEquals(BigInteger.valueOf(-9), r6.add(r1).getNum());
        assertEquals(BigInteger.TWO, r6.add(r1).getDenom());
    }

    @Test
    public void subTest() {
        Rational r1 = new Rational(1, 2);
        assertEquals(BigInteger.ZERO, r1.sub(r1).getNum());
        assertEquals(BigInteger.ONE, r1.sub(r1).getDenom());

        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        assertEquals(BigInteger.valueOf(-1), r2.sub(r3).getNum());
        assertEquals(BigInteger.TWO, r2.sub(r3).getDenom());

        Rational r4 = new Rational(2, 5);
        assertEquals(BigInteger.valueOf(-3), r2.sub(r4).getNum());
        assertEquals(BigInteger.valueOf(20), r2.sub(r4).getDenom());

        Rational r5 = new Rational(-1, 4);
        assertEquals(BigInteger.ONE, r2.sub(r5).getNum());
        assertEquals(BigInteger.TWO, r2.sub(r5).getDenom());

        Rational r6 = new Rational(-5);
        assertEquals(BigInteger.valueOf(-11), r6.sub(r1).getNum());
        assertEquals(BigInteger.TWO, r6.sub(r1).getDenom());
    }

    @Test
    public void multTest() {
        Rational r1 = new Rational(1, 2);
        assertEquals(BigInteger.ONE, r1.mult(r1).getNum());
        assertEquals(BigInteger.valueOf(4), r1.mult(r1).getDenom());

        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        assertEquals(BigInteger.valueOf(3), r2.mult(r3).getNum());
        assertEquals(BigInteger.valueOf(16), r2.mult(r3).getDenom());

        Rational r4 = new Rational(2, 5);
        assertEquals(BigInteger.ONE, r2.mult(r4).getNum());
        assertEquals(BigInteger.TEN, r2.mult(r4).getDenom());

        Rational r5 = new Rational(-1, 4);
        assertEquals(BigInteger.valueOf(-1), r2.mult(r5).getNum());
        assertEquals(BigInteger.valueOf(16), r2.mult(r5).getDenom());

        Rational r6 = new Rational(-5);
        assertEquals(BigInteger.valueOf(-5), r6.mult(r1).getNum());
        assertEquals(BigInteger.TWO, r6.mult(r1).getDenom());
    }

    @Test
    public void divTest() {
        Rational r1 = new Rational(1, 2);
        assertEquals(BigInteger.ONE, r1.div(r1).getNum());
        assertEquals(BigInteger.ONE, r1.div(r1).getDenom());

        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        assertEquals(BigInteger.ONE, r2.div(r3).getNum());
        assertEquals(BigInteger.valueOf(3), r2.div(r3).getDenom());

        Rational r4 = new Rational(2, 5);
        assertEquals(BigInteger.valueOf(5), r2.div(r4).getNum());
        assertEquals(BigInteger.valueOf(8), r2.div(r4).getDenom());

        Rational r5 = new Rational(-1, 4);
        assertEquals(BigInteger.valueOf(-1), r2.div(r5).getNum());
        assertEquals(BigInteger.ONE, r2.div(r5).getDenom());

        Rational r6 = new Rational(-5);
        assertEquals(BigInteger.valueOf(-10), r6.div(r1).getNum());
        assertEquals(BigInteger.ONE, r6.div(r1).getDenom());
    }

    @Test(expected = NullPointerException.class)
    public void compareToNullTest() {
        new Rational(1).compareTo(null);
    }

    @Test
    public void compareToTest() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        Rational r4 = new Rational(2, 5);
        Rational r5 = new Rational(-1, 4);
        Rational r6 = new Rational(-5);
        assertEquals(0, r1.compareTo(r1));
        assertTrue(r1.compareTo(r2) > 0);
        assertTrue(r5.compareTo(r2) < 0);
        assertTrue(r4.compareTo(r6) > 0);
        assertTrue(r3.compareTo(r6) > 0);
    }

    @Test
    public void lessThanTest() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        Rational r4 = new Rational(2, 5);
        Rational r5 = new Rational(-1, 4);
        Rational r6 = new Rational(-5);

        assertFalse(r1.lessThan(r2));
        assertTrue(r2.lessThan(r1));
        assertTrue(r6.lessThan(r5));
        assertFalse(r3.lessThan(r4));
        assertFalse(r1.lessThan(r1));
        assertFalse(r6.lessThan(r6));
    }

    @Test
    public void lessThanOrEqualTest() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        Rational r4 = new Rational(2, 5);
        Rational r5 = new Rational(-1, 4);
        Rational r6 = new Rational(-5);

        assertFalse(r1.lessThanOrEquals(r2));
        assertTrue(r2.lessThanOrEquals(r1));
        assertTrue(r6.lessThanOrEquals(r5));
        assertFalse(r3.lessThanOrEquals(r4));
        assertTrue(r1.lessThanOrEquals(r1));
        assertTrue(r6.lessThanOrEquals(r6));
    }

    @Test
    public void greaterThanTest() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        Rational r4 = new Rational(2, 5);
        Rational r5 = new Rational(-1, 4);
        Rational r6 = new Rational(-5);

        assertTrue(r1.greaterThan(r2));
        assertFalse(r2.greaterThan(r1));
        assertFalse(r6.greaterThan(r5));
        assertTrue(r3.greaterThan(r4));
        assertFalse(r1.greaterThan(r1));
        assertFalse(r6.greaterThan(r6));
    }

    @Test
    public void greaterThanOrEqualTest() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        Rational r4 = new Rational(2, 5);
        Rational r5 = new Rational(-1, 4);
        Rational r6 = new Rational(-5);

        assertTrue(r1.greaterThanOrEquals(r2));
        assertFalse(r2.greaterThanOrEquals(r1));
        assertFalse(r6.greaterThanOrEquals(r5));
        assertTrue(r3.greaterThanOrEquals(r4));
        assertTrue(r1.greaterThanOrEquals(r1));
        assertTrue(r6.greaterThanOrEquals(r6));
    }

    @Test
    public void sqrtTest() {
        Rational r1 = new Rational(1);
        Rational eps1 = new Rational(1, 1_000);
        Rational sqrt = r1.sqrt(eps1);
        assertTrue(r1.sub(sqrt.pow(2)).compareTo(r1.mult(eps1)) < 0);
        Rational r2 = new Rational(9);
        sqrt = r2.sqrt(eps1);
        assertTrue(r2.sub(sqrt.pow(2)).compareTo(r2.mult(eps1)) < 0);
        Rational r3 = new Rational(10_000);
        sqrt = r3.sqrt(eps1);
        assertTrue(r3.sub(sqrt.pow(2)).compareTo(r3.mult(eps1)) < 0);
        Rational r4 = new Rational(1, 100);
        sqrt = r4.sqrt(eps1);
        assertTrue(r4.sub(sqrt.pow(2)).compareTo(r4.mult(eps1)) < 0);
    }

    @Test
    public void powTest() {
        Rational r1 = new Rational(0);
        Rational r2 = new Rational(1, 2);
        Rational r3 = new Rational(2, 5);
        Rational r4 = new Rational(-1, 4);
        Rational r5 = new Rational(3);
        Rational r6 = new Rational(-3);

        assertEquals(Rational.ONE, r1.pow(0));
        assertEquals(Rational.ZERO, r1.pow(1));
        assertEquals(Rational.ZERO, r1.pow(2));

        assertEquals(Rational.ONE, r2.pow(0));
        assertEquals(new Rational(1, 2), r2.pow(1));
        assertEquals(new Rational(1, 4), r2.pow(2));
        assertEquals(new Rational(2), r2.pow(-1));

        assertEquals(Rational.ONE, r3.pow(0));
        assertEquals(new Rational(2, 5), r3.pow(1));
        assertEquals(new Rational(4, 25), r3.pow(2));
        assertEquals(new Rational(5, 2), r3.pow(-1));

        assertEquals(Rational.ONE, r4.pow(0));
        assertEquals(new Rational(-1, 4), r4.pow(1));
        assertEquals(new Rational(1, 16), r4.pow(2));
        assertEquals(new Rational(-4), r4.pow(-1));

        assertEquals(Rational.ONE, r5.pow(0));
        assertEquals(new Rational(3), r5.pow(1));
        assertEquals(new Rational(9), r5.pow(2));
        assertEquals(new Rational(1, 3), r5.pow(-1));

        assertEquals(Rational.ONE, r6.pow(0));
        assertEquals(new Rational(-3), r6.pow(1));
        assertEquals(new Rational(9), r6.pow(2));
        assertEquals(new Rational(-1, 3), r6.pow(-1));
    }

    @Test
    public void toStringTest() {
        Rational r1 = new Rational(1, 2);
        Rational r2 = new Rational(1, 4);
        Rational r3 = new Rational(3, 4);
        Rational r4 = new Rational(2, 5);
        Rational r5 = new Rational(-1, 4);
        Rational r6 = new Rational(-5);

        assertEquals("1/2", r1.toString());
        assertEquals("1/4", r2.toString());
        assertEquals("3/4", r3.toString());
        assertEquals("2/5", r4.toString());
        assertEquals("-1/4", r5.toString());
        assertEquals("-5", r6.toString());
    }

}
