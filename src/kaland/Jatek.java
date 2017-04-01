package kaland;

/**
 * A játék logikáját tartalmazó osztály
 * Alapvetően stringeket ad vissza, a játékos helyzetének, akcióinak függvényében
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
    szoveg = new StringBuilder();
    szoveg.append(jatekos.helyszinLeiras());
    String targyak = jatekos.lathatoFelvehetoTargyak();
    if (!targyak.isEmpty()) {
      szoveg.append('\n');
      szoveg.append(targyak);
    }
    // sortoro(targySorolas());
    // sortoro(ellensegSorolas());
    return szoveg.toString();
  }
  
  Object vegrehajt() {
    return Ertelmezo.vegrehajt(jatekos);
  }
  
}
