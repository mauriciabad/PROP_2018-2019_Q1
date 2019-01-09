/** \class Schedule
    \file Schedule.java
    \brief Codi de la Class Schedule
    \author Raul Main
 */

package domain;
import java.util.*;

import domain.controllers.CtrlDomain;


public class Schedule{

  private String name;

  private List<Session> schedule = new ArrayList<>();

  private String customRestrictions = "{}";

  //Constructor

  /** \brief Constructora de la classe schedule
   */
  public Schedule(String name){
    this.name = name;
  }

  /** \brief Generar un horari valid per mitja de backtracking
   */
  private boolean algorithm(Stack<Session> sessions){
    if(sessions.empty()) return true;
    else{
      Session s = sessions.pop();
      
      Set<ClassroomDayHour> removedCDH = s.removeUnsatisfactoryCDH(this.schedule);
      Set<ClassroomDayHour> allowedClassroomDayHour = s.getAllowedClassroomDayHour();

      for(ClassroomDayHour cdh : allowedClassroomDayHour) {
        s.setClassroomDayHour(cdh);
        this.schedule.add(s);
        if (algorithm(sessions) == true) return true;
        else{
          this.schedule.remove(s);
          s.setClassroomDayHour(null);
        }
      }

      s.addClassroomDayHour(removedCDH);
      sessions.push(s);
      return false;
    }
  }

  /** \brief Borra l'horari guardat i el torna a generar a partir de les sessions d'entrada
   */
  public boolean generateSchedule(Stack<Session> sessions){
    schedule.clear();
    return algorithm(sessions);
  }

  // Getters && Setters
  /** \brief Setter per el atribut name de schedule
  */
  public void setName(String name){
    this.name = name;
  }
  /** \brief Getter per el atribut name de schedule
  */
  public String getName(){
    return name;
  }

  /** \brief Getter per el atribut schedule
   */
  public List<Session> getSchedule() {
    return schedule;
  }

  public String getCustomRestrictions() {
    return customRestrictions;
  }

  public void setCustomRestrictions(String customRestrictions) {
    this.customRestrictions = customRestrictions;
  }

}
