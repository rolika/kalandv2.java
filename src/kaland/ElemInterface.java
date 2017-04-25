package kaland;

import java.util.EnumSet;

/**
 * Interface a kalandelemekhez (tárgy, ajtó, csapda, ellenség)
 * 
 * @author rolika
 */
interface ElemInterface {
  
  String getNev();
  String getLeiras();
  EnumSet<HelyszinEnum> getHely();
  EnumSet<AllapotEnum> getAllapot();
  void addAllapot(AllapotEnum allapot);
  void delAllapot(AllapotEnum allapot);
  void setHely(HelyszinEnum hely);
  TargyEnum getKulcs();
  
}
