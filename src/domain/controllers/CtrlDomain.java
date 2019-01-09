package domain.controllers;

import java.util.*;
import data.CtrlDB;
import domain.*;
import org.json.*;


public class CtrlDomain {

  private CtrlDB db = CtrlDB.getInstance();

  private String campus;
  private Map<String, Classroom> classrooms = new HashMap<String, Classroom>();

  private String curriculum;
  private Map<String, Subject> subjects = new HashMap<String, Subject>();


  private static CtrlDomain singletonObject;

  public static CtrlDomain getInstance() {
    if (singletonObject == null)
      singletonObject = new CtrlDomain() {
      };
    return singletonObject;
  }

  private CtrlDomain() {}
  public void initCtrlDomain(String campus, String curriculum) {
    this.campus     = campus;
    this.curriculum = curriculum;

    classrooms = db.loadCampus(campus);
    subjects   = db.loadCurriculum(curriculum);
  }
  public void initCampus(String campus) {
    this.campus = campus;
    classrooms  = db.loadCampus(campus);
  }
  public void initCurriculum(String curriculum) {
    this.curriculum = curriculum;
    subjects       = db.loadCurriculum(curriculum);
  }


  public List<Classroom> getAllClassrooms() {
    return new ArrayList<Classroom>(classrooms.values());
  }
  public List<Subject> getAllSubjects() {
    return new ArrayList<Subject>(subjects.values());
  }

  public List<Session> getAllSessions() {
    List<Session> sessions = new ArrayList<Session>();
    Collection<Subject> subjects = this.subjects.values();
    for (Subject s : subjects) {
      for (ClassType t : s.getClassTypes()) {
        for (int n = 0; n < t.getWeeklySessions(); n++) {
          for (String g : t.makeGroups()) {
            Session session = new Session(t, n, g);
            session.addDefaultRestrictions();
            sessions.add(session);
          }
        }
      }
    }
    return sessions;
  }

  public List<Session> getAllSessions(JSONObject restrictionsJSON) {
    List<Session> sessions = new ArrayList<Session>();
    Collection<Subject> subjects = this.subjects.values();
    for (Subject s : subjects) {
      for (ClassType t : s.getClassTypes()) {
        for (int n = 0; n < t.getWeeklySessions(); n++) {
          for (String g : t.makeGroups()) {
            Session session = new Session(t, n, g);
            session.addDefaultRestrictions();
            addCustomRestrictions(session, restrictionsJSON);
            sessions.add(session);
          }
        }
      }
    }
    return sessions;
  }

  private void addCustomRestrictions(Session session, JSONObject restrictionsJSON) {
    for (String restType : restrictionsJSON.keySet()) {
      JSONArray rests = restrictionsJSON.getJSONArray(restType);
      for (int j = 0; j < rests.length(); j++) {
        JSONObject rest = rests.getJSONObject(j);
        JSONObject sessionJSON;
        String rs, rt, rg;
        int rn;
        switch (restType){
          case "RestUFixSessionTime":
            sessionJSON = rest.getJSONObject("session");
            rs = sessionJSON.getString("subject");
            rt = sessionJSON.getString("type");
            rg = sessionJSON.getString("group");
            rn = sessionJSON.getInt("number");
            if( session.getSubject().getName().equals(rs) &&
                    session.getClassType().getType().equals(rt) &&
                    session.getGroup().equals(rg) &&
                    session.getNumber() == rn ){
              session.addRestrictionUnary(new RestUFixSessionTime(rest.getInt("start"),rest.getInt("weekDay")));
            }
            break;
          case "RestUFixSessionClassroom":
            sessionJSON = rest.getJSONObject("session");
            rs = sessionJSON.getString("subject");
            rt = sessionJSON.getString("type");
            rg = sessionJSON.getString("group");
            rn = sessionJSON.getInt("number");
            if( session.getSubject().getName().equals(rs) &&
                    session.getClassType().getType().equals(rt) &&
                    session.getGroup().equals(rg) &&
                    session.getNumber() == rn ){
              session.addRestrictionUnary(new RestUFixSessionClassroom(classrooms.get(rest.getString("classroom"))));
            }
            break;
          case "RestBSameTypeOverlapping":
            sessionJSON = rest.getJSONObject("session");
            rs = sessionJSON.getString("subject");
            rt = sessionJSON.getString("type");
            if( session.getSubject().getName().equals(rs) &&
                  session.getClassType().getType().equals(rt)){
              session.addRestrictionBinary(new RestBLevelOverlapping());
            }
            break;
        }
      }
    }
  }

