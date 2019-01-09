package data;

import java.io.File;
import java.nio.charset.*;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
import domain.*;
import domain.controllers.*;
import org.json.*;

public class CtrlDB {

  private static CtrlDB singletonObject;

  public static CtrlDB getInstance() {
    if (singletonObject == null)
      singletonObject = new CtrlDB();
    return singletonObject;
  }

  private CtrlDB() {
  }

  public List<String> getAllCampus() {
    File folder = new File("data/campus");
    File[] listOfFiles = folder.listFiles();
    List<String> campuslist = new ArrayList<>();
    for (File file : listOfFiles) {
      if (file.isFile()) {
        campuslist.add(file.getName().replace(".json", ""));
      }
    }
    return campuslist;
  }

  public String getCampus(String campus) {
    String path = "data/campus/" + campus + ".json";
    String data = readFile(path);
    return (data);
  }

  public List<String> getAllSchedules() {
    File folder = new File("data/schedule");
    File[] listOfFiles = folder.listFiles();
    List<String> schedlist = new ArrayList<>();
    for (File file : listOfFiles) {
      if (file.isFile()) {
        schedlist.add(file.getName().replace(".json", ""));
      }
    }
    return schedlist;
  }

  public void setCampus(String campus, String value) {
    String path = "data/campus/"+campus+".json";
    saveFile(path,value);
  }
  public void deleteCampus(String campus) {
    String path = "data/campus/"+campus+".json";
    File file = new File(path);
    file.delete();
   }
  public void deleteCurriculum(String curriculum) {
    String path = "data/curriculum/"+curriculum+".json";
    File file = new File(path);
    file.delete();
  }


  public List<String> getAllCurriculums(){
    File folder = new File("data/curriculum");
    File[] listOfFiles = folder.listFiles();
    List<String> plaEstList = new ArrayList<>();
    for (File file : listOfFiles) {
      if (file.isFile()) {
        plaEstList.add(file.getName().replace(".json", ""));
      }
    }
    return plaEstList;
  }

  public String getCurriculum(String curriculum){
    String path = "data/curriculum/"+curriculum+".json";
    String data = readFile(path);
    return (data);
  }

  public String getSchedule(String schedule){
    String path = "data/schedule/"+schedule+".json";
    String data = readFile(path);
    return (data);
  }

  public void setCurriculum(String campus, String value) {
    String path = "data/curriculum/"+campus+".json";
    saveFile(path,value);
  }

  private static String readFile(String path) {
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(path));
      return new String(encoded, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  private static void saveFile(String path, String content) {
    try {
      Files.write(Paths.get(path), content.getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static List<String> toList(JSONArray jsonArray) {
    List<String> list = new ArrayList<String>();
    for (int i = 0; i < jsonArray.length(); i++) {
      list.add(jsonArray.getString(i));
    }
    return list;
  }

  public static Map<String, Classroom> loadCampus(String campusID) {
    String raw = readFile("./data/campus/" + campusID + ".json");
    JSONObject campus = new JSONObject(raw);
    JSONArray classrooms = new JSONObject(raw).getJSONArray("classrooms");

    Map<String, Classroom> result = new HashMap<String, Classroom>();

    for (int i = 0; i < classrooms.length(); i++) {
      result.put(classrooms.getJSONObject(i).getString("name"), new Classroom(
              classrooms.getJSONObject(i).getString("name"),
              classrooms.getJSONObject(i).getInt("capacity"),
              toList(classrooms.getJSONObject(i).getJSONArray("material"))
      ));
    }

    // System.out.println(result);
    return result;
  }

  public static Map<String, Subject> loadCurriculum(String curriculumID) {
    String raw = readFile("./data/curriculum/" + curriculumID + ".json");
    JSONObject curriculum = new JSONObject(raw);
    JSONArray subjects = curriculum.getJSONArray("subjects");

    Map<String, Subject> result = new HashMap<String, Subject>();

    //for each subject
    for (int i = 0; i < subjects.length(); i++) {
      JSONObject subjectJSON = subjects.getJSONObject(i);
      JSONArray classTypesJSON = subjectJSON.getJSONArray("classTypes");

      Subject subject = new Subject(
              subjectJSON.getString("name"),
              subjectJSON.getString("longName"),
              subjectJSON.getInt("level"),
              subjectJSON.getInt("students"),
              toList(subjectJSON.getJSONArray("preRequisit")),
              toList(subjectJSON.getJSONArray("coRequisit")),
              toList(subjectJSON.getJSONArray("preCoRequisit"))
      );

      //for each classType
      for (int j = 0; j < classTypesJSON.length(); j++) {

        JSONObject classTypeJSON = classTypesJSON.getJSONObject(j);
        subject.addClassType(new ClassType(
                subject,
                classTypeJSON.getString("type"),
                classTypeJSON.getInt("weeklySessions"),
                classTypeJSON.getInt("duration"),
                classTypeJSON.getInt("subgroup"),
                classTypeJSON.getInt("groupSize"),
                toList(classTypeJSON.getJSONArray("materials"))
        ));
      }

      result.put(subject.getName(), subject);
    }

    // System.out.println(result);
    return result;
  }

  public static String saveSchedule(List<Session> sessions, String name, String curriculum, String campus, String customRestrictions) {
    JSONObject scheduleJSON = new JSONObject();
    scheduleJSON.put("name", name);
    scheduleJSON.put("curriculum", curriculum);
    scheduleJSON.put("campus", campus);
    scheduleJSON.put("restrictions",  new JSONObject(customRestrictions));

    JSONArray sessionsJSON = new JSONArray();
    for (Session session : sessions) {
      JSONObject sessionJSON = new JSONObject();
      sessionJSON.put("subject", session.getSubject().getName());
      sessionJSON.put("type", session.getClassType().getType());
      sessionJSON.put("group", session.getGroup());
      sessionJSON.put("groupSize", session.getClassType().getGroupSize());
      sessionJSON.put("classroom", session.getClassroom().getName());
      sessionJSON.put("weekDay", session.getWeekDay());
      sessionJSON.put("start", session.getHour());
      sessionJSON.put("end", session.getHour() + session.getDuration());

      sessionsJSON.put(sessionJSON);
    }
    scheduleJSON.put("sessions", sessionsJSON);

    saveFile("./data/schedule/" + name + ".json", scheduleJSON.toString(2));
    return scheduleJSON.toString(2);

  }

  boolean isValidCurriculum(JSONObject c, String name){
    return c.getString("name").equals(name) && isValidCurriculum(c);
  }
  boolean isValidCurriculum(JSONObject c){
    return c.getJSONArray("subjects").length()>0;
  }

}