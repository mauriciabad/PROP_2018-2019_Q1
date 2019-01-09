package domain;
import java.util.*;
import java.lang.*;

public class RestBLevelCompact extends RestrictionBinary {

  private int weight = 70;
  private int weightFromSession = -35;

  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    return 80;
  //   int level = s.getSubject().getLevel();
  //   for(Session session : sessions) {
  //     int dist = 0;
  //     if(session.getWeekDay() == d && session.getSubject().getLevel() == level){
  //       dist = Math.abs(session.getHour() - h);
  //       return weight + dist * weightFromSession;
  //     }
  //   }
  //   return weight;
  // }
}
