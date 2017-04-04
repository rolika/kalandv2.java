package kaland;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A játékost megvalósító osztály kalandjátékhoz
 *
 * @author rolika
 */
final class Jatekos {

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

  void setHelyszin(HelyszinEnum helyszin) {
    this.helyszin = helyszin;
    helyszin.setKijaratok(KijaratEnum.valueOf(helyszin.toString())); // ugyanaz a konstans nevük
  }
  
  String helyszinLeiras() {
    switch (getLeiroMod()) {
      case HOSSZU:
        getHelyszin().setBejart(false);
        return getHelyszin().getLeiras();
      case ROVID:
        getHelyszin().setBejart(true);
        return getHelyszin().getNev();
      default:
        String leiras = getHelyszin().getNormalLeiras();
        getHelyszin().setBejart(true);
        return leiras;
    }
  }
  
  String targySorolo() {
    return targyak(helyszin);
  }

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
    vesztett = true;
    return UzenetEnum.VISZLAT.toString();
  }
  
  String leltar(Set<SzotarInterface> parancsszavak) {
    return targyak(HelyszinEnum.LELTAR);
  }
  
  String vizsgal(Set<SzotarInterface> parancsszavak) {
    helyszin.targyak(TargyEnum::isLathato);
    return "Játékos vizsgál.";
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
  
  private String targyak(HelyszinEnum aktualisHelyszin) {
    Set<TargyEnum> leltar =
      aktualisHelyszin.targyak(targy -> targy.isLathato() && targy.isFelveheto());
    if (leltar.isEmpty()) {
      return aktualisHelyszin == HelyszinEnum.LELTAR ? UzenetEnum.NINCS_LELTAR.toString() : "";
    } else {
      StringBuilder uzenet = new StringBuilder(aktualisHelyszin == HelyszinEnum.LELTAR ? 
        UzenetEnum.LELTAR.toString() : UzenetEnum.VAN_ITT.toString());
      leltar
        .forEach(targy -> {
          uzenet.append(UzenetEnum.EGY);
          uzenet.append(targy.getNev());
          uzenet.append(",");
        });
      return uzenet.toString();
    }
  }

}
