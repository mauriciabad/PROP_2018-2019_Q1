/** \class DrvSchedule
    \fileDrvSchedules.java
    \brief Codi del driver de la clase Schedule
    \author Raul Main
 */

package domain.drivers;
import java.util.*;
import domain.Schedule;
import domain.Session;


public class DrvSchedule {
  private static void printhorari(List<Session> horari){
    System.out.println(horari);
  }

    public static void generateSchedule(){
      Schedule s = new Schedule("Test");
      boolean aux = s.generateSchedule();
      if(aux){
        System.out.println("1 Solucio trobada y horari generat");
        List<Session> horari = s.getSchedule();
        printhorari(horari);
      }else{
        System.out.println("0 Solucio no trobada");
      }
    }

	private static void getSchedule() {
		Schedule s = new Schedule("Test");
		List<Session> horari = s.getSchedule();
        printhorari(horari);
	}
    public static void main (String [] args){
      int out = 0;
      while(out == 0){
        System.out.println("Schedule Class Test");
        System.out.println("1) getSchedule");
        System.out.println("2) generateSchedule");
        System.out.println("3) exit");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();

        switch (i) {
          case 1: getSchedule();
                  break;
          case 2: generateSchedule();
                  break;
          case 3: out = 1;
                  s.close();
                  break;
        }
      }
    }

}
