package domain.drivers;
import java.util.*;
import data.*;
import domain.*;

public class DrvSubject {
    public static void testGetName(Subject subject){
        System.out.println(subject.getName());
    };
    public static void testGetLongName(Subject subject){
        System.out.println(subject.getLongName());
    };
    public static void testGetLevel(Subject subject){
        System.out.println(subject.getLevel());
    };
    public static void testGetStudents(Subject subject){
        System.out.println(subject.getStudents());
    };
    public static void testGetPreRequisits(Subject subject){
        System.out.println(subject.getPreRequisits());
    };
    public static void testGetCoRequisits(Subject subject){
        System.out.println(subject.getCoRequisits());
    };
    public static void testGetPreCoRequisits(Subject subject){
        System.out.println(subject.getPreCoRequisits());
    };
    public static void testGetClassType(Subject subject, String type){
        System.out.println(subject.getClassType(type));
    };
    public static void testGetClassTypes(Subject subject){
        System.out.println(subject.getClassTypes());
    };
    public static void testAddClassType(Subject subject, ClassType classtype){
        try
            {
                subject.addClassType(classtype);
                System.out.println(true);	
            }
        catch(Exception e)
            {
                System.out.println(false);
            }
    };
    public static void testAddClassTypes(Subject subject, List<ClassType> classtypes){
        try
            {
                subject.addClassTypes(classtypes);
                System.out.println(true);	
            }
        catch(Exception e)
            {
                System.out.println(false);
            }
    };


    

    public static void main (String [] args){
        
        List<String> requisits = new ArrayList<>();
        requisits.add("req1");
        requisits.add("req2");

        Subject subject = new Subject("PROP", "Projectes de Programacio", 1, 60, requisits, requisits, requisits);

        List<String> materials = new ArrayList<>();
        materials.add("mat1");
        materials.add("mat2");
        
        ClassType classType = new ClassType(subject, "Teoria", 3, 2, 20, 60, materials);

        List<ClassType> classtypes = new ArrayList<>();
        classtypes.add(classType);
        classtypes.add(classType);

        System.out.printf("Enter a number to choose what you want to test: \n");
        System.out.printf("1- Test GetName() \n");
        System.out.printf("2- Test GetLongName() \n");
        System.out.printf("3- Test GetLevel() \n");
        System.out.printf("4- Test GetStudents() \n");
        System.out.printf("5- Test GetPreRequisits() \n");
        System.out.printf("6- Test GetCoRequisit() \n");
        System.out.printf("7- Test GetPreCoRequisit() \n");
        System.out.printf("8- Test MakeGroups() \n");
        System.out.printf("9- Test GetClassType() \n");
        System.out.printf("10- Test GetClassTypes() \n");
        System.out.printf("11- Test AddClassType() \n");
        System.out.printf("12- Test AddClassTypes() \n");
        
        
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        while(true){
            if(num>0 && num<=11){
                if (num==1) testGetName(subject);
                else if (num==2) testGetLongName(subject);
                else if (num==3) testGetLevel(subject);
                else if (num==4) testGetStudents(subject);
                else if (num==5) testGetPreRequisits(subject);
                else if (num==6) testGetCoRequisits(subject);
                else if (num==7) testGetPreCoRequisits(subject);
                else if (num==8) testGetClassType(subject, "Teoria");
                else if (num==9) testGetClassTypes(subject);
                else if (num==10) testAddClassType(subject, classType);
                else if (num==11) testAddClassTypes(subject, classtypes);
            }
            else System.out.println("Error! Invalid number.");

            num = in.nextInt();
        }
    }
}