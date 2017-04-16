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

  HelyszinEnum getHelyszin() {
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

  void setHelyszin(HelyszinEnum helyszin) {
    this.helyszin = helyszin;
    helyszin.setKijaratok(KijaratEnum.valueOf(helyszin.toString())); // ugyanaz a konstans nevük
  }

  String megy(IranyEnum irany) {
    HelyszinEnum ujHelyszin = helyszin.getKijarat(irany);
    if (ujHelyszin == null) {
      return UzenetEnum.ARRA_NEM.toString();
    } else {
      switch (helyszin.ajto(ujHelyszin)) {
        case CSUKVA:
          return UzenetEnum.CSUKVA.toString();
        case ZARVA:
          return UzenetEnum.ZARVA.toString();
        case NYITVA: // fall through
          setHelyszin(ujHelyszin);
          return UzenetEnum.NYITVA.toString();
        default:
          setHelyszin(ujHelyszin);
          return UzenetEnum.RENDBEN.toString();
      }
    }
  }

  String kilep(Set<SzotarInterface> parancsszavak) {
    vesztett = true;
    return UzenetEnum.VISZLAT.toString();
  }

  String leltar(Set<SzotarInterface> parancsszavak) {
    return HelyszinEnum.LELTAR.targyak();
  }

  String vizsgal(Set<SzotarInterface> parancsszavak) {
    if (parancsszavak.size() == 1) {
      TargyEnum targy = TargyEnum.valueOf(parancsszavak.iterator().next().toString());
      Set<TargyEnum> lathatoTargyak = helyszin.targySzuro(TargyEnum::isLathato);
      lathatoTargyak.addAll(HelyszinEnum.LELTAR.targySzuro(leltar -> true));
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
      Set<TargyEnum> lathatoTargyak = helyszin.targySzuro(TargyEnum::isLathato);
      Set<TargyEnum> felvehetoTargyak = helyszin.targySzuro(TargyEnum::isFelveheto);
      Set<TargyEnum> leltar = HelyszinEnum.LELTAR.targySzuro(t -> true);
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
      Set<TargyEnum> leltar = HelyszinEnum.LELTAR.targySzuro(t -> true);
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
    helyszin.setLeiroMod(LeiroEnum.HOSSZU);
    return UzenetEnum.RENDBEN.toString();
  }

  String rovid(Set<SzotarInterface> parancsszavak) {
    helyszin.setLeiroMod(LeiroEnum.ROVID);
    return UzenetEnum.RENDBEN.toString();
  }

  String normal(Set<SzotarInterface> parancsszavak) {
    helyszin.setLeiroMod(LeiroEnum.NORMAL);
    return UzenetEnum.RENDBEN.toString();
  }

  String megerosit(Set<SzotarInterface> parancsszavak) {
    return "Játékos megerősít.";
  }

}
