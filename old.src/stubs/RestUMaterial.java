package domain;
import java.util.*;
import domain.*;

public class RestUMaterial extends RestrictionUnary {
  
  private int weight = 70;
  private int weightEmptySit = -3;

  public int check(Session s, int h, int d, Classroom c){
    return 80;
    // System.out.println("hasMaterials="+c.hasMaterials( s.getClassType().getMaterials() ));
    // if (c.hasMaterials( s.getClassType().getMaterials() )) {
    //   return weight;
    // } else{
    //   return -weight + weightEmptySit * (s.getClassType().getMaterials().size() - c.getMaterials().size());
    // }
  }
}