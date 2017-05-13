package kaland;

import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A játékost megvalósító osztály kalandjátékhoz
 *
 * @author rolika
 */
final class Jatekos {

  private final EnumSet<Allapot> allapot;
  private Helyszin helyszin;

  Jatekos(Helyszin helyszin) {
    allapot = EnumSet.of(Allapot.EL, Allapot.NEM_NYERT, Allapot.NEM_VESZTETT);
    setHelyszin(helyszin);
  }

  boolean jatekbanVan() {
    return allapot.contains(Allapot.EL) && allapot.contains(Allapot.NEM_NYERT)
      && allapot.contains(Allapot.NEM_VESZTETT);
  }

  Helyszin getHelyszin() {
    return helyszin;
  }

  void setMeghalt() {
    allapot.remove(Allapot.EL);
  }

  void setNyert() {
    allapot.remove(Allapot.NEM_NYERT);
  }

  void setVesztett() {
    allapot.remove(Allapot.NEM_VESZTETT);
  }

  void setHelyszin(Helyszin helyszin) {
    this.helyszin = helyszin;
    helyszin.setKijaratok(Kijarat.valueOf(helyszin.toString())); // ugyanaz a konstans nevük
  }

  String megy(Irany irany) {
    Helyszin ujHelyszin = helyszin.getKijarat(irany);
    if (ujHelyszin == null) {
      return Uzenet.ARRA_NEM.toString();
    } else {
      Csapda csapda = helyszin.csapda(ujHelyszin);
      if (csapda != Csapda.NINCS && !csapda.getAllapot().contains(Allapot.LATHATO)) {
        setMeghalt();
        return csapda.getAktiv();
      }
      Ajto ajto = helyszin.ajto(ujHelyszin);
      if (ajto == Ajto.NINCS || ajto.getAllapot().contains(Allapot.NYITVA)) {
        setHelyszin(ujHelyszin);
        return csapda.getAllapot().contains(Allapot.LATHATO)
          ? csapda.getInaktiv() : Uzenet.RENDBEN.toString();
      } else {
        if (ajto.getAllapot().contains(Allapot.CSUKVA)) {
          return Uzenet.CSUKVA.getNevelo(ajto);
        } else { // különben be van zárva
          return Uzenet.ZARVA.getNevelo(ajto);
        }
      }
    }
  }

  String kilep(Set<Szotar> parancsszavak) {
    setVesztett();
    return Uzenet.VISZLAT.toString();
  }

  String leltar(Set<Szotar> parancsszavak) {
    return Helyszin.LELTAR.targyak();
  }

  String vesz(Set<Szotar> parancsszavak) {
    return mozgat(parancsszavak, helyszin, Helyszin.LELTAR);
  }

  String tesz(Set<Szotar> parancsszavak) {
    return mozgat(parancsszavak, Helyszin.LELTAR, helyszin);
  }

  String vizsgal(Set<Szotar> parancsszavak) {
    if (parancsszavak.size() == 1) {
      Elem elem = Ertelmezo.getElem(parancsszavak.iterator().next());
      Set<Elem> lathatoTargyak = helyszin.elemSzuro(Allapot.LATHATO);
      lathatoTargyak.addAll(Helyszin.LELTAR.elemSzuro());
      if (lathatoTargyak.contains(elem)) {
        elem.addAllapot(Allapot.VIZSGALT);
        StringBuilder leiras = new StringBuilder(elem.getLeiras());
        if (elem.getAllapot().contains(Allapot.NYITHATO)) {
          leiras.append('\n');
        }
        if (elem.getAllapot().contains(Allapot.NYITVA)) {
          leiras.append(Uzenet.NYITVA.getNevelo(elem));
        } else if (elem.getAllapot().contains(Allapot.CSUKVA)) {
          leiras.append(Uzenet.CSUKVA.getNevelo(elem));
        } else if (elem.getAllapot().contains(Allapot.ZARVA)) {
          leiras.append(Uzenet.ZARVA.getNevelo(elem));
        }
        return leiras.toString();
      }
    }
    return Uzenet.NEM_ERTEM.toString();
  }

  String hasznal(Set<Szotar> parancsszavak) {
    for (Elem elem : Ertelmezo.getElemek()) {
      if (!elem.getAllapot().contains(Allapot.HASZNALHATO)) {
        return Uzenet.NEM_LEHET.toString();
      } else if (!elem.getHely().contains(helyszin) && !elem.getHely().contains(Helyszin.LELTAR)) {
        return Uzenet.NEM_ERTEM.toString();
      } else if (!elem.getAllapot().contains(Allapot.LATHATO)) {
        return Uzenet.NEM_LATHATO.toString();
      } else if (elem.getPar() != Targy.NINCS && (!elem.getPar().getHely().contains(helyszin)
        && !elem.getPar().getHely().contains(Helyszin.LELTAR))) {
        return Uzenet.MIVEL.toString();
      }
    }
    for (Elem elem : Ertelmezo.getElemek()) {
      if (elem.getAllapot().contains(Allapot.KAPCSOLGATHATO)) {
        if (elem.getAllapot().contains(Allapot.AKTIV)) {
          elem.removeAllapot(Allapot.AKTIV);
        } else {
          elem.addAllapot(Allapot.AKTIV);
        }
      } else {
        elem.addAllapot(Allapot.AKTIV);
      }
    }return Uzenet.RENDBEN.toString();
  }

