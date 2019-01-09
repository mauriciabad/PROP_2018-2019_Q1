package presentation;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static javax.swing.JOptionPane.YES_OPTION;

public class Aules {
  private JButton backbtn;
  private JPanel Panel;
  private JComboBox comboBox1;
  private JList list1;
  private JButton guardarButton;
  private JButton esborrarButton;
  private JTextField nomfield;
  private JTextField materialfield;
  private JTextField capacityfield;
  private JButton afegAula;
  private JButton esbCampus;
  private JSONObject jsoncampus;

  private CtrlUI ui = new CtrlUI();

  private JFrame aula=new JFrame("Gestionar Campus");//creating instance of JFrame


  public Aules(){

    list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    list1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
    list1.setVisibleRowCount(-1);

    listCampus();
    firstloadlist();

    backbtn.addActionListener(new ActionListener() {
      /**
       * Torna a la finestra previa
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        aula.dispose();
        MainMenu.drawMenu();
      }
    });
    comboBox1.addActionListener(new ActionListener() {
      /**
       * Evalua el element triat del desplegable de campus y carrega les dades o crea un campus nou
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        String campus = (String) comboBox1.getSelectedItem();
        //Volem crear un nou campus
        if(campus.equals("Afegir...")){
          String str = JOptionPane.showInputDialog(null, "Insereix nom per el nou campus: ",
                  "Nou Campus", JOptionPane.INFORMATION_MESSAGE);
          if(str != null){
            int pos = comboBox1.getSelectedIndex();
            comboBox1.insertItemAt(str,pos);
            comboBox1.setSelectedItem(str);
            String[] aules = new String[0];
            list1.setListData(aules);
            jsoncampus = new JSONObject();
            jsoncampus.put("name",str);
            JSONArray array = new JSONArray();
            jsoncampus.put("classrooms",array);
          }

        }else{
          try {
            String datacampus = ui.getCampus(campus);
            jsoncampus = new JSONObject(datacampus);
            JSONArray arr = jsoncampus.getJSONArray("classrooms");
            drawlist(arr);
          }catch(org.json.JSONException ex){
            JOptionPane.showMessageDialog(
                    null,
                    "Error al obrir el fitxer",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    });
    list1.addComponentListener(new ComponentAdapter() {
    });
    list1.addFocusListener(new FocusAdapter() {
    });
    list1.addMouseListener(new MouseAdapter() {
      /**
       * {@inheritDoc}
       * @brief Evalua el element de la llista de Aules i crear una aula nova, esborra el campus o visualitza/modifica una aula
       * @param e
       */
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        try {
          String selectedItem = (String) list1.getSelectedValue();
          JSONArray arr = jsoncampus.getJSONArray("classrooms");
          for (int i = 0; i < arr.length(); i++) {
            if ((arr.getJSONObject(i).getString("name")).equals(selectedItem)) {
              nomfield.setText(arr.getJSONObject(i).getString("name"));
              capacityfield.setText(String.valueOf(arr.getJSONObject(i).getInt("capacity")));
              JSONArray jsonmat = arr.getJSONObject(i).getJSONArray("material");
              String material = "";
              for (int j = 0; j < jsonmat.length(); j++) {
                material = material + (String) jsonmat.get(j);
                material = material + ",";
              }
              material = material.substring(0, material.length() - 1);
              materialfield.setText(material);
              break;
            }
          }
        }catch(NullPointerException ex){
          JOptionPane.showMessageDialog(
                  null,
                  "No hi ha dades disponibles per seleccionar",
                  "Sense Dades",
                  JOptionPane.INFORMATION_MESSAGE);
        }
      }
    });
    /**
     * Guarda els canvis efectuats a l'aula al campus
     * @param e
     */
    guardarButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        try{
          JSONArray arr = jsoncampus.getJSONArray("classrooms");
          if(nomfield.getText().equals("") || capacityfield.getText().equals("")){
            JOptionPane.showMessageDialog(null,
                    "Es necesari omplir tots els camps indicats",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
            return;
          }
          if (list1.getSelectedValue().equals("Nova")) { //Es el cas de una aula nova
            JSONObject obj = new JSONObject();
            String mat = materialfield.getText();

            //Construim Array JSON de Materials
            String[] materialsarray = (mat.split(","));
            JSONArray materialsjson = new JSONArray();
            for (int i = 0; i < materialsarray.length; ++i) {
              materialsjson.put(materialsarray[i]);
            }

            //Construim l'array per classrooms
            obj.put("material", materialsjson);
            obj.put("name", nomfield.getText());
            try {
              obj.put("capacity", Integer.parseInt(capacityfield.getText()));
            }catch(NumberFormatException e){
              JOptionPane.showMessageDialog(null,
                      "El camp Capacitat requereix un valor numeric",
                      "Error",
                      JOptionPane.WARNING_MESSAGE);
              return;
            }

            //Guardem el nou JSON a disc

            arr.put(arr.length(), obj);
            jsoncampus.put("classrooms", arr);

          } else { //Es vol modificar una aula ja existent
            String aula = (String) list1.getSelectedValue();
            JSONArray newarr = new JSONArray();

            for (int i = 0; i < arr.length(); ++i) {
              JSONObject jsob = arr.getJSONObject(i);
              if (jsob.getString("name").equals(list1.getSelectedValue())) {

                String mat = materialfield.getText();
                //Construim Array JSON de Materials
                String[] materialsarray = (mat.split(","));
                JSONArray materialsjson = new JSONArray();
                for (int j = 0; j < materialsarray.length; ++j) {
                  materialsjson.put(materialsarray[j]);
                }
                //Construim l'array per classrooms
                jsob.put("material", materialsjson);
                jsob.put("name", nomfield.getText());
                try {
                  jsob.put("capacity", Integer.parseInt(capacityfield.getText()));
                }catch(NumberFormatException e){
                  JOptionPane.showMessageDialog(null,
                          "El camp Capacitat requereix un valor numeric",
                          "Error",
                          JOptionPane.WARNING_MESSAGE);
                  return;
                }
                newarr.put(i, jsob);
              } else {
                newarr.put(i, arr.get(i));
              }
            }
            jsoncampus.put("classrooms", newarr);
          }
          arr = jsoncampus.getJSONArray("classrooms");
          ui.setCampus((String) comboBox1.getSelectedItem(), jsoncampus.toString(2));
          drawlist(arr);
        }catch(NullPointerException e){
          if(list1.isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Es necesari indicar un valor en la llista de Aules",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
          }else {
            JOptionPane.showMessageDialog(null,
                    "Error al inseri les dades",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
          }
        }
      }

    });
    esborrarButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        try{
          JSONArray arr = jsoncampus.getJSONArray("classrooms");
          String aula = (String)list1.getSelectedValue();

          JSONArray newarr = new JSONArray();
          boolean eliminat = false;
          for (int i = 0; i < arr.length(); ++i) {
            JSONObject jsob = arr.getJSONObject(i);
            if (!jsob.getString("name").equals(aula)) {
              if (eliminat) {
                newarr.put(i - 1, arr.get(i));
              } else {
                newarr.put(i, arr.get(i));
              }
            } else {
              eliminat = true;
            }
          }
          jsoncampus.put("classrooms", newarr);

          arr = jsoncampus.getJSONArray("classrooms");
          drawlist(arr);
        }
        catch(NullPointerException e){
          JOptionPane.showMessageDialog(null,
                  "Sense dades per esborrar",
                  "Error",
                  JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    afegAula.addActionListener(new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        nomfield.setText("");
        capacityfield.setText("");
        materialfield.setText("");
        JSONArray arr = jsoncampus.getJSONArray("classrooms");
        String[] aules = new String[arr.length()+1];
        for (int i = 0; i < arr.length(); i++) {
          aules[i] = (arr.getJSONObject(i).getString("name"));
        }
        aules[arr.length()] = "Nova";
        list1.setListData(aules);
        list1.setSelectedValue("Nova",false);
      }
    });
    esbCampus.addActionListener(new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        int n = JOptionPane.showConfirmDialog(
                null,
                "Està segur que desitja eliminar al Campus?",
                "Requereix confirmació",
                JOptionPane.WARNING_MESSAGE,
                JOptionPane.YES_NO_OPTION);
        if(n==YES_OPTION) {
          if(comboBox1.getSelectedItem().equals("Afegir...")){
            JOptionPane.showMessageDialog(null,
                    "No es pot esborrar el campus",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
          }else {
            ui.deleteCampus((String) comboBox1.getSelectedItem());
            int index = comboBox1.getSelectedIndex();
            comboBox1.setSelectedIndex(1);
            comboBox1.removeItemAt(index);
            list1.setListData(new String[0]);
          }
        }
      }
    });
  }

  private void firstloadlist() {
    comboBox1.setSelectedIndex(0);
    String campus = (String) comboBox1.getSelectedItem();
    if(campus.equals("Afegir...")) return;
    try {
      String datacampus = ui.getCampus(campus);
      jsoncampus = new JSONObject(datacampus);
      JSONArray arr = jsoncampus.getJSONArray("classrooms");
      drawlist(arr);
    }catch(org.json.JSONException ex){
      JOptionPane.showMessageDialog(
              null,
              "Error al obrir el fitxer",
              "Error",
              JOptionPane.ERROR_MESSAGE);
    }
  }

  private void drawlist(JSONArray arr) {
    //Tornem a carregar la llista de aules amb la nova aula afegida
    String[] aules = new String[arr.length()];
    for (int i = 0; i < arr.length(); i++) {
      aules[i] = (arr.getJSONObject(i).getString("name"));
    }
    list1.setListData(aules);
  }

  private void listCampus() {
    List<String> campuslst = ui.getAllCampus();
    for(String campus : campuslst) {
      comboBox1.addItem(campus);
    }
    comboBox1.addItem( "Afegir..." );
  }

  public void drawAules() {

    aula.setResizable(false);
    try {
      aula.setIconImage(ImageIO.read(new File("img/magic-lamp.png")));
    }catch(IOException e){

    }
    aula.setContentPane(this.Panel);
    aula.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    centerWindow();
    aula.pack();
    aula.setVisible(true);
  }

  private void centerWindow() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int x = (screenSize.width - 450) / 2;
    int y = (screenSize.height - 270) / 2;
    aula.setLocation(x, y);
  }
}
