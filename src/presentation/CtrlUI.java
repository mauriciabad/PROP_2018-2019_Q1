package presentation;
import data.CtrlDB;
import domain.Classroom;
import domain.Subject;
import domain.controllers.CtrlDomain;
import org.json.JSONObject;

import java.util.*;


public class CtrlUI {

  private CtrlDB db = CtrlDB.getInstance();
  private CtrlDomain d = CtrlDomain.getInstance();

  public CtrlUI() {}

  public void setup() {
  }

  public List<String> getAllCampus(){
    return (db.getAllCampus());
  }

  public String getCampus(String name){
    return(db.getCampus(name));
  }

  public void setCampus(String campus, String value){
    db.setCampus(campus,value);
  }

  public void deleteCampus(String campus){
    db.deleteCampus(campus);
  }

  public List<String> getAllSchedules() {
    return (db.getAllSchedules());
  }

  public List<String> getAllCurriculums(){ return (db.getAllCurriculums()); }

  public String getCurriculum(String name){
    return(db.getCurriculum(name));
  }

  public void setCurriculum(String curriculum, String value){
    db.setCurriculum(curriculum,value);
  }

  public void deleteCurriculum(String curriculum){
    db.deleteCurriculum(curriculum);
  }

  public String getSchedule(String name){
    return(db.getSchedule(name));
  }

  public void generateScheduleFromJSON(JSONObject scheduleJSON , List<String> priority){ d.generateScheduleFromJSON(scheduleJSON, priority); };

  public List<String> getAllClassrooms(){
    List<String> l = new ArrayList<String>();
    for (Classroom c : d.getAllClassrooms()){
      l.add(c.getName());
    }
    return l;
  };

  public List<String> getAllSubjects(){
    List<String> l = new ArrayList<String>();
    for (Subject s : d.getAllSubjects()){
      l.add(s.getName());
    }
    return l;
  };

  public List<String> getNSessions(String subjectName, String typeName) { return d.getNSessions(subjectName, typeName); }
  public List<String> getGroups(String subjectName, String typeName) { return d.getGroups(subjectName, typeName); }
  public List<String> getClassTypes(String subjectName) { return d.getClassTypes(subjectName); }

  public void initCurriculum(String curriculumName) { d.initCurriculum(curriculumName); }
  public void initCampus(String campusName) { d.initCampus(campusName); }
}