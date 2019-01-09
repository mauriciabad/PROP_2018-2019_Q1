package domain;
import java.util.*;

public class Classroom {

  private String name;
  private String building;
  private int floor;
  private int room;

  private int capacity;
  private List<String> materials = new ArrayList<>();

  //Constructors
  public Classroom(String name, int capacity) {
    this.name     = name;
    this.capacity = capacity;
    decomposeName(name);
  }

  public Classroom(String name, int capacity, List<String> materials) {
    this.name      = name;
    this.capacity  = capacity;
    this.materials = materials;
    decomposeName(name);
  }

  
  //Functions
  public boolean hasMaterials(List<String> materials) {
    return this.materials.containsAll(materials);
  }

  private  void decomposeName(String name) {
    this.building = name.substring(0, 2);
    if (name.substring(2,3).equals("S")) {
      this.floor = -Integer.parseInt(name.substring(3,4));
      this.room  = Integer.parseInt(name.substring(4,6));
    }else if (name.substring(2,3).equals("E")) {
      this.floor = 0;
      this.room  = Integer.parseInt(name.substring(3,5));
    }else if(name.length() == 5){
      this.floor = Integer.parseInt(name.substring(2,3));
      this.room  = Integer.parseInt(name.substring(3,5));
    }else{
      this.floor = 0;
      this.room  = 0;
    }
  }

  public String toString() {
    return "{name=" + name + ", capacity=" + capacity + ", materials=[" + String.join(", ", materials) +"]}";
  }

  //Getters and setters
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBuilding() {
    return this.building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public int getFloor() {
    return this.floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  public int getRoom() {
    return this.room;
  }

  public void setRoom(int room) {
    this.room = room;
  }

  public int getCapacity() {
    return this.capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public List<String> getMaterials() {
    return this.materials;
  }

  public void setMaterials(List<String> materials) {
    this.materials = materials;
  }

}
