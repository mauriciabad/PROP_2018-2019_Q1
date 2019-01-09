/** \class Schedule
    \file Schedule.java
    \brief Stub de la Class Schedule
    \author Raul Main
 */

package stubs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Schedule{


  // [day][hour]<session> contains the sesions in a timetable
  private List<Session> schedule = new ArrayList<>();


  //Constructor
  public Schedule(){
  }

  public Schedule(Boolean[][] allowedTime) {
    this.allowedTime = allowedTime;
  }

  public List<Session> getSchedule() {
    return schedule;
  }

  /** \brief Class previa a la execucio del algoritme de generacio de horaris
   */
  public boolean generateSchedule(){
    return true;
  }


}
