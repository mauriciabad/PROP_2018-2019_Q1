package presentation;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class CreateSchedule {
  private JButton eliminarButton;
  private JComboBox tipusComboBox1;
  private JButton visualitzarHorariButton;
  private JComboBox restrictionComboBox;
  private JComboBox curriculumComboBox;
  private JComboBox comboBox3;
  private JList restrictionsList;
  private JPanel Panel;
  private JPanel cards;
  private JComboBox horaComboBox;
  private JComboBox diaComboBox;
  private JPanel FixHour;
  private JButton afegirButton;
  private JComboBox aulaComboBox;
  private JPanel FixClassroom;
  private JPanel Overlap;
  private JComboBox assignaturaComboBox3;
  private JComboBox tipusComboBox3;
  private JComboBox campusComboBox;
  private JTextField nomDeLHorariTextField;
  private JComboBox assignaturaComboBox2;
  private JComboBox assignaturaComboBox1;
  private JComboBox grupComboBox2;
  private JComboBox grupComboBox1;
  private JComboBox nsessioComboBox1;
  private JComboBox nsessioComboBox2;
  private JComboBox tipusComboBox2;
  private JButton backbtn;
  private List<String> priority = new ArrayList<>();

  private JFrame frame=new JFrame("Crear Horari");//creating instance of JFrame

  private CtrlUI ui = new CtrlUI();
  private JSONObject scheduleJSON = new JSONObject("{\n" +
          "  \"name\": \"ExampleSchedule\",\n" +
          "  \"curriculum\": \"ExampleCurriculum\",\n" +
          "  \"campus\": \"ExampleCampus\",\n" +
          "  \"restrictions\": {\"RestUFixSessionTime\": [], \"RestUFixSessionClassroom\": [], \"RestBSameTypeOverlapping\": []}," +
          "  \"sessions\": []" +
          "}");
  private JSONObject restrictionsJSON = new JSONObject("{\"RestUFixSessionTime\": [], \"RestUFixSessionClassroom\": [], \"RestBSameTypeOverlapping\": []}");
  private JSONObject campusJSON = new JSONObject("{}");
  private JSONObject curriculumJSON = new JSONObject("{}");


  public CreateSchedule(){
    setup();
    nomDeLHorariTextField.setText("Horari-"+(int )(Math. random() * 100000));
    campusComboBox.setSelectedItem("Real");
    curriculumComboBox.setSelectedItem("GEI2010");
    updateScheduleJSON();
  }

  private void removeRestriction(String restName) {
    for (String restType : restrictionsJSON.keySet()) {
      JSONArray rests = restrictionsJSON.getJSONArray(restType);
      for (int j = 0; j < rests.length(); j++) {
        if (rests.getJSONObject(j).getString("name").equals(restName)) rests.remove(j);
      }
    }
  }

  public CreateSchedule(String name){
    scheduleJSON     = new JSONObject(ui.getSchedule(name));
    restrictionsJSON = scheduleJSON.getJSONObject("restrictions");
    campusJSON       = new JSONObject(ui.getCampus(scheduleJSON.getString("campus")));
    curriculumJSON   = new JSONObject(ui.getCurriculum(scheduleJSON.getString("curriculum")));

    setup();
    visualizeScheduleJSON();

  }

  public void updateScheduleJSON(){
    scheduleJSON.put("curriculum", (String) curriculumComboBox.getSelectedItem());
    scheduleJSON.put("campus", (String) campusComboBox.getSelectedItem());
    scheduleJSON.put("name", (String) nomDeLHorariTextField.getText());
    scheduleJSON.put("restrictions", restrictionsJSON);
  }

  public void visualizeScheduleJSON(){
    campusComboBox.setSelectedItem(scheduleJSON.getString("campus"));
    curriculumComboBox.setSelectedItem(scheduleJSON.getString("curriculum"));
    visualizeRestrictions();
    nomDeLHorariTextField.setText(scheduleJSON.getString("name"));
  }

  public void setAllItems(JComboBox c, List<String> l){
    c.removeAllItems();
    for (String e : l){ c.addItem(e); }
  }

  public void visualizeCampus(String campusName){
    if (campusName == null) return;
    ui.initCampus(campusName);
    setAllItems(aulaComboBox, ui.getAllClassrooms());
  }
  public void visualizeCurriculum(String curriculumName) {
    if (curriculumName == null) return;
    ui.initCurriculum(curriculumName);
    List<String> l = ui.getAllSubjects();
    setAllItems(assignaturaComboBox1, l);
    setAllItems(assignaturaComboBox2, l);
    setAllItems(assignaturaComboBox3, l);
  }
  public void visualizeRestrictions() {
    List<String> l = new ArrayList<String>();
    for (String restType : restrictionsJSON.keySet()) {
      JSONArray rests = restrictionsJSON.getJSONArray(restType);
      for (int j = 0; j < rests.length(); j++) {
        String name = generateName(rests.getJSONObject(j), restType);
        rests.getJSONObject(j).put("name", name);
        l.add(name);
        }
      }
    restrictionsList.setListData(new Vector(l));
  }

  private String generateName(JSONObject rest, String restType) {
    JSONObject sessionJSON;
    String name = "";
    switch (restType) {
      case "RestUFixSessionTime":
        sessionJSON = rest.getJSONObject("session");
        name =  sessionJSON.getString("subject") + " " +
                sessionJSON.getString("type") + " " +
                sessionJSON.getString("group") + " " +
                sessionJSON.getInt("number") + ": " +
                writeWeekDay(rest.getInt("weekDay")) + " a les " +
                rest.getInt("start") + "h";
        break;
      case "RestUFixSessionClassroom":
        sessionJSON = rest.getJSONObject("session");
        name =  sessionJSON.getString("subject") + " " +
                sessionJSON.getString("type") + " " +
                sessionJSON.getString("group") + " " +
                sessionJSON.getInt("number") + ": " +
                rest.getString("classroom");
        break;
      case "RestBSameTypeOverlapping":
        sessionJSON = rest.getJSONObject("session");
        name =  sessionJSON.getString("subject") + " " +
                sessionJSON.getString("type") + ": " +
                "No solapaments";
        break;
    }
    return name;
  }

  private String writeWeekDay(int weekDay) {
    String day = "desconegut";
    switch (weekDay){
      case 0: day = "Dilluns"; break;
      case 1: day = "Dimarts"; break;
      case 2: day = "Dimecres"; break;
      case 3: day = "Dijous"; break;
      case 4: day = "Divendres"; break;
      case 5: day = "Dissabte"; break;
      case 6: day = "Diumenge"; break;
      default: day = "dia nº "+weekDay; break;
    }
    return day;
  }
  private int writeWeekDayNumber(String weekDay) {
    int day = 0;
    switch (weekDay){
      case "Dilluns":   day = 0; break;
      case "Dimarts":   day = 1; break;
      case "Dimecres":  day = 2; break;
      case "Dijous":    day = 3; break;
      case "Divendres": day = 4; break;
      case "Dissabte":  day = 5; break;
      case "Diumenge":  day = 6; break;
      default: day = 0; break;
    }
    return day;
  }

  public void visualizeSubject(int n, String subjectName){
    if (subjectName == null) {
      switch (n){
        case 1: tipusComboBox1.removeAllItems(); break;
        case 2: tipusComboBox2.removeAllItems(); break;
        case 3: tipusComboBox3.removeAllItems(); break;
      }
      visualizeType(n,subjectName, null);
      return;
    }
    switch (n){
      case 1: setAllItems(tipusComboBox1, ui.getClassTypes(subjectName)); break;
      case 2: setAllItems(tipusComboBox2, ui.getClassTypes(subjectName)); break;
      case 3: setAllItems(tipusComboBox3, ui.getClassTypes(subjectName)); break;
    }
  }
  public void visualizeType(int n, String subjectName, String typeName) {
    visualizeGroup(n, subjectName, typeName);
    visualizeNSessions(n, subjectName, typeName);
  }
  public void visualizeNSessions(int n, String subjectName, String typeName) {
    if (subjectName == null || typeName == null) {
      switch (n){
        case 1: nsessioComboBox1.removeAllItems(); break;
        case 2: nsessioComboBox2.removeAllItems(); break;
      }
      return;
    }
    switch (n) {
      case 1: setAllItems(nsessioComboBox1, ui.getNSessions(subjectName,typeName)); break;
      case 2: setAllItems(nsessioComboBox2, ui.getNSessions(subjectName,typeName)); break;
    }
  }
  public void visualizeGroup(int n, String subjectName, String typeName) {
    if (subjectName == null || typeName == null) {
      switch (n){
        case 1: grupComboBox1.removeAllItems(); break;
        case 2: grupComboBox2.removeAllItems(); break;
      }
      return;
    }
    switch (n) {
      case 1: setAllItems(grupComboBox1, ui.getGroups(subjectName,typeName)); break;
      case 2: setAllItems(grupComboBox2, ui.getGroups(subjectName,typeName)); break;
    }
  }

  public void setup() {
    backbtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        MainMenu.drawMenu();
      }
    });
    restrictionComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String cardName = (String)cb.getSelectedItem();
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, cardName);
      }
    });
    campusComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selecionat = (String) campusComboBox.getSelectedItem();
        if(selecionat.equals("Crear...")){
          frame.setVisible(false);
          Aules a = new Aules();
          a.drawAules();
        }else{
          campusJSON = new JSONObject(ui.getCampus(selecionat));
          visualizeCampus(selecionat);
        }
      }
    });
    curriculumComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selecionat = (String) curriculumComboBox.getSelectedItem();
        if(selecionat.equals("Crear...")){
          frame.setVisible(false);
          PlaEstudis p = new PlaEstudis();
          p.drawPlaEstudis();
        }else{
          curriculumJSON = new JSONObject(ui.getCurriculum(selecionat));
          visualizeCurriculum(selecionat);
        }
      }
    });
    visualitzarHorariButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        updateScheduleJSON();
        ui.generateScheduleFromJSON(scheduleJSON,priority);
        frame.setVisible(false);
        PreviewSchedule o = new PreviewSchedule(scheduleJSON.getString("name"));
        o.drawFrame();
      }
    });
    assignaturaComboBox1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeSubject(1, (String) assignaturaComboBox1.getSelectedItem());
        visualizeType(1, (String) assignaturaComboBox1.getSelectedItem(), (String) tipusComboBox1.getSelectedItem());
      }
    });
    assignaturaComboBox2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeSubject(2, (String) assignaturaComboBox2.getSelectedItem());
        visualizeType(2, (String) assignaturaComboBox2.getSelectedItem(), (String) tipusComboBox2.getSelectedItem());
      }
    });
    assignaturaComboBox3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeSubject(3, (String) assignaturaComboBox3.getSelectedItem());
        visualizeType(3, (String) assignaturaComboBox3.getSelectedItem(), (String) tipusComboBox3.getSelectedItem());
      }
    });
    tipusComboBox1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeType(1, (String) assignaturaComboBox1.getSelectedItem(), (String) tipusComboBox1.getSelectedItem());
      }
    });
    tipusComboBox2.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeType(2, (String) assignaturaComboBox2.getSelectedItem(), (String) tipusComboBox2.getSelectedItem());
      }
    });
    tipusComboBox3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        visualizeType(3, (String) assignaturaComboBox3.getSelectedItem(), (String) tipusComboBox3.getSelectedItem());
      }
    });
    eliminarButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        priority.remove(restrictionsList.getSelectedIndex());
        removeRestriction((String) restrictionsList.getSelectedValue());
        visualizeRestrictions();
      }
    });
    afegirButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String input = (String) assignaturaComboBox1.getSelectedItem();
        input = input +","+ (String) tipusComboBox1.getSelectedItem();
        if(restrictionsList.getSelectedIndex() == 2){
          input = input + ",-1";
        }else {
          input = input + "," + (String) grupComboBox1.getSelectedItem();
        }
        priority.add(input);
        addRestriction(getRestrictionTypeName((String) restrictionComboBox.getSelectedItem()));
        visualizeRestrictions();
      }
    });

    setAllItems(campusComboBox,ui.getAllCampus());
    campusComboBox.addItem( "Crear..." );

    setAllItems(curriculumComboBox,ui.getAllCurriculums());
    curriculumComboBox.addItem( "Crear..." );
  }

  private void addRestriction(String restName) {
    JSONObject rest    = new JSONObject();
    JSONObject session = new JSONObject();
    switch (restName){
      case "RestUFixSessionTime":
        rest.put("start", Integer.parseInt((String) horaComboBox.getSelectedItem()));
        rest.put("weekDay", writeWeekDayNumber((String) diaComboBox.getSelectedItem()));
        session.put("number",  Integer.parseInt((String) nsessioComboBox1.getSelectedItem()) -1);
        session.put("subject", assignaturaComboBox1.getSelectedItem());
        session.put("type",    tipusComboBox1.getSelectedItem());
        session.put("group",   grupComboBox1.getSelectedItem());
        rest.put("session", session);
        break;
      case "RestUFixSessionClassroom":
        rest.put("classroom", (String) aulaComboBox.getSelectedItem());
        session.put("number",  Integer.parseInt((String) nsessioComboBox2.getSelectedItem()) -1);
        session.put("subject", assignaturaComboBox2.getSelectedItem());
        session.put("type",    tipusComboBox2.getSelectedItem());
        session.put("group",   grupComboBox2.getSelectedItem());
        rest.put("session", session);
        break;
      case "RestBSameTypeOverlapping":
        session.put("subject", (String) assignaturaComboBox3.getSelectedItem());
        session.put("type", (String) tipusComboBox3.getSelectedItem());
        rest.put("session", session);
        break;
    }
    rest.put("name", generateName(rest, restName));
    restrictionsJSON.getJSONArray(restName).put(rest);
  }

  private String getRestrictionTypeName(String name){
    switch (name){
      case "Fixar Hora": return "RestUFixSessionTime";
      case "Fixar Aula": return "RestUFixSessionClassroom";
      case "No solapaments d'un tipus": return "RestBSameTypeOverlapping";
    }
    return "unknown";
  }

  public void drawFrame() {
    try {
      frame.setIconImage(ImageIO.read(new File("img/magic-lamp.png")));
    }catch(IOException e){}
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(this.Panel);
    frame.pack();
    centerWindow();
    frame.setVisible(true);
  }

  public void itemStateChanged(ItemEvent evt) {
    CardLayout cl = (CardLayout)(cards.getLayout());
    cl.show(cards, (String)evt.getItem());
  }

  private void centerWindow() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int x = (screenSize.width - frame.getWidth()) / 2;
    int y = (screenSize.height - frame.getHeight()) / 2;
    frame.setLocation(x, y);
  }
}
