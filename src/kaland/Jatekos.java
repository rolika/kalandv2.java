package kaland;

import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A játékost megvalósító osztály kalandjátékhoz
 *
 * @author rolika
 */
final class Jatekos implements Elem {

  private final EnumSet<Allapot> allapot;
  private Helyszin hely;

  /**
   * A konstruktor beállítja a játékos állapotait és elhelyezi a térképen
   *
   * @param hely
   */
  Jatekos(Helyszin hely, Allapot ... allapot) {
    this.allapot = Sets.newEnumSet(Arrays.asList(allapot), Allapot.class);
    setHely(hely);
  }

  @Override
  public String getNev() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public String getLeiras() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public EnumSet<Helyszin> getHely() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public void addAllapot(Allapot... allapot) {
    this.allapot.addAll(Arrays.asList(allapot));
  }

  @Override
  public void removeAllapot(Allapot... allapot) {
    this.allapot.removeAll(Arrays.asList(allapot));
  }

  @Override
  public boolean checkAllapot(Allapot... allapot) {
    return this.allapot.containsAll(Arrays.asList(allapot));
  }

  @Override
  public void setHely(Helyszin hely) {
    this.hely = hely;
    hely.setKijaratok(Kijarat.valueOf(hely.toString())); // ugyanaz a konstans nevük
  }

