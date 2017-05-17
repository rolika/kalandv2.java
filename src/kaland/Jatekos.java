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

  /**
   * Elmozdulás a helyszínről, a következő lehetőségekkel: 1) a játékos falba ütközik, azaz arra nem
   * mehet; 2) a játékos csukva vagy zárva lévő ajtóba ütközik, arra sem mehet; 3) az
   * ajtó/elmozdulás UTÁN csapdába esik és meghal. Fontos, hogy utána: a játékos ugyan ebből nem
   * vesz észre semmit, de a csapda az áthaladás HELYETT fog működésbe lépni. Azaz ebben a játékban
   * a csapdák mindig az adott helyszín bejárata mögött helyezkednek el. Olyat nem lehet, hogy a
   * előbb csapda van és utána jönne az ajtó. Fontos játéktervezési szempont!; 4) a játékos
   * akadálytalanul mozog a célhelyszínre, kikerülve az esetleges csapdát.
   *
   * @param irany a játékos szándékának megfelelő irányenum
   * @return szöveges üzenet a szándékolt elmozdulás következményéről
   */
  String megy(Irany irany) {
    Helyszin ujHelyszin = helyszin.getKijarat(irany);
    // a játékos falba ütközik
    if (ujHelyszin == null) {
      return Uzenet.ARRA_NEM.toString();
    }
    Ajto ajto = helyszin.ajto(ujHelyszin);
    Csapda csapda = helyszin.csapda(ujHelyszin);
    // a játékos csukva vagy zárva lévő ajtóba ütközik
    if (ajto.checkAllapot(Allapot.CSUKVA)) {
      return Uzenet.CSUKVA.getNevelo(ajto);
    } else if (ajto.checkAllapot(Allapot.ZARVA)) {
      return Uzenet.ZARVA.getNevelo(ajto);
    }
    // a játékos csapdába esik és meghal
    if (csapda != Csapda.NINCS && !csapda.checkAllapot(Allapot.LATHATO)) {
      setMeghalt();
      return csapda.getAktiv();
    }
    // a játékos továbbhalad az új helyszínre
    // (nincs ajtó, vagy nyitva van, nincs csapda, vagy már inaktív)
    setHelyszin(ujHelyszin);
    return csapda == Csapda.NINCS ? Uzenet.RENDBEN.toString() : csapda.getInaktiv();
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

  /**
   * Kalandelemek vizsgálata. A parancsnak egy elemmel van igazán értelme, és persze látható elemek
   * esetén. Nyitható elemek esetén kiírja a nyitva-csukva-zárva állapotot is.
   * Magában állva ("megnézem") a helyszín leírását adja vissza.
   *
   * @param parancsszavak
   * @return elemek, helyszínek leírása
   */
  String vizsgal(Set<Szotar> parancsszavak) {
    if (parancsszavak.size() == 1) {
      Elem elem = Ertelmezo.getElem(parancsszavak.iterator().next());
      Set<Elem> lathatoTargyak = helyszin.elemSzuro(Allapot.LATHATO);
      lathatoTargyak.addAll(Helyszin.LELTAR.elemSzuro());
      if (lathatoTargyak.contains(elem)) {
        elem.addAllapot(Allapot.VIZSGALT);
        StringBuilder leiras = new StringBuilder(elem.getLeiras());
        if (elem.checkAllapot(Allapot.NYITHATO)) {
          leiras.append('\n');
        }
        if (elem.checkAllapot(Allapot.NYITVA)) {
          leiras.append(Uzenet.NYITVA.getNevelo(elem));
        } else if (elem.checkAllapot(Allapot.CSUKVA)) {
          leiras.append(Uzenet.CSUKVA.getNevelo(elem));
        } else if (elem.checkAllapot(Allapot.ZARVA)) {
          leiras.append(Uzenet.ZARVA.getNevelo(elem));
        }
        return leiras.toString();
      }
    } else if (parancsszavak.size() < 1) {
      return helyszin.getHosszuLeiras();
    }
    return Uzenet.NEM_ERTEM.toString();
  }

  /**
   * Elem(ek) használata. Lehetséges kimenetelek: 1) az elem nem használható 2) az elem nincs se a
   * helyszínen, se a leltárban 3) az elem nem látható 4) az elemnek van egy kötelezően használandő
   * párja, ami nincs benn a parancsban
   *
   * @param parancsszavak
   * @return szöveges üzenet az eredményről
   */
  String hasznal(Set<Szotar> parancsszavak) {
    Set<Elem> elemek = Ertelmezo.getElemek();
    for (Elem elem : elemek) {
      if (!elem.checkAllapot(Allapot.HASZNALHATO)) {
        return Uzenet.NEM_LEHET.toString();
      } else if (!elem.getHely().contains(helyszin) && !elem.getHely().contains(Helyszin.LELTAR)) {
        return Uzenet.NEM_ERTEM.toString();
      } else if (!elem.checkAllapot(Allapot.LATHATO)) {
        return Uzenet.NEM_LATHATO.toString();
      } else if (elem.getPar() != Targy.NINCS && !elemek.contains(elem.getPar())) {
        return Uzenet.MIVEL.toString();
      }
    }
    for (Elem elem : elemek) {
      if (elem.checkAllapot(Allapot.KAPCSOLGATHATO)) {
        if (elem.checkAllapot(Allapot.AKTIV)) {
          elem.removeAllapot(Allapot.AKTIV);
        } else {
          elem.addAllapot(Allapot.AKTIV);
        }
      } else {
        elem.addAllapot(Allapot.AKTIV);
      }
    }
    return Uzenet.RENDBEN.toString();
  }

  String nyit(Set<Szotar> parancsszavak) {
    Set<Elem> parancsElemek = Ertelmezo.getElemek();
    Elem nyitandoElem;
    try {
      nyitandoElem = parancsElemek.stream()
        .filter(elem -> elem.checkAllapot(Allapot.NYITHATO, Allapot.LATHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      return Uzenet.NEM_ERTEM.toString();
    }
    Set<Elem> nyithatoElemek = helyszin.elemSzuro(Allapot.NYITHATO);
    if (!nyithatoElemek.contains(nyitandoElem)) {
      return Uzenet.NEM_ERTEM.toString();
    } else if (nyitandoElem.checkAllapot(Allapot.CSUKVA)) {
      nyitandoElem.removeAllapot(Allapot.CSUKVA);
      nyitandoElem.addAllapot(Allapot.NYITVA);
      return Uzenet.RENDBEN.toString();
    } else if (nyitandoElem.checkAllapot(Allapot.ZARVA)
      && !parancsElemek.contains(nyitandoElem.getPar())) {
      return Uzenet.MIVEL.toString();
    } else if (!Helyszin.LELTAR.elemSzuro().contains(nyitandoElem.getPar())) {
      return Uzenet.NINCS_NALAD.toString();
    } else if (nyitandoElem.checkAllapot(Allapot.NYITVA)) {
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
        .filter(elem -> elem.checkAllapot(Allapot.NYITHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      return Uzenet.NEM_ERTEM.toString();
    }
    Set<Elem> csukhatoElemek = helyszin.elemSzuro(Allapot.NYITHATO);
    if (!csukhatoElemek.contains(csukandoElem)) {
      return Uzenet.NEM_ERTEM.toString();
    } else if (csukandoElem.checkAllapot(Allapot.NYITVA)) {
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
        .filter(elem -> elem.checkAllapot(Allapot.NYITHATO))
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
    } else if ((zarandoElem.checkAllapot(Allapot.NYITVA)
      || zarandoElem.checkAllapot(Allapot.CSUKVA))) {
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
