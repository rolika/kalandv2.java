package kaland;

import java.util.EnumSet;

/**
 * Interface a kalandelemekhez (tárgy, ajtó, csapda, ellenség)
 * 
 * @author rolika
 */
interface Elem {
  
  String getNev();
  String getLeiras();
  EnumSet<Helyszin> getHely();
  EnumSet<Allapot> getAllapot();
  void addAllapot(Allapot ... allapot);
  void removeAllapot(Allapot ... allapot);
  void setHely(Helyszin hely);
  Targy getPar();
  
}
