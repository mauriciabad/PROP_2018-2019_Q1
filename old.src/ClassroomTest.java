import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;


public class ClassroomTest {

  @Test
  public void evaluatesMaterialsEmpty() {
		Classroom classroom = new Classroom("A5S108", 10);
		List<String> check = Arrays.asList("mat1", "mat3");

		boolean result = classroom.hasMaterials(check);
		assertEquals(false, result);

	}
  @Test
  public void evaluateshasMaterials() {
		Classroom classroom = new Classroom("A5S108", 10);
		List<String> check = Arrays.asList("mat1", "mat3");

		List<String> materials = Arrays.asList("mat1", "mat3");
		classroom.setMaterials(materials);
		
		boolean result = classroom.hasMaterials(check);
		assertEquals(true, result);
    
  }

  @Test
  public void evaluateshasMaterialsFail() {
		Classroom classroom = new Classroom("A5S108", 10);
		List<String> check = Arrays.asList("mat1", "mat2");

		List<String> materials = Arrays.asList("mat1", "mat3");
		classroom.setMaterials(materials);
		
		boolean result = classroom.hasMaterials(check);
		assertEquals(false, result);
  }

	@Test
 public void evaluatesdecomposeNameS() {
		Classroom classroom = new Classroom("A5S108", 10);
		int floor = classroom.getFloor();
		int room = classroom.getRoom();
		String building = classroom.getBuilding();
		assertEquals(-1, floor);
		assertEquals(8, room);
		assertEquals("A5", building);

  }
 public void evaluatesdecomposeNameE() {
		Classroom classroom = new Classroom("A2E06", 10);
		int floor = classroom.getFloor();
		int room = classroom.getRoom();
		String building = classroom.getBuilding();
		assertEquals(0, floor);
		assertEquals(6, room);
		assertEquals("A2", building);

  }
 public void evaluatesdecomposeName() {
		Classroom classroom = new Classroom("B1204", 10);
		int floor = classroom.getFloor();
		int room = classroom.getRoom();
		String building = classroom.getBuilding();
		assertEquals(2, floor);
		assertEquals(4, room);
		assertEquals("B1", building);

  }

  @Test
  public void evaluatestoString() {
		List<String> materials = Arrays.asList("mat1", "mat2");
    Classroom classroom = new Classroom("A5S108", 10, materials);
		String ret = classroom.toString();
    assertEquals("{name=A5S108, capacity=10, materials=[mat1, mat2]}", ret);
  }
}