  public void generateSchedule(String name){
    Schedule s = new Schedule(name);
    Stack<Session> allSessions = new Stack<Session>();
    allSessions.addAll(this.getAllSessions());
    s.generateSchedule(allSessions);
    saveSchedule(s);
  }

  public void generateScheduleFromJSON(JSONObject scheduleJSON, List<String> priority) {
    initCtrlDomain(scheduleJSON.getString("campus"), scheduleJSON.getString("curriculum"));
    Schedule s = new Schedule(scheduleJSON.getString("name"));
    Stack<Session> allSessions = new Stack<Session>();
    if(priority.isEmpty()) {
      allSessions.addAll(this.getAllSessions(scheduleJSON.getJSONObject("restrictions")));
    }else{
      List<Session> seslist =this.getAllSessions(scheduleJSON.getJSONObject("restrictions"));
      List <Session> prioses = new ArrayList<>();
      for(Session ses : seslist){
        boolean b = true;
        ClassType clas = ses.getClassType();
        for(int i = 0; i < priority.size();i++){
          String[] prioinfo = (priority.get(i).split(","));
          if(prioinfo[2].equals("-1")) {
            if ((clas.getSubject().getName().equals(prioinfo[0]) && clas.getType().equals(prioinfo[1]))){
              b = false;
              prioses.add(ses);
              break;
            }
          }else if(prioinfo[1].equals("Teoria")) {
            if ((clas.getSubject().getName().equals(prioinfo[0]) && clas.getType().equals(prioinfo[1]) && Integer.parseInt(ses.getGroup()) == Integer.parseInt(prioinfo[2]))) {
              b = false;
              prioses.add(ses);
              break;
            }
          }else{
            if ((clas.getSubject().getName().equals(prioinfo[0]) && clas.getType().equals(prioinfo[1]) && clas.getSubgroup() == Integer.parseInt(prioinfo[2]))) {
              b = false;
              prioses.add(ses);
              break;
            }

          }
        }
        if(b) allSessions.push(ses);
      }
      allSessions.addAll(prioses);
    }
    s.generateSchedule(allSessions);
    s.setCustomRestrictions(scheduleJSON.getJSONObject("restrictions").toString());
    saveSchedule(s);
  }

  public void saveSchedule(Schedule s){
    String savedSchedule = db.saveSchedule(s.getSchedule(), s.getName(), curriculum, campus, s.getCustomRestrictions());
//    System.out.println();
    System.out.println("Schedule saved at /data/schedule/" + s.getName() +".json");
    // System.out.println(savedSchedule);
  }

  public String getCampus() {
    return this.campus;
  }

  public String getCurriculum() {
    return this.curriculum;
  }

  public List<String> getClassTypes(String subjectName) {
    List<String> l = new ArrayList<>();
    for (ClassType t : subjects.get(subjectName).getClassTypes()) l.add(t.getType());
    return l;
  }

  public List<String> getGroups(String subjectName, String typeName) {
    return subjects.get(subjectName).getClassType(typeName).makeGroups();
  }

  public List<String> getNSessions(String subjectName, String typeName) {
    List<String> l = new ArrayList<>();
    for (int i = subjects.get(subjectName).getClassType(typeName).getWeeklySessions(); i > 0; i--) {
      l.add(""+i);
    }
    return l;
  }
}
