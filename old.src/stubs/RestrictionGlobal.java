/** \class RestrictionGlobal
    \file RestrictionGlobal.java
    \brief Codi de la Class RestrictionGlobal
    \author Raul Main
 */

package domain;
import java.util.*;

abstract public class RestrictionGlobal {

  private int weight = 0;

  public RestrictionGlobal() {}
  public RestrictionGlobal(int weight) {
    this.weight = weight;
  }
  
  abstract public int check(List<Session> sessions, Session s, int h, int d, Classroom c);
  
  final public String toString(Session s, int h, int d, Classroom c) {
    return this.getClass().getSimpleName() + "(day=" + d + ", hour=" + h + ", session=" + s + ", classroom=" + "A6101)";
  }
  
  final public int getWeight() {
    return this.weight;
  }

  final public void setWeight(int weight) {
    this.weight = weight;
  }

}