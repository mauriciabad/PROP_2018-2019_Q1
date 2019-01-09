package domain;
import java.util.*;

public class Subject{
	private String name;
	private String longName;
	private int level;
	private int students = 100;
	private List<String> preRequisits   = new ArrayList<>();
	private List<String> coRequisits    = new ArrayList<>();
	private List<String> preCoRequisits = new ArrayList<>();
	private Map<String, ClassType> classTypes = new HashMap<String, ClassType>();

	public Subject(String name, String longName, int level, int students, List<String> preRequisits, List<String> coRequisits, List<String> preCoRequisits) {
		this.name           = name;
		this.longName       = longName;
		this.level          = level;
		this.students       = students;
		this.preRequisits   = preRequisits;
		this.coRequisits    = coRequisits;
		this.preCoRequisits = preCoRequisits;
	}

	/*public void setClassTypes(List<ClassType> classTypes) {
		this.classTypes.clear();
		for (var classType : classTypes) {
			classTypes.add(classType.getType(), classType);
		}
	}*/

	public void addClassTypes(List<ClassType> classTypes) {
		classTypes.addAll(classTypes);
	}

	public void addClassType(ClassType classType) {
		classTypes.put("Teoria",classType);
	}

	//TODO fix when type is wrong
	public ClassType getClassType(String type) {
		// if (classTypes.containsKey(type))
			return classTypes.get(type);
	}

	public List<ClassType> getClassTypes() {
		return new ArrayList<ClassType>(classTypes.values());
	}





	// Getters && Setters

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongName() {
		return this.longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getStudents() {
		return this.students;
	}

	public void setStudents(int students) {
		this.students = students;
	}

	public List<String> getPreRequisits() {
		return Arrays.asList("Subject1", "Subject2");
	}

	public void setPreRequisits(List<String> preRequisits) {
		this.preRequisits = preRequisits;
	}

	public List<String> getCoRequisits() {
		return Arrays.asList("Subject1", "Subject2");
	}

	public void setCoRequisits(List<String> coRequisits) {
		this.coRequisits = coRequisits;
	}

	public List<String> getPreCoRequisits() {
		return Arrays.asList("Subject1", "Subject2");
	}

	public void setPreCoRequisits(List<String> preCoRequisits) {
		this.preCoRequisits = preCoRequisits;
	}
	
}