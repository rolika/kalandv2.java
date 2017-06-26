package kaland;

import java.util.Set;

/**
 * A játék logikáját tartalmazó osztály Alapvetően stringeket ad vissza, a játékos helyzetének,
 * akcióinak függvényében
 *
 * @author rolika
 */
class Jatek {

  private static final Helyszin KEZDO_HELYSZIN = Helyszin.HAZ_ELOTT;

  private final Jatekos jatekos;
  private StringBuilder szoveg;

  /**
   * A konstruktor létrehozza a játékost és egy üres szövegmezőt.
   */
  Jatek() {
    jatekos = new Jatekos(KEZDO_HELYSZIN, Allapot.EL, Allapot.NEM_NYERT, Allapot.NEM_VESZTETT);
    szoveg = new StringBuilder();
  }

  /**
   * A játékos játékban van (nem halt meg, nem nyert, nem vesztett)
   *
   * @return igaz, ha játékban van, hamis, ha nem
   */
  boolean fut() {
    return jatekos.checkAllapot(Allapot.EL, Allapot.NEM_NYERT, Allapot.NEM_VESZTETT);
  }

  /**
   * Felvázolja a játékos helyzetét. Ha nincs sötét: - helyszínleírás - látható és felevhető tárgyak
   * felsorolása. Ha sötét van, és van ellenség, az megöli a játékost, egyébként csak jelzi, hogy
   * sötét van.
   *
   * @return megfelelő szövege üzenet
   */
  String helyzet() {
    szoveg = new StringBuilder();
    Helyszin helyszin = jatekos.getHelyszin();
    if (jatekos.lat()) {
      szoveg.append(helyszin.getNormalLeiras());
      allapotfuggoUzenetek();
      String targyak = helyszin.targyak();
      if (!targyak.isEmpty()) {
        szoveg.append('\n');
        szoveg.append(targyak);
      }
      String ellensegek = helyszin.ellensegek(jatekos);
      if (!ellensegek.isEmpty()) {
        szoveg.append('\n');
        szoveg.append(ellensegek);
        Set<Elem> ellenek = helyszin.elemSzuro(elem -> elem.getClass().equals(Ellenseg.class));
        ellenek.stream() // az aktív ellenségek automatikusan tovább gerjednek támadóvá
          .filter(ellen -> ellen.checkAllapot(Allapot.AKTIV))
          .map(ellen -> (Ellenseg) ellen)
          .forEach(ellenseg -> ellenseg.gerjeszt());
      }
    } else {
      Set<Elem> ellenek = helyszin.elemSzuro(elem -> elem.getClass().equals(Ellenseg.class));
      if (!ellenek.isEmpty()) {
        for (Elem ellen : ellenek) {
          if (ellen.checkAllapot(Allapot.EL) || ellen.checkAllapot(Allapot.AKTIV)
            || ellen.checkAllapot(Allapot.TAMAD)) {
            szoveg.append(Uzenet.SOTETBEN_TAMAD.toString());
            jatekos.removeAllapot(Allapot.EL);
            break;
          }
        }
      } else {
        szoveg.append(Uzenet.SOTET.toString());
      }
    }
    return szoveg.toString();
  }

  /**
   * Meghívja az parancsvégrehajtót. Azért van rá szükség, hogy a konzolos és a gui-változat
   * független lehessen, mivel kell a játékos (amúgy a játékost a konzolban és a gui-ban is
   * deklarálni kellene valahogy); az értelmező a megfelelő metódust reflection-nel hívja meg.
   *
   * @return a játékos szándékának megfelelő szöveges üzenet
   */
  Object vegrehajt() {
    return Ertelmezo.vegrehajt(jatekos);
  }

