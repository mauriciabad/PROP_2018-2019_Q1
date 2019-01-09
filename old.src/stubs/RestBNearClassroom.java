/** \class RestBNearClassroom
    \file RestBNearClassroom.java
    \brief Codi de la Class RestBNearClassroom
    \author Raul Main
 */
package domain;
import java.util.*;

public class RestBNearClassroom extends RestrictionBinary {
  private int weight = 10;
  private int weightFromBuilding = 10;
  private int weightFromFloor = 5;
  private int weightFromRoom = 2;

  /** \brief Funcio per evaluar la distancia de les aules entre asignatures
   */
  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    return 80;
//     int result = 100;
//     for(Session session : sessions) {
//       Classroom clasaux = session.getClassroom();
//       if(session.getHour() >= h && session.getHour() < h+s.getDuration() &&session.getWeekDay() == d){
//         if(c.getBuilding().equals(clasaux.getBuilding())){
//           if(c.getFloor() == clasaux.getFloor()){
//             if(c.getRoom() == clasaux.getRoom()){
//               if(weight < result){
//                 result = weight;
//               }
//             }else{
//               if(-weightFromRoom < result){
//                 result = -weightFromRoom;
//               }
//             }
//           }else{
//             int aux = c.getFloor() - clasaux.getFloor();
//             if (aux  < 0) aux = -aux;
//             if(-weightFromFloor * aux < result)
//               result = (-weightFromFloor * aux);
//           }
//         }else{
//           if(-weightFromBuilding < result)
//             return -weightFromBuilding;
//         }
//       }
//     }	
// 	 if(result == 100) return -weight;
//     return result;
//  }
 
}

