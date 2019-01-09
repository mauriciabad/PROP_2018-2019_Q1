package domain;
import java.util.*;
import domain.*;
import domain.controllers.*;

public class Session {

  private ClassType classType;
  private int number;
  private String group;

  private List<RestrictionUnary>  restsU = new ArrayList<>();
  private List<RestrictionBinary> restsB = new ArrayList<>();
  private List<RestrictionGlobal> restsG = new ArrayList<>();

  private Classroom classroom;
  private int weekDay;
  private int hour;

  private int restThreshold = 0;


  private Set<ClassroomDayHour> allowedClassroomDayHour = new HashSet<>();

  public Session(ClassType classType, int number, String group) {
    this.classType = classType;
    this.number    = number;
    this.group     = group;
    initAllowedClassroomDayHour();
  }

  public Session(String group, int number, ClassType classType, Classroom classroom, List<RestrictionUnary> restsU, List<RestrictionBinary> restsB, List<RestrictionGlobal> restsG) {
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
    if (chd == null) {
      this.classroom = null;
      this.weekDay   = 0;
      this.hour      = 0;
    }else{
      this.classroom = chd.getClassroom();
      this.weekDay   = chd.getDay();
      this.hour      = chd.getHour();
    }
  }

  public void initAllowedClassroomDayHour() {
    CtrlDomain domain = CtrlDomain.getInstance();
    List<Classroom> allClassrooms = domain.getAllClassrooms();
    for (Classroom c : allClassrooms) {
      for (int d = 0; d < 5; d++) {
        for (int h = 8; h < 20; h++) {
          allowedClassroomDayHour.add(new ClassroomDayHour(d,h,c));
        }
      }
    }
  }

  public Set<ClassroomDayHour> getAllowedClassroomDayHour() {
    // initAllowedClassroomDayHour();
    return this.allowedClassroomDayHour;
  }

  public void addClassroomDayHour(Set<ClassroomDayHour> cdhs) {
    this.allowedClassroomDayHour.addAll(cdhs);
  }

  public Set<ClassroomDayHour> removeUnsatisfactoryCDH(List<Session> s) {
    Set<ClassroomDayHour> removedCDH = new HashSet<>();
    removedCDH.addAll(checkAllBinaryRestrictions(s));
    removedCDH.addAll(checkAllGlobalRestrictions(s));
    return removedCDH;
  }

  // post: allowedClassroomDayHour removed incompatible classes
  private Set<ClassroomDayHour> checkAllUnaryRestrictions() {
    System.out.println("checkAllUnaryRestrictions " + this.toString());
    Set<ClassroomDayHour> removedCDH = new HashSet<>();
    for (RestrictionUnary r : restsU) {
      removedCDH.addAll(checkUnaryRestriction(r));
    }
    return removedCDH;
  }
  
  private Set<ClassroomDayHour> checkUnaryRestriction(RestrictionUnary r) {
    Set<ClassroomDayHour> removedCDH = new HashSet<>();
    for (ClassroomDayHour cdh : allowedClassroomDayHour) { // cdh stands for ClassroomDayHour
      // System.out.printf(r.toString(this, cdh.getHour(), cdh.getDay(), cdh.getClassroom()));
      int points = r.check(this, cdh.getHour(), cdh.getDay(), cdh.getClassroom());
      cdh.addPuntuation(points);
      if (points < restThreshold)  removedCDH.add(cdh);
      // System.out.printf(" points="+points+", totalPoints="+cdh.getPuntuation()+"\n");
    }
    allowedClassroomDayHour.removeAll(removedCDH);
    return removedCDH;
  }

  private Set<ClassroomDayHour> checkAllBinaryRestrictions(List<Session> s) {
    Set<ClassroomDayHour> removedCDH = new HashSet<>();
    for (RestrictionBinary r : restsB) {
      removedCDH.addAll(checkBinaryRestriction(r, s));
    }
    return removedCDH;
  }
  private Set<ClassroomDayHour> checkBinaryRestriction(RestrictionBinary r, List<Session> s) {
    Set<ClassroomDayHour> removedCDH = new HashSet<>();
    for (ClassroomDayHour cdh : allowedClassroomDayHour) { // cdh stands for ClassroomDayHour
      // System.out.printf(r.toString(this, cdh.getHour(), cdh.getDay(), cdh.getClassroom()));
      int points = r.check(s, this, cdh.getHour(), cdh.getDay(), cdh.getClassroom());
      cdh.addPuntuation(points);
      if (points < restThreshold)  removedCDH.add(cdh);
      // System.out.printf(" points="+points+", totalPoints="+cdh.getPuntuation()+"\n");
    }
    allowedClassroomDayHour.removeAll(removedCDH);
    return removedCDH;
  }

