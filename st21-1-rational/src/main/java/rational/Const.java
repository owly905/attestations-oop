package rational;

public class Const implements Expr {
  public final int value;

  public Const(int v) {
    this.value = v;
  }

  @Override
  public <T> T accept(Visitor<T> visitor) {return visitor.visit(this);}

  @Override
  public String toString() {return String.valueOf(value);}
}
