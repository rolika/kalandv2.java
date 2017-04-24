package kaland;

import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A játékost megvalósító osztály kalandjátékhoz
 *
 * @author rolika
 */
final class Jatekos {

  private final EnumSet<AllapotEnum> allapot;
  private HelyszinEnum helyszin;

  Jatekos(HelyszinEnum helyszin) {
    allapot = EnumSet.of(AllapotEnum.EL, AllapotEnum.NEM_NYERT, AllapotEnum.NEM_VESZTETT);
    setHelyszin(helyszin);
  }

  boolean jatekbanVan() {
    return allapot.contains(AllapotEnum.EL) && allapot.contains(AllapotEnum.NEM_NYERT)
      && allapot.contains(AllapotEnum.NEM_VESZTETT);
  }

  HelyszinEnum getHelyszin() {
    return helyszin;
  }

  void setMeghalt() {
    allapot.remove(AllapotEnum.EL);
  }

  void setNyert() {
    allapot.remove(AllapotEnum.NEM_NYERT);
  }

  void setVesztett() {
    allapot.remove(AllapotEnum.NEM_VESZTETT);
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
      EnumSet<AllapotEnum> ajtoAllapot = helyszin.ajto(ujHelyszin);
      if (ajtoAllapot.contains(AllapotEnum.NINCS) || ajtoAllapot.contains(AllapotEnum.NYITVA)) {
        setHelyszin(ujHelyszin);
        return UzenetEnum.RENDBEN.toString();
      } else {
        if (ajtoAllapot.contains(AllapotEnum.CSUKVA)) {
          return UzenetEnum.CSUKVA.toString();
        } else { // különben be van zárva
          return UzenetEnum.ZARVA.toString();
        }
      }
    }
  }

  String kilep(Set<SzotarInterface> parancsszavak) {
    setVesztett();
    return UzenetEnum.VISZLAT.toString();
  }

  String leltar(Set<SzotarInterface> parancsszavak) {
    return HelyszinEnum.LELTAR.targyak();
  }

  String vesz(Set<SzotarInterface> parancsszavak) {
    return mozgat(parancsszavak, helyszin, HelyszinEnum.LELTAR);
  }

  String tesz(Set<SzotarInterface> parancsszavak) {
    return mozgat(parancsszavak, HelyszinEnum.LELTAR, helyszin);
  }

  String vizsgal(Set<SzotarInterface> parancsszavak) {
    if (parancsszavak.size() == 1) {
      ElemInterface elem = Ertelmezo.getElem(parancsszavak.iterator().next());
      Set<ElemInterface> lathatoTargyak = helyszin.elemSzuro(AllapotEnum.LATHATO);
      lathatoTargyak.addAll(HelyszinEnum.LELTAR.elemSzuro());
      if (lathatoTargyak.contains(elem)) {
        elem.addAllapot(AllapotEnum.VIZSGALT);
        return elem.getLeiras();
      }
    }
    return UzenetEnum.NEM_ERTEM.toString();
  }

  String aktival(Set<SzotarInterface> parancsszavak) {
    return "Játékos aktivál.";
  }

  String deaktival(Set<SzotarInterface> parancsszavak) {
    return "Játékos deaktivál.";
  }

  String nyit(Set<SzotarInterface> parancsszavak) {
    Set<ElemInterface> parancsElemek = Ertelmezo.getElemek();
    ElemInterface nyitandoTargy;
    try {
      nyitandoTargy = parancsElemek.stream()
        .filter(elem -> elem.getAllapot().contains(AllapotEnum.NYITHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      nyitandoTargy = null;
    }
    if (nyitandoTargy == null) {
      return UzenetEnum.NEM_ERTEM.toString();
    } else {
      Set<ElemInterface> nyithatoTargyak = helyszin.elemSzuro(AllapotEnum.NYITHATO);
      if (!nyithatoTargyak.contains(nyitandoTargy)) {
        return UzenetEnum.NEM_ERTEM.toString();
      } else if (nyitandoTargy.getAllapot().contains(AllapotEnum.NYITVA)) {
        return UzenetEnum.MAR_NYITVA.toString();
      } else {
        nyitandoTargy.addAllapot(AllapotEnum.NYITVA);
        return UzenetEnum.RENDBEN.toString();
      }
    }
  }

  String csuk(Set<SzotarInterface> parancsszavak) {
    return "Játékos becsuk.";
  }

  String zar(Set<SzotarInterface> parancsszavak) {
    return "Játékos bezár.";
  }

  String tamad(Set<SzotarInterface> parancsszavak) {
    return "Játékos támad.";
  }

  String hosszu(Set<SzotarInterface> parancsszavak) {
    helyszin.setLeiroMod(AllapotEnum.HOSSZU);
    return UzenetEnum.RENDBEN.toString();
  }

  String rovid(Set<SzotarInterface> parancsszavak) {
    helyszin.setLeiroMod(AllapotEnum.ROVID);
    return UzenetEnum.RENDBEN.toString();
  }

  String normal(Set<SzotarInterface> parancsszavak) {
    helyszin.setLeiroMod(AllapotEnum.NORMAL);
    return UzenetEnum.RENDBEN.toString();
  }

  String megerosit(Set<SzotarInterface> parancsszavak) {
    return "Játékos megerősít.";
  }

  String minden(Set<SzotarInterface> parancsszavak) {
    return UzenetEnum.NEM_ERTEM.toString();
  }

  private String mozgat(Set<SzotarInterface> parancsszavak, HelyszinEnum forras, HelyszinEnum cel) {
    Set<ElemInterface> mozgathatoTargyak
      = forras.elemSzuro(AllapotEnum.LATHATO, AllapotEnum.FELVEHETO);
    Set<ElemInterface> mozgatandoTargyak;
    mozgatandoTargyak = parancsszavak.remove(ParancsEnum.MINDEN)
      ? mozgathatoTargyak : Ertelmezo.getElemek();
    if (mozgathatoTargyak.containsAll(mozgatandoTargyak)) {
      HelyszinEnum tmp = forras == HelyszinEnum.LELTAR ? HelyszinEnum.KEZ_LE : HelyszinEnum.KEZ_FEL;
      mozgatandoTargyak.forEach(targy -> targy.setHely(tmp));
      String uzenet = tmp.targyak();
      mozgatandoTargyak.forEach(targy -> targy.setHely(cel));
      mozgatandoTargyak.forEach(targy -> targy.addAllapot(AllapotEnum.VIZSGALT));
      return uzenet;
    } else {
      return UzenetEnum.NEM_ERTEM.toString();
    }
  }

}
