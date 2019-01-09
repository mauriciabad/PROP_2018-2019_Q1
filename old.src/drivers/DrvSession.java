package domain.drivers;
import java.util.*;
import data.*;
import domain.*;

public class DrvSession {
    public static void testGetSubject(Session session){
        System.out.println(session.getSubject());
    };
    public static void testGetClassroom(Session session){
        System.out.println(session.getClassroom());
    };
    public static void testGetNumber(Session session){
        System.out.println(session.getNumber());
    };
    public static void testGetDuration(Session session){
        System.out.println(session.getDuration());
    };
    public static void testGetClassType(Session session){
        System.out.println(session.getClassType());
    };
    public static void testGetRestsU(Session session){
        System.out.println(session.getRestsU());
    };
    public static void testGetRestsB(Session session){
        System.out.println(session.getRestsB());
    };
    public static void testGetRestsG(Session session){
        System.out.println(session.getRestsG());
    };
    public static void testGetRestThreshold(Session session){
        System.out.println(session.getRestThreshold());
    };
    public static void testGetAllowedClassroomDayHour(Session session){
        System.out.println(session.getAllowedClassroomDayHour());
    };
    public static void testGetGroup(Session session){
        System.out.println(session.getGroup());
    };
    public static void testGetWeekDay(Session session){
        System.out.println(session.getWeekDay());
    };
    public static void testGetHour(Session session){
        System.out.println(session.getHour());
    };
    public static void testInitAllowedClassroomDayHour(Session session){
        session.initAllowedClassroomDayHour();
        System.out.println("Funciona");
    };
    public static void testRemoveUnsatisfactoryCDH(Session session, List<Session> s){
        System.out.println(session.removeUnsatisfactoryCDH(s));
    };
    public static void testAddDefaultRestrictions(Session session){
        session.addDefaultRestrictions();
        System.out.println("Funciona");
    };
    

    
    public static void main (String [] args){
        
        List<String> materials = new ArrayList<>();
        materials.add("mat1");
        materials.add("mat2");

        Scanner in = new Scanner(System.in);

        System.out.printf("Create Subject to Test: \n");
        System.out.printf("Introduce ClassType name: \n");
        String clNameIn = in.nextLine();
        System.out.printf("Introduce Number \n");
        int numberIn = in.nextInt();
        System.out.printf("Introduce Group: \n");
        String intro = in.nextLine();
        String groupIn = in.nextLine();
        
        Subject subject = new Subject(clNameIn, clNameIn, 0, 60, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        ClassType classtype = new ClassType(subject, "Teoria", 1,2,3,20,new ArrayList<>());
        Session session = new Session(classtype, numberIn, groupIn);
        List<Session> schedule = new ArrayList<>();
        schedule.add(session);

        System.out.printf("Enter a number to choose what you want to test: \n");
        System.out.printf("1- Test GetSubject() \n");
        System.out.printf("2- Test GetClassroom() \n");
        System.out.printf("3- Test GetNumber() \n");
        System.out.printf("4- Test GetDuration() \n");
        System.out.printf("5- Test GetClassType() \n");
        System.out.printf("6- Test GetGroup() \n");
        System.out.printf("7- Test GetWeekDay() \n");
        System.out.printf("8- Test GetHour() \n");
        System.out.printf("9- Test GetRestsU() \n");
        System.out.printf("10- Test GetRestsB() \n");
        System.out.printf("11- Test GetRestsG() \n");
        System.out.printf("12- Test GetRestThreshold() \n");
        System.out.printf("13- Test GetAllowedClassroomDayHour() \n");
        System.out.printf("14- Test GetInitAllowedClassroomDayHour() \n");
        System.out.printf("15- Test RemoveUnsatisfactoryCDH() \n");
        System.out.printf("16- Test AddDefaultRestrictions() \n");

        int num = in.nextInt();
        while(true){
            switch (num) {
                case 1: testGetSubject(session);
                        break;
                case 2: testGetClassroom(session);
                        break;
                case 3: testGetNumber(session);
                        break;
                case 4: testGetDuration(session);
                        break;
                case 5: testGetClassType(session);
                        break;
                case 6: testGetGroup(session);
                        break;
                case 7: testGetWeekDay(session);
                        break;
                case 8: testGetHour(session);
                        break;
                case 9: testGetRestsU(session);
                        break;
                case 10: testGetRestsB(session);
                        break;
                case 11: testGetRestsG(session);
                        break;
                case 12: testGetRestThreshold(session);
                        break;
                case 13: testGetAllowedClassroomDayHour(session);
                        break;
                case 14: testInitAllowedClassroomDayHour(session);
                        break;
                case 15: testRemoveUnsatisfactoryCDH(session, schedule);
                        break;
                case 16: testAddDefaultRestrictions(session);
                        break;
              }
              num = in.nextInt();
        }
    }
}