package domain;
import java.util.*;

abstract public class RestrictionUnary {

  private int weight = 30;

  public RestrictionUnary() {}
  public RestrictionUnary(int weight) {
    this.weight = weight;
  }
  
  abstract public int check(Session s, int h, int d, Classroom c);

  final public String toString(Session s, int h, int d, Classroom c) {
    return this.getClass().getSimpleName() + "(day=" + d + ", hour=" + h + ", session=" + s + ", classroom=" + c.getName() +")";
  }
  
  final public int getWeight() {
    return this.weight;
  }

  final public void setWeight(int weight) {
    this.weight = weight;
  }

}