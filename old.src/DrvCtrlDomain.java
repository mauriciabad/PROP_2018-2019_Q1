// import presentation.CtrlUI;
import java.util.*;
import domain.*;
import domain.controllers.*;

public class DrvCtrlDomain {

  public static void main (String [] args){

    Scanner in = new Scanner(System.in);
    System.out.println("     -  Welcome to Schedule Genie  -");

    System.out.println("\nInsert the campus's name:");
    String campus = in.nextLine();
    // String campus = "Real";

    System.out.println("\nInsert the curriculum's name:");
    String curriculum = in.nextLine();
    // String curriculum = "GEI2010";

    System.out.println("\nInsert the schedule's name:");
    String scheduleName = in.nextLine();
    // String scheduleName = "Test";
    
    CtrlDomain domain = CtrlDomain.getInstance();
    // domain.initCtrlDomain("Nord", "GEI2010");
    domain.initCtrlDomain(campus, curriculum);

    // System.out.println("\nGenerating schedule " + scheduleName + " for campus " + campus + " and curriculum " + curriculum);
    domain.generateSchedule(scheduleName);

  }
}