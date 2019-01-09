package presentation;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PreviewSchedule {
  private JTable table1;
  private JPanel panel;
  private JComboBox scheduleComboBox;
  private JButton editarButton;
  private JButton backbtn;

  JFrame frame = new JFrame("Visualitzar horari");

  private CtrlUI ui = new CtrlUI();
  private JSONObject scheduleJSON = new JSONObject("{\n" +
          "  \"name\": \"ExampleSchedule\",\n" +
          "  \"curriculum\": \"ExampleCurriculum\",\n" +
          "  \"campus\": \"ExampleCampus\",\n" +
          "  \"restrictions\": {\"RestUFixSessionTime\": [], \"RestUFixSessionClassroom\": [], \"RestBSameTypeOverlapping\": []}," +
          "  \"sessions\": []" +
          "}");

  private String[] columnNames = {"Hora","Dilluns", "Dimarts", "Dimecres", "Dijous","Divendres"};

  public PreviewSchedule(){

    setup();

  }

  public PreviewSchedule(String name){
    scheduleJSON = new JSONObject(ui.getSchedule(name));

    setup();
    scheduleComboBox.setSelectedItem(name);
  }

  private void setup() {
    backbtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
        MainMenu.drawMenu();
      }
    });
    scheduleComboBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selecionat = (String) scheduleComboBox.getSelectedItem();
        if(selecionat.equals("Crear...")){
          frame.setVisible(false);
          CreateSchedule a = new CreateSchedule();
          a.drawFrame();
        }else{
          scheduleJSON = new JSONObject(ui.getSchedule(selecionat));
          visualizeSchedule(selecionat);
        }
      }
    });
    editarButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.setVisible(false);
        CreateSchedule a = new CreateSchedule((String) scheduleComboBox.getSelectedItem());
        a.drawFrame();
      }
    });

    setAllItems(scheduleComboBox,ui.getAllSchedules());
    scheduleComboBox.addItem( "Crear..." );
  }

  private void visualizeSchedule(String sheduleName) {
    Object[][] data = generateData();
    table1.setModel(new AbstractTableModel() {
      public int getColumnCount() { return 6; }
      public int getRowCount() { return 12;}
      public String getColumnName(int index) { return columnNames[index]; }
      public Object getValueAt(int row, int col) { return data[row][col]; }
    });
    updateRowHeights();
  }

  public Object[][] generateData() {
    Object[][] data = { {"8h - 9h", "", "", "", "", ""},
            {"9h - 10h", "", "", "", "", ""},
            {"10h - 11h", "", "", "", "", ""},
            {"11h - 12h", "", "", "", "", ""},
            {"12h - 13h", "", "", "", "", ""},
            {"13h - 14h", "", "", "", "", ""},
            {"14h - 15h", "", "", "", "", ""},
            {"15h - 16h", "", "", "", "", ""},
            {"16h - 17h", "", "", "", "", ""},
            {"17h - 18h", "", "", "", "", ""},
            {"18h - 19h", "", "", "", "", ""},
            {"19h - 20h", "", "", "", "", ""}
    };
    JSONArray sessions = scheduleJSON.getJSONArray("sessions");
    for (int i=0; i<data.length; ++i){
      for (int j=1; j<data[i].length; ++j){
          data[i][j]="<html><br>";
      }
    }
    for (int k = 0; k < sessions.length(); k++) {
      int weekday = sessions.getJSONObject(k).getInt("weekDay");
      int hora = sessions.getJSONObject(k).getInt("start");

      int cellEmpty;
      if (data[hora - 8][weekday + 1] == "<html><br>") cellEmpty=1;
      else cellEmpty=0;

      if (cellEmpty==0)data[hora - 8][weekday + 1] += "<br>"+"<br>";
      data[hora - 8][weekday + 1] += sessions.getJSONObject(k).getString("subject")+" ";
      data[hora - 8][weekday + 1] += sessions.getJSONObject(k).getString("group")+" ";
      data[hora - 8][weekday + 1] += sessions.getJSONObject(k).getString("type")+"<br>";
      data[hora - 8][weekday + 1] += sessions.getJSONObject(k).getString("classroom");
    }
    for (int i=0; i<data.length; ++i){
      for (int j=1; j<data[i].length; ++j){
        data[i][j]+="<br><br></html>";
      }
    }
    return data;
  }

  private void updateRowHeights()
  {
    for (int row = 0; row < table1.getRowCount(); row++)
    {
      int rowHeight = table1.getRowHeight();

      for (int column = 0; column < table1.getColumnCount(); column++)
      {
        Component comp = table1.prepareRenderer(table1.getCellRenderer(row, column), row, column);
        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
      }

      table1.setRowHeight(row, rowHeight);
    }
  }

  public void drawFrame() {
    try {
      frame.setIconImage(ImageIO.read(new File("img/magic-lamp.png")));
    }catch(IOException e){}
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(this.panel);
    frame.pack();
    centerWindow();
    frame.setVisible(true);
  }

  public void setAllItems(JComboBox c, List<String> l){
    c.removeAllItems();
    for (String e : l){ c.addItem(e); }
  }

  private void centerWindow() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int x = (screenSize.width - frame.getWidth()) / 2;
    int y = (screenSize.height - frame.getHeight()) / 2;
    if(y > 0) frame.setLocation(x, y);
    else frame.setLocation(x, 0);
  }
}
