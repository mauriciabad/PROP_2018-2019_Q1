package domain.drivers;
import java.util.*;
import data.*;
import domain.*;

public class DrvClassroomDayHour {
    public static void testGetDay(ClassroomDayHour cdh){
        System.out.println(cdh.getDay());
    };
    public static void testGetHour(ClassroomDayHour cdh){
        System.out.println(cdh.getHour());
    };
    public static void testGetClassroom(ClassroomDayHour cdh){
        System.out.println(cdh.getClassroom());
    };
    public static void testGetPuntuation(ClassroomDayHour cdh){
        System.out.println(cdh.getPuntuation());
    };
    public static void testAddPuntuation(ClassroomDayHour cdh, int points){
        cdh.addPuntuation(points);
        System.out.println("Funciona");
    };
    public static void testCompareTo(ClassroomDayHour cdh, ClassroomDayHour cdh2){
        System.out.println(cdh.compareTo(cdh2));
    };
    

    public static void main (String [] args){
        Scanner in = new Scanner(System.in);

        System.out.printf("Create ClassroomDayHour to Compare: \n");
        System.out.printf("Introduce Day: \n");
        int dayIn = in.nextInt();
        System.out.printf("Introduce Hour: \n");
        int hourIn = in.nextInt();
        System.out.printf("Introduce Puntuation \n");
        int puntuationIn = in.nextInt();
        

        Classroom classroom = new Classroom("A6101", 60);
        ClassroomDayHour cdh = new ClassroomDayHour(dayIn, hourIn, classroom, puntuationIn);

        System.out.printf("Enter a number to choose what you want to test: \n");
        System.out.printf("1- Test GetDay() \n");
        System.out.printf("2- Test GetHour() \n");
        System.out.printf("3- Test GetClassroom() \n");
        System.out.printf("4- Test GetPuntuation() \n");
        System.out.printf("5- Test AddPuntuation() \n");
        System.out.printf("6- Test CompareTo() \n");


        int num = in.nextInt();

        while(true){
            switch (num) {
                case 1: testGetDay(cdh);
                        break;
                case 2: testGetHour(cdh);
                        break; 
                case 3: testGetClassroom(cdh);
                        break;
                case 4: testGetPuntuation(cdh);
                        break;
                case 5: System.out.printf("Introduce Puntuation \n");
                        int points = in.nextInt();
                        testAddPuntuation(cdh, points);
                        break;
                case 6: System.out.printf("Create ClassroomDayHour to Compare: \n");
                        System.out.printf("Introduce Day: \n");
                        int dayIn2 = in.nextInt();
                        System.out.printf("Introduce Hour: \n");
                        int hourIn2 = in.nextInt();
                        System.out.printf("Introduce Puntuation \n");
                        int puntuationIn2 = in.nextInt();
                        
                
                        Classroom classroom2 = new Classroom("A6101", 60);
                        ClassroomDayHour cdh2 = new ClassroomDayHour(dayIn2, hourIn2, classroom, puntuationIn2);
                        testCompareTo(cdh, cdh2);
                        break;
            }
            num = in.nextInt();
        }
    }
}