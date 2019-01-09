/** \class RestGWeekPayload
    \file RestGWeekPayload.java
    \brief Codi de la Class RestGWeekPayload
    \author Raul Main
 */

package domain;
import java.util.*;
import java.lang.*;

public class RestGWeekPayload extends RestrictionGlobal {
  private int weight = 30;
  private int weightExtraHour = 10;
  private int optimal = 4;
  private int thresholdDeviation = 4;

  public RestGWeekPayload() {}
  public RestGWeekPayload(int optimal, int thresholdDeviation) {
    this.optimal = optimal;
    this.thresholdDeviation = thresholdDeviation;
  }

  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    return 80;
//     int payload = 0;
//     for(Session session : sessions) {
//       if(session.getWeekDay() == d) payload++;
//     }
//     if(payload <= optimal) return weight;
//     if (payload > (optimal+thresholdDeviation)) {
//       return weight -weightExtraHour * (payload-optimal);
//     }else{
//       return -weight;
//     }
//  }
}
