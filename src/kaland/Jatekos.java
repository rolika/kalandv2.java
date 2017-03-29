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
  
  String kilep(Set<SzotarInterface> parancsszavak) {
    /*if (Kaland.jatekVege()) {
      System.exit(0);
    }
    Kaland.ujJatek();*/
    return "Játékos kilép.";
  }
  
  String leltar(Set<SzotarInterface> parancsszavak) {
    return "Játékos leltározik.";
  }
  
  String aktival(Set<SzotarInterface> parancsszavak) {
    return "Játékos aktivál.";
  }
  
  String deaktival(Set<SzotarInterface> parancsszavak) {
    return "Játékos deaktivál.";
  }
  
  String nyit(Set<SzotarInterface> parancsszavak) {
    return "Játékos kinyit.";
  }
  
  String csuk(Set<SzotarInterface> parancsszavak) {
    return "Játékos becsuk.";
  }
  
  String zar(Set<SzotarInterface> parancsszavak) {
    return "Játékos bezár.";
  }
  
  String vesz(Set<SzotarInterface> parancsszavak) {
    return "Játékos felvesz.";
  }
  
  String tesz(Set<SzotarInterface> parancsszavak) {
    return "Játékos letesz.";
  }
  
  String vizsgal(Set<SzotarInterface> parancsszavak) {
    return "Játékos vizsgál.";
    // parancsszavak.forEach(System.out::println);
  }
  
  String tamad(Set<SzotarInterface> parancsszavak) {
    return "Játékos leltározik.";
  }
  
  String hosszu(Set<SzotarInterface> parancsszavak) {
    leiroMod = LeiroEnum.HOSSZU;
    return UzenetEnum.RENDBEN.toString();
  }
  
  String rovid(Set<SzotarInterface> parancsszavak) {
    leiroMod = LeiroEnum.ROVID;
    return UzenetEnum.RENDBEN.toString();
  }
  
  String normal(Set<SzotarInterface> parancsszavak) {
    leiroMod = LeiroEnum.NORMAL;
    return UzenetEnum.RENDBEN.toString();
  }
  
  String info(Set<SzotarInterface> parancsszavak) {
    return "Játékos információt kér.";
  }
  
  String megerosit(Set<SzotarInterface> parancsszavak) {
    return "Játékos megerősít.";
  }

}
