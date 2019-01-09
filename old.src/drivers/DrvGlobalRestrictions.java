package domain.drivers;
import java.util.*;

import domain.ClassType;
import domain.Classroom;
import domain.*;
import domain.Session;
import domain.Subject;

public class DrvGlobalRestrictions {
    private static void testWeekPayLoad() {
      RestGWeekPayload restriccio = new RestGWeekPayload();
      System.out.println("Insert the Subject name and type of class two times for previous schedule");
      Classroom c = new Classroom("A6101",100);
      List<Session> schedule = new ArrayList<>();
      Scanner sc = new Scanner(System.in);

      for(int i = 0; i < 2; ++i){
        String name = sc.nextLine(); 
        Subject subject = new Subject(name,"Long Name",1,100, null, null, null);
        String type = sc.nextLine(); 
        ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);
        Session session = new Session(classType, 1, "Teoria");
        schedule.add(session);
      }

      System.out.println("Insert the Subject level, name and type of class");
      String name = sc.nextLine(); 
      Subject subject = new Subject(name,"Long Name",1,100, null, null, null);
      String type = sc.nextLine(); 
      ClassType classType = new ClassType(subject, type, 1, 1, 1, 1, null);


      Session session = new Session(classType, 1, "Teoria");
      int result = restriccio.check(schedule,session,1,1,c);

      System.out.println("Resultat: " + result);
    }

    public static void main (String [] args){
      int out = 0;
      while(out == 0){
        System.out.println("Schedule Class Test");
        System.out.println("1) Check WeekPayLoad");
    
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();

        switch (i) {
          case 1: testWeekPayLoad();
                  break;
        }
      }
    }
}