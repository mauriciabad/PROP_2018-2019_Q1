package domain;
import java.util.*;

public class RestUClassroomCapacity extends RestrictionUnary {
  
  private int weight = 80;
  private int weightEmptySit = 2;
  private int weightExtraSit = -2;

  public int check(Session s, int h, int d, Classroom c){
    // System.out.println("\ncapacity="+c.getCapacity()+", size="+s.getClassType().getGroupSize()+", empty="+(c.getCapacity() - s.getClassType().getGroupSize()));
    if (s.getClassType().getGroupSize() > c.getCapacity()) {
      return -weight + weightExtraSit * (s.getClassType().getGroupSize() - c.getCapacity());
    }
    return weight + weightEmptySit * (c.getCapacity() - s.getClassType().getGroupSize());
  }
}