package domain;

public class RestUAllowedTime extends RestrictionUnary {

  private int weight = 90;
  private int weightHoursFromAllowed = -50;
  private Boolean[][] allowedTime = new Boolean[5][24];

  public RestUAllowedTime() {
    for (int d = 0; d < allowedTime.length; d++) {
      for (int h = 0; h < allowedTime[d].length; h++) {
        allowedTime[d][h] = true;
      }
    }
  }

  // allows everydar from srart to end
  public RestUAllowedTime(int start, int end) {
    for (int d = 0; d < allowedTime.length; d++) {
      for (int h = 0; h < allowedTime[d].length; h++) {
        allowedTime[d][h] = h>=start && h<end;
      }
    }
  }

  public RestUAllowedTime(Boolean[][] allowedTime) {
    this.allowedTime = allowedTime;
  }
  
  public int check(Session s, int h, int d, Classroom c){
    for (int part = 0; part < s.getDuration() && h+part < allowedTime[d].length; part++) {
      if (!allowedTime[d][h+part]) return -weight;
    }
    return weight;
  }
  
  // old version that calculated distance from nearest allowed hour
  // public int check(Session s, int h, int d, Classroom c){
  //   int totalHoursFromAllowed = 0;
  //   for (int i = 0; i < s.getDuration(); i++) {
      
  //     int hoursFromAllowed = 0;
  //     while (!((hoursFromAllowed <= h+i && h+i < allowedTime[d].length && allowedTime[d][h+i-hoursFromAllowed]) || 
  //     (hoursFromAllowed < allowedTime[d].length-h-i && allowedTime[d][h+i+hoursFromAllowed]))) {
  //       // System.out.println((h+i-hoursFromAllowed) + " " + (h+i+hoursFromAllowed));
  //       ++hoursFromAllowed;
  //     }
  //     // System.out.println("hoursFromAllowed="+hoursFromAllowed);
  //     totalHoursFromAllowed += hoursFromAllowed;
  //   }
  //   if (totalHoursFromAllowed > 0) {
  //     return -weight + weightHoursFromAllowed * totalHoursFromAllowed;
  //   }else{
  //     return weight;
  //   }
  // }
}