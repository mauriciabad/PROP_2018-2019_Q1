/** \class RestBCoRequisit
    \file RestBCoRequisit.java
    \brief Codi de la Class RestBCoRequisit
    \author Raul Main
 */

package domain;
import java.util.*;

public class RestBCoRequisit extends RestrictionBinary {

  private int weight = 30;

  /** \brief Funcio per evaluar el solapament entre corequisits i precorequisits
   */
  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    List<String> coRequisits    = s.getSubject().getCoRequisits();
    List<String> preCoRequisits = s.getSubject().getPreCoRequisits();
    int overlaps = 0;

    for(Session session : sessions) {
      String subjectName = session.getSubject().getName();
      if(session.getHour() >= h && session.getHour() < h+s.getDuration() &&session.getWeekDay() == d){
        if (s.isOverlapping(session) && (coRequisits.contains(subjectName) || preCoRequisits.contains(subjectName))){
          ++overlaps;
        }
      }
    }

    if (overlaps > 0) return -weight * overlaps;
    else return weight;
  }
}