  private Set<ClassroomDayHour> checkAllGlobalRestrictions(List<Session> s) {
    Set<ClassroomDayHour> removedCDH = new HashSet<>();
    for (RestrictionGlobal r : restsG) {
      removedCDH.addAll(checkGlobalRestriction(r, s));
    }
    return removedCDH;
  }
  private Set<ClassroomDayHour> checkGlobalRestriction(RestrictionGlobal r, List<Session> s) {
    Set<ClassroomDayHour> removedCDH = new HashSet<>();
    for (ClassroomDayHour cdh : allowedClassroomDayHour) { // cdh stands for ClassroomDayHour
      // System.out.printf(r.toString(this, cdh.getHour(), cdh.getDay(), cdh.getClassroom()));
      int points = r.check(s, this, cdh.getHour(), cdh.getDay(), cdh.getClassroom());
      cdh.addPuntuation(points);
      if (points < restThreshold)  removedCDH.add(cdh);
      // System.out.printf(" points="+points+", totalPoints="+cdh.getPuntuation()+"\n");
    }
    allowedClassroomDayHour.removeAll(removedCDH);
    return removedCDH;
  }


  public void addRestrictions(List<RestrictionUnary> rUs, List<RestrictionBinary> rBs, List<RestrictionGlobal> rGs){
    for (RestrictionUnary  rU : rUs) this.addRestrictionUnary(rU);
    for (RestrictionBinary rB : rBs) this.addRestrictionBinary(rB);
    for (RestrictionGlobal rG : rGs) this.addRestrictionGlobal(rG);
  }

  public void addRestrictionUnary(RestrictionUnary r)  {restsU.add(r); checkUnaryRestriction(r);}
  public void addRestrictionBinary(RestrictionBinary r){restsB.add(r);}
  public void addRestrictionGlobal(RestrictionGlobal r){restsG.add(r);}

  public void addDefaultRestrictions() {
    this.addRestrictionUnary(new RestUAllowedTime(8,20));
    this.addRestrictionUnary(new RestUClassroomCapacity());
    this.addRestrictionUnary(new RestUMaterial());

    this.addRestrictionBinary(new RestBLevelCompact());
    this.addRestrictionBinary(new RestBLevelOverlapping());
    this.addRestrictionBinary(new RestBCoRequisit());
    this.addRestrictionBinary(new RestBNearClassroom());

    this.addRestrictionGlobal(new RestGWeekPayload());
  }

  public boolean isOverlapping(int start, int duration) {
    return this.hour <= start+duration && start <= this.hour + duration;
  }

  public boolean isOverlapping(Session s) {
    return this.hour <= s.getHour()+s.getDuration() && s.getHour() <= this.hour + this.classType.getDuration();
  }

  public String toString() {
    return "{name=" + this.getSubject().getName() +", type="+ classType.getType() +", number="+ number +", group="+ group +"}";
  }

  public boolean isSameGroup(Session s) {
    return group.equals(s.getGroup()) || group.equals((Integer.parseInt(s.getGroup())/10) * 10);
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

  public List<RestrictionUnary> getRestsU() {
    return this.restsU;
  }

  public void setRestsU(List<RestrictionUnary> restsU) {
    this.restsU = restsU;
  }

  public List<RestrictionBinary> getRestsB() {
    return this.restsB;
  }

  public void setRestsB(List<RestrictionBinary> restsB) {
    this.restsB = restsB;
  }

  public List<RestrictionGlobal> getRestsG() {
    return this.restsG;
  }

  public void setRestsG(List<RestrictionGlobal> restsG) {
    this.restsG = restsG;
  }

  public Classroom getClassroom() {
    return this.classroom;
  }

  public void setClassroom(Classroom classroom) {
    this.classroom = classroom;
  }

  public int getWeekDay() {
    return this.weekDay;
  }

  public void setWeekDay(int weekDay) {
    this.weekDay = weekDay;
  }

  public int getHour() {
    return this.hour;
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