package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.YES_OPTION;

public class PlaEstudis {

    CtrlUI ui = new CtrlUI();

    private JFrame plaEst=new JFrame("Gestionar Pla d'estudis");//creating instance of JFrame
    private JComboBox comboBox1;
    private JButton esborrarPlaDEstudisButton;
    private JButton novaMateriaButton;
    private JTextField idField;
    private JTextField coField;
    private JTextField nomField;
    private JTextField preField;
    private JTextField nivellField;
    private JTextField estudiantsField;
    private JTextField sesiofield;
    private JTextField materialsfield;
    private JTextField midagrupfield;
    private JTextField duraciofield;
    private JTextField subgrupfield;
    private JTextField precoField;
    private JPanel Panel;
    private JRadioButton problemesRadioButton;
    private JRadioButton teoriaRadioButton1;
    private JRadioButton laboratoriRadioButton;
    private JButton guardarmateriaButton;
    private JButton esborrarButton;
    private JButton guardarclaseButton;
    private JList list1;
  private JButton esborrarsesiobutton;
  private JButton backbtn;
  private JSONObject jsonestudis;


    public PlaEstudis(){

        list1.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list1.setVisibleRowCount(-1);

        listPlaEstudis();
        firstloadlist();

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String data = (String) comboBox1.getSelectedItem();
                //Volem crear un nou pla d'estudis
                if(data.equals("Afegir...")){
                    String str = JOptionPane.showInputDialog(null, "Insereix nom per el nou campus: ",
                            "Nou Campus", JOptionPane.INFORMATION_MESSAGE);
                    if(str != null) {
                      int pos = comboBox1.getSelectedIndex();
                      comboBox1.insertItemAt(str,pos);
                      comboBox1.setSelectedItem(str);
                      String[] estudis = new String[0];
                      list1.setListData(estudis);
                      jsonestudis = new JSONObject();
                      jsonestudis.put("name", str);
                      JSONArray array = new JSONArray();
                      jsonestudis.put("subjects", array);
                    }
                }else{
                    try {
                      String dataestudis = ui.getCurriculum(data);
                      jsonestudis = new JSONObject(dataestudis);
                      JSONArray arr = jsonestudis.getJSONArray("subjects");
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
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                  String selectedItem = (String) list1.getSelectedValue();
                  JSONArray arr = jsonestudis.getJSONArray("subjects");
                  for (int i = 0; i < arr.length(); i++) {
                    if ((arr.getJSONObject(i).getString("name")).equals(selectedItem)) {
                      idField.setText(arr.getJSONObject(i).getString("name"));
                      nomField.setText(arr.getJSONObject(i).getString("longName"));
                      nivellField.setText(String.valueOf(arr.getJSONObject(i).getInt("level")));
                      estudiantsField.setText(String.valueOf(arr.getJSONObject(i).getInt("students")));

                      JSONArray prearr = arr.getJSONObject(i).getJSONArray("preRequisit");
                      JSONArray coarr = arr.getJSONObject(i).getJSONArray("coRequisit");
                      JSONArray precoarr = arr.getJSONObject(i).getJSONArray("preCoRequisit");
                      String data = "";
                      for (int j = 0; j < prearr.length(); j++) {
                        data = data + prearr.get(j);
                        data = data + ",";
                      }
                      if (data.length() > 0) data = data.substring(0, data.length() - 1);
                      preField.setText(data);

                      data = "";
                      for (int j = 0; j < coarr.length(); j++) {
                        data = data + coarr.get(j);
                        data = data + ",";
                      }
                      if (data.length() > 0) data = data.substring(0, data.length() - 1);
                      coField.setText(data);

                      data = "";
                      for (int j = 0; j < precoarr.length(); j++) {
                        data = data + precoarr.get(j);
                        data = data + ",";
                      }
                      if (data.length() > 0) data = data.substring(0, data.length() - 1);
                      precoField.setText(data);

                      sesiofield.setText("");
                      subgrupfield.setText("");
                      duraciofield.setText("");
                      midagrupfield.setText("");
                      materialsfield.setText("");

                      teoriaRadioButton1.setSelected(false);
                      laboratoriRadioButton.setSelected(false);
                      problemesRadioButton.setSelected(false);

                      if(teoriaRadioButton1.isSelected()){
                        JSONArray clasarr = arr.getJSONObject(i).getJSONArray("classTypes");
                        for (int j = 0; j < clasarr.length(); j++){
                          if(clasarr.getJSONObject(j).getString("type").equals("Teoria")){
                            sesiofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("weeklySessions")));
                            duraciofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("duration")));
                            subgrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("subgroup")));
                            midagrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("groupSize")));
                            JSONArray matarr = clasarr.getJSONObject(j).getJSONArray("materials");
                            String material = "";
                            for (int z = 0; z < matarr.length(); z++) {
                              material = material + (String) matarr.get(z);
                              material = material + ",";
                            }
                            if(material.length()>0)material = material.substring(0, material.length() - 1);
                            materialsfield.setText(material);
                            break;
                          }
                        }
                      }else if(problemesRadioButton.isSelected()){
                        JSONArray clasarr = arr.getJSONObject(i).getJSONArray("classTypes");
                        for (int j = 0; j < clasarr.length(); j++){
                          if(clasarr.getJSONObject(j).getString("type").equals("Problemes")){
                            sesiofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("weeklySessions")));
                            duraciofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("duration")));
                            subgrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("subgroup")));
                            midagrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("groupSize")));
                            JSONArray matarr = clasarr.getJSONObject(j).getJSONArray("materials");
                            String material = "";
                            for (int z = 0; z < matarr.length(); z++) {
                              material = material + (String) matarr.get(z);
                              material = material + ",";
                            }
                            if(material.length()>0)material = material.substring(0, material.length() - 1);
                            materialsfield.setText(material);
                            break;
                          }
                        }
                      }else if(laboratoriRadioButton.isSelected()){
                        JSONArray clasarr = arr.getJSONObject(i).getJSONArray("classTypes");
                        for (int j = 0; j < clasarr.length(); j++){
                          if(clasarr.getJSONObject(j).getString("type").equals("Laboratori")){
                            sesiofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("weeklySessions")));
                            duraciofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("duration")));
                            subgrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("subgroup")));
                            midagrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("groupSize")));
                            JSONArray matarr = clasarr.getJSONObject(j).getJSONArray("materials");
                            String material = "";
                            for (int z = 0; z < matarr.length(); z++) {
                              material = material + (String) matarr.get(z);
                              material = material + ",";
                            }
                            if(material.length()>0)material = material.substring(0, material.length() - 1);
                            materialsfield.setText(material);
                            break;
                          }
                        }
                      }
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
        novaMateriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                idField.setText("");
                nomField.setText("");
                nivellField.setText("");
                estudiantsField.setText("");
                preField.setText("");
                coField.setText("");
                precoField.setText("");
                sesiofield.setText("");
                duraciofield.setText("");
                subgrupfield.setText("");
                materialsfield.setText("");
                midagrupfield.setText("");

                JSONArray arr = jsonestudis.getJSONArray("subjects");
                String[] data = new String[arr.length()+1];
                for (int i = 0; i < arr.length(); i++) {
                    data[i] = (arr.getJSONObject(i).getString("name"));
                }
                data[arr.length()] = "Nova";
                list1.setListData(data);
                list1.setSelectedValue("Nova",false);
            }
        });

        teoriaRadioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JSONArray arr = jsonestudis.getJSONArray("subjects");
                for (int i = 0; i < arr.length(); i++) {
                    String data = (arr.getJSONObject(i).getString("name"));
                    if(data.equals(list1.getSelectedValue())){
                        JSONArray clasarr = arr.getJSONObject(i).getJSONArray("classTypes");
                        for (int j = 0; j < clasarr.length(); j++){
                            if(clasarr.getJSONObject(j).getString("type").equals("Teoria")){
                                sesiofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("weeklySessions")));
                                duraciofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("duration")));
                                subgrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("subgroup")));
                                midagrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("groupSize")));
                                JSONArray matarr = clasarr.getJSONObject(j).getJSONArray("materials");
                                String material = "";
                                for (int z = 0; z < matarr.length(); z++) {
                                    material = material + (String) matarr.get(z);
                                    material = material + ",";
                                }
                                if(material.length()>0)material = material.substring(0, material.length() - 1);
                                materialsfield.setText(material);
                                return;
                            }
                        }
                    }
                }
                sesiofield.setText("");
                subgrupfield.setText("");
                duraciofield.setText("");
                midagrupfield.setText("");
                materialsfield.setText("");
            }
        });

        laboratoriRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JSONArray arr = jsonestudis.getJSONArray("subjects");
                for (int i = 0; i < arr.length(); i++) {
                    String data = (arr.getJSONObject(i).getString("name"));
                    if(data.equals(list1.getSelectedValue())){
                        JSONArray clasarr = arr.getJSONObject(i).getJSONArray("classTypes");
                        for (int j = 0; j < clasarr.length(); j++){
                            if(clasarr.getJSONObject(j).getString("type").equals("Laboratori")){
                                sesiofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("weeklySessions")));
                                duraciofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("duration")));
                                subgrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("subgroup")));
                                midagrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("groupSize")));
                                JSONArray matarr = clasarr.getJSONObject(j).getJSONArray("materials");
                                String material = "";
                                for (int z = 0; z < matarr.length(); z++) {
                                    material = material + (String) matarr.get(z);
                                    material = material + ",";
                                }
                                if(material.length()>0)material = material.substring(0, material.length() - 1);
                                materialsfield.setText(material);
                                return;
                            }
                        }
                    }
                }
                sesiofield.setText("");
                subgrupfield.setText("");
                duraciofield.setText("");
                midagrupfield.setText("");
                materialsfield.setText("");
            }
        });

        problemesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JSONArray arr = jsonestudis.getJSONArray("subjects");
                for (int i = 0; i < arr.length(); i++) {
                    String data = (arr.getJSONObject(i).getString("name"));
                    if(data.equals(list1.getSelectedValue())){
                        JSONArray clasarr = arr.getJSONObject(i).getJSONArray("classTypes");
                        for (int j = 0; j < clasarr.length(); j++){
                            if(clasarr.getJSONObject(j).getString("type").equals("Problemes")){
                                sesiofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("weeklySessions")));
                                duraciofield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("duration")));
                                subgrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("subgroup")));
                                midagrupfield.setText(String.valueOf(clasarr.getJSONObject(j).getInt("groupSize")));
                                JSONArray matarr = clasarr.getJSONObject(j).getJSONArray("materials");
                                String material = "";
                                for (int z = 0; z < matarr.length(); z++) {
                                    material = material + (String) matarr.get(z);
                                    material = material + ",";
                                }
                                if(material.length()>0)material = material.substring(0, material.length() - 1);
                                materialsfield.setText(material);
                                return;
                            }
                        }
                    }
                }
                sesiofield.setText("");
                subgrupfield.setText("");
                duraciofield.setText("");
                midagrupfield.setText("");
                materialsfield.setText("");
            }
        });


      guardarmateriaButton.addActionListener(new ActionListener() {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
          try {
            JSONArray arr = jsonestudis.getJSONArray("subjects");
            if(idField.getText().equals("") || nomField.getText().equals("") ||
                    nivellField.getText().equals("") || estudiantsField.getText().equals("")){
              JOptionPane.showMessageDialog(null,
                      "Es necesari omplir tots els camps indicats",
                      "Error",
                      JOptionPane.WARNING_MESSAGE);
              return;
            }
            if (list1.getSelectedValue().equals("Nova")) {
              JSONObject obj = new JSONObject();

              String data = preField.getText();
              String[] prearray = (data.split(","));
              JSONArray prejson = new JSONArray();
              for (int i = 0; i < prearray.length; ++i) {
                prejson.put(prearray[i]);
              }
              obj.put("preRequisit", prejson);

              data = precoField.getText();
              String[] precoarry = (data.split(","));
              JSONArray precojson = new JSONArray();
              for (int i = 0; i < precoarry.length; ++i) {
                precojson.put(precoarry[i]);
              }
              obj.put("preCoRequisit", precojson);

              data = coField.getText();
              String[] coarr = (data.split(","));
              JSONArray cojson = new JSONArray();
              for (int i = 0; i < coarr.length; ++i) {
                cojson.put(coarr[i]);
              }
              obj.put("coRequisit", cojson);

              JSONArray clases = new JSONArray();
              String sesio = sesiofield.getText();

              if (!sesio.isEmpty() && !sesio.equals("0")) { //es pot crear la clase amb una sesio existent
                JSONObject jobj = new JSONObject();
                if(duraciofield.getText().equals("") || subgrupfield.getText().equals("") || midagrupfield.getText().equals("")){
                  JOptionPane.showMessageDialog(null,
                          "Es necesari omplir tots els camps indicats",
                          "Error",
                          JOptionPane.WARNING_MESSAGE);
                  return;
                }
                if (teoriaRadioButton1.isSelected()) {
                  jobj.put("type", "Teoria");
                } else if (problemesRadioButton.isSelected()) {
                  jobj.put("type", "Problemes");
                } else if (laboratoriRadioButton.isSelected()) {
                  jobj.put("type", "Laboratori");
                }
                try {
                  jobj.put("weeklySessions", Integer.parseInt(sesiofield.getText()));
                }catch(NumberFormatException ex){
                  JOptionPane.showMessageDialog(null,
                          "El camp Sesio requereix un valor numeric",
                          "Error",
                          JOptionPane.WARNING_MESSAGE);
                  return;
                }
                try{
                  jobj.put("duration", Integer.parseInt(duraciofield.getText()));
                }catch(NumberFormatException ex){
                  JOptionPane.showMessageDialog(null,
                          "El camp Duracio requereix un valor numeric",
                          "Error",
                          JOptionPane.WARNING_MESSAGE);
                  return;
                }
                try{
                  jobj.put("subgroup", Integer.parseInt(subgrupfield.getText()));
                }catch(NumberFormatException ex){
                  JOptionPane.showMessageDialog(null,
                          "El camp Subgrup requereix un valor numeric",
                          "Error",
                          JOptionPane.WARNING_MESSAGE);
                  return;
                }
                try{
                  jobj.put("groupSize", Integer.parseInt(midagrupfield.getText()));
                }catch(NumberFormatException ex){
                  JOptionPane.showMessageDialog(null,
                          "El camp Mida Grups requereix un valor numeric",
                          "Error",
                          JOptionPane.WARNING_MESSAGE);
                  return;
                }
                String mat = materialsfield.getText();
                //Construim Array JSON de Materials
                String[] materialsarray = (mat.split(","));
                JSONArray materialsjson = new JSONArray();
                for (int z = 0; z < materialsarray.length; ++z) {
                  materialsjson.put(materialsarray[z]);
                }
                jobj.put("materials", materialsjson);
                clases.put(0, jobj);
              }

              obj.put("classTypes", clases);
              obj.put("name", idField.getText());
              obj.put("longName", nomField.getText());
              try {
                obj.put("level", Integer.parseInt(nivellField.getText()));
              }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,
                        "El camp Nivell requereix un valor numeric",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
              }
              try{
                obj.put("students", Integer.parseInt(estudiantsField.getText()));
              }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,
                        "El camp Estudiants requereix un valor numeric",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
              }

              arr.put(arr.length(), obj);
              //jsonestudis.put("classTypes", arr);
            } else {
              String materia = (String) list1.getSelectedValue();
              JSONArray newarr = new JSONArray();
              for (int i = 0; i < arr.length(); ++i) {
                JSONObject jsob = arr.getJSONObject(i);
                if (jsob.getString("name").equals(list1.getSelectedValue())) {
                  newarr.put(i, arr.get(i));

                  String data = preField.getText();
                  String[] prearray = (data.split(","));
                  JSONArray prejson = new JSONArray();
                  for (int j = 0; j < prearray.length; ++j) {
                    prejson.put(prearray[j]);
                  }
                  jsob.put("preRequisit", prejson);

                  data = precoField.getText();
                  String[] precoarry = (data.split(","));
                  JSONArray precojson = new JSONArray();
                  for (int j = 0; j < precoarry.length; ++j) {
                    precojson.put(precoarry[j]);
                  }
                  jsob.put("preCoRequisit", precojson);

                  data = coField.getText();
                  String[] coarr = (data.split(","));
                  JSONArray cojson = new JSONArray();
                  for (int j = 0; j < coarr.length; ++j) {
                    cojson.put(coarr[j]);
                  }
                  jsob.put("coRequisit", cojson);


                  JSONArray clasarr = arr.getJSONObject(i).getJSONArray("classTypes");
                  JSONArray newclasarr = new JSONArray();
                  boolean create = false;
                  int j;
                  for (j = 0; j < clasarr.length(); j++) {
                    String sesio = sesiofield.getText();
                    if ((teoriaRadioButton1.isSelected() && clasarr.getJSONObject(j).getString("type").equals("Teoria")) ||
                            (problemesRadioButton.isSelected() && clasarr.getJSONObject(j).getString("type").equals("Problemes")) ||
                            (laboratoriRadioButton.isSelected() && clasarr.getJSONObject(j).getString("type").equals("Laboratori"))) {
                      JSONObject jobj = new JSONObject();
                      if (!sesio.isEmpty() && !sesio.equals("0")) { //No es vol eliminar la sesio
                        jobj.put("type", clasarr.getJSONObject(j).getString("type"));
                        try {
                          jobj.put("weeklySessions", Integer.parseInt(sesiofield.getText()));
                        }catch(NumberFormatException ex){
                          JOptionPane.showMessageDialog(null,
                                  "El camp Sesio requereix un valor numeric",
                                  "Error",
                                  JOptionPane.WARNING_MESSAGE);
                          return;
                        }
                        try{
                          jobj.put("duration", Integer.parseInt(duraciofield.getText()));
                        }catch(NumberFormatException ex){
                          JOptionPane.showMessageDialog(null,
                                  "El camp Duracio requereix un valor numeric",
                                  "Error",
                                  JOptionPane.WARNING_MESSAGE);
                          return;
                        }
                        try{
                          jobj.put("subgroup", Integer.parseInt(subgrupfield.getText()));
                        }catch(NumberFormatException ex){
                          JOptionPane.showMessageDialog(null,
                                  "El camp Subgrup requereix un valor numeric",
                                  "Error",
                                  JOptionPane.WARNING_MESSAGE);
                          return;
                        }
                        try{
                          jobj.put("groupSize", Integer.parseInt(midagrupfield.getText()));
                        }catch(NumberFormatException ex){
                          JOptionPane.showMessageDialog(null,
                                  "El camp Mida Grups requereix un valor numeric",
                                  "Error",
                                  JOptionPane.WARNING_MESSAGE);
                          return;
                        }
                        String mat = materialsfield.getText();
                        //Construim Array JSON de Materials
                        String[] materialsarray = (mat.split(","));
                        JSONArray materialsjson = new JSONArray();
                        for (int z = 0; z < materialsarray.length; ++z) {
                          materialsjson.put(materialsarray[z]);
                        }
                        jobj.put("materials", materialsjson);
                        newclasarr.put(j, jobj);

                      }
                      create = true;
                    } else {
                      newclasarr.put(j, clasarr.get(j));
                    }
                  }
                  if (!create && (teoriaRadioButton1.isSelected() || laboratoriRadioButton.isSelected() || problemesRadioButton.isSelected())) {
                    JSONObject jobj = new JSONObject();
                    if (teoriaRadioButton1.isSelected()) {
                      jobj.put("type", "Teoria");
                    } else if (problemesRadioButton.isSelected()) {
                      jobj.put("type", "Problemes");
                    } else if (laboratoriRadioButton.isSelected()) {
                      jobj.put("type", "Laboratori");
                    }

                    try {
                      jobj.put("weeklySessions", Integer.parseInt(sesiofield.getText()));
                    }catch(NumberFormatException ex){
                      JOptionPane.showMessageDialog(null,
                              "El camp Sesio requereix un valor numeric",
                              "Error",
                              JOptionPane.WARNING_MESSAGE);
                      return;
                    }
                    try{
                      jobj.put("duration", Integer.parseInt(duraciofield.getText()));
                    }catch(NumberFormatException ex){
                      JOptionPane.showMessageDialog(null,
                              "El camp Duracio requereix un valor numeric",
                              "Error",
                              JOptionPane.WARNING_MESSAGE);
                      return;
                    }
                    try{
                      jobj.put("subgroup", Integer.parseInt(subgrupfield.getText()));
                    }catch(NumberFormatException ex){
                      JOptionPane.showMessageDialog(null,
                              "El camp Subgrup requereix un valor numeric",
                              "Error",
                              JOptionPane.WARNING_MESSAGE);
                      return;
                    }
                    try{
                      jobj.put("groupSize", Integer.parseInt(midagrupfield.getText()));
                    }catch(NumberFormatException ex){
                      JOptionPane.showMessageDialog(null,
                              "El camp Mida Grups requereix un valor numeric",
                              "Error",
                              JOptionPane.WARNING_MESSAGE);
                      return;
                    }
                    String mat = materialsfield.getText();
                    //Construim Array JSON de Materials
                    String[] materialsarray = (mat.split(","));
                    JSONArray materialsjson = new JSONArray();
                    for (int z = 0; z < materialsarray.length; ++z) {
                      materialsjson.put(materialsarray[z]);
                    }
                    jobj.put("materials", materialsjson);
                    newclasarr.put(j, jobj);
                    create = true;
                  }

                  jsob.put("name", idField.getText());
                  jsob.put("longName", nomField.getText());
                  try {
                    jsob.put("level", Integer.parseInt(nivellField.getText()));
                  }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,
                            "El camp Nivell requereix un valor numeric",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                  }
                  try{
                    jsob.put("students", Integer.parseInt(estudiantsField.getText()));
                  }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,
                            "El camp Estudiants requereix un valor numeric",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                  }
                  jsob.put("classTypes", newclasarr);
                  newarr.put(i, jsob);
                } else {
                  newarr.put(i, arr.get(i));
                }
              }
              //jsonestudis.put("classTypes", newarr);
            }
            arr = jsonestudis.getJSONArray("subjects");
            ui.setCurriculum((String) comboBox1.getSelectedItem(), jsonestudis.toString(2));
            drawlist(arr);
          }catch (NullPointerException ex) {
            if (list1.isSelectionEmpty()) {
              JOptionPane.showMessageDialog(null,
                      "Es necesari indicar un valor en la llista de Asignatures",
                      "Error",
                      JOptionPane.WARNING_MESSAGE);
            } else {
              JOptionPane.showMessageDialog(null,
                      "Error al inseri les dades",
                      "Error",
                      JOptionPane.WARNING_MESSAGE);
            }
          }
        }
      });
      esborrarButton.addActionListener(new ActionListener() {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
          try{
            JSONArray arr = jsonestudis.getJSONArray("subjects");
            String clase = (String)list1.getSelectedValue();

            JSONArray newarr = new JSONArray();
            boolean eliminat = false;
            for (int i = 0; i < arr.length(); ++i) {
              JSONObject jsob = arr.getJSONObject(i);
              if (!jsob.getString("name").equals(clase)) {
                if (eliminat) {
                  newarr.put(i - 1, arr.get(i));
                } else {
                  newarr.put(i, arr.get(i));
                }
              } else {
                eliminat = true;
              }
            }
            jsonestudis.put("subjects", newarr);

            arr = jsonestudis.getJSONArray("subjects");
            ui.setCurriculum((String) comboBox1.getSelectedItem(), jsonestudis.toString(2));
            drawlist(arr);
          }
          catch(NullPointerException ex){
            JOptionPane.showMessageDialog(null,
                    "Sense dades per esborrar",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
          }
        }
      });
      esborrarPlaDEstudisButton.addActionListener(new ActionListener() {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
          int n = JOptionPane.showConfirmDialog(
                  null,
                  "Està segur que desitja eliminar al Pla d'Estudis?",
                  "Requereix confirmació",
                  JOptionPane.WARNING_MESSAGE,
                  JOptionPane.YES_NO_OPTION);
          if(n==YES_OPTION) {
            if(comboBox1.getSelectedItem().equals("Afegir...")){
              JOptionPane.showMessageDialog(null,
                      "No es pot esborrar el pla d'estudis",
                      "Error",
                      JOptionPane.WARNING_MESSAGE);
            }else {
              int index = comboBox1.getSelectedIndex();
              String data = (String)comboBox1.getSelectedItem();
              comboBox1.setSelectedIndex(1);
              comboBox1.removeItemAt(index);
              list1.setListData(new String[0]);
              ui.deleteCurriculum(data);
            }
          }
        }
      });
      esborrarsesiobutton.addActionListener(new ActionListener() {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
          JSONArray arr = jsonestudis.getJSONArray("subjects");
          JSONArray newarr = new JSONArray();
          for (int i = 0; i < arr.length(); i++) {
            String data = (arr.getJSONObject(i).getString("name"));
            if (data.equals(list1.getSelectedValue())) {
              JSONObject jso = arr.getJSONObject(i);
              JSONArray sesionsarr = arr.getJSONObject(i).getJSONArray("classTypes");
              JSONArray newsesarr = new JSONArray();
              boolean esborrat = false;
              for(int j = 0; j < sesionsarr.length();++j) {
                if ((teoriaRadioButton1.isSelected() && sesionsarr.getJSONObject(j).getString("type").equals("Teoria")) ||
                        (problemesRadioButton.isSelected() && sesionsarr.getJSONObject(j).getString("type").equals("Problemes")) ||
                        (laboratoriRadioButton.isSelected() && sesionsarr.getJSONObject(j).getString("type").equals("Laboratori"))) {
                  esborrat = true;
                }else {
                  if (esborrat) {
                    newsesarr.put(j - 1, sesionsarr.get(j));
                  } else {
                    newsesarr.put(j, sesionsarr.get(j));
                  }
                }
              }
              //
              jso.put("classTypes",newsesarr);
              newarr.put(i,jso);
            }else{
              newarr.put(i,arr.get(i));
            }
          }
          jsonestudis.put("subjects",newarr);
          arr =jsonestudis.getJSONArray("subjects");
          ui.setCurriculum((String) comboBox1.getSelectedItem(),jsonestudis.toString(2));
          drawlist(arr);


      }
      });
      backbtn.addActionListener(new ActionListener() {
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
          plaEst.dispose();
          MainMenu.drawMenu();
        }
      });
    }

  private void firstloadlist() {
    comboBox1.setSelectedIndex(0);
    String data = (String) comboBox1.getSelectedItem();
    if(data.equals("Afegir...")) return;
    try {
      String dataestudis = ui.getCurriculum(data);
      jsonestudis = new JSONObject(dataestudis);
      JSONArray arr = jsonestudis.getJSONArray("subjects");
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
        String[] data = new String[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            data[i] = (arr.getJSONObject(i).getString("name"));
        }
        list1.setListData(data);
    }

    private void listPlaEstudis() {
        List<String> estudislst = ui.getAllCurriculums();
        for(String estudis : estudislst) {
            estudis = estudis.replace(".json", "");
            comboBox1.addItem(estudis);
        }
        comboBox1.addItem( "Afegir..." );
    }

    public void drawPlaEstudis() {
      plaEst.setResizable(false);
      try {
          plaEst.setIconImage(ImageIO.read(new File("img/magic-lamp.png")));
      }catch(IOException e){

      }
      plaEst.setContentPane(this.Panel);
      plaEst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      centerWindow();

      plaEst.pack();
      plaEst.setVisible(true);


    }

  private void centerWindow() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int x = (screenSize.width - 495) / 2;
    int y = (screenSize.height - 475) / 2;
    plaEst.setLocation(x, y);
  }
}
