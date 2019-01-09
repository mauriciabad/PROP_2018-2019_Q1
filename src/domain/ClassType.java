package domain;
import java.util.*;
import java.lang.*;

public class ClassType {

  private Subject subject;
  private String type;
  private int weeklySessions = 1;
  private int duration = 2;
  private int subgroup;
  private int groupSize;
  private List<String> materials = new ArrayList<>();

  
  public ClassType(Subject subject, String type, int weeklySessions, int duration, int subgroup, int groupSize, List<String> materials) {
    this.subject   = subject;
    this.type      = type;
    this.weeklySessions = weeklySessions;
    this.duration  = duration;
    this.subgroup  = subgroup;
    this.groupSize = groupSize;
    this.materials = materials;
  }

  //gives a list with the names of all groups of that type of class
  //subgroup may need an ajustment
  public List<String> makeGroups() {
    List<String> l = new ArrayList<>();
    
    int groups = (int) Math.ceil( subject.getStudents() / (double) groupSize);

    if (subgroup > 0) {
      for (int g = 0; g < groups; g++) {
        l.add("" + (g/subgroup + 1) + (g%subgroup + 1));
      }
    }else{
      for (int g = 0; g < groups; g++) {
        l.add("" + ((g+1) * 10));
      }
    }
    return l;
  }


  // Getters && Setters
  
  public Subject getSubject() {
    return this.subject;
  }

  public void setSubject(Subject subject) {
    this.subject = subject;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getWeeklySessions() {
    return this.weeklySessions;
  }

  public void setWeeklySessions(int weeklySessions) {
    this.weeklySessions = weeklySessions;
  }

  public int getDuration() {
    return this.duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getSubgroup() {
    return this.subgroup;
  }

  public void setSubgroup(int subgroup) {
    this.subgroup = subgroup;
  }

  public int getGroupSize() {
    return this.groupSize;
  }

  public void setGroupSize(int groupSize) {
    this.groupSize = groupSize;
  }

  public List<String> getMaterials() {
    return this.materials;
  }

  public void setMaterials(List<String> materials) {
    this.materials = materials;
  }
  

}