  String nyit(Set<Szotar> parancsszavak) {
    Set<Elem> parancsElemek = Ertelmezo.getElemek();
    Elem nyitandoElem;
    try {
      nyitandoElem = parancsElemek.stream()
        .filter(elem -> elem.getAllapot().contains(Allapot.NYITHATO) &&
          elem.getAllapot().contains(Allapot.LATHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      return Uzenet.NEM_ERTEM.toString();
    }
    Set<Elem> nyithatoElemek = helyszin.elemSzuro(Allapot.NYITHATO);
    if (!nyithatoElemek.contains(nyitandoElem)) {
      return Uzenet.NEM_ERTEM.toString();
    } else if (nyitandoElem.getAllapot().contains(Allapot.CSUKVA)) {
      nyitandoElem.removeAllapot(Allapot.CSUKVA);
      nyitandoElem.addAllapot(Allapot.NYITVA);
      return Uzenet.RENDBEN.toString();
    } else if (nyitandoElem.getAllapot().contains(Allapot.ZARVA)
      && !parancsElemek.contains(nyitandoElem.getPar())) {
      return Uzenet.MIVEL.toString();
    } else if (!Helyszin.LELTAR.elemSzuro().contains(nyitandoElem.getPar())) {
      return Uzenet.NINCS_NALAD.toString();
    } else if (nyitandoElem.getAllapot().contains(Allapot.NYITVA)) {
      return Uzenet.NYITVA.getNevelo(nyitandoElem);
    } else {
      nyitandoElem.removeAllapot(Allapot.ZARVA, Allapot.CSUKVA);
      nyitandoElem.addAllapot(Allapot.NYITVA);
      return Uzenet.RENDBEN.toString();
    }
  }

  String csuk(Set<Szotar> parancsszavak) {
    Set<Elem> parancsElemek = Ertelmezo.getElemek();
    Elem csukandoElem;
    try {
      csukandoElem = parancsElemek.stream()
        .filter(elem -> elem.getAllapot().contains(Allapot.NYITHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      return Uzenet.NEM_ERTEM.toString();
    }
    Set<Elem> csukhatoElemek = helyszin.elemSzuro(Allapot.NYITHATO);
    if (!csukhatoElemek.contains(csukandoElem)) {
      return Uzenet.NEM_ERTEM.toString();
    } else if (csukandoElem.getAllapot().contains(Allapot.NYITVA)) {
      csukandoElem.removeAllapot(Allapot.NYITVA);
      csukandoElem.addAllapot(Allapot.CSUKVA);
      return Uzenet.RENDBEN.toString();
    } else {
      return Uzenet.CSUKVA.getNevelo(csukandoElem);
    }
  }

  String zar(Set<Szotar> parancsszavak) {
    Set<Elem> parancsElemek = Ertelmezo.getElemek();
    Elem zarandoElem;
    try {
      zarandoElem = parancsElemek.stream()
        .filter(elem -> elem.getAllapot().contains(Allapot.NYITHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      return Uzenet.NEM_ERTEM.toString();
    }
    Set<Elem> zarhatoElemek = helyszin.elemSzuro(Allapot.NYITHATO);
    if (!zarhatoElemek.contains(zarandoElem)) {
      return Uzenet.NEM_ERTEM.toString();
    } else if (zarandoElem.getPar() == Targy.NINCS) {
      return Uzenet.NEM_LEHET.toString();
    } else if (!parancsElemek.contains(zarandoElem.getPar())) {
      return Uzenet.MIVEL.toString();
    } else if (!Helyszin.LELTAR.elemSzuro().contains(zarandoElem.getPar())) {
      return Uzenet.NINCS_NALAD.toString();
    } else if ((zarandoElem.getAllapot().contains(Allapot.NYITVA)
      || zarandoElem.getAllapot().contains(Allapot.CSUKVA))) {
      zarandoElem.removeAllapot(Allapot.NYITVA, Allapot.CSUKVA);
      zarandoElem.addAllapot(Allapot.ZARVA);
      return Uzenet.RENDBEN.toString();
    } else {
      return Uzenet.ZARVA.getNevelo(zarandoElem);
    }
  }

  String tamad(Set<Szotar> parancsszavak) {
    return "Játékos támad.";
  }

  String hosszu(Set<Szotar> parancsszavak) {
    helyszin.setLeiroMod(Allapot.HOSSZU);
    return Uzenet.RENDBEN.toString();
  }

  String rovid(Set<Szotar> parancsszavak) {
    helyszin.setLeiroMod(Allapot.ROVID);
    return Uzenet.RENDBEN.toString();
  }

  String normal(Set<Szotar> parancsszavak) {
    helyszin.setLeiroMod(Allapot.NORMAL);
    return Uzenet.RENDBEN.toString();
  }

  String megerosit(Set<Szotar> parancsszavak) {
    return "Játékos megerősít.";
  }

  String minden(Set<Szotar> parancsszavak) {
    return Uzenet.NEM_ERTEM.toString();
  }

  private String mozgat(Set<Szotar> parancsszavak, Helyszin forras, Helyszin cel) {
    Set<Elem> mozgathatoTargyak
      = forras.elemSzuro(Allapot.LATHATO, Allapot.FELVEHETO);
    Set<Elem> mozgatandoTargyak;
    mozgatandoTargyak = parancsszavak.remove(Parancs.MINDEN)
      ? mozgathatoTargyak : Ertelmezo.getElemek();
    if (mozgathatoTargyak.containsAll(mozgatandoTargyak)) {
      Helyszin tmp = forras == Helyszin.LELTAR ? Helyszin.KEZ_LE : Helyszin.KEZ_FEL;
      mozgatandoTargyak.forEach(targy -> targy.setHely(tmp));
      String uzenet = tmp.targyak();
      mozgatandoTargyak.forEach(targy -> targy.setHely(cel));
      mozgatandoTargyak.forEach(targy -> targy.addAllapot(Allapot.VIZSGALT));
      return uzenet;
    } else {
      return Uzenet.NEM_ERTEM.toString();
    }
  }

}
