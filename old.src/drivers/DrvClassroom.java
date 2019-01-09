package domain.drivers;
import java.util.*;
import data.*;
import domain.*;

public class DrvClassroom {
    public static void testGetName(Classroom classroom){
        System.out.println(classroom.getName());
    };
    public static void testGetCapacity(Classroom classroom){
        System.out.println(classroom.getCapacity());
    };
    public static void testGetMaterials(Classroom classroom){
        System.out.println(classroom.getMaterials());
    };
    public static void testHasMaterial(Classroom classroom, List<String> materials){
        System.out.println(classroom.hasMaterials(materials));
    };

    public static void main (String [] args){

        Scanner in = new Scanner(System.in);

        System.out.printf("Create Subject to Test: \n");
        System.out.printf("Introduce Name: \n");
        String nameIn = in.nextLine();
        System.out.printf("Introduce Capacity: \n");
        int capacityIn = in.nextInt();
        System.out.printf("Introduce Materials: \n");
        String intro = in.nextLine();
        String materialsIn = in.nextLine();
        String partsMaterialsIn[] = materialsIn.split(" ");
        List<String> listMaterialsIn = Arrays.asList(partsMaterialsIn);
        
        Classroom classroom = new Classroom(nameIn, capacityIn, listMaterialsIn);

        System.out.printf("Enter a number to choose what you want to test: \n");
        System.out.printf("1- Test getName() \n");
        System.out.printf("2- Test getCapacity() \n");
        System.out.printf("3- Test getMaterials() \n");
        System.out.printf("4- Test hasMaterials() \n");
        
        int num = in.nextInt();

        while(true){
            switch (num) {
                case 1: testGetName(classroom);
                        break;
                case 2: testGetCapacity(classroom);
                        break; 
                case 3: testGetMaterials(classroom);
                        break;
                case 4: testHasMaterial(classroom, listMaterialsIn);
                        break; 
            }
            num = in.nextInt();
        }
    }
}