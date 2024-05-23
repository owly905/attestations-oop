package rational;

public class EvalVisitor implements Visitor<Integer> {
  public Integer visit(Const e) {
    return e.value;
  }

  public Integer visit(Plus e) {
    return e.op1.accept(this) + e.op2.accept(this);
  }

  public Integer visit(Minus e) {
    return e.op1.accept(this) - e.op2.accept(this);
  }

  public Integer visit(Mult e) {
    return e.op1.accept(this) * e.op2.accept(this);
  }

  public Integer visit(Neg e) {
    return -e.op.accept(this);
  }
}
