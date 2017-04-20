package kaland;

import com.google.common.collect.Sets;
import java.util.EnumSet;
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
    setVesztett();
    return UzenetEnum.VISZLAT.toString();
  }

  String leltar(Set<SzotarInterface> parancsszavak) {
    return HelyszinEnum.LELTAR.targyak();
  }

  String vizsgal(Set<SzotarInterface> parancsszavak) {
    if (parancsszavak.size() == 1) {
      TargyEnum targy = TargyEnum.valueOf(parancsszavak.iterator().next().toString());
      EnumSet<TargyEnum> lathatoTargyak = helyszin.targySzuro(AllapotEnum.LATHATO);
      lathatoTargyak.addAll(HelyszinEnum.LELTAR.targySzuro());
      if (lathatoTargyak.contains(targy)) {
        targy.addAllapot(AllapotEnum.VIZSGALT);
        return targy.getLeiras();
      } else {
        return UzenetEnum.NEM_LATHATO.toString();
      }
    } else {
      return UzenetEnum.NEM_ERTEM.toString();
    }
  }

  String vesz(Set<SzotarInterface> parancsszavak) {
    return mozgat(parancsszavak, helyszin, HelyszinEnum.LELTAR);
  }

  String tesz(Set<SzotarInterface> parancsszavak) {
    return mozgat(parancsszavak, HelyszinEnum.LELTAR, helyszin);
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
    EnumSet<TargyEnum> mozgathatoTargyak
      = forras.targySzuro(AllapotEnum.LATHATO, AllapotEnum.FELVEHETO);
    EnumSet<TargyEnum> mozgatandoTargyak;
    if (parancsszavak.contains(ParancsEnum.MINDEN)) {
      mozgatandoTargyak = mozgathatoTargyak;
    } else {
      mozgatandoTargyak = Sets.newEnumSet(parancsszavak.stream()
        .filter(szo -> szo.getClass().equals(TargySzotarEnum.class))
        .map(targy -> TargyEnum.valueOf(targy.toString()))
        .collect(Collectors.toSet()), TargyEnum.class);
    }
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
