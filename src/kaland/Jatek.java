package kaland;

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

  Jatek() {
    jatekos = new Jatekos(KEZDO_HELYSZIN);
    szoveg = new StringBuilder();
  }

  Jatekos getJatekos() {
    return jatekos;
  }

  boolean fut() {
    return jatekos.jatekbanVan();
  }

  String helyzet() {
    szoveg = new StringBuilder();
    if (jatekos.getHelyszin().getAllapot().contains(Allapot.SOTET)) {
      szoveg.append(Uzenet.SOTET);
    } else {
      szoveg.append(jatekos.getHelyszin().getLeiras());
      String targyak = jatekos.getHelyszin().targyak();
      if (!targyak.isEmpty()) {
        szoveg.append('\n');
        szoveg.append(targyak);
      }
      // sortoro(ellensegSorolas());
    }
    return szoveg.toString();
  }

  Object vegrehajt() {
    return Ertelmezo.vegrehajt(jatekos);
  }

  /**
   * A játékos tevékenysége következtében beálló, előre meghatározott változások.
   */
  void szkript() {
    if (Targy.LABTORLO.getAllapot().contains(Allapot.VIZSGALT)) { // kulcs a lábtörlő alatt
      Targy.KULCS.addAllapot(Allapot.LATHATO);
    }
    if (Targy.ELOTER_PADLO.getAllapot().contains(Allapot.VIZSGALT)) { // felfedi a gödör-csapdát
      Csapda.GODOR.addAllapot(Allapot.LATHATO);
      Targy.CSAPOAJTO.addAllapot(Allapot.LATHATO);
    }
  }

}
