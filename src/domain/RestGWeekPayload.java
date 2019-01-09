package domain;

import java.util.*;
import java.lang.*;

public class RestGWeekPayload extends RestrictionGlobal {
  private int weight = 40;
  private int weightExtraHour = -10;
  private int optimal = 4;
  private int thresholdDeviation = 4;

  public RestGWeekPayload() {}
  public RestGWeekPayload(int optimal, int thresholdDeviation) {
    this.optimal = optimal;
    this.thresholdDeviation = thresholdDeviation;
  }

  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    if(s.getSubject().getLevel() > 0){
      int payload = 0;
      for(Session session : sessions) {
        if(session.getWeekDay() == d && 
        session.getSubject().getLevel() == s.getSubject().getLevel() &&
        session.isSameGroup(s)
        ){
          ++payload;
        }
      }

      int deviation = Math.abs(payload-optimal);
      if(deviation<=thresholdDeviation){
        return Math.max(0, weight + weightExtraHour * deviation);
      }else{
        return -weight + weightExtraHour * deviation;
      }
    }else{
      return weight;
    }
 }

}