  @Override
  public Elem getPar() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  @Override
  public void setLeiras(String leiras) {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

  /**
   * A helyszín, amelyben a játékos éppen tartózkodik
   *
   * @return aktuális helyszín
   */
  Helyszin getHelyszin() {
    return hely;
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
    Helyszin ujHelyszin = hely.getKijarat(irany);
    // a játékos falba ütközik
    if (ujHelyszin == null) {
      return Uzenet.ARRA_NEM.toString();
    }
    Ajto ajto = hely.ajto(ujHelyszin);
    Csapda csapda = hely.csapda(ujHelyszin);
    // a játékos csukva vagy zárva lévő ajtóba ütközik
    if (ajto.checkAllapot(Allapot.CSUKVA)) {
      return Uzenet.CSUKVA.getNevelo(ajto);
    } else if (ajto.checkAllapot(Allapot.ZARVA)) {
      return Uzenet.ZARVA.getNevelo(ajto);
    }
    // a játékos csapdába esik és meghal
    if (csapda != Csapda.NINCS && !csapda.checkAllapot(Allapot.LATHATO)) {
      removeAllapot(Allapot.EL);
      return csapda.getAktiv();
    }
    // a játékos továbbhalad az új helyszínre
    // (nincs ajtó, vagy nyitva van, nincs csapda, vagy már inaktív)
    setHely(ujHelyszin);
    return csapda == Csapda.NINCS ? Uzenet.RENDBEN.toString() : csapda.getInaktiv();
  }

  /**
   * A játékos kilép
   *
   * @return viszlát üzenet
   */
  String kilep() {
    removeAllapot(Allapot.NEM_VESZTETT);
    return Uzenet.VISZLAT.toString();
  }

  /**
   * Felsorolja a játékosnál lévő tárgyakat
   *
   * @return
   */
  String leltar() {
    return Helyszin.LELTAR.targyak();
  }

  String vesz() {
    return mozgat(hely, Helyszin.LELTAR);
  }

  String tesz() {
    return mozgat(Helyszin.LELTAR, hely);
  }

  /**
   * Kalandelemek vizsgálata. A parancsnak egy elemmel van igazán értelme, és persze látható elemek
   * esetén. Nyitható elemek esetén kiírja a nyitva-csukva-zárva állapotot is. Magában állva
   * ("megnézem") a helyszín leírását adja vissza.
   *
   * @return elemek, helyszínek leírása
   */
  String vizsgal() {
    Set<Elem> vizsgalandoElem = Ertelmezo.getElemek();
    if (vizsgalandoElem.size() == 1) {
      Elem elem = vizsgalandoElem.iterator().next();
      Set<Elem> lathatoTargyak = hely.elemSzuro(Allapot.LATHATO);
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
    } else if (vizsgalandoElem.size() < 1) {
      return hely.getLeiras();
    }
    return Uzenet.NEM_ERTEM.toString();
  }

  /**
   * Elem(ek) használata. Lehetséges kimenetelek: 1) az elem nem használható 2) az elem nincs se a
   * helyszínen, se a leltárban 3) az elem nem látható 4) az elemnek van egy kötelezően használandó
   * párja, ami nincs benn a parancsban
   *
   * @return megfelelő szöveges üzenet
   */
  String hasznal() {
    Set<Elem> elemek = Ertelmezo.getElemek();
    for (Elem elem : elemek) {
      if (!elem.checkAllapot(Allapot.HASZNALHATO)) {
        return Uzenet.NEM_LEHET.toString();
      } else if (!elem.getHely().contains(hely) && !elem.getHely().contains(Helyszin.LELTAR)) {
        return Uzenet.NEM_ERTEM.toString();
      } else if (!elem.checkAllapot(Allapot.LATHATO)) {
        return Uzenet.NEM_LATHATO.toString();
      } else if (elem.getPar() != Targy.NINCS && !elemek.contains(elem.getPar())) {
        return Uzenet.MIVEL.toString();
      }
    }
    elemek.forEach(elem -> {
      if (elem.checkAllapot(Allapot.KAPCSOLGATHATO)) {
        if (elem.checkAllapot(Allapot.AKTIV)) {
          elem.removeAllapot(Allapot.AKTIV);
        } else {
          elem.addAllapot(Allapot.AKTIV);
        }
      } else {
        elem.addAllapot(Allapot.AKTIV);
      }
    });
    return Uzenet.RENDBEN.toString();
  }

  /**
   * A játékos megpróbál kinyitni valamit: 1) csak nyitható és látható elemeket lehet kinyitni 2) az
   * elemnek nyilván a helyszínen kell lennie 3) az elemnek csukva kell lennie 4) ha zárva van, kell
   * a párja (kulcsa) is a parancsba (és nyilván a játékosnál kell legyen) 5) ha az elem már nyitva
   * van
   *
   * @return megfelelő szöveges üzenet
   */
  String nyit() {
    Set<Elem> parancsElemek = Ertelmezo.getElemek();
    Elem nyitandoElem;
    try {
      nyitandoElem = parancsElemek.stream()
        .filter(elem -> elem.checkAllapot(Allapot.NYITHATO, Allapot.LATHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      return Uzenet.NEM_ERTEM.toString();
    }
    Set<Elem> nyithatoElemek = hely.elemSzuro(Allapot.NYITHATO, Allapot.LATHATO);
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

  /**
   * A játékos megpróbál becsukni valamit: 1) csak nyitható és látható elemeket lehet becsukni 2) az
   * elemnek nyilván a helyszínen kell lennie 3) az elemnek nyitva kell lennie 4) ha az elem már
   * csukva van
   *
   * @return megfelelő szöveges üzenet
   */
  String csuk() {
    Set<Elem> parancsElemek = Ertelmezo.getElemek();
    Elem csukandoElem;
    try {
      csukandoElem = parancsElemek.stream()
        .filter(elem -> elem.checkAllapot(Allapot.NYITHATO, Allapot.LATHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      return Uzenet.NEM_ERTEM.toString();
    }
    Set<Elem> csukhatoElemek = hely.elemSzuro(Allapot.NYITHATO, Allapot.LATHATO);
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

  /**
   * A játékos megpróbál bezárni valamit: 1) csak nyitható és látható elemeket lehet becsukni 2) az
   * elemnek nyilván a helyszínen kell lennie 3) az elemnek nyitva vagy csukva kell lennie 4) ha az
   * elemnek van kulcsa, az is a parancsban ill. a játékosnál legyen
   *
   * @return megfelelő szöveges üzenet
   */
  String zar() {
    Set<Elem> parancsElemek = Ertelmezo.getElemek();
    Elem zarandoElem;
    try {
      zarandoElem = parancsElemek.stream()
        .filter(elem -> elem.checkAllapot(Allapot.NYITHATO, Allapot.LATHATO))
        .iterator().next();
    } catch (NoSuchElementException e) {
      return Uzenet.NEM_ERTEM.toString();
    }
    Set<Elem> zarhatoElemek = hely.elemSzuro(Allapot.NYITHATO, Allapot.LATHATO);
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

  /**
   * A játékos megtámad valamit: 1) a támadás céljának megtámadhatónak, láthatónak és a helyszínen
   * lévőnek kell lennie 2) ha van párja (ellenszere, ellenfegyvere), a játékosnál kell lennie, ill
   * szerepelnie kell a parancsban
   *
   * @return megfelelő szöveges üzenet
   */
  String tamad() {
    return "Játékos támad.";
  }

  /**
   * Mindig a helyszín hosszú leírását közli
   *
   * @return rendben üzenet
   */
  String hosszu() {
    hely.setLeiroMod(Allapot.HOSSZU);
    return Uzenet.RENDBEN.toString();
  }

  /**
   * Mindig a helyszín rövid leírását közli
   *
   * @return rendben üzenet
   */
  String rovid() {
    hely.setLeiroMod(Allapot.ROVID);
    return Uzenet.RENDBEN.toString();
  }

  /**
   * Bejárt helyszín esetén a rövid, új esetén a hosszú leírást közli
   *
   * @return rendben üzenet
   */
  String normal() {
    hely.setLeiroMod(Allapot.NORMAL);
    return Uzenet.RENDBEN.toString();
  }

  /**
   * A játékos igennel vagy nemmel válaszol egy kérdésre.
   *
   * @return megfelelő szöveges üzenet
   */
  String megerosit() {
    return "Játékos megerősít.";
  }

  private String mozgat(Helyszin forras, Helyszin cel) {
    Set<Elem> mozgathatoTargyak = forras.elemSzuro(Allapot.LATHATO, Allapot.FELVEHETO);
    Set<Elem> mozgatandoTargyak = Ertelmezo.getElemek();
    if (mozgatandoTargyak.remove(Targy.MINDEN)) {
      mozgatandoTargyak = mozgathatoTargyak;
    }
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

  @Override
  public JelzoSzotar getJelzo() {
    throw new UnsupportedOperationException("Nincs szükség rá.");
  }

}
