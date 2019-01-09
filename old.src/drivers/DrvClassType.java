package domain.drivers;
import java.util.*;
import data.*;
import domain.*;

public class DrvClassType {
    public static void testGetSubject(ClassType classType){
        System.out.println(classType.getSubject());
    };
    public static void testGetType(ClassType classType){
        System.out.println(classType.getType());
    };
    public static void testGetWeeklySessions(ClassType classType){
        System.out.println(classType.getWeeklySessions());
    };
    public static void testGetSubgroup(ClassType classType){
        System.out.println(classType.getSubgroup());
    };
    public static void testGetGroupSize(ClassType classType){
        System.out.println(classType.getGroupSize());
    };
    public static void testGetMaterials(ClassType classType){
        System.out.println(classType.getMaterials());
    };
    public static void testMakeGroups(ClassType classType){
        System.out.println(classType.makeGroups());
    };
    

    public static void main (String [] args){
        Scanner in = new Scanner(System.in);

        System.out.printf("Create Subject to Test: \n");
        System.out.printf("Introduce Type: \n");
        String typeIn = in.nextLine();
        System.out.printf("Introduce number of Weekly Sessions: \n");
        int weeklySessionsIn = in.nextInt();
        System.out.printf("Introduce Duration: \n");
        int durationIn = in.nextInt();
        System.out.printf("Introduce Subgroup: \n");
        int subgroupIn = in.nextInt();
        System.out.printf("Introduce Group Size: \n");
        int groupsizeIn = in.nextInt();
        System.out.printf("Introduce Materials: \n");
        String intro = in.nextLine();
        String materialsIn = in.nextLine();
        String partsMaterialsIn[] = materialsIn.split(" ");
        List<String> listMaterialsIn = Arrays.asList(partsMaterialsIn);

        List<String> requisits = new ArrayList<>();
        requisits.add("req1");
        requisits.add("req2");

        Subject subject = new Subject("PROP", "Projectes de Programacio", 1, 130, requisits, requisits, requisits);

        ClassType classType = new ClassType(subject, typeIn, weeklySessionsIn, durationIn, subgroupIn, groupsizeIn, listMaterialsIn);

        System.out.printf("Enter a number to choose what you want to test: \n");
        System.out.printf("1- Test testGetSubject() \n");
        System.out.printf("2- Test testGetType() \n");
        System.out.printf("3- Test testGetWeeklySessions() \n");
        System.out.printf("4- Test testGetSubgroup() \n");
        System.out.printf("5- Test testGetGroupSize() \n");
        System.out.printf("6- Test testGetMaterials() \n");
        System.out.printf("7- Test testMakeGroups() \n");

        int num = in.nextInt();

        while(true){
            switch (num) {
                case 1: testGetSubject(classType);
                        break;
                case 2: testGetType(classType);
                        break; 
                case 3: testGetWeeklySessions(classType);
                        break;
                case 4: testGetSubgroup(classType);
                        break;
                case 5: testGetGroupSize(classType);
                        break;
                case 6: testGetMaterials(classType);
                        break;
                case 7: testMakeGroups(classType);
                        break; 
            }
            num = in.nextInt();
        }
    }
}