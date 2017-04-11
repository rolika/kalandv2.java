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
    for (HelyszinEnum helyszin : HelyszinEnum.values()) {
      helyszin.setBejart(false);
    }
    jatekos = new Jatekos(KEZDO_HELYSZIN);
    szoveg = new StringBuilder();
  }

  Jatekos getJatekos() {
    return jatekos;
  }

  boolean fut() {
    return !(jatekos.isMeghalt() || jatekos.isNyert() || jatekos.isVesztett());
  }

  String helyzet() {
    szkript();
    szoveg = new StringBuilder();
    szoveg.append(jatekos.getHelyszin().getLeiras());
    String targyak = jatekos.targySorolo();
    if (!targyak.isEmpty()) {
      szoveg.append('\n');
      szoveg.append(targyak);
    }
    // sortoro(ellensegSorolas());
    return szoveg.toString();
  }

  Object vegrehajt() {
    return Ertelmezo.vegrehajt(jatekos);
  }

  /**
   * A játékos tevékenysége következtében beálló, előre meghatározott változások.
   */
  private void szkript() {
    if (TargyEnum.LABTORLO.isVizsgalt()) { // kulcs a lábtörlő alatt
      TargyEnum.KULCS.setLathato(true);
    }
  }

}
