package rational;

public class Test {
  public static void main(String[] args) {
    Expr e = new Plus(new Neg(new Minus(new Const(1), new Const(2))), new Const(12));
    System.out.println(e + " = " + e.accept(new EvalVisitor()));
    System.out.println(e + " = " + e.accept(new LukasiewiczVisitor()));
  }
}
