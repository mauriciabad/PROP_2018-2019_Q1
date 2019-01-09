/** \class DrvBinaryRestrictions
    \file DrvBinaryRestrictions.java
    \brief Codi del driver de la clase RestrictionBinary
    \author Raul Main
 */
package domain.drivers;

import java.util.*;

import domain.ClassType;
import domain.Classroom;
import domain.RestBCoRequisit;
import domain.RestBLevelOverlapping;
import domain.RestBLevelCompact;
import domain.RestBNearClassroom;
import domain.RestBSameTypeOverlapping;
import domain.Session;
import domain.Subject;

public class DrvBinaryRestrictions {

    public static void main (String [] args){
      int out = 0;
      //testnearclassroomjdb();
      while(out == 0){
        System.out.println("Schedule Class Test");
        System.out.println("1) Check Overlaping");
        System.out.println("2) Check Near Classroom");
        System.out.println("3) Check Level");
        System.out.println("4) Check Corequisit");
        System.out.println("5) Check LevelCompact");
        System.out.println("6) exit");
        Scanner s = new Scanner(System.in);
        int i = s.nextInt(); s.nextLine(); 

        switch (i) {
          case 1: testOverlaping();
                  break;
          case 2: testnearclassroom();
                  break;
          case 3: testlevel();
          break;
          case 4: testcoreq();
          break;
          case 5: testlevelCompact();
          break;
          case 6: out = 1;
                  break;
        }
      }
    }

	private static void testcoreq() {
    RestBCoRequisit restriccio = new RestBCoRequisit();
    System.out.println("Insert the Subject name and type of class two times for previous schedule");
    Classroom c = new Classroom("A1103",100);
    List<Session> schedule = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    for(int i = 0; i < 2; ++i){
      String name = sc.nextLine(); 
      Subject subject = new Subject(name,"Long Name",1,100, null, null, null);
      String type = sc.nextLine(); 
      ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);
      Session session = new Session(classType, 1, "Test");
      schedule.add(session);
      System.out.println("----");
    }

    System.out.println("Insert the Subject level and name and type of class");
    String name = sc.nextLine(); 
    Subject subject = new Subject(name,"Long Name",1,100, null, null, null);
    String type = sc.nextLine(); 
    ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);


    Session session = new Session(classType, 1, "Test");
    int result = restriccio.check(schedule,session,1,1,c);

    System.out.println("Resultat: " + result);
	}

	private static void testlevel() {
    RestBLevelOverlapping restriccio = new RestBLevelOverlapping();
    System.out.println("Insert the Subject level and name and type of class two times for previous schedule");
    Classroom c = new Classroom("A1103",100);
    List<Session> schedule = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    for(int i = 0; i < 2; ++i){
      int level = sc.nextInt();
      sc.nextLine();
      String name = sc.nextLine(); 
      Subject subject = new Subject("N","Long Name",level,100, null, null, null);
      String type = sc.nextLine(); 
      ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);
      Session session = new Session(classType, 1, "Test");
      schedule.add(session);
      System.out.println("----");
    }

    System.out.println("Insert the Subject level and name and type of class");
    int level = sc.nextInt();
    sc.nextLine();
    String name = sc.nextLine(); 
    Subject subject = new Subject(name,"Long Name",level,100, null, null, null);
    String type = sc.nextLine(); 
    ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);


    Session session = new Session(classType, 1, "Test");
    int result = restriccio.check(schedule,session,1,1,c);

    System.out.println("Resultat: " + result);
	}

	private static void testlevelCompact() {
    RestBLevelCompact restriccio = new RestBLevelCompact();
    System.out.println("Insert the Subject level, name, type of class and hour two times for previous schedule");
    Classroom c = new Classroom("A1103",100);
    List<Session> schedule = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    for(int i = 0; i < 2; ++i){
      int level = sc.nextInt();
      sc.nextLine();
      String name = sc.nextLine(); 
      Subject subject = new Subject("N","Long Name",level,100, null, null, null);
      String type = sc.nextLine(); 
      ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);
      Session session = new Session(classType, 1, "Test");
      int hour = sc.nextInt();
      sc.nextLine();
			session.setHour(hour);
      schedule.add(session);
      System.out.println("----");
    }

    System.out.println("Insert the Subject level, name, type of class and hour");
    int level = sc.nextInt();
    sc.nextLine();
    String name = sc.nextLine(); 
    Subject subject = new Subject(name,"Long Name",level,100, null, null, null);
    String type = sc.nextLine(); 
    ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);


    Session session = new Session(classType, 1, "Test");
    int hour = sc.nextInt();
    sc.nextLine();
    session.setHour(hour);
    int result = restriccio.check(schedule,session,hour,1,c);

    System.out.println("Resultat: " + result);
	}

	private static void testnearclassroom() {
    RestBNearClassroom restriccio = new RestBNearClassroom();
    
    System.out.println("Insert the Classroom Room, Classroom Floor, Clasroom Building two times for previous schedule");
    List<Session> schedule = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
	for(int i = 0; i < 2; ++i){
      Classroom caux = new Classroom("A1103",100);
      Subject subject = new Subject("N","Long Name",1,100, null, null, null);
      ClassType classType = new ClassType(subject, "T", 1, 1, 1, 1, null);

			int room = sc.nextInt();sc.nextLine();
			caux.setRoom(room);
			int floor = sc.nextInt();sc.nextLine();
			caux.setFloor(floor); 
			String building = sc.nextLine();
			caux.setBuilding(building);

      Session session = new Session(classType, 1, "Test");
			session.setClassroom(caux);
      schedule.add(session);
      System.out.println("----");
    }
		System.out.println("Insert the Classroom Room, Classroom Floor, Clasroom Building to evaluate");

    Classroom c = new Classroom("A1103",100);
		int room = sc.nextInt();sc.nextLine();
		c.setRoom(room);
		int floor = sc.nextInt();sc.nextLine();
		c.setFloor(floor); 
		String building = sc.nextLine();
		c.setBuilding(building);

		Subject subject = new Subject("N","Long Name",1,100, null, null, null);
    ClassType classType = new ClassType(subject, "T", 1, 1, 1, 1, null);
		Session session = new Session(classType, 1, "Test");

		int result = restriccio.check(schedule,session,1,1,c);
    System.out.println("Resultat: " + result);
	}

	private static void testOverlaping() {
    System.out.println("Insert name of the subject and type of class two times for previous schedule");
    RestBSameTypeOverlapping restriccio = new RestBSameTypeOverlapping();
    Classroom c = new Classroom("A1103",100);
    List<Session> schedule = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    for(int i = 0; i < 2; ++i){
      String name = sc.nextLine(); 
      Subject subject = new Subject(name,"Long Name",1,100, null, null, null);
      String type = sc.nextLine(); 
      ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);
      Session session = new Session(classType, 1, "Test");
      schedule.add(session);
      System.out.println("----");
    }
    System.out.println("Insert name of the subject and type of class for check restrictions");
    String name = sc.nextLine(); 
    Subject subject = new Subject(name,"Long Name",1,100, null, null, null);
    String type = sc.nextLine(); 
    ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);
    Session session = new Session(classType, 1, "Test");
    int result = restriccio.check(schedule,session,1,1,c);

    System.out.println("Resultat: " + result);
	}

  
}