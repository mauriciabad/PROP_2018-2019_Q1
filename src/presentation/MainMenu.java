package presentation;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class MainMenu {
  private JButton crearButton;
  private JPanel Panel;
  private JButton obrirButton;
  private JButton plaEstudisButton;
  private JButton aulaButton;

  CtrlUI ui = new CtrlUI();

  private static JFrame f=new JFrame("Schedule Genie");//creating instance of JFrame

  public MainMenu() {
    crearButton.addActionListener(new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param actionEvent the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        f.setVisible(false);
        CreateSchedule a = new CreateSchedule();
        a.drawFrame();
      }
    });
    obrirButton.addActionListener(new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param actionEvent the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
//                List<String> campuslst = ui.getAllCampus();
//                Object[] campusarray = new Object[campuslst.size()];
//                int i = 0;
//                for(String campus : campuslst) {
//                    campusarray[i] = campus.replace(".json", "");
//                    ++i;
//                }
//
//                String s = (String)JOptionPane.showInputDialog(
//                        null,
//                        "Quin horari desitje obrir?",
//                        "Obrir Horari",
//                        JOptionPane.PLAIN_MESSAGE,
//                        null,
//                        campusarray,
//                        campusarray[0]);
//
        f.setVisible(false);
        PreviewSchedule o = new PreviewSchedule();
        o.drawFrame();
      }
    });
    aulaButton.addActionListener(new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param actionEvent the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        f.dispose();
        Aules a = new Aules();
        a.drawAules();
      }
    });
    plaEstudisButton.addActionListener(new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param actionEvent the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        f.setVisible(false);
        PlaEstudis p = new PlaEstudis();
        p.drawPlaEstudis();
      }
    });
  }

  public static void drawMenu() {
    f.setResizable(false);
    try {
      f.setIconImage(ImageIO.read(new File("img/magic-lamp.png")));
    }catch(IOException e){}
    f.setContentPane(new MainMenu().Panel);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    centerWindow();
    f.pack();
    f.setVisible(true);

  }

  private static void centerWindow() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();
    int x = (screenSize.width - 450) / 2;
    int y = (screenSize.height - 350) / 2;
    f.setLocation(x, y);
  }

  public static void main(String[] args) {
    drawMenu();
  }
}
