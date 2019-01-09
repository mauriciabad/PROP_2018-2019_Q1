package domain;

import java.util.*;

public class RestrictionBinary {

  private int weight = 20;

  public RestrictionBinary() {}
  public RestrictionBinary(int weight) {
    this.weight = weight;
  }

  public int check(List<Session> sessions, Session s, int h, int d, Classroom c){
    return 0;
  }

  public int getWeight() {
    return this.weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }
}
