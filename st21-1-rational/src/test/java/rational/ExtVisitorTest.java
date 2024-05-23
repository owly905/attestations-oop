package rational;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static rational.ExprTest.cons;
import static rational.ExprTest.neg;
import static rational.ExprTest.pow;
import static rational.ExprTest.var;

public class ExtVisitorTest {

    @Test(expected = UnknownVariableException.class)
    public void evalExceptionTest() {
        Expr e = var('x');
        Map<Var, Rational> vars = new HashMap<>();
        vars.put(var('y'), new Rational(5));
        e.accept(new EvalVisitor(vars, Rational.ZERO));
    }

    @Test
    public void testSimplify() {
        Expr e = neg(pow(cons(4), 1));
        assertEquals(cons(-4), e.accept(new SimplifyVisitor()));
    }
}
