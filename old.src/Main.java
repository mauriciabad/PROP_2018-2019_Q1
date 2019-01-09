// import presentation.CtrlUI;
import java.util.*;
import domain.*;
import domain.controllers.*;

public class Main {

  // private static CtrlUI ui = new CtrlUI();
  // public static void main (String[] args) throws Exception { 
  //   ui.setup();
  //   ui.showUI();
  // }
  public static void main (String [] args){

    Scanner in = new Scanner(System.in);
    System.out.println("     -  Welcome to Schedule Genie  -");
    System.out.println("");

    // System.out.printf("Insert the campus name: \n");
    // String campus = in.nextLine();
    String campus = "Real";

    // System.out.printf("Insert the curriculum name: \n");
    // String curriculum = in.nextLine();
    String curriculum = "GEI2010";

    // System.out.printf("Insert the schedule name: \n");
    // String scheduleName = in.nextLine();
    String scheduleName = "Test";
    
    CtrlDomain domain = CtrlDomain.getInstance();
    // domain.initCtrlDomain("Nord", "GEI2010");
    domain.initCtrlDomain(campus, curriculum);

    System.out.println("Generating schedule for campus " + campus + " and curriculum " + curriculum);
    domain.generateSchedule(scheduleName);


    // System.out.printf("Enter a number to choose what you want to test: \n");
    // System.out.printf("1- Test getName() \n");
    // System.out.printf("2- Test getCapacity() \n");
    // System.out.printf("3- Test getMaterials() \n");
    // System.out.printf("4- Test hasMaterials() \n");
    // int num = in.nextInt();

    // while(true){
    //     if(num>0 && num<=4){
    //         if (num==1) testGetName(classroom);
    //         else if (num==2) testGetCapacity(classroom);
    //         else if (num==3) testGetMaterials(classroom);
    //         else if (num==4) testHasMaterial(classroom);
    //     }
    //     else System.out.println("Error! Invalid number.");

    //     num = in.nextInt();
    // }

}
}