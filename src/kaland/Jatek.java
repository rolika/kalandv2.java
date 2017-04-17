package kaland;

/**
 * A játék logikáját tartalmazó osztály Alapvetően stringeket ad vissza, a játékos helyzetének,
 * akcióinak függvényében
 *
 * @author rolika
 */
class Jatek {

  private static final HelyszinEnum KEZDO_HELYSZIN = HelyszinEnum.HAZ_ELOTT;

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
    szkript();
    szoveg = new StringBuilder();
    if (jatekos.getHelyszin().getAllapot().contains(AllapotEnum.SOTET)) {
      szoveg.append(UzenetEnum.SOTET);
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
  private void szkript() {
    if (TargyEnum.LABTORLO.getAllapot().contains(AllapotEnum.VIZSGALT)) { // kulcs a lábtörlő alatt
      TargyEnum.KULCS.addAllapot(AllapotEnum.LATHATO);
    }
  }

}
