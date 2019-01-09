package domain;
import java.util.*;

public class RestUFixSessionClassroom extends RestrictionUnary {
  
  private int weight = 75;
  private Classroom classroom;


  public RestUFixSessionClassroom(Classroom classroom) {
    this.classroom = classroom;
  }

  // TODO: implement close classrooms
  public int check(Session s, int h, int d, Classroom c){
    if (c.getName().equals(classroom.getName())) {
      return weight;
    }else{
      return -weight;
    }
  }
}