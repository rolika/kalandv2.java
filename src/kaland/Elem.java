package kaland;

import java.util.EnumSet;

/**
 * Interface a kalandelemekhez (játékos, helyszín, tárgy, ajtó, csapda, ellenség)
 * 
 * @author rolika
 */
interface Elem {
  
  String getNev();
  String getLeiras();
  EnumSet<Helyszin> getHely();
  void addAllapot(Allapot ... allapot);
  void removeAllapot(Allapot ... allapot);
  boolean checkAllapot(Allapot ... allapot);
  void setHely(Helyszin hely);
  Elem getPar();
  void setLeiras(String leiras);
  
}
