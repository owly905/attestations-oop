package rational;

public interface Visitor<T> {
  T visit(Const e);

  T visit(Plus e);

  T visit(Minus e);

  T visit(Mult e);

  T visit(Neg e);
}
