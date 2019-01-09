/** \class RestBSameTypeOverlapping
    \file RestBSameTypeOverlapping.java
    \brief Codi de la Class RestBSameTypeOverlapping
    \author Raul Main
 */
package domain;
import java.util.*;

public class RestBSameTypeOverlapping extends RestrictionBinary {

  private int weight = 90;

  /** \brief Funcio per evaluar el solapament de la mateixa asignatura a la mateixa franja horaria
   */
  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    String subjectName = s.getSubject().getName();
    String type = s.getClassType().getType();

    for(Session session : sessions) {
      int duration = session.getClassType().getDuration();
      for (int h2 = 0; h2 < duration; h2++) {
        if(session.getHour()+h2 == h && session.getWeekDay() == d &&
        subjectName.equals(session.getSubject().getName()) && 
        type.equals(session.getClassType().getType()) && 
        session.isOverlapping(h, duration)){
          return -weight;
        }
      }
    }
    return weight;
  }
}
