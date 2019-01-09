package domain;
import java.util.*;
import domain.*;

public class ClassroomDayHour implements Comparable<ClassroomDayHour>{
  private int day;
  private int hour;
  private Classroom classroom;
  private int puntuation = 0;
  
  public int compareTo(ClassroomDayHour o){
    return(o.puntuation - puntuation);
  }

  public ClassroomDayHour(int day, int hour, Classroom classroom) {
    this.day       = day;
    this.hour      = hour;
    this.classroom = classroom;
  }

  public ClassroomDayHour(int day, int hour, Classroom classroom, int puntuation) {
    this.day        = day;
    this.hour       = hour;
    this.classroom  = classroom;
    this.puntuation = puntuation;
  }

  public void addPuntuation(int points) {
    this.puntuation += points;
  }

  public String toString() {
    return "{day=" + day + ", hour=" + hour + ", classroom=" + classroom.getName() + ", points=" + puntuation +"}";
  }

  // Getters && Setters
  public int getDay() {
    return this.day;
  }

  public void setDay(int day) {
    this.day = day;
  }

  public int getHour() {
    return this.hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public Classroom getClassroom() {
    return this.classroom;
  }

  public void setClassroom(Classroom classroom) {
    this.classroom = classroom;
  }

  public int getPuntuation() {
    return this.puntuation;
  }

  public void setPuntuation(int puntuation) {
    this.puntuation = puntuation;
  }
  
}