  /**
   * A játékos tevékenysége következtében beálló, előre meghatározott változások.
   */
  void szkript() {
    if (Targy.LABTORLO.checkAllapot(Allapot.VIZSGALT)) { // kulcs a lábtörlő alatt
      Targy.KIS_KULCS.addAllapot(Allapot.LATHATO);
    }
    if (Targy.ELOTER_PADLO.checkAllapot(Allapot.VIZSGALT)) { // felfedi a gödör-csapdát
      Csapda.CSAPOAJTO.addAllapot(Allapot.LATHATO);
    }
    if (Targy.SZOBOR.checkAllapot(Allapot.VIZSGALT)) { // felfedi a szobor karját
      Targy.SZOBOR_KAR.addAllapot(Allapot.LATHATO);
    }
    if (Targy.KANDALLO.checkAllapot(Allapot.VIZSGALT)) { // felfedi a piszkavasat
      Targy.PISZKAVAS.addAllapot(Allapot.LATHATO);
    }
    if (Targy.SZOBOR_KAR.checkAllapot(Allapot.AKTIV)) { // hatástalanítja a penge-csapdát
      Csapda.PENGE.addAllapot(Allapot.LATHATO);
    }
    if (Targy.KONYHASZEKRENY.checkAllapot(Allapot.VIZSGALT)) { // felfedi a fiókot és az ajtót
      Targy.FIOK.addAllapot(Allapot.LATHATO);
      Targy.KONYHASZEKRENYAJTO.addAllapot(Allapot.LATHATO);
    }
    if (Targy.FIOK.checkAllapot(Allapot.NYITVA)) { // felfedi a kacatot
      Targy.KACAT.addAllapot(Allapot.LATHATO);
      Targy.FIOK.setLeiras(Uzenet.FIOK.toString());
    }
    if (Targy.KACAT.checkAllapot(Allapot.VIZSGALT)) { // felfedi a ládakulcsot
      Targy.NAGY_KULCS.addAllapot(Allapot.LATHATO);
    }
    if (Targy.GERENDA.checkAllapot(Allapot.AKTIV) && Targy.KOTEL.checkAllapot(Allapot.AKTIV)) {
      Csapda.KURTO.addAllapot(Allapot.LATHATO); // hatástalanítja kürtőt
      Targy.KOTEL.removeAllapot(Allapot.HASZNALHATO, Allapot.FELVEHETO); // és már nem lehet
      Targy.GERENDA.removeAllapot(Allapot.HASZNALHATO); // visszacsinálni
    }
    if (Ajto.LADA.checkAllapot(Allapot.NYITVA)) { // ha egyszer kinyílt,
      Ajto.LADA.removeAllapot(Allapot.NYITHATO); // nem lehet visszacsukni
    }
    if (Targy.TORMELEK.checkAllapot(Allapot.VIZSGALT)) { // a jegyzet a törmelék között bújik meg
      Targy.JEGYZET.addAllapot(Allapot.LATHATO);
    }
    if (Targy.JEGYZET.checkAllapot(Allapot.VIZSGALT) && Targy.GEP.checkAllapot(Allapot.VIZSGALT)) {
      Targy.NYOMOGOMB.addAllapot(Allapot.LATHATO);
    }
    if (Targy.GEP.checkAllapot(Allapot.VIZSGALT)) {
      Targy.JEGYZET.setLeiras(Uzenet.JEGYZET.toString());
    }
    if (Targy.NYOMOGOMB.checkAllapot(Allapot.AKTIV) && Ajto.PORTAL.checkAllapot(Allapot.ZARVA)) {
      Ajto.PORTAL.removeAllapot(Allapot.ZARVA);
      Ajto.PORTAL.addAllapot(Allapot.CSUKVA);
    }
    if (!Targy.NYOMOGOMB.checkAllapot(Allapot.AKTIV)) {
      Ajto.PORTAL.removeAllapot(Allapot.NYITVA, Allapot.CSUKVA);
      Ajto.PORTAL.addAllapot(Allapot.ZARVA);
    }
  }

  private void allapotfuggoUzenetek() {
    if (jatekos.getHelyszin() == Helyszin.PADLAS_VEGE
      && Targy.KOTEL.checkAllapot(Allapot.AKTIV) && !Ajto.LADA.checkAllapot(Allapot.NYITVA)) {
      szoveg.append('\n');
      szoveg.append(Uzenet.KOTEL_1);
    } else if (jatekos.getHelyszin() == Helyszin.PADLAS_VEGE
      && Targy.KOTEL.checkAllapot(Allapot.AKTIV) && Ajto.LADA.checkAllapot(Allapot.NYITVA)) {
      szoveg.append('\n');
      szoveg.append(Uzenet.KOTEL_2);
    }
    if (jatekos.getHelyszin() == Helyszin.REJTETT_PINCE
      && Targy.JEGYZET.checkAllapot(Allapot.VIZSGALT) && Targy.GEP.checkAllapot(Allapot.VIZSGALT)) {
      szoveg.append('\n');
      szoveg.append(Uzenet.GOMB);
    }
  }

}
