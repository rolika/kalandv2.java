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
  private LeiroEnum leiroMod;
  //private EnumSet<HelyszinEnum, TargyEnum> leltar;

  Jatekos(HelyszinEnum helyszin) {
    meghalt = false;
    nyert = false;
    vesztett = false;
    setHelyszin(helyszin);
    leiroMod = LeiroEnum.NORMAL;
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

  public LeiroEnum getLeiroMod() {
    return leiroMod;
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
  
  void leltar(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos leltározik.");
  }
  
  void aktival(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos aktivál.");
  }
  
  void deaktival(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos deaktivál.");
  }
  
  void nyit(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos kinyit.");
  }
  
  void csuk(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos becsuk.");
  }
  
  void zar(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos bezár.");
  }
  
  void vesz(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos felvesz.");
  }
  
  void tesz(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos letesz.");
  }
  
  void vizsgal(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos vizsgál.");
    parancsszavak.forEach(System.out::println);
  }
  
  void tamad(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos leltározik.");
  }
  
  void hosszu(Set<SzotarInterface> parancsszavak) {
    leiroMod = LeiroEnum.HOSSZU;
  }
  
  void rovid(Set<SzotarInterface> parancsszavak) {
    leiroMod = LeiroEnum.ROVID;
  }
  
  void normal(Set<SzotarInterface> parancsszavak) {
    leiroMod = LeiroEnum.NORMAL;
  }
  
  void info(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos információt kér.");
  }
  
  void megerosit(Set<SzotarInterface> parancsszavak) {
    System.out.println("Játékos megerősít.");
  }

}
