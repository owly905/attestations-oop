package rational;

public class LukasiewiczVisitor implements Visitor<String> {
  @Override
  public String visit(Const e) {
    return String.valueOf(e.value);
  }

  @Override
  public String visit(Plus e) {
    return "+ " + e.op1.accept(this) + " " + e.op2.accept(this);
  }

  @Override
  public String visit(Minus e) {
    return "- " + e.op1.accept(this) + " " + e.op2.accept(this);
  }

  @Override
  public String visit(Mult e) {
    return "* " + e.op1.accept(this) + " " + e.op2.accept(this);
  }

  @Override
  public String visit(Neg e) {
    return "~ " + e.op.accept(this);
  }
}
