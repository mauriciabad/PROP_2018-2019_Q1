package domain;
import java.util.*;
import java.lang.*;

public class RestUFixSessionTime extends RestrictionUnary {

  private int weight = 100;
  private int weightHoursFromFixed = 150;
  private int weightWrongDay = 500;
  private int hour;
  private int weekDay;

  public RestUFixSessionTime(int hour, int weekDay) {
    this.hour    = hour;
    this.weekDay = weekDay;
  }
  
  public int check(Session s, int h, int d, Classroom c){
    return 80;
    // int hoursFromFixed = Math.abs(h - hour);

    // if (d != weekDay) {
    //   if (hoursFromFixed > 0) {
    //     return -weight + weightWrongDay + weightHoursFromFixed * hoursFromFixed;
    //   }else{
    //     return -weight + weightWrongDay;
    //   }
    // }else{
    //   if (hoursFromFixed > 0) {
    //     return -weight + weightHoursFromFixed * hoursFromFixed;
    //   }else{
    //     return weight;
    //   }
    // }
  }
}