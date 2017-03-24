package kaland;

import java.util.HashSet;
import java.util.Set;

/**
 * A játékost megvalósító osztály kalandjátékhoz
 *
 * @author rolika
 */
class Jatekos {

  private boolean meghalt, nyert, vesztett;
  private HelyszinEnum helyszin;
  //private EnumSet<HelyszinEnum, TargyEnum> leltar;

  Jatekos(HelyszinEnum helyszin) {
    meghalt = false;
    nyert = false;
    vesztett = false;
    setHelyszin(helyszin);

  }

  boolean isMeghalt() {
    return meghalt;
  }

  boolean isNyert() {
    return nyert;
  }

  boolean isVesztett() {
    return vesztett;
  }

  public HelyszinEnum getHelyszin() {
    return helyszin;
  }

  void setMeghalt(boolean meghalt) {
    this.meghalt = meghalt;
  }

  void setNyert(boolean nyert) {
    this.nyert = nyert;
  }

  void setVesztett(boolean vesztett) {
    this.vesztett = vesztett;
  }

  private void setHelyszin(HelyszinEnum helyszin) {
    this.helyszin = helyszin;
    helyszin.setKijaratok(KijaratEnum.valueOf(helyszin.toString())); // ugyanaz a konstans nevük
  }

  /**
   * Játékos mozgásának kezelése
   * @param irany
   * @return szöveges reakció a mozgási szándékra
   */
  String megy(IranyEnum irany) {
    HelyszinEnum ujHelyszin = helyszin.getKijarat(irany);
    if (ujHelyszin == null) {
      return UzenetEnum.ARRA_NEM.toString();
    } else {
      setHelyszin(ujHelyszin);
      return UzenetEnum.RENDBEN.toString();
    }
  }
  
  /**
   * Kilépés-kezelő
   * 
   * @param parancsszavak nem használja, de az általánosítás miatt szükség van rá
   */
  void kilep(Set<SzotarInterface> parancsszavak) {
    if (Kaland.jatekVege()) {
      System.exit(0);
    }
    Kaland.ujJatek();
  }
  
  void vizsgal(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos vizsgál.");
    parancsszavak.forEach(System.out::println);
  }

}
