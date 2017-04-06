package kaland;

import java.util.Set;

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
        helyszin.setBejart(false);
        return helyszin.getLeiras();
      case ROVID:
        helyszin.setBejart(true);
        return helyszin.getNev();
      default:
        String leiras = helyszin.getNormalLeiras();
        helyszin.setBejart(true);
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
    if (parancsszavak.size() == 1) {
      TargyEnum targy = TargyEnum.valueOf(parancsszavak.iterator().next().toString());
      Set<TargyEnum> lathatoTargyak = helyszin.targyak(TargyEnum::isLathato);
      lathatoTargyak.addAll(HelyszinEnum.LELTAR.targyak(leltar -> true));
      if (lathatoTargyak.contains(targy)) {
        targy.setVizsgalt(true);
        return targy.getLeiras();
      } else {
        return UzenetEnum.NINCS_ITT_ILYEN.toString();
      }
    } else {
      return UzenetEnum.NEM_ERTEM.toString();
    }
  }

  String vesz(Set<SzotarInterface> parancsszavak) {
    if (parancsszavak.size() == 1) {
      TargyEnum targy = TargyEnum.valueOf(parancsszavak.iterator().next().toString());
      Set<TargyEnum> lathatoTargyak = helyszin.targyak(TargyEnum::isLathato);
      Set<TargyEnum> felvehetoTargyak = helyszin.targyak(TargyEnum::isFelveheto);
      Set<TargyEnum> leltar = HelyszinEnum.LELTAR.targyak(t -> true);
      if (leltar.contains(targy)) {
        return UzenetEnum.MAR_NALAD_VAN.toString();
      } else if (!felvehetoTargyak.contains(targy)) {
        return UzenetEnum.NEM_FELVEHETO.toString();
      } else if (!lathatoTargyak.contains(targy)) {
        return UzenetEnum.NINCS_ITT_ILYEN.toString();
      } else {
        targy.setHely(HelyszinEnum.LELTAR);
        targy.setVizsgalt(true);
        StringBuilder felvesz = new StringBuilder(UzenetEnum.RENDBEN.toString());
        felvesz.append(' ');
        felvesz.append(targy.getLeiras());
        return felvesz.toString();
      }
    } else {
      return UzenetEnum.NEM_ERTEM.toString();
    }
  }

  String tesz(Set<SzotarInterface> parancsszavak) {
    if (parancsszavak.size() == 1) {
      TargyEnum targy = TargyEnum.valueOf(parancsszavak.iterator().next().toString());
      Set<TargyEnum> leltar = HelyszinEnum.LELTAR.targyak(t -> true);
      if (leltar.contains(targy)) {
        targy.setHely(helyszin);
        return UzenetEnum.RENDBEN.toString();
      } else {
        return UzenetEnum.NINCS_NALAD.toString();
      }
    } else {
      return UzenetEnum.NEM_ERTEM.toString();
    }
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
    return helyszin.getLeiras();
  }

  String megerosit(Set<SzotarInterface> parancsszavak) {
    return "Játékos megerősít.";
  }

  private String targyak(HelyszinEnum aktualisHelyszin) {
    Set<TargyEnum> leltar
      = aktualisHelyszin.targyak(targy -> targy.isLathato() && targy.isFelveheto());
    if (leltar.isEmpty()) {
      return aktualisHelyszin == HelyszinEnum.LELTAR ? UzenetEnum.NINCS_LELTAR.toString() : "";
    } else {
      StringBuilder uzenet = new StringBuilder(aktualisHelyszin == HelyszinEnum.LELTAR
        ? UzenetEnum.LELTAR.toString() : UzenetEnum.VAN_ITT.toString());
      leltar
        .forEach(targy -> {
          uzenet.append(UzenetEnum.EGY);
          uzenet.append(targy.getNev());
          uzenet.append(",");
        });
      uzenet.replace(uzenet.length() - 1, uzenet.length(), ".");
      return uzenet.toString();
    }
  }

}
