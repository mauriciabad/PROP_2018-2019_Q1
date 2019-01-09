/** \class RestBLevelOverlapping
    \file RestBLevelOverlapping.java
    \brief Codi de la Class RestBLevelOverlapping
    \author Raul Main
 */
package domain;
import java.util.*;

public class RestBLevelOverlapping extends RestrictionBinary {

  private int weight = 80;

  /** \brief Funcio per evaluar el solapament dels mateixos nivells de una asignatura
   */
  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    return 80;
  //   int level = s.getSubject().getLevel();
  //   for(Session session : sessions) {
  //     if(session.getHour() >= h && session.getHour() < h+s.getDuration() &&session.getWeekDay() == d){
  //       if(level == session.getSubject().getLevel() && s.isOverlapping(session)) return -weight;
  //     }
  //   }
  //   return weight;
  // }
}
