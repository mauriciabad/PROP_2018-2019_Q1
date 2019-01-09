package domain;
import java.util.*;
import domain.*;

public class Session {

  private ClassType classType;
  private int number;
  private String group;

  private List<Object> restsU = new ArrayList<>();
  private List<Object> restsB = new ArrayList<>();
  private List<Object> restsG = new ArrayList<>();

  private Classroom classroom;
  private int weekDay;
  private int hour;

  private int restThreshold = 0;


  private Set<ClassroomDayHour> allowedClassroomDayHour = new Set<>();

  public Session(ClassType classType, int number, String group) {
    this.classType = classType;
    this.number    = number;
    this.group     = group;
    initAllowedClassroomDayHour();
  }

  public Session(String group, int number, ClassType classType, Classroom classroom, List<Object> restsU, List<Object> restsB, List<Object> restsG) {
    this.group     = group;
    this.number    = number;
    this.classType = classType;
    this.classroom = classroom;
    this.restsU    = restsU;
    this.restsB    = restsB;
    this.restsG    = restsG;
    initAllowedClassroomDayHour();
    checkAllUnaryRestrictions();
  }

  public void setClassroomDayHour(ClassroomDayHour chd){
    this.classroom = chd.getClassroom();
    this.weekDay   = chd.getDay();
    this.hour      = chd.getHour();
  }

  public void initAllowedClassroomDayHour() {
  }

  //Getters
  public Set<ClassroomDayHour> getAllowedClassroomDayHour(List<Session> s) {
    return this.allowedClassroomDayHour;
  }

  // post: allowedClassroomDayHour removed incompatible classes
  public void checkAllUnaryRestrictions() {
  }
  public void checkUnaryRestriction(Object r) {
  }

  public void checkAllBinaryRestrictions(List<Session> s) {
  }
  public void checkBinaryRestriction(Object r, List<Session> s) {
  }

  public void checkAllGlobalRestrictions(List<Session> s) {
  }
  public void checkGlobalRestriction(Object r, List<Session> s) {
  }


  public void addRestrictions(List<Object> rUs, List<Object> rBs, List<Object> rGs){
  }

  public void addRestrictionUnary(Object r)  {restsU.add(r); checkUnaryRestriction(r);}
  public void addRestrictionBinary(Object r){restsB.add(r);}
  public void addRestrictionGlobal(Object r){restsG.add(r);}

  public void addDefaultRestrictions() {
  }

  public boolean isOverlapping(Session s) {
    return true;
  }

  public String toString() {
    return "{name=" + this.getSubject().getName() +", type= Teoria" +", number="+ number +", group="+ group +"}";
  }

  // Extra Getters
  public Subject getSubject() {
    return this.classType.getSubject();
  }
  public int getDuration() {
    return this.classType.getDuration();
  }

  // Getters && Setters
  public ClassType getClassType() {
    return this.classType;
  }

  public void setClassType(ClassType classType) {
    this.classType = classType;
  }

  public int getNumber() {
    return this.number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public String getGroup() {
    return this.group;
  }

  public void setGroup(String group) {
    this.group = group;
  }

  public List<Object> getRestsU() {
    return this.restsU;
  }

  public void setRestsU(List<Object> restsU) {
    this.restsU = restsU;
  }

  public List<Object> getRestsB() {
    return this.restsB;
  }

  public void setRestsB(List<Object> restsB) {
    this.restsB = restsB;
  }

  public List<Object> getRestsG() {
    return this.restsG;
  }

  public void setRestsG(List<Object> restsG) {
    this.restsG = restsG;
  }

  public Classroom getClassroom() {
    return this.classroom;
  }

  public void setClassroom(Classroom classroom) {
    this.classroom = classroom;
  }

  public int getWeekDay() {
    return 1;
  }

  public void setWeekDay(int weekDay) {
    this.weekDay = weekDay;
  }

  public int getHour() {
    return 1;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getRestThreshold() {
    return this.restThreshold;
  }

  public void setRestThreshold(int restThreshold) {
    this.restThreshold = restThreshold;
  }
}