/** \class RestrictionBinary
    \file RestrictionBinary.java
    \brief Codi de la Class RestrictionBinary
    \author Raul Main
 */

package domain;
import java.util.*;

abstract public class RestrictionBinary {

  private int weight = 20;

  /** \brief Constructora per defecte de la classe schedule
   */
  public RestrictionBinary() {}

  /** \brief Constructora alternativa de la classe schedule
   */
  public RestrictionBinary(int weight) {
    this.weight = weight;
  }

  abstract public int check(List<Session> sessions, Session s, int h, int d, Classroom c);

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
