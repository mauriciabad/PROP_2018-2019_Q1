package domain;
import java.util.*;
import java.lang.*;

public class RestBNearClassroom extends RestrictionBinary {
  private int weight = 20;
  private int weightFromBuilding = -10;
  private int weightFromFloor = -5;
  private int weightFromRoom = -2;

  /** \brief Funcio per evaluar la distancia de les aules entre asignatures
   */
  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    for(Session session : sessions) {
      if(session.getSubject().getLevel() == s.getSubject().getLevel() &&
      session.getHour()+session.getDuration() == h &&
      session.isSameGroup(s)
      ){
        int roomDiference     = Math.abs(c.getRoom() -session.getClassroom().getRoom());
        int floorDiference    = Math.abs(c.getFloor()-session.getClassroom().getFloor());
        int buildingDiference = Math.abs(session.getClassroom().getBuilding().charAt(0) - c.getBuilding().charAt(0)) + Math.abs(session.getClassroom().getBuilding().charAt(1) - c.getBuilding().charAt(1));
        
        return Math.max(0, weight + weightFromBuilding * roomDiference + weightFromFloor * floorDiference + weightFromRoom * buildingDiference);
      }
    }
    return 0;
  }